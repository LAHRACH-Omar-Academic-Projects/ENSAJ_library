package gestionBibliotheque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.services.EmpruntDAO;
import gestionBibliotheque.model.utilisateurs.Adherant;
import gestionBibliotheque.model.utilisateurs.Bibliothecaire;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class EmpruntRoute extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel emprunt_panel;
	private JPanel ajouterEmp_panel;
	private JLabel addEmpLabel;
	private JScrollPane scrollPane;
	private Utilisateur user;
	private String docType;

	public EmpruntRoute(Utilisateur user) {
		
		this.setLayout(new BorderLayout(0,0));
		
		emprunt_panel = new JPanel();
		emprunt_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 35,35));
		emprunt_panel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane(emprunt_panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		if(user instanceof Bibliothecaire) {
			try {
				EmpruntDAO empDAO = new EmpruntDAO();
				List<Object> obj = empDAO.getAllEmprunt();
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
  
        ajouterEmp_panel = new JPanel();
        ajouterEmp_panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        ajouterEmp_panel.setBackground(Color.WHITE);

        addEmpLabel = new JLabel("");
        BufferedImage addEmpImg = null;
		try {
			addEmpImg = ImageIO.read(new File("images/addEmp.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image daddEmpImg = addEmpImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon addEmpIcon = new ImageIcon(daddEmpImg);
		addEmpLabel.setIcon(addEmpIcon);
		addEmpLabel.setPreferredSize(new Dimension(60,60));
        addEmpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addEmpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Add frame = new Add("emprunt", "Ajouter un emprunt", (Bibliothecaire)user, emprunt_panel, "");
				frame.setVisible(true);
			}
		});
        
	        ajouterEmp_panel.add(addEmpLabel);
        }    
        if(user instanceof Bibliothecaire) {
        	this.add(ajouterEmp_panel, BorderLayout.SOUTH);
        }
        
        else if(user instanceof Adherant) {
        	try {
    			EmpruntDAO empDAO = new EmpruntDAO();
    			List<Object> obj = empDAO.getAllEmprunt(((Adherant) user).getNumLecteur());
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
        }
        this.add(scrollPane, BorderLayout.CENTER);
        
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
