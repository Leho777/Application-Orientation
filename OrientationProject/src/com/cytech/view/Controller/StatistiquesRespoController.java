package com.cytech.view.Controller;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import com.cytech.collections.EtudiantCollection;
import com.cytech.collections.VoeuxCollection;
import com.cytech.individu.Etudiant;
import com.cytech.individu.Voeux;
import com.cytech.model.ChangeScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class StatistiquesRespoController {

    private ChangeScene sceneChanger = new ChangeScene(); // Instance de ChangeScene pour changer de scène

    @FXML
    private Label labelNombreTotalEtudiants; // Label pour afficher le nombre total d'étudiants

    @FXML
    private Label labelNombreEtudiantsFiliere; // Label pour afficher le nombre d'étudiants par filière

    @FXML
    private BarChart<String, Number> barChartVoeux; // BarChart pour afficher les vœux les plus sollicités

    @FXML
    private Label labelPourcentageVoeux; // Label pour afficher le pourcentage de chaque vœu

    // Méthode appelée automatiquement après le chargement du fichier FXML
    @FXML
    public void initialize() {
        // Charger les statistiques et les afficher
        chargerStatistiques();
    }

    // Méthode pour gérer l'action du bouton retour
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilRespo.fxml", event);
    }

    // Méthode pour charger les statistiques et les afficher
    private void chargerStatistiques() {
        // Charger les collections
        EtudiantCollection etudiantCollection = new EtudiantCollection();
        etudiantCollection.lireJson();
        VoeuxCollection voeuxCollection = new VoeuxCollection();
        voeuxCollection.lireJson();

        // Nombre total d'étudiants
        int nombreTotalEtudiants = etudiantCollection.getCollection().size();
        labelNombreTotalEtudiants.setText("Nombre total d'étudiants : " + nombreTotalEtudiants);

        // Nombre d'étudiants par filière
        Map<String, Long> etudiantsParFiliere = etudiantCollection.getCollection().stream()
                .collect(Collectors.groupingBy(Etudiant::getFiliere, Collectors.counting()));
        String repartitionFiliere = etudiantsParFiliere.entrySet().stream()
                .map(entry -> entry.getKey() + " : " + entry.getValue())
                .collect(Collectors.joining(", "));
        labelNombreEtudiantsFiliere.setText("Répartition des étudiants par filière : " + repartitionFiliere);

        // Vœux les plus sollicités
        Map<String, Long> voeuxSollicites = etudiantCollection.getCollection().stream()
                .flatMap(etudiant -> etudiant.getListeVoeux().stream())
                .collect(Collectors.groupingBy(Voeux::getNom, Collectors.counting()));
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        voeuxSollicites.forEach((voeu, count) -> 
                series.getData().add(new XYChart.Data<>(voeu, count)));
        barChartVoeux.getData().add(series);

        // Pourcentage de chaque vœu accepté
        Map<Integer, Long> voeuxAcceptes = etudiantCollection.getCollection().stream()
                .filter(etudiant -> etudiant.getResultat() != null)
                .collect(Collectors.groupingBy(etudiant -> etudiant.getListeVoeux().indexOf(etudiant.getResultat()) + 1, Collectors.counting()));
        String pourcentageVoeux = voeuxAcceptes.entrySet().stream()
                .map(entry -> "Vœu " + entry.getKey() + " : " + String.format("%.2f", (double) entry.getValue() / nombreTotalEtudiants * 100) + "%")
                .collect(Collectors.joining(", "));
        labelPourcentageVoeux.setText("Pourcentage de chaque vœu accepté : " + pourcentageVoeux);
    }
}
