package com.cytech.individu;

import java.util.ArrayList;
import java.util.Objects;

public class Voeux {
	private String nom;
	private int nbPlace;
	private String description;
	private ArrayList<String> filiereEligible = new ArrayList<>(); 
	private int idVoeux;
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the nbPlace
	 */
	public int getNbPlace() {
		return nbPlace;
	}
	/**
	 * @param nbPlace the nbPlace to set
	 */
	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the filiereEligible
	 */
	public ArrayList<String> getFiliereEligible() {
		return filiereEligible;
	}
	/**
	 * @param filiereEligible the filiereEligible to set
	 */
	public void setFiliereEligible(ArrayList<String> filiereEligible) {
		this.filiereEligible = filiereEligible;
	}
	/**
	 * @return the id_voeux
	 */
	public int getIdVoeux() {
		return idVoeux;
	}
	/**
	 * @param id_voeux the id_voeux to set
	 */
	public void setIdVoeux(int idVoeux) {
		this.idVoeux = idVoeux;
	}
	@Override
	public int hashCode() {
		return Objects.hash(description, filiereEligible, idVoeux, nbPlace, nom);
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Voeux other = (Voeux) obj;
	    return idVoeux == other.idVoeux;
	}

	@Override
	public String toString() {
		return "Voeux [nom=" + nom + ", nbPlace=" + nbPlace + ", description=" + description + ", filiereEligible="
				+ filiereEligible + ", id_voeux=" + idVoeux + "]";
	}
	
}


