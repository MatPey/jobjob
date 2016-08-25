package main;

public class questionReponse 
{
	/**
	 * Structure utilis�e pour g�n�rer le tableau contenant les questions auquelles le candidat devra r�pondre
	 * 
	 * categorieQuestion - La cat�gorie de la question qui peut �tre :
	 * 							"CJ" pour Culture Java 
	 * 							"CG" pour Culture G�n�rale Informatique
	 * 							"E" pour Langage Exotique
	 * 							"S" pour Stress
	 * 					
	 * numeroQuestion - Le num�ro que la question a dans le panel de questions
	 * 
	 * reponseCorrecte - La r�ponse correcte � la question
	 * 
	 * reponseCandidat - La r�ponse du candidat
	 * 
	 * timerAuMomentDeLaReponse - On r�cup�re le timer au moment o� le candidat r�pond � la question		
	 */
	
	byte categorieQuestion;
	byte numeroQuestion;
	boolean reponseCorrecteOuIncorrecte;
	int timerAuMomentDeLaReponse; // En secondes pour le moment
	
	
	
}
