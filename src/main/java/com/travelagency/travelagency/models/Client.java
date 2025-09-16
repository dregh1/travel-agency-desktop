package com.travelagency.travelagency.models;

public class Client {
    private String nom;
    private String email;
    private String telephone;
    private int frequence; // ✅ ajouté

    public Client(String nom, String email, String telephone, int frequence) {
        this.nom = nom;
        this.email = email;
        this.telephone = telephone;
        this.frequence = frequence;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getFrequence() {   // ✅ getter ajouté
        return frequence;
    }
}
