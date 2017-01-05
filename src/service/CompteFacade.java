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
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author lenovo
 */
public class CompteFacade extends AbstractFacade<Compte> {

    public CompteFacade() {
        super(Compte.class);
    }

    public void createCompte(String nom, String prenom, int tel, String email, Ville ville, Quartier quartier) {
        Compte compte = new Compte(nom, prenom, tel, email, ville, quartier);
        create(compte);
    }
    
   

    public void modifyCompte(Compte compte, String nom, String prenom, int tel, String email, Ville ville, Quartier quartier) {
        compte.setNom(nom);
        compte.setPrenom(prenom);
        compte.setEmail(email);
        compte.setVille(ville);
        compte.setQuartier(quartier);
        edit(compte);
    }
    
} 