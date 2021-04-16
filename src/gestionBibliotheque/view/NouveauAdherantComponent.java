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
import gestionBibliotheque.model.utilisateurs.Adherant;

public class NouveauAdherantComponent extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel header, content, footer;
	private JLabel image, login, nom ,acceptation, refus;
	private String adhLogin;
	private String adhMdp;
	private String adhNom;

	public NouveauAdherantComponent(String adhLogin, String adhMdp, String adhNom, JPanel adh_panel) {
		
		this.adhLogin = adhLogin;
		this.adhMdp = adhMdp;
		this.adhNom = adhNom;
		
		Dimension d1 = new Dimension(210,290);
		Dimension d2 = new Dimension(110,120);
		Dimension d3 = new Dimension(210,130);
		Dimension d4 = new Dimension(210,40);
		
		Color borderColor;
		String imageSource;
		borderColor = new Color(0,120,255);
		imageSource = "images/user_b.png";
		
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
				
		login = new JLabel(getAdhLogin());
		login.setHorizontalAlignment(SwingConstants.CENTER);
		login.setForeground(Color.DARK_GRAY);
		Font loginFont = new Font("Arial", Font.BOLD, 12);
		login.setFont(loginFont);
		
		nom = new JLabel(getAdhNom());
		nom.setHorizontalAlignment(SwingConstants.CENTER);
		nom.setForeground(Color.DARK_GRAY);
		Font nomFont = new Font("Arial", Font.BOLD, 12);
		nom.setFont(nomFont);
		
		
		acceptation = new JLabel("Accepter");
		acceptation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					AdherantDAO adhDAO = new AdherantDAO();
					Adherant ad = adhDAO.getAdherant(getAdhLogin(),getAdhMdp());
					System.out.println(ad.getLogin() + ad.getMdp() +  ad.getNom());
					adhDAO.verifyAdherant(ad);
					try {
						adh_panel.removeAll();
						adh_panel.getGraphics().clearRect(0, 0, adh_panel.getWidth(), adh_panel.getHeight());
						adh_panel.revalidate();
						adh_panel.repaint();
						AdherantDAO adhDAO1 = new AdherantDAO();
						List<Adherant> obj = adhDAO1.getNotVerifiedAdherant();
						for( int i=0; i<obj.size();i++) {
							Adherant o =(Adherant)obj.get(i);
		    				if(i%2==1) {
		    					adh_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
		    				}
		    				if(i%2==0) {
		    					adh_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
		    				}
		    				adh_panel.add(new NouveauAdherantComponent(o.getLogin(),o.getMdp(),o.getNom(),adh_panel));
						}	
					}catch(DAOException ex) {
						System.out.println(ex);
					}
				}catch(DAOException daoe) {
					daoe.getMessage();
				}
			}
		});
		BufferedImage acceptationImage = null;
		try {
			acceptationImage = ImageIO.read(new File("images/accepter.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dacceptationImage = acceptationImage.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		ImageIcon acceptationIcon = new ImageIcon(dacceptationImage);
		acceptation.setIcon(acceptationIcon);
		acceptation.setCursor(new Cursor(Cursor.HAND_CURSOR));
		acceptation.setIconTextGap(5);
		acceptation.setHorizontalAlignment(SwingConstants.LEFT);
		acceptation.setForeground(new Color(0,255,120));
		acceptation.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		Font acceptationFont = new Font("Verdana", Font.PLAIN, 12);
		acceptation.setFont(acceptationFont);
		
		refus = new JLabel("Refuser");
		refus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(refus, "Voulez vous refuser cet Enseignant?","Refuser",JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.OK_OPTION){
				try {
					AdherantDAO adhDAO = new AdherantDAO();
					Adherant ad = adhDAO.getAdherant(getAdhLogin(),getAdhMdp());
					adhDAO.deleteAdherant(ad);
					JOptionPane.showMessageDialog(refus, "Cet enseignant est refuser avec succès!",  
                            "Refus avec succès",  
                            JOptionPane.INFORMATION_MESSAGE);
					try {
						adh_panel.removeAll();
						adh_panel.getGraphics().clearRect(0, 0, adh_panel.getWidth(), adh_panel.getHeight());
						adh_panel.revalidate();
						adh_panel.repaint();
						AdherantDAO adhDAO1 = new AdherantDAO();
						List<Adherant> obj = adhDAO1.getNotVerifiedAdherant();
						for( int i=0; i<obj.size();i++) {
							Adherant o =(Adherant)obj.get(i);
		    				if(i%2==1) {
		    					adh_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
		    				}
		    				if(i%2==0) {
		    					adh_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
		    				}
		    				adh_panel.add(new NouveauAdherantComponent(o.getLogin(),o.getMdp(),o.getNom(),adh_panel));
						}	
					}catch(DAOException ex) {
						System.out.println(ex);
					}
				}catch(DAOException daoe) {
					daoe.getMessage();
				}
			}
		}
		});
		BufferedImage refusImage = null;
		try {
			refusImage = ImageIO.read(new File("images/refuser.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image drefusImage = refusImage.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		ImageIcon refusIcon = new ImageIcon(drefusImage);
		refus.setIcon(refusIcon);
		refus.setCursor(new Cursor(Cursor.HAND_CURSOR));
		refus.setIconTextGap(5);
		refus.setHorizontalAlignment(SwingConstants.LEFT);
		refus.setForeground(new Color(255,120,0));
		refus.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		Font refusFont = new Font("Verdana", Font.PLAIN, 12);
		refus.setFont(refusFont);
		
		content.setLayout(new GridLayout(2,1));
		content.add(nom);
		content.add(login);
		footer.add(acceptation);
		footer.add(refus);
		
	}

	public String getAdhLogin() {
		return adhLogin;
	}

	public void setAdhLogin(String adhLogin) {
		this.adhLogin = adhLogin;
	}

	public String getAdhNom() {
		return adhNom;
	}

	public void setAdhNom(String adhNom) {
		this.adhNom = adhNom;
	}

	public String getAdhMdp() {
		return adhMdp;
	}

	public void setAdhMdp(String adhMdp) {
		this.adhMdp = adhMdp;
	}
	
	
}