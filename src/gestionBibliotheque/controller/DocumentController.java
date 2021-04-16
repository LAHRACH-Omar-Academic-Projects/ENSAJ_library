package gestionBibliotheque.controller;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.document.ExemplaireDAO;
import gestionBibliotheque.dao.document.LivreDAO;
import gestionBibliotheque.dao.document.RevueDAO;
import gestionBibliotheque.model.documents.Document;
import gestionBibliotheque.model.documents.Exemplaire;
import gestionBibliotheque.model.documents.Livre;
import gestionBibliotheque.model.documents.Revue;

/** Class qui représente un controlleur des documents
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class DocumentController {
	/** L'opération voulu du controlleur (ex: ajouter un document)*/
	private String op;
	/** Le document (livre/revue) controllé*/
	private Document doc;
	/** L'exemplaire controllé */
	private Exemplaire exemp;
	
	/** Un premier constructeur qui prend deux paramètres pour retourner un objet document controller
	 *  et qui va exécuter l'opération op sur le document doc
	@param		op une chaîne de caractères
	@param		doc un Document
	*/
	public DocumentController(String op, Document doc) {
		this.op = op;
		this.doc = doc;
		this.executeOp(op, doc);
	}
	
	/** Un deuxième constructeur qui prend deux paramètres pour retourner un objet document controller
	 *  et qui va exécuter l'opération op sur l'exemplaire doc
	@param		op une chaîne de caractères
	@param		exemp un Exemplaire
	*/
	public DocumentController(String op, Exemplaire exemp) {
		this.op = op;
		this.exemp = exemp;
		this.executeOp(op, exemp);
	}
	
	/** Getter de l'opération
	@return		la valeur de l'opération op
    */
	public String getOp() {
		return op;
	}
	
	/** Setter de l'opération
	@param		op une chaîne de caractère
    */
	public void setOp(String op) {
		this.op = op;
	}
	
	/** Getter du document (livre/revue)
	@return		le référence de l'objet document doc
    */
	public Document getDoc() {
		return doc;
	}
	
	/** Setter du document à controller
	@param		doc un Document
    */
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	/** Getter de l'exemplaire
	@return		le référence de l'objet exemplaire exemp
    */
	public Exemplaire getExemp() {
		return exemp;
	}
	
	/** Setter de l'exemplaire à controller
	@param		exemp un Exemplaire
    */
	public void setExemp(Exemplaire exemp) {
		this.exemp = exemp;
	}
	
	/** Méthode executeOp qui va exécuter l'opération op sur le document doc
	@param		op une chaîne de caractères
	@param		doc un Document
    */
	public void executeOp(String op, Document doc) {
		if(this.getOp()=="add") {
			if(doc instanceof Livre ) {
				try {
					LivreDAO livreDAO= new LivreDAO();
					livreDAO.addLivre((Livre) doc);
					} catch(DAOException ex) {
						System.out.println(ex);
					}
			}
			else if(doc instanceof Revue ) {
				try {
					RevueDAO revueDAO= new RevueDAO();
					revueDAO.addRevue((Revue) doc);
					} catch(DAOException ex) {
						System.out.println(ex);
					}
			}
		}
		else if(this.getOp()=="delete") {
			if(doc instanceof Livre ) {
				try {
					LivreDAO livreDAO= new LivreDAO();
					livreDAO.deleteLivre((Livre) doc);
					} catch(DAOException ex) {
						System.out.println(ex);
					}
			}
			else if(doc instanceof Revue ) {
				try {
					RevueDAO revueDAO= new RevueDAO();
					revueDAO.deleteRevue((Revue) doc);
					} catch(DAOException ex) {
						System.out.println(ex);
					}
			}
		}
	}
	
	/** Méthode executeOp qui va exécuter l'opération op sur l'exemplaire exemp
	@param		op une chaîne de caractères
	@param		exemp un Exemplaire
    */
	public void executeOp(String op, Exemplaire exemp) {
		if(this.getOp()=="add") {
			try {
				ExemplaireDAO exempDAO= new ExemplaireDAO();
				exempDAO.addExemplaire(exemp);
				} catch(DAOException ex) {
					System.out.println(ex);
				}
			}
		else if(this.getOp()=="delete") {
			try {
				ExemplaireDAO exempDAO= new ExemplaireDAO();
				exempDAO.deleteExemplaire(exemp);
				} catch(DAOException ex) {
					System.out.println(ex);
				}
			}
	}
	
}
