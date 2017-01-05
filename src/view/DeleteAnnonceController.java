/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Annonce;
import bean.Compte;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import service.AnnonceFacade;
import service.CompteFacade;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DeleteAnnonceController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox<Compte> compte= new ComboBox<>();
    @FXML
    private ComboBox<Annonce> annonce = new ComboBox<>();
    
    CompteFacade compteFacade = new CompteFacade();
    AnnonceFacade annonceFacade = new AnnonceFacade();
    
    public void DeleteAnnonce(){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez supprimer cette annonce ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            annonceFacade.deleteAnnonce(annonce.getValue());
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Information");
            al.setHeaderText(null);
            al.setContentText("le compte a ete supprime ! ");
            al.showAndWait();
            compte.setValue(null);
            annonce.setValue(null);
        } 
        
    }
    
    @FXML
    public void findAnnonceByCompte(Event event){
       Compte c = compte.getSelectionModel().getSelectedItem();
       annonce.setItems(FXCollections.observableArrayList(annonceFacade.findAnnonceByCompte(c)));
    }
    
    public void initComboBox() {
        compte.setItems(FXCollections.observableArrayList(compteFacade.findAll()));
        annonce.setItems(FXCollections.observableArrayList(annonceFacade.findAll()));
    }
    
    public void annuler(ActionEvent actionEvent) throws IOException{
        ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboBox();
        // TODO
    }       
    
}
