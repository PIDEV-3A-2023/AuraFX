/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Technicien;
import entity.Terrain;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

/**
 *
 * @author issamfekih.if
 */
public class CRUDterrain {
    Statement ste;
    Connection connection = MyConnection.getInstance().getConnection();
    public List<Terrain> afficherTerrain() {

        List<Terrain> terrains = new ArrayList<>();
        try {

            String sql = "SELECT * FROM terrain";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_membre = resultSet.getInt("membre_id");
                double surface = resultSet.getDouble("surface");
                String adresse = resultSet.getString("adresse");
                double potentiel = resultSet.getDouble("potentiel");
                Terrain terrain = new Terrain(id, adresse, surface, potentiel, id_membre);
                terrains.add(terrain);
            }

        } catch (SQLException e) {
            System.out.println("Error displaying publications: " + e.getMessage());
        }
        return terrains;
    }


}
