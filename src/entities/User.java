/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class User {
    
    private int id ;
    private String tel ; 
    private String nom ; 
    private String prenom ;
    private String mdp ;
    private String email ;
    private String role ; 
    private Date datenai ;
    private String image ;
    private int isavtive ;
    private String adresse ;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public Date getDatenai() {
        return datenai;
    }

    public void setDatenai(Date datenai) {
        this.datenai = datenai;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIsavtive() {
        return isavtive;
    }

    public void setIsavtive(int isavtive) {
        this.isavtive = isavtive;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", email=" + email + ", role=" + role +", datenai=" + datenai + ", image=" + image + ", isavtive=" + isavtive + '}';
    }
    
    
    
}
