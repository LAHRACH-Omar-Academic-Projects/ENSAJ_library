package gestionBibliotheque.model.services;

/** Class qui représente un emprunt
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class Emprunt {
	/** Numéro de lecteur d'un adhérant pour un emprunt */
	private int numLecteur;
	
	/** Cote de l'exemplaire à emprunter */
	private int cote;
	
	/** Date de début d'un emprunt */
	private String dateDebut;
	
	/** Date de fin d'un emprunt */
	private String dateFin;
	
	/** Identifiant du bibliothecaire qui va réaliser un emprunt */
	private int idBibliothecaire;
	
	/** Un constructeur qui prend cinq paramètres pour retourner un objet emprunt
	@param		numLecteur un entier
	@param		cote un entier
	@param		dateDebut une chaîne de caractères
	@param		dateFin une chaîne de caractères
	@param		idBibliothecaire un entier
	*/
	public Emprunt(int numLecteur, int cote, String dateDebut, String dateFin, int idBibliothecaire) {
		this.numLecteur = numLecteur;
		this.cote = cote;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.idBibliothecaire = idBibliothecaire;
	}
	
	/** Getter de numéro de lecteur pour l'emprunt
	@return		la valeur de numéro de lecteur pour l'emprunt numLecteur
    */
	public int getNumLecteur() {
		return numLecteur;
	}
	
	/** Setter de numéro de lecteur pour l'emprunt
	@param		numLecteur un entier
    */
	public void setNumLecteur(int numLecteur) {
		this.numLecteur = numLecteur;
	}
	
	/** Getter de la cote de l'exemplaire emprunté
	@return		la valeur de la cote de l'exemplaire emprunté cote
    */
	public int getCote() {
		return cote;
	}
	
	/** Setter de la cote de l'exemplaire à emprunter
	@param		cote un entier
    */
	public void setCote(int cote) {
		this.cote = cote;
	}
	
	/** Getter de la date de début de l'emprunt
	@return		la valeur de la date de début de l'emprunt dateDebut
    */
	public String getDateDebut() {
		return dateDebut;
	}
	
	/** Setter de la date de début de l'emprunt
	@param		dateDebut une chaîne de caractères
    */
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	/** Getter de la date de début de l'emprunt
	@return		la valeur de la date de début de l'emprunt dateFin
    */
	public String getDateFin() {
		return dateFin;
	}
	
	/** Setter de la date de début de l'emprunt
	@param		dateFin une chaîne de caractères
    */
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	/** Getter de l'id de bibliothecaire qui a réaliser l'emprunt
	@return		la valeur de l'id de bibliothecaire qui a réaliser l'emprunt idBibliothecaire
    */
	public int getIdBibliothecaire() {
		return idBibliothecaire;
	}
	
	/** Setter de l'id de bibliothecaire qui a réaliser l'emprunt
	@param		idBibliothecaire un entier
    */
	public void setIdBibliothecaire(int idBibliothecaire) {
		this.idBibliothecaire = idBibliothecaire;
	}
	
	/** Méthode qui retourne une représentation d'un objet emprunt qui est facile à lire pour une personne.
	@return 	la représentation d'un objet emprunt
    */
	@Override
	public String toString() {
		return "Numéro de lecteur : " +  numLecteur + ", Cote : " + cote + ", Date de début : " + dateDebut + ", Date fin : " + dateFin + "Id bibliothecaire : " + idBibliothecaire;
	}
		
}
