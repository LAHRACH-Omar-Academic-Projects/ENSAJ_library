package gestionBibliotheque.controller;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.services.EmpruntDAO;
import gestionBibliotheque.dao.services.ReservationDAO;
import gestionBibliotheque.model.services.Emprunt;
import gestionBibliotheque.model.services.Reservation;

/** Class qui représente un controlleur des services d'emprunt et de réservation
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class ServiceController {
	/** L'opération voulu du controlleur (ex: ajouter un emprunt)*/
	private String op;
	/** L'emprunt à controlleur*/
	private Emprunt emp;
	/** Réservation à controlleur*/
	private Reservation res;
	
	/** Un premier constructeur qui prend deux paramètres pour retourner un objet service controller
	 *  et qui va exécuter l'opération op sur l'emprunt emp
	@param		op une chaîne de caractères
	@param		emp un Emprunt
	*/
	public ServiceController(String op, Emprunt emp) {
		this.op = op;
		this.emp = emp;
		this.executeOp(op, emp);
	}
	
	/** Un deuxème constructeur qui prend deux paramètres pour retourner un objet service controller
	 *  et qui va exécuter l'opération op sur la réservation res
	@param		op une chaîne de caractères
	@param		res un Reservation
	*/
	public ServiceController(String op, Reservation res) {
		this.op = op;
		this.res = res;
		this.executeOp(op, res);
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

	/** Getter du réaliseur de l'emprunt
	@return		le référence de l'objet utilisateur réaliseur de l'emprunt emp
    */
	public Emprunt getUser() {
		return emp;
	}
	
	/** Setter de l'emprunt 
	@param		emp un Emprunt
	*/
	public void setEmp(Emprunt emp) {
		this.emp = emp;
	}
	
	/** Getter de la réservation 
	@return		le référence de l'objet réservation res
	*/
	public Reservation getRes() {
		return res;
	}
	
	/** Setter de la réservation
	@param		res une Reservation
	*/
	public void setRes(Reservation res) {
		this.res = res;
	}
	
	/** Méthode executeOp qui va exécuter l'opération op sur l'emprunt emp
	@param		op une chaîne de caractères
	@param		emp un Emprunt
    */
	public void executeOp(String op, Emprunt emp) {
		if(this.getOp()=="delete") {
			try {
				EmpruntDAO empDAO= new EmpruntDAO();
				empDAO.deleteEmprunt(emp);
				} catch(DAOException ex) {
					System.out.println(ex);
				}
		}
		else if(this.getOp() == "add") {
			try {
				EmpruntDAO empDAO= new EmpruntDAO();
				empDAO.addEmprunt(emp);
				} catch(DAOException ex) {
					System.out.println(ex);
				}
		}
	}
	
	/** Méthode executeOp qui va exécuter l'opération op sur la réservation res
	@param		op une chaîne de caractères
	@param		res une Reservation
    */
	public void executeOp(String op, Reservation res) {
		if(this.getOp()=="delete") {
			try {
				ReservationDAO resDAO= new ReservationDAO();
				resDAO.deleteReservation(res);
				} catch(DAOException ex) {
					System.out.println(ex);
				}
			}
		}
}
