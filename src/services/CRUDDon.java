package services;

import entites.Don;
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
import Interfaces.InterfaceDon;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CRUDDon implements InterfaceDon {

    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterDon(Don don) {
        try {
            String req = "INSERT INTO don(montant, date, carte, message, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setDouble(1, don.getMontant());
            ps.setTimestamp(2, Timestamp.valueOf(don.getDate()));
            ps.setString(3, don.getCarte());
            ps.setString(4, don.getMessage());
            ps.setString(5, don.getEmail());
            ps.executeUpdate();
            System.out.println("AJOUTÉ AVEC SUCCÈS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierDon(Don don) {
        try {
            String req = "UPDATE don SET montant=?, date=?, carte=?, message=?, email=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setDouble(1, don.getMontant());
            ps.setTimestamp(2, Timestamp.valueOf(don.getDate()));
            ps.setString(3, don.getCarte());
            ps.setString(4, don.getMessage());
            ps.setString(5, don.getEmail());
            ps.setInt(6, don.getId());
            ps.executeUpdate();
            System.out.println("MODIFIÉ AVEC SUCCÈS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerDon(Don don) {
        try {
            String req = "DELETE FROM don WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, don.getId());
            ps.executeUpdate();
            System.out.println("SUPPRIMÉ AVEC SUCCÈS");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Don> afficherDon() {
        List<Don> Dons = new ArrayList<>();
        try {
            String req = "SELECT * FROM don";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Don don = new Don();
                don.setId(rs.getInt("id"));
                don.setMontant(rs.getDouble("montant"));
                don.setDate(rs.getTimestamp("date").toLocalDateTime());
                don.setCarte(rs.getString("carte"));
                don.setMessage(rs.getString("message"));
                don.setEmail(rs.getString("email"));
                Dons.add(don);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Dons;
    }

    public Don rechercherDon(int id) {
        Don don = null;
        String req = "SELECT * FROM don WHERE id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                don = new Don();
                don.setId(rs.getInt("id"));
                don.setMontant(rs.getDouble("montant"));
                don.setDate(rs.getObject("date", LocalDateTime.class));
                don.setCarte(rs.getString("carte"));
                don.setMessage(rs.getString("message"));
                don.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return don;
    }

    public ObservableList<Integer> listerIds() {
        ObservableList<Integer> observableIds = FXCollections.observableArrayList();
        String req = "SELECT id FROM don";
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

    public ObservableList<Don> listerDons() {
        List<Don> Don = afficherDon();
        return FXCollections.observableArrayList(Don);
    }

}
