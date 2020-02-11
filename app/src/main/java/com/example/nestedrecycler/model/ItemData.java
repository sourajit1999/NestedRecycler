package com.example.nestedrecycler.model;

public class ItemData {

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String image,name;


    public ItemData() {
    }

    public ItemData(String image, String name) {
        this.image = image;
        this.name = name;
    }
}
