package org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Document
public class FileUploaded {
    @Id
    private String id;
    private Binary file;
    private String color;

    public FileUploaded(MultipartFile file, String color) throws IOException {
        this.file = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Binary getFile() {
        return file;
    }

    public void setFile(MultipartFile file) throws IOException {
        this.file = new Binary(BsonBinarySubType.BINARY, file.getBytes());
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
