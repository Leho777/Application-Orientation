package com.cytech.view.Controller;

import java.io.IOException;
import com.cytech.collections.GestionAccesVoeux;
import com.cytech.model.ChangeScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class RemplirVoeuxRespoController {

    private ChangeScene sceneChanger = new ChangeScene(); // Instance de ChangeScene pour changer de scène

    private GestionAccesVoeux gestionAccesVoeux = new GestionAccesVoeux(); // Instance pour gérer l'accès aux voeux

    @FXML
    private CheckBox toggleRemplissageVoeux; // CheckBox pour activer/désactiver le remplissage des fiches de voeux

    // Méthode appelée automatiquement après le chargement du fichier FXML
    @FXML
    private void initialize() {
        // Initialiser la CheckBox avec la valeur actuelle de boolValue
        toggleRemplissageVoeux.setSelected(gestionAccesVoeux.getBoolValue());
       
    }
    
    @FXML
    private void handleCocheDecoche(ActionEvent event) throws IOException {
    	 // Mettre à jour la valeur de l'accès au remplissage des voeux et sauvegarder dans le fichier JSON
        gestionAccesVoeux.setBoolValue(toggleRemplissageVoeux.isSelected());
        gestionAccesVoeux.modifierJson();
    }

    // Méthode pour gérer l'action du bouton retour
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
       
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilRespo.fxml", event);
    }
}
