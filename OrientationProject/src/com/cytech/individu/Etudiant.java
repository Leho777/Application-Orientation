package com.cytech.individu;

import java.util.ArrayList;
import java.util.Objects;

public class Etudiant {
	private String nom;
	private String prenom;
	private String mail;
	private String mdpEtu;
	private int numEtu;
	private String filiere;
	private double moyGen;
	private ArrayList<Voeux> listeVoeux = new ArrayList<>(); 
	private Voeux resultat;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * @return the mdpEtu
	 */
	public String getMdpEtu() {
		return mdpEtu;
	}
	/**
	 * @param mdpEtu the mdpEtu to set
	 */
	public void setMdpEtu(String mdpEtu) {
		this.mdpEtu = mdpEtu;
	}
	/**
	 * @return the numEtu
	 */
	public int getNumEtu() {
		return numEtu;
	}
	/**
	 * @param numEtu the numEtu to set
	 */
	public void setNumEtu(int numEtu) {
		this.numEtu = numEtu;
	}
	/**
	 * @return the filiere
	 */
	public String getFiliere() {
		return filiere;
	}
	/**
	 * @param filiere the filiere to set
	 */
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	/**
	 * @return the moyGen
	 */
	public double getMoyGen() {
		return moyGen;
	}
	/**
	 * @param moyGen the moyGen to set
	 */
	public void setMoyGen(double moyGen) {
		this.moyGen = moyGen;
	}
	/**
	 * @return the listVoeux
	 */
	public ArrayList<Voeux> getListeVoeux() {
		return listeVoeux;
	}
	/**
	 * @param listVoeux the listVoeux to set
	 */
	public void setListVoeux(ArrayList<Voeux> listeVoeux) {
		this.listeVoeux = listeVoeux;
	}
	/**
	 * @return the resultat
	 */
	public Voeux getResultat() {
		return resultat;
	}
	/**
	 * @param resultat the resultat to set
	 */
	public void setResultat(Voeux resultat) {
		this.resultat = resultat;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(filiere, listeVoeux, mail, mdpEtu, moyGen, nom, numEtu, prenom, resultat);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		return Objects.equals(filiere, other.filiere) && Objects.equals(listeVoeux, other.listeVoeux)
				&& Objects.equals(mail, other.mail) && Objects.equals(mdpEtu, other.mdpEtu)
				&& Double.doubleToLongBits(moyGen) == Double.doubleToLongBits(other.moyGen)
				&& Objects.equals(nom, other.nom) && numEtu == other.numEtu && Objects.equals(prenom, other.prenom)
				&& Objects.equals(resultat, other.resultat);
	}
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdpEtu=" + mdpEtu + ", numEtu="
				+ numEtu + ", filiere=" + filiere + ", moyGen=" + moyGen + ", listVoeux=" + listeVoeux + ", resultat="
				+ resultat + "]";
	}
	
}
