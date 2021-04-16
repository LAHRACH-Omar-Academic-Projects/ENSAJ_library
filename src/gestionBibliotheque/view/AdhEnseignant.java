package gestionBibliotheque.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gestionBibliotheque.dao.DAOException;
import gestionBibliotheque.dao.utilisateurs.EnseignantDAO;
import gestionBibliotheque.model.utilisateurs.Enseignant;
import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class AdhEnseignant extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel recherche_panel;
	private JPanel ens_panel;
	private JPanel tabNavigation;
	private JPanel tab1, tab1_footer; 
	private JPanel tab2, tab2_footer;
	private JLabel tab1_label, tab2_label;
	private JScrollPane scrollPane;
	private boolean tab2Active;
	private Utilisateur user;

	public AdhEnseignant(Utilisateur user, CardLayout cl, AdherantRoute route) {
		this.tab2Active = true;
		this.setLayout(new BorderLayout(0,0));
		
		recherche_panel = new JPanel();
		recherche_panel.setLayout(new BorderLayout(0,0));
		recherche_panel.setBorder(new EmptyBorder(10,10,10,10));
		recherche_panel.setBackground(Color.WHITE);
			
		tabNavigation = new JPanel();
		tabNavigation.setLayout(new FlowLayout(FlowLayout.LEFT, 0,0));
		tabNavigation.setBorder(new EmptyBorder(10,10,10,10));
		tabNavigation.setBackground(Color.WHITE);
		recherche_panel.add(tabNavigation, BorderLayout.CENTER);
		
		tab2_footer = new JPanel();
		tab2_footer.setPreferredSize(new Dimension(150, 5));
		setTab2FooterBg(new Color(50, 230, 150));
		tab2_label = new JLabel("Enseignant");
		tab2_label.setHorizontalAlignment(SwingConstants.CENTER);
		Font tab2_label_font = new Font("Arial", Font.PLAIN, 15);
		tab2_label.setFont(tab2_label_font);
		setTab2LabelColor(new Color(50, 230, 150));
		tab2 = new JPanel();
		tab2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setTab2LabelColor(new Color(50, 230, 150));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!isTab2Active()) 
					setTab2LabelColor(Color.DARK_GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setTab2Active(true);
				setTab2LabelColor(new Color(50, 230, 150));
				setTab2FooterBg(new Color(50, 230, 150));
				setTab1LabelColor(Color.DARK_GRAY);
				setTab1FooterBg(Color.WHITE);
				cl.show(route, "enseignant page");
			}
		});
		tab2.setLayout(new BorderLayout(0,0));
		tab2.setPreferredSize(new Dimension(150, 60));
		tab2.setBorder(new EmptyBorder(10,0,0,0));
		tab2.setBackground(Color.WHITE);
		tab2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tab2.add(tab2_label, BorderLayout.CENTER);
		tab2.add(tab2_footer, BorderLayout.SOUTH);
		
		
		
		tab1_footer = new JPanel();
		tab1_footer.setPreferredSize(new Dimension(150, 5));
		setTab1FooterBg(Color.WHITE);
		tab1_label = new JLabel("Etudiant");
		tab1_label.setHorizontalAlignment(SwingConstants.CENTER);
		Font tab1_label_font = new Font("Arial", Font.PLAIN, 15);
		tab1_label.setFont(tab1_label_font);
		setTab1LabelColor(Color.DARK_GRAY);
		tab1 = new JPanel();
		tab1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setTab1LabelColor(new Color(50, 230, 150));
			}
			@Override
			public void mouseExited(MouseEvent e) {
					setTab1LabelColor(Color.DARK_GRAY);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				cl.show(route, "etudiant page");
			}
		});
		tab1.setLayout(new BorderLayout(0,0));
		tab1.setPreferredSize(new Dimension(150, 60));
		tab1.setBorder(new EmptyBorder(10,0,0,0));
		tab1.setBackground(Color.WHITE);
		tab1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tab1.add(tab1_label, BorderLayout.CENTER);
		tab1.add(tab1_footer, BorderLayout.SOUTH);
		tabNavigation.add(tab1);
		tabNavigation.add(tab2);
		this.add(recherche_panel, BorderLayout.NORTH);
		
		ens_panel = new JPanel();
		ens_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 35,35));
		ens_panel.setBackground(Color.WHITE);
		ens_panel.setPreferredSize(new Dimension(0,(24*300+24*35)));
        
		try {
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
		}catch(DAOException daoe) {
			daoe.getMessage();
		}
        
        scrollPane = new JScrollPane(ens_panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		this.add(scrollPane, BorderLayout.CENTER);		
	}
	
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public void setTab1LabelColor(Color color) {
		tab1_label.setForeground(color);
	}
	public void setTab1FooterBg(Color color) {
		tab1_footer.setBackground(color);
	}
	public void setTab2LabelColor(Color color) {
		tab2_label.setForeground(color);
	}
	public void setTab2FooterBg(Color color) {
		tab2_footer.setBackground(color);
	}	
	public boolean isTab2Active() {
		return tab2Active;
	}
	public void setTab2Active(boolean tab2Active) {
		this.tab2Active = tab2Active;
	}
}
