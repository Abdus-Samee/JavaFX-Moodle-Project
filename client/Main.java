package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Login window");
        primaryStage.setScene(new Scene(root, 300, 275));
        login controller = loader.getController();
        controller.setMain(this);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
