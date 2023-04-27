/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author azerb
 */
public class facture {
    int id;
    membre m;
    float montant;
    Date date;

    public facture(int id, membre m, float montant, Date date) {
        this.id = id;
        this.m = m;
        this.montant = montant;
        this.date = date;
    }

    public facture(membre m, float montant, Date date) {
        this.m = m;
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public membre getM() {
        return m;
    }

    public void setM(membre m) {
        this.m = m;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
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
        return "facture{" + "id=" + id + ", m=" + m + ", montant=" + montant + ", date=" + date + '}';
    }

    public facture() {
    }
    
    
}
