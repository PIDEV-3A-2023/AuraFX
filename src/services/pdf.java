/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.achat;
import java.awt.Desktop;
import java.io.File;
/**
 *
 * @author azerb
 */
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class pdf {
    public static void generer(String id, String nom_event, String heure_debut, String heyre_fin, String localisation) {
        try {
            // Créer un nouveau document PDF
            Document document = new Document();
            // Écrire dans un fichier de sortie
            String outputFilePath = "invitation.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
            // Ouvrir le document
            document.open();
            
            // Ajouter l'image en haut du document
            
//            Image image = Image.getInstance("‪C:\\Users\\azerb\\Desktop\\po.PNG");
            
/*image.scaleToFit(100, 100); // définir la taille de l'image
image.setAbsolutePosition(document.right() - 100, document.top() - 100); // définir la position de l'image
document.add(image);*/
            
            // Ajouter le texte formaté
            Paragraph p = new Paragraph();
            p.setAlignment(Element.ALIGN_CENTER);
            p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
            p.add("Invitation\n\n");
            p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
            p.add("Vous êtes cordialement invités à l'événement suivant :\n\n");
            p.add("Nom de l'événement : " + nom_event + "\n\n");
            p.add("Heure de début : " + heure_debut + "\n\n");
            p.add("Heure de fin : " + heyre_fin + "\n\n");
            p.add("Localisation : " + localisation + "\n\n");
            p.add("Nous espérons vous voir nombreux pour partager ce moment avec nous.\n\n");
            p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
            p.add("Merci de confirmer votre présence avant le " + LocalDate.now().plusDays(7) + ".");
            
            // Ajouter le paragraphe au document
            document.add(p);
            // Fermer le document
            document.close();
            System.out.println("Le fichier PDF a été généré avec succès !");

            // Ouvrir le fichier PDF avec le programme par défaut
            File file = new File(outputFilePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   public static void genererFacture(String idFacture, double montant, LocalDate date, List<achat> listeAchats) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();
        // Écrire dans un fichier de sortie
        String outputFilePath = "facture.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        // Ouvrir le document
        document.open();

        // Ajouter le titre
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
        p.add("Facture\n\n");
        document.add(p);

        // Ajouter l'ID de la facture, le montant et la date
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
        p.add("ID de la facture : " + idFacture + "\n\n");
        p.add("Montant : " + montant + " euros\n\n");
        p.add("Date : " + date.toString() + "\n\n");
        document.add(p);

        // Ajouter le tableau des achats
        PdfPTable table = new PdfPTable(3); // 3 colonnes pour ID produit, nom produit, prix
        table.setWidthPercentage(100); // la table prend 100% de la largeur du document
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Ajouter l'en-tête de la table
        PdfPCell cell = new PdfPCell(new Phrase("ID produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        // Ajouter les lignes de la table
        for (achat achat : listeAchats) {
            table.addCell(String.valueOf(achat.getProduit().getId())); // ID produit
            table.addCell(achat.getProduit().getNom()); // Nom produit
            table.addCell(String.valueOf(achat.getProduit().getPrix())); // Prix
        }

        // Ajouter la table au document
        document.add(table);

        // Fermer le document
        document.close();
        System.out.println("Le fichier PDF de la facture a été généré avec succès !");

        // Ouvrir le fichier PDF avec le programme par défaut
        File file = new File(outputFilePath);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   public static void genererFacture1(String idFacture, double montant, LocalDate date, List<achat> listeAchats) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();
        // Écrire dans un fichier de sortie
        String outputFilePath = "facture.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        // Ouvrir le document
        document.open();

        // Ajouter le titre
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
        p.add("Facture\n\n");
        document.add(p);

        // Ajouter l'ID de la facture, le montant et la date
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
        p.add("ID de la facture : " + idFacture + "\n\n");
        p.add("Montant : " + montant + " euros\n\n");
        p.add("Date : " + date.toString() + "\n\n");
        document.add(p);

        // Ajouter le tableau des achats
        PdfPTable table = new PdfPTable(3); // 3 colonnes pour ID produit, nom produit, prix
        table.setWidthPercentage(100); // la table prend 100% de la largeur du document
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Définir les styles de cellules
        Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
        Font dataFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
        BaseColor headerBackgroundColor = new BaseColor(220, 220, 220);

        // Ajouter l'en-tête de la table
        PdfPCell cell = new PdfPCell(new Phrase("ID produit", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(headerBackgroundColor);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom produit", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(headerBackgroundColor);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix", headerFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(headerBackgroundColor);
        table.addCell(cell);

        // Ajouter les lignes de la table
        for (achat achat : listeAchats) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(achat.getProduit().getId()), dataFont))); // ID produit
            table.addCell(new PdfPCell(new Phrase(achat.getProduit().getNom(), dataFont))); // Nom produit
            table.addCell(new PdfPCell(new Phrase(String.valueOf(achat.getProduit().getPrix()), dataFont))); // Prix
        }

        // Ajouter la table au document
        document.add(table);

        // Fermer le document
        document.close();
        System.out.println("Le fichier PDF de la facture a été généré avec succès !");

        // Ouvrir le fichier PDF avec le programme par défaut
        File file = new File(outputFilePath);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  public static void genererFacture2(String idFacture, double montant, LocalDate date, List<achat> listeAchats) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();
        // Écrire dans un fichier de sortie
        String outputFilePath = "facture.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        // Ouvrir le document
        document.open();

        // Ajouter le titre
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
        p.add("Facture\n\n");
        document.add(p);

        // Ajouter l'ID de la facture, le montant et la date
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
        p.add("ID de la facture : " + idFacture + "\n\n");
        p.add("Montant : " + montant + " euros\n\n");
        p.add("Date : " + date.toString() + "\n\n");
        document.add(p);

        // Ajouter le tableau des achats
        PdfPTable table = new PdfPTable(4); // 4 colonnes pour ID produit, nom produit, nbr_piece et prix total
        table.setWidthPercentage(100); // la table prend 100% de la largeur du document
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Ajouter l'en-tête de la table
        PdfPCell cell = new PdfPCell(new Phrase("ID produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nombre de pièces"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix total"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        // Ajouter les lignes de la table
        for (achat achat : listeAchats) {
            table.addCell(String.valueOf(achat.getProduit().getId())); // ID produit
            table.addCell(achat.getProduit().getNom()); // Nom produit
            table.addCell(String.valueOf(achat.getNbr())); // Nombre de pièces
            table.addCell(String.valueOf(achat.getPrix())); // Prix total
        }

        // Ajouter la table au document
        document.add(table);

        // Fermer le document
        document.close();
        System.out.println("Le fichier PDF de la facture a été généré avec succès !");

        // Ouvrir le fichier PDF avec le programme par défaut
        File file = new File(outputFilePath);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  public static void genererFacture3(String idFacture, double montant, Date date, List<achat> listeAchats) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();
        // Écrire dans un fichier de sortie
        String outputFilePath = "facture.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        // Ouvrir le document
        document.open();

        // Ajouter le titre
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
        p.add("Facture\n\n");
        document.add(p);

        // Ajouter l'ID de la facture, le montant et la date
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
        p.add("ID de la facture : " + idFacture + "\n");
        p.add("Date : " + date.toString() + "\n\n");
        document.add(p);

        // Ajouter la table des achats
        PdfPTable table = new PdfPTable(4); // 4 colonnes pour ID produit, nom produit, nbr_piece et prix total
        table.setWidthPercentage(100); // la table prend 100% de la largeur du document
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Ajouter l'en-tête de la table
        PdfPCell cell = new PdfPCell(new Phrase("ID produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nbr de pièces"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix total"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        // Ajouter les lignes de la table
        for (achat achat : listeAchats) {
            table.addCell(String.valueOf(achat.getProduit().getId())); // ID produit
            table.addCell(achat.getProduit().getNom()); // Nom produit
            table.addCell(String.valueOf(achat.getNbr())); // Nbr de pièces
            table.addCell(String.valueOf(achat.getPrix()) + " euros"); // Prix total
        }

        // Ajouter la table au document
        document.add(table);

        // Ajouter le montant total de la facture
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
        p.add("\nMontant total : " + montant + " euros\n\n");
        document.add(p);

        // Fermer le document
        document.close();
        System.out.println("Le fichier PDF de la facture a été généré avec succès !");

        // Ouvrir le fichier PDF avec le programme par défaut
        File file = new File(outputFilePath);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
  public static void genererFacturesansouvrir(String idFacture, double montant, Date date, List<achat> listeAchats) {
    try {
        // Créer un nouveau document PDF
        Document document = new Document();
        // Écrire dans un fichier de sortie
        String outputFilePath = "facture.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
        // Ouvrir le document
        document.open();

        // Ajouter le titre
        Paragraph p = new Paragraph();
        p.setAlignment(Element.ALIGN_CENTER);
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
        p.add("Facture\n\n");
        document.add(p);

        // Ajouter l'ID de la facture, le montant et la date
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
        p.add("ID de la facture : " + idFacture + "\n");
        p.add("Date : " + date.toString() + "\n\n");
        document.add(p);

        // Ajouter la table des achats
        PdfPTable table = new PdfPTable(4); // 4 colonnes pour ID produit, nom produit, nbr_piece et prix total
        table.setWidthPercentage(100); // la table prend 100% de la largeur du document
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Ajouter l'en-tête de la table
        PdfPCell cell = new PdfPCell(new Phrase("ID produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nom produit"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Nbr de pièces"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix total"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);

        // Ajouter les lignes de la table
        for (achat achat : listeAchats) {
            table.addCell(String.valueOf(achat.getProduit().getId())); // ID produit
            table.addCell(achat.getProduit().getNom()); // Nom produit
            table.addCell(String.valueOf(achat.getNbr())); // Nbr de pièces
            table.addCell(String.valueOf(achat.getPrix()) + " euros"); // Prix total
        }

        // Ajouter la table au document
        document.add(table);

        // Ajouter le montant total de la facture
        p = new Paragraph();
        p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
        p.add("\nMontant total : " + montant + " euros\n\n");
        document.add(p);

        // Fermer le document
        document.close();
        System.out.println("Le fichier PDF de la facture a été généré avec succès !");

        // Ouvrir le fichier PDF avec le programme par défaut
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}




}