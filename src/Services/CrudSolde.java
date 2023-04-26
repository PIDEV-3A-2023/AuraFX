/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Solde;
import Interfaces.InterfaceSolde ; 
import Utils.Maconnexion;
import java.sql.Statement;
import java.util.List;
import Utils.Maconnexion ; 
import java.sql.Connection ; 
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
  
        
      String req = "INSERT INTO `solde`(`id_part` , `montant`, `date`, `id_terrain`) VALUES ( ' "+s.getId_part()+"' ,' "+s.getMontant()+"','" +s.getDate()+"' , '"+s.getId_terrain()+"' )" ; 
    try { 
        ste=conn.createStatement();
   
        ste.executeUpdate(req); 
          
        System.out.println ("solde ajouté avec succés !! ") ; 
    } catch (SQLException ex) {
      System.out.println ("solde non ajouté !! ") ; 
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
        String req ="INSERT INTO `solde`(`id_part` , `montant`, `date`, `id_terrain`) VALUES (  ? , ? , ?,?) " ; 
        PreparedStatement pst  = conn.prepareStatement(req);
        
      pst.setInt(1, s.getId_part());
     pst.setDouble(2, s.getMontant());
      pst.setString(3, s.getDate());
     pst.setInt(4, s.getId_terrain()); 

      System.out.println ("solde ajouté avec succés !! ") ; 
        }catch(SQLException ex ){
            System.out.println ("solde non ajouté !! ") ; 
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
             String req = "DELETE FROM solde WHERE id_part  = ?  ";
    try { 
       
          PreparedStatement pst = conn.prepareStatement(req);
        pst.setInt(1,id);
         
    
         int rowsAffected = pst.executeUpdate();
             // Execute the SQL statement and delete the comment
      
        // Check if the comment was deleted
        if (rowsAffected > 0) {
            System.out.println("solde a été supprimée avec succès !");
        } else {
            System.out.println("ce solde n'existe pas!!");
        }
    } catch (SQLException e) {
        System.out.println("Error deleting Solde: " + e.getMessage());
    }
} 
       

    @Override
    public List<Solde> afficherAll() {
        
        List<Solde> list = new ArrayList<>();
        String req = "Select * FROM `solde` ";
        Statement ste;
    try {
        ste = conn.createStatement();
          ResultSet RS = ste.executeQuery(req);
            System.out.println ("la liste est affichée !!") ;
    while (RS.next()){
        Solde s = new Solde ();     
        s.setId_part(RS.getInt(1));
         s.setId_terrain(RS.getInt(2));
         s.setMontant(RS.getDouble(3));
        s.setDate(RS.getString(4));
       
        list.add(s); }}
     catch(SQLException ex) {
        System.out.println (ex.getMessage());
      
    }
        return list ;  
    }

   
    public List<Solde> displaySoldes(int idTerrain) {
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
            String date = resultSet.getString("date");
            int id_Terrain = resultSet.getInt("id_terrain");
            double montant = resultSet.getDouble("montant");
            Solde c = new Solde(id, id_Terrain, montant, date);
            System.out.println("ID: " + id);
            System.out.println("date: " + date);
            System.out.println("montant ID: " + montant);
    list.add(c);
        }
    } catch (SQLException e) {
        System.out.println("Error displaying soldes: " + e.getMessage());
    }
    return list;
}
    }



           
