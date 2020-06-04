package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class login {
    private Main main;
    @FXML Button btn;
    @FXML private TextField user;
    @FXML private TextField pass;

    @FXML
    public void logIn(ActionEvent event){
        String username,password;
        username = user.getText();
        password = pass.getText();

        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Asus\\Desktop\\signup.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Asus\\Desktop\\login.txt",true))){
            while(true){
                String line = br.readLine();
                String[] arr = line.split(" ");
                if(arr[0].equals(username) && arr[1].equals(password)){
                    bw.write(username + '\n');
                    bw.close();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("sample.fxml"));
                    Parent root = loader.load();
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Main window");
                    primaryStage.setScene(new Scene(root, 300, 275));

                    Controller controller = loader.getController();
                    controller.setlogIn(this);
                    Socket s = new Socket("127.0.0.1",8888);
                    DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    network net = new network(s,dis,dos,controller,username);
                    controller.setNet(net);
                    controller.setStage(primaryStage);

                    Stage stage = (Stage) btn.getScene().getWindow();
                    stage.close();

                    primaryStage.show();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void signUp(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("signup.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Signup window");
        primaryStage.setScene(new Scene(root, 300, 275));
        signup controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setLogin(this);

        primaryStage.show();
    }

    public void setMain(Main main){
        this.main = main;
    }
}
