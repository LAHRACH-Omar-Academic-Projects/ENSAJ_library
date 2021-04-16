package gestionBibliotheque.view;

import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gestionBibliotheque.model.utilisateurs.Bibliothecaire;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean livreRouteActive;
	private boolean revueRouteActive;
	private boolean empruntRouteActive;
	private boolean nouveauAdherantRouteActive;
	private boolean adherantRouteActive;
	private DocRoute livreRoute;
	private DocRoute revueRoute;
	private EmpruntRoute empruntRoute;
	private EmpruntRoute mesEmpruntsRoute;
	private NouveauAdherantRoute nouveauAdherantRoute;
	private AdherantRoute adherantRoute;
	private JLabel livre_route;
	private JLabel revue_route;
	private JLabel emprunt_route;
	private JLabel nouveauAdherant_route;
	private JLabel adherant_route;
	private JPanel menuPanel;
	private JPanel contentPane;
	private JPanel logoPanel;
	private JPanel routesPanel;
	private JPanel routesPanel_header;
	private JPanel routesPanel_empty_space;
	private JPanel routesPanel_footer;
	
	public Home(Utilisateur user, CardLayout cl, JPanel cards) {
		setLayout(new BorderLayout(0, 0));
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout(0, 0));
		add(menuPanel, BorderLayout.WEST);
		
		livreRoute = new DocRoute(user, "livre");
		revueRoute = new DocRoute(user, "revue");
		empruntRoute  = new EmpruntRoute(user);
		mesEmpruntsRoute = new EmpruntRoute(user);
		nouveauAdherantRoute = new NouveauAdherantRoute(user);
		adherantRoute = new AdherantRoute(user);
		contentPane = new JPanel();
		CardLayout cardlayout = new CardLayout();
		contentPane.setLayout(cardlayout);
		contentPane.add(livreRoute, "livre page");
		contentPane.add(revueRoute, "revue page");
		contentPane.add(empruntRoute, "emprunt page");
		contentPane.add(mesEmpruntsRoute, "mes emprunts page");
		contentPane.add(nouveauAdherantRoute, "nouveau adherant page");
		contentPane.add(adherantRoute, "adherant page");
		add(contentPane, BorderLayout.CENTER);
		
		BufferedImage logoImg = null;
		try {
			logoImg = ImageIO.read(new File("images/logo.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dlogoImg = logoImg.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		ImageIcon logoIcon = new ImageIcon(dlogoImg);
		
		logoPanel = new JPanel();
		logoPanel.setBackground(Color.WHITE);
		logoPanel.setLayout(new FlowLayout());
		menuPanel.add(logoPanel, BorderLayout.NORTH);
		
		GridLayout glheader = new GridLayout(3,1);
		glheader.setVgap(25);
		GridLayout glfooter = new GridLayout(3,1);
		glfooter.setVgap(25);
		
		routesPanel = new JPanel();
		routesPanel.setBackground(Color.WHITE);
		menuPanel.add(routesPanel, BorderLayout.CENTER);
		routesPanel.setLayout(new BorderLayout(0, 0));
		
		routesPanel_header = new JPanel();
		routesPanel_header.setBackground(Color.WHITE);
		routesPanel.add(routesPanel_header, BorderLayout.NORTH);
		routesPanel_header.setLayout(glheader);
		
		routesPanel_empty_space = new JPanel();
		routesPanel_empty_space.setBackground(Color.WHITE);
		routesPanel.add(routesPanel_empty_space, BorderLayout.CENTER);

		routesPanel_footer = new JPanel();
		routesPanel_footer.setBackground(Color.WHITE);
		routesPanel.add(routesPanel_footer, BorderLayout.SOUTH);
		routesPanel_footer.setLayout(glfooter);

		livre_route = new JLabel("Livres");
		livre_route.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setLivreRouteStyle("images/livre_b.png", new Color(0,120,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isLivreRouteActive()) 
					setLivreRouteStyle("images/livre_g.png", new Color(73,73,73));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setLivreRouteActive(true);
				cardlayout.show(contentPane, "livre page");
				setLivreRouteStyle("images/livre_b.png", new Color(0,120,255));
				setRevueRouteStyle("images/revue_g.png", new Color(73,73,73));
				if(user instanceof Bibliothecaire) 
					setEmpruntRouteStyle("images/emprunt_g.png", new Color(73,73,73));
				else
					setEmpruntRouteStyle("images/reservation_g.png", new Color(73,73,73));
				setNouveauAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setRevueRouteActive(false);
				setEmpruntRouteActive(false);
				setNouveauAdherantRouteActive(false);
				setAdherantRouteActive(false);
			}
		});
		livre_route.setBorder(new EmptyBorder(0,40,10,10));
		livre_route.setFont(new Font("Titillium Web Light", Font.PLAIN, 18));
		livre_route.setIconTextGap(20);
		livre_route.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLivreRouteActive(true);
		setLivreRouteStyle("images/livre_b.png", new Color(0,120,255));
		
		revue_route = new JLabel("Revues");
		revue_route.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setRevueRouteStyle("images/revue_b.png", new Color(0,120,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isRevueRouteActive())
					setRevueRouteStyle("images/revue_g.png", new Color(73,73,73));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setRevueRouteActive(true);
				cardlayout.show(contentPane, "revue page");
				setRevueRouteStyle("images/revue_b.png", new Color(0,120,255));
				setLivreRouteStyle("images/livre_g.png", new Color(73,73,73));
				if(user instanceof Bibliothecaire) 
					setEmpruntRouteStyle("images/emprunt_g.png", new Color(73,73,73));
				else
					setEmpruntRouteStyle("images/reservation_g.png", new Color(73,73,73));
				setNouveauAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setLivreRouteActive(false);
				setEmpruntRouteActive(false);
				setNouveauAdherantRouteActive(false);
				setAdherantRouteActive(false);
			}
		});
		revue_route.setBorder(new EmptyBorder(0,40,10,10));
		revue_route.setFont(new Font("Titillium Web Light", Font.PLAIN, 18));
		revue_route.setIconTextGap(20);
		revue_route.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setRevueRouteStyle("images/revue_g.png", new Color(73,73,73));
			
		if(user instanceof Bibliothecaire) 
			emprunt_route = new JLabel("Gestion emprunt");
		else
			emprunt_route = new JLabel("Mes emprunts");
		emprunt_route.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if(user instanceof Bibliothecaire) 
					setEmpruntRouteStyle("images/emprunt_b.png", new Color(0,120,255));
				else
					setEmpruntRouteStyle("images/reservation_b.png", new Color(0,120,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isEmpruntRouteActive()) {
					if(user instanceof Bibliothecaire) 
						setEmpruntRouteStyle("images/emprunt_g.png", new Color(73,73,73));
					else
						setEmpruntRouteStyle("images/reservation_g.png", new Color(73,73,73));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setEmpruntRouteActive(true);
				if(user instanceof Bibliothecaire) 
					cardlayout.show(contentPane, "emprunt page");
				else
					cardlayout.show(contentPane, "mes emprunts page");
				
				if(user instanceof Bibliothecaire) 
					setEmpruntRouteStyle("images/emprunt_b.png", new Color(0,120,255));
				else
					setEmpruntRouteStyle("images/reservation_b.png", new Color(0,120,255));
				setLivreRouteStyle("images/livre_g.png", new Color(73,73,73));
				setRevueRouteStyle("images/revue_g.png", new Color(73,73,73));
				setNouveauAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setLivreRouteActive(false);
				setRevueRouteActive(false);
				setNouveauAdherantRouteActive(false);
				setAdherantRouteActive(false);
			}
		});
		emprunt_route.setBorder(new EmptyBorder(0,40,10,10));
		emprunt_route.setFont(new Font("Titillium Web Light", Font.PLAIN, 18));	
		emprunt_route.setIconTextGap(20);
		emprunt_route.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(user instanceof Bibliothecaire) 
			setEmpruntRouteStyle("images/emprunt_g.png", new Color(73,73,73));
		else
			setEmpruntRouteStyle("images/reservation_g.png", new Color(73,73,73));
		
		nouveauAdherant_route = new JLabel("Nouveau adhérants");
		nouveauAdherant_route.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setNouveauAdherantRouteStyle("images/gestion_b.png", new Color(0,120,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isNouveauAdherantRouteActive()) {
					setNouveauAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setNouveauAdherantRouteActive(true);
				cardlayout.show(contentPane, "nouveau adherant page");
				setNouveauAdherantRouteStyle("images/gestion_b.png", new Color(0,120,255));
				setAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setLivreRouteStyle("images/livre_g.png", new Color(73,73,73));
				setRevueRouteStyle("images/revue_g.png", new Color(73,73,73));
				if(user instanceof Bibliothecaire) 
					setEmpruntRouteStyle("images/emprunt_g.png", new Color(73,73,73));
				else
					setEmpruntRouteStyle("images/reservation_g.png", new Color(73,73,73));
				setLivreRouteActive(false);
				setRevueRouteActive(false);
				setEmpruntRouteActive(false);
				setAdherantRouteActive(false);
			}
		});
		
		nouveauAdherant_route.setBorder(new EmptyBorder(0,40,10,10));
		nouveauAdherant_route.setFont(new Font("Titillium Web Light", Font.PLAIN, 18));
		nouveauAdherant_route.setIconTextGap(20);
		nouveauAdherant_route.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setNouveauAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
		
		adherant_route = new JLabel("Adhérants");
		adherant_route.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setAdherantRouteStyle("images/gestion_b.png", new Color(0,120,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isAdherantRouteActive()) {
					setAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cardlayout.removeLayoutComponent(adherantRoute);
				adherantRoute = new AdherantRoute(user);
				contentPane.add(adherantRoute, "adherant page");
				
				setAdherantRouteActive(true);
				cardlayout.show(contentPane, "adherant page");
				setAdherantRouteStyle("images/gestion_b.png", new Color(0,120,255));
				setNouveauAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));
				setLivreRouteStyle("images/livre_g.png", new Color(73,73,73));
				setRevueRouteStyle("images/revue_g.png", new Color(73,73,73));
				if(user instanceof Bibliothecaire) 
					setEmpruntRouteStyle("images/emprunt_g.png", new Color(73,73,73));
				else
					setEmpruntRouteStyle("images/reservation_g.png", new Color(73,73,73));
				setLivreRouteActive(false);
				setRevueRouteActive(false);
				setEmpruntRouteActive(false);
				setNouveauAdherantRouteActive(false);
			}
		});
		adherant_route.setBorder(new EmptyBorder(0,40,10,10));
		adherant_route.setFont(new Font("Titillium Web Light", Font.PLAIN, 18));
		adherant_route.setIconTextGap(20);
		adherant_route.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setAdherantRouteStyle("images/gestion_g.png", new Color(73,73,73));

		JLabel logoLabel = new JLabel("");
		logoLabel.setBorder(new EmptyBorder(0,30,10,10));	
		logoLabel.setIcon(logoIcon);
		logoPanel.add(logoLabel);

		JLabel logoName = new JLabel("My Biblio");
		logoName.setFont(new Font("Titillium Web Light", Font.PLAIN, 20));
		logoName.setForeground(new Color(0,120,255));
		logoName.setBorder(new EmptyBorder(30,0,40,60));	
		logoPanel.add(logoName);

		routesPanel_header.add(livre_route);
		routesPanel_header.add(revue_route);
		routesPanel_header.add(emprunt_route);
		if(user instanceof Bibliothecaire) {		
			routesPanel_footer.add(nouveauAdherant_route);
			routesPanel_footer.add(adherant_route);
		}
		
		JLabel dec = new JLabel("Déconnecter");
		dec.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(cards, "sign in page");
			}
		});
		BufferedImage decImage = null;
		try {
			decImage = ImageIO.read(new File("images/deconnecter.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image ddecImage = decImage.getScaledInstance(22, 22, Image.SCALE_SMOOTH);
		ImageIcon decIcon = new ImageIcon(ddecImage);
		dec.setIcon(decIcon);
		dec.setBorder(new EmptyBorder(0,40,10,10));
		dec.setCursor(new Cursor(Cursor.HAND_CURSOR));
		dec.setHorizontalAlignment(SwingConstants.CENTER);
		dec.setForeground(new Color(255,80,0));
		dec.setBorder(new EmptyBorder(new Insets(0, 15, 0, 0)));
		Font decFont = new Font("Verdana", Font.PLAIN, 12);
		dec.setFont(decFont);
		routesPanel_footer.add(dec);
		
	}
	
	public boolean isLivreRouteActive() {
		return livreRouteActive;
	}
	public void setLivreRouteActive(boolean livreRouteActive) {
		this.livreRouteActive = livreRouteActive;
	}
	public boolean isRevueRouteActive() {
		return revueRouteActive;
	}
	public void setRevueRouteActive(boolean revueRouteActive) {
		this.revueRouteActive = revueRouteActive;
	}
	public boolean isEmpruntRouteActive() {
		return empruntRouteActive;
	}
	public void setEmpruntRouteActive(boolean empruntRouteActive) {
		this.empruntRouteActive = empruntRouteActive;
	}
	public boolean isNouveauAdherantRouteActive() {
		return nouveauAdherantRouteActive;
	}
	public void setNouveauAdherantRouteActive(boolean nouveauAdherantRouteActive) {
		this.nouveauAdherantRouteActive = nouveauAdherantRouteActive;
	}
	public boolean isAdherantRouteActive() {
		return adherantRouteActive;
	}
	public void setAdherantRouteActive(boolean adherantRouteActive) {
		this.adherantRouteActive = adherantRouteActive;
	}
	public void setLivreRouteStyle(String livreRouteImage, Color livreRouteLabelFg) {
		BufferedImage livreImage = null;
		try {
			livreImage = ImageIO.read(new File(livreRouteImage));
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		}
		Image dlivreImage = livreImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon livreIcon = new ImageIcon(dlivreImage);
		livre_route.setIcon(livreIcon);
		livre_route.setForeground(livreRouteLabelFg);
	}
	public void setRevueRouteStyle(String revueRouteImage, Color revueRouteLabelFg) {
		BufferedImage revueImage = null;
		try {
			revueImage = ImageIO.read(new File(revueRouteImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image drevueImage = revueImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon revueIcon = new ImageIcon(drevueImage);
		revue_route.setIcon(revueIcon);
		revue_route.setForeground(revueRouteLabelFg);
	}
	public void setEmpruntRouteStyle(String empruntRouteImage, Color empruntRouteLabelFg) {
		BufferedImage empruntImage = null;
		try {
			empruntImage = ImageIO.read(new File(empruntRouteImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dempruntImage = empruntImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon empruntIcon = new ImageIcon(dempruntImage);
		emprunt_route.setIcon(empruntIcon);
		emprunt_route.setForeground(empruntRouteLabelFg);
	}
	public void setNouveauAdherantRouteStyle(String nouveauAdherantRouteImage, Color nouveauAdherantRouteLabelFg) {
		BufferedImage nouveauAdherantImage = null;
		try {
			nouveauAdherantImage = ImageIO.read(new File(nouveauAdherantRouteImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dnouveauAdherantImage = nouveauAdherantImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon nouveauAdherantIcon = new ImageIcon(dnouveauAdherantImage);
		nouveauAdherant_route.setIcon(nouveauAdherantIcon);
		nouveauAdherant_route.setForeground(nouveauAdherantRouteLabelFg);
	}
	public void setAdherantRouteStyle(String adherantRouteImage, Color adherantRouteLabelFg) {
		BufferedImage adherantImage = null;
		try {
			adherantImage = ImageIO.read(new File(adherantRouteImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dadherantImage = adherantImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon adherantIcon = new ImageIcon(dadherantImage);
		adherant_route.setIcon(adherantIcon);
		adherant_route.setForeground(adherantRouteLabelFg);
	}
}
