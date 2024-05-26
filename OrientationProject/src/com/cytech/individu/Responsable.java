package com.cytech.individu;

import java.util.Objects;

public class Responsable {
	private String nom;
	private String prenom;
	private String mail;
	private String mdpRespo;
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
	 * @return the mdpRespo
	 */
	public String getMdpRespo() {
		return mdpRespo;
	}
	/**
	 * @param mdpRespo the mdpRespo to set
	 */
	public void setMdpRespo(String mdpRespo) {
		this.mdpRespo = mdpRespo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mail, mdpRespo, nom, prenom);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Responsable other = (Responsable) obj;
		return Objects.equals(mail, other.mail) && Objects.equals(mdpRespo, other.mdpRespo)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}
	@Override
	public String toString() {
		return "Responsable [nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdpRespo=" + mdpRespo + "]";
	}
	
}
