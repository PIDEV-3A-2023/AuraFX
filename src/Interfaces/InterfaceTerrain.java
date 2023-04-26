/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Terrain;
import java.util.List;

/**
 *
 * @author Cyrine
 */
public interface InterfaceTerrain {
   public void ajouterTerrain (Terrain t) ;
   public void ajouterTerrain2(Terrain t ); 
  
    public void supprimerTerrain  (int id ); 
   public List<Terrain> afficherAll() ; 
    public void update(int id, double surface ,double potentiel,String adresse) ;
}
