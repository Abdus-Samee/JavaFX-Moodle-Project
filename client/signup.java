package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class signup {
    private Stage stage;
    private login main;
    @FXML public Button close;
    @FXML private TextField signup_user;
    @FXML private TextField signup_pass;

    @FXML
    public void go(ActionEvent event){
        int found = 1;
        String username,password;
        username = signup_user.getText();
        password = signup_pass.getText();
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Asus\\Desktop\\signup.txt"))){
            while(true){
                String line = br.readLine();
                if(line == null) break;
                String[] arr = line.split(" ");
                if(arr[0].equals(username)){
                    found = 0;
                    break;
                }
            }
            if(found == 1){
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Asus\\Desktop\\signup.txt", true))) {
                    bw.write('\n' + username + " " + password );
                }catch (Exception e){
                    System.out.println(e);
                }
                Stage stage = (Stage) close.getScene().getWindow();
                stage.close();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setLogin(login main){
        this.main = main;
    }
}
