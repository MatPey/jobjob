package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

/**
 * Le panelFormulaire est le panel qui sera charg� par la fen�tre Principale apr�s appui sur le bouton "NouveauTest".
 * Il est destin� � �tre rempli par le recruteur
 * @author Mathieu
 * @throws ParseException
 */
public class panelFormulaire extends JPanel {

	//D�claration des �l�ments de la barre de Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFichier = new JMenu("Fichier");
	private JMenu menuAide = new JMenu("Aide");
	protected JMenu itemNouveauTest= new JMenu("Nouveau Test");
	protected JMenuItem itemCandidatExistant = new JMenuItem("Candidat Existant");
	protected JMenuItem itemNouveauCandidat = new JMenuItem("Nouveau Candidat");
	protected JMenuItem itemSauvegarder = new JMenuItem("Sauvegarder");
	protected JMenuItem itemQuitter = new JMenuItem("Quitter");
	protected JMenuItem itemAide = new JMenuItem("Aide");
	private JLabel statusBar = new JLabel();
	private JMenuBar menuBar2 = new JMenuBar();
	protected JButton boutonSave = new JButton("Sauvegarder");

	protected JPanel panelSaisie;

/**
 * Constructeur du panelFormulaire
 * 	Sch�ma de l'imbrication de ses Layouts:
 * 		C'est un BorderLayout contenant:
 * 			- Nord: Un barre de Menu menuBar
 * 			- Sud: Vide
 * 			- West: Un panel logo (GridLayout avec 3 colonnes, 3 lignes) qui contient notre logo dans la 2nde case du haut
 * 			- East: Un panel "tampon", panelDroite, en GridLayout � 1 seule colonne, contenant un JLabel. On peut d�finir la largeur de ce JLabel afin de g�rer l'�cart du panneau central avec notre bordure notamment
 * 			- Center: Un panel principal, panelCentre, en GridLayout 1 colonne contenant :
 * 						- En haut : un panel panelCentreHaut, en BoxLayout, � l'alignement vertical. Il contient un panneau, panelSaisie
 * 								- panelSaisie : en GridLayout, 2 colonnes, contenant l'ensemble des champs de saisie.
 * 						- En Bas : un panel panelCentreBas, BoxLayout ax� vertical qui contient lui-m�me deux panels:
 * 								- en haut : un panelReponses, en GridLayout, 4 colonnes. Il est vide ici, mais servira pour le panelQuestion entre autres
 * 								- en bas : un panelElementBasCentre, en FlowLayout, centr�, permettant d'accueillir un �l�ment quelconque de mani�re centr�. Ici, le bouton "Save". La barre de progression dans le panelQuestion par exemple 
 * 
 * 
 * @throws ParseException
 */
	public panelFormulaire() throws ParseException {
		
		//Constitution de la barre de menu : menuBar
		menuBar.add(menuFichier);
		menuBar.add(menuAide);
		
		menuFichier.add(itemNouveauTest);
		itemNouveauTest.add(itemCandidatExistant);
		itemNouveauTest.add(itemNouveauCandidat);
		menuFichier.add(itemSauvegarder);
		itemSauvegarder.setEnabled(false);
		menuFichier.addSeparator();
		menuFichier.add(itemQuitter);
		
		menuAide.add(itemAide);
		
		
		
		//Cr�ation du panel de gauche, contenant le logo : panelLogo
		JPanel panelLogo = new JPanel();
		panelLogo.setLayout(new GridLayout(0, 3));
		
			//Cr�ation du logo
			ImageIcon logo = new ImageIcon("logoAFPA.png");
			Image img = logo.getImage();
			Image newImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			logo = new ImageIcon(newImg);
			JLabel logoFinal = new JLabel(logo);
			
			//Cr�ation des "cases vides"
			JLabel labelvide = new JLabel("");
			JLabel labelvide2 = new JLabel(" ");
			JLabel labelvide3 = new JLabel(" ");
			JLabel labelvide4 = new JLabel(" ");
			JLabel labelvide5 = new JLabel(" ");
			JLabel labelvide6 = new JLabel(" ");
			JLabel labelvide7 = new JLabel(" ");
			JLabel labelvide8 = new JLabel(" ");
	
			//Assemblage du panelLogo
			panelLogo.add(labelvide);
			panelLogo.add(logoFinal);
			panelLogo.add(labelvide2);
			panelLogo.add(labelvide3);
			panelLogo.add(labelvide4);
			panelLogo.add(labelvide5);
			panelLogo.add(labelvide6);
			panelLogo.add(labelvide7);
			panelLogo.add(labelvide8);
		

			
		//Cr�ation du panel qui ira au centre du panel principal : panelCentre
			JPanel panelCentre = new JPanel();
			panelCentre.setLayout(new GridLayout(0, 1));
			
				//Il contiendra lui-m�me 2 panels : le panel des champs de saisie, le panel en-dessous
									
					// Cr�ation du panel Central sup�rieur, qui contiendra le panelSaisie
						JPanel panelCentreHaut = new JPanel();
						panelCentreHaut.setLayout(new BoxLayout(panelCentreHaut, BoxLayout.PAGE_AXIS));
						
							//cr�ation du panel qui contiendra les champs de saisie :
							panelSaisie = new JPanel(new GridLayout(0, 2, 0, 20));
			
								//Cr�ation du contenu
								Font fontChampSaisie = new Font("Courier", Font.BOLD, 20);
								JLabel champ1 = new JLabel("Nom");
								champ1.setFont(fontChampSaisie);
								JLabel champ2 = new JLabel("Pr�nom");
								champ2.setFont(fontChampSaisie);
								JLabel champ3 = new JLabel("E-mail");
								champ3.setFont(fontChampSaisie);
								JLabel champ4 = new JLabel("T�l�phone");
								champ4.setFont(fontChampSaisie);
								JLabel champ5 = new JLabel("N� identifiant");
								champ5.setFont(fontChampSaisie);
								

								//Un FormattedTextField permet de mettre une contrainte � ce qui est entr� dans le TextField, � l'aide d'un Mask dont on d�finit la structure
								MaskFormatter maskNomPrenom = new MaskFormatter("**************************************************");
								maskNomPrenom.setValidCharacters("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ����������-�����");
								JFormattedTextField fieldNom = new JFormattedTextField(maskNomPrenom);
								JFormattedTextField fieldPrenom = new JFormattedTextField(maskNomPrenom);
								
								JFormattedTextField fieldMail = new JFormattedTextField();
								
								MaskFormatter maskTelephone = new MaskFormatter("**.**.**.**.**");
								maskTelephone.setValidCharacters("0123456789");
								JFormattedTextField fieldTelephone = new JFormattedTextField(maskTelephone);
								Candidat c = new Candidat();
								JFormattedTextField fieldId = new JFormattedTextField(c.numeroCandidat());
								fieldId.enable(false);
						
								//Assemblage du panelSaisie
								panelSaisie.add(champ1);
								panelSaisie.add(fieldNom);
								panelSaisie.add(champ2);
								panelSaisie.add(fieldPrenom);
								panelSaisie.add(champ3);
								panelSaisie.add(fieldMail);
								panelSaisie.add(champ4);
								panelSaisie.add(fieldTelephone);
								panelSaisie.add(champ5);
								panelSaisie.add(fieldId);
								
								
						panelSaisie.setMaximumSize(new Dimension(400, 200));			
						panelCentreHaut.add(panelSaisie, BorderLayout.CENTER);
						
					//cr�ation du panel CentralCentre inf�rieur, qui contiendra 2 panels
						JPanel panelCentreBas = new JPanel();
						panelCentreBas.setLayout(new BoxLayout(panelCentreBas, BoxLayout.Y_AXIS));
						
							//Un Panel pour les futurs 4 r�ponses possibles
							JPanel panelReponses = new JPanel(new GridLayout(0, 4));
						
							//Un panel pour un �l�ment centr�
							JPanel panelElementBasCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));
							panelElementBasCentre.add(boutonSave);

						panelCentreBas.add(panelReponses);
						panelCentreBas.add(panelElementBasCentre);
					
			
			//Assemblage du panelCentralCentre
			panelCentre.add(panelCentreHaut);
			panelCentre.add(panelCentreBas);
				
		//Cr�ation d'une zone tampon � droite
		JPanel panelDroite = new JPanel();
		panelDroite.setLayout(new GridLayout(0, 1));
		
		JLabel labelVide9 = new JLabel();
		Dimension dim = new Dimension(80, 80);
		labelVide9.setPreferredSize(dim);
		
		panelDroite.add(labelVide9);
		

	
		//Assemblage G�n�ral du PanelFormulaire
		this.setLayout(new BorderLayout(30,30));
		this.add(menuBar, BorderLayout.NORTH);
		this.add(panelLogo, BorderLayout.WEST);
		this.add(panelCentre, BorderLayout.CENTER);
		this.add(panelDroite, BorderLayout.EAST);
	

	}

	/**
	 * C'est une m�thode permettant de v�rifier la validit� sous format mail d'un JLabel.
	 * retourne true si c'est un mail valide qui est entr�.
	 * @author mathieu
	 * @param string
	 * @return boolean
	 */
	public boolean checkFormatMail(String string) {
		Pattern p = Pattern.compile("^[A-Z0-9._%+_]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
		Matcher m = p.matcher(string.toUpperCase());
		
		return m.matches();
	}

	
}
