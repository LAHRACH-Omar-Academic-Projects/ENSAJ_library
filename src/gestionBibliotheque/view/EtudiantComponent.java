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
import gestionBibliotheque.dao.utilisateurs.AdherantDAO;
import gestionBibliotheque.dao.utilisateurs.EtudiantDAO;
import gestionBibliotheque.model.utilisateurs.Adherant;
import gestionBibliotheque.model.utilisateurs.Etudiant;

public class EtudiantComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel header, content, footer;
	private JLabel image, login, nom, numLecteur, cne, adresse, filliere ,modification, suppression;
	private String etdLogin;
	private String etdMdp;
	private String etdNom;
	private int etdNumLecteur;
	private String etdCne;
	private String etdAdresse;
	private String etdFilliere;
	private String etdDep;

	public EtudiantComponent(String etdLogin, String etdMdp, String etdNom, int etdNumLecteur, String etdCne, String etdAdresse, String etdFilliere, JPanel etd_panel) {
		
		this.etdLogin = etdLogin;
		this.etdNom = etdNom;
		this.etdMdp = etdMdp;
		this.etdNumLecteur = etdNumLecteur;
		this.etdCne = etdCne;
		this.etdAdresse = etdAdresse;
		this.etdFilliere = etdFilliere;
		
		Dimension d1 = new Dimension(210,290);
		Dimension d2 = new Dimension(110,120);
		Dimension d3 = new Dimension(210,130);
		Dimension d4 = new Dimension(210,40);
		
		Color borderColor;
		String imageSource;
		borderColor = new Color(0,120,255);
		imageSource = "images/etudiant.png";
		
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
				
		login = new JLabel(getEtdLogin());
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setForeground(Color.DARK_GRAY);
		Font loginFont = new Font("Arial", Font.BOLD, 12);
		login.setFont(loginFont);
		
		nom = new JLabel(getEtdNom());
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setForeground(Color.DARK_GRAY);
		Font nomFont = new Font("Arial", Font.BOLD, 12);
		nom.setFont(nomFont);
		
		numLecteur = new JLabel("Numéro de lecteur : " + String.valueOf(getEtdNumLecteur()));
		numLecteur.setHorizontalAlignment(SwingConstants.CENTER);
		numLecteur.setForeground(Color.DARK_GRAY);
		Font numLecteurFont = new Font("Arial", Font.BOLD, 12);
		numLecteur.setFont(numLecteurFont);
		
		cne = new JLabel("Cne : " + getEtdCne());
		cne.setHorizontalAlignment(SwingConstants.CENTER);
		cne.setForeground(Color.DARK_GRAY);
		Font cneFont = new Font("Arial", Font.BOLD, 12);
		cne.setFont(cneFont);	
		
		adresse = new JLabel("Adresse : " + getEtdAdresse());
		adresse.setHorizontalAlignment(SwingConstants.CENTER);
		adresse.setForeground(Color.DARK_GRAY);
		Font adresseFont = new Font("Arial", Font.BOLD, 12);
		adresse.setFont(adresseFont);
		
		filliere = new JLabel("Filliere : " + getEtdFilliere());
		filliere.setHorizontalAlignment(SwingConstants.CENTER);
		filliere.setForeground(Color.DARK_GRAY);
		Font filliereFont = new Font("Arial", Font.BOLD, 12);
		filliere.setFont(filliereFont);
		
		modification = new JLabel("Modifier");
		modification.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Etudiant etd = new Etudiant(getEtdLogin(), getEtdMdp(), getEtdNom(), getEtdNumLecteur(), getEtdCne(), getEtdAdresse(), getEtdFilliere());
				Modifier mod = new Modifier("etudiant", etd_panel, etd);
				mod.setVisible(true);
			}
		});
		BufferedImage modificationImage = null;
		try {
			modificationImage = ImageIO.read(new File("images/modifier.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dmodificationImage = modificationImage.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		ImageIcon modificationIcon = new ImageIcon(dmodificationImage);
		modification.setIcon(modificationIcon);
		modification.setCursor(new Cursor(Cursor.HAND_CURSOR));
		modification.setIconTextGap(5);
		modification.setHorizontalAlignment(SwingConstants.LEFT);
		modification.setForeground(new Color(200,150,0));
		modification.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		Font modificationFont = new Font("Verdana", Font.PLAIN, 12);
		modification.setFont(modificationFont);
		
		suppression = new JLabel("supprimer");
		suppression.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(suppression, "Voulez vous supprimer cet Etudiant?","Supprimer",JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION){
					try {
						AdherantDAO adhDAO = new AdherantDAO();
						Adherant ad = adhDAO.getAdherant(getEtdLogin(),getEtdMdp());
						adhDAO.deleteAdherant(ad);
						JOptionPane.showMessageDialog(suppression, "Cet etudiant a été supprimé avec succès!",  
	                            "Suppression avec succès",  
	                            JOptionPane.INFORMATION_MESSAGE);
						try {
							etd_panel.removeAll();
							etd_panel.getGraphics().clearRect(0, 0, etd_panel.getWidth(), etd_panel.getHeight());
							etd_panel.revalidate();
							etd_panel.repaint();
							EtudiantDAO adhDAO1 = new EtudiantDAO();
							List<Etudiant> obj = adhDAO1.getAllEtudiant();
							for( int i=0; i<obj.size();i++) {
								Etudiant o =(Etudiant)obj.get(i);
			    				if(i%2==1) {
			    					etd_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
			    				}
			    				if(i%2==0) {
			    					etd_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
			    				}
			    				etd_panel.add(new EtudiantComponent(o.getLogin(), o.getMdp(), o.getNom(), o.getNumLecteur(), o.getCne(), o.getAdresse(), o.getFilliere(),  etd_panel));
							}	
						}catch(DAOException ex) {
							System.out.println(ex);
						}
					}catch(DAOException daoe) {
						JOptionPane.showMessageDialog(suppression, "Vous ne pouvez pas supprimer un etudiant qui a un emprunt",  
	                            "Erreur de suppression",  
	                            JOptionPane.WARNING_MESSAGE);
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
		suppression.setIconTextGap(5);
		suppression.setHorizontalAlignment(SwingConstants.LEFT);
		suppression.setForeground(new Color(255,120,0));
		suppression.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		Font suppressionFont = new Font("Verdana", Font.PLAIN, 12);
		suppression.setFont(suppressionFont);
		
		content.setLayout(new GridLayout(6,1));
		content.add(nom);
		content.add(login);
		content.add(numLecteur);
		content.add(cne);
		content.add(adresse);
		content.add(filliere);
		footer.add(modification);
		footer.add(suppression);
		
	}

	public String getEtdLogin() {
		return etdLogin;
	}

	public void setEtdLogin(String etdLogin) {
		this.etdLogin = etdLogin;
	}

	public String getEtdMdp() {
		return etdMdp;
	}

	public void setEtdMdp(String etdMdp) {
		this.etdMdp = etdMdp;
	}

	public String getEtdNom() {
		return etdNom;
	}

	public void setEtdNom(String etdNom) {
		this.etdNom = etdNom;
	}

	public int getEtdNumLecteur() {
		return etdNumLecteur;
	}

	public void setEtdNumLecteur(int etdNumLecteur) {
		this.etdNumLecteur = etdNumLecteur;
	}

	public String getEtdCne() {
		return etdCne;
	}

	public void setEtdCne(String etdCne) {
		this.etdCne = etdCne;
	}

	public String getEtdAdresse() {
		return etdAdresse;
	}

	public void setEtdAdresse(String etdAdresse) {
		this.etdAdresse = etdAdresse;
	}

	public String getEtdFilliere() {
		return etdFilliere;
	}

	public void setEtdFilliere(String etdFilliere) {
		this.etdFilliere = etdFilliere;
	}

	public String getEtdDep() {
		return etdDep;
	}

	public void setEtdDep(String etdDep) {
		this.etdDep = etdDep;
	}
	
		
}