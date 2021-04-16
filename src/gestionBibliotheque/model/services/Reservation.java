package gestionBibliotheque.model.services;

/** Class qui représente une réservation
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class Reservation {
	/** Numéro de lecteur de l'adhérent qui a réserver */
	private int numLecteur;
	
	/** Cote de l'exemplaire réservé */
	private int cote;
	
	/** Date d'une réservation */
	private String date;
		
	/** Un premier constructeur qui prend deux paramètres pour retourner un objet réservation
	@param		numLecteur un entier
	@param		cote un entier
	*/
	public Reservation(int numLecteur, int cote) {
		this.numLecteur = numLecteur;
		this.cote = cote;
		this.date = "";
	}
	
	/** Un deuxième constructeur qui prend trois paramètres pour retourner un objet réservation
	@param		numLecteur un entier
	@param		cote un entier
	@param		date une chaîne de caractères
	*/
	public Reservation(int numLecteur, int cote, String date) {
		this.numLecteur = numLecteur;
		this.cote = cote;
		this.date = date;
	}
	
	/** Getter du numéro de lecteur de l'adhérant qui va réserver
	@return		la valeur du numéro de lecteur de l'adhérant qui va réserver numLecteur
    */
	public int getNumLecteur() {
		return numLecteur;
	}
	
	/** Setter du numéro de lecteur de l'adhérant qui va réserver
	@param		numLecteur un entier
    */
	public void setNumLecteur(int numLecteur) {
		this.numLecteur = numLecteur;
	}
	
	/** Getter de la cote de l'exemplaire réservé
	@return		la valeur de la cote de l'exemplaire réservé cote
    */
	public int getCote() {
		return cote;
	}
	
	/** Setter de la cote de l'exemplaire réservé
	@param		cote un entier
    */
	public void setCote(int cote) {
		this.cote = cote;
	}
	
	/** Getter de la date de réservation
	@return		la valeur de la date de réservation date
    */
	public String getDate() {
		return date;
	}
	
	/** Setter de la date de réservation
	@param		date une chaîne de caractères
    */
	public void setDate(String date) {
		this.date = date;
	}

	/** Méthode qui retourne une représentation d'un objet réservation qui est facile à lire pour une personne.
	@return 	la représentation d'un objet réservation
    */
	@Override
	public String toString() {
		return "Numéro de lecteur : " +  numLecteur + ", Cote : " + cote + ", Date de réservation : " + date;
	}
}
