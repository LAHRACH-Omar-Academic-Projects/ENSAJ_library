package gestionBibliotheque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.document.ExemplaireDAO;
import gestionBibliotheque.dao.document.RevueDAO;
import gestionBibliotheque.model.documents.Revue;
import gestionBibliotheque.model.utilisateurs.Bibliothecaire;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class DocRoute extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel recherche_panel;
	private JPanel doc_panel;
	private JPanel ajouterDoc_panel;
	private PlaceholderTextField recherche_field;
	private JLabel addLabel, addExLabel;
	private JScrollPane scrollPane;
	private Utilisateur user;
	private String docType;

	public DocRoute(Utilisateur user, String docType) {
		
		this.setLayout(new BorderLayout(0,0));
		
		recherche_panel = new JPanel();
		recherche_panel.setLayout(new BorderLayout(0,0));
		recherche_panel.setBorder(new EmptyBorder(10,10,10,10));
		recherche_panel.setBackground(Color.WHITE);
		
		doc_panel = new JPanel();
		doc_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 35,35));
		doc_panel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane(doc_panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		if(docType=="livre") {
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
    				doc_panel.add(new DocComponent(user,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",doc_panel,""));
				}
				
			
			}catch(DAOException ex) {
				System.out.println(ex);
			}
		}else {
			try {
				RevueDAO rvDAO = new RevueDAO();
				List<Revue> rv = rvDAO.getAllRevue();
				for( int i=0; i<rv.size();i++) {
					Revue o =rv.get(i);
    				if(i%2==1) {
    					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
    				}
    				if(i%2==0) {
    					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
    				}
					doc_panel.add(new DocComponent(user,"revue",0,o.getTitre(),"","","","",o.getId(),o.getPeriodicite(),o.getDateParution(),doc_panel,""));
				}
				
			
			}catch(DAOException ex) {
				System.out.println(ex);
			}
		}
        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            protected void updateFieldState() {
                String text = recherche_field.getText();
                doc_panel.removeAll();
                doc_panel.getGraphics().clearRect(0, 0, doc_panel.getWidth(), doc_panel.getHeight());
                doc_panel.revalidate();
                doc_panel.repaint();
                if(docType=="livre") {
                	try {
            			ExemplaireDAO exempDAO = new ExemplaireDAO();
            			List<Object> obj = exempDAO.searchExemplaire(text);
            			for( int i=0; i<obj.size();i++) {
            				Object[] o =(Object[]) obj.get(i);
            				if(i%2==1) {
            					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
            				}
            				if(i%2==0) {
            					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
            				}
            				doc_panel.add(new DocComponent(user,"livre",(int)o[0], (String)o[1], (String)o[2], (String)o[3], (String)o[4], (String)o[5],0,"","",doc_panel,text));
            			}
            			
            		
            		}catch(DAOException ex) {
            			System.out.println(ex);
            		}
                }
                else {
        			try {
        				RevueDAO rvDAO = new RevueDAO();
        				List<Revue> rv = rvDAO.searchRevue(text);
        				for( int i=0; i<rv.size();i++) {
        					Revue o =rv.get(i);
            				if(i%2==1) {
            					doc_panel.setPreferredSize(new Dimension(0,((i+1)*300/2+(i+1)*150/2)));
            				}
            				if(i%2==0) {
            					doc_panel.setPreferredSize(new Dimension(0,((i+2)*300/2+(i+2)*150/2)));
            				}
        					doc_panel.add(new DocComponent(user,"revue",0,o.getTitre(),"","","","",o.getId(),o.getPeriodicite(),o.getDateParution(),doc_panel,text));
        				}
        			}catch(DAOException ex) {
        				System.out.println(ex);
        			}
        		}
            }
        };
		
		this.add(recherche_panel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		recherche_field = new PlaceholderTextField("");
		if(docType=="livre") {
			recherche_field.setPlaceholder("Rechercher un livre par titre, auteur ou bien un nom d'éditeur");
		}
		else {
			recherche_field.setPlaceholder("Rechercher une revue par titre");
		}
     	
		Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        recherche_field.setFont(fieldFont);
        recherche_field.setBackground(new Color(255,255,255));
        recherche_field.setForeground(new Color(50,50,50));
        recherche_field.setColumns(30);
        recherche_field.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(1, new Color(0,120,255), new Point(15,15)), 
                new EmptyBorder(new Insets(10, 20, 10, 20))));
        recherche_panel.add(recherche_field,BorderLayout.CENTER);

        recherche_field.getDocument().addDocumentListener(dl);
        
        ajouterDoc_panel = new JPanel();
        ajouterDoc_panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        ajouterDoc_panel.setBackground(Color.WHITE);
        if(docType == "revue") {
        	addLabel = new JLabel("");
	        BufferedImage addImg = null;
			try {
				addImg = ImageIO.read(new File("images/addRv.png"));
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
					Add frame = new Add(docType, "Ajouter une revue",(Bibliothecaire) user,doc_panel,recherche_field.getText());
					frame.setVisible(true);
				}
			});
	        ajouterDoc_panel.add(addLabel);
        }
        
        if(docType == "livre") {
        	addExLabel = new JLabel("");
	        BufferedImage addExImg = null;
			try {
				addExImg = ImageIO.read(new File("images/addEx.png"));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			Image daddExImg = addExImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
			ImageIcon addExIcon = new ImageIcon(daddExImg);
			addExLabel.setIcon(addExIcon);
			addExLabel.setPreferredSize(new Dimension(60,60));
			addExLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			addExLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Add frame = new Add("exemplaire", "Ajouter un exemplaire",(Bibliothecaire) user,doc_panel,recherche_field.getText());
					frame.setVisible(true);
				}
			});
			addLabel = new JLabel("");
	        BufferedImage addImg = null;
			try {
				addImg = ImageIO.read(new File("images/addLv.png"));
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
					Add frame = new Add(docType, "Ajouter un livre",(Bibliothecaire) user,doc_panel,recherche_field.getText());
					frame.setVisible(true);
				}
			});
	        ajouterDoc_panel.add(addExLabel);
	        ajouterDoc_panel.add(addLabel); 
        }
         
        
        if(user instanceof Bibliothecaire) {
        	this.add(ajouterDoc_panel, BorderLayout.SOUTH);
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
}
