package gestionBibliotheque.dao.document;

import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.model.documents.Exemplaire;

/** Class qui permet la manipulation des données des exemplaires
@author 	lahrach omar, mazer omar
@version 	juin 2020
*/

public class ExemplaireDAO {
	/** Connection permet de l'accée au base de données */
	private Connection connection;
	
	/** Un constructeur qui réalize une connexion
	 * @throws DAOException Exception
	 */
	public ExemplaireDAO() throws DAOException {
		
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
	
	/** Méthode addExemplaire permet d'ajouter un exemplaire
	 * @param exemp un 	Exemplaire
	 * @throws DAOException une Exception
	 */
	public void addExemplaire(Exemplaire exemp) throws DAOException{
		PreparedStatement req = null;
		String sql;
		try {
			sql = "INSERT INTO exemplaire (cote, etat, idLivre) VALUES (0, ?, ?) ";
			req = connection.prepareStatement(sql);
			req.setString(1, exemp.getEtat());
			req.setInt(2, exemp.getIdLivre());
			req.executeUpdate();
			
		} catch(SQLException e) {
			throw new DAOException("problème d'ajout d'un exemplaire ", e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permettant de supprimer un Exemplaire
	 * @param exemp Exemplaire
	 * @throws DAOException une Exception
	 */
	public void deleteExemplaire(Exemplaire exemp) throws DAOException{
		PreparedStatement req = null;
		String sql;
		try {
			sql = "DELETE FROM exemplaire WHERE cote=?";
			req = connection.prepareStatement(sql);
			req.setInt(1,exemp.getCote());
			req.executeUpdate();
			
		} catch(SQLException e) {
			throw new DAOException("problème de suppression d'un exemplaire ", e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode d'ajout
	 * @param n un entier
	 * @param arr table des entiers
	 * @param x un entier
	 * @return table des entiers
	 */
	public static Integer[] add(int n, Integer arr[], int x) 
    { 
        int i; 
        Integer newarr[] = new Integer[n + 1]; 
        for (i = 0; i < n; i++) 
            newarr[i] = arr[i]; 
        newarr[n] = x; 
        return newarr; 
    } 
	
	/** Méthode permettant de renvoyer tous les cote des exemplaires
	 * @return table des entiers
	 * @throws DAOException une Exception
	 */
	public Integer[] getAllCote() throws DAOException {
		Statement req = null;
		ResultSet rs = null;
		String sql;
		Integer[] tab= {};
		int i = 0;
		int cote = 0;
		try {
			req = connection.createStatement();
			sql = "SELECT cote FROM exemplaire WHERE cote NOT IN (SELECT cote FROM emprunt)";
			rs = req.executeQuery(sql);
			
			while (rs.next()) {
				cote = rs.getInt("cote");
				tab=add(i,tab,cote);
				i++;
			}

			return tab;
		}catch(SQLException e) {
			throw new DAOException("problème d'obtention d'un titre de exemplaire " , e);
		}finally {
			close(req);
		}
	}
	
	/** Méthode permattant de renvoyer tous les exemplaires
	 * @return list des objets exemplaires
	 * @throws DAOException une Exception
	 */
	public List<Object> getAllExemplaire() throws DAOException {
		List<Object> list = new ArrayList<Object>();
		
		Statement req = null;
		ResultSet rs = null;
		String sql;
		
		PreparedStatement req2 = null;
		String sql2;
		
		PreparedStatement req3 = null;
		String sql3;
		int n = 0;
		int m = 0;
		
		try {
			req = connection.createStatement();
			sql = "SELECT livre.*,exemplaire.* FROM livre INNER JOIN exemplaire ON exemplaire.idLivre=livre.id";
			rs = req.executeQuery(sql);
			
			while (rs.next()) {
				int cote = rs.getInt("cote");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String nomEditeur = rs.getString("nomEditeur");
				String etat = rs.getString("etat");
				String disponibilite="Disponible";
				
				
				sql2 = "SELECT COUNT(*) as nb FROM reservation where cote=?";
				req2 = connection.prepareStatement(sql2);
				req2.setInt(1,cote);
				ResultSet rs2= req2.executeQuery();
				
				while (rs2.next()) {
					n = rs2.getInt("nb");
				}
				
				sql3 = "SELECT COUNT(*) as nb FROM emprunt where cote=?";
				req3 = connection.prepareStatement(sql3);
				req3.setInt(1,cote);
				ResultSet rs3= req3.executeQuery();
				
				while (rs3.next()) {
					m = rs3.getInt("nb");
				}
				if(n==1) {
					disponibilite="Réservé";
				}
				else if(m==1) {
					disponibilite="Emprunté";
				}
				
				list.add(new Object[] {cote,titre,auteur,nomEditeur,etat,disponibilite});
			}

			return list;		
		}catch (SQLException e) {
			throw new DAOException("problème d'affichage de la liste des exemplaires ", e);
		}finally {
			close(req, rs);
		}
	}
	
	/** Méthode permettant de rechercher un exemplaire
	 * @param mot une chaîne de caractères
	 * @return list des objets exemplaires
	 * @throws DAOException une Exception
	 */
	public List<Object> searchExemplaire(String mot) throws DAOException {
		List<Object> list = new ArrayList<Object>();
		
		PreparedStatement req = null;
		String sql;
		
		PreparedStatement req2 = null;
		String sql2;
		
		PreparedStatement req3 = null;
		String sql3;
		int n = 0;
		int m = 0;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String date=dateFormat.format(cal.getTime());
		
		try {
			sql = "SELECT livre.*,exemplaire.* FROM livre INNER JOIN exemplaire ON exemplaire.idLivre=livre.id where livre.titre LIKE ? OR livre.auteur LIKE ? OR livre.nomEditeur LIKE ? ";
			req = connection.prepareStatement(sql);
			req.setString(1,"%"+mot+"%");
			req.setString(2,"%"+mot+"%");
			req.setString(3,"%"+mot+"%");
			
			ResultSet rs= req.executeQuery();
			
			
			while (rs.next()) {
				int cote = rs.getInt("cote");
				String titre = rs.getString("titre");
				String auteur = rs.getString("auteur");
				String nomEditeur = rs.getString("nomEditeur");
				String etat = rs.getString("etat");
				String disponibilite="Disponible";
				
				
				sql2 = "SELECT COUNT(*) as nb FROM reservation where cote=? and DATEDIFF(?,date)<2";
				req2 = connection.prepareStatement(sql2);
				req2.setInt(1,cote);
				req2.setString(2,date);
				ResultSet rs2= req2.executeQuery();
				
				while (rs2.next()) {
					n = rs2.getInt("nb");
				}
				
				sql3 = "SELECT COUNT(*) as nb FROM emprunt where cote=?";
				req3 = connection.prepareStatement(sql3);
				req3.setInt(1,cote);
				ResultSet rs3= req3.executeQuery();
				
				while (rs3.next()) {
					m = rs3.getInt("nb");
				}
				if(n==1) {
					disponibilite="Réservé";
				}
				else if(m==1) {
					disponibilite="Emprunté";
				}
				
				list.add(new Object[] {cote,titre,auteur,nomEditeur,etat,disponibilite});
			}

			if(mot=="") {
				return getAllExemplaire();
			}
			else {
				return list;
			}
		}catch (SQLException e) {
			throw new DAOException("problème de recherche de la liste des exemplaires ", e);
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

