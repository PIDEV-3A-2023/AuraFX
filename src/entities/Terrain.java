/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author issamfekih.if
 */
package entity;

public class Terrain {
    int id;
    String adresse;
    double surface;
    double potentiel;
    int membre_id;

    public Terrain() {
    }
    public Terrain(int id, String adresse, double surface, double potentiel, int membre_id) {
        this.id = id;
        this.adresse = adresse;
        this.surface = surface;
        this.potentiel = potentiel;
        this.membre_id = membre_id;
    }
    public Terrain(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
    public void setId(int id_terrain) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }
    public double getPotentiel() {
        return potentiel;
    }
        public void setPotentiel(double potentiel) {
        this.potentiel = potentiel;
    }
    public int getIdMembre() {
        return membre_id;
    }
    public void setIdMembre(int membre_id) {
        this.membre_id = membre_id;
    }
    @Override
    public String toString() {
        return "Terrain{" + "id_terrain=" + id + ", adresse=" + adresse + ", surface=" + surface + ", potentiel=" + potentiel + ", membre_id=" + membre_id + '}';
    }
}
