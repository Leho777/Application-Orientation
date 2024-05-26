package com.cytech.view.Controller;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

import com.cytech.collections.VoeuxCollection;
import com.cytech.individu.Etudiant;
import com.cytech.individu.Voeux;
import com.cytech.model.ChangeScene;
import com.cytech.model.EtudiantSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VoeuxDispoEtudiantController {

    // Instance de ChangeScene pour changer de scène
    private ChangeScene sceneChanger = new ChangeScene();

    @FXML
    private Accordion accordionVoeux; // Accordion pour afficher les voeux disponibles

    // Méthode pour initialiser le contrôleur après que son contenu a été complètement chargé
    @FXML
    private void initialize() {
        // Créer une collection de voeux
        VoeuxCollection voeuxCollection = new VoeuxCollection();
        
        // Lire les voeux depuis le fichier JSON
        voeuxCollection.lireJson();
        
        // Obtenir l'étudiant connecté
        Etudiant etudiantConnecte = EtudiantSession.getInstance().getEtudiantConnecte();
        
        // Filtrer les voeux en fonction de la filière de l'étudiant connecté
        List<Voeux> voeuxFiltres = voeuxCollection.getCollection().stream()
            .filter(voeux -> voeux.getFiliereEligible().contains(etudiantConnecte.getFiliere()))
            .collect(Collectors.toList());
        
        // Ajouter des TitledPane à l'Accordion pour chaque voeu filtré
        for (Voeux voeu : voeuxFiltres) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(voeu.getNom());
            
            VBox content = new VBox();
            content.setSpacing(10);
            
            BigDecimal NbPlace = new BigDecimal(voeu.getNbPlace()).setScale(0, RoundingMode.HALF_UP);
            
            Label nbPlaces = new Label("Nombre de places : " + NbPlace);
            Label description = new Label("Description : " + voeu.getDescription());
            content.getChildren().addAll(nbPlaces, description);
            
            titledPane.setContent(content);
            accordionVoeux.getPanes().add(titledPane);
        }
    }

    // Méthode pour gérer l'action de retour à l'écran d'accueil de l'étudiant
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilEtudiant.fxml", event);
    }
}
