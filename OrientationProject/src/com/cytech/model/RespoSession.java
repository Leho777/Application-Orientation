package com.cytech.model;


import com.cytech.individu.Responsable;

public class RespoSession {
    // Instance unique de la classe ResponsableSession (singleton)
    private static RespoSession instance;
    
    // Le responsable actuellement connecté
    private Responsable respoConnecte;

    // Constructeur privé pour empêcher l'instanciation directe
    private RespoSession() {
    }

    /**
     * Retourne l'instance unique de RespoSession.
     * Crée une nouvelle instance si elle n'existe pas encore.
     * 
     * @return l'instance unique de RespoSession
     */
    public static RespoSession getInstance() {
        if (instance == null) {
            instance = new RespoSession();
        }
        return instance;
    }

    /**
     * Définit le responsable actuellement connecté.
     * 
     * @param respo le responsable à définir comme étant connecté
     */
    public void setRespoConnecte(Responsable respo) {
        this.respoConnecte = respo;
    }

    /**
     * Retourne le responsable actuellement connecté.
     * 
     * @return le responsable actuellement connecté
     */
    public Responsable getRespoConnecte() {
        return respoConnecte;
    }

    /**
     * Efface la session du responsable actuellement connecté.
     */
    public void clearSession() {
        respoConnecte = null;
    }
}
