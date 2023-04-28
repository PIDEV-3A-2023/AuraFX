/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Services.crudTerrain;
  import Entities.Solde;

  import Entities.Terrain;

  import Entities.membre;
import Interfaces.InterfaceSolde ; 
import Utils.Maconnexion;
import java.sql.Statement;
import java.util.List;
import Utils.Maconnexion ; 
import java.sql.Connection ; 
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cyrine
 */
public class CrudSolde implements InterfaceSolde {
Statement ste ; 
Connection conn = Maconnexion.getInstance().getCnx() ;


    @Override
public void ajouterSolde(Solde s) {
    String req = "INSERT INTO `solde`(`id_part`, `montant`, `date`, `id_terrain`) VALUES ('" + s.getId() + "', '" + s.getMontant() + "', '" + s.getDate() + "', '" + s.getTerrain().getId_terrain() + "')";
    try { 
        ste = conn.createStatement();
        ste.executeUpdate(req); 
        System.out.println("Solde ajouté avec succès !!"); 
    } catch (SQLException ex) {
        System.out.println("Solde non ajouté !!"); 
        System.out.println(ex.getMessage());
    }
}
    
    public void create(int id_terrain, double montant) {
    try {
        // Create a SQL statement to insert data into the "commentaires" table
        String sql = "INSERT INTO solde ( id_terrain, montant, date) VALUES (?, ?, NOW())";

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id_terrain);
        statement.setDouble(2, montant);

        // Execute the SQL statement and insert the commentaire into the database
        statement.executeUpdate();
        
    } catch (SQLException e) {
        System.out.println("Error : " + e.getMessage());
    }
}


    @Override
public void ajouterSolde2(Solde s) {
    try {
        String req = "INSERT INTO `solde`(`id_part`, `montant`, `date`, `id_terrain`) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(req);
        
        pst.setInt(1, s.getId());
        pst.setDouble(2, s.getMontant());
        pst.setDate(3, new java.sql.Date(s.getDate().getTime())); // convertir Date en java.sql.Date
        pst.setInt(4, s.getTerrain().getId_terrain()); // utiliser la méthode getId_terrain() de l'objet Terrain
        
        pst.executeUpdate(); // exécuter la requête SQL
        
        System.out.println("Solde ajouté avec succès !!");
    } catch (SQLException ex) {
        System.out.println("Solde non ajouté !!"); 
        System.out.println(ex.getMessage());
    }
}

    @Override 
    public void modiferSolde( double montant , int id) {
        
     String req  = "update solde SET montant = ?  where  id_part = ?";
        PreparedStatement pst;
    try {
        pst = conn.prepareStatement(req);
       pst.setDouble(1 , montant);   
             pst.setInt(2, id); 
    
              
              int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                   System.out.println( " la table solde est modifiée avec succées ");
        } else {
            System.out.println("pas de solde  trouvé avec cet ID");
        }
    } catch (SQLException e) {
        System.out.println("Erreur de modification : " + e.getMessage());
    }
    
}

    @Override
    public void supprimerSolde(int id) {
   String req = "DELETE FROM solde WHERE id_part = ?"; // Modifier la requête SQL pour supprimer le solde en fonction de la clé étrangère membre dans Terrain
try { 
   // Remplacez les variables url, username et password avec les informations de connexion à la base de données
    PreparedStatement pst = conn.prepareStatement(req);
    pst.setInt(1, id);  // Modifier l'argument pour qu'il corresponde à la clé étrangère membre
    int rowsAffected = pst.executeUpdate();
  
   // Vérifiez si le solde a été supprimé avec succès
    if (rowsAffected > 0) {
        System.out.println("Solde a été supprimé avec succès !");
    } else {
        System.out.println("Ce solde n'existe pas !!");
    }
} catch (SQLException e) {
    System.out.println("Error deleting Solde: " + e.getMessage());
}
    }

@Override
public List<Solde> afficherAll() {
    List<Solde> list = new ArrayList<>();
    String req = "SELECT s.id_part, s.montant, s.date, t.id_terrain, t.adresse, t.surface, t.potentiel, m.id_membre, m.nom, m.prenom " +
                 "FROM solde s " +
                 "JOIN terrain t ON s.id_terrain = t.id_terrain " +
                 "JOIN membre m ON t.id_membre = m.id_membre";
    Statement ste;
    try {
        ste = conn.createStatement();
        ResultSet RS = ste.executeQuery(req);
        System.out.println("La liste des soldes est affichée !!");
        while (RS.next()) {
            Solde s = new Solde();
            
            s.setId(RS.getInt("id_part"));
            s.setId(RS.getInt("id_terrain"));
            s.setMontant(RS.getDouble("montant"));
            s.setDate(RS.getDate("date"));
            
            Terrain t = new Terrain();
            t.setId_terrain(RS.getInt("id_terrain"));
            t.setAdresse(RS.getString("adresse"));
            t.setSurface(RS.getDouble("surface"));
            t.setPotentiel(RS.getDouble("potentiel"));
            
            membre m = new membre();
            m.setId_membre(RS.getInt("id_membre"));
            m.setNom(RS.getString("nom"));
            m.setPrenom(RS.getString("prenom"));
            
            t.setMembre(m);
            s.setTerrain(t);
            
            list.add(s);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'affichage des soldes : " + ex.getMessage());
    }
    return list;
}



 
   /* public List<Solde> displaySoldes(int idTerrain) {
         List<Solde> list = new ArrayList<>();

    try { 
        // Create a SQL statement to select all comments associated with a publication
        String sql = "SELECT * FROM solde WHERE id_terrain = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idTerrain);

        // Execute the SQL statement and get the result set of comments
        ResultSet resultSet = statement.executeQuery();
        
        // Print each comment's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("id_part"); 
            Date date = resultSet.getDate("date");
            int id_Terrain = resultSet.getInt("id_terrain");
            double montant = resultSet.getDouble("montant");
            Terrain t = new Terrain (); 
            t.setId_terrain(id_Terrain);
            Solde c = new Solde(id, t, montant, date);
            System.out.println("ID: " + id);
            System.out.println("date: " + date);
            System.out.println("montant ID: " + montant);
    list.add(c);
        }
    } catch (SQLException e) {
        System.out.println("Error displaying soldes: " + e.getMessage());
    }
    return list;
}*/ 
public List<Solde> displaySoldes(int idTerrain) {
    List<Solde> list = new ArrayList<>();

    try { 
        // Create a SQL statement to select all soldes associated with a terrain
        String sql = "SELECT * FROM solde WHERE id_terrain = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idTerrain);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id_part"); 
            Date date = resultSet.getDate("date");
            int idTerrainSolde = resultSet.getInt("id_terrain");
            double montant = resultSet.getDouble("montant");

            // Create a new SQL statement to select the details of the associated terrain
            String sqlTerrain = "SELECT * FROM terrain WHERE id_terrain = ?";
            PreparedStatement statementTerrain = conn.prepareStatement(sqlTerrain);
            statementTerrain.setInt(1, idTerrainSolde);
            ResultSet resultSetTerrain = statementTerrain.executeQuery();

            Terrain terrain = null;
            if (resultSetTerrain.next()) {
                terrain = new Terrain();
                terrain.setId_terrain(idTerrainSolde);
                terrain.setAdresse(resultSetTerrain.getString("adresse"));
                terrain.setSurface(resultSetTerrain.getDouble("surface"));
                terrain.setPotentiel(resultSetTerrain.getDouble("potentiel"));
            }

            Solde solde = new Solde(id, terrain, montant, date);
            System.out.println("ID: " + id);
            System.out.println("Date: " + date);
            System.out.println("Montant: " + montant);
            System.out.println("Terrain: " + terrain);

            list.add(solde);
        }
    } catch (SQLException e) {
        System.out.println("Error displaying soldes: " + e.getMessage());
    }

    return list;
}
public double getSommeSoldesParTerrain(int idTerrain) {
    double sommeSoldes = 0;
    CrudSolde crudSolde = new CrudSolde();
    List<Solde> soldesTerrain = crudSolde.displaySoldes(idTerrain);

    for (Solde solde : soldesTerrain) {
        sommeSoldes += solde.getMontant();
    }

    return sommeSoldes;
}
}



           
