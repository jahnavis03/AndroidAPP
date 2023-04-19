package com.example.login_signup;

public class ImageClass {
    private String ID;

    private String ClickImage;

    public ImageClass(String ID, String clickImage) {
        this.ID = ID;
        this.ClickImage = clickImage;
    }

    public String getID() {
        return ID;
    }

    public String getClickImage() {
        return ClickImage;
    }
}
