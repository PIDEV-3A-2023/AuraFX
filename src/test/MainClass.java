/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.categorie;
import entities.produit;
import services.categorieservice;
import services.produitservice;
import utils.MaConnection;

/**
 *
 * @author azerb
 */
public class MainClass {
    public static void main(String[] args) {
       // MaConnection m = new MaConnection();
       categorie c =new categorie(11,"azer","ggg",8);
       categorieservice cs= new categorieservice();
      // produit p=new produit(32,c,"adem","hhhhhhhhhhhhhhhhhhhhhhhhhhhh","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRZ0oIdI0Sg3NQ4RqK6TO40MSbekA2pOJ3fg&usqp=CAU",10,1);
       // produitservice ps=new produitservice();
        //System.out.print( ps.getAll());
        //ps.modifier("azer", "ggg", "hhh", 0, 0, c,p);
        //ps.supprimer(p);
        cs.modifier("zzz", "zzz", 0, c);
        
    }
}
