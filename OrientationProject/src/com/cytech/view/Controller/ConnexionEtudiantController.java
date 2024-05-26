package com.cytech.view.Controller;

import java.io.IOException;
import com.cytech.collections.EtudiantCollection;
import com.cytech.model.BoiteDeDialogue;
import com.cytech.model.ChangeScene;
import com.cytech.model.EtudiantSession;
import com.cytech.individu.Etudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ConnexionEtudiantController {

    private ChangeScene sceneChanger = new ChangeScene();
    private BoiteDeDialogue dialogue = new BoiteDeDialogue();

    @FXML
    private TextField textNum;

    @FXML
    private PasswordField textmdp;

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
            passwordVisibleField.setText(textmdp.getText());
            passwordVisibleField.setVisible(true);
            passwordVisibleField.setManaged(true);
            textmdp.setVisible(false);
            textmdp.setManaged(false);
        } else {
        	textmdp.setText(passwordVisibleField.getText());
        	textmdp.setVisible(true);
        	textmdp.setManaged(true);
            passwordVisibleField.setVisible(false);
            passwordVisibleField.setManaged(false);
        }
    }

    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/Accueil.fxml", event);
    }

    @FXML
    private void handleMdpOublie(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/MdpOublieEtudiant.fxml", event);
    }

    @FXML
    private void handleSeConnecter(ActionEvent event) throws IOException {
        EtudiantCollection etudiantCollection = new EtudiantCollection();

        int codeEtu;
        try {
            codeEtu = Integer.parseInt(textNum.getText());
        } catch (NumberFormatException e) {
            dialogue.Error("Le numéro étudiant est composé de chiffres uniquement !");
            textNum.clear();
            textmdp.clear();
            passwordVisibleField.clear();
            return;
        }
        String mdp = isPasswordVisible ? passwordVisibleField.getText() : textmdp.getText();

        etudiantCollection.lireJson();

        for (Etudiant etudiant : etudiantCollection.getCollection()) {
            if (etudiant.getNumEtu() == codeEtu && etudiant.getMdpEtu().equals(mdp)) {
                EtudiantSession.getInstance().setEtudiantConnecte(etudiant);
                dialogue.Welcome("Bienvenue " + etudiant.getPrenom() + " " + etudiant.getNom());
                sceneChanger.changeScene("/com/cytech/view/FXML/AccueilEtudiant.fxml", event);
                return;
            }
        }

        dialogue.Error("Numéro étudiant ou mot de passe incorrect !");
        textNum.clear();
        textmdp.clear();
        passwordVisibleField.clear();
    }
}
