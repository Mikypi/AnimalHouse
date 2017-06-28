package com.example.michaeliverson.animalhouse.Utils;

import java.io.Serializable;

/**
 * Created by michaeliverson on 6/27/17.
 */

public class Animal implements Serializable {
    private String Animal;
    private String Category;
    private String Description;
    private String picture;
    private String sound;
    private byte[] pictureData;
    private byte[] soundData;

    public Animal(String animal, String category, String description, String picture, String sound, byte[] pictureData, byte[] soundData) {
        Animal = animal;
        Category = category;
        Description = description;
        this.picture = picture;
        this.sound = sound;
        this.pictureData = pictureData;
        this.soundData = soundData;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public byte[] getPictureData() {
        return pictureData;
    }

    public void setPictureData(byte[] pictureData) {
        this.pictureData = pictureData;
    }

    public byte[] getSoundData() {
        return soundData;
    }

    public void setSoundData(byte[] soundData) {
        this.soundData = soundData;
    }

    public Animal()
    {

    }

    public String getAnimal() {
        return Animal;
    }

    public void setAnimal(String aninmal) {
        Animal = aninmal;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicture() {
        return picture;
    }

    public String getSound() {
        return sound;
    }
}
