package com.cytech.view.Controller;

import java.io.IOException;

import com.cytech.model.ChangeScene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MdpOublieRespoController {
	
	private ChangeScene sceneChanger = new ChangeScene(); // Créez une instance de ChangeScene

	@FXML
	private void handleAnnuler(ActionEvent event) throws IOException {
		sceneChanger.changeScene("/com/cytech/view/FXML/ConnexionRespo.fxml", event);
	}
}
