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
	public int bonneReponse;
	public byte reponseCandidat;
	
	public questionReponse[] questionsCandidat;
	

	
	public questionReponse()
	{
		
	}



	/**
	 * @author Cyril
	 *  Cette fonction est appel� lorsque le candidat appuie sur "START" sur le "panelCandidat"
	 *  Elle a pour but d'envoyer un tableau de 15 instances de QuestionR�ponse � la couche Donn�es pour qu'il puisse la remplir les lib�ll�s des questions et r�ponses associ�s
	 * @param questionsCandidat est un tableau de 15 instances de QuestionR�ponse
	 * @throws SQLException 
	 * 
	 */


	public questionReponse[] chercherQuestionR�ponse(questionReponse[] questionsCandidat) throws SQLException

	{		
		ConnectionDB laConnection = new ConnectionDB();	
		questionsCandidat = laConnection.chercherQuestionEnBase(questionsCandidat);		
		return questionsCandidat;
	}
	
	
	//recup�ration de la r�ponse du candidat
	public void recupereReponse(byte reponse,int compteur)
	{
		System.out.println("Question : "+compteur);
		System.out.println("Reponse : "+reponse);
		
		
//		EnregistrementDonnee ed = new EnregistrementDonnee();
//		ed.enregistrerReponse(reponse,compteur); // enregistrement de la reponse donneepar l'utilisateur correspondant � la question n� compteur
		
		// comparer r�ponse avec r�ponse correcte
		
		// envoyer r�sultat � la DONNEE avec comme param�tre: index_table de la question,numero question, et r�ponse correcte ou pas.

	}
	
	
	
	//recuperation du temps lors de la r�ponse du candidat
	public void recupereTimerCandidat(int Timer){
		
		
	}
}
