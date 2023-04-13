/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Post {
    private int id,nbr_Vue;
     private String theme,nom,contenu,image;
    private Date date_Creation;

    public Post(int id, String theme, String nom, String contenu, String image) {
        this.id = id;
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.image = image;
    }
   

    public Post(int id, String theme, String nom, String contenu, String image, Date date_Creation) {
        this.id = id;
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.image = image;
        this.date_Creation = date_Creation;
        this.nbr_Vue = nbr_Vue;
    }
    
     public Post( String theme, String nom, String contenu, String image, Date date_Creation) {
       
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.image = image;
        this.date_Creation = date_Creation;
 
    }
    public Post(String theme, String nom, String contenu,Date date_Creation) {
       
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
        this.date_Creation = date_Creation;
    }

    public Post(String theme, String nom, String contenu) {
        this.theme = theme;
        this.nom = nom;
        this.contenu = contenu;
    }
   
    
    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_Creation() {
        return date_Creation;
    }

    public void setDate_Creation(Date date_Creation) {
        this.date_Creation = date_Creation;
    }
     public int getNbr_Vue() {
        return nbr_Vue;
    }

    public void setNbr_Vue(int nbr_Vue) {
        this.nbr_Vue = nbr_Vue;
        
    }
    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", theme=" + theme + ", nom=" + nom + ", contenu=" + contenu + ", image=" + image + ", date_Creation=" + date_Creation + '}';
    }

    public int getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
