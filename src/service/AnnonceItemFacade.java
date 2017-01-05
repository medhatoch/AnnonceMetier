/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Annonce;
import bean.AnnonceItem;
import bean.Metier;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class AnnonceItemFacade extends AbstractFacade<AnnonceItem>{
    
    public AnnonceItemFacade() {
        super(AnnonceItem.class);
    }
    
    /*public AnnonceItem CreateDemandeItem(Metier metier, int nombre){
    AnnonceItem annonceItem = new AnnonceItem(generateId("AnnonceItem", "id"),metier, nombre);
    return annonceItem;
    }
    */
    public List<AnnonceItem> findAnnonceItemByAnnonce(Annonce annonce){
        return getEntityManager().createQuery("SELECT a FROM AnnonceItem a WHERE a.annonce.id="+annonce.getId()).getResultList();
    }
        
}
