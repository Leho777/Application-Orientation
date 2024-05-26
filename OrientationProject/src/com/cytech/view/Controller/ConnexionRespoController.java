package com.cytech.view.Controller;

import java.io.IOException;
import com.cytech.collections.ResponsableCollection;
import com.cytech.individu.Responsable;
import com.cytech.model.BoiteDeDialogue;
import com.cytech.model.ChangeScene;
import com.cytech.model.RespoSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ConnexionRespoController {

    private ChangeScene sceneChanger = new ChangeScene(); // Créez une instance de ChangeScene
    private BoiteDeDialogue dialogue = new BoiteDeDialogue();

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordVisibleField;

    @FXML
    private ImageView togglePasswordView;

    private boolean isPasswordVisible = false;

    @FXML
    private void initialize() {
        passwordVisibleField.setManaged(false);
        passwordVisibleField.setVisible(false);
    }

    @FXML
    private void handleTogglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            passwordVisibleField.setText(passwordField.getText());
            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            passwordField.setVisible(false);
            passwordField.setManaged(false);
        } else {
            passwordField.setText(passwordVisibleField.getText());
            passwordField.setVisible(true);
            passwordField.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
        }
    }

    @FXML
    private void handleSeConnecter(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = isPasswordVisible ? passwordVisibleField.getText() : passwordField.getText();
        int var = 0;

        // Créer une collection de responsables
        ResponsableCollection responsableCollection = new ResponsableCollection();
        responsableCollection.lireJson();

        for (Responsable respo : responsableCollection.getCollection()) {
            if (respo.getMail().equals(email) && respo.getMdpRespo().equals(password)) {
                var = 1;
                RespoSession.getInstance().setRespoConnecte(respo);
                dialogue.Welcome("Bienvenue "+respo.getPrenom()+" "+ respo.getNom());
            }
        }
        if (var == 1) {
            sceneChanger.changeScene("/com/cytech/view/FXML/AccueilRespo.fxml", event);
        } else {
            dialogue.Error("La connexion a échoué : Email ou mot de passe incorrect !");
        }
    }

    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/Accueil.fxml", event);
    }

    @FXML
    private void handleMdpOublie(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/MdpOublieRespo.fxml", event);
    }
}
