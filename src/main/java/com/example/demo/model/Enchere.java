package com.example.demo.model;

import java.util.Date;
import java.sql.Time;

public class Enchere {
    int id;
    String description;
    Time duree;
    Date dateDebut;
    int prixMin;
    int photoenchereid=1;
    int statueid=1;
    int produitid=5;
    int idClient;

    public int getPhotoenchereid() {
        return photoenchereid;
    }

    public void setPhotoenchereid(int photoenchereid) {
        this.photoenchereid = photoenchereid;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getStatueid() {
        return statueid;
    }

    public void setStatueid(int statueid) {
        this.statueid = statueid;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }


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


    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(int prixMin) {
        this.prixMin = prixMin;
    }

    public Enchere(int id, String description, Time duree, Date dateDebut, int prixMin, int statueid, int photoenchereid,
            int produitid) {
        this.id = id;
        this.description = description;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.prixMin = prixMin;
        this.statueid = statueid;
        this.produitid = produitid;
        this.setPhotoenchereid(photoenchereid);
    }

    public Enchere() {

    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

}
