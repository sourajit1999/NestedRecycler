package com.example.nestedrecycler.model;

public class ItemData {

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public void setImage(int image){
//        this.image = image;
//    }
//        public int getImage() {
//        return image;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String name;
    private String image;
//    private int image;


    public ItemData() {
    }

    public ItemData(String image, String name) {
        this.image = image;
        this.name = name;
    }
}
