/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author issamfekih.if
 */
public class Technicien {
    int id;
    String nom;
    String prenom;
    String tel;
    String email;
    String specialite;
    Double salaire;

    public Technicien() {
    }
    public Technicien(int id, String nom, String prenom, String tel, String email, String specialite, Double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
        this.salaire = salaire;
    }
    public Technicien(String nom, String prenom, String tel, String email, String specialite, Double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
        this.salaire = salaire;
    }
    public Technicien(int id) {
        this.id = id;
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
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSpecialite() {
        return specialite;
    }
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
    public Double getSalaire() {
        return salaire;
    }
    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }
    @Override
    public String toString() {
        return "Technicien{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", email=" + email + ", specialite=" + specialite + ", salaire=" + salaire + '}';
    }
}