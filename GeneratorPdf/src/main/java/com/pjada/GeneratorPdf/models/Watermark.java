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
    @Lob
    private byte[] image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Watermark() {
    }

    public Watermark(String name, byte[] image, User user) {
        this.name = name;
        this.image = image;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Watermark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
