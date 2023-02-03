package com.example.demo.model;

public class DetailEnchere {
    int idenchere;
    String duree;
    String datedebut;
    int prixmin;
    int produitid;
    String produit;
    int categorieid;
    String categorie;

    public int getIdEnchere() {
        return idenchere;
    }

    public void setIdEnchere(int id) {
        this.idenchere = id;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public int getPrixmin() {
        return prixmin;
    }

    public void setPrixmin(int prixmin) {
        this.prixmin = prixmin;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public DetailEnchere() {

    }

    public DetailEnchere(int id, String duree, String datedebut, int prixmin, int produitid, String produit,
            int categorieid, String categorie) {
        this.idenchere = id;
        this.duree = duree;
        this.datedebut = datedebut;
        this.prixmin = prixmin;
        this.produitid = produitid;
        this.produit = produit;
        this.categorieid = categorieid;
        this.categorie = categorie;
    }

}
