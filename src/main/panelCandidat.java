package main;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * TODO : 
 * renommer panel1 avec un nom explicit
 * mettre � jour la javadoc
 * am�liorer l'indentation du code (ex startTimer et suivant trop � gauche 
 * effacer chaque t�che de cette TODOlist une fois que c'est r�alis�
 */

/**
 * 
 * 
 * @author Cyril, Audric
 * @version v1.00
 * 
 */

public class panelCandidat extends JPanel  {
	
	protected JPanel panel1;	//TODO � renommer d'urgence
	protected JLabel iconeEntreprise;
	protected ImageIcon logo;

	public panelCandidat() 
	{		
		panel1= new JPanel();
		iconeEntreprise = new JLabel(new ImageIcon("logoAFPA.png"));	
		this.setLayout(new BorderLayout());		
		panel1.setLayout(new BorderLayout());
		
		panel1.add(iconeEntreprise,BorderLayout.NORTH);	
		
		this.add(panel1,BorderLayout.WEST);		
	}

public void startTimer(){
	TimerFormulaire tp = new TimerFormulaire();
	tp.start();
}
	
public void randomQuestionsJava(){
	
}

public void randomQuestionsCultureG(){
	
}

public void randomQuestionsCodeExo(){
	
}

}
