    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.TextFXPutUtil;
import bean.Annonce;
import bean.AnnonceItem;
import static bean.Annonce_.datecre;
import bean.Compte;
import bean.Metier;
import bean.Quartier;
import bean.Ville;
import helper.AnnonceFxHelper;
import helper.AnnonceItemFxHelper;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class AnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField titre;
    @FXML
    private ComboBox<Compte> compte = new ComboBox<>();
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
    private TableView annonceItem = new TableView();
    @FXML
    private Label erreur;

    AnnonceFacade annonceFacade = new AnnonceFacade();
    private AnnonceItemFxHelper annonceItemFxHelper;
    private AnnonceFxHelper annonceFxHelper;
    private VilleFacade villeFacade = new VilleFacade();
    private QuartierFacade quartierFacade = new QuartierFacade();
    private MetierFacade metierFacade = new MetierFacade();
    private CompteFacade compteFacade = new CompteFacade();
    private AnnonceItemFacade annonceItemFacade = new AnnonceItemFacade();

    private AnnonceItem getParamAnnonceItem() {
        AnnonceItem annonceIte;
        annonceIte = new AnnonceItem(metier.getSelectionModel().getSelectedItem(), Integer.parseInt(nombre.getText()));
        return annonceIte;
    }

    static LocalDate datecre;

    public static LocalDate convertToLocal(Date date) {
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        return localDate;
    }

    public static Date convert(LocalDate localDate) {
        java.util.Date date = java.sql.Date.valueOf(localDate);
        return date;
    }

    Date datecrea = convert(datecre.now());
    TextFXPutUtil textFXPutUtil = new TextFXPutUtil();

    @FXML
    public void saveAnnonce(ActionEvent actionEvent) throws IOException {
        if (compte.getValue() == null) {
            erreur.setText("(Erreur 'Compte') un des champs n'a pas bien remplit !");
        } else if (textFXPutUtil.onlyLetters(titre.getText()) == false) {
            erreur.setText("(Erreur 'Titre') un des champs n'a pas bien remplit !");
        }else if (annonceItem.getItems().isEmpty()){
            erreur.setText("(Erreur 'Liste des metiers') un des champs n'a pas bien remplit !");
        }else if (ville.getValue() == null) {
            erreur.setText("(Erreur 'Ville') un des champs n'a pas bien remplit !");
        } else if (textFXPutUtil.estprix(prix.getText()) == false) {
            erreur.setText("(Erreur 'prix') un des champs n'a pas bien remplit !");
        } else {
            
            Annonce annonce = new Annonce(annonceFacade.generateId("Annonce", "id"), titre.getText(),new Double(prix.getText()), ville.getValue(), quartier.getValue(), compte.getSelectionModel().getSelectedItem(), datecrea);
            annonceFacade.save(annonce, annonceItemFxHelper.getTable().getItems());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("l'annonce a ete cree ! ");
            alert.showAndWait();
            ViewLauncher.forward(actionEvent, "Annonce.fxml", this.getClass());
       
        }
        
    }

    public void annuler(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());
    }

    @FXML
    public void saveAnnonceItem(ActionEvent actionEvent) {
        AnnonceItem annonceIte;
        if (metier.getValue() == null) {
            erreur.setText("(Erreur 'Metier') un des champs n'a pas bien remplit !");
        } else if (textFXPutUtil.onlyNumbers(nombre.getText()) == false || nombre.getText()=="" || Integer.parseInt(nombre.getText()) > 10  || Integer.parseInt(nombre.getText()) < 1) {
            erreur.setText("(Erreur 'Nombre') un des champs n'a pas bien remplit !");
        } else{
            annonceIte = new AnnonceItem(metier.getSelectionModel().getSelectedItem(), Integer.parseInt(nombre.getText()));
            annonceItemFxHelper.getTable().getItems().add(annonceIte);
        }
        
    }

    @FXML
    public void deleteAnnonceItem(ActionEvent actionEvent) {
        int index = this.annonceItem.getSelectionModel().getSelectedIndex();
        annonceItemFxHelper.getTable().getItems().remove(index);
    }

    AnnonceItem a = new AnnonceItem();

    @FXML
    public void getAnnonceItem(Event event) {
        AnnonceItem a = (AnnonceItem) annonceItem.getSelectionModel().getSelectedItem();
    }

    public void initComboBox() {
        ville.setItems(FXCollections.observableArrayList(villeFacade.findAll()));
        ville.getSelectionModel().select(0);
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findAll()));
        metier.setItems(FXCollections.observableArrayList(metierFacade.findAll()));
        compte.setItems(FXCollections.observableArrayList(compteFacade.findAll()));
    }

    public void initHelper() {
        annonceItemFxHelper = new AnnonceItemFxHelper(annonceItem);
    }

    @FXML
    public void findQuartierByVille(Event event) {
        Ville v = ville.getSelectionModel().getSelectedItem();
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findQuartierByVille(v)));
    }

    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();
        // TODO
    }

}
