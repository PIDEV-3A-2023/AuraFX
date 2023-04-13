/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entites.Association;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface InterfaceAssociation {

    public void ajouterAssociation(Association p);

    public void modifierAssociation(Association p);

    public void supprimerAssociation(Association p);

    public List<Association> afficherAssociation();

}
