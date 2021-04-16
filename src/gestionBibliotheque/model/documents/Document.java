package gestionBibliotheque.model.documents;

/** Class qui représente un document
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class Document {
	/** Identifiant d'un document */
	protected int id;
	
	/** Titre d'un document */
	protected String titre;
	
	/** Un constructeur qui prend deux paramètres pour retourner un objet document
	@param		id un entier
	@param		titre une chaîne de caractères
	*/
	public Document(int id, String titre) {
		this.id = id;
		this.titre = titre;
	}
	
	/** Getter de l'identifiant du document
	@return		la valeur de l'identifiant du document id
    */
	public int getId() {
		return id;
	}
	
	/** Setter de l'identifiant du document
	@param		id un entier
    */
	public void setId(int id) {
		this.id = id;
	}
	
	/** Getter de titre du document
	@return		la valeur de titre du document titre
    */
	public String getTitre() {
		return titre;
	}
	
	/** Setter de titre du document
	@param		titre une chaîne de caractères
    */
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	/** Méthode qui retourne une représentation d'un objet document qui est facile à lire pour une personne.
	@return 	la représentation d'un objet document
    */
	@Override
	public String toString() {
		return "Id : " + id + ", Titre : " + titre;
	}
}
