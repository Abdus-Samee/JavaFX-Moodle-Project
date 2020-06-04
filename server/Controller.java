package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

public class Controller{
    private Main main;
    private network net;
    private fileNet file_net;
    @FXML private TextArea area;
    @FXML private TextField field;
    @FXML private Button btn;

    public void setNet(network net){
        this.net = net;
    }

    public void setFileNet(fileNet file_net){
        this.file_net = file_net;
    }

    public void setMain(Main main){
        this.main = main;
    }
}
