package com.cytech.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import com.cytech.individu.Etudiant;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;


public class EtudiantCollection {
	private List<Etudiant> collection;
    private static final String FILENAME = "BDD/etudiant.json"; // 
    private static final Gson gson = new Gson();
    
 // Constructeur pour initialiser le stock de boissons
    public EtudiantCollection() {
      this.collection = new ArrayList<>();
    }

    public List<Etudiant> getCollection() {
      return collection;
    }

    public void setCollection(List<Etudiant> collection) {
      this.collection = collection;
    }
    
    //Cette fonction lit le fichier json et met tous les etudiants dans collection (c'est un deuxième setter)
    public void lireJson() {
        try (FileReader reader = new FileReader(FILENAME)) {
            // Utilisation de TypeToken pour récupérer la liste des étudiants
            List<Etudiant> etudiants = gson.fromJson(reader, new TypeToken<List<Etudiant>>(){}.getType());
            setCollection(etudiants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 // Méthode pour ajouter un étudiant à la collection et mettre à jour le fichier JSON
    // ATTENTION: Pour utiliser cette fonction, la collection doit d'abord être initialisée avec le contenu du fichier JSON
    // Pour modifier la liste des voeux d'un étudiant, il faut mettre à jour sa listeVoeux puis appeler ajouterEtudiant avec la nouvelle version de l'étudiant
    public void ajouterEtudiant(Etudiant etudiant) {
        // Rechercher et remplacer l'étudiant existant ou ajouter le nouveau
        boolean etudiantExiste = false;
        for (int i = 0; i < this.collection.size(); i++) {
            if (this.collection.get(i).getNumEtu() == etudiant.getNumEtu()) {
                this.collection.set(i, etudiant);
                etudiantExiste = true;
                break;
            }
        }
        
        // Si l'étudiant n'existe pas encore, l'ajouter à la collection
        if (!etudiantExiste) {
            this.collection.add(etudiant);
        }

        // Écrire la collection mise à jour dans le fichier JSON
        sauvegarderJson();
    }
    
    // Méthode pour mettre à jour un étudiant existant dans la collection et le fichier JSON
    public void mettreAJourEtudiant(Etudiant etudiant) {
        for (int i = 0; i < this.collection.size(); i++) {
            if (this.collection.get(i).getNumEtu() == etudiant.getNumEtu()) {
                this.collection.set(i, etudiant);
                break;
            }
        }
        // Écrire la collection mise à jour dans le fichier JSON
        sauvegarderJson();
    }

    // Méthode pour sauvegarder la collection actuelle des étudiants dans le fichier JSON
    public void sauvegarderJson() {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(this.collection, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode pour trier la collection par moyennes générales
    public void triParMoy() {
        Collections.sort(this.collection, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                return Double.compare(e2.getMoyGen(), e1.getMoyGen());
            }
        });
    }
}
