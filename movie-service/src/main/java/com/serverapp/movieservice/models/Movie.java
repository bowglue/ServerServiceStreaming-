package com.serverapp.movieservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "movie")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private byte[] wide_image;
    private byte[] poster_image;
    private byte[] title_image;
    private byte[] focus_image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getWide_image() {
        return wide_image;
    }

    public void setWide_image(byte[] wide_image) {
        this.wide_image = wide_image;
    }

    public byte[] getPoster_image() {
        return poster_image;
    }

    public void setPoster_image(byte[] poster_image) {
        this.poster_image = poster_image;
    }

    public byte[] getTitle_image() {
        return title_image;
    }

    public void setTitle_image(byte[] title_image) {
        this.title_image = title_image;
    }

    public byte[] getFocus_image() {
        return focus_image;
    }

    public void setFocus_image(byte[] focus_image) {
        this.focus_image = focus_image;
    }
}
