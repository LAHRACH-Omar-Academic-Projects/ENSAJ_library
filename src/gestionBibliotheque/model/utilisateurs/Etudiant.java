package gestionBibliotheque.model.utilisateurs;

/** Class qui représente un etudiant et qui hérite de la classe Adherant
@author 	lahrach omar, mazer omar
@version 	juin 2020
@see 		Adherant
*/

public class Etudiant extends Adherant {
	/** Code national d'un étudiant */
	private String cne;
	
	/** Adresse d'un étudiant */
	private String adresse;
	
	/** Filliere d'un étudiant */
	private String filliere;
	
	/** Un constructeur qui prend sept paramètres pour retourner un objet étudiant
	@param		login une chaîne de caractères
	@param		mdp une chaîne de caractères
	@param		nom une chaîne de caractères
	@param		numLecteur un entier
	@param		cne une chaîne de caractères
	@param		adresse une chaîne de caractères
	@param		filliere une chaîne de caractères	
	*/
	public Etudiant(String login, String mdp, String nom, int numLecteur,  String cne, String adresse,
			String filliere) {
		super(login, mdp, nom, numLecteur, 2);
		this.cne = cne;
		this.adresse = adresse;
		this.filliere = filliere;
	}

	/** Getter du code national de l'étudiant
	@return		la valeur du code national cne
    */
	public String getCne() {
		return cne;
	}

	/** Setter du code national de l'étudiant
	@param		cne une chaîne de caractères 
    */
	public void setCne(String cne) {
		this.cne = cne;
	}

	/** Getter de l'adresse de l'enseignant
	@return		la valeur de l'adresse adresse
    */
	public String getAdresse() {
		return adresse;
	}

	/** Setter de l'adresse de l'enseignant
	@param	adresse une chaîne de caractères
    */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/** Getter de la filliere de l'étudiant
	@return		la valeur de la filliere filliere
    */
	public String getFilliere() {
		return filliere;
	}

	/** Setter de la filliere de l'étudiant
	@param	filliere une chaîne de caractères
    */
	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}

	/** Méthode qui retourne une représentation d'un objet étudiant qui est facile à lire pour une personne.
	@return 	la représentation d'un objet étudiant
	@see		Adherant#toString()
    */
	@Override
	public String toString() {
		return super.toString() + ", CNE : " + cne + ", Adresse : " + adresse + ", Fillière : " + filliere;
	}
	
	
	
	
}
