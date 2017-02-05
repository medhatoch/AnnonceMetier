/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author lenovo
 */
@Entity
public class Annonce implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String titre;
    private double prix;
    @OneToMany(mappedBy="annonce",
    cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<AnnonceItem> annonceItems;
    @ManyToOne
    private Ville ville;
    @ManyToOne
    private Quartier quartier;
    @ManyToOne
    private Compte compte;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datecre;


    public Date getDatecre() {
        return datecre;
    }

    public void setDatecre(Date datecre) {
        this.datecre = datecre;
    }

    public Annonce(Long id, String titre, double prix, Ville ville, Quartier quartier, Compte compte, Date datecre) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.ville = ville;
        this.quartier = quartier;
        this.compte = compte;
        this.datecre = datecre;
    }

    
    
    

    public List<AnnonceItem> getAnnonceItems() {
        return annonceItems;
    }

    public void setAnnonceItems(List<AnnonceItem> annonceItems) {
        this.annonceItems = annonceItems;
    }

    public Annonce() {
    }

    public Annonce( String titre, double prix, Ville ville, Quartier quartier) {
        this.titre = titre;
        this.prix = prix;
        this.ville = ville;
        this.quartier = quartier;
    }

    public Annonce(String titre, double prix, Ville ville, Quartier quartier, Compte compte) {
        this.titre = titre;
        this.prix = prix;
        this.ville = ville;
        this.quartier = quartier;
        this.compte = compte;
    }

   
    public Annonce(Long id, String titre, double prix, Ville ville, Quartier quartier, Compte compte) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.ville = ville;
        this.quartier = quartier;
        this.compte = compte;
    }

    public Annonce(Long id, String titre, double prix, List<AnnonceItem> annonceItems, Ville ville, Quartier quartier, Compte compte) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.annonceItems = annonceItems;
        this.ville = ville;
        this.quartier = quartier;
        this.compte = compte;
    }

    
    public Annonce(Long id) {
        this.id = id;
    }
    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }


    public Ville getVille() {
        
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Quartier getQuartier() {
       
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public Compte getCompte() {
        return compte;
    }
    

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annonce)) {
            return false;
        }
        Annonce other = (Annonce) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return titre;
    }

    
    
}
