/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Annonce;
import bean.AnnonceItem;
import bean.Compte;
import bean.Quartier;
import bean.Ville;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class AnnonceFacade extends AbstractFacade<Annonce> {

    public AnnonceFacade() {
        super(Annonce.class);
    }
    private CompteFacade compteFacade = new CompteFacade();
    private AnnonceItemFacade annonceItemFacade = new AnnonceItemFacade();

    
   
    public void save(Annonce annonce, List<AnnonceItem> annonceItems) {
        create(annonce);
        for (AnnonceItem annonceItem : annonceItems) {
            annonceItem.setAnnonce(annonce);
            annonceItem.setId(annonceItemFacade.generateId("AnnonceItem", "id"));
            annonceItemFacade.create(annonceItem);
        }
    }

    public List<Annonce> FindAnnonce(String titre, Ville ville, Quartier quartier) {
        String requette = "SELECT annonce FROM Annonce annonce WHERE 1=1";
        if (!"".equals(titre)) {
            requette += " AND annonce.titre='" + titre + "'";
        }
        if (ville != null) {
            requette += " AND annonce.ville.id=" + ville.getId();
        }
        if (quartier != null && quartier.getId() != null) {
            requette += " AND annonce.quartier.id=" + quartier.getId();
        }
        return getEntityManager().createQuery(requette).getResultList();
    }
    
    public List<Annonce> FindAnnonceAvance(String titre,Ville ville,Quartier quartier,Compte compte,Date datcre){
        String requette = "SELECT annonce FROM Annonce annonce WHERE 1=1";
        if(!"".equals(titre)){
           requette += " AND annonce.titre='"+ titre + "'";
        }
        if (ville != null) {
            requette += " AND annonce.ville.id=" + ville.getId();
        }
        if (quartier != null && quartier.getId() != null) {
            requette += " AND annonce.quartier.id=" + quartier.getId();
        }
        if (compte != null) {
            requette += " AND annonce.compte.id=" + compte.getId();
        }if(datcre !=null){
            requette += " AND annonce.datecre='"+ datcre +"'";
        }
        
        return getEntityManager().createQuery(requette).getResultList();
    }

    public List<Compte> findCompteByAnnonce(Annonce annonce) {
        return getEntityManager().createQuery("SELECT c.compte FROM Annonce c WHERE c.id=" + annonce.getId()).getResultList();
    }

    public List<Annonce> findAnnonceByCompte(Compte compte) {
        return getEntityManager().createQuery("SELECT a FROM Annonce a WHERE a.compte.id=" + compte.getId()).getResultList();
    }

    
    public void deleteAnnonce(Annonce annonce) {
        List<AnnonceItem> annonceItems = new ArrayList<>();
        annonceItems = annonceItemFacade.findAnnonceItemByAnnonce(annonce);
        for (AnnonceItem annonceItem : annonceItems) {
            annonceItemFacade.remove(annonceItem);
        }
        remove(annonce);
    }
    
    public void deleteCompte(Compte compte){
        List<Annonce> annonces = new ArrayList<>();
        for (Annonce annonce : annonces) {
            deleteAnnonce(annonce);
        }
        compteFacade.remove(compte);
    }

}
