package com.cytech.view.Controller;

import java.io.IOException;
import com.cytech.model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AccueilController {

    // Instance de ChangeScene pour gérer le changement de scènes
    private ChangeScene sceneChanger = new ChangeScene();

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton pour les étudiants.
     * Change la scène pour afficher la page de connexion des étudiants.
     * 
     * @param event l'événement déclenché par le clic sur le bouton
     * @throws IOException si le fichier FXML ne peut pas être chargé
     */
    @FXML
    private void handleEtudiant(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/ConnexionEtudiant.fxml", event);
    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton pour les responsables.
     * Change la scène pour afficher la page de connexion des responsables.
     * 
     * @param event l'événement déclenché par le clic sur le bouton
     * @throws IOException si le fichier FXML ne peut pas être chargé
     */
    @FXML
    private void handleRespo(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/ConnexionRespo.fxml", event);
    }
}
