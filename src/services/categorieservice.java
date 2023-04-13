/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.categorie;
import entities.produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MaConnection;

/**
 *
 * @author azerb
 */
public class categorieservice{
    Connection cnx;
    public categorieservice()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    public List getAll() {
List<categorie> cats= new ArrayList<>();
    try {
        String sql = "SELECT * FROM categorie";
        Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        
        while (s.next()) {
            categorie c = new categorie(s.getInt("id"), s.getString("nom"), s.getString("image"), s.getInt("nbrproduit"));
            cats.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return cats;    }

    public void ajouter(categorie t) {
        try {
        String sql = "INSERT INTO categorie(nom,image,nbrproduit) VALUES (?,?,?)"
                    ;
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,t.getNom());
            ste.setString(2,t.getImage());
            ste.setInt(3,t.getNbrproduit());
            
            
           
            ste.executeUpdate();
            System.out.println("categorie ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void modifier(String nom, String image, int nbr, categorie c) {
                String sql = "update categorie set nom=?,image=?,nbrproduit=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setString(2, image);
            ste.setInt(3, nbr);
            ste.setInt(4,c.getId());
            ste.executeUpdate();
            System.out.println("modification effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(categorie t) {
            String sql = "DELETE FROM `categorie` WHERE `categorie`.`id`=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("categorie supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
