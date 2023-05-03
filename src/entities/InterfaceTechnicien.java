/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import entity.Technicien;

/**
 *
 * @author issamfekih.if
 */
public interface InterfaceTechnicien <Technicien>{

    public void ajouterTechnicien(Technicien t);

    public void ajouterTechnicien2(Technicien t);

    public void updateTechnicien(Technicien t);

    public void supprimerTechnicien(int id);

    public List<Technicien> afficherTechnicien();

    public List<Technicien> afficherTechnicien2(int technicienId);


}
