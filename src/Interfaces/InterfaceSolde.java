/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Solde;
import java.util.List;

/**
 *
 * @author Cyrine
 */
public interface InterfaceSolde {
    public void ajouterSolde (Solde s) ;
   public void ajouterSolde2(Solde s); 
   public void modiferSolde(double montant , int id); 
    public void supprimerSolde  (int id );   
    public void create(int id_terrain, double montant);
  public List<Solde> afficherAll();
}
