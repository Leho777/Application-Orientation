package com.cytech.model;

import com.cytech.individu.Etudiant;

public class EtudiantSession {
    // Instance unique de la classe EtudiantSession (singleton)
    private static EtudiantSession instance;
    
    // L'étudiant actuellement connecté
    private Etudiant etudiantConnecte;

    // Constructeur privé pour empêcher l'instanciation directe
    private EtudiantSession() {
    }

    /**
     * Retourne l'instance unique de EtudiantSession.
     * Crée une nouvelle instance si elle n'existe pas encore.
     * 
     * @return l'instance unique de EtudiantSession
     */
    public static EtudiantSession getInstance() {
        if (instance == null) {
            instance = new EtudiantSession();
        }
        return instance;
    }

    /**
     * Définit l'étudiant actuellement connecté.
     * 
     * @param etudiant l'étudiant à définir comme étant connecté
     */
    public void setEtudiantConnecte(Etudiant etudiant) {
        this.etudiantConnecte = etudiant;
    }

    /**
     * Retourne l'étudiant actuellement connecté.
     * 
     * @return l'étudiant actuellement connecté
     */
    public Etudiant getEtudiantConnecte() {
        return etudiantConnecte;
    }

    /**
     * Efface la session de l'étudiant actuellement connecté.
     */
    public void clearSession() {
        etudiantConnecte = null;
    }
}
