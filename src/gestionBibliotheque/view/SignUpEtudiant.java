package gestionBibliotheque.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionBibliotheque.controller.UtilisateurController;
import gestionBibliotheque.model.utilisateurs.Etudiant;

import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpEtudiant extends JPanel {
	
	//Components attributes
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel headerPanel;
	private JPanel footerPanel;
	private JPanel userIconPanel;
	private JPanel userNamePanel;
	private JPanel emailPanel;
	private JPanel motDePassePanel;
	private JPanel signUpButtonPanel;
	private JPanel usersPanel;
	private JPanel cnePanel;
	private JPanel adressePanel;
	private JPanel fillierePanel;
	private JLabel userIconLabel;
	private JPanel signUpLinkPanel;
	private JLabel signUpLinkLabel;
	private JButton signUpButton;
	private PlaceholderTextField userNameInput;
	private PlaceholderTextField emailInput;
	private PlaceholderPasswordField motDePasseInput;
	private PlaceholderTextField cneInput;
	private PlaceholderTextField adresseInput;
	private JComboBox<?> filliereList;
	private JButton etudiant;
	private JButton enseignant;
	private GridBagConstraints gbcIcon;
	private GridBagConstraints gbcUserName;
	private GridBagConstraints gbcEmail;
	private GridBagConstraints gbcMotDePasse;
	private GridBagConstraints gbcUsers;
	private GridBagConstraints gbcCNE;
	private GridBagConstraints gbcAdresse;
	private GridBagConstraints gbcFilliere;
	private GridBagConstraints gbcSignUpButton;
	private GridBagConstraints gbcSignUpLink;
	private GridBagConstraints gbcHeader;
	private GridBagConstraints gbcFooter;
	//Controller

	private UtilisateurController uc;

	//Sign Up Screen constructor
	public SignUpEtudiant(CardLayout cl, JPanel cards, boolean etdSelected, boolean ensSelected, boolean bibSelected) {
			
		//content Panel
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout());
        
        gbcIcon = new GridBagConstraints();
        gbcUserName = new GridBagConstraints();
        gbcEmail = new GridBagConstraints();
        gbcMotDePasse = new GridBagConstraints();
        gbcUsers = new GridBagConstraints();
        gbcCNE = new GridBagConstraints();
        gbcAdresse = new GridBagConstraints();
        gbcFilliere = new GridBagConstraints();
        gbcSignUpButton = new GridBagConstraints();
        gbcSignUpLink = new GridBagConstraints();
        gbcHeader = new GridBagConstraints();
        gbcFooter = new GridBagConstraints();
        gbcIcon.fill = GridBagConstraints.BOTH;
        gbcUserName.fill = GridBagConstraints.BOTH;
        gbcEmail.fill = GridBagConstraints.BOTH;
        gbcMotDePasse.fill = GridBagConstraints.BOTH;
        gbcUsers.fill = GridBagConstraints.BOTH;
        gbcCNE.fill = GridBagConstraints.BOTH;
        gbcAdresse.fill = GridBagConstraints.BOTH;
        gbcFilliere.fill = GridBagConstraints.BOTH;
        gbcSignUpButton.fill = GridBagConstraints.BOTH;
        gbcSignUpLink.fill = GridBagConstraints.BOTH;
        gbcHeader.fill = GridBagConstraints.BOTH;
        gbcFooter.fill = GridBagConstraints.BOTH;
        
        //header Panel
        headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        contentPane.add(headerPanel, gbcHeader);
        
		//icon Panel
        userIconPanel = new JPanel();
        userIconPanel.setBackground(Color.WHITE);
        gbcIcon.gridx = 0;
        gbcIcon.gridy = 1;
        contentPane.add(userIconPanel, gbcIcon);
        
        //nom d'utilisateur Panel
        userNamePanel = new JPanel();
        userNamePanel.setBackground(Color.WHITE);
        gbcUserName.gridx = 0;
        gbcUserName.gridy = 2;  
        contentPane.add(userNamePanel, gbcUserName);
        
        //email Panel
        emailPanel = new JPanel();
        emailPanel.setBackground(Color.WHITE);
        gbcEmail.gridx = 0;
        gbcEmail.gridy = 3;  
        contentPane.add(emailPanel, gbcEmail);
        
        //mot de passe Panel
        motDePassePanel = new JPanel();
        motDePassePanel.setBackground(Color.WHITE);
        gbcMotDePasse.gridx = 0;
        gbcMotDePasse.gridy = 4;  
        contentPane.add(motDePassePanel, gbcMotDePasse);
        
        //utilisateurs Panel
        usersPanel = new JPanel();
        usersPanel.setBackground(Color.WHITE);
        gbcUsers.gridx = 0;
        gbcUsers.gridy = 5;
        contentPane.add(usersPanel, gbcUsers);
        
        //CNE Panel
        cnePanel = new JPanel();
        cnePanel.setBackground(Color.WHITE);
        gbcCNE.gridx = 0;
        gbcCNE.gridy = 6;
        contentPane.add(cnePanel, gbcCNE);
        
        //adresse Panel
        adressePanel = new JPanel();
        adressePanel.setBackground(Color.WHITE);
        gbcAdresse.gridx = 0;
        gbcAdresse.gridy = 7;
        contentPane.add(adressePanel, gbcAdresse);
        
        //fillière Panel
        fillierePanel = new JPanel();
        fillierePanel.setBackground(Color.WHITE);
        gbcFilliere.gridx = 0;
        gbcFilliere.gridy = 8;
        contentPane.add(fillierePanel, gbcFilliere);
        
        //sign up button Panel
        signUpButtonPanel = new JPanel();
        signUpButtonPanel.setBackground(Color.WHITE);
        gbcSignUpButton.gridx = 0;
        gbcSignUpButton.gridy = 9;  
        gbcSignUpButton.ipadx = 200;
        contentPane.add(signUpButtonPanel, gbcSignUpButton);
        
        //sign up link Panel
        signUpLinkPanel = new JPanel();
        signUpLinkPanel.setBackground(Color.WHITE);
        gbcSignUpLink.gridx = 0;
        gbcSignUpLink.gridy = 10; 
        contentPane.add(signUpLinkPanel, gbcSignUpLink);
 
		//footer Panel
        footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setLayout(new FlowLayout());
        gbcFooter.gridx = 0;
        gbcFooter.gridy = 11;
        contentPane.add(footerPanel, gbcFooter);
        
        //user icon label
        userIconLabel = new JLabel();
        userIconPanel.add(userIconLabel);
        
        //user icon
		BufferedImage userImg = null;
		try {
			userImg = ImageIO.read(new File("images/adduserIcon.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image duserImg = userImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon userIcon = new ImageIcon(duserImg);
		userIconLabel.setIcon(userIcon);
		
		//nom d'utilisateur input field
		userNameInput = new PlaceholderTextField("");
		userNameInput.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(6,6)), 
                new EmptyBorder(new Insets(15, 15, 15, 15))));
		userNameInput.setPlaceholder("Nom utilisateur");
		userNameInput.setColumns(25);
		userNameInput.setMargin(new Insets(10, 10, 10, 10));
		userNameInput.setForeground(new Color(80, 80, 80));
		userNamePanel.add(userNameInput);
		
		//email input field
		emailInput = new PlaceholderTextField("");
		emailInput.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(6,6)), 
                new EmptyBorder(new Insets(15, 15, 15, 15))));
		emailInput.setPlaceholder("Email");
		emailInput.setColumns(25);
		emailInput.setMargin(new Insets(10, 10, 10, 10));
		emailInput.setForeground(new Color(80, 80, 80));
		emailPanel.add(emailInput);
		
		//mot de passe input field
		motDePasseInput = new PlaceholderPasswordField("");
		motDePasseInput.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(6,6)), 
                new EmptyBorder(new Insets(15, 15, 15, 15))));
		motDePasseInput.setPlaceholder("Mot de passe");
		motDePasseInput.setColumns(25);
		motDePasseInput.setMargin(new Insets(10, 10, 10, 10));
		motDePasseInput.setForeground(new Color(80, 80, 80));
		motDePassePanel.add(motDePasseInput);
		
		//CNE input field
		cneInput = new PlaceholderTextField("");
		cneInput.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(6,6)), 
                new EmptyBorder(new Insets(15, 15, 15, 15))));
		cneInput.setPlaceholder("CNE");
		cneInput.setColumns(25);
		cneInput.setMargin(new Insets(10, 10, 10, 10));
		cneInput.setForeground(new Color(80, 80, 80));
		cnePanel.add(cneInput);
		
		//adresse input field
		adresseInput = new PlaceholderTextField("");
		adresseInput.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(6,6)), 
                new EmptyBorder(new Insets(15, 15, 15, 15))));
		adresseInput.setPlaceholder("Adresse");
		adresseInput.setColumns(25);
		adresseInput.setMargin(new Insets(10, 10, 10, 10));
		adresseInput.setForeground(new Color(80, 80, 80));
		adressePanel.add(adresseInput);
		
		//fillière input field
		String[] fillieres = { "2ITE", "ISIC", "G2E", "GI"};
		filliereList = new JComboBox<Object>(fillieres);
		filliereList.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(6,6)), 
                new EmptyBorder(new Insets(15, 15, 15, 15))));
		filliereList.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, Color.DARK_GRAY, new Point(6,6)), 
                new EmptyBorder(new Insets(0, 0, 0, 0))));
		filliereList.setSelectedIndex(0);
		filliereList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		filliereList.setPreferredSize(new Dimension(200, 30));
		filliereList.setBackground(Color.WHITE);
		filliereList.setForeground(new Color(0,200,100));
		fillierePanel.add(filliereList);
		
		
		//sign up button
		signUpButton = new JButton("S'enregistrer");
		signUpButton.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1,new Color(0,145,250) , new Point(6,6)), 
                new EmptyBorder(new Insets(12, 98, 12, 98))));
		signUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String name = userNameInput.getText();
				String email = emailInput.getText();
				@SuppressWarnings("deprecation")
				String mdp = motDePasseInput.getText();
				String cne = cneInput.getText();
				String adresse = adresseInput.getText();
				String fil= fillieres[filliereList.getSelectedIndex()];
				
				Etudiant et = new Etudiant(email,mdp,name,0,cne,adresse,fil);
				uc =new UtilisateurController("add",et);
				userNameInput.setText("");
				emailInput.setText("");
				motDePasseInput.setText("");
				cneInput.setText("");
				adresseInput.setText("");
				filliereList.setSelectedIndex(0);
				JOptionPane.showMessageDialog(signUpButton, "Votre compte ne sera accessible qu'après sa validation",  
				"Message",  
				JOptionPane.INFORMATION_MESSAGE);
				cl.show(cards, "sign in page");
			}
		});
		signUpButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
		signUpButton.setFocusPainted(false);
		signUpButton.setMargin(new Insets(8, 85, 8, 85));
		signUpButton.setBackground(new Color(0,145,250));
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpButtonPanel.add(signUpButton);
		
		//sign up link label
		signUpLinkLabel = new JLabel("Vous avez déjà un compte ? Se connecter");
		signUpLinkLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(cards, "sign in page");
			}
		});
		signUpLinkLabel.setForeground(new Color(0,60,255));
		Font font = signUpLinkLabel.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		signUpLinkLabel.setFont(font.deriveFont(attributes));
		signUpLinkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpLinkPanel.add(signUpLinkLabel);
		
        //sign up icon
		BufferedImage signUpImg = null;
		try {
			signUpImg = ImageIO.read(new File("images/signUp.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dsignUpImg = signUpImg.getScaledInstance(18,18, Image.SCALE_SMOOTH);
		ImageIcon signUpIcon = new ImageIcon(dsignUpImg);
		signUpButton.setIcon(signUpIcon);
		
		//users button
		etudiant = new JButton("Etudiant");
		etudiant.setFont(new Font("Century Gothic", Font.BOLD, 14));
		etudiant.setFocusPainted(false);
		etudiant.setBackground(Color.WHITE);
		etudiant.setForeground(new Color(0,120,255));
		etudiant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        enseignant = new JButton("Enseignant"); 
        enseignant.setFont(new Font("Century Gothic", Font.BOLD, 14));
        enseignant.setFocusPainted(false);
        enseignant.setBackground(Color.WHITE);
        enseignant.setForeground(new Color(0,120,255));
        enseignant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        usersPanel.add(etudiant);
        usersPanel.add(enseignant);
        
        etudiant.setEnabled(etdSelected);
        enseignant.setEnabled(ensSelected);
        
        //etudiant icon
		BufferedImage etudiantImage = null;
		try {
			etudiantImage = ImageIO.read(new File("images/etudiant.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image detudiantImage = etudiantImage.getScaledInstance(18,18, Image.SCALE_SMOOTH);
		ImageIcon etudiantIcon = new ImageIcon(detudiantImage);
		etudiant.setIcon(etudiantIcon);
		
        //enseignant icon
		BufferedImage enseignantImage = null;
		try {
			enseignantImage = ImageIO.read(new File("images/enseignant.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image denseignantImage = enseignantImage.getScaledInstance(18,18, Image.SCALE_SMOOTH);
		ImageIcon enseignantIcon = new ImageIcon(denseignantImage);
		enseignant.setIcon(enseignantIcon);
        
        //navigation
        etudiant.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cl.show(cards, "sign up etudiant page");
        	}
        });
        enseignant.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cl.show(cards, "sign up enseignant page");
        	}
        });
        
		setBackground(Color.WHITE);           
		add(contentPane);
	}

	public UtilisateurController getUc() {
		return uc;
	}

	public void setUc(UtilisateurController uc) {
		this.uc = uc;
	}
	
}
