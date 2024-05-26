/*
 * Recuperation / Sauvegarde d'une liste d'objets categorie contenant une liste de produits
 * dans un fichier JSON
 * 
 * Reference : https://www.jmdoudoux.fr/java/dej/chap-gson.htm
 * 
 * Utilisation de la bibliotheque GSON de Google :
 * Integrer gson-2.10.1.jar dans le ClassPath des librairies du BuildPath
 * par glisser-deposer a partir de l'explorateur (cliquer sur copie). Il doit
 * apparaitre alors dans les "Referenced Libraries"
 */
package com.cytech.gestionFichiers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Arrays;

import com.cytech.data.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GestionJSON {

	/* Recuperation de toute les categories de produits avec les produits a partir du fichier JSON
	 * Entree : nom du fichier
	 * Sortie : Collection List des Categories
	 */
	public static List<Categorie> lireJSON(String fichierJSON) {
		try {
			JsonReader reader = new JsonReader(new FileReader(fichierJSON));
			// On peut lire un seul objet ou un tableau avec []
			Categorie[] tabCat = new Gson().fromJson(reader, Categorie[].class);
			return Arrays.asList(tabCat);
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace() + " : File Not Found");
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
		}
		return null;
	}

	/* Recuperation d'un objet unique de classe T (classe passee en parametre)
	 * Entree : nom du fichier et Classe de l'objet a recuperer
	 * Sortie : objet recupere
	 * Remarque : ne peut recuperer qu'un objet de la classe, pas une collection, qui doit etre geree a l'appel
	 */
	public static <T> T lireObjetJSON(String fichierJSON, Class<T> classe) {
        try (FileReader reader = new FileReader(fichierJSON)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, classe);
        } catch (IOException | JsonParseException e) {
            e.printStackTrace();
            return null;
        }
    }

	/* Sauvegarde d'une collection (liste) d'objets de classe parametree
	 * Entree : Collection liste, de type classe et nom de fichier
	 * Sortie : Vrai si reussi et faux sinon
	 * Sauvegarde directe des attributs des objets grace a la methode toJson
	 * Attention, il y a des restrictions a voir sur le site de jmdoudou
	 */
	public static <T> boolean EcrireJsonDirecte(String fichierJSON, List<T> liste, Class<T> classe) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonStr = gson.toJson(liste);
			BufferedWriter bw = new BufferedWriter(new FileWriter(fichierJSON));
			bw.write(jsonStr);
			bw.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		} catch (JsonParseException e) {
			System.out.println(e.getStackTrace() + " : JsonParseException");
			return false;
		}
		return true;
	}
	
	/* Meme fonction mais par creation manuelle du fichier JSON avec JsonWriter
	 * Entree : collection des categorie et nom du fichier
	 * Sortie : Vrai si reussi et Faux sinon
	 * Remarque : la classe ne peut etre parametree car il faut gerer chaque attribu de la classe
	 */
	public static boolean EcrireJsonManuelle(String fichierJSON, List<Categorie> lstCat) {
		try {
			JsonWriter writer = new JsonWriter(new FileWriter(fichierJSON));
			writer.beginArray(); // une liste devient un tableau en JSON
			for (Categorie cat : lstCat) {
				writer.beginObject();
				writer.name("name").value(cat.getName()); // champs unique
				writer.name("lstpdt	");
				writer.beginArray(); // tableau des produits
				for (Produit pdt : cat.getLstPdt()) {
					writer.beginObject();
					writer.name("ref").value(pdt.getRef());
					writer.name("img").value(pdt.getImg());
					writer.name("description").value(pdt.getDescription());
					writer.name("prix").value(pdt.getPrix());
					writer.name("stock").value(pdt.getStock());
					writer.endObject();
				}
				writer.endArray();
				writer.endObject();
			}
			writer.endArray();
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getStackTrace() + " : Probleme de fichier");
			return false;
		}
		return true;
	}
	
}
