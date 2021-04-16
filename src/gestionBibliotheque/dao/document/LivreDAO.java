package gestionBibliotheque.dao.document;

import java.util.*;
import java.sql.*;
import java.io.*;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.model.documents.Livre;

/** Class qui permet la manipulation des données des livres
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class LivreDAO {
	/** Connection permet de l'accée au base de données */
	private Connection connection;
	
	/** Un constructeur qui réalize une connexion
	 * @throws DAOException Exception
	 */
	public LivreDAO() throws DAOException {
		
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
	
	/** Méthode addLivre permet d'ajouter un Livre
	 * @param l un 	Livre
	 * @throws DAOException une Exception
	 */
	public void addLivre(Livre l) throws DAOException{
		PreparedStatement req = null;
		String sql;
		try {
			sql = "INSERT INTO livre (id, titre, nomEditeur, auteur) VALUES (0, ?, ?, ?) ";
			req = connection.prepareStatement(sql);
			req.setString(1, l.getTitre());
			req.setString(2, l.getNomEditeur());
			req.setString(3, l.getAuteur());
			req.executeUpdate();
			
		} catch(SQLException e) {
			throw new DAOException("problème d'ajout d'un livre " + l.getTitre(), e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permettant de supprimer un Livre
	 * @param l Livre
	 * @throws DAOException une Exception
	 */
	public void deleteLivre(Livre l) throws DAOException{
		PreparedStatement req = null;
		String sql;
		try {
			sql = "DELETE FROM livre WHERE titre=? and nomEditeur=? and auteur=?";
			req = connection.prepareStatement(sql);
			req.setString(1, l.getTitre());
			req.setString(2, l.getNomEditeur());
			req.setString(3, l.getAuteur());
			req.executeUpdate();
			
		} catch(SQLException e) {
			throw new DAOException("problème de suppression d'un livre " + l.getTitre(), e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permettant de renvoyer l'id d'un livre par titre
	 * @param titre une chaîne de caractères
	 * @return l'id du livre
	 * @throws DAOException une Exception
	 */
	public int getIdByTitre(String titre) throws DAOException{
		PreparedStatement req = null;
		ResultSet rs = null;
		int id=0;
		String sql;
		try {
			
			sql = "SELECT id FROM livre WHERE titre = ? ";
			req = connection.prepareStatement(sql);
			req.setString(1, titre);
			rs = req.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
			
			return id;
		} catch(SQLException e) {
			throw new DAOException("problème de recherche d'un id de livre ", e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode d'ajout
	 * @param n un entier
	 * @param arr table de chaîne de caractères
	 * @param x une chaîne de caractères
	 * @return table de chaîne de caractères
	 */
	public static String[] add(int n, String arr[], String x) 
    { 
        int i; 
        String newarr[] = new String[n + 1]; 
        for (i = 0; i < n; i++) 
            newarr[i] = arr[i]; 
        newarr[n] = x; 
        return newarr; 
    } 
	
	/** Méthode permettant de renvoyer tous les titres de tous les livres
	 * @return table de chaîne de caractères
	 * @throws DAOException une Exception
	 */
	public String[] getAllTitre() throws DAOException {
		Statement req = null;
		ResultSet rs = null;
		String sql;
		String[] tab= {};
		String titre = "";
		int i = 0;
		try {
			req = connection.createStatement();
			sql = "SELECT DISTINCT titre FROM livre ";
			rs = req.executeQuery(sql);
			
			while (rs.next()) {
				titre = rs.getString("titre");
				tab=add(i,tab,titre);
				i++;
			}

			return tab;
		}catch(SQLException e) {
			throw new DAOException("problème d'obtention d'un titre de livre " , e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permettant de renvoyer tous les livres
	 * @return list des livres
	 * @throws DAOException une Exception
	 */
	public List<Livre> getAllLivre() throws DAOException {
		List<Livre> list = new ArrayList<Livre>();
		
		Statement req = null;
		ResultSet rs = null;
		String sql;
		
		try {
			req = connection.createStatement();
			sql = "SELECT * FROM livre ";
			rs = req.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String nomEditeur = rs.getString("nomEditeur");
				String auteur = rs.getString("auteur");
				
				Livre l = new Livre(id,titre,nomEditeur,auteur);
				list.add(l);
			}

			return list;		
		}catch (SQLException e) {
			throw new DAOException("problème d'affichage de la liste des livres ", e);
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

