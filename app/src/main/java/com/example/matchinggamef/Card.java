package com.example.matchinggamef;

public class Card {
    int card_img_res;
    boolean flipable = true;

    public Card() {
    }

    public Card(int card_img_res, boolean flipable) {
        this.card_img_res = card_img_res;
        this.flipable = flipable;
    }

    public int getCard_img_res() {
        return card_img_res;
    }

    public boolean isFlipable() {
        return flipable;
    }

    public void setFlipable(boolean flipable) {
        this.flipable = flipable;
    }
}
