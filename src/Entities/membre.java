/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date; 
import java.util.logging.Logger;

/**
 *
 * @author Cyrine
 */
public class membre {
   private int id_membre;
 private String nom ;
  private String prenom ;
      private String email;
       private String password;
   private Date date_naissance ;
   
 private String telephone;
 private String adresse;
   private String role;

    public membre(int id_membre, String nom, String prenom, String email, Date date_nais, String tel, String adresse, String cin) {
        this.id_membre = id_membre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
         this.password = password;
        this.date_naissance = date_nais;
        this.telephone = tel;
        this.adresse = adresse;
        this.role = role;
    }

    public membre() {
    }

    public int getId_membre() {
        return id_membre;
    }

    public membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public membre(int id_membre, String nom, String prenom, String email, String tel, String adresse) {
        this.id_membre = id_membre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = tel;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public Date getDate_nais() {
        return date_naissance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getTel() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getRole() {
        return role;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email= email;
    }

    public void setDate_nais(Date date_nais) {
        this.date_naissance = date_nais;
    }

    public void setTel(String tel) {
        this.telephone = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "membre{" + "id_membre=" + id_membre + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", date_nais=" + date_naissance + ", tel=" + telephone + ", adresse=" + adresse + ", role=" + role+ '}';
    }

    public membre(int id_membre, String nom, String prenom, String email, String password, Date date_nais, String tel, String adresse, String role) {
        this.id_membre = id_membre;
        this.nom = nom;
        this.prenom = prenom;
        this.email= email;
        this.password = password;
        this.date_naissance = date_nais;
        this.telephone = tel;
        this.adresse = adresse;
        this.role = role;
    }

   
     
}
