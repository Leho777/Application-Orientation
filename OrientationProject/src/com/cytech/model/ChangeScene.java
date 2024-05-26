package com.cytech.model;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeScene {

    /**
     * Change la scène affichée dans la fenêtre actuelle.
     *
     * @param fxmlFile le chemin du fichier FXML à charger
     * @param event l'événement déclenché par l'utilisateur (généralement un clic)
     * @throws IOException si le fichier FXML ne peut pas être chargé
     */
    public void changeScene(String fxmlFile, ActionEvent event) throws IOException {
        // Charger le fichier FXML spécifié
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
        
        // Créer une nouvelle scène avec le contenu chargé
        Scene scene = new Scene(parent);
        
        // Obtenir la fenêtre (Stage) à partir de l'événement
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Définir la nouvelle scène dans la fenêtre
        window.setScene(scene);
        
        // Afficher la nouvelle scène
        window.show();
    }
}
