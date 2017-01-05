/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.Annonce;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author lenovo
 */
public class AnnonceFxHelper extends AbstractFxHelper<Annonce> {

    private static AbstractFxHelperItem[] titres;

    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Titre", "titre"),
            new AbstractFxHelperItem("Ville", "ville"),
            new AbstractFxHelperItem("Quartier", "quartier"),
            new AbstractFxHelperItem("Prix", "prix"),
            new AbstractFxHelperItem("Date", "datecre")
    };
}   

public AnnonceFxHelper(TableView<Annonce> table, List<Annonce> list) {
        super(titres,table,list);
    }
public AnnonceFxHelper(TableView<Annonce> table) {
        super(titres, table);
    }
    
}
