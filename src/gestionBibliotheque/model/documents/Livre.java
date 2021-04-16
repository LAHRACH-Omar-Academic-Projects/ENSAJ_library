package gestionBibliotheque.model.documents;

/** Class qui représente un livre et qui hérite de la classe document
@author 	lahrach omar, mazer omar
@version 	juin 2020
@see 		Document
*/

public class Livre extends Document {
	/** Nom de l'éditeur d'un livre */
	private String nomEditeur;
	
	/** Auteur d'un livre */
	private String auteur;
	
	/** Un constructeur qui prend quatre paramètres pour retourner un objet livre
	@param		id un entier
	@param		titre une chaîne de caractères
	@param		nomEditeur une chaîne de caractères
	@param		auteur une chaîne de caractères
	*/
	public Livre(int id, String titre, String nomEditeur, String auteur) {
		super(id, titre);
		this.nomEditeur = nomEditeur;
		this.auteur = auteur;
	}
	
	/** Getter de nom de l'éditeur du livre
	@return		la valeur de nom de l'éditeur du livre nomEditeur
    */
	public String getNomEditeur() {
		return nomEditeur;
	}
	
	/** Setter de nom de l'éditeur du livre
	@param		nomEditeur une chaîne de caractères
    */
	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
	}
	
	/** Getter de l'auteur du livre
	@return		la valeur de l'auteur du livre auteur
    */
	public String getAuteur() {
		return auteur;
	}
	
	/** Setter de l'auteur du livre
	@param		auteur une chaîne de caractères
    */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	
	/** Méthode qui retourne une représentation d'un objet livre qui est facile à lire pour une personne.
	@return 	la représentation d'un objet livre
	@see		Document#toString()
    */
	@Override
	public String toString() {
		return super.toString() + ", Nom de l'éditeur : " + nomEditeur + ", Auteur : " + auteur;
	}
	
	
	
}
