package gestionBibliotheque.dao;

/** Class qui permet la gestion des exception d'accée au base de données
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class DAOException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/** un constructeur qui ne fait rien */
	public DAOException() {
    }
	
	/** un constructeur qui prend en paramètre un message à afficher
	 * @param message une chaîne de caractère
	 */
    public DAOException(String message) {
        super(message);
    }
    
    /** un constructeur qui prend en paramètre un message et un raison
     * @param message une chaîne de caractères
     * @param cause Throwable
     */
    public DAOException(String message,Throwable cause) {
        super(message,cause);
    }
    
    /** Méthode getMessage() permet d'afficher un message claire pour l'exception
    @return un message
    */
    @Override
    public String getMessage(){
    	return super.getMessage() + super.getCause();
    }

}

