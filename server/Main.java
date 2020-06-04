package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.ServerSocket;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));

        Controller controller = loader.getController();
        controller.setMain(this);
        ServerSocket ss = new ServerSocket(8888);
        //ServerSocket file_ss = new ServerSocket(9999);
        network net = new network(ss,controller);
        //fileNet file_net = new fileNet(file_ss,controller);
        controller.setNet(net);
        //controller.setFileNet(file_net);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
