package main.presentation;

import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*; 
import java.lang.*; 



public class Chrono extends JPanel implements Runnable{ 
	/* deux fa�ons de cr�er un thread: 
- Cr�ation d'un objet qui h�rite de la classe Thread 
- Execution de la primitive new Thread() sur un objet qui impl�mente l'interface Runnable 
(et c'est ce k'on a fait dans ce programme) */ 

	Thread h; //d�claration du thread h 
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
	//------------------------------ 
	//------------------------------ 

	int x0=500,y0=400;
	int x11,y11;
	int i=0; 
	int rayon = 200;

	double tm=(java.lang.Math.PI)/9; 
	/* -- "tm" c'est l'angle avec lequel les aiguilles bougent,il est calcul� en radian. 
cet angle est �gale � 6 degr�s -- */ 
	double y=tm; 


	//- d�but de paint() : la m�thode qui dessine tout 
	public void paintComponent(Graphics g)
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

		//--- dessin du cercle
		g.setColor(Color.white);
		g.drawOval(x0-rayon,y0-rayon,2*rayon,2*rayon); 
		//g.drawOval(300,200,100,100);
		//g.setColor(Color.white); 
		//g.drawString(i+"s",490,100); //Affichage Num�rique
	} 
	//------------fin de la fonction paint()------------------------------------- 

	public void horloge()
	{ 
		//constructeur 
		setVisible(true); //pour que la fen�tre divienne visible ! 
		setSize(800,600); //dimensions de la fen�tre 
		setBackground(Color.black);//couleur du fond(noir) 

		h = new Thread(this); //cr�ation 
		h.start(); //m�thode de la classe Thread,pour lancer la m�thode run() 
	} 
}
