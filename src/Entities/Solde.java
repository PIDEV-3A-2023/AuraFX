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
public class Solde {
   
     private int id_part ;
 private int id_terrain;
 private double montant;
 private String  date;

    public Solde(double montant) {
        this.montant = montant;
    }


 public Solde(int id_part , int id_terrain, double montant, String date) {
        this.id_part = id_part ;
        this.id_terrain = id_terrain;
        this.montant = montant;
        this.date = date;
 }

    public Solde() {
    }
 

    public Solde(int id_terrain, double  montant, String date) {
        this.id_terrain = id_terrain;
        this.montant = montant;
        this.date = date;
    }

    public Solde(int id, double montant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_part() {
        return id_part;
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public double getMontant() {
        return montant;
    }

    public String getDate() {
        return date;
    }

    public void setId_part(int id_part) {
        this.id_part = id_part;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Solde{" + "id_part=" + id_part + ", id_terrain=" + id_terrain + ", montant=" + montant + ", date=" + date + '}';
    }
 
}