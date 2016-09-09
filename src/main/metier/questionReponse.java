package main.metier;

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
	String categorieQuestion;
	public byte numQuestion;
	public byte numQuestionBDD;
	public String libelleQuestion;
	public String libelleReponse1; 
	public String libelleReponse2; 
	public String libelleReponse3; 
	public String libelleReponse4; 
	
	public questionReponse[] questionsCandidat;
	

	
	public questionReponse()
	{
		
	}
	
//	public void genererQuestionsCandidat() throws ClassNotFoundException
//	{		
//		questionsCandidat = new questionReponse[15];
//		byte valeurGeneree;
//		boolean trouve = false;
//		Random r = new Random();
//		questionReponse question;
//		
//		
//		// ====>       ici ecrire une fonction (DONNEES) pourqu'ils retournent le nombre d'�l�ments d'une cat�gorie
//		// ====>  exemple int nbreQuestion=demanderNbreQuestion(cat�gorie);
//		
//		// ligne qui suit POUR TEST - � effacer quand la fonction donn�es sera termin�e
//		int nbreQuestion=15;
//		
//		// GENERE LES QUESTIONS 1 A 5
//		int i = 0;
//		while(i < 5)
//		{
//			question = new questionReponse("CG");
//			trouve = false;
//			// ici il y a peut etre 30 questions au lieu de 15
//			valeurGeneree = (byte) (r.nextInt(nbreQuestion-1) + 1);
//			
//			for(int j = 0; j < i; j++)
//			{
//				if(valeurGeneree == questionsCandidat[j].numQuestion)
//				{
//					trouve = true;
//				}
//			}
//			
//			if(trouve == false)
//			{
//				question.numQuestion = valeurGeneree;
//				questionsCandidat[i] = question;
//				i++;
//			}
//		}
//		
//		// GENERE LES QUESTIONS 6 A 10
//		while(i < 10)
//		{
//			question = new questionReponse("CJ");
//			trouve = false;
//			valeurGeneree = (byte) (r.nextInt(nbreQuestion-1) + 1);
//			
//			for(int j = 0; j < i; j++)
//			{
//				if(valeurGeneree == questionsCandidat[j].numQuestion)
//				{
//					
//					trouve = true;
//				}
//			}
//			
//			if(trouve == false)
//			{
//				question.numQuestion = valeurGeneree;
//				questionsCandidat[i] = question;
//				i++;
//			}
//		}
//		
//		// VA CHERCHER LA QUESTION STRESS - QUESTION 11
//		question = new questionReponse("S");
//		question.numQuestion = 1;
//		questionsCandidat[i] = question;
//		i++;
//		
//		// GENERE LES QUESTIONS 12 A 14
//		valeurGeneree = (byte) (r.nextInt(11));
//		String[] tableauLangagesExotiques = {"DELPHI", "PERL", "FORTRAN", "ADA", "PASCAL", "SMALLTALK", "TCLTK", "LISP", "VISUALBASIC", "SQUIRREL", "COBOL", "EIFFEL"};
////		String langage = tableauLangagesExotiques[valeurGeneree];
//		
//		int compteurTemp=1;
//		while(i < 14)
//		{
//			
//			String cat = "LE" + tableauLangagesExotiques[valeurGeneree];
//			question = new questionReponse(cat);
//			questionsCandidat[i] = question;
//			questionsCandidat[i].numQuestion=(byte)compteurTemp;
//			compteurTemp++;
//			i++;
//		}
//		
//		// GENERE LA QUESTION 15	
//		while(i < 15)
//		{
//			question = new questionReponse("CG");
//			trouve = false;
//			valeurGeneree = (byte) (r.nextInt(nbreQuestion-1) + 1);
//			
//			for(int j = 0; j < i; j++)
//			{
//				if(valeurGeneree == questionsCandidat[j].numQuestion)
//				{
//					trouve = true;
//				}
//			}
//			
//			if(trouve == false)
//			{
//				question.numQuestion = valeurGeneree;
//				questionsCandidat[i] = question;
//				i++;
//			}
//		}
//		
//		chercherQuestionR�ponse(questionsCandidat,0);				
//		
//		// Fonctions test -- � effacer par la suite
//		affichageTableauGenere(questionsCandidat,15);		
//		
//	}
	
	/**
	 * @author Cyril
	 *  Cette fonction est appel� lorsque le candidat appuie sur "START" sur le "panelCandidat"
	 *  Elle a pour but d'envoyer un tableau de 15 instances de QuestionR�ponse � la couche Donn�es pour qu'il puisse la remplir les lib�ll�s des questions et r�ponses associ�s
	 * @param questionsCandidat est un tableau de 15 instances de QuestionR�ponse
	 * 
	 */

	public void chercherQuestionR�ponse(questionReponse[] questionsCandidat)

	{			
		ConnectionDB laConnection = new ConnectionDB();	
	//	ConnectionDB.chercherQuestionEnBase(questionsCandidat);		
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
