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
import gestionBibliotheque.dao.services.EmpruntDAO;
import gestionBibliotheque.model.services.Emprunt;
import gestionBibliotheque.model.utilisateurs.Bibliothecaire;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class EmpruntComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel header, content, footer;
	private JLabel image, numLecteur, cote, dateDebut, dateFin, delai, suppression;
	private Utilisateur user;

	private int empruntNumLecteur;
	private int empruntCote;
	private String empruntDateDebut;
	private String empruntDateFin;
	private int empruntDelai;

	public EmpruntComponent(Utilisateur user,
			int empruntCote,
			int empruntNumLecteur,
			String empruntDateDebut,
			String empruntDateFin,
			int empruntDelai,
			JPanel emprunt_panel) {
		
		this.user = user;
		this.empruntCote = empruntCote;
		this.empruntNumLecteur = empruntNumLecteur;
		this.empruntDateDebut = empruntDateDebut;
		this.empruntDateFin = empruntDateFin;
		this.empruntDelai = empruntDelai;
		
		Dimension d1 = new Dimension(210,290);
		Dimension d2 = new Dimension(110,110);
		Dimension d3 = new Dimension(210,140);
		Dimension d4 = new Dimension(210,40);
		
		Color borderColor;
		String imageSource;
		borderColor = new Color(255,200,0);
		imageSource = "images/livre.png";
		
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
		
		numLecteur = new JLabel("Num de Lecteur : " + String.valueOf(getEmpruntNumLecteur()));
		numLecteur.setHorizontalAlignment(SwingConstants.LEFT);
		numLecteur.setForeground(Color.DARK_GRAY);
		numLecteur.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font numLecteurFont = new Font("Arial", Font.PLAIN, 12);
		numLecteur.setFont(numLecteurFont);
		
		cote = new JLabel("Cote : " + getEmpruntCote());
		cote.setHorizontalAlignment(SwingConstants.LEFT);
		cote.setForeground(Color.DARK_GRAY);
		cote.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font coteFont = new Font("Arial", Font.PLAIN, 12);
		cote.setFont(coteFont);
		
		dateDebut = new JLabel("Date début : " + getEmpruntDateDebut());
		dateDebut.setHorizontalAlignment(SwingConstants.LEFT);
		dateDebut.setForeground(Color.DARK_GRAY);
		dateDebut.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font dateDebutFont = new Font("Arial", Font.PLAIN, 12);
		dateDebut.setFont(dateDebutFont);
		
		dateFin = new JLabel("Date fin : " + getEmpruntDateFin());
		dateFin.setHorizontalAlignment(SwingConstants.LEFT);
		dateFin.setForeground(Color.DARK_GRAY);
		dateFin.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font dateFinFont = new Font("Arial", Font.PLAIN, 12);
		dateFin.setFont(dateFinFont);

		delai = new JLabel("Délai : " + getEmpruntDelai());
		delai.setHorizontalAlignment(SwingConstants.LEFT);
		delai.setForeground(Color.DARK_GRAY);
		delai.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font delaiFont = new Font("Arial", Font.PLAIN, 12);
		delai.setFont(delaiFont);
		
		suppression = new JLabel("Supprimer");
		suppression.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						Emprunt emp = new Emprunt(getEmpruntCote(), getEmpruntNumLecteur(), getEmpruntDateDebut(), getEmpruntDateFin(), 0);
						
						int option = JOptionPane.showConfirmDialog(suppression, "Voulez vous supprimer cet emprunt?","Supprimer",JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.OK_OPTION){
							try {
								EmpruntDAO empDAO = new EmpruntDAO();
								empDAO.deleteEmprunt(emp);
								JOptionPane.showMessageDialog(suppression, "Cet emprunt a été supprimé avec succès!",  
			                            "Suppression avec succès",  
			                            JOptionPane.INFORMATION_MESSAGE);
				                emprunt_panel.removeAll();
				                emprunt_panel.getGraphics().clearRect(0, 0, emprunt_panel.getWidth(), emprunt_panel.getHeight());
				                emprunt_panel.revalidate();
				                emprunt_panel.repaint();
									try {
										EmpruntDAO empDAO1 = new EmpruntDAO();
										List<Object> obj = empDAO1.getAllEmprunt();
										for( int i=0; i<obj.size();i++) {
											Object[] o =(Object[]) obj.get(i);
				            				if(i%2==1) {
				            					emprunt_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
				            				}
				            				if(i%2==0) {
				            					emprunt_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
				            				}
											emprunt_panel.add(new EmpruntComponent(user,(int)o[0], (int)o[1], (String)o[2], (String)o[3], (int)o[4], emprunt_panel));
										}
									}catch(DAOException ex) {
										System.out.println(ex);
									}
							
						}catch(DAOException ex) {
							JOptionPane.showMessageDialog(suppression, "Vous ne pouvez pas supprimer un exemplaire emprunté!!",  
		                            "Erreur de suppression",  
		                            JOptionPane.WARNING_MESSAGE);
							System.out.println(ex);
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
		
		content.setLayout(new GridLayout(5,1));
		content.add(numLecteur);
		content.add(cote);
		content.add(dateDebut);
		content.add(dateFin);
		content.add(delai);
		if(user instanceof Bibliothecaire) {
			footer.add(suppression);
		}
		
		
		
	}
	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public int getEmpruntNumLecteur() {
		return empruntNumLecteur;
	}

	public void setEmpruntNumLecteur(int empruntNumLecteur) {
		this.empruntNumLecteur = empruntNumLecteur;
	}

	public int getEmpruntCote() {
		return empruntCote;
	}

	public void setEmpruntCote(int empruntCote) {
		this.empruntCote = empruntCote;
	}

	public String getEmpruntDateDebut() {
		return empruntDateDebut;
	}

	public void setEmpruntDateDebut(String empruntDateDebut) {
		this.empruntDateDebut = empruntDateDebut;
	}

	public String getEmpruntDateFin() {
		return empruntDateFin;
	}

	public void setEmpruntDateFin(String empruntDateFin) {
		this.empruntDateFin = empruntDateFin;
	}

	public int getEmpruntDelai() {
		return empruntDelai;
	}

	public void setEmpruntDelai(int empruntDelai) {
		this.empruntDelai = empruntDelai;
	}
	
	
}