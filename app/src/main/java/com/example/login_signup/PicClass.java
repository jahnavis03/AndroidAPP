package com.example.login_signup;

public class PicClass {
    private String TagNo;
    private String Image;

    public String getImage() {
        return Image;
    }

    public String getTagNo() {
        return TagNo;
    }

    public PicClass(String tagNo, String image) {
        this.TagNo = tagNo;
        this.Image = image;
    }
}
