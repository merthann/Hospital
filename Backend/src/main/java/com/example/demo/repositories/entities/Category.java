package com.example.demo.repositories.entities;

public enum Category{
	Antienfeksiyon(1),
	Hormon(2),
	Immun_sistem(3),
	Kalp_ve_damar(4),
	Kan(5),
	Mide_bagirsak(6);
	
	private final int nameValue;

    Category(int nameValue) {
        this.nameValue = nameValue;
    }

    public int getNameValue() {
        return nameValue;
    }
}
