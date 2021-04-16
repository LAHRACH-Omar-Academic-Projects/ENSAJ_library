package gestionBibliotheque.model.utilisateurs;

/** Class qui représente un adhérant et qui hérite de la classe Utilisateur
@author 	lahrach omar, mazer omar
@version 	juin 2020
@see 		Utilisateur
*/

public class Adherant extends Utilisateur {
	/** Numéro de lecteur , identifiant d'un adhérant */
	protected int numLecteur;
	
	/** Nombre max de réservation possible qu'un adhérant peut faire(2 pour un étudiant/4 pour un enseignant) */
	protected int nbMax;
	
	/** Un constructeur qui prend cinq paramètres pour retourner un objet adhérant
	@param		login une chaîne de caractères
	@param		mdp une chaîne de caractères
	@param		nom une chaîne de caractères
	@param		numLecteur un entier
	@param		nbMax un entier	
	*/
	public Adherant(String login, String mdp, String nom, int numLecteur, int nbMax) {
		super(login, mdp, nom);
		this.numLecteur = numLecteur;
		this.nbMax = nbMax;
	}
	
	/** Getter du numéro de lecteur de l'adhérant
	@return		la valeur du numéro de lecteur numLecteur
    */
	public int getNumLecteur() {
		return numLecteur;
	}
	
	/** Setter du numéro de lecteur de l'adhérant
	@param		numLecteur un entier
    */
	public void setNumLecteur(int numLecteur) {
		this.numLecteur = numLecteur;
	}
	
	/** Getter du nombre de max de réservations de l'adhérant
	@return		la valeur du nombre max de réservations nbMax
    */
	public int getNbMax() {
		return nbMax;
	}
	
	/** Setter du nombre de max de réservations de l'adhérant 
	@param      nbMax un entier
	*/
	public void setNbMax(int nbMax) {
		this.nbMax = nbMax;
	}
	
	/** Méthode qui retourne une représentation d'un objet adhérant qui est facile à lire pour une personne.
	@return 	la représentation d'un objet adhérant
	@see		Utilisateur#toString()
    */
	@Override
	public String toString() {
		return super.toString() + ", Numéro de lecteur : " + numLecteur + ", Nombre max de réservation : " + nbMax;
	}

}
