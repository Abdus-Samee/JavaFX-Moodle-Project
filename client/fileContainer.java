package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

public class fileContainer implements Serializable {
    private String fileName;
    private String userName;
    private Integer fileSize;
    private byte[] pdf;

    public void setProperty(String fileName, String userName, Integer fileSize, byte[] pdf){
        this.fileName = fileName;
        this.userName = userName;
        this.fileSize = fileSize;
        this.pdf = pdf;
    }

    public String getFileName(){ return fileName; }

    public String getUserName() { return userName; }

    public Integer getFileSize(){ return fileSize; }

    public byte[] getPdf(){ return pdf; }
}
