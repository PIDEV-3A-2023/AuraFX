/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.produit;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author azerb
 */
public class panier {
    private static panier instance;
    private Map<produit,Integer> p;
    private panier() {
        p = new HashMap<>();
    }
    public static panier getInstance() {
        if (instance == null) {
            instance = new panier();
        }
        return instance;
    }
    public Map<produit, Integer> getProduitsQuantites() {
        return p;
    }
    public void setProduitsQuantites(Map<produit, Integer> produitsQuantites) {
        this.p = produitsQuantites;
    }
    public void ajouterProduit(produit produit) {
    if (p.containsKey(produit)) {
        int quantite = p.get(produit);
        p.put(produit, quantite + 1);
    } else {
        p.put(produit, 1);
    }
}
    public  void decreaseValue(produit key) {
    Integer value = p.get(key);
    if (value != null) {
        value--;
        if (value > 0) {
            p.put(key, value);
        } else {
            p.remove(key);
        }
    }
}
    public  void remove(produit key) {
    p.remove(key);
      }
    public float calculerSommeTotale() {
    float somme = 0;
    for (Map.Entry<produit, Integer> entry : p.entrySet()) {
        produit produit = entry.getKey();
        int quantite = entry.getValue();
        somme += produit.getPrix() * quantite;
    }
    return somme;
}

    
}
