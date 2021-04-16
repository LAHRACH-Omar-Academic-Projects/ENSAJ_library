package gestionBibliotheque.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.utilisateurs.EnseignantDAO;
import gestionBibliotheque.dao.utilisateurs.EtudiantDAO;
import gestionBibliotheque.model.utilisateurs.Enseignant;
import gestionBibliotheque.model.utilisateurs.Etudiant;

import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Modifier extends JFrame {
	
	//Components attributes
	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JPanel headerPanel;
	private JPanel footerPanel;
	private JPanel userIconPanel;
	private JPanel userNamePanel;
	private JPanel emailPanel;
	private JPanel motDePassePanel;
	private JPanel modifierButtonPanel;
	private JPanel departementPanel;
	private JPanel fillierePanel;
	private JLabel userIconLabel;
	private JButton modifierButton;
	private JPanel cnePanel;
	private JPanel adressePanel;
	private PlaceholderTextField userNameInput;
	private PlaceholderTextField cneInput;
	private PlaceholderTextField emailInput;
	private PlaceholderTextField adresseInput;
	private PlaceholderPasswordField motDePasseInput;
	private JComboBox<?> departementList;
	private JComboBox<?> filliereList;
	private GridBagConstraints gbcIcon;
	private GridBagConstraints gbcUserName;
	private GridBagConstraints gbcEmail;
	private GridBagConstraints gbcMotDePasse;
	private GridBagConstraints gbcDepartement;
	private GridBagConstraints gbcCne;
	private GridBagConstraints gbcAdresse;
	private GridBagConstraints gbcFilliere;
	private GridBagConstraints gbcmodifierButton;
	private GridBagConstraints gbcHeader;
	private GridBagConstraints gbcFooter;
	
	public Modifier(String routeName, JPanel ens_panel, Enseignant ens) {
	contentPane = new JPanel();
	this.setTitle("Modification d'un " + routeName);
	this.setSize(new Dimension(400,600));
	this.setLocationRelativeTo(null);
	contentPane.setBackground(new Color(238,238,238));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(new GridBagLayout());
    
    gbcIcon = new GridBagConstraints();
    gbcUserName = new GridBagConstraints();
    gbcEmail = new GridBagConstraints();
    gbcMotDePasse = new GridBagConstraints();
    gbcDepartement = new GridBagConstraints();
    gbcmodifierButton = new GridBagConstraints();
    gbcHeader = new GridBagConstraints();
    gbcFooter = new GridBagConstraints();
    gbcIcon.fill = GridBagConstraints.BOTH;
    gbcUserName.fill = GridBagConstraints.BOTH;
    gbcEmail.fill = GridBagConstraints.BOTH;
    gbcMotDePasse.fill = GridBagConstraints.BOTH;
    gbcDepartement.fill = GridBagConstraints.BOTH;
    gbcmodifierButton.fill = GridBagConstraints.BOTH;
    gbcHeader.fill = GridBagConstraints.BOTH;
    gbcFooter.fill = GridBagConstraints.BOTH;
    
    //header Panel
    headerPanel = new JPanel();
    gbcHeader.gridx = 0;
    gbcHeader.gridy = 0;
    contentPane.add(headerPanel, gbcHeader);
    
	//icon Panel
    userIconPanel = new JPanel();
    gbcIcon.gridx = 0;
    gbcIcon.gridy = 1;
    contentPane.add(userIconPanel, gbcIcon);
    
    //nom d'utilisateur Panel
    userNamePanel = new JPanel();
    gbcUserName.gridx = 0;
    gbcUserName.gridy = 2;  
    contentPane.add(userNamePanel, gbcUserName);
    
    //email Panel
    emailPanel = new JPanel();
    gbcEmail.gridx = 0;
    gbcEmail.gridy = 3;  
    contentPane.add(emailPanel, gbcEmail);
    
    //mot de passe Panel
    motDePassePanel = new JPanel();
    gbcMotDePasse.gridx = 0;
    gbcMotDePasse.gridy = 4;  
    contentPane.add(motDePassePanel, gbcMotDePasse);
    
    //sign Up button Panel
    modifierButtonPanel = new JPanel();
    gbcmodifierButton.gridx = 0;
    gbcmodifierButton.gridy = 7;  
    gbcmodifierButton.ipadx = 200;
    contentPane.add(modifierButtonPanel, gbcmodifierButton);
    
    
    
    //département Panel
    departementPanel = new JPanel();
    gbcDepartement.gridx = 0;
    gbcDepartement.gridy = 6;
    contentPane.add(departementPanel, gbcDepartement);

	//footer Panel
    footerPanel = new JPanel();
    footerPanel.setLayout(new FlowLayout());
    gbcFooter.gridx = 0;
    gbcFooter.gridy = 9;
    contentPane.add(footerPanel, gbcFooter);
    
    //user icon label
    userIconLabel = new JLabel();
    userIconPanel.add(userIconLabel);
    
    //user icon
	BufferedImage userImg = null;
	try {
		userImg = ImageIO.read(new File("images/enseignant.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Image duserImg = userImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon userIcon = new ImageIcon(duserImg);
	userIconLabel.setIcon(userIcon);

	//nom d'utilisateur input field
	userNameInput = new PlaceholderTextField("");
	userNameInput.setText(ens.getNom());
	userNameInput.setPlaceholder("Nom utilisateur");
	userNameInput.setColumns(25);
	userNameInput.setMargin(new Insets(10, 10, 10, 10));
	userNameInput.setForeground(new Color(80, 80, 80));
	userNamePanel.add(userNameInput);
	
	//email input field
	emailInput = new PlaceholderTextField("");
	emailInput.setText(ens.getLogin());
	emailInput.setPlaceholder("Email");
	emailInput.setColumns(25);
	emailInput.setMargin(new Insets(10, 10, 10, 10));
	emailInput.setForeground(new Color(80, 80, 80));
	emailPanel.add(emailInput);
	
	//mot de passe input field
	motDePasseInput = new PlaceholderPasswordField("");
	motDePasseInput.setText(ens.getMdp());
	motDePasseInput.setPlaceholder("Mot de passe");
	motDePasseInput.setColumns(25);
	motDePasseInput.setMargin(new Insets(10, 10, 10, 10));
	motDePasseInput.setForeground(new Color(80, 80, 80));
	motDePassePanel.add(motDePasseInput);
	
	String[] departements = { "TRI", "STIN"};
	departementList = new JComboBox<Object>(departements);
	if(ens.getDepartement().equals("TRI")) {
		departementList.setSelectedIndex(0);
	}else {
		departementList.setSelectedIndex(1);
	}	
	departementList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	departementList.setPreferredSize(new Dimension(150, 25));
	departementList.setBackground(Color.WHITE);
	departementList.setForeground(new Color(0,200,100));
	departementPanel.add(departementList);
	
	modifierButton = new JButton("Modifier");
	modifierButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			String name = userNameInput.getText();
			String email = emailInput.getText();
			@SuppressWarnings("deprecation")
			String mdp = motDePasseInput.getText();
			String dep= departements[departementList.getSelectedIndex()];
			try {
				EnseignantDAO ensDAO = new EnseignantDAO();
				Enseignant adhEns = new Enseignant(ens.getLogin(),ens.getMdp(),ens.getNom(),ens.getNumLecteur(),ens.getDepartement());
				ensDAO.modifyEnseignant(adhEns,email,mdp,name,dep);
				JOptionPane.showMessageDialog(modifierButton, "Cette modification a été effectué avec succès!",  
                        "Modification avec succès",  
                        JOptionPane.INFORMATION_MESSAGE);
				dispose();
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
				daoe.getMessage();
			}
		}
	});
	modifierButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
	modifierButton.setFocusPainted(false);
	modifierButton.setMargin(new Insets(8, 85, 8, 85));
	modifierButton.setBackground(new Color(200,100,0));
	modifierButton.setForeground(Color.WHITE);
	modifierButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	modifierButtonPanel.add(modifierButton);
	
	BufferedImage signUpImg = null;
	try {
		signUpImg = ImageIO.read(new File("images/modifier.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Image dsignUpImg = signUpImg.getScaledInstance(18,18, Image.SCALE_SMOOTH);
	ImageIcon signUpIcon = new ImageIcon(dsignUpImg);
	modifierButton.setIcon(signUpIcon);
    

    
	setBackground(Color.WHITE);           
	add(contentPane);
}
	public Modifier(String routeName, JPanel etd_panel, Etudiant etd) {
		contentPane = new JPanel();
		this.setTitle("Modification d'un " + routeName);
		this.setSize(new Dimension(400,600));
		this.setLocationRelativeTo(null);
		contentPane.setBackground(new Color(238,238,238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        
        gbcIcon = new GridBagConstraints();
        gbcUserName = new GridBagConstraints();
        gbcEmail = new GridBagConstraints();
        gbcMotDePasse = new GridBagConstraints();
        gbcAdresse = new GridBagConstraints();
        gbcCne = new GridBagConstraints();
        gbcFilliere = new GridBagConstraints();
        gbcmodifierButton = new GridBagConstraints();
        gbcHeader = new GridBagConstraints();
        gbcFooter = new GridBagConstraints();
        gbcIcon.fill = GridBagConstraints.BOTH;
        gbcUserName.fill = GridBagConstraints.BOTH;
        gbcEmail.fill = GridBagConstraints.BOTH;
        gbcMotDePasse.fill = GridBagConstraints.BOTH;
        gbcAdresse.fill = GridBagConstraints.BOTH;
        gbcCne.fill = GridBagConstraints.BOTH;
        gbcFilliere.fill = GridBagConstraints.BOTH;
        gbcmodifierButton.fill = GridBagConstraints.BOTH;
        gbcHeader.fill = GridBagConstraints.BOTH;
        gbcFooter.fill = GridBagConstraints.BOTH;
        
        //header Panel
        headerPanel = new JPanel();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        contentPane.add(headerPanel, gbcHeader);
        
		//icon Panel
        userIconPanel = new JPanel();
        gbcIcon.gridx = 0;
        gbcIcon.gridy = 1;
        contentPane.add(userIconPanel, gbcIcon);
        
        //nom d'utilisateur Panel
        userNamePanel = new JPanel();
        gbcUserName.gridx = 0;
        gbcUserName.gridy = 2;  
        contentPane.add(userNamePanel, gbcUserName);
        
        //email Panel
        emailPanel = new JPanel();
        gbcEmail.gridx = 0;
        gbcEmail.gridy = 3;  
        contentPane.add(emailPanel, gbcEmail);
        
        //mot de passe Panel
        motDePassePanel = new JPanel();
        gbcMotDePasse.gridx = 0;
        gbcMotDePasse.gridy = 4;  
        contentPane.add(motDePassePanel, gbcMotDePasse);
        
        adressePanel = new JPanel();
        gbcAdresse.gridx = 0;
        gbcAdresse.gridy = 5;
        contentPane.add(adressePanel, gbcAdresse);
        
        
        cnePanel = new JPanel();
        gbcCne.gridx = 0;
        gbcCne.gridy = 6;
        contentPane.add(cnePanel, gbcCne);
        
        fillierePanel = new JPanel();
        gbcFilliere.gridx = 0;
        gbcFilliere.gridy = 7;
        contentPane.add(fillierePanel, gbcFilliere);
        

      
        
        //sign Up button Panel
        modifierButtonPanel = new JPanel();
        gbcmodifierButton.gridx = 0;
        gbcmodifierButton.gridy = 8;  
        gbcmodifierButton.ipadx = 200;
        contentPane.add(modifierButtonPanel, gbcmodifierButton);
        
 
		//footer Panel
        footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        gbcFooter.gridx = 0;
        gbcFooter.gridy = 9;
        contentPane.add(footerPanel, gbcFooter);
        
        //user icon label
        userIconLabel = new JLabel();
        userIconPanel.add(userIconLabel);
        
        //user icon
		BufferedImage userImg = null;
		try {
			userImg = ImageIO.read(new File("images/Etudiant.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image duserImg = userImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon userIcon = new ImageIcon(duserImg);
		userIconLabel.setIcon(userIcon);
	
		//nom d'utilisateur input field
		userNameInput = new PlaceholderTextField("");
		userNameInput.setText(etd.getNom());
		userNameInput.setPlaceholder("Nom utilisateur");
		userNameInput.setColumns(25);
		userNameInput.setMargin(new Insets(10, 10, 10, 10));
		userNameInput.setForeground(new Color(80, 80, 80));
		userNamePanel.add(userNameInput);
		
		//email input field
		emailInput = new PlaceholderTextField("");
		emailInput.setText(etd.getLogin());
		emailInput.setPlaceholder("Email");
		emailInput.setColumns(25);
		emailInput.setMargin(new Insets(10, 10, 10, 10));
		emailInput.setForeground(new Color(80, 80, 80));
		emailPanel.add(emailInput);
		
		//email input field
		cneInput = new PlaceholderTextField("");
		cneInput.setText(etd.getCne());
		cneInput.setPlaceholder("Cne");
		cneInput.setColumns(25);
		cneInput.setMargin(new Insets(10, 10, 10, 10));
		cneInput.setForeground(new Color(80, 80, 80));
		cnePanel.add(cneInput);
		
		//email input field
		adresseInput = new PlaceholderTextField("");
		adresseInput.setText(etd.getAdresse());
		adresseInput.setPlaceholder("Adresse");
		adresseInput.setColumns(25);
		adresseInput.setMargin(new Insets(10, 10, 10, 10));
		adresseInput.setForeground(new Color(80, 80, 80));
		adressePanel.add(adresseInput);
		
		//mot de passe input field
		motDePasseInput = new PlaceholderPasswordField("");
		motDePasseInput.setText(etd.getMdp());
		motDePasseInput.setPlaceholder("Mot de passe");
		motDePasseInput.setColumns(25);
		motDePasseInput.setMargin(new Insets(10, 10, 10, 10));
		motDePasseInput.setForeground(new Color(80, 80, 80));
		motDePassePanel.add(motDePasseInput);
		
		//départements input field
		String[] fillieres = { "2ITE", "ISIC", "G2E", "GI"};
		filliereList = new JComboBox<String>(fillieres);
		if(etd.getFilliere().equals("2ITE")) {
			filliereList.setSelectedIndex(0);
		}else if(etd.getFilliere().equals("ISIC")){
			filliereList.setSelectedIndex(1);
		}else if(etd.getFilliere().equals("G2E")){
			filliereList.setSelectedIndex(2);
		}else{
			filliereList.setSelectedIndex(3);
		}
		filliereList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		filliereList.setPreferredSize(new Dimension(150, 25));
		filliereList.setBackground(Color.WHITE);
		filliereList.setForeground(new Color(0,200,100));
		fillierePanel.add(filliereList);
		
		modifierButton = new JButton("Modifier");
		modifierButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String name = userNameInput.getText();
				String email = emailInput.getText();
				String adresse = adresseInput.getText();
				String cne = cneInput.getText();
				@SuppressWarnings("deprecation")
				String mdp = motDePasseInput.getText();
				String fill= fillieres[filliereList.getSelectedIndex()];
				try {
					EtudiantDAO etdDAO = new EtudiantDAO();
					Etudiant adhetd = new Etudiant(etd.getLogin(),etd.getMdp(),etd.getNom(),etd.getNumLecteur(),etd.getCne(), etd.getAdresse(), etd.getFilliere());
					etdDAO.modifyEtudiant(adhetd,email,mdp,name,cne, adresse, fill);
					JOptionPane.showMessageDialog(modifierButton, "Cette modification a été effectué avec succès!",  
                            "Modification avec succès",  
                            JOptionPane.INFORMATION_MESSAGE);
					dispose();
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
		    				etd_panel.add(new EtudiantComponent(o.getLogin(), o.getMdp(), o.getNom(), o.getNumLecteur(), o.getCne(), o.getAdresse(), o.getFilliere(), etd_panel));
						}	
					}catch(DAOException ex) {
						System.out.println(ex);
					}
				}catch(DAOException daoe) {
					daoe.getMessage();
				}
			}
		});
		modifierButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
		modifierButton.setFocusPainted(false);
		modifierButton.setMargin(new Insets(8, 85, 8, 85));
		modifierButton.setBackground(new Color(255,120, 0));
		modifierButton.setForeground(Color.WHITE);
		modifierButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		modifierButtonPanel.add(modifierButton);
		
		BufferedImage signUpImg = null;
		try {
			signUpImg = ImageIO.read(new File("images/modifier.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dsignUpImg = signUpImg.getScaledInstance(18,18, Image.SCALE_SMOOTH);
		ImageIcon signUpIcon = new ImageIcon(dsignUpImg);
		modifierButton.setIcon(signUpIcon);
        

        
		setBackground(Color.WHITE);           
		add(contentPane);
	}
	
}
