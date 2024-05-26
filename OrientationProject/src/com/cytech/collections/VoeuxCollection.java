package com.cytech.collections;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cytech.individu.Voeux;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VoeuxCollection {
	private List<Voeux> collection;
    private static final String FILENAME = "BDD/voeux.json"; // 
    private static final Gson gson = new Gson();
    
 // Constructeur pour initialiser le stock de boissons
    public VoeuxCollection() {
      this.collection = new ArrayList<>();
    }

    public List<Voeux> getCollection() {
      return collection;
    }

    public void setCollection(List<Voeux> collection) {
      this.collection = collection;
    }
    
  //Cette fonction lit le fichier json et met tous les responsables dans collection (c'est un deuxième setter)
    public void lireJson() {
        try (FileReader reader = new FileReader(FILENAME)) {
            // Utilisation de TypeToken pour récupérer la liste des étudiants
            List<Voeux> responsables = gson.fromJson(reader, new TypeToken<List<Voeux>>(){}.getType());
            setCollection(responsables);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
 // Méthode pour ajouter un responsable au fichier JSON 
    // ATTENTION (pour utiliser cette fonction il faut que la collection soit constituée de ce qu'il y a initialement dans le json)
    // Je ne sais pas si cette fonction sera utile
       public void ajouterVoeux(Voeux voeux) {
       	
       	// Rechercher et remplacer l'étudiant existant ou ajouter le nouveau
           boolean responsableExiste = false;
           for (int i = 0; i < this.collection.size(); i++) {
               if (this.collection.get(i).getIdVoeux() == voeux.getIdVoeux()) {
                   this.collection.set(i, voeux);
                   responsableExiste = true;
                   break;
               }
           }
           
           if (!responsableExiste) {
               this.collection.add(voeux);
           }

           // Écrire la liste mise à jour dans le fichier JSON
           try (FileWriter writer = new FileWriter(FILENAME)) {
               gson.toJson(this.collection, writer);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       
    // Méthode pour mettre à jour un voeu existant dans la collection et le fichier JSON
       public void mettreAJour(Voeux voeux) {
    	   for (int i = 0; i < this.collection.size(); i++) {
    	        Voeux voeuExistant = this.collection.get(i);
    	        if (voeuExistant.getNom().equals(voeux.getNom())||voeuExistant.getIdVoeux()==(voeux.getIdVoeux())) {
    	            // Mettre à jour les champs de l'objet existant avec les nouvelles valeurs
    	        	voeuExistant.setNom(voeux.getNom());
    	        	voeuExistant.setFiliereEligible(voeux.getFiliereEligible());
    	        	voeuExistant.setNbPlace(voeux.getNbPlace());
    	        	voeuExistant.setDescription(voeux.getDescription());
    	        	voeuExistant.setIdVoeux(voeux.getIdVoeux());
    	            // Sauvegarder la collection mise à jour dans le fichier JSON
    	            sauvegarderJson();
    	            break;
    	        }
    	    }
       }
       
    // Méthode pour sauvegarder la collection actuelle des voeux dans le fichier JSON
       public void sauvegarderJson() {
           try (FileWriter writer = new FileWriter(FILENAME)) {
               gson.toJson(this.collection, writer);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
}
