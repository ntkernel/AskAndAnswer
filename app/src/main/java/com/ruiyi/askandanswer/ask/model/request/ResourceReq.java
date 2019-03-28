package com.ruiyi.askandanswer.ask.model.request;

import java.io.Serializable;

public class ResourceReq implements Serializable {

    private String guid;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    private String extension;


}
