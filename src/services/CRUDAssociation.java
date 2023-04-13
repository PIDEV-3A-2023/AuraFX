package services;

import entites.Association;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import Interfaces.InterfaceAssociation;

public class CRUDAssociation implements InterfaceAssociation {

    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterAssociation(Association association) {
        try {
            String req = "INSERT INTO association(nom, adresse, email, rib) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, association.getNom());
            ps.setString(2, association.getAdresse());
            ps.setString(3, association.getEmail());
            ps.setString(4, association.getRib());
            ps.executeUpdate();
            System.out.println("AJOUTÉ AVEC SUCCÈS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierAssociation(Association association) {
        try {
            String req = "UPDATE association SET nom=?, adresse=?, email=?, rib=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, association.getNom());
            ps.setString(2, association.getAdresse());
            ps.setString(3, association.getEmail());
            ps.setString(4, association.getRib());
            ps.setInt(5, association.getId());
            ps.executeUpdate();
            System.out.println("MODIFIÉ AVEC SUCCÈS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerAssociation(Association association) {
        try {
            String req = "DELETE FROM association WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, association.getId());
            ps.executeUpdate();
            System.out.println("SUPPRIMÉ AVEC SUCCÈS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Association> afficherAssociation() {
        List<Association> list = new ArrayList<>();

        String req = "SELECT * FROM association";

        try (Statement ste = conn.createStatement(); ResultSet rs = ste.executeQuery(req)) {
            while (rs.next()) {
                Association association = new Association();
                association.setId(rs.getInt("id"));
                association.setNom(rs.getString("nom"));
                association.setAdresse(rs.getString("adresse"));
                association.setEmail(rs.getString("email"));
                association.setRib(rs.getString("rib"));
                list.add(association);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public Association rechercherAssociation(int id) {
        Association association = null;
        String req = "SELECT * FROM association WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                association = new Association();
                association.setId(rs.getInt("id"));
                association.setNom(rs.getString("nom"));
                association.setAdresse(rs.getString("adresse"));
                association.setEmail(rs.getString("email"));
                association.setRib(rs.getString("rib"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return association;
    }

    public ObservableList<Integer> listerIds() {
        ObservableList<Integer> observableIds = FXCollections.observableArrayList();
        String req = "SELECT id FROM association";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                observableIds.add(id);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return observableIds;
    }

    public ObservableList<Association> listerAssociations() {
        List<Association> associations = afficherAssociation();
        return FXCollections.observableArrayList(associations);
    }

}
