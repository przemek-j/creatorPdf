package com.pjada.GeneratorPdf.frame;

import javax.persistence.*;

@Entity
@Table(name = "Frames")
public class Frame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String image;

    public Frame(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Frame{" +
                "name='" + name + '\'' +
                '}';
    }
}
