package com.cytech.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BoiteDeDialogue {
	
	// Attribut qui va permettre d'ouvrir une boite de dialogue
		public Stage dialogStage;

		/**
		 * Sets the stage of this dialog.
		 *
		 * @param dialogStage
		 */
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;
		}
		
		public void Confirmation(String message) {
			// Show the welcome message.
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(dialogStage);
			alert.setTitle("SUCCES");
			alert.setHeaderText("Action exécutée avec succès :");
			alert.setContentText(message);
			alert.showAndWait();
		}

		public void Welcome(String message) {
			// Show the welcome message.
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(dialogStage);
			alert.setTitle("CONNECTE");
			alert.setHeaderText("Connexion établie avec succès !");
			alert.setContentText(message);
			alert.showAndWait();
		}

		public void Error(String message) {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERREUR");
			alert.setHeaderText("Une erreur est survenue !");
			alert.setContentText(message);
			alert.showAndWait();
		}

		public void Information(String message) {
			// Show the informative message.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(dialogStage);
			alert.setTitle("INFORMATION");
			alert.setHeaderText("");
			alert.setContentText(message);
			alert.showAndWait();
		}
}
