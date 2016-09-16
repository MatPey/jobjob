package main.presentation;

import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*; 
import java.lang.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*; 
import java.lang.*;
import java.text.ParseException; 


/**
 * CLASSE Chrono
 * 
 * <b>Cette classe est le timer avec son dessin  </b>
 * 
 * 
 * <ul>
 * <li>Il ne manque plus qu'� l'int�grer dans le programme principal
 *  et de permettre le r�glage du timer en mettant un param�tre</li>
 * </ul>
 * 
 * 
 * @author Lionel
 * @author Florent
 * @author Alban
 * 
 * @version 1.01
 */

public class Chrono extends JFrame implements Runnable{ 
	/* deux fa�ons de cr�er un thread: 
- Cr�ation d'un objet qui h�rite de la classe Thread 
- Execution de la primitive new Thread() sur un objet qui impl�mente l'interface Runnable 
(et c'est ce k'on a fait dans ce programme) */ 

	//- L'ecouteur de la fen�tre 
	class EF extends WindowAdapter //qd on quitte la fenetre 
	{ 
		public void windowClosing(WindowEvent e) 
		{} 
	}
	/**
	 * 
	 * @throws m�thode qui lance le chrono
	 * 
	 */
	// Param�tre pour d�terminer si le chrono est de type G�n�ral ou Stress
	private int j;
	public Thread h; //d�claration du thread h 
	public void run() //la m�thode vertuelle run() est obligatoire (voir cours "les threads" !!!) 
	{ 
		while(true)
		{ 
			try{h.sleep(1000);} 

			//m�thode sleep de la classe Thread : sleep (en milli-secondes) 
			catch(InterruptedException e){} 
			repaint(); 
		} 
	} 
	
	//init du chrono graphique
	int x0,y0;
	int x11,y11;
	int i=0; 
	int rayon;
	
	/**
	 * Ici le rapport de 900 permet de fixer le timer � 30 min pour les 360 degr�s
	 * 
	 */
	double tm; 
	/* -- "tm" c'est l'angle avec lequel les aiguilles bougent,il est calcul� en radian. 
	* cet angle est �gale � 6 degr�s --
	*/ 
	double y; 
	
	// Mini constructeur qui fait passer le type du constructeur
	Chrono(int ind)
	{
		this.j = ind;

		setAlwaysOnTop(true);
		this.setResizable(false);
		this.setUndecorated(true);
		addWindowListener(new EF());// 
		
		if(this.j == 1)
		{
			setLocation(1680,20);
			setSize(220,220); //dimensions de la fen�tre 
			rayon = 100;
			x0 = 110;
			y0 = 110;
			tm = (java.lang.Math.PI)/15; 
			y = tm;
		}
		else
		{
			setLocation(1720,20);
			setSize(160,160); //dimensions de la fen�tre 
			rayon = 50;
			x0 = 80;
			y0 = 80;
			tm = (java.lang.Math.PI)/900; 
			y = tm;
		}
		
		setVisible(true); //pour que la fen�tre divienne visible ! 
		
		//setBackground(Color.black);//couleur du fond(noir) 

		h = new Thread(this); //cr�ation 
		h.start(); //m�thode de la classe Thread,pour lancer la m�thode run() 
	}


	/*
	 *  d�but de paint() : la m�thode qui dessine tout 
	 *  le cercle et le decompte
	 */
	public void paint(Graphics g)
	{ 
		Graphics2D g1 = (Graphics2D) g;
		BasicStroke line = new BasicStroke(4.0f);
		g1.setStroke(line);
		
		x11=x0+(int)(rayon*(java.lang.Math.cos(tm - (java.lang.Math.PI)/2))); 
		y11=y0+(int)(rayon*(java.lang.Math.sin(tm - (java.lang.Math.PI)/2))); 
		g.setColor(Color.red); 
		g.drawLine(x0,y0,x11,y11);
		i++; 
		tm+=y; 
		//A chaque fois l'angle de l'aiguille est chang� -- 
		//Et on calcule la nouvelle position -- 

		if(j == 1)
		{
			line = new BasicStroke(8.0f);
			g1.setStroke(line);
			g.setColor(Color.red); 
		}
		else
		{
			g.setColor(Color.black); 
		}
		//--- dessin des cercles de l'horloge 
		
		g.drawOval(x0-rayon,y0-rayon,2*rayon,2*rayon); 
		//g.drawOval(300,200,100,100);


		//g.setColor(Color.white); 
		//g.drawString(i+"s",490,100); //Affichage Num�rique

	} 
	//------------fin de la fonction paint()------------------------------------- 




//	public void horloge(){ //constructeur 
//		/*
//le but de cette instruction est : 
//lorsqu'on ferme la fen�tre graphique 
////le message suivant apparait sur la fenetre DOS : 
////"Press any key to continue ..." 
////permettant ainsi de quitter facilement la fen�tre DOS 
////		 */ 
////
//		setAlwaysOnTop(true);
//		this.setResizable(false);
//		this.setUndecorated(true);
//		addWindowListener(new EF());// 
//		
//		if(this.j == 1)
//		{
//			setLocation(1680,20);
//			setSize(220,220); //dimensions de la fen�tre 
//		}
//		else
//		{
//			setLocation(1720,20);
//			setSize(160,160); //dimensions de la fen�tre 
//		}
//		
//		setVisible(true); //pour que la fen�tre divienne visible ! 
//		
//		//setBackground(Color.black);//couleur du fond(noir) 
//
//		h = new Thread(this); //cr�ation 
//		h.start(); //m�thode de la classe Thread,pour lancer la m�thode run() 
////
//	} 
}
