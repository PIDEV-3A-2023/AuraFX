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
private membre  membre  ; 

    public Terrain(int id_terrain, String adresse, double surface, double potentiel, membre membre) {
        this.id_terrain = id_terrain;
        this.adresse = adresse;
        this.surface = surface;
        this.potentiel = potentiel;
        this.membre = membre;
        
    }

    public Terrain(String adresse, double surface, double potentiel,  membre membre) {
        this.adresse = adresse;
        this.surface = surface;
        this.potentiel = potentiel;
        this.membre = membre;
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

    public membre getMembre() {
        return membre;
    }

    public void setMembre(membre membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Terrain{" + "id_terrain=" + id_terrain + ", adresse=" + adresse + ", surface=" + surface + ", potentiel=" + potentiel + ", membre=" + membre + '}';
    }

   
}
