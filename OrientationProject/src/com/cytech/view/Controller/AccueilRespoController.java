package com.cytech.view.Controller;

import java.io.IOException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.cytech.individu.Responsable;
import com.cytech.model.ChangeScene;
import com.cytech.model.RespoSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class AccueilRespoController {

	private ChangeScene sceneChanger = new ChangeScene(); // Créez une instance de ChangeScene
	
	@FXML
	private Label labelBienvenue;  // Label pour afficher le message de bienvenue
	
	@FXML
	public void initialize() {
		Responsable respo = RespoSession.getInstance().getRespoConnecte();
		 if (respo != null) {
	            labelBienvenue.setText("Bienvenue, " + respo.getPrenom() + " " + respo.getNom());
	        } else {
	            labelBienvenue.setText("Bienvenue");
	        }
	}
	
	// Méthode pour gérer la déconnexion du responsable
	@FXML
	private void handleDeconnexion(ActionEvent event) throws IOException {
		RespoSession.getInstance().clearSession();// Nettoyer la session
		sceneChanger.changeScene("/com/cytech/view/FXML/Accueil.fxml", event);
	}
	
	@FXML
	private void handleGererVoeux(ActionEvent event) throws IOException {
		sceneChanger.changeScene("/com/cytech/view/FXML/GererVoeuxRespo.fxml", event);
	}
	@FXML
    private void handleRemplirVoeuxRespo(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/RemplirVoeuxRespo.fxml", event);
    }
	@FXML
    private void handleLancerOperationPage(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/LancerOperation.fxml", event);
    }
	
	@FXML
    private void handleStatistiquesRespo(ActionEvent event) throws IOException {
        sceneChanger.changeScene("/com/cytech/view/FXML/StatistiquesRespo.fxml", event);
    }
	
	@FXML
    private void handleChooseEtudiants(ActionEvent event) {
        File file = chooseFile();
        if (file != null) {
            copyFile(file, "BDD/etudiant.json");
        }
    }

    @FXML
    private void handleChooseVoeux(ActionEvent event) {
        File file = chooseFile();
        if (file != null) {
            copyFile(file, "BDD/voeux.json");
        }
    }

    @FXML
    private void handleChooseResponsables(ActionEvent event) {
        File file = chooseFile();
        if (file != null) {
            copyFile(file, "BDD/responsable.json");
        }
    }

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open JSON File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        Stage stage = (Stage) labelBienvenue.getScene().getWindow();
        return fileChooser.showOpenDialog(stage);
    }

    private void copyFile(File source, String destinationPath) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destinationPath)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
