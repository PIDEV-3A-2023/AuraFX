/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import entities.categorie;
import entities.facture;
import entities.membre;
import entities.produit;
import java.sql.Connection;
import java.sql.Date;
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
public class factureservice {
    Connection cnx;
    public factureservice()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    public void ajouter(facture t) {
try {
            String sql = "insert into facture(membre_id,montant,date)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getM().getId());
            ste.setFloat(2,t.getMontant());
            ste.setDate(3,t.getDate());
            
            
            
            
           
            ste.executeUpdate();
            System.out.println("facture ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }
    
    
    public List<facture> getAll() {
        List<facture> factures= new ArrayList<>();
            try {
                String sql = "SELECT * FROM facture ";
                Statement ste = cnx.createStatement();
                ResultSet s = ste.executeQuery(sql);

                while (s.next()) {
                    facture p = new facture(s.getInt("id"),new User(s.getInt("membre_id")),s.getFloat("montant"),s.getDate("date") );

                    factures.add(p);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return factures;    
    }
    public List<facture> getbymem(int id) {
        List<facture> factures= new ArrayList<>();
            try {
                String sql = "SELECT * FROM facture where membre_id= "+id;
                Statement ste = cnx.createStatement();
                ResultSet s = ste.executeQuery(sql);

                while (s.next()) {
                    facture p = new facture(s.getInt("id"),new User(s.getInt("membre_id")),s.getFloat("montant"),s.getDate("date") );

                    factures.add(p);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return factures;    
    }
    public facture lastfacture() {
        List<facture> factures= new ArrayList<>();
        facture p = new facture() ;    
        try {
                String sql = "SELECT * FROM facture ORDER BY id DESC LIMIT 1;";
                Statement ste = cnx.createStatement();
                ResultSet s = ste.executeQuery(sql);

                while (s.next()) {

                     p.setId(s.getInt("id"));
                     p.setMontant(s.getFloat("montant"));
                     p.setM(new User(s.getInt("membre_id")));
                     p.setDate(s.getDate("date"));

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return p;    
    }
    
}
