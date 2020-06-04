package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class network extends Thread{
    private ServerSocket ss;
    private Controller controller;
    private List<Socket> socket_set = new ArrayList<Socket>();
    private List<ObjectOutputStream> os_set = new ArrayList<ObjectOutputStream>();

    public network(ServerSocket ss,Controller controller) throws Exception {
        this.ss = ss;
        this.controller = controller;

        while (true){
            Socket s = ss.accept();
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
            os_set.add(os);
            socket_set.add(s);
            addUser(s,os);
            connThread conn = new connThread(s,os,is,controller);
            conn.start();
        }
    }

    public void addUser(Socket s, ObjectOutputStream os){
        try {
                //ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
                try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Asus\\Desktop\\login.txt"))){
                    while(true){
                        String user = br.readLine();
                        if(user == null) break;
                        fileContainer fc = new fileContainer();
                        fc.setProperty(null,user,0, null);
                        os.writeObject(fc);
                        os.reset();
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    private class connThread extends Thread{
        private Socket s;
        private ObjectOutputStream os;
        private ObjectInputStream is;
        private Controller controller;

        public connThread(Socket s, ObjectOutputStream os, ObjectInputStream is, Controller controller){
            this.s = s;
            this.os = os;
            this.is = is;
            this.controller = controller;
        }

        /*public void write(String message) throws IOException {
            dos.writeUTF(message);
            dos.flush();
        }*/

        @Override
        public void run(){
            while(true){
                try {
                    fileContainer fc = (fileContainer)is.readObject();
                    for(ObjectOutputStream os : os_set){
                        if(this.os != os){
                            os.writeObject(fc);
                            os.reset();
                        }
                    }
                    if(fc.getFileSize() != 0){
                        String path = "F:\\" + fc.getFileName();
                        byte[] arr = fc.getPdf();
                        File file = new File(path);
                        OutputStream fos = new FileOutputStream(file);
                        fos.write(arr);
                        fos.close();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
