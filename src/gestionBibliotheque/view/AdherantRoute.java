package gestionBibliotheque.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

import gestionBibliotheque.model.utilisateurs.Utilisateur;

public class AdherantRoute extends JPanel {
	private static final long serialVersionUID = 1L;
	private Utilisateur user;
	
	public AdherantRoute(Utilisateur user) {
		this.user = user;
		this.setBackground(Color.WHITE);
		CardLayout cardlayout = new CardLayout();
		AdhEtudiant adhetudiant  = new AdhEtudiant(user, cardlayout, this);
		AdhEnseignant adhenseignant  = new AdhEnseignant(user, cardlayout, this);
		this.setLayout(cardlayout);
		this.add(adhetudiant, "etudiant page");
		this.add(adhenseignant, "enseignant page");
		cardlayout.show(this, "etudiant page");
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
}


