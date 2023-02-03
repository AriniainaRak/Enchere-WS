package com.example.demo.model;

import java.sql.Date;
import java.sql.Time;

public class V_Enchere {
    private int id;
    private String description;
    private Time duree;
    private Date dateDebut;
    private int prixmin;
    private int idClientAuteur;
    private String statue;
    private String acheteur;
    private int montant;
    private Date dateMise;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Time getDuree() {
        return duree;
    }
    public void setDuree(Time duree) {
        this.duree = duree;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public int getPrixmin() {
        return prixmin;
    }
    public void setPrixmin(int prixmin) {
        this.prixmin = prixmin;
    }
    public int getIdClientAuteur() {
        return idClientAuteur;
    }
    public void setIdClientAuteur(int idClientAuteur) {
        this.idClientAuteur = idClientAuteur;
    }
    public String getStatue() {
        return statue;
    }
    public void setStatue(String statue) {
        this.statue = statue;
    }
    public String getAcheteur() {
        return acheteur;
    }
    public void setAcheteur(String acheteur) {
        this.acheteur = acheteur;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }
    public Date getDateMise() {
        return dateMise;
    }
    public void setDateMise(Date dateMise) {
        this.dateMise = dateMise;
    }
    
}
