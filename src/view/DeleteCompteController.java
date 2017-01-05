/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.Compte;
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
import service.AnnonceFacade;
import service.CompteFacade;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DeleteCompteController implements Initializable {

    @FXML
    private ComboBox<Compte> compte = new ComboBox<>();
    

    CompteFacade compteFacade = new CompteFacade();
    CompteFxHelper compteFxHelper;
    AnnonceFacade annonceFacade = new AnnonceFacade();

    public void supCompte(ActionEvent actionEvent) throws IOException {
        if(compte.getValue()==null){
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Il faut choisir un compte !");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation ");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez supprimer ce compte ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                annonceFacade.deleteCompte(compte.getValue());
                Alert al = new Alert(AlertType.INFORMATION);
                al.setTitle("Information");
                al.setHeaderText(null);
                al.setContentText("le compte a ete supprime ! ");
                al.showAndWait();
                ViewLauncher.forward(actionEvent, "DeleteCompte.fxml", this.getClass());
            }
        }
        
    }

    public void acceuil(ActionEvent actionEvent) throws IOException {
        ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());
    }

    public void initComboBox() {
        compte.setItems(FXCollections.observableArrayList(compteFacade.findAll()));
    }

    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        initComboBox();
        // TODO
    }

}
