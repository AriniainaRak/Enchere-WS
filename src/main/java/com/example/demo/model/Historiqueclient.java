package com.example.demo.model;

public class Historiqueclient {
    int idclient;
    String nom;
    String prenom;
    String date;
    String description;
    int prixmin;
    int mise;
    String statue;

    public int getIdclient() {
        return idclient;
    }

    public void setIdClient(int id) {
        this.idclient = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String descri){
        this.description = descri;
    }

    public int getPrixmin() {
        return idclient;
    }

    public void setPrixmin(int prix) {
        this.prixmin = prix;
    }

    public int getMise() {
        return mise;
    }

    public void setMise(int mise) {
        this.mise = mise;
    }

    public String getStatue(){
        return statue;
    }

    public void setStatue(String statue){
        this.statue = statue;
    }

    public Historiqueclient(){

    }

    public Historiqueclient(int idclient,String nom,String prenom,String date,String description,int prixmin,int mise,String statue){
        this.idclient = idclient;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.description = description;
        this.prixmin = prixmin;
        this.mise = mise;
        this.statue = statue;
    }
}
