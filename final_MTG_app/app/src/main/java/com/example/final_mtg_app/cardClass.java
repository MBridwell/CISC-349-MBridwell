package com.example.final_mtg_app;

import java.util.List;

public class cardClass {

    private String CMC, cardType, cardName, cardImage;
    private List<String> cardColor;


    public cardClass(List<String> cardColor, String CMC, String cardType, String cardName, String cardImage) {
        this.cardColor = cardColor;
        this.CMC = CMC;
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardImage = cardImage;
    }

    // getters
    public List<String> getCardColor() {
        return cardColor;
    }

    public String getCMC() {
        return CMC;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardImage() {
        return cardImage;
    }

    // setters
    public void setCardColor(List<String> cardColor) {
        this.cardColor = cardColor;
    }

    public void setCMC(String CMC) {
        this.CMC = CMC;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }
}