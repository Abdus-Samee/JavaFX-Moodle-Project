package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class fileNet {
    private ServerSocket ss;
    private Controller controller;
    private List<Socket> file_socket_set = new ArrayList<Socket>();

    public fileNet(ServerSocket ss, Controller controller) throws Exception{
        this.ss = ss;
        this.controller = controller;

        while (true){
            Socket s = ss.accept();
            file_socket_set.add(s);
            ObjectInputStream is = new ObjectInputStream(s.getInputStream());
            fileThread file_thread = new fileThread(s,is,controller);
            file_thread.start();
        }
    }

    private class fileThread extends Thread{
        private Socket s;
        private ObjectInputStream is;
        private Controller controller;
        //private InputStream is;

        public fileThread(Socket s, ObjectInputStream is, Controller controller) throws Exception {
            this.s = s;
            this.is = is;
            this.controller = controller;
            //this.fis = s.getInputStream();
        }

        @Override
        public void run(){
            while(true){
                try{
                    fileContainer fc = (fileContainer)is.readObject();
                    String path = "F:\\" + fc.getFileName();
                    byte[] arr = fc.getPdf();
                    File file = new File(path);
                    OutputStream fos = new FileOutputStream(file);
                    fos.write(arr);
                    fos.close();
                    for(Socket socket : file_socket_set){
                        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        dos.writeUTF(fc.getFileName());
                        dos.flush();
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
            }
        }
    }
}
