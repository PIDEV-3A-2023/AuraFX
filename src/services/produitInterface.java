/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.categorie;
import entities.produit;
import java.util.List;

/**
 *
 * @author azerb
 */
public interface produitInterface <P>{
    public void ajouter(P t);
    public List<P> getAll();
    public List<P> findById(int id);
  public void modifier(String nom,String desc,String image,float prix,int nbr,categorie c,produit t);
     void supprimer(P t);
}
