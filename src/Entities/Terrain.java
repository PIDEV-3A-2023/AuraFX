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
public class Terrain {
       private int id_terrain ;
 private String adresse;
 private double surface ;
 private double potentiel ;
private int id_membre  ;

    public Terrain(int id_terrain, String adresse, double surface, double potentiel, int id_membre) {
        this.id_terrain = id_terrain;
        this.adresse = adresse;
        this.surface = surface;
        this.potentiel = potentiel;
        this.id_membre = id_membre;
    }

    public Terrain(String adresse, double surface, double potentiel, int id_membre) {
        this.adresse = adresse;
        this.surface = surface;
        this.potentiel = potentiel;
        this.id_membre = id_membre;
    }

    public Terrain() {
    }

    public int getId_terrain() {
        return id_terrain;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getSurface() {
        return surface;
    }

    public double getPotentiel() {
        return potentiel;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public void setPotentiel(double potentiel) {
        this.potentiel = potentiel;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    @Override
    public String toString() {
        return "Terrain{" + "id_terrain=" + id_terrain + ", adresse=" + adresse + ", surface=" + surface + ", potentiel=" + potentiel + ", id_membre=" + id_membre + '}';
    }
    

}
