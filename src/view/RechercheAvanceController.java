/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Annonce;
import bean.AnnonceItem;
import bean.Compte;
import bean.Quartier;
import bean.Ville;
import helper.AnnonceFxHelper;
import helper.AnnonceItemFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.AnnonceFacade;
import service.AnnonceItemFacade;
import service.CompteFacade;
import service.QuartierFacade;
import service.VilleFacade;
import util.DateUtil;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class RechercheAvanceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField titre;
    @FXML
    private ComboBox<Ville> ville = new ComboBox<>();
    @FXML
    private ComboBox<Quartier> quartier = new ComboBox<>();
    @FXML
    private TableView annonce = new TableView();
    @FXML
    private TableView annonceIm = new TableView();
    @FXML
    private ComboBox<Compte> compte = new ComboBox<>();
    @FXML
    private DatePicker datecre = new DatePicker();

    private AnnonceFacade annonceFacade = new AnnonceFacade();
    private AnnonceItem annonceItem = new AnnonceItem();
    private AnnonceItemFacade annonceItemFacade = new AnnonceItemFacade();
    private AnnonceFxHelper annonceFxHelper;
    private AnnonceItemFxHelper annonceItemFxHelper;
    private VilleFacade villeFacade = new VilleFacade();
    private QuartierFacade quartierFacade = new QuartierFacade();
    private CompteFacade compteFacade = new CompteFacade();
   
    

    public void recherche(ActionEvent actionEvent) {
        Date d =  null ;
        if(datecre.getValue()!=null){
            d = DateUtil.convert1( DateUtil.convert(datecre.getValue().toString()));    
        }
        annonceFxHelper.setList(annonceFacade.FindAnnonceAvance(titre.getText(), ville.getValue(), quartier.getValue(), compte.getValue(),d));
    }

    @FXML
    public void findanItemByAnnonce(Event event) {
        Annonce theAnnonce = annonceFxHelper.getSelected();
        annonceItemFxHelper.setList(annonceItemFacade.findAnnonceItemByAnnonce(theAnnonce));
    }

    public void initComboBox() {
        ville.setItems(FXCollections.observableArrayList(villeFacade.findAll()));
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findAll()));
        compte.setItems(FXCollections.observableArrayList(compteFacade.findAll()));
    }

    public void initHelper() {
        annonceItemFxHelper = new AnnonceItemFxHelper(annonceIm);
        annonceFxHelper = new AnnonceFxHelper(annonce);
    }
    
    public void acceuil(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());
    }

    @FXML
    public void findQuartierByVille(Event event) {
        Ville v = ville.getSelectionModel().getSelectedItem();
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findQuartierByVille(v)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();
        // TODO
    }

}
