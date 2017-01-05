/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Annonce;
import bean.AnnonceItem;
import bean.Quartier;
import bean.Ville;
import helper.AnnonceFxHelper;
import helper.AnnonceItemFxHelper;
import helper.CompteFxHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.AnnonceFacade;
import service.AnnonceItemFacade;
import service.CompteFacade;
import service.QuartierFacade;
import service.VilleFacade;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class MainController implements Initializable {

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
    private TableView compte = new TableView();

    private AnnonceFacade annonceFacade = new AnnonceFacade();
    private AnnonceItem annonceItem = new AnnonceItem();
    private AnnonceItemFacade annonceItemFacade = new AnnonceItemFacade();
    private AnnonceFxHelper annonceFxHelper;
    private AnnonceItemFxHelper annonceItemFxHelper;
    private CompteFxHelper compteFxHelper;
    private VilleFacade villeFacade = new VilleFacade();
    private QuartierFacade quartierFacade = new QuartierFacade();
    private CompteFacade compteFacade = new CompteFacade();

    @FXML
    public void recherche(ActionEvent actionEvent) {
        annonceFxHelper.setList(annonceFacade.FindAnnonce(titre.getText(), ville.getValue(), quartier.getValue()));
    }

    @FXML
    public void findanItemandCompteByAnnonce(Event event) {
        Annonce theAnnonce = annonceFxHelper.getSelected();
        annonceItemFxHelper.setList(annonceItemFacade.findAnnonceItemByAnnonce(theAnnonce));
        compteFxHelper.setList(annonceFacade.findCompteByAnnonce(theAnnonce));
    }

    public void initComboBox() {
        ville.setItems(FXCollections.observableArrayList(villeFacade.findAll()));
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findAll()));
    }

    public void initHelper() {
        annonceItemFxHelper = new AnnonceItemFxHelper(annonceIm);
        annonceFxHelper = new AnnonceFxHelper(annonce);
        compteFxHelper = new CompteFxHelper(compte);
    }

    @FXML
    public void findQuartierByVille(Event event) {
        Ville v = ville.getSelectionModel().getSelectedItem();
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findQuartierByVille(v)));
    }

    public void creerCompte(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Compte.fxml", this.getClass());
    }

    public void modifyCompte(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "ModifyCompte.fxml", this.getClass());
    }

    public void deleteCompte(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "DeleteCompte.fxml", this.getClass());
    }

    public void creerAnnonce(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Annonce.fxml", this.getClass());
    }

    public void modifyAnnonce(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "ModifyAnnonce.fxml", this.getClass());
    }

    public void deleteAnnonce(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "DeleteAnnonce.fxml", this.getClass());
    }
    public void rechercheAvance(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "RechercheAvance.fxml", this.getClass());
    }
    public void changemdp(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "changePass.fxml", this.getClass());
    }

    public void seDeconnecter(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez se deconnecter ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ViewLauncher.forward(actionEvent, "User.fxml", this.getClass());
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        initHelper();
        // TODO
    }

}
