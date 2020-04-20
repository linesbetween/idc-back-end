package org.utpe.freeopenuniversity.intelligentdocumentclassifier.service.dataProcess;

import java.io.File;

public enum Constant {
    DATAPATH(System.getProperty("user.dir") + File.separator + "data");
    public String value;
    Constant(String value) {
        this.value = value;
    }
}
