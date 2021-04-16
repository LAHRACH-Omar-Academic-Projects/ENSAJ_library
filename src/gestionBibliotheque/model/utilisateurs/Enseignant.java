package gestionBibliotheque.model.utilisateurs;

/** Class qui représente un enseignant et qui hérite de la classe Adherant
@author 	lahrach omar, mazer omar
@version 	juin 2020
@see 		Adherant
*/

public class Enseignant extends Adherant {
	/** Département d'un enseignant */
	private String departement;
	
	/** Un constructeur qui prend cinq paramètres pour retourner un objet enseignant
	@param		login une chaîne de caractères
	@param		mdp une chaîne de caractères
	@param		nom une chaîne de caractères
	@param		numLecteur un entier
	@param		departement une chaîne de caractères
	*/
	public Enseignant(String login, String mdp, String nom, int numLecteur, String departement) {
		super(login, mdp, nom, numLecteur, 4);
		this.departement = departement;
	}
	
	/** Getter de département de l'enseignant
	@return		la valeur de département departement
    */
	public String getDepartement() {
		return departement;
	}
	
	/** Setter de département de l'enseignant
	@param		departement une chaîne de caractère
    */
	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	/** Méthode qui retourne une représentation d'un objet enseignant qui est facile à lire pour une personne.
	@return 	la représentation d'un objet enseignant
	@see		Adherant#toString()
    */
	@Override
	public String toString() {
		return super.toString() + ", Département : " + departement;
	}
	
	
	
}
