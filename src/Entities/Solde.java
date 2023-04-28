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
public class Solde {
    private int id;
    private Terrain terrain;
    private double montant;
    private Date date;

    public Solde(int id_part, Terrain terrain, double montant, Date date) {
        this.id = id_part;
        this.terrain = terrain;
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Solde() {
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Solde{" + "id_solde =" + id + ", terrain=" + terrain + ", montant=" + montant + ", date=" + date + '}';
    }

    
    }


 
