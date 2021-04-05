package com.example.kurir254.kurir.models;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="m_biodata")
public class Biodata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private long Id;

    @Column(name="fullname")
    private String Fullname;

    @Column(name="mobil_phone", length = 15,nullable = true)
    private String MobilePhone;

    @Lob
    @Column(name="image")
    private Blob Image;

    @Column(name="image_path")
    private String ImagePath;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public Blob getImage() {
        return Image;
    }

    public void setImage(Blob image) {
        Image = image;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
