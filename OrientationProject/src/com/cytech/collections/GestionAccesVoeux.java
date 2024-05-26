package com.cytech.collections;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestionAccesVoeux {

	private static final String FILENAME = "BDD/accesFaireVoeux.json";
    private static final Gson gson = new Gson();
    private boolean boolValue;

    public GestionAccesVoeux() {
        //On initialise boolValue avec la valeur qu'il y a dans le json
        lireJson();
    }

    //Méthode pour lire la valeur qu'il y a dans le json et l'attribuer à boolValue
    private void lireJson() {
        try (FileReader reader = new FileReader(FILENAME)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            this.boolValue = jsonObject.get("boolValue").getAsBoolean();
        } catch (IOException e) {
            System.out.println(FILENAME + " not found or could not be read. Initializing with default values.");
            this.boolValue = false;
        } catch (Exception e) {
            System.out.println("Error parsing JSON. Initializing with default values.");
            this.boolValue = false;
        }
    }

    //Methode pour modifier le json avec la valeur de boolValue
    public void modifierJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("boolValue", this.boolValue);

        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(jsonObject, writer);
        } catch (IOException e) {
            System.out.println("Error writing to " + FILENAME + ": " + e.getMessage());
        }
    }

    public boolean getBoolValue() {
        return this.boolValue;
    }

    public void setBoolValue(boolean value) {
        this.boolValue = value;
    }
	
}
