package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class network{
    private Socket s;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private DataOutputStream dos;
    private DataInputStream dis;
    private Controller controller;
    private connThread conn;
    private String username;

    public network(Socket s, DataInputStream dis,DataOutputStream dos,Controller controller,String username) throws Exception {
        this.s = s;
        this.os = new ObjectOutputStream(s.getOutputStream());
        this.is = new ObjectInputStream(s.getInputStream());
        this.dis = dis;
        this.dos = dos;
        this.controller = controller;
        this.conn = new connThread();
        this.username = username;
        //dos.writeUTF(username);
        fileContainer fc = new fileContainer();
        fc.setProperty("",this.username,0, null);
        os.writeObject(fc);
        os.reset();
        conn.start();
    }

    public String getUsername() { return username; }

    public void write(fileContainer fc) throws Exception{
        os.writeObject(fc);
        os.flush();
    }

    private class connThread extends Thread{

        @Override
        public void run(){
            while(true){
                try {
                    fileContainer fc = (fileContainer)is.readObject();
                    controller.add(fc);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
