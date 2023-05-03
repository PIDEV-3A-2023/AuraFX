/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author issamfekih.if
 */
package services;

import interfaces.InterfaceAffectations;
import entity.Affectations;
import entity.Technicien;
import entity.Terrain;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;

public class CRUDaffectations implements InterfaceAffectations <Affectations> {
    
    Statement ste;
    Connection connection;
    
    public CRUDaffectations() {
        connection = MyConnection.getInstance().getConnection();
    }
    
    
    

         
         
    @Override
    public void ajouterAffectations(Affectations a) {
        
        try {
            String requette = "INSERT INTO affectations(`id`, `technicien_id`, `terrain_id`, `date_debut`, `date_fin`) "
                    + "VALUES (?, ?, ?, ?, ?)";
            //String requette = "INSERT INTO `affectations`(`id`, `technicien_id`, `terrain_id`, `date_debut`, `date_fin`) VALUES ('" + a.getId() + "','" + a.getTechnicien_id() + "','" + a.getTerrain_id() + "','" + a.getDate_debut().toString() + "','" + a.getDate_fin().toString() + "')";
            PreparedStatement pstmt = connection.prepareStatement(requette);
            pstmt.setInt(1, a.getId());
            pstmt.setInt(2, a.getTechnicien().getId());
            pstmt.setInt(3, a.getTerrain().getId());
            pstmt.setDate(4, new java.sql.Date(a.getDateDebut().getTime()));
            pstmt.setDate(5, new java.sql.Date(a.getDateFin().getTime()));
            pstmt.executeUpdate();
            System.out.println("Ajout Affectations avec succès ");
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'ajout Affectation : " + ex.getMessage());
        }
        
        

    try {
        String requete = "INSERT INTO `affectation`(`id_technicien`, `id_terrain`, `date_debut`, `date_fin`) "
                + "VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, a.getTechnicien().getId());
        ps.setInt(2, a.getTerrain().getId());
        ps.setDate(3, java.sql.Date.valueOf(a.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        ps.setDate(4, java.sql.Date.valueOf(a.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    

        ps.executeUpdate();

        System.out.println("Ajout Affectation avec succès ");
    } catch (SQLException ex) {
        System.out.println("Erreur lors d'ajout Affectation : " + ex.getMessage());
    }
}
  
        
        
           @Override
    public void ajouterAffectations2(Affectations a) {
    try {
        String requete = "INSERT INTO `affectation`(`id_technicien`, `id_terrain`, `date_debut`, `date_fin`) "
                + "VALUES ('" + a.getTechnicien() + "','" + a.getTerrain() + "','" + a.getDateDebut() + "','" + a.getDateFin() + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(requete);
        System.out.println("Ajout Affectation avec succès ");
    } catch (SQLException ex) {
        System.out.println("Erreur lors d'ajout Affectation : " + ex.getMessage());
    }
} 
        
        
public int stat(Technicien t) {
    int sum = 1;
    try {
        String requete = "SELECT COUNT(*)AS affectations "
                + "FROM affectations "
                + "JOIN Terrain ON Affectations.terrain_id = Terrain.id "
                + "JOIN Technicien ON Affectations.technicien_id = Technicien.id"
                + "WHERE technicien_id = ?";

        PreparedStatement ste = connection.prepareStatement(requete);
        ResultSet rs = ste.executeQuery();

        if (rs.next()) {
            sum = rs.getInt("affectations");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return sum;
}

      
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    /*
    @Override
    public void ajouterAffectations2(Affectations a) {
        try {
            String requett = "INSERT INTO 'affectations'('technicien_id','terrain_id','date_debut','date_fin') VALUES (?,?,?,?)";
            //String requett = "INSERT INTO 'affectations'('id', 'technicien_id','terrain_id','date_debut','date_fin') ('" + a.getId() + "','" + a.getTechnicien_id() + "','" + a.getTerrain_id() + "','" + a.getDate_debut().toString() + "','" + a.getDate_fin().toString() + "')";
            PreparedStatement ps = connection.prepareStatement(requett);
            ps.setInt(1, a.getTechnicien_id());
            ps.setInt(2, a.getTerrain_id());
            ps.setDate(3, new java.sql.Date(a.getDate_debut().getTime()));
            ps.setDate(4, new java.sql.Date(a.getDate_fin().getTime()));

            ps.executeUpdate();

            System.out.println("Ajout Affectations avec succès ");
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'ajout Affectation : " + ex.getMessage());
        }
    }

    */
/*
    @Override
public void ajouterAffectations2(Affectations a) {
    try {
        String requett = "INSERT INTO affectations (technicien_id, terrain_id, date_debut, date_fin) "
                + "VALUES (" + a.getTechnicien() 
                + ", " 
                + a.getTerrain() 
                + ", '" 
                + new java.sql.Date(a.getDateDebut().getTime()) 
                + "', '" 
                + new java.sql.Date(a.getDateFin().getTime()) 
                + "')";
        Statement statement = connection.createStatement();
        statement.executeUpdate(requett);

        System.out.println("Ajout Affectations avec succès ");
    } catch (SQLException ex) {
        System.out.println("Erreur lors d'ajout Affectation : " + ex.getMessage());
    }
}
*/

    @Override
    public void modifierAffectations(Affectations a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    @Override
    public List<Affectations> afficherAffectations(){
        List<Affectations> list = new ArrayList<>();
        try {
            String requette = "SELECT Affectations.id,Terrain.id AS terrain_id, Technicien.id AS technicien_id,  Affectations.date_debut, Affectations.date_fin "
                + "FROM affectations " 
                + "JOIN Terrain ON Affectations.terrain_id = Terrain.id "
                + "JOIN Technicien ON Affectations.technicien_id = Technicien.id";

            Statement ste = connection.createStatement();
            ResultSet RS = ste.executeQuery(requette);
            while (RS.next()) {
                Affectations a = new Affectations(
                    RS.getInt("id"),
                    new Technicien(RS.getInt("technicien_id")),
                    new Terrain(RS.getInt("terrain_id")),
                    new Date(RS.getTimestamp("date_debut").getTime()),
                    new Date(RS.getTimestamp("date_fin").getTime())
            );  
            list.add(a);
        }
            System.out.println("Affichage Affectations avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
    @Override
    public List<Affectations> getafficherterrain() {
        List<Affectations> list = new ArrayList<>();
        try {
            String requette = "SELECT Affectations.terrain_id"
                    + "FROM affectations ";
         
            Statement ste;
            ste = connection.createStatement();
            ResultSet RS = ste.executeQuery(requette);
            while (RS.next()) {
                Affectations a = new Affectations();
                a.setId(RS.getInt("id"));
                Terrain terrain = new Terrain(
                        RS.getInt("terrain_id"));
                a.setTerrain(terrain);
                list.add(a);
            }
            System.out.println("Affichage Affectations avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public List<Affectations> getaffichertechnicien() {
        List<Affectations> list = new ArrayList<>();
        try {
            String requette = "SELECT Affectations.technicien_id"
                    + "FROM affectations ";
         
            Statement ste;
            ste = connection.createStatement();
            ResultSet RS = ste.executeQuery(requette);
            while (RS.next()) {
                Affectations a = new Affectations();
                a.setId(RS.getInt("id"));
                Technicien technicien = new Technicien(
                    RS.getInt("technicien_id"));
                a.setTechnicien(technicien);
            
                list.add(a);

            }
            System.out.println("Affichage Affectations avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    @Override
    public void supprimerAffectations(int id) {
               try {
            Statement sa = connection.createStatement();
            String requette = "DELETE FROM affectations WHERE id = " + id + "";
            sa.executeUpdate(requette);
            System.out.println("Affctations supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
