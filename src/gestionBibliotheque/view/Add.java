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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionBibliotheque.controller.DocumentController;
import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.document.ExemplaireDAO;
import gestionBibliotheque.dao.document.LivreDAO;
import gestionBibliotheque.dao.document.RevueDAO;
import gestionBibliotheque.dao.services.EmpruntDAO;
import gestionBibliotheque.model.documents.Exemplaire;
import gestionBibliotheque.model.documents.Livre;
import gestionBibliotheque.model.documents.Revue;
import gestionBibliotheque.model.services.Emprunt;
import gestionBibliotheque.model.utilisateurs.Bibliothecaire;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Add extends JFrame {
	private static final long serialVersionUID = 1L;
	private PlaceholderTextField docTitre, 
								 docNomEditeur, 
								 docAuteur, 
								 docDateDeParution, 
								 docNumLecteur, 
								 docDateDebut, 
								 docDateFin;
	private JComboBox<?> docSelectTitre, 
						 docEtat, 
						 docPeriodicite, 
						 docCote;
	private JPanel container, main, footer, header;
	private JLabel addLabel, formTitre;
	
	public Add(String docType, String titre,Bibliothecaire bib,JPanel panel,String mot) {
		this.setTitle(titre);
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		
		container = new JPanel();
		container.setLayout(new BorderLayout(0,0));
		this.setContentPane(container);
		
		main = new JPanel();
		main.setBorder(new EmptyBorder(50,50,50,50));
		
		header = new JPanel();
		header.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		footer = new JPanel();
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		
		container.add(main, BorderLayout.CENTER);
		container.add(footer, BorderLayout.SOUTH);
		container.add(header, BorderLayout.NORTH);
		
		formTitre = new JLabel();
		formTitre.setFont(new Font("Titillium Web Light", Font.PLAIN, 20));
		formTitre.setForeground(new Color(0,200,120));	
		header.add(formTitre);
		
		String[] etat = { "Bon état", "Mauvais état"};
		docEtat = new JComboBox<Object>(etat);
		docEtat.setSelectedIndex(0);
		docEtat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		docEtat.setBackground(Color.WHITE);
		docEtat.setForeground(Color.DARK_GRAY);
		
		String[] periodicite = { "Quotidienne", "Hebdomadaire", "Mensuel"};
		docPeriodicite = new JComboBox<Object>(periodicite);
		docPeriodicite.setSelectedIndex(0);
		docPeriodicite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		docPeriodicite.setBackground(Color.WHITE);
		docPeriodicite.setForeground(new Color(0,200,100));
		
		docNumLecteur = new PlaceholderTextField("");
        docNumLecteur.setPlaceholder("Numéro de Lecteur");
        Font docNumLecteurFont = new Font("Arial", Font.PLAIN, 16);
        docNumLecteur.setFont(docNumLecteurFont);
        docNumLecteur.setBackground(new Color(255,255,255));
        docNumLecteur.setForeground(new Color(50,50,50));
        docNumLecteur.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        
        docDateDebut = new PlaceholderTextField("");
        docDateDebut.setPlaceholder("Date de début(jour/mois/année)");
        Font docDateDebutFont = new Font("Arial", Font.PLAIN, 16);
        docDateDebut.setFont(docDateDebutFont);
        docDateDebut.setBackground(new Color(255,255,255));
        docDateDebut.setForeground(new Color(50,50,50));
        docDateDebut.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        
        docDateFin = new PlaceholderTextField("");
        docDateFin.setPlaceholder("Date fin(jour/mois/année)");
        Font docDateFinFont = new Font("Arial", Font.PLAIN, 16);
        docDateFin.setFont(docDateFinFont);
        docDateFin.setBackground(new Color(255,255,255));
        docDateFin.setForeground(new Color(50,50,50));
        docDateFin.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
		
	
		try {
			LivreDAO lDAO = new LivreDAO();
			String[] ListTitre =lDAO.getAllTitre();
			docSelectTitre = new JComboBox<Object>(ListTitre);
			docSelectTitre.setSelectedIndex(0);
			docSelectTitre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			docSelectTitre.setBackground(Color.WHITE);
			docSelectTitre.setForeground(new Color(0,200,100));	
			
			ExemplaireDAO exDAO = new ExemplaireDAO();
			Integer cote[] = exDAO.getAllCote();
			docCote = new JComboBox<Object>(cote);
			docCote.setSelectedIndex(0);
			docCote.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			docCote.setBackground(Color.WHITE);
			docCote.setForeground(new Color(0,200,100));
		
		addLabel = new JLabel("");
        BufferedImage addImg = null;
		try {
			addImg = ImageIO.read(new File("images/save.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image daddImg = addImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon addIcon = new ImageIcon(daddImg);
		addLabel.setIcon(addIcon);
        addLabel.setPreferredSize(new Dimension(60,60));
        addLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(docType =="livre") {
					Livre l = new Livre(0,docTitre.getText(),docNomEditeur.getText(),docAuteur.getText());
					@SuppressWarnings("unused")
					DocumentController dCtrl = new DocumentController("add",l);
					JOptionPane.showMessageDialog(addLabel, "Ce livre a été ajouté avec succès!",  
                            "Ajout avec succès",  
                            JOptionPane.INFORMATION_MESSAGE);
					dispose();
					panel.removeAll();
	                panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
	                panel.revalidate();
	                panel.repaint();
	                if(mot=="") {
						try {
							ExemplaireDAO exempDAO = new ExemplaireDAO();
							List<Object> obj = exempDAO.getAllExemplaire();
							for( int i=0; i<obj.size();i++) {
								Object[] o =(Object[]) obj.get(i);
	            				if(i%2==1) {
	            					panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
	            				}
	            				if(i%2==0) {
	            					panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
	            				}
								panel.add(new DocComponent(bib,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",panel,mot));
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
	                					panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
	                				}
	                				if(i%2==0) {
	                					panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
	                				}
	                				panel.add(new DocComponent(bib,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",panel,mot));
	                			}
	                			
	                		
	                		}catch(DAOException ex) {
	                			System.out.println(ex);
	                		}
	                }
	                
				}else if(docType =="exemplaire") {
					String et= etat[docEtat.getSelectedIndex()];
					int id=0;
					try {
						LivreDAO lDAO = new LivreDAO();
						id=lDAO.getIdByTitre(ListTitre[docSelectTitre.getSelectedIndex()]);
						
					}catch(DAOException ex) {
						System.out.println(ex);
					}
					Exemplaire exemp = new Exemplaire(0,et,id);
					@SuppressWarnings("unused")
					DocumentController dCtrl = new DocumentController("add",exemp);
					JOptionPane.showMessageDialog(addLabel, "Cet exemplaire a été ajouté avec succès!",  
                            "Ajout avec succès",  
                            JOptionPane.INFORMATION_MESSAGE);
					dispose();
					panel.removeAll();
	                panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
	                panel.revalidate();
	                panel.repaint();
	                if(mot=="") {
						try {
							ExemplaireDAO exempDAO = new ExemplaireDAO();
							List<Object> obj = exempDAO.getAllExemplaire();
							for( int i=0; i<obj.size();i++) {
								Object[] o =(Object[]) obj.get(i);
	            				if(i%2==1) {
	            					panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
	            				}
	            				if(i%2==0) {
	            					panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
	            				}
								panel.add(new DocComponent(bib,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",panel,mot));
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
	                					panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
	                				}
	                				if(i%2==0) {
	                					panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
	                				}
	                				panel.add(new DocComponent(bib,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",panel,mot));
	                			}
	                			
	                		
	                		}catch(DAOException ex) {
	                			System.out.println(ex);
	                		}
	                }
				}else if(docType == "revue") {
					Revue r = new Revue(0,docTitre.getText(),periodicite[docPeriodicite.getSelectedIndex()],docDateDeParution.getText());
					@SuppressWarnings("unused")
					DocumentController dCtrl = new DocumentController("add",r);
					JOptionPane.showMessageDialog(addLabel, "Cette revue a été ajoutée avec succès!",  
                            "Ajout avec succès",  
                            JOptionPane.INFORMATION_MESSAGE);
					dispose();
					panel.removeAll();
	                panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
	                panel.revalidate();
	                panel.repaint();
	                if(mot=="") {
						try {
							RevueDAO revueDAO = new RevueDAO();
							List<Revue> obj = revueDAO.getAllRevue();
							for( int i=0; i<obj.size();i++) {
								Revue o = obj.get(i);
								panel.add(new DocComponent(bib,"revue",0,o.getTitre(),"","","","",o.getId(),o.getPeriodicite(),o.getDateParution(),panel,mot));									}
						}catch(DAOException ex) {
							System.out.println(ex);
						}
	
	                }else {
						try {
							RevueDAO revueDAO = new RevueDAO();
							List<Revue> obj = revueDAO.searchRevue(mot);
							for( int i=0; i<obj.size();i++) {
								Revue o = obj.get(i);
								panel.add(new DocComponent(bib,"revue",0,o.getTitre(),"","","","",o.getId(),o.getPeriodicite(),o.getDateParution(),panel,mot));									}
						}catch(DAOException ex) {
							System.out.println(ex);
						}
	                }
				}else if(docType == "emprunt") {
					
					Emprunt emp = new Emprunt(Integer.parseInt(docNumLecteur.getText()), cote[docCote.getSelectedIndex()], docDateDebut.getText(), docDateFin.getText(), bib.getId());
						
					try {
						
						EmpruntDAO empDAO= new EmpruntDAO();
					
						if(empDAO.addEmprunt(emp)) {
							JOptionPane.showMessageDialog(addLabel, "Cette emprunt a été ajouté avec succès!",  
		                            "Ajout avec succès",  
		                            JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}else {
							JOptionPane.showMessageDialog(addLabel, "Cette emprunt est impossible!",  
		                            "Erreur d'ajout",  
		                            JOptionPane.INFORMATION_MESSAGE);
							}
						} catch(DAOException ex) {
							System.out.println(ex);
					}
					
					panel.removeAll();
	                panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
	                panel.revalidate();
	                panel.repaint();
	                try {
	    				EmpruntDAO empDAO = new EmpruntDAO();
	    				List<Object> obj = empDAO.getAllEmprunt();
	    				for( int i=0; i<obj.size();i++) {
	    					Object[] o =(Object[]) obj.get(i);
	        				if(i%2==1) {
	        					panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
	        				}
	        				if(i%2==0) {
	        					panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
	        				}
	        				panel.add(new EmpruntComponent(bib,(int)o[0], (int)o[1], (String)o[2], (String)o[3], (int)o[4], panel));
	    				}	
	    			}catch(DAOException ex) {
	    				System.out.println(ex);
	    			}
				}
			}
		});
		}catch(DAOException ex) {
			System.out.println(ex);
		}
        footer.add(addLabel);
        
		docTitre = new PlaceholderTextField("");
		docTitre.setPlaceholder("Titre");
        Font docTitreFont = new Font("Arial", Font.PLAIN, 16);
        docTitre.setFont(docTitreFont);
        docTitre.setBackground(new Color(255,255,255));
        docTitre.setForeground(new Color(50,50,50));
        docTitre.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        
        docNomEditeur = new PlaceholderTextField("");
        docNomEditeur.setPlaceholder("Nom éditeur");
        Font docNomEditeurFont = new Font("Arial", Font.PLAIN, 16);
        docNomEditeur.setFont(docNomEditeurFont);
        docNomEditeur.setBackground(new Color(255,255,255));
        docNomEditeur.setForeground(new Color(50,50,50));
        docNomEditeur.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        
        docAuteur = new PlaceholderTextField("");
        docAuteur.setPlaceholder("Auteur");
        Font docAuteurFont = new Font("Arial", Font.PLAIN, 16);
        docAuteur.setFont(docAuteurFont);
        docAuteur.setBackground(new Color(255,255,255));
        docAuteur.setForeground(new Color(50,50,50));
        docAuteur.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        
        docDateDeParution = new PlaceholderTextField("");
        docDateDeParution.setPlaceholder("Date de parution (yyyy-MM-dd)");
        Font docDateDeParutionFont = new Font("Arial", Font.PLAIN, 16);
        docDateDeParution.setFont(docDateDeParutionFont);
        docDateDeParution.setBackground(new Color(255,255,255));
        docDateDeParution.setForeground(new Color(50,50,50));
        docDateDeParution.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        
        
		
		main.setLayout(new GridLayout(5,1, 20,5));
		
		if(docType == "livre") {
			formTitre.setText("Ajoutez un livre");
			main.add(docTitre);
			main.add(docNomEditeur);
			main.add(docAuteur);
		}
		else if(docType == "revue") {
			formTitre.setText("Ajoutez un revue");
			main.add(docTitre);
			main.add(docPeriodicite);
			main.add(docDateDeParution);
		}
		else if(docType == "emprunt") {
			formTitre.setText("Ajoutez un emprunt");
			main.add(docNumLecteur);
			main.add(docCote);
			main.add(docDateDebut);
			main.add(docDateFin);
		}
		else if(docType == "exemplaire") {
			formTitre.setText("Ajoutez un exemplaire");
			main.add(docEtat);
			main.add(docSelectTitre);
		}
	}
}
