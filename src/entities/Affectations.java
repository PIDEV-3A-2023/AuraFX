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

import java.util.Date;

public class Affectations {
    int id;
    Technicien technicien_id;
    Terrain terrain_id;
    Date date_debut;
    Date date_fin;

    public Affectations() {
    }
    public Affectations(int id,Technicien technicien, Terrain terrain, Date date_debut, Date date_fin) {    
        this.id = id;
        this.technicien_id = technicien;
        this.terrain_id = terrain;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    public Affectations(Technicien technicien, Terrain terrain, Date date_debut, Date date_fin) {
        this.technicien_id = technicien;
        this.terrain_id = terrain;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    public Affectations(int id, int technicien_id, int terrain_id, Date date_debut, Date date_fin) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
        public Affectations(Technicien technicien, Terrain terrain) {
        this.technicien_id = technicien;
        this.terrain_id = terrain;
    }
    
    
    
    
    
    
    
    
    
    
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Technicien getTechnicien() {
        return technicien_id;
    }
    public void setTechnicien(Technicien technicien) {
        this.technicien_id = technicien;
    }
    public Terrain getTerrain() {
        return terrain_id;
    }
    public void setTerrain(Terrain terrain) {
        this.terrain_id = terrain;
    }
    public Date getDateDebut() {
        return date_debut;
    }

    public void setDateDebut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDateFin() {
        return date_fin;
    }

    public void setDateFin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Affectations{" + "id=" + id + ", technicien_id=" + technicien_id + ", terrain_id=" + terrain_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
}
