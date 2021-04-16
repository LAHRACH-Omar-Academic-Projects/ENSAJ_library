package gestionBibliotheque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.utilisateurs.AdherantDAO;
import gestionBibliotheque.model.utilisateurs.Adherant;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class NouveauAdherantRoute extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel adh_panel;
	private JScrollPane scrollPane;
	private Utilisateur user;
	private String docType;

	public NouveauAdherantRoute(Utilisateur user) {
		
		this.setLayout(new BorderLayout(0,0));
		
		adh_panel = new JPanel();
		adh_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 35,35));
		adh_panel.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane(adh_panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
			try {
				AdherantDAO adhDAO = new AdherantDAO();
				List<Adherant> obj = adhDAO.getNotVerifiedAdherant();
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
		this.add(scrollPane);
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
