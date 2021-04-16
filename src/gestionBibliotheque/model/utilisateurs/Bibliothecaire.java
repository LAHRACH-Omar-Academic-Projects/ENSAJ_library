package gestionBibliotheque.model.utilisateurs;

/** Class qui représente un bibliothecaire et qui hérite de la classe Utilisateur
@author 	lahrach omar, mazer omar
@version 	juin 2020
@see 		Utilisateur
*/

public class Bibliothecaire extends Utilisateur {
	/** Identifiant d'un bibliothecaire */
	private int id;
	
	/** Date de naissance d'un bibliothecaire */
	private String dateNaissance;
	
	/** Un constructeur qui prend cinq paramètres pour retourner un objet bibliothecaire
	@param		login une chaîne de caractères
	@param		mdp une chaîne de caractères
	@param		nom une chaîne de caractères
	@param		id un entier
	@param		dateNaissance une chaîne de caractères
	*/
	public Bibliothecaire(int id,String login, String mdp, String nom,  String dateNaissance) {
		super(login, mdp, nom);
		this.id = id;
		this.dateNaissance = dateNaissance;
	}
	

    /** Getter de l'identifiant du bibliothecaire
	@return 	la valeur de l'identifiant id
    */
	public int getId() {
		return id;
	}
	
	/** Setter de l'identifiant du bibliothecaire 
	@param      id un entier
	*/
	public void setId(int id) {
		this.id = id;
	}
	
	/** Getter de la date de naissance du bibliothecaire
	@return 	la valeur de la date de naissance dateNaissance
    */
	public String getDateNaissance() {
		return dateNaissance;
	}
	
	/** Setter de la date de naissance du bibliothecaire 
	@param      dateNaissance une chaîne de caractère
	*/
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	/** Méthode qui retourne une représentation d'un objet bibliothecaire qui est facile à lire pour une personne.
	@return 	la représentation d'un objet bibliothecaire
	@see		Utilisateur#toString()
    */
	@Override
	public String toString() {
		return super.toString() + ", Id : " + id + ", Date de naissance : " + dateNaissance;
	}
	
	
	
}
