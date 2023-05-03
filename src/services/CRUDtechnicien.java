/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Affectations;
import interfaces.InterfaceTechnicien;
import entity.Technicien;
import entity.Terrain;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.List;
import utils.MaConnection;
import javafx.collections.FXCollections;
import static jdk.nashorn.internal.runtime.Debug.id;
import static org.omg.CORBA.AnySeqHelper.id;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author issamfekih.if
 */
public class CRUDtechnicien implements InterfaceTechnicien <Technicien>{

    Statement ste;
    Connection connection;

    public CRUDtechnicien() {
        connection = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterTechnicien(Technicien t) {
        try {
            String requett = "INSERT INTO `technicien`(`id`, `nom`, `prenom`, `tel`, `email`, `specialite`, `salaire`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(requett);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getTel());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getSpecialite());
            ps.setDouble(7, t.getSalaire());
            ps.executeUpdate();
            System.out.println("Ajout Technicien avec succès ");
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'ajout Technicien : " + ex.getMessage());
        }
    }

    @Override
    public void ajouterTechnicien2(Technicien t) {
        try {
            String requete = "INSERT INTO `technicien`( `nom`, `prenom`, `tel`, `email`, `specialite`, `salaire`) VALUES ('" + t.getNom() + "','" + t.getPrenom() + "','" + t.getTel() + "','" + t.getEmail() + "','" + t.getSpecialite() + "','" + t.getSalaire() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(requete);
            System.out.println("Ajout Technicien avec succès ");
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'ajout Technicien : " + ex.getMessage());
        }
    }


    @Override
    public void updateTechnicien(Technicien t) {
        try {
            String requett = "UPDATE technicien SET nom=?, prenom=?, tel=?, email=?,specialite=? ,salaire=? WHERE id=?";
            //String requett = "INSERT INTO `technicien`(`id`, `nom`, `prenom`, `tel`, `email`, `specialite`, `salaire`) VALUES ('" + t.getId() + "','" + t.getNom() + "','" + t.getPrenom() + "','" + t.getTel() + "','" + t.getEmail() + "','" + t.getSpecialite() + "','" + t.getSalaire() + "')";
            PreparedStatement ps = connection.prepareStatement(requett);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getTel());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getSpecialite());
            ps.setDouble(7, t.getSalaire());
            ps.executeUpdate();

            System.out.println("Ajout Technicien avec succès ");
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'ajout Technicien : " + ex.getMessage());
        }
    }

    
    public Technicien getTechnicienById(int id) {
    Technicien t = null;
    try {
        String req = "SELECT * FROM technicien WHERE id = " + id;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            t = new Technicien();
            t.setId(rs.getInt("id"));
            t.setNom(rs.getString("nom"));
            t.setPrenom(rs.getString("prenom"));
            t.setTel(rs.getString("tel"));
            t.setEmail(rs.getString("email"));
            t.setSpecialite(rs.getString("specialite"));
            t.setSalaire(rs.getDouble("salaire"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return t;
}








    @Override
    public void supprimerTechnicien(int id) {
        try {
            Statement st = connection.createStatement();
            String requette = "DELETE FROM technicien WHERE id = " + id + "";
            st.executeUpdate(requette);
            System.out.println("Technicien supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Technicien> afficherTechnicien() {
        List<Technicien> technicien = FXCollections.observableArrayList();

        try {
            String requette = "SELECT * FROM technicien ";

            Statement ste = connection.createStatement();
            ResultSet RS = ste.executeQuery(requette);
            while (RS.next()) {
                Technicien t = new Technicien();

                t.setId(RS.getInt("id"));
                t.setNom(RS.getString("nom"));
                t.setPrenom(RS.getString("prenom"));
                t.setTel(RS.getString("tel"));
                t.setEmail(RS.getString("email"));
                t.setSpecialite(RS.getString("specialite"));
                t.setSalaire(RS.getDouble("salaire"));

                technicien.add(t);

            }
            System.out.println("Affichage Technicien avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return technicien;

    }
    
        @Override
    public List<Technicien> afficherTechnicien2(int technicienId) {
        List<Technicien> technicien = FXCollections.observableArrayList();

        try {
            String requette = "SELECT * FROM technicien ";

            Statement ste = connection.createStatement();
            ResultSet RS = ste.executeQuery(requette);
            while (RS.next()) {
                Technicien t = new Technicien();

                t.setId(RS.getInt("id"));
                t.setNom(RS.getString("nom"));
                t.setPrenom(RS.getString("prenom"));
                t.setTel(RS.getString("tel"));
                t.setEmail(RS.getString("email"));
                t.setSpecialite(RS.getString("specialite"));
                t.setSalaire(RS.getDouble("salaire"));

                technicien.add(t);

            }
            System.out.println("Affichage Technicien avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return technicien;

    }
}
