package com.pjada.GeneratorPdf.models;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.awt.*;

@Entity
@Table(name = "Watermark")
public class Watermark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String imagePath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Watermark() {
    }

    public Watermark(String name, String imagePath, User user) {
        this.name = name;
        this.imagePath = imagePath;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return imagePath;
    }

    public void setImage(String image) {
        this.imagePath = image;
    }

    @Override
    public String toString() {
        return "Watermark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + imagePath + '\'' +
                '}';
    }
}
