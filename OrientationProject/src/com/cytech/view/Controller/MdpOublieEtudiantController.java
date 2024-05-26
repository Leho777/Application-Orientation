package com.cytech.view.Controller;

import java.io.IOException;
import java.util.List;

import com.cytech.collections.EtudiantCollection;
import com.cytech.individu.Etudiant;
import com.cytech.model.BoiteDeDialogue;
import com.cytech.model.ChangeScene;
import com.cytech.model.EnvoieEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;

public class MdpOublieEtudiantController {

    private ChangeScene sceneChanger = new ChangeScene();
    private BoiteDeDialogue boiteDeDialogue = new BoiteDeDialogue();

    @FXML
    private TextField emailTextField;

    @FXML
    private void handleAnnuler(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/ConnexionEtudiant.fxml", event);
    }

    @FXML
    private void handleValider(ActionEvent event) {
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            boiteDeDialogue.Error("Veuillez entrer une adresse email.");
            return;
        }

        EtudiantCollection etudiantCollection = new EtudiantCollection();
        etudiantCollection.lireJson();
        List<Etudiant> etudiants = etudiantCollection.getCollection();

        Etudiant etudiant = etudiants.stream()
            .filter(e -> email.equals(e.getMail()))
            .findFirst()
            .orElse(null);

        if (etudiant == null) {
            boiteDeDialogue.Error("Adresse email non trouvée.");
            return;
        }

        String motDePasse = etudiant.getMdpEtu();
        String sujet = "Récupération de mot de passe";
        String message = "Votre mot de passe est : " + motDePasse;

        try {
            EnvoieEmail.sendEmail(email, sujet, message);
            boiteDeDialogue.Information("Email envoyé avec succès.");
        } catch (MessagingException e) {
            boiteDeDialogue.Error("Échec de l'envoi de l'email.");
            e.printStackTrace();
        }
    }
}
