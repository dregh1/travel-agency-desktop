package com.travelagency.travelagency.models;


import java.time.LocalDateTime;
public class Reservation {
    private String nom;
    private String destination;
    private String paiement;
    private int nbPersonnes;

    private LocalDateTime  departure;

    public Reservation(String nom, String destination, LocalDateTime  departure, String paiement, int nbPersonnes) {
        this.nom = nom;
        this.destination = destination;
        this.departure = departure;
        this.paiement = paiement;
        this.nbPersonnes = nbPersonnes;

    }

    public String getNom() { return nom; }
    public String getDestination() { return destination; }
    public String getPaiement() { return paiement; }
    public int getNbPersonnes() { return nbPersonnes; }

    public LocalDateTime getDeparture() {
        return departure;
    }
}
