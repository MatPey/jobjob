package main.metier;

import java.sql.SQLException;
import java.util.Random;

import main.donnees.ConnectionDB;
import main.donnees.EnregistrementDonnee;

/**
 * Structure utilis�e pour g�n�rer le tableau contenant les questions auquelles le candidat devra r�pondre
 * 
 * categorieQuestion - La cat�gorie de la question qui peut �tre :
 * 							"CJ" pour Culture Java 
 * 							"CG" pour Culture G�n�rale Informatique
 * 							"LE" pour Langage Exotique
 * 							"S" pour Stress
 * 					
 * numeroQuestion - Le num�ro que la question a dans le panel de questions
 * 
 * reponseCorrecte - La r�ponse correcte � la question
 * 
 * reponseCandidat - La r�ponse du candidat
 * 
 * timerAuMomentDeLaReponse - On r�cup�re le timer au moment o� le candidat r�pond � la question	
 * 	
 * @author Alban
 * @author Cyril
 * @author Benjamin
 * @version 1.00
 * 
 * 
 */

public class questionReponse 
{
	public byte categorieQuestion;
//	public byte numQuestion;
	public int  numQuestionBDD;
	public String libelleQuestion;
	public String libelleReponse1; 
	public String libelleReponse2; 
	public String libelleReponse3; 
	public String libelleReponse4; 
	public String bonneReponse;
	public byte reponseCandidat;
	
	public questionReponse[] questionsCandidat;
	
	public String[] tableauReponsesChoisiesParCandidat = new String[15];
	public int[] scoreParReponseCandidat = new int[15];
	
	
	public questionReponse()
	{
		//Remplissage d'un tableauReponsesChoisiesParCandidat par d�faut:
		//@Author Mathieu
		for (int i = 0; i < 15; i++) {
			tableauReponsesChoisiesParCandidat[i] = "";
		}
	}


	/**
	 * @author Cyril
	 *  Cette fonction est appel� lorsque le candidat appuie sur "START" sur le "panelCandidat"
	 *  Elle a pour but d'envoyer un tableau de 15 instances de QuestionR�ponse � la couche Donn�es pour qu'il puisse la remplir les lib�ll�s des questions et r�ponses associ�s
	 * @param questionsCandidat est un tableau de 15 instances de QuestionR�ponse
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */


	public questionReponse[] chercherQuestionR�ponse(questionReponse[] questionsCandidat) throws SQLException, ClassNotFoundException

	{		
		ConnectionDB laConnection = new ConnectionDB();	
		questionsCandidat = laConnection.chercherQuestionEnBase(questionsCandidat);		
		return questionsCandidat;
	}
	
	
	//recup�ration de la r�ponse du candidat
	/**
	 * M�thode remplissant dans notre objet QuestionReponse le tableauReponsesChoisiesParCandidat.
	 * Elle est appel�e � chaque validation d'une r�ponse par le candidat
	 * @Mathieu
	 * @param byte reponse
	 * @param int compteur
	 * @param String reponseChoisie
	 */
	public void recupereReponse(byte reponse,int compteur, String reponseChoisie)
	{
		System.out.println("Question : "+compteur);
		System.out.println("Reponse : "+reponse);
		System.out.println("Texte Reponse : " + reponseChoisie);
		
		tableauReponsesChoisiesParCandidat[compteur-1] = reponseChoisie;
	}
	
	/**
	 * M�thode qui va remplir le tableau scoreParReponseCandidat. 
	 * C'est un tableau de 15 int qui, pour chaque r�ponse affectera 0 ou 1 point au candidat.
	 * 0 point si mauvaise r�ponse, 1 point si bonne r�ponse.
	 * 
	 * On peut facilement faire entrer en jeu le bar�me de notre Qcm, ins�r� dans la BDD, en utilisant les fonctions .obtenirScore* (une fois qu'elles seront impl�ment�es
	 * 
	 * @author 34011-82-06 Mathieu
	 * @return void
	 */
	public void genererTableauScoreCandidat() {

//		GestionDonnees gestion = new GestionDonnees();
		
		for (int i = 0; i < 15; i++) {
			if(tableauReponsesChoisiesParCandidat[i].equals(questionsCandidat[i].bonneReponse)) {
//				scoreParReponseCandidat[i] = gestion.obtenirScoreBonneReponse();
				scoreParReponseCandidat[i] = 1;
			}
			if(tableauReponsesChoisiesParCandidat[i].equals(questionsCandidat[i].bonneReponse) == false) {
//				scoreParReponseCandidat[i] = gestion.obtenirScoreMauvaiseReponse();
				scoreParReponseCandidat[i] = 0;
			}
		}
		
		System.out.println("Tableau des scores du candidat :");
		for (int i = 0; i < 15; i++) {
			System.out.print(scoreParReponseCandidat[i] + ", ");
		}
	}
	
	
	
	//recuperation du temps lors de la r�ponse du candidat
	public void recupereTimerCandidat(int Timer){
		
		
	}
}
