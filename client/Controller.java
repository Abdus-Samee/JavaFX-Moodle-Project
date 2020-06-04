package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller{
    private Main main;
    private login logIn;
    private network net;
    private Stage st;
    @FXML private TextArea file_name;
    @FXML private Button btn_file;
    @FXML private Button btn_logout;
    @FXML TextArea active_user;

    public void add(fileContainer fc) throws Exception {
        if(fc.getFileSize() == 0) active_user.appendText(fc.getUserName() + '\n');
        else file_name.appendText(fc.getFileName() + '\n');
    }

    @FXML
    public void send(){
        try{
            FileChooser chooser = new FileChooser();
            chooser.setTitle("JavaFX Projects");
            String currentDir = System.getProperty("user.home");
            File defaultDirectory = new File(currentDir);
            chooser.setInitialDirectory(defaultDirectory);
            Stage ds = (Stage)btn_file.getScene().getWindow();
            File f = chooser.showOpenDialog(ds);

            byte[] pdf = new byte [(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(pdf,0,pdf.length);
            fileContainer fc = new fileContainer();
            String name = f.getName();

            try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Asus\\Desktop\\file.txt",true))){
                bw.write(name + '\n');
                bw.close();
                file_name.appendText(name + '\n');
            }catch(Exception e){
                System.out.println(e);
            }
            fc.setProperty(name, net.getUsername(), (int) f.length(), pdf);
            net.write(fc);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    public void handle(){

    }

    public void setNet(network net){
        this.net = net;
    }

    public void setStage(Stage st) { this.st = st; }

    public void setlogIn(login logIn){
        this.logIn = logIn;
    }
}
