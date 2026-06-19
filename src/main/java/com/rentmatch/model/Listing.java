package com.rentmatch.model;

public class Listing {
    private String id;
    private int price;
    private int bedrooms;
    private double bathrooms;
    private boolean petsAllowed;
    private String neighbourhood;
    private String address;
    private String title;
    private String description;

    public Listing(String id, int price, int bedrooms, double bathrooms, boolean petsAllowed, String neighbourhood, String address, String title, String description) {
        this.id = id;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.petsAllowed = petsAllowed;
        this.neighbourhood = neighbourhood;
        this.address = address;
        this.title = title;
        this.description = description;
    }

    public String getId(){
        return this.id;
    }

    public int getPrice(){
        return this.price;
    }

    public int getBedrooms(){
        return this.bedrooms;
    }

    public double getBathrooms(){
        return this.bathrooms;
    }

    public boolean isPetsAllowed(){
        return this.petsAllowed;
    }

    public String getNeighbourhood(){
        return this.neighbourhood;
    }

    public String getAddress(){
        return this.address;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }
}