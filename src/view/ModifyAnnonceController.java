/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Annonce;
import bean.AnnonceItem;
import bean.Compte;
import bean.Metier;
import bean.Quartier;
import bean.Ville;
import helper.AnnonceFxHelper;
import helper.AnnonceItemFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.AnnonceFacade;
import service.AnnonceItemFacade;
import service.CompteFacade;
import service.MetierFacade;
import service.QuartierFacade;
import service.VilleFacade;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifyAnnonceController implements Initializable {

   /**
     * Initializes the controller class.
     */
    @FXML
    private TextField titre;
    @FXML
    private ComboBox<Compte> compte= new ComboBox<>();
    @FXML
    private TextField prix;
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<Ville> ville = new ComboBox<>();
    @FXML
    private ComboBox<Quartier> quartier = new ComboBox<>();
    @FXML
    private ComboBox<Metier> metier = new ComboBox<>();
    @FXML
    private ComboBox<Annonce> annonce = new ComboBox<>();
    @FXML
    private TableView annonceItem = new TableView();
    
    AnnonceFacade annonceFacade = new AnnonceFacade();
    private AnnonceItemFxHelper annonceItemFxHelper ;
    private AnnonceFxHelper annonceFxHelper;
    private VilleFacade villeFacade = new VilleFacade();
    private QuartierFacade quartierFacade = new QuartierFacade();
    private MetierFacade metierFacade = new MetierFacade();
    private CompteFacade compteFacade = new CompteFacade();
    private AnnonceItemFacade annonceItemFacade=new AnnonceItemFacade();
    
    
    private AnnonceItem getParamAnnonceItem() {
        AnnonceItem annonceIte;
        annonceIte = new AnnonceItem(metier.getSelectionModel().getSelectedItem(),Integer.parseInt(nombre.getText()));
        return annonceIte;
    }
    
    
    private Annonce getParamAnnonce(){
        Annonce annonce = new Annonce(annonceFacade.generateId("Annonce", "id"),titre.getText(),Integer.parseInt(prix.getText()), ville.getValue(), quartier.getValue(),compte.getSelectionModel().getSelectedItem());
        return annonce;
    }
 
    @FXML
    public void modifierAnnonce(ActionEvent actionEvent) throws IOException{
        Annonce annonce = getParamAnnonce();
        annonceFacade.modifyAnnonce(annonce, titre.getText(), ville.getValue(), quartier.getValue(), Integer.parseInt(prix.getText()), annonceItemFxHelper.getTable().getItems());
      annonceFacade.deleteAnnonce(annonce);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("L'annonce a ete modifie ! ");
            alert.showAndWait();
            ViewLauncher.forward(actionEvent, "ModifyAnnonce.fxml", this.getClass());
    }
    
    public void remplirAnnonce(Event event){
        titre.setText(annonce.getValue().getTitre());
        prix.setText(""+annonce.getValue().getPrix());
        ville.setValue(annonce.getValue().getVille());
        quartier.setValue(annonce.getValue().getQuartier());
        annonceItemFxHelper.getTable().getItems().setAll((annonce.getValue().getAnnonceItems()));
    }
    
    @FXML
    public void saveAnnonceItem(ActionEvent actionEvent){
                AnnonceItem annonceItem = getParamAnnonceItem();
            annonceItemFxHelper.getTable().getItems().add(annonceItem);
    }
    
    public void deleteAnnonceItem(ActionEvent actionEvent){
        int index =  this.annonceItem.getSelectionModel().getSelectedIndex();
        annonceItemFxHelper.getTable().getItems().remove(index);
    }
    
    AnnonceItem a = new AnnonceItem();
    @FXML
    public void getAnnonceItem(Event event){
        AnnonceItem a = (AnnonceItem) annonceItem.getSelectionModel().getSelectedItem();
    }
    
    public void initComboBox() {
        ville.setItems(FXCollections.observableArrayList(villeFacade.findAll()));
        ville.getSelectionModel().select(0);
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findAll()));
        metier.setItems(FXCollections.observableArrayList(metierFacade.findAll()));
        compte.setItems(FXCollections.observableArrayList(compteFacade.findAll()));
        annonce.setItems(FXCollections.observableArrayList(annonceFacade.findAll()));
    }
    
    public void initHelper(){
        annonceItemFxHelper = new AnnonceItemFxHelper(annonceItem);
    }

    @FXML
    public void findQuartierByVille(Event event) {
        Ville v = ville.getSelectionModel().getSelectedItem();
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findQuartierByVille(v)));
    }
    
    @FXML
    public void findAnnonceByCompte(Event event){
       Compte c = compte.getSelectionModel().getSelectedItem();
       annonce.setItems(FXCollections.observableArrayList(annonceFacade.findAnnonceByCompte(c)));
    }
    
    public void annuler(ActionEvent actionEvent) throws IOException{
        ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();
        // TODO
    }    
    
}
