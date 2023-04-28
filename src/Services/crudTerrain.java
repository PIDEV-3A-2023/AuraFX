/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Solde;
import Entities.Terrain;
import Entities.membre;
import Services.CrudMembre;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class crudTerrain implements InterfaceTerrain {
Statement ste ; 
Connection conn = Maconnexion.getInstance().getCnx() ; 


    @Override
   public void ajouterTerrain(Terrain t) {
    try {
        String req = "INSERT INTO `terrain`(`id_terrain`, `adresse`, `surface`, `potentiel`, `id_membre`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(req);
        ps.setInt(1, t.getId_terrain());
        ps.setString(2, t.getAdresse());
        ps.setDouble(3, t.getSurface());
        ps.setDouble(4, t.getPotentiel());
        ps.setInt(5, t.getMembre().getId_membre()); // Utilisation de l'attribut id_membre de l'objet Membre
        ps.executeUpdate();
        System.out.println("Terrain ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du terrain : " + ex.getMessage());
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
     pst.setInt(4, t.getMembre().getId_membre()); 
    
  
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
     membre m = new membre();
            m.setId_membre(RS.getInt("id_membre"));
            s.setMembre(m);
            list.add(s);
        }}
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
   

/*public List<Terrain> displayTerrains() {
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
}*/
    public List<Terrain> displayTerrains() {
    List<Terrain> terrains = new ArrayList<>();
    try {
        String sql = "SELECT t.id_terrain, t.adresse, t.surface, t.potentiel, m.id_membre, m.nom, m.prenom, m.email, m.password, m.date_nais, m.tel, m.adresse, m.role FROM terrain t JOIN membre m ON t.id_membre = m.id_membre";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id_terrain = resultSet.getInt("id_terrain");
            String adresse = resultSet.getString("adresse");
            double surface = resultSet.getDouble("surface");
            double potentiel = resultSet.getDouble("potentiel");
            int id_membre = resultSet.getInt("id_membre");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            Date date_nais = resultSet.getDate("date_nais");
            String tel = resultSet.getString("tel");
            String role = resultSet.getString("role");

            membre membre = new membre(id_membre, nom, prenom, email, password, date_nais, tel, adresse, role);
            Terrain terrain = new Terrain(id_terrain, adresse, surface, potentiel, membre);
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
        String sql = "SELECT * FROM terrain ORDER BY potentiel DESC";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int idTerrain = resultSet.getInt("id_terrain");
            membre membre = new membre();
            membre.setId_membre(resultSet.getInt("id_membre"));
            double surface = resultSet.getDouble("surface");
            String adresse = resultSet.getString("adresse");
            double potentiel = resultSet.getDouble("potentiel");
            Terrain terrain = new Terrain(idTerrain, adresse, surface, potentiel, membre);
            terrains.add(terrain);
        }    
    } catch (SQLException e) {
        System.out.println("Error displaying terrains: " + e.getMessage());
    }
    return terrains;
}

public List<Terrain> displayTerrainRecherche(String adresse) {
    List<Terrain> terrains = new ArrayList<>();
    try {
        // Create a SQL statement to select all terrains matching the given address, surface or potentiel
        String sql = "SELECT * FROM terrain WHERE adresse LIKE ? OR surface LIKE ? OR potentiel LIKE ?";

        // Create a prepared statement object to execute the SQL query
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, "%" + adresse + "%");
        statement.setString(2, "%" + adresse + "%");
        statement.setString(3, "%" + adresse + "%");

        // Execute the SQL statement and get the result set of terrains
        ResultSet resultSet = statement.executeQuery();

        // Print each terrain's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("id_terrain");
            int idMembre = resultSet.getInt("id_membre");
            double surface = resultSet.getDouble("surface");
            String adresseTerrain = resultSet.getString("adresse");
            double potentiel = resultSet.getDouble("potentiel");
            CrudMembre cm = new CrudMembre();
            membre membre = cm.getMembreById(idMembre); // Fetch the corresponding member using the id
            Terrain terrain = new Terrain(id, adresseTerrain, surface, potentiel, membre);
            System.out.println(terrain);
            terrains.add(terrain);
        }

    } catch (SQLException e) {
        System.out.println("Error displaying terrains: " + e.getMessage());
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
            int idMembre = result.getInt("id_membre");

            // Retrieve the member associated with the terrain
                CrudMembre cm = new CrudMembre();
            membre membre = cm.getMembreById(idMembre); 
          

            // Create a new Terrain object with the retrieved information
            terrain = new Terrain(idT, adresse, surface, potentiel, membre);
        } else {
            System.out.println("Terrain not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving publication: " + e.getMessage());
    }
    return terrain;
}

   
    }



 