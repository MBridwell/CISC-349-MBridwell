package com.example.walmartstores;

public class WalmartStores {
    private String name, street_address, phone_number, city, store_code;

    public WalmartStores(String name, String street_address, String phone_number, String city, String store_code){
        this.name=name;
        this.city=city;
        this.street_address=street_address;
        this.phone_number=phone_number;
        this.store_code=store_code;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }
    public String getStreet_address(){
        return street_address;
    }
    public void setStreet_address(String street_address){
        this.street_address=street_address;
    }
    public String getPhone_number(){
        return phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number=phone_number;
    }
    public String getStore_code(){
        return store_code;
    }
    public void setStore_code(String store_code){
        this.store_code=store_code;
    }

}
