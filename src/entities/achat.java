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
public class achat {
    int id;
    facture facture;
    User membre;
    produit produit;
    int nbr;
    float prix;

    public achat(int id, facture facture, User membre, produit produit, int nbr, float prix) {
        this.id = id;
        this.facture = facture;
        this.membre = membre;
        this.produit = produit;
        this.nbr = nbr;
        this.prix = prix;
    }

    public achat(facture facture, User membre, produit produit, int nbr, float prix) {
        this.facture = facture;
        this.membre = membre;
        this.produit = produit;
        this.nbr = nbr;
        this.prix = prix;
    }

    public achat( produit produit, int nbr, float prix) {
        
        this.produit = produit;
        this.nbr = nbr;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public facture getFacture() {
        return facture;
    }

    public void setFacture(facture facture) {
        this.facture = facture;
    }

    public User getMembre() {
        return membre;
    }

    public void setMembre(User membre) {
        this.membre = membre;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "achat{" + "id=" + id + ", facture=" + facture + ", membre=" + membre + ", produit=" + produit + ", nbr=" + nbr + ", prix=" + prix + '}';
    }
    
    
}
