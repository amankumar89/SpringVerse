package com.aman.products.entity;

import lombok.Getter;

@Getter
public enum Category {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    BOOKS("Books"),
    HOME_APPLIANCES("Home Appliances"),
    BEAUTY("Beauty"),
    SPORTS("Sports"),
    TOYS("Toys"),
    AUTOMOTIVE("Automotive"),
    GROCERY("Grocery"),
    FURNITURE("Furniture"),
    HEALTH("Health"),
    PET_SUPPLIES("Pet Supplies");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}

