/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Cyrine
 */
public class membre {
   private int id_membre;
 private String nom ;
  private String prenom ;
   private String date_naissance ;
    private String EMAIL;
 private String password;
private String telephone  ;
 private String adresse;
   private String cin ;

    public membre(int id_membre, String nom, String prenom, String date_naissance, String EMAIL, String password, String telephone, String adresse, String cin) {
        this.id_membre = id_membre;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.EMAIL = EMAIL;
        this.password = password;
        this.telephone = telephone;
        this.adresse = adresse;
        this.cin = cin;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
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

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "membre{" + "id_membre=" + id_membre + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", EMAIL=" + EMAIL + ", password=" + password + ", telephone=" + telephone + ", adresse=" + adresse + ", cin=" + cin + '}';
    }

    public membre() {
    }
   
}
