/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import bean.AnnonceItem;
import java.util.List;
import javafx.scene.control.TableView;

/**
 *
 * @author lenovo
 */
public class AnnonceItemFxHelper extends AbstractFxHelper<AnnonceItem>{
    
    private static AbstractFxHelperItem[] titres;

    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("Metier", "metier"),
            new AbstractFxHelperItem("Nombre", "nombre"),
    };
}   

public AnnonceItemFxHelper(TableView<AnnonceItem> table, List<AnnonceItem> list) {
        super(titres,table,list);
    }
public AnnonceItemFxHelper(TableView<AnnonceItem> table) {
        super(titres, table);
    }
    
}
