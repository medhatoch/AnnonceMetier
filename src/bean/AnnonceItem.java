/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author lenovo
 */
@Entity
public class AnnonceItem implements Serializable {

    @ManyToOne
    private Annonce annonce;

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    @ManyToOne
    private Metier metier;
    private int nombre;

    public AnnonceItem() {
    }

    public AnnonceItem(Long id, Metier metier, int nombre) {
        this.id = id;
        this.metier = metier;
        this.nombre = nombre;
    }

    public AnnonceItem(Long id) {
        this.id = id;
    }

    public AnnonceItem(Metier metier, int nombre) {
        this.metier = metier;
        this.nombre = nombre;
    }

    public Annonce getAnnonce() {
        return annonce;
    }

    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

    public Metier getMetier() {
        return metier;
    }

    public void setMetier(Metier metier) {
        this.metier = metier;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof AnnonceItem)) {
            return false;
        }
        AnnonceItem other = (AnnonceItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.DemandeItem[ id=" + id + " ]";
    }

}
