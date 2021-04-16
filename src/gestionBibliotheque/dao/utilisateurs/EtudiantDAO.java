package gestionBibliotheque.dao.utilisateurs;

import java.util.*;
import java.sql.*;
import java.io.*;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.model.utilisateurs.Etudiant;

/** Class qui permet la récupération des données des étudiants
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class EtudiantDAO {
	/** Connection permet de l'accée au base de données */
	private Connection connection;
	
	/** Un constructeur qui réalize une connexion
	 * @throws DAOException Exception
	 */
	public EtudiantDAO() throws DAOException {
		
		Properties dbprops = new Properties();
		try {
			dbprops.load(new FileInputStream("dbprops.properties"));
		} catch(IOException e) {
			throw new DAOException("problème de chargement des informations de la base de donnée...", e);
		}
		String user, password, dburl;
		user = dbprops.getProperty("user");
		password = dbprops.getProperty("password");
		dburl = dbprops.getProperty("dburl");
		
		
		try {
			connection = DriverManager.getConnection(dburl, user, password);
		} catch(SQLException e) {
			throw new DAOException("problème de connexion au base de donnée " + dburl + "...", e);
		}
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	/** Méthode addEtudiant permet d'ajouter un étudiant
	 * @param etudiant un Etudiant
	 * @throws DAOException une Exception
	 */
	public void addEtudiant(Etudiant etudiant) throws DAOException{
		PreparedStatement req1 = null;
		PreparedStatement req2 = null;
		PreparedStatement req3 = null;
		String sql1,sql2,sql3;
		try {
			sql1 = "INSERT INTO adherant(numLecteur, login, mdp, nbMax, nom,isVerified) VALUES (0, ?, ?, ?, ?,0)";
			req1 = connection.prepareStatement(sql1);
			req1.setString(1, etudiant.getLogin());
			req1.setString(2, etudiant.getMdp());
			req1.setInt(3, 2);
			req1.setString(4, etudiant.getNom());
			req1.executeUpdate();
			
			sql2 = "SELECT numLecteur FROM Adherant WHERE login = ? and nom =? and isVerified=0";
			req2 = connection.prepareStatement(sql2);
			req2.setString(1, etudiant.getLogin());
			req2.setString(2, etudiant.getNom());
			ResultSet rs= req2.executeQuery();
			int id=0;
			while (rs.next()) {
				id= rs.getInt("numLecteur");
			}
			sql3 = "INSERT INTO etudiant(numLecteur, cne, adresse, filliere) VALUES (?, ?, ?, ?)";
			req3 = connection.prepareStatement(sql3);
			req3.setInt(1, id);
			req3.setString(2, etudiant.getCne());
			req3.setString(3, etudiant.getAdresse());
			req3.setString(4, etudiant.getFilliere());
			req3.executeUpdate();
			
		} catch(SQLException e) {
			throw new DAOException("problème d'ajout de l'étudiant " + etudiant.getNom(), e);
		}finally {
			close(req1);
			close(req2);
			close(req3);
		}
	}
	
	/** Méthode qui permet la modification d'un étudiant
	 * @param et Etudiant à modifier
	 * @param login	une chaîne de caractères
	 * @param mdp une chaîne de caractères
	 * @param nom une chaîne de caractères
	 * @param cne une chaîne de caractères
	 * @param adresse une chaîne de caractères
	 * @param fil une chaîne de caractères
	 * @throws DAOException une Exception
	 */
	public void modifyEtudiant(Etudiant et,String login,String mdp,String nom,String cne,String adresse,String fil) throws DAOException{
		PreparedStatement req1 = null;
		PreparedStatement req2 = null;
		PreparedStatement req3 = null;
		String sql1,sql3;
		
		try {
			sql1 = "UPDATE adherant SET login = ?, mdp = ?, nom = ? WHERE numLecteur = ?";
			req1 = connection.prepareStatement(sql1);
			req1.setString(1, login);
			req1.setString(2, mdp);
			req1.setString(3, nom);
			req1.setInt(4, et.getNumLecteur());
			req1.executeUpdate();

			sql3 = "UPDATE etudiant SET cne = ?, adresse = ?, filliere = ? WHERE numLecteur = ?";
			req3 = connection.prepareStatement(sql3);
			req3.setString(1, cne);
			req3.setString(2, adresse);
			req3.setString(3, fil);
			req3.setInt(4, et.getNumLecteur());
			req3.executeUpdate();

		} catch(SQLException e) {
			throw new DAOException("problème de modification d'un etudiant " + et.getNom(), e);
		}finally {
			close(req1);
			close(req2);
			close(req3);
		}
	}
	
	/** Méthode getAllEtudiant permet de renvoyer tous les étudiant
	 * @return	list de tous les étudiants
	 * @throws DAOException Exception
	 */
	public List<Etudiant> getAllEtudiant() throws DAOException {
		List<Etudiant> list = new ArrayList<Etudiant>();
		
		Statement req = null;
		ResultSet rs = null;
		String sql;
		
		try {
			req = connection.createStatement();
			sql = "SELECT adherant.*,etudiant.cne as cne, etudiant.adresse as adresse ,etudiant.filliere as filliere FROM adherant INNER JOIN etudiant on adherant.numLecteur=etudiant.numLecteur where adherant.isVerified=1";
			rs = req.executeQuery(sql);
			
			while (rs.next()) {
				int numLecteur = rs.getInt("numLecteur");
				String login = rs.getString("login");
				String mdp = rs.getString("mdp");
				String nom = rs.getString("nom");
				String cne = rs.getString("cne");
				String adr = rs.getString("adresse");
				String fil = rs.getString("filliere");
				Etudiant et = new Etudiant(login,mdp,nom,numLecteur,cne,adr,fil);
				list.add(et);
			}

			return list;		
		}catch (SQLException e) {
			throw new DAOException("problème d'affichage de la liste des etudiants ", e);
		}finally {
			close(req, rs);
		}
	}
	
	/** Méthode de class permet la fermeture de connexion, statement, requête
	 * @param myConn Connection
	 * @param myStmt Statement
	 * @param myRs ResultSet
	 * @throws DAOException Exception
	 */
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws DAOException {

		if (myRs != null) {
			try {
				myRs.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing the ResultSet ... ", e);
			}
		}

		if (myStmt != null) {
			try {
				myStmt.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing the Statement ... ", e);
			}
		}
		
		if (myConn != null) {
			try {
				myConn.close();
			} catch (SQLException e) {
				throw new DAOException("Error closing the Connection ... ", e);
			}
		}
	}
	
	/** Méthode de class permet la fermeture de statement, requête
	 * @param myStmt Statement
	 * @param myRs ResultSet
	 * @throws DAOException Exception
	 */
	private void close(Statement myStmt, ResultSet myRs) throws DAOException {
		close(null, myStmt, myRs);		
	}
	
	/** Méthode de class permet la fermeture de statement
	 * @param myStmt Statement
	 * @throws DAOException Exception
	 */
	private void close(Statement myStmt) throws DAOException {
		close(null, myStmt, null);		
	}

}

