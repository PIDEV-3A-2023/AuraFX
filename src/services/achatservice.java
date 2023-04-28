/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.achat;
import entities.facture;
import entities.membre;
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
public class achatservice {
    Connection cnx;
    public achatservice()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    public void ajouter(achat t) {
        try {
                    String sql = "insert into achat(facture_id,membre_id,produit_id,nbr_piece,prix)"
                            + "values (?,?,?,?,?)";
                    PreparedStatement ste = cnx.prepareStatement(sql);
                    ste.setInt(1, t.getFacture().getId());
                    ste.setInt(2,t.getMembre().getId());
                    ste.setInt(3,t.getProduit().getId());
                    ste.setInt(4,t.getNbr());
                    ste.setFloat(5,t.getPrix());





                    ste.executeUpdate();
                    System.out.println("achat ajoutée");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }    
    }
    public int achatparcat(int id){
        int n=0;
        try {
                    String sql = "SELECT COUNT(*) FROM achat " +
                     "INNER JOIN produit ON achat.produit_id = produit.id " +
                     "INNER JOIN categorie ON produit.categorie_id = categorie.id " +
                     "WHERE categorie.id = "+id;
                            
                    PreparedStatement ste = cnx.prepareStatement(sql);
                    
                    ResultSet s = ste.executeQuery(sql);

                    while (s.next()) {
                    n=s.getInt(1);
                }



                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } 
        
        
        return n;
        
    }
     public List<achat> achatparfact(int id) {
        List<achat> achats= new ArrayList<>();
            try {
                String sql = "SELECT a.id, p.nom_prod as pnom, p.prix as pprix, a.nbr_piece, a.prix,a.produit_id FROM achat a JOIN produit p ON a.produit_id = p.id where a.facture_id="+id;
                Statement ste = cnx.createStatement();
                ResultSet s = ste.executeQuery(sql);

                while (s.next()) {
                    achat p =  new achat( new produit(s.getInt("produit_id"), s.getString("pnom")), s.getInt("nbr_piece"), s.getFloat("prix"));
                    achats.add(p);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return achats;    
    }
}
