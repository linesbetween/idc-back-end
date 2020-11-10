package org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document
public class FileUploaded {
    @Id
    private String id;
    private MultipartFile file;
    private String color;

    public FileUploaded(MultipartFile file, String color) {
        this.file = file;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
