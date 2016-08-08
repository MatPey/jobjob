
package main;

import javax.swing.UIManager;

/**
 * Cette classe g�re le main et le lancement de l'interface graphique
 * 
 * @author Khadidja Boudjema
 * @author Benjamin Champetier
 * @author Lionel Chiavo
 * @author Damien El Sabbagh
 * @author Ana�s Gueyte
 * @author Audric Lespagnol
 * @author Alban Martinez
 * @author Cyril Mathieu
 * @author Marc Naouache
 * @author Mathieu Peyramard
 * @author Emmanuel Piat
 * @author Florent Valadier
 * @author david di Marcantonio
 * 
 * @version 0.1
 *
 */
public class Application 
{

	/**
	 * Constructeur
	 * 
	 */
	public Application() 
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Main de l'application 
	 * @param args arguments pass�s en param�res lors du lancement de l'application
	 */
	public static void main(String[] args) 
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); //le Look and feel peut avoir plusieurs types d'apparence
		}
		catch(Exception e) 
		{
			
		}
		new Application();
	}

}
