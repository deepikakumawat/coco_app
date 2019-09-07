package com.nav.richkart.Model;

/**
 * Created by wolfsoft3 on 19/1/19.
 */

public class Item_Fashion_Model {
    Integer image;
    String text;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Item_Fashion_Model(Integer image, String text) {
        this.image = image;
        this.text = text;
    }
}
