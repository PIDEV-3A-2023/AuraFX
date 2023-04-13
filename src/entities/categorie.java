/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author azerb
 */
public class categorie {
    int id;
    String nom;
    String image;
    int nbrproduit;

    public categorie(int id, String nom, String image, int nbrproduit) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.nbrproduit = nbrproduit;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbrproduit() {
        return nbrproduit;
    }

    public void setNbrproduit(int nbrproduit) {
        this.nbrproduit = nbrproduit;
    }

    public categorie(String nom, String image, int nbrproduit) {
        this.nom = nom;
        this.image = image;
        this.nbrproduit = nbrproduit;
    }

    public categorie() {
    }

    public categorie(String nom) {
        this.nom = nom;
    }
    
    @Override
    public String toString() {
        return  nom;
    }

    public categorie(int id) {
        this.id = id;
    }
    
}
