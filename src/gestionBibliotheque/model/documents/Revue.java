package gestionBibliotheque.model.documents;

/** Class qui représente un revue et qui hérite de la classe document
@author 	lahrach omar, mazer omar
@version 	juin 2020
@see 		Document
*/

public class Revue extends Document {
	/** Périodicité d'un revue (hebdomadaire, quotidienne, mensuel) */
	private String periodicite;
	
	/** Date de parution d'un revue */
	private String dateParution;
	
	/** Un constructeur qui prend quatre paramètres pour retourner un objet revue
	@param		id un entier
	@param		titre une chaîne de caractères
	@param		periodicite une chaîne de caractères
	@param		dateParution une chaîne de caractères
	*/
	public Revue(int id, String titre, String periodicite,String dateParution) {
		super(id, titre);
		this.periodicite = periodicite;
		this.dateParution = dateParution;
	}
	
	/** Getter de la date de parution du revue
	@return		la valeur de la date de parution du livre dateParution
    */
	public String getDateParution() {
		return dateParution;
	}
	
	/** Setter de la date de parution du revue
	@param		dateParution une chaîne de caractères
    */
	public void setDateParution(String dateParution) {
		this.dateParution = dateParution;
	}
	
	/** Getter de la périodicité du revue
	@return		la valeur de la périodicité du revue periodicite
    */
	public String getPeriodicite() {
		return periodicite;
	}
	
	/** Setter de la périodicité du revue
	@param		periodicite une chaîne de caractères
    */
	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}
	
	/** Méthode qui retourne une représentation d'un objet revue qui est facile à lire pour une personne.
	@return 	la représentation d'un objet revue
	@see		Document#toString()
    */
	@Override
	public String toString() {
		return super.toString() + ", Périodicité : " + periodicite + ", Date de parution : " + dateParution;
	}
	
	
}
