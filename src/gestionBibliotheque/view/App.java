package gestionBibliotheque.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cl;
	private JPanel cards;
	private SignIn signInPage;
	private SignUpEtudiant signUpEtudiantPage;
	private SignUpEnseignant signUpEnseignantPage;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App app = new App();
					app.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
					app.setBounds(160,80,1050,630);
					app.setBackground(Color.WHITE);
	                app.setVisible(true);
	                
                    Dimension d=app.getSize();
                    d.width=700;
                    d.height=520;
                    app.setMinimumSize(d);
	                
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		
		cl = new CardLayout();
		cards = new JPanel(cl);
		cards.setBackground(Color.WHITE);
		
		signInPage = new SignIn(cl, cards);
		signInPage.setBackground(Color.WHITE);
		
        cards.add(signInPage, "sign in page");
        
        signUpEtudiantPage = new SignUpEtudiant(cl, cards, false, true, true);
        signUpEtudiantPage.setBackground(Color.WHITE);
        cards.add(signUpEtudiantPage, "sign up etudiant page");
        
        signUpEnseignantPage = new SignUpEnseignant(cl, cards, true, false, true);
        signUpEnseignantPage.setBackground(Color.WHITE);
        cards.add(signUpEnseignantPage, "sign up enseignant page");
        
        setContentPane(cards);

        cl.show(cards, "sign in page");
        
	}
}


