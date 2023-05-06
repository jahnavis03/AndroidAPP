package com.example.login_signup;

public class DataClass {

    private String petName;
    private String age;
    private String breed;
    private String tagNo;
    private String key;
    private String medicalHist;

    private String faceImage1,faceImage2,faceImage3,muzzleImage1,muzzleImage2,muzzleImage3;

    public String getFaceImage1() {
        return faceImage1;
    }

    public String getFaceImage2() {
        return faceImage2;
    }
public String getKey() {
    return key;
}
public void setKey(String key) {
    this.key = key;
}

    public String getFaceImage3() {
        return faceImage3;
    }

    public String getMuzzleImage1() {
        return muzzleImage1;
    }

    public String getMuzzleImage2() {
        return muzzleImage2;
    }

    public String getMuzzleImage3() {
        return muzzleImage3;
    }

    public String getPetName() {
        return petName;
    }

    public String getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getTagNo() {
        return tagNo;
    }

    public String getMedicalHist() {
        return medicalHist;
    }

    public DataClass(String petName, String age, String breed, String tagNo, String medicalHist, String faceImage1, String faceImage2, String faceImage3, String muzzleImage1, String muzzleImage2, String muzzleImage3) {
        this.petName = petName;
        this.age = age;
        this.breed = breed;
        this.tagNo = tagNo;
        this.medicalHist = medicalHist;
        this.faceImage1=faceImage1;
        this.faceImage2=faceImage2;
        this.faceImage3=faceImage3;
        this.muzzleImage1=muzzleImage1;
        this.muzzleImage2=muzzleImage2;
        this.muzzleImage3=muzzleImage3;
    }
    public DataClass(){

    }
}
