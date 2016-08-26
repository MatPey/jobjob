/**
 * 
 */
package main;


import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * <b>D�finit la fen�tre principale de l'application qui va g�rer l'interaction des diff�rents panneaux </b>
 * 
 * 
 * <ul>
 * <li>Un panneau sera d�clar� pour chaque interface/�tape de l'application ...</li>
 * </ul>
 * 
 * 
 * @author Mathieu et Cyril
 * @version 1.02
 */

public class FenetrePrincipale extends JFrame implements ActionListener
{
	private panelAccueil panAccueil;
	private panelFormulaire panFormulaire;
	private panelCandidat panCandidat;
	private panelQuestion panQuestion;
	private panelFin panFin;
	
	private int compteurQuestions = 0;

	
	/**
	 * 
	 * @throws HeadlessException Si jamais il y a un probl�me d'environnement avec le clavier et/ou souris
	 * @throws ParseException 
	 */

	public FenetrePrincipale() throws HeadlessException, ParseException 
	{

		this.setTitle("Job-Job"); 
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setResizable(true);
		this.setMinimumSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		
		panAccueil = new panelAccueil();
		panFormulaire = new panelFormulaire();
		panCandidat = new panelCandidat();
		panQuestion = new panelQuestion();
		panFin = new panelFin();

		this.panAccueil.itemCandidatExistant.addActionListener(this);
		this.panAccueil.itemNouveauCandidat.addActionListener(this);
		this.panAccueil.itemNouveauTest.addActionListener(this);
		this.panAccueil.itemQuitter.addActionListener(this);
		this.panAccueil.itemAide.addActionListener(this);
		
		this.setContentPane(panAccueil); // imbrication de notre panel dans notre fen�tre

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}


	
	/** Fonction qui r�cup�re tous les ActionListener de tous les panneaux, issues des diff�rents fichiers
	 * @param arg0
	 * 		Ce param�tre va permettre d'ouvrir un panneau selon les condition ci-dessous
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String composant=arg0.getActionCommand();
		
		/**
		 * On va avoir par la suite tout les encha�nements entre les diff�rents panel � charger dans notre fen�tre
		 */
		// Passage du panAccueil au PanFormulaire
			//Premi�re possibilit�, c'est un nouveau candidat, le champ N� identifiant sera gris�
			if(arg0.getSource() == this.panAccueil.itemNouveauCandidat)
			{	
				panFormulaire.fieldId.setEditable(false);
				
				panFormulaire.itemSauvegarder.setEnabled(true);

				panFormulaire.itemSauvegarder.addActionListener(this);	
				panFormulaire.boutonSave.addActionListener(this);
				
				this.getContentPane().removeAll();
				this.setContentPane(panFormulaire);
				this.validate();
			}
			//Seconde possibilit�, c'est un candidat existant, seul le champ N� identifiant sera accessible
			if(arg0.getSource() == this.panAccueil.itemCandidatExistant)
			{	
				panFormulaire.fieldNom.setEditable(false);
				panFormulaire.fieldPrenom.setEditable(false);
				panFormulaire.fieldMail.setEditable(false);
				panFormulaire.fieldTelephone.setEditable(false);
				
				panFormulaire.itemSauvegarder.setEnabled(true);

				panFormulaire.itemSauvegarder.addActionListener(this);	
				panFormulaire.boutonSave.addActionListener(this);
				
				this.getContentPane().removeAll();
				this.setContentPane(panFormulaire);
				this.validate();
			}
		
			
		//Passage du panFormulaire au panCandidat
			if(arg0.getSource() == this.panFormulaire.boutonSave || arg0.getSource() == this.panFormulaire.itemSauvegarder)
			{	
				panCandidat.buttonStart.addActionListener(this);
				
				this.getContentPane().removeAll();
				this.setContentPane(panCandidat);
				this.validate();
			}
		
			
		//Passage du panCandidat au panQuestion
			if(arg0.getSource() == this.panCandidat.buttonStart)
			{	

				this.getContentPane().removeAll();
				this.setContentPane(panQuestion);
				this.validate();
			}
			
			
		//Passage d'une question � une autre ?

		
			
		//Passage du panQuestion au panFin
			if(compteurQuestions == 15)
			{	
				this.getContentPane().removeAll();
				this.setContentPane(panFin);
				this.validate();
			}
		
			
//		/**
//		 * Les actions � mener sur notre panel Accueil, charg� initialement
//		 */
//		//Quitter l'appli	
//			if((arg0.getSource() == this.panAccueil.itemQuitter) || (arg0.getSource() == this.panFormulaire.itemQuitter))
//			{	
//				// a voir, message erreur dans console
//				this.dispose();
//			}
//		// Affichage de l'aide
//			if(arg0.getSource() == this.panAccueil.itemAide)
//			{	
//				JOptionPane.showMessageDialog(null, "Cette interface n'est pas d�velopp�e - en attente de l'�quipe Projet");
//			}

		/**
		 * Rechargement d'un formulaire depuis un panel Formulaire
		 */

	}

	
	
//=============================================== Fonctions cr��es initialement par David, non utilis�es pour l'instant, mais elles sont peut-�tre poas l� pour rien	=====================================================
	/**
     * Constructeur non utilis� sous la version 1.00 � 1.xx
     * @deprecated Depuis v1.00, remplac� par FenetrePrincipale() throws HeadlessException 
     */
	public FenetrePrincipale(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
     * Constructeur non utilis� sous la version 1.00 � 1.xx
     * @deprecated Depuis v1.00, remplac� par FenetrePrincipale() throws HeadlessException 
     */
	public FenetrePrincipale(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	/**
     * Constructeur non utilis� sous la version 1.00 � 1.xx
     * @deprecated Depuis v1.00, remplac� par FenetrePrincipale() throws HeadlessException 
     */
	public FenetrePrincipale(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
}
