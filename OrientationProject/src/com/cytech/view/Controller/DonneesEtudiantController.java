package com.cytech.view.Controller;

import java.io.IOException;
import com.cytech.model.ChangeScene;
import com.cytech.model.EtudiantSession;
import com.cytech.individu.Etudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DonneesEtudiantController {

    // Instance de ChangeScene pour changer de scène
    private ChangeScene sceneChanger = new ChangeScene();

    @FXML
    private Label labelNom;    // Label pour afficher le nom de l'étudiant

    @FXML
    private Label labelPrenom; // Label pour afficher le prénom de l'étudiant

    @FXML
    private Label labelEmail;  // Label pour afficher l'email de l'étudiant

    @FXML
    private Label labelNumEtu; // Label pour afficher le numéro étudiant

    @FXML
    private Label labelFiliere;// Label pour afficher la filière de l'étudiant

    @FXML
    private Label labelMoyGen; // Label pour afficher la moyenne générale de l'étudiant

    // Méthode appelée automatiquement après le chargement du fichier FXML
    @FXML
    public void initialize() {
        // Récupérer l'étudiant actuellement connecté
        Etudiant etudiant = EtudiantSession.getInstance().getEtudiantConnecte();
        
        if (etudiant != null) {
            // Mettre à jour les labels avec les informations de l'étudiant
            labelNom.setText(etudiant.getNom());
            labelPrenom.setText(etudiant.getPrenom());
            labelEmail.setText(etudiant.getMail());
            labelNumEtu.setText(String.valueOf(etudiant.getNumEtu()));
            labelFiliere.setText(etudiant.getFiliere());
            
            BigDecimal MoyGen = new BigDecimal(etudiant.getMoyGen()).setScale(2, RoundingMode.HALF_UP);
            
            labelMoyGen.setText(String.valueOf(MoyGen));
        }
    }

    // Méthode pour gérer l'action de retour à l'écran d'accueil de l'étudiant
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilEtudiant.fxml", event);
    }
}
