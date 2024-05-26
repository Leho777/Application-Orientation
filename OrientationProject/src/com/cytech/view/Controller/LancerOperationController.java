package com.cytech.view.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cytech.collections.EtudiantCollection;
import com.cytech.collections.VoeuxCollection;
import com.cytech.individu.Etudiant;
import com.cytech.model.ChangeScene;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LancerOperationController {

    private ChangeScene sceneChanger = new ChangeScene(); // Instance de ChangeScene pour changer de scène

    @FXML
    private Label labelResultat;

    @FXML
    private ListView<String> listViewResultats; // Liste déroulante pour afficher les résultats

    // Méthode pour gérer l'action de lancer l'opération
    @FXML
    private void handleLancerOperation(ActionEvent event) {
        try {
            // Exécuter l'algorithme d'orientation et obtenir les résultats
        	long startTime = System.nanoTime();
        	
            List<String> resultats = lancerOperation();
            
            long endTime = System.nanoTime();
            
            double tempsExe= (endTime - startTime) / 1_000_000_000.0; // Division pour convertir en seconde 
            
         // Arrondir le résultat à deux décimales
            BigDecimal tempsExe2 = new BigDecimal(tempsExe).setScale(3, RoundingMode.HALF_UP);
            System.out.println("Temps d'exécution de l'opération d'orientation : " + tempsExe  + " secondes.");
            
            ObservableList<String> items = FXCollections.observableArrayList(resultats);
            listViewResultats.setItems(items);
            labelResultat.setText("Opération d'orientation terminée avec succès en " + tempsExe2 +"secondes !");
        } catch (Exception e) {
            e.printStackTrace();
            labelResultat.setText("Erreur lors de l'exécution de l'opération d'orientation.");
        }
    }

    // Méthode pour gérer l'action du bouton retour
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilRespo.fxml", event);
    }

    // Algorithme pour lancer l'opération et obtenir les résultats
    public List<String> lancerOperation() {
        EtudiantCollection etuCollection = new EtudiantCollection();
        etuCollection.lireJson();
        VoeuxCollection voeuxCollection = new VoeuxCollection();
        voeuxCollection.lireJson();

        // Trier etuCollection.collection par moyenne
        etuCollection.triParMoy();

        // Créer une liste de compteurs pour les places restantes dans les options
        List<Integer> compteurPlace = new ArrayList<>();
        for (int i = 0; i < voeuxCollection.getCollection().size(); i++) {
            compteurPlace.add(voeuxCollection.getCollection().get(i).getNbPlace());
        }

        // Liste pour stocker les résultats des étudiants
        List<String> resultats = new ArrayList<>();

        // Pour tous les étudiants
        for (int i = 0; i < etuCollection.getCollection().size(); i++) {
            Etudiant etudiant = etuCollection.getCollection().get(i);
            boolean placeTrouvee = false;

            // Parcourir la liste des voeux de chaque étudiant
            for (int j = 0; j < etudiant.getListeVoeux().size(); j++) {

                // Trouver la position du voeu dans la liste des voeux
                int k = 0;
                while (!(voeuxCollection.getCollection().get(k).equals(etudiant.getListeVoeux().get(j)))) {
                    k++;
                }

                // Vérifier s'il reste des places
                if (compteurPlace.get(k) > 0) {
                    etudiant.setResultat(etudiant.getListeVoeux().get(j));
                   compteurPlace.set(k, (int) (compteurPlace.get(k) - 1.));

                    // Ajouter le résultat à la liste des résultats
                    resultats.add(etudiant.getPrenom() + " " + etudiant.getNom() + " - " + etudiant.getFiliere() + " - " + etudiant.getResultat().getNom());

                    // Écrire dans le fichier JSON
                    etuCollection.ajouterEtudiant(etudiant);

                    // Sortir de la boucle car le voeu de l'étudiant a été accepté
                    placeTrouvee = true;
                    break;
                }
            }

            // Si aucune place trouvée pour les voeux de l'étudiant
            if (!placeTrouvee) {
                resultats.add(etudiant.getPrenom() + " " + etudiant.getNom() + " - " + etudiant.getFiliere() + " - Aucune place disponible pour les voeux");
            }
        }
        return resultats;
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////VERSION AVEC ENVOIE D'EMAIL///////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//package com.cytech.view.Controller;
/*
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cytech.collections.EtudiantCollection;
import com.cytech.collections.VoeuxCollection;
import com.cytech.individu.Etudiant;
import com.cytech.individu.Voeux;
import com.cytech.model.ChangeScene;
import com.cytech.model.EnvoieEmail;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javax.mail.MessagingException;

public class LancerOperationController {

    private ChangeScene sceneChanger = new ChangeScene(); // Instance de ChangeScene pour changer de scène

    @FXML
    private Label labelResultat;

    @FXML
    private ListView<String> listViewResultats; // Liste déroulante pour afficher les résultats

    // Méthode pour gérer l'action de lancer l'opération
    @FXML
    private void handleLancerOperation(ActionEvent event) {
        try {
            // Exécuter l'algorithme d'orientation et obtenir les résultats
            List<String> resultats = lancerOperation();
            ObservableList<String> items = FXCollections.observableArrayList(resultats);
            listViewResultats.setItems(items);
            labelResultat.setText("Opération d'orientation terminée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            labelResultat.setText("Erreur lors de l'exécution de l'opération d'orientation.");
        }
    }

    // Méthode pour gérer l'action du bouton retour
    @FXML
    private void handleRetour(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/AccueilRespo.fxml", event);
    }

    // Algorithme pour lancer l'opération et obtenir les résultats
    public List<String> lancerOperation() {
        EtudiantCollection etuCollection = new EtudiantCollection();
        etuCollection.lireJson();
        VoeuxCollection voeuxCollection = new VoeuxCollection();
        voeuxCollection.lireJson();

        // Trier etuCollection.collection par moyenne
        etuCollection.triParMoy();

        // Créer une liste de compteurs pour les places restantes dans les options
        List<Double> compteurPlace = new ArrayList<>();
        for (int i = 0; i < voeuxCollection.getCollection().size(); i++) {
            compteurPlace.add(voeuxCollection.getCollection().get(i).getNbPlace());
        }

        // Liste pour stocker les résultats des étudiants
        List<String> resultats = new ArrayList<>();

        // Pour tous les étudiants
        for (int i = 0; i < etuCollection.getCollection().size(); i++) {
            Etudiant etudiant = etuCollection.getCollection().get(i);
            boolean placeTrouvee = false;

            // Parcourir la liste des voeux de chaque étudiant
            for (int j = 0; j < etudiant.getListeVoeux().size(); j++) {

                // Trouver la position du voeu dans la liste des voeux
                int k = 0;
                while (!(voeuxCollection.getCollection().get(k).equals(etudiant.getListeVoeux().get(j)))) {
                    k++;
                }

                // Vérifier s'il reste des places
                if (compteurPlace.get(k) > 0) {
                    etudiant.setResultat(etudiant.getListeVoeux().get(j));
                    compteurPlace.set(k, compteurPlace.get(k) - 1.);

                    // Ajouter le résultat à la liste des résultats
                    resultats.add(etudiant.getPrenom() + " " + etudiant.getNom() + " - " + etudiant.getFiliere() + " - " + etudiant.getResultat().getNom());

                    // Écrire dans le fichier JSON
                    etuCollection.ajouterEtudiant(etudiant);

                    // Envoyer un email à l'étudiant
                    String email = etudiant.getMail();
                    String sujet = "Résultat de votre orientation";
                    String message = "Bonjour " + etudiant.getPrenom() + ",\n\nVous avez été sélectionné pour le voeu suivant : " + etudiant.getResultat().getNom() + ".\n\nCordialement,\nL'équipe d'orientation";

                    try {
                        EnvoieEmail.sendEmail(email, sujet, message);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                        resultats.add("Erreur lors de l'envoi de l'email à " + etudiant.getPrenom() + " " + etudiant.getNom());
                    }

                    // Sortir de la boucle car le voeu de l'étudiant a été accepté
                    placeTrouvee = true;
                    break;
                }
            }

            // Si aucune place trouvée pour les voeux de l'étudiant
            if (!placeTrouvee) {
                resultats.add(etudiant.getPrenom() + " " + etudiant.getNom() + " - " + etudiant.getFiliere() + " - Aucune place disponible pour les voeux");

                // Envoyer un email à l'étudiant pour l'informer qu'aucune place n'a été trouvée
                String email = etudiant.getMail();
                String sujet = "Résultat de votre orientation";
                String message = "Bonjour " + etudiant.getPrenom() + ",\n\nMalheureusement, aucune place n'était disponible pour vos voeux.\n\nCordialement,\nL'équipe d'orientation";

                try {
                    EnvoieEmail.sendEmail(email, sujet, message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                    resultats.add("Erreur lors de l'envoi de l'email à " + etudiant.getPrenom() + " " + etudiant.getNom());
                }
            }
        }

        return resultats;
    }
}
*/
