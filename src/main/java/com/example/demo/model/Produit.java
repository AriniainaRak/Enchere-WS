package com.example.demo.model;

public class Produit {
    int id;
    String nom;
    String categorie;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    public Produit(int id, String nom, String categorie) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
    }

    public Produit(){
        
    }
}
