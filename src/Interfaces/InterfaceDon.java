package Interfaces;

import entites.Don;
import java.util.List;

/**
 *
 * @author belkn
 */
public interface InterfaceDon {

    public void ajouterDon(Don p);

    public void modifierDon(Don p);

    public void supprimerDon(Don p);

    public List<Don> afficherDon();

}
