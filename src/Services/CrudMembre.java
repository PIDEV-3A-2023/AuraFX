/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.membre;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Cyrine
 */
public class CrudMembre {
    Statement ste ; 
Connection conn = Maconnexion.getInstance().getCnx() ;

   public membre getMembreById(int idMembre) throws SQLException {
    String sql = "SELECT * FROM membre WHERE id_membre = ?";
    PreparedStatement statement = conn.prepareStatement(sql);
    statement.setInt(1, idMembre);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) {
        int id = resultSet.getInt("id_membre");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String adresse = resultSet.getString("adresse");
        String email = resultSet.getString("EMAIL");
        String telephone = resultSet.getString("telephone");
        return new membre(id, nom, prenom, adresse, email, telephone);
    } else {
        throw new SQLException("No member found with id " + idMembre);
    }
} 
}
