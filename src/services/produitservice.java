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
public class produitservice implements produitInterface <produit>{
    Connection cnx;
    public produitservice()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(produit t) {
try {
            String sql = "insert into produit(nom_prod,description,image,prix,nbr_prods,categorie_id)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,t.getNom());
            ste.setString(2,t.getDesc());
            ste.setString(3,t.getImage());
            ste.setFloat(4,t.getPrix());
            ste.setInt(5, t.getNbr());
            ste.setInt(6, t.getCategorie().getId());
            
           
            ste.executeUpdate();
            System.out.println("produit ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public List<produit> getAll() {
List<produit> produits= new ArrayList<>();
    try {
        String sql = "SELECT produit.id, produit.nom_prod, produit.description,produit.image,produit.prix,produit.nbr_prods, categorie.nom AS cat_nom " +
                     "FROM produit " +
                     "JOIN categorie ON produit.categorie_id = categorie.id";
        Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        
        while (s.next()) {
            produit p = new produit(s.getInt("id"),new categorie(s.getString("cat_nom")),s.getString("nom_prod"),s.getString("description"),s.getString("image"),s.getFloat("prix"),s.getInt("nbr_prods") );
            produits.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return produits;    }

    @Override
    public List<produit> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void supprimer(produit p) {
String sql = "DELETE FROM `produit` WHERE `produit`.`id`=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
            System.out.println("produit supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
        @Override
    public void modifier(String nom, String desc, String image, float prix, int nbr,categorie c, produit t) {
        String sql = "update produit set nom_prod=?,description=?,image=?,prix=?,nbr_prods=?,categorie_id=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setString(2, desc);
            ste.setString(3, image);
            ste.setFloat(4, prix);
            ste.setInt(5, nbr);
            ste.setInt(6,c.getId());
            ste.setInt(7,t.getId());
            ste.executeUpdate();
            System.out.println("modification effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
