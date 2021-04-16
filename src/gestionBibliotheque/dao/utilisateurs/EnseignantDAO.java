package gestionBibliotheque.dao.utilisateurs;

import java.util.*;
import java.sql.*;
import java.io.*;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.model.utilisateurs.Enseignant;

/** Class qui permet la récupération des données des enseignants
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class EnseignantDAO {
	/** Connection permet de l'accée au base de données */
	private Connection connection;
	
	/** Un constructeur qui réalize une connexion
	 * @throws DAOException Exception
	 */
	public EnseignantDAO() throws DAOException {
		
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
	
	/** Méthode addEnseignant permet d'ajouter un enseignant
	 * @param enseignant un Enseignant
	 * @throws DAOException une Exception
	 */
	public void addEnseignant(Enseignant enseignant) throws DAOException{
		PreparedStatement req1 = null;
		PreparedStatement req2 = null;
		PreparedStatement req3 = null;
		String sql1,sql2,sql3;
		try {
			sql1 = "INSERT INTO adherant(numLecteur, login, mdp, nbMax, nom, isVerified) VALUES (0, ?, ?, ?, ?,0)";
			req1 = connection.prepareStatement(sql1);
			req1.setString(1, enseignant.getLogin());
			req1.setString(2, enseignant.getMdp());
			req1.setInt(3, 4);
			req1.setString(4, enseignant.getNom());
			req1.executeUpdate();
			
			sql2 = "SELECT numLecteur FROM Adherant WHERE login = ? and nom =? and isVerified=0";
			req2 = connection.prepareStatement(sql2);
			req2.setString(1, enseignant.getLogin());
			req2.setString(2, enseignant.getNom());
			ResultSet rs= req2.executeQuery();
			int id=0;
			while (rs.next()) {
				id= rs.getInt("numLecteur");
			}
			sql3 = "INSERT INTO enseignant(numLecteur,departement) VALUES (?, ?)";
			req3 = connection.prepareStatement(sql3);
			req3.setInt(1, id);
			req3.setString(2, enseignant.getDepartement());
			req3.executeUpdate();
			
		} catch(SQLException e) {
			throw new DAOException("problème d'ajout de l'enseignant " + enseignant.getNom(), e);
		}finally {
			close(req1);
			close(req2);
			close(req3);
		}
	}
	
	/** Méthode qui permet la modification d'un enseignant
	 * @param en Enseignant à modifier
	 * @param login	une chaîne de caractères
	 * @param mdp une chaîne de caractères
	 * @param nom une chaîne de caractères
	 * @param dep une chaîne de caractères
	 * @throws DAOException une Exception
	 */
	public void modifyEnseignant(Enseignant en,String login,String mdp,String nom,String dep) throws DAOException{
		PreparedStatement req1 = null;
		PreparedStatement req2 = null;
		PreparedStatement req3 = null;
		
		String sql1,sql2,sql3;
		int id=0;
		try {
			sql1 = "UPDATE adherant SET login = ?, mdp = ?, nom = ? WHERE login=? and nom=?";
			req1 = connection.prepareStatement(sql1);
			req1.setString(1, login);
			req1.setString(2, mdp);
			req1.setString(3, nom);
			req1.setString(4, en.getLogin());
			req1.setString(5, en.getNom());
			req1.executeUpdate();
			sql2 = "SELECT numLecteur FROM Adherant WHERE login = ? and nom =?";
			req2 = connection.prepareStatement(sql2);
			req2.setString(1, login);
			req2.setString(2, nom);
			ResultSet rs= req2.executeQuery();
			
			while (rs.next()) {
				id= rs.getInt("numLecteur");
			}
			sql3 = "UPDATE enseignant SET departement = ? WHERE numLecteur=?";
			req3 = connection.prepareStatement(sql3);
			req3.setString(1, dep);
			req3.setInt(2, id);
			req3.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException("problème de modification d'un enseignant " + en.getNom(), e);
		}finally {
			close(req1);
			close(req2);
			close(req3);
		}
		System.out.println(req1);
	}
	
	/** Méthode getAllEnseignant permet de renvoyer tous les enseignants
	 * @return	list de tous les enseignants
	 * @throws DAOException Exception
	 */
	public List<Enseignant> getAllEnseignant() throws DAOException {
		List<Enseignant> list = new ArrayList<Enseignant>();
		
		Statement req = null;
		ResultSet rs = null;
		String sql;
		
		try {
			req = connection.createStatement();
			sql = "SELECT adherant.*,enseignant.departement as dep FROM adherant INNER JOIN enseignant on adherant.numLecteur=enseignant.numLecteur where adherant.isVerified=1";
			rs = req.executeQuery(sql);
			
			while (rs.next()) {
				int numLecteur = rs.getInt("numLecteur");
				String login = rs.getString("login");
				String mdp = rs.getString("mdp");
				String nom = rs.getString("nom");
				String dep = rs.getString("dep");
				Enseignant en = new Enseignant(login,mdp,nom,numLecteur,dep);
				list.add(en);
			}

			return list;		
		}catch (SQLException e) {
			throw new DAOException("problème d'affichage de la liste des enseignants ", e);
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

