package com.home.work.Model;

import java.io.Serializable;

public class DataModel implements Serializable {
    String name;
    String imageURL;

    public DataModel(String name, String url) {
        this.name = name;
        this.imageURL = url;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}
