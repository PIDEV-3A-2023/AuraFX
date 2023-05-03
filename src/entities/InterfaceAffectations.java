/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entity.Technicien;
import entity.Terrain;
import entity.Affectations;
import java.util.List;


/**
 *
 * @author issamfekih.if
 */
public interface InterfaceAffectations <Affectations>{

    public void ajouterAffectations(Affectations a);

    public void ajouterAffectations2(Affectations a);

    public void modifierAffectations(Affectations a);

    public void supprimerAffectations(int id);

    public List<Affectations> afficherAffectations();
    
    public List<Affectations> getafficherterrain();
    
    public List<Affectations> getaffichertechnicien();
}
