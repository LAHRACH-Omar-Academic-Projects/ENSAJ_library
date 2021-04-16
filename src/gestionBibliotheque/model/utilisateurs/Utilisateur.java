package gestionBibliotheque.model.utilisateurs;

/** Class qui représente un utilisateur
@author		lahrach omar, mazer omar
@version	juin 2020
*/

public class Utilisateur {
	/** Login d'un utilisateur */
	protected String login;
	
	/** Mot de passe d'un utilisateur */
	protected String mdp;
	
	/** Nom d'un utilisateur */
	protected String nom;
	
	/** Un premier constructeur qui prend deux paramètres pour retourner un objet utilisateur
	@param		login une chaîne de caractères
	@param		mdp une chaîne de caractères
	*/
	public Utilisateur(String login, String mdp) {
		this.login = login;
		this.mdp = mdp;
	}
	
	/** Un deuxième constructeur qui prend trois paramètres pour returner un objet utilisateur
	@param		login une chaîne de caractères
	@param		mdp une chaîne de caractères
	@param		nom une chaîne de caractères	
	*/
	public Utilisateur(String login, String mdp, String nom) {
		this(login, mdp);
		this.nom = nom;
	}
	
	/** Getter du login de l'utilisateur
	@return		la valeur du login login
    */
	public String getLogin() {
		return login;
	}
	
	/** Setter du login de l'utilisateur 
	@param      login une chaîne de caractères 
	*/
	public void setLogin(String login) {
		this.login = login;
	}
	
	/** Getter du mot de passe de l'utilisateur
	@return		la valeur du mot de passe mdp
    */
	public String getMdp() {
		return mdp;
	}
	
	/** Setter du mot de passe de l'utilisateur 
	@param      mdp une chaîne de caractères
	*/
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	/** Getter du nom de l'utilisateur
	@return 	la valeur du nom nom
    */
	public String getNom() {
		return nom;
	}
	
	/** Setter du nom de l'utilisateur
	@param      nom une chaîne de caractères
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/** Méthode qui retourne une représentation d'un objet utilisateur qui est facile à lire pour une personne.
	@return 	la représentation d'un objet utilisateur
    */
	@Override
	public String toString() {
		return "Login : " + login + ", Mot de passe : " + mdp + ", Nom : " + nom;
	}	
}
