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
import gestionBibliotheque.dao.utilisateurs.EnseignantDAO;
import gestionBibliotheque.model.utilisateurs.Adherant;
import gestionBibliotheque.model.utilisateurs.Enseignant;

public class EnseignantComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel header, content, footer;
	private JLabel image, login, nom, numLecteur, dep ,modification, suppression;
	private String ensLogin;
	private String ensMdp;
	private String ensNom;
	private int ensNumLecteur;
	private String ensDep;

	public EnseignantComponent(String ensLogin, String ensMdp, String ensNom, int ensNumLecteur, String ensDep, JPanel ens_panel) {
		
		this.ensLogin = ensLogin;
		this.ensNom = ensNom;
		this.ensMdp = ensMdp;
		this.ensNumLecteur = ensNumLecteur;
		this.ensDep = ensDep;
		
		Dimension d1 = new Dimension(210,290);
		Dimension d2 = new Dimension(110,120);
		Dimension d3 = new Dimension(210,130);
		Dimension d4 = new Dimension(210,40);
		
		Color borderColor;
		String imageSource;
		borderColor = new Color(0,120,255);
		imageSource = "images/enseignant.png";
		
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
				
		login = new JLabel(getEnsLogin());
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setForeground(Color.DARK_GRAY);
		Font loginFont = new Font("Arial", Font.BOLD, 12);
		login.setFont(loginFont);
		
		nom = new JLabel(getEnsNom());
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setForeground(Color.DARK_GRAY);
		Font nomFont = new Font("Arial", Font.BOLD, 12);
		nom.setFont(nomFont);
		
		numLecteur = new JLabel("Numéro de lecteur : " + String.valueOf(getEnsNumLecteur()));
		numLecteur.setHorizontalAlignment(SwingConstants.CENTER);
		numLecteur.setForeground(Color.DARK_GRAY);
		Font numLecteurFont = new Font("Arial", Font.BOLD, 12);
		numLecteur.setFont(numLecteurFont);
		
		dep = new JLabel("Département : " + getEnsDep());
		dep.setHorizontalAlignment(SwingConstants.CENTER);
		dep.setForeground(Color.DARK_GRAY);
		Font depFont = new Font("Arial", Font.BOLD, 12);
		dep.setFont(depFont);		
		
		modification = new JLabel("Modifier");
		modification.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Enseignant ens = new Enseignant(getEnsLogin(), getEnsMdp(), getEnsNom(), getEnsNumLecteur(), getEnsDep());
				Modifier mod = new Modifier("enseignant", ens_panel, ens);
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
				int option = JOptionPane.showConfirmDialog(suppression, "Voulez vous supprimer cet Enseignant?","Supprimer",JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION){
					try {
						AdherantDAO adhDAO = new AdherantDAO();
						Adherant ad = adhDAO.getAdherant(getEnsLogin(),getEnsMdp());
						adhDAO.deleteAdherant(ad);
						JOptionPane.showMessageDialog(suppression, "Cet enseignant a été supprimé avec succès!",  
	                            "Suppression avec succès",  
	                            JOptionPane.INFORMATION_MESSAGE);
						try {
							ens_panel.removeAll();
							ens_panel.getGraphics().clearRect(0, 0, ens_panel.getWidth(), ens_panel.getHeight());
							ens_panel.revalidate();
							ens_panel.repaint();
							EnseignantDAO adhDAO1 = new EnseignantDAO();
							List<Enseignant> obj = adhDAO1.getAllEnseignant();
							for( int i=0; i<obj.size();i++) {
								Enseignant o =(Enseignant)obj.get(i);
			    				if(i%2==1) {
			    					ens_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
			    				}
			    				if(i%2==0) {
			    					ens_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
			    				}
			    				ens_panel.add(new EnseignantComponent(o.getLogin(), o.getMdp(), o.getNom(), o.getNumLecteur(), o.getDepartement(), ens_panel));
							}	
						}catch(DAOException ex) {
							System.out.println(ex);
						}
					}catch(DAOException daoe) {
						JOptionPane.showMessageDialog(suppression, "Vous ne pouvez pas supprimer un enseignant qui a un emprunt",  
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
		
		content.setLayout(new GridLayout(4,1));
		content.add(nom);
		content.add(login);
		content.add(numLecteur);
		content.add(dep);
		footer.add(modification);
		footer.add(suppression);
		
	}

	public String getEnsLogin() {
		return ensLogin;
	}

	public void setEnsLogin(String ensLogin) {
		this.ensLogin = ensLogin;
	}

	public String getEnsMdp() {
		return ensMdp;
	}

	public void setEnsMdp(String ensMdp) {
		this.ensMdp = ensMdp;
	}

	public String getEnsNom() {
		return ensNom;
	}

	public void setEnsNom(String ensNom) {
		this.ensNom = ensNom;
	}

	public int getEnsNumLecteur() {
		return ensNumLecteur;
	}

	public void setEnsNumLecteur(int ensNumLecteur) {
		this.ensNumLecteur = ensNumLecteur;
	}

	public String getEnsDep() {
		return ensDep;
	}

	public void setEnsDep(String ensDep) {
		this.ensDep = ensDep;
	}
		
}