/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Solde;
import Entities.Terrain;
import Interfaces.InterfaceTerrain ; 
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Cyrine
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class crudTerrain implements InterfaceTerrain {
Statement ste ; 
Connection conn = Maconnexion.getInstance().getCnx() ; 


    @Override
    public void ajouterTerrain(Terrain t) {
    try { 
         String req = "INSERT INTO `terrain`(`id_terrain`, `adresse`, `surface`, `potentiel`, `id_membre`) VALUES (' "+t.getId_terrain()+"' ,' "+t.getAdresse()+"','" +t.getSurface()+"' , '"+t.getPotentiel()+"' ,' "+t.getId_membre()+"' )" ; 
        ste=conn.createStatement();
         ste.executeUpdate(req);
        System.out.println ("terrain ajouté avec succés !! ") ; 
    } catch (SQLException ex) {
 System.out.println ("terrain non ajouté !! ") ; 
      System.out.println(ex.getMessage());
    }
    }
    
    
    
    @Override
    public void ajouterTerrain2(Terrain t) {
            try {
        String req ="INSERT INTO `terrain`(`id_terrain`, `adresse`, `surface`, `potentiel`, `id_membre`)  VALUES ( ?, ? , ? , ?,?) " ; 
        PreparedStatement pst  = conn.prepareStatement(req);
       
      pst.setInt(1, t.getId_terrain());
     pst.setString(2, t.getAdresse());
      pst.setDouble(3, t.getSurface());
     pst.setInt(4, t.getId_membre()); 
    
      
      System.out.println ("terrain ajouté avec succés !! ") ; 
        }catch(SQLException ex ){
            System.out.println ("terrain non ajouté !! ") ; 
            System.out.println(ex.getMessage());
        } 
    }
   

    @Override  
    public void supprimerTerrain(int id ) {
         String req = "DELETE FROM terrain WHERE id_terrain=?";   
    try { 
         
          PreparedStatement pst = conn.prepareStatement(req);
         pst.setInt(1,id);
        //  pst.execute("SET FOREIGN_KEY_CHECKS=0");
        
        int rowsAffected = pst.executeUpdate();
        
             // Execute the SQL statement and delete the comment
      
        // Check if the comment was deleted
        if (rowsAffected > 0) {
            System.out.println("le terrain a été supprimée avec succès !");
        } else {
            System.out.println("ce terrain'existe pas!!");
        }
    } catch (SQLException e) {
        System.out.println("Error deleting terrain: " + e.getMessage());
    }
} 
    

   @Override
    public List<Terrain> afficherAll() {
        
        List<Terrain> list = new ArrayList<>();
        String req = "Select * FROM `terrain` ";
        Statement ste;
    try {
        ste = conn.createStatement();
          ResultSet RS = ste.executeQuery(req);
            System.out.println ("la liste est affichée !!") ;
    while (RS.next()){
        Terrain s = new Terrain ();
         
        s.setId_terrain(RS.getInt(1));
         s.setAdresse(RS.getString("adresse"));
        s.setSurface(RS.getDouble(3));
         s.setPotentiel(RS.getDouble(4));
         s.setId_membre(RS.getInt(5));
        list.add(s); }}
     catch(SQLException ex) {
        System.out.println ("ERREUR !!");
      
    }
  
        return list ;  
    
    }
 @Override
    
    public void update(int id, double surface ,double potentiel,String adresse) {
    try {
        // Create a SQL statement to update the description of a publication
        String sql = "UPDATE terrain SET surface = ?, adresse = ?,potentiel=? WHERE id_terrain = ?";

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDouble(1, surface);
                statement.setDouble(3, potentiel);
                statement.setString(2,adresse);
        statement.setInt(4, id);

        // Execute the SQL statement and update the publication's description
        int rowsAffected = statement.executeUpdate();

        // Check if the publication was updated
        if (rowsAffected > 0) {
            System.out.println("terrain  updated successfully!");
        } else {
            System.out.println("terrain not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating terrain: " + e.getMessage());
    }
}
   

public List<Terrain> displayTerrains() {
    List<Terrain> terrains = new ArrayList<>();
    try {
        // Create a SQL statement to select all publications
        String sql = "SELECT * FROM terrain";

        // Create a statement object to execute the SQL query
        Statement statement = conn.createStatement();

        // Execute the SQL statement and get the result set of publications
        ResultSet resultSet = statement.executeQuery(sql);
        
        // Print each publication's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("id_terrain");
            int id_membre = resultSet.getInt("id_membre");
            double surface = resultSet.getDouble("surface");
            String adresse = resultSet.getString("adresse");

            double potentiel = resultSet.getDouble("potentiel");
            
            
            Terrain terrain = new Terrain(id,adresse, surface,potentiel, id_membre);
             terrains.add(terrain);
        }
        
    } catch (SQLException e) {
        System.out.println("Error displaying publications: " + e.getMessage());
    }
    return terrains;
}
public List<Terrain> displayTerrainsTrier() {
    List<Terrain> terrains = new ArrayList<>();
    try {
        // Create a SQL statement to select all publications
        String sql = "SELECT * FROM terrain order by potentiel DESC";

        // Create a statement object to execute the SQL query
        Statement statement = conn.createStatement();

        // Execute the SQL statement and get the result set of publications
        ResultSet resultSet = statement.executeQuery(sql);
        
        // Print each publication's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("id_terrain");
            int idPart = resultSet.getInt("id_membre");
            double surface = resultSet.getDouble("surface");
            String adresse = resultSet.getString("adresse");

            double potentiel = resultSet.getDouble("potentiel");
            Terrain terrain = new Terrain(id, adresse,surface, potentiel,idPart);
             terrains.add(terrain);
        }
        
    } catch (SQLException e) {
        System.out.println("Error displaying publications: " + e.getMessage());
    }
    return terrains;
}


public List<Terrain> displayTerrainRecherche(String adressee) {
    List<Terrain> terrains = new ArrayList<>();
    try {
        // Create a SQL statement to select all publications
        String sql = "SELECT * FROM terrain WHERE adresse LIKE ? OR surface LIKE ? OR potentiel LIKE ? ";

        // Create a statement object to execute the SQL query
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, adressee);
        statement.setString(2, adressee);
        
        statement.setString(3, adressee);

        // Execute the SQL statement and get the result set of publications
        ResultSet resultSet = statement.executeQuery();
        
        // Print each publication's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("id_terrain");
            int idPart = resultSet.getInt("id_membre");
            double surface = resultSet.getDouble("surface");
            String adresse = resultSet.getString("adresse");
            double potentiel = resultSet.getDouble("potentiel");
            
            Terrain terrain = new Terrain(id, adresse,surface, potentiel,idPart);
            System.out.println(terrain);
             terrains.add(terrain);
        }
        
    } catch (SQLException e) {
        System.out.println("Error displaying publications: " + e.getMessage());
    }
    return terrains;
}


public Terrain getTerrainById(int id) {
    Terrain terrain = new Terrain();
    try {
        // Create a SQL statement to retrieve a publication by its ID
        String sql = "SELECT * FROM terrain WHERE id_terrain = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        // Execute the SQL statement and retrieve the publication
        ResultSet result = statement.executeQuery();

        // Check if a publication was found
        if (result.next()) {
            // Print the publication's details
             int idT = result.getInt("id_terrain");
        
        
            String adresse = result.getString("adresse");
 double surface = result.getDouble("surface");
            double potentiel = result.getDouble("potentiel");
                int idPart = result.getInt("id_membre");
             terrain = new Terrain(idT, adresse,surface,  potentiel, idPart);
        } else {
            System.out.println("Terrain not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving publication: " + e.getMessage());
    }
    return terrain;
}
    }



 