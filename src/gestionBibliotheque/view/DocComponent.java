package gestionBibliotheque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.document.ExemplaireDAO;
import gestionBibliotheque.dao.document.RevueDAO;
import gestionBibliotheque.dao.services.ReservationDAO;
import gestionBibliotheque.dao.utilisateurs.AdherantDAO;
import gestionBibliotheque.model.documents.Exemplaire;
import gestionBibliotheque.model.documents.Revue;
import gestionBibliotheque.model.services.Reservation;
import gestionBibliotheque.model.utilisateurs.Adherant;
import gestionBibliotheque.model.utilisateurs.Bibliothecaire;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class DocComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel header, content, footer;
	private JLabel image, titre, cote, etat, auteur, nomEditeur, Periodicite, disponibilite,parution, reservation, suppression;
	private Utilisateur user;
	private String docType;
	private int docId;
	private String docTitre;
	private int docCote;
	private String docEtat;
	private String docAuteur;
	private String docNomEditeur;
	private String docPeriodicite;
	private String docDisponibilite;
	private String docDateParution;

	public DocComponent(Utilisateur user,
			String docType,
			int docCote,
			String docTitre,
			String docAuteur,
			String docNomEditeur ,
			String docEtat,
			String docDisponibilite,
			int docId,
			String docPeriodicite,
			String docDateParution,
			JPanel doc_panel,
			String mot) {
		
		this.user = user;
		this.docType = docType;
		this.docId = docId;
		this.docTitre = docTitre;
		this.docCote = docCote;
		this.docEtat = docEtat;
		this.docAuteur = docAuteur;
		this.docNomEditeur = docNomEditeur;
		this.docPeriodicite = docPeriodicite;
		this.docPeriodicite = docPeriodicite;
		this.docDateParution = docDateParution;
		this.docDisponibilite = docDisponibilite;
		
		Dimension d1 = new Dimension(210,290);
		Dimension d2 = new Dimension(110,120);
		Dimension d3 = new Dimension(210,130);
		Dimension d4 = new Dimension(210,40);
		
		Color borderColor;
		String imageSource;
		if(docType == "livre") {
			borderColor = new Color(0,120,255);
			imageSource = "images/livre.png";
		}
		else if(docType == "revue") {
			borderColor = new Color(50,250,200);
			imageSource = "images/revue.png";
		}
		else {
			borderColor = new Color(255,200,0);
			imageSource = "images/livre.png";
		}
		
		this.setLayout(new BorderLayout(0,0));
		this.setPreferredSize(d1);
		this.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, borderColor, new Point(5,5)), 
                new EmptyBorder(new Insets(2, 2, 2, 2))));
		
		
		
		header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setPreferredSize(d2);
		
		content = new JPanel();
		content.setBackground(Color.WHITE);
		content.setPreferredSize(d3);
		
		footer = new JPanel();
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
		footer.setBackground(new Color(255,255,255));
		footer.setPreferredSize(d4);
		
		this.add(header, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
		
		image  = new JLabel("");
		image.setPreferredSize(d2);
		BufferedImage livreImage = null;
		try {
			livreImage = ImageIO.read(new File(imageSource));
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		}
		Image dlivreImage = livreImage.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		ImageIcon livreIcon = new ImageIcon(dlivreImage);
		image.setIcon(livreIcon);
		header.add(image);
				
		titre = new JLabel(getDocTitre());
		titre.setHorizontalAlignment(SwingConstants.CENTER);
		titre.setForeground(Color.DARK_GRAY);
		Font titreFont = new Font("Arial", Font.BOLD, 18);
		titre.setFont(titreFont);
		
		cote = new JLabel("Cote : " + String.valueOf(getDocCote()));
		cote.setHorizontalAlignment(SwingConstants.LEFT);
		cote.setForeground(Color.DARK_GRAY);
		cote.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font coteFont = new Font("Arial", Font.PLAIN, 12);
		cote.setFont(coteFont);
		
		etat = new JLabel("Etat : " + getDocEtat());
		etat.setHorizontalAlignment(SwingConstants.LEFT);
		etat.setForeground(Color.DARK_GRAY);
		etat.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font etatFont = new Font("Arial", Font.PLAIN, 12);
		etat.setFont(etatFont);
		
		auteur = new JLabel("Auteur : " + getDocAuteur());
		auteur.setHorizontalAlignment(SwingConstants.LEFT);
		auteur.setForeground(Color.DARK_GRAY);
		auteur.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font auteurFont = new Font("Arial", Font.PLAIN, 12);
		auteur.setFont(auteurFont);

		nomEditeur = new JLabel("Nom de l'editeur : " + getDocNomEditeur());
		nomEditeur.setHorizontalAlignment(SwingConstants.LEFT);
		nomEditeur.setForeground(Color.DARK_GRAY);
		nomEditeur.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font nomEditeurFont = new Font("Arial", Font.PLAIN, 12);
		nomEditeur.setFont(nomEditeurFont);
		
		Periodicite = new JLabel("Périodicité : " + getDocPeriodicite());
		Periodicite.setForeground(Color.DARK_GRAY);
		Periodicite.setHorizontalAlignment(SwingConstants.LEFT);
		Periodicite.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font PeriodiciteFont = new Font("Arial", Font.PLAIN, 12);
		Periodicite.setFont(PeriodiciteFont);

		parution = new JLabel("Date de parution : " + getDocDateParution());
		parution.setForeground(Color.DARK_GRAY);
		parution.setHorizontalAlignment(SwingConstants.LEFT);
		parution.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font parutionFont = new Font("Arial", Font.PLAIN, 12);
		parution.setFont(parutionFont);
		
		disponibilite = new JLabel(getDocDisponibilite());
		disponibilite.setHorizontalAlignment(SwingConstants.LEFT);
		disponibilite.setForeground(new Color(0,120,255));
		disponibilite.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font disponibiliteFont = new Font("Verdana", Font.PLAIN, 12);
		disponibilite.setFont(disponibiliteFont);
		
		reservation = new JLabel("Réserver");
		reservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean flag = true;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				String date=dateFormat.format(cal.getTime());
				try {
					AdherantDAO adDAO = new AdherantDAO();
					Adherant ad = adDAO.getAdherant(user.getLogin(),user.getMdp());
					Reservation r = new Reservation(ad.getNumLecteur(),getDocCote(),date);
					
					int option = JOptionPane.showConfirmDialog(reservation, "Voulez vous réserver ce exemplaire?","Réserver",JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.OK_OPTION){
						try {
							ReservationDAO rDAO = new ReservationDAO();
							flag=rDAO.addReservation(r);  
							if(flag==false) {
			                	JOptionPane.showMessageDialog(reservation, "Vous ne pouvez pas réserver plus que le nombre maximal!!",  
			                            "Erreur de réservation",  
			                            JOptionPane.WARNING_MESSAGE);
			                }
							else {
				                doc_panel.removeAll();
				                doc_panel.getGraphics().clearRect(0, 0, doc_panel.getWidth(), doc_panel.getHeight());
				                doc_panel.revalidate();
				                doc_panel.repaint();
				                
				              
				                
				                if(mot=="") {
									try {
										ExemplaireDAO exempDAO = new ExemplaireDAO();
										List<Object> obj = exempDAO.getAllExemplaire();
										for( int i=0; i<obj.size();i++) {
											Object[] o =(Object[]) obj.get(i);
				            				if(i%2==1) {
				            					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
				            				}
				            				if(i%2==0) {
				            					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
				            				}
											doc_panel.add(new DocComponent(user,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",doc_panel,mot));
										}
										
									
									}catch(DAOException ex) {
										System.out.println(ex);
									}
				
				                }else {
				                    	try {
				                			ExemplaireDAO exempDAO = new ExemplaireDAO();
				                			List<Object> obj = exempDAO.searchExemplaire(mot);
				                			for( int i=0; i<obj.size();i++) {
				                				Object[] o =(Object[]) obj.get(i);
				                				if(i%2==1) {
				                					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
				                				}
				                				if(i%2==0) {
				                					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
				                				}
				                				doc_panel.add(new DocComponent(user,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",doc_panel,mot));
				                			}
				                			
				                		
				                		}catch(DAOException ex) {
				                			System.out.println(ex);
				                		}
				                }
							}
						}catch(DAOException ex) {
							System.out.println(ex);
						}
					
					}
				}catch(DAOException ex) {
					System.out.println(ex);
				}
			}
		});
		BufferedImage reservationImage = null;
		try {
			reservationImage = ImageIO.read(new File("images/reserver.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dreservationImage = reservationImage.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		ImageIcon reservationIcon = new ImageIcon(dreservationImage);
		reservation.setIcon(reservationIcon);
		reservation.setCursor(new Cursor(Cursor.HAND_CURSOR));
		reservation.setIconTextGap(5);
		reservation.setHorizontalAlignment(SwingConstants.LEFT);
		reservation.setForeground(new Color(0,255,200));
		reservation.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		Font reservationFont = new Font("Verdana", Font.PLAIN, 12);
		reservation.setFont(reservationFont);
		
		
		suppression = new JLabel("Supprimer");
		suppression.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(docType=="livre") {
						Exemplaire exemp = new Exemplaire(getDocCote(),getDocEtat(),getDocId());
						int option = JOptionPane.showConfirmDialog(suppression, "Voulez vous supprimer cet exemplaire?","Supprimer",JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.OK_OPTION){
							try {
								ExemplaireDAO exDAO = new ExemplaireDAO();
								exDAO.deleteExemplaire(exemp);
								JOptionPane.showMessageDialog(suppression, "Cet exemplaire a été supprimé avec succès!",  
			                            "Suppression avec succès",  
			                            JOptionPane.INFORMATION_MESSAGE);
				                doc_panel.removeAll();
				                doc_panel.getGraphics().clearRect(0, 0, doc_panel.getWidth(), doc_panel.getHeight());
				                doc_panel.revalidate();
				                doc_panel.repaint();
				                if(mot=="") {
									try {
										ExemplaireDAO exempDAO = new ExemplaireDAO();
										List<Object> obj = exempDAO.getAllExemplaire();
										for( int i=0; i<obj.size();i++) {
											Object[] o =(Object[]) obj.get(i);
				            				if(i%2==1) {
				            					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
				            				}
				            				if(i%2==0) {
				            					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
				            				}
											doc_panel.add(new DocComponent(user,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",doc_panel,mot));
										}
										
									
									}catch(DAOException ex) {
										System.out.println(ex);
									}
				
				                }else {
				                    	try {
				                			ExemplaireDAO exempDAO = new ExemplaireDAO();
				                			List<Object> obj = exempDAO.searchExemplaire(mot);
				                			for( int i=0; i<obj.size();i++) {
				                				Object[] o =(Object[]) obj.get(i);
				                				if(i%2==1) {
				                					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
				                				}
				                				if(i%2==0) {
				                					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
				                				}
				                				doc_panel.add(new DocComponent(user,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",doc_panel,mot));
				                			}
				                			
				                		
				                		}catch(DAOException ex) {
				                			System.out.println(ex);
				                		}
				                }
							
						}catch(DAOException ex) {
							JOptionPane.showMessageDialog(suppression, "Vous ne pouvez pas supprimer un exemplaire emprunté!!",  
		                            "Erreur de suppression",  
		                            JOptionPane.WARNING_MESSAGE);
							System.out.println(ex);
						}
					}
				}else {
					Revue rv = new Revue(getDocId(),getDocTitre(),getDocPeriodicite(),getDocDateParution());
					int option = JOptionPane.showConfirmDialog(suppression, "Voulez vous supprimer cete revue?","Supprimer",JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.OK_OPTION){
						try {
							RevueDAO rvDAO = new RevueDAO();
							rvDAO.deleteRevue(rv);
							JOptionPane.showMessageDialog(suppression, "Cette revue a été supprimé avec succès!",  
		                            "Suppression avec succès",  
		                            JOptionPane.INFORMATION_MESSAGE);
			                doc_panel.removeAll();
			                doc_panel.getGraphics().clearRect(0, 0, doc_panel.getWidth(), doc_panel.getHeight());
			                doc_panel.revalidate();
			                doc_panel.repaint();
			                if(mot=="") {
								try {
									RevueDAO revueDAO = new RevueDAO();
									List<Revue> obj = revueDAO.getAllRevue();
									for( int i=0; i<obj.size();i++) {
										Revue o = obj.get(i);
										doc_panel.add(new DocComponent(user,"revue",0,o.getTitre(),"","","","",o.getId(),o.getPeriodicite(),o.getDateParution(),doc_panel,mot));									}
								}catch(DAOException ex) {
									System.out.println(ex);
								}
			
			                }else {
								try {
									RevueDAO revueDAO = new RevueDAO();
									List<Revue> obj = revueDAO.searchRevue(mot);
									for( int i=0; i<obj.size();i++) {
										Revue o = obj.get(i);
										doc_panel.add(new DocComponent(user,"revue",0,o.getTitre(),"","","","",o.getId(),o.getPeriodicite(),o.getDateParution(),doc_panel,mot));									}
								}catch(DAOException ex) {
									System.out.println(ex);
								}
			                }
						
					}catch(DAOException ex) {
						System.out.println(ex);
					}
				}
				}
			}
		});
		BufferedImage suppressionImage = null;
		try {
			suppressionImage = ImageIO.read(new File("images/supprimer.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dsuppressionImage = suppressionImage.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		ImageIcon suppressionIcon = new ImageIcon(dsuppressionImage);
		suppression.setIcon(suppressionIcon);
		suppression.setCursor(new Cursor(Cursor.HAND_CURSOR));
		suppression.setHorizontalAlignment(SwingConstants.LEFT);
		suppression.setForeground(new Color(255,80,0));
		suppression.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font suppressionFont = new Font("Verdana", Font.PLAIN, 12);
		suppression.setFont(suppressionFont);
		
		
		content.add(titre);
		
		if(getDocType().equals("livre") && user instanceof Adherant) {					
			content.setLayout(new GridLayout(5,1));
			content.add(cote);
			content.add(etat);
			content.add(auteur);
			content.add(nomEditeur);
			if(getDocDisponibilite()!="Réservé" && getDocDisponibilite()!="Emprunté" ) {
				footer.add(reservation);
			}
			footer.add(disponibilite);
		}
		else if(getDocType().equals("revue") && user instanceof Adherant) {
			content.setLayout(new GridLayout(3,1));
			content.add(Periodicite);
			content.add(parution);
		}
		else if(getDocType().equals("livre") && user instanceof Bibliothecaire) {
			content.setLayout(new GridLayout(5,1));
			content.add(cote);
			content.add(etat);
			content.add(auteur);
			content.add(nomEditeur);	
			footer.add(suppression);
		}
		else if(getDocType().equals("revue") && user instanceof Bibliothecaire) {
			content.setLayout(new GridLayout(3,1));
			content.add(Periodicite);
			content.add(parution);
			footer.add(suppression);
		}
		
		
	}


	public Utilisateur getUser() {
		return user;
	}




	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	

	public String getDocType() {
		return docType;
	}




	public void setDocType(String docType) {
		this.docType = docType;
	}




	public int getDocId() {
		return docId;
	}




	public void setDocId(int docId) {
		this.docId = docId;
	}




	public String getDocTitre() {
		return docTitre;
	}




	public void setDocTitre(String docTitre) {
		this.docTitre = docTitre;
	}




	public int getDocCote() {
		return docCote;
	}




	public void setDocCote(int docCote) {
		this.docCote = docCote;
	}




	public String getDocEtat() {
		return docEtat;
	}




	public void setDocEtat(String docEtat) {
		this.docEtat = docEtat;
	}




	public String getDocAuteur() {
		return docAuteur;
	}




	public void setDocAuteur(String docAuteur) {
		this.docAuteur = docAuteur;
	}




	public String getDocNomEditeur() {
		return docNomEditeur;
	}




	public void setDocNomEditeur(String docNomEditeur) {
		this.docNomEditeur = docNomEditeur;
	}




	public String getDocPeriodicite() {
		return docPeriodicite;
	}




	public void setDocPeriodicite(String docPeriodicite) {
		this.docPeriodicite = docPeriodicite;
	}


	public String getDocDisponibilite() {
		return docDisponibilite;
	}


	public void setDocDisponibilite(String docDisponibilite) {
		this.docDisponibilite = docDisponibilite;
	}


	public String getDocDateParution() {
		return docDateParution;
	}


	public void setDocDateParution(String docDateParution) {
		this.docDateParution = docDateParution;
	}
}