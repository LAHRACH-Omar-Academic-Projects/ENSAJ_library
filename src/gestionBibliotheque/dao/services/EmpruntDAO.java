package gestionBibliotheque.dao.services;

import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.model.services.Emprunt;

/** Class qui permet la manipulation des données des emprunts
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class EmpruntDAO {
	/** Connection permet de l'accée au base de données */
	private Connection connection;
	
	/** Un constructeur qui réalize une connexion
	 * @throws DAOException Exception
	 */
	public EmpruntDAO() throws DAOException {
		
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
	
	/** Méthode permettant de renvoyer tous emprunts par numéro de lecteur
	 * @param nL un entier
	 * @return list des objets
	 * @throws DAOException une exception
	 */
	public List<Object> getAllEmprunt(int nL) throws DAOException {
		List<Object> list = new ArrayList<Object>();
		
		PreparedStatement req = null;
		String sql;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		
		try {
			
			sql = "SELECT *,DATEDIFF(dateFin, ?) AS delai FROM emprunt WHERE numLecteur=?";
			req = connection.prepareStatement(sql);
			req.setString(1, date);
			req.setInt(2, nL);
			ResultSet rs= req.executeQuery();
			
			while (rs.next()) {
				int cote = rs.getInt("cote");
				int numLecteur  = rs.getInt("numLecteur");
				String dateDebut = rs.getString("dateDebut");
				String dateFin = rs.getString("dateFin");
				int delai = rs.getInt("delai");
				
				list.add(new Object[] {cote,numLecteur,dateDebut,dateFin,delai});
			}

			return list;		
		}catch (SQLException e) {
			throw new DAOException("problème d'affichage de la liste des emprunts ", e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permettant de renvoyer tous les emprunts
	 * @return list des objets
	 * @throws DAOException une Exception
	 */
	public List<Object> getAllEmprunt() throws DAOException {
		List<Object> list = new ArrayList<Object>();
		
		PreparedStatement req = null;
		String sql;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		try {
			
			sql = "SELECT *,DATEDIFF(dateFin, ?) AS delai FROM emprunt";
			req = connection.prepareStatement(sql);
			req.setString(1, date);
			ResultSet rs= req.executeQuery();
			
			while (rs.next()) {
				int numLecteur = rs.getInt("numLecteur");
				int cote = rs.getInt("cote");
				String dateDebut = rs.getString("dateDebut");
				String dateFin = rs.getString("dateFin");
				int delai = rs.getInt("delai");
				
				list.add(new Object[] {numLecteur,cote,dateDebut,dateFin,delai});
			}

			return list;		
		}catch (SQLException e) {
			throw new DAOException("problème d'affichage de la liste des emprunts ", e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permettant d'ajouter un emprunt
	 * @param emp un Emprunt
	 * @return un boolean indiquant l'état de l'opération d'ajout
	 * @throws DAOException une exception
	 */
	public boolean addEmprunt(Emprunt emp) throws DAOException{
		PreparedStatement req1 = null;
		PreparedStatement req2 = null;
		PreparedStatement req3 = null;
		PreparedStatement req4 = null;
		
		String sql1,sql2,sql3,sql4;
		int nbEmp = 0;
		int n = 0;
		int nRes= 0;
		try {
			sql4 = "SELECT COUNT(*) as nRes FROM reservation WHERE numLecteur=? and cote=?";
			req4 = connection.prepareStatement(sql4);
			req4.setInt(1, emp.getNumLecteur());
			req4.setInt(2, emp.getCote());
			ResultSet rs4= req4.executeQuery();
			while (rs4.next()) {
				nRes= rs4.getInt("nRes");
			}
			
			sql1 = "SELECT COUNT(*) as nbEmp FROM emprunt WHERE numLecteur = ?";
			req1 = connection.prepareStatement(sql1);
			req1.setInt(1, emp.getNumLecteur());
			ResultSet rs1= req1.executeQuery();
			while (rs1.next()) {
				nbEmp= rs1.getInt("nbEmp");
			}
			sql2 = "SELECT COUNT(*) as n FROM reservation WHERE cote=?";
			req2 = connection.prepareStatement(sql2);
			req2.setInt(1, emp.getCote());
			ResultSet rs2= req2.executeQuery();
			while (rs2.next()) {
				n= rs2.getInt("n");
			}
			
			if((nbEmp<5 && n==0) || (n==1 && nRes==1 && nbEmp<5)) {
				sql3 = "INSERT INTO emprunt(numLecteur, cote, dateDebut, dateFin,idBibliothecaire) VALUES (?, ?, ?, ?, ?)";
				req3 = connection.prepareStatement(sql3);
				req3.setInt(1, emp.getNumLecteur());
				req3.setInt(2, emp.getCote());
				req3.setString(3, emp.getDateDebut());
				req3.setString(4, emp.getDateFin());
				req3.setInt(5, emp.getIdBibliothecaire());
				req3.executeUpdate();
				return true;
			}
			else {
				return false;
			}
		} catch(SQLException e) {
			throw new DAOException("problème d'ajout d'un emprunt ", e);
		}finally {
			close(req1);
			close(req2);
			close(req3);
		}
	}
	
	/** Méthode permettant de supprimer un emprunt
	 * @param emp un Emprunt
	 * @throws DAOException une Exception
	 */
	public void deleteEmprunt(Emprunt emp) throws DAOException{
		PreparedStatement req = null;
		String sql;
		try {

			sql = "DELETE FROM emprunt WHERE numLecteur = ? and cote = ?";
			req = connection.prepareStatement(sql);
			req.setInt(1, emp.getNumLecteur());
			req.setInt(2, emp.getCote());
			req.executeUpdate();
		} catch(SQLException e) {
			throw new DAOException("problème de suppression d'un emprunt ", e);
		}finally {
			close(req);
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
	@SuppressWarnings("unused")
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

