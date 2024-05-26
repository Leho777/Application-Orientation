package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import com.cytech.collections.*;
import com.cytech.individu.*;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML pour la vue d'accueil
            // Assurez-vous que le chemin est correct et correspond à l'emplacement du fichier FXML
            Parent accueilView = FXMLLoader.load(getClass().getResource("/com/cytech/view/FXML/Accueil.fxml"));
            
            // Définir le titre de la fenêtre principale
            primaryStage.setTitle("Orientation des étudiants");
            
            // Définir la scène avec la vue d'accueil et spécifier la taille de la fenêtre
            primaryStage.setScene(new Scene(accueilView, 800, 600)); // Vous pouvez ajuster la taille si nécessaire
            
            // Afficher la fenêtre principale
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.NullLogger");
		launch(args);
		
		//Test de récupération du booléen et de sa modification
		/*
		GestionAccesVoeux g = new GestionAccesVoeux();
		System.out.println(g.getBoolValue());
		//On attribut à boolValue sa valeur inverse
		g.setBoolValue(!g.getBoolValue());
		g.modifierJson();
		System.out.println(g.getBoolValue());
		*/
		
		//Test pour lancer l'operation
		/*
		lancerOperation();
		
		EtudiantCollection etudiantCollection = new EtudiantCollection();
        
        // Lire les étudiants depuis le fichier JSON
        etudiantCollection.lireJson();
        
        // Afficher la liste triée
        for (Etudiant etudiant : etudiantCollection.getCollection()) {
            System.out.println(etudiant.getPrenom());
            System.out.println(etudiant.getResultat());
        }
        */
		
	}
	
	//Méthode pour lancer l'opération
	public static void lancerOperation() {
		EtudiantCollection etuCollection = new EtudiantCollection();
		etuCollection.lireJson();
		VoeuxCollection voeuxCollection = new VoeuxCollection();
		voeuxCollection.lireJson();
		
		//Trier etuCollection.collection par moyenne
		etuCollection.triParMoy();
		
		//On crée une liste de compteur qui nous indique le nombre de places restantes dans les options
		List<Integer> compteurPlace = new ArrayList<>();
		for(int i = 0; i < voeuxCollection.getCollection().size(); i++) {
			compteurPlace.add(voeuxCollection.getCollection().get(i).getNbPlace());
		}
		
		//Pour tous les étudiants
		for (int i = 0; i < etuCollection.getCollection().size(); i++) {
			Etudiant etudiant = etuCollection.getCollection().get(i);
			
			//Pour chaque étudiant on parcours sa liste de voeux
			for (int j = 0; j < etudiant.getListeVoeux().size(); j++) {
				
				//Il faut trouver la position du voeux dans la liste des voeux pour pouvoir ensuite regarder dans la liste des compteur combien de places reste-il
				int k = 0;
				while(!(voeuxCollection.getCollection().get(k).equals(etudiant.getListeVoeux().get(j)))) {
					k++;
				}
				
				//On regarde si il reste de la place
				if(compteurPlace.get(k) > 0) {
					etudiant.setResultat(etudiant.getListeVoeux().get(j));
					compteurPlace.set(k, (int) (compteurPlace.get(k) - 1.));
					
					//On écrit dans la json
					etuCollection.ajouterEtudiant(etudiant);
					
					//On sort de la boucle for car le j ème voeux de l'étudiant a été accepté
					break;
				}
			}

		}
	}
	
}
