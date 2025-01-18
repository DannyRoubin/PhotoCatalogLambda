package org.example.model;

public class ImageTagPayloadModel {
    private final String id;
    private final String PhotoID;
    private final String photoGUID;
    private final String tags;

    // Constructor
    public ImageTagPayloadModel(String photoID, String photoGUID, String tags) {
        this.id = photoID;
        this.PhotoID = photoID;
        this.photoGUID = photoGUID;
        this.tags = tags;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPhotoID() {
        return PhotoID;
    }

    public String getPhotoGUID() {
        return photoGUID;
    }

    public String getTags() {
        return tags;
    }
}
