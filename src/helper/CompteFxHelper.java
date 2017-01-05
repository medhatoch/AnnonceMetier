/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Compte;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author lenovo
 */
public class CompteFxHelper extends AbstractFxHelper<Compte>{
    
    private static AbstractFxHelperItem[] titres;

    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Nom", "nom"),
            new AbstractFxHelperItem("Prenom", "prenom"),
            new AbstractFxHelperItem("Ville", "ville"),
            new AbstractFxHelperItem("Quartier", "quartier"),
            new AbstractFxHelperItem("Email", "email"),
            new AbstractFxHelperItem("Tel", "tel")
    };
}   

public CompteFxHelper(TableView<Compte> table, List<Compte> list) {
        super(titres,table,list);
    }
public CompteFxHelper(TableView<Compte> table) {
        super(titres, table);
    }
    
}
