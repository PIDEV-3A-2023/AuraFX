/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.achat;
import entities.categorie;
import entities.facture;
import entities.membre;
import entities.produit;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.mail.MessagingException;
import services.EmailSender;
import services.achatservice;
import services.categorieservice;
import services.factureservice;
import services.pdf;
import services.produitservice;
import utils.MaConnection;

/**
 *
 * @author azerb
 */
public class MainClass {
    public static void main(String[] args) throws MessagingException {
       // MaConnection m = new MaConnection();
       categorie c =new categorie(11,"azer","ggg",8);
       //categorieservice cs= new categorieservice();
       produit p=new produit(32,c,"adem","hhhhhhhhhhhhhhhhhhhhhhhhhhhh","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRZ0oIdI0Sg3NQ4RqK6TO40MSbekA2pOJ3fg&usqp=CAU",10,1);
       // produitservice ps=new produitservice();
        //System.out.print( ps.getAll());
        //ps.modifier("azer", "ggg", "hhh", 0, 0, c,p);
        //ps.supprimer(p);
        //cs.modifier("zzz", "zzz", 0, c);
        membre m =new membre(16);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = localDate.format(formatter);
        Date date= Date.valueOf(localDate);
        facture f =new facture(1,m, 0, date);
        achatservice as=new achatservice();
        factureservice fs = new  factureservice();
        System.out.print(as.achatparfact(1));
       // System.out.println(date);
       //achat a =new achat(f, m, p, 1, 5); 
      // as.ajouter(a);
       //System.out.print(""+as.achatparcat(9));
       //pdf pd =new pdf();
       //pdf.generer("", "", "", "", "");
      // pdf.genererFacturesansouvrir("0069", 50, date, as.achatparfact(41));
      // EmailSender.with("azerbennasr@gmail.com", "Subject", "Message body");
//     EmailSender.sendEmail("azerbennasr@gmail.com", "Subject", "Message body");
       
    }
}
