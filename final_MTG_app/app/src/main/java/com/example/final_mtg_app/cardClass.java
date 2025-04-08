package com.example.final_mtg_app;

public class cardClass {

    //what do i want info wise to be returned
    //1. Color
    //2. CMC
    //3. Card Type
    //4. Name
    //5.imageLink ? ? ? ? ? LOL
    private String cardColor, CMC, cardType, cardName, cardImage;
    public cardClass(String cardColor, String CMC, String cardType, String cardName, String cardImage){
        this.cardColor = cardColor;
        this.CMC = CMC;
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardImage = cardImage;
    }


    //getters
    public String getCardColor() {
        return cardColor;
    }
    public String getCMC(){
        return CMC;
    }
    public String getCardType(){
        return cardType;
    }
    public String getCardName(){
        return cardName;
    }
    public String getCardImage(){
        return cardImage;
    }
    //setters
    public String setCardColor(String cardColor){
        return this.cardColor = cardColor;
    }
    public String setCMC(String CMC){
        return this.CMC = CMC;
    }
    public String setCardType(String cardType){
        return this.cardType = cardType;
    }
    public String setCardName(String cardName){
        return this.cardName = cardName;
    }
    public String setCardImage(String cardImage){
        return this.cardImage = cardImage;
    }
}
