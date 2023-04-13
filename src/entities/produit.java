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
public class produit {
    int id;
    categorie categorie;
    String nom;
    String desc;
    String image;
    float prix;
    int nbr;

    public produit() {
    }

    public produit(categorie categorie, String nom, String desc, String image, float prix, int nbr) {
        this.categorie = categorie;
        this.nom = nom;
        this.desc = desc;
        this.image = image;
        this.prix = prix;
        this.nbr = nbr;
    }

    public produit(int id, categorie categorie, String nom, String desc, String image, float prix, int nbr) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.desc = desc;
        this.image = image;
        this.prix = prix;
        this.nbr = nbr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(categorie categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", categorie=" + categorie + ", nom=" + nom + ", desc=" + desc + ", image=" + image + ", prix=" + prix + ", nbr=" + nbr + '}';
    }
}
