package com.cytech.view.Controller;

import java.io.IOException;

import com.cytech.model.ChangeScene;
import com.cytech.model.EtudiantSession;
import com.cytech.individu.Etudiant;
import com.cytech.individu.Voeux;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultatEtudiantController {

    private ChangeScene sceneChanger = new ChangeScene(); // Instance de ChangeScene pour changer de scène

    @FXML
    private Label labelResultat; // Label pour afficher le résultat

    // Méthode appelée automatiquement après le chargement du fichier FXML
    @FXML
    public void initialize() {
        Etudiant etudiant = EtudiantSession.getInstance().getEtudiantConnecte();
        if (etudiant != null) {
            Voeux resultatVoeux = etudiant.getResultat();
            if (resultatVoeux != null) {
                labelResultat.setText(resultatVoeux.getNom());
            } else {
                labelResultat.setText("Vous n'avez pas encore été affecté à un vœu.");
            }
        } else {
            labelResultat.setText("Aucun étudiant connecté.");
        }
    }

    // Méthode pour gérer l'action du bouton retour
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilEtudiant.fxml", event);
    }
}
