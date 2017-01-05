/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.TextFXPutUtil;
import bean.Compte;
import bean.Quartier;
import bean.Ville;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.CompteFacade;
import service.QuartierFacade;
import service.VilleFacade;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifyCompteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<Quartier> quartier = new ComboBox<>();
    @FXML
    private ComboBox<Ville> ville = new ComboBox<>();
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private Label erreur;
    @FXML
    private ComboBox<Compte> compte = new ComboBox<>();

    VilleFacade villeFacade = new VilleFacade();
    QuartierFacade quartierFacade = new QuartierFacade();
    CompteFacade compteFacade = new CompteFacade();
    TextFXPutUtil textFXPutUtil = new TextFXPutUtil();

    public void modifyCompte(ActionEvent actionEvent) throws IOException{
        if(compte.getValue()==null){
            erreur.setText("(Erreur 'Compte') un des champs n'a pas bien remplit !");
        }
        else if(textFXPutUtil.onlyLetters(prenom.getText())==false){
            erreur.setText("(Erreur 'Prenom') un des champs n'a pas bien remplit !");
        }
        else if(ville.getValue()==null){
            erreur.setText("(Erreur 'Ville') un des champs n'a pas bien remplit !");
        }
        else if(textFXPutUtil.isTel(tel.getText())==false){
            erreur.setText("(Erreur 'Tel') un des champs n'a pas bien remplit !");
        }
        else if(textFXPutUtil.isEmailAdresse(email.getText())==false){
            erreur.setText("(Erreur 'Email') un des champs n'a pas bien remplit !");
        }
        
        else{
            compteFacade.modifyCompte(compte.getValue(), nom.getText(), prenom.getText(),Integer.parseInt(tel.getText()), email.getText(), ville.getValue(), quartier.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("le compte a ete modifie ! ");
            alert.showAndWait();
            ViewLauncher.forward(actionEvent, "ModifyCompte.fxml", this.getClass());
        }
    }
    
    public void annuler(ActionEvent actionEvent) throws IOException{
        ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());
    }

    public void remplirCompte(Event event) {
        nom.setText(compte.getValue().getNom());
        prenom.setText(compte.getValue().getPrenom());
        tel.setText("0" + compte.getValue().getTel());
        email.setText(compte.getValue().getEmail());
        ville.setValue(compte.getValue().getVille());
        quartier.setValue(compte.getValue().getQuartier());
    }

    public void initComboBox() {
        ville.setItems(FXCollections.observableArrayList(villeFacade.findAll()));
        ville.getSelectionModel().select(0);
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findAll()));
        compte.setItems(FXCollections.observableArrayList(compteFacade.findAll()));
    }

    @FXML
    public void findQuartierByVille(Event event) {
        Ville v = ville.getSelectionModel().getSelectedItem();
        quartier.setItems(FXCollections.observableArrayList(quartierFacade.findQuartierByVille(v)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        // TODO
    }

}
