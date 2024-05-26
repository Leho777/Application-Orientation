package com.cytech.view.Controller;

import java.io.IOException;
import com.cytech.model.ChangeScene;
import com.cytech.model.EtudiantSession;
import com.cytech.individu.Etudiant;
import com.cytech.individu.Voeux;
import com.cytech.model.BoiteDeDialogue;
import com.cytech.collections.GestionAccesVoeux;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccueilEtudiantController {

    // Instances de ChangeScene et BoiteDeDialogue pour changer les scènes et afficher les boîtes de dialogue
    private ChangeScene sceneChanger = new ChangeScene(); 
    private BoiteDeDialogue boiteDeDialogue = new BoiteDeDialogue();
    private GestionAccesVoeux gestionAccesVoeux = new GestionAccesVoeux(); // Instance pour gérer l'accès aux voeux

    @FXML
    private Label labelBienvenue;  // Label pour afficher le message de bienvenue

    @FXML
    private Button ficheVoeuxButton;  // Bouton pour accéder à la fiche de voeux

    // Méthode appelée automatiquement après le chargement du fichier FXML
    @FXML
    public void initialize() {
        Etudiant etudiant = EtudiantSession.getInstance().getEtudiantConnecte();
        if (etudiant != null) {
            labelBienvenue.setText("Bienvenue, " + etudiant.getPrenom() + " " + etudiant.getNom());
        } else {
            labelBienvenue.setText("Bienvenue");
        }
        ficheVoeuxButton.setDisable(!gestionAccesVoeux.getBoolValue()); // Désactiver le bouton si l'accès est désactivé
    }

    // Méthode pour gérer la déconnexion de l'étudiant
    @FXML
    private void handleSeDeconnecter(ActionEvent event) throws IOException {
        EtudiantSession.getInstance().clearSession(); // Nettoyer la session
        sceneChanger.changeScene("/com/cytech/view/FXML/Accueil.fxml", event);
    }

    // Méthode pour accéder aux données de l'étudiant
    @FXML
    private void handleDonnees(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/DonneesEtudiant.fxml", event);
    }

    // Méthode pour accéder aux voeux disponibles
    @FXML
    private void handleVoeuxDispo(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/VoeuxDispoEtudiant.fxml", event);
    }

    // Méthode pour accéder à la fiche de voeux de l'étudiant
    @FXML
    private void handleFicheVoeux(ActionEvent event) throws IOException {
        Etudiant etudiant = EtudiantSession.getInstance().getEtudiantConnecte();
        if (etudiant != null) {
            // Initialiser la boîte de dialogue avec la scène principale
            Stage primaryStage = (Stage) ficheVoeuxButton.getScene().getWindow();
            boiteDeDialogue.setDialogStage(primaryStage);

            if (etudiant.getListeVoeux() != null && !etudiant.getListeVoeux().isEmpty()) {
                // Afficher une alerte si l'étudiant a déjà confirmé ses voeux
                boiteDeDialogue.Information("Vous avez déjà envoyé votre liste de voeux : \n" + getVoeuxListAsString(etudiant));
            } else {
                // Changer la scène vers la fiche de voeux si l'étudiant n'a pas encore confirmé ses voeux
                sceneChanger.changeScene("/com/cytech/view/FXML/FicheVoeuxEtudiant.fxml", event);
            }
        }
    }

    // Méthode pour obtenir la liste des voeux de l'étudiant sous forme de chaîne de caractères
    private String getVoeuxListAsString(Etudiant etudiant) {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Voeux voeux : etudiant.getListeVoeux()) {
            sb.append(index).append(". ").append(voeux.getNom()).append("\n");
            index++;
        }
        return sb.toString();
    }

    // Méthode pour accéder aux résultats des voeux de l'étudiant
    @FXML
    private void handleResultat(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/ResultatEtudiant.fxml", event);
    }
}
