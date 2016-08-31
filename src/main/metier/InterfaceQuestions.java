/**
 *Cette interfaceQuestions est aussi trait�e par la partie pr�sentation.
 *
*Ici, on pr�cise les variables Score et TempsQ pour enregistrer le score du candidat et le temps de chaque question.
*Les m�thodes li�es: 
*- calculPoints() des points tout au long du questionnaire
*- calculTemps() enregistrement du temps enregistr� pour chaque question

Les r�sultats de ces donn�es seront utilis�es par la couche donn�es afin de les enregistrer dans le fichier final.
 */
package main.metier;

/**
 * @author Audric
 * @author Benjamin
 *
 */
public interface InterfaceQuestions {

	int score = 0;
	int tempsQ = 0;
	
	//Recup�ration du score du candidat
	public void calculPoints();
	
		static int scoreFinal (){
			
	    return score ;
	}
	
	//Recup�ration du temps r�alis� par le candidat
	public void calculTemps();
	
		static int tempscandidat (){
	    return tempsQ ;
	}
		
}
