package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageTagPayloadModel {
    private final String id;
    private String photoID;
    private String photoGUID;
    private String tags;

    public ImageTagPayloadModel() {
        this.id = null;
    }

    @JsonCreator
    public ImageTagPayloadModel(
            @JsonProperty("photoID") String photoID,
            @JsonProperty("photoGUID") String photoGUID,
            @JsonProperty("tags") String tags) {
        this.id = photoID;
        this.photoID = photoID;
        this.photoGUID = photoGUID;
        this.tags = tags;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPhotoID() {
        return photoID;
    }

    public String getPhotoGUID() {
        return photoGUID;
    }

    public String getTags() {
        return tags;
    }

    // Setters (excluding `id` since it's final)
    public void setPhotoID(String photoID) {
        this.photoID = photoID;
    }

    public void setPhotoGUID(String photoGUID) {
        this.photoGUID = photoGUID;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
