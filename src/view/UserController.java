/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.UserService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class UserController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label msg;

    private UserService userService = new UserService();

    private User getParam() {
        return new User(login.getText(), password.getText());
    }

    public void seConnecte(ActionEvent actionEvent) throws IOException {
        User user = getParam();
        Object[] res = userService.seConnecte(user);
        int res1 = (int) res[0];
        if (res1 == 1) {
            System.out.println("Bienvenu");
            Session.setAttribut(res[1], "connectedUser");
            ViewLauncher.forward(actionEvent, "Main.fxml", this.getClass());

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Votre nom d'utilisateur ou mot de passe est incorrect!");

            alert.showAndWait();
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
