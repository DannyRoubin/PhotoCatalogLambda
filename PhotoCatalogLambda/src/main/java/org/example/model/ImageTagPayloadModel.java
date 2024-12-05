package org.example.model;

import java.io.InputStream;

public class ImageTagPayloadModel {
    private final String photoID;
    private final String photoGUID;
    private final String tags;


    // Constructor
    public ImageTagPayloadModel(String photoID, String photoGUID, String tags, InputStream inputStream) {
        this.photoID = photoID;
        this.photoGUID = photoGUID;
        this.tags = tags;
    }

    // Getters
    public String getPhotoID() {
        return photoID;
    }

    public String getPhotoGUID() {
        return photoGUID;
    }

    public String getTags() {
        return tags;
    }
}
