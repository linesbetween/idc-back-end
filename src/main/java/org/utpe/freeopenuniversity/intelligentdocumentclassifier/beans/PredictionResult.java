package org.utpe.freeopenuniversity.intelligentdocumentclassifier.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PredictionResult {

    @Id
    public String id;

    private String result;

    public PredictionResult(String result) {
        this.result = result;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
