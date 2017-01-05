/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Quartier;
import bean.Ville;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class QuartierFacade extends AbstractFacade<Quartier>{
    
    public QuartierFacade() {
        super(Quartier.class);
    }
    
    public List<Quartier> findQuartierByVille(Ville ville){
        return getEntityManager().createQuery("SELECT q FROM Quartier q WHERE q.ville.id='"+ville.getId() +"'").getResultList();
    }
    
}
