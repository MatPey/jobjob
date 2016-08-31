package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
/**
 * 
 * @author Damien El Sabbagh
 * @author Khadidja Boudjema
 * @version 0.1
 *
 */

public class EnregistrementDonnee 
{

	int identifiant = 1;
	
	/**
	 * M�thode pour l'enregistrement du nom de l'entreprise dans un fichier .txt
	 * @param nom String
	 */
	public void creerFichier(String nom)
	{
		File f = new File (nom+".txt");
		System.out.println(" Creation du fichier"+nom);
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("JobJob recrutement");
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

/**
 * Methode pour l'enregistrement d'un candidat dans un fichier .txt
 * @param info String[]
 */
	
	public void enregistrerCandidat(String[] info){

		DateFormat format = new SimpleDateFormat("ddMMyy");
		String date = format.format(new Date());


		File f = new File ("../../git/recrutement/candidats/candidat_"+nombreCandidat()+".txt");
		File fb = new File("../../git/recrutement/scores/scoreCandidat_"+nombreCandidat()); 
		fb.mkdirs(); 

		
		try {
			FileWriter fw = new FileWriter(f);
			fw.write("Identifiant :" +nombreCandidat());
			fw.write("\r\n");

			for (int j = 0; j < info.length; j++) {
				fw.write(info[j]);
				fw.write("\r\n");
			}

			fw.write("Date : "+date);

			fw.close();
			//identifiant=identifiant+1;
			//System.out.println(identifiant);
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
	
	
/**
* methode qui cherche le candidat donn�e avec son identifiant
* et affiche l'ensemble de ses d�tails
* @param str String
*/
	
	public boolean rechercheCandidat(String str){
		boolean trouver=false;
		
		File dossier = new File("..\\..\\git\\recrutement\\candidats");
		String[] contenu = dossier.list();
		int i=0;
		while(trouver == false & i < contenu.length){
			if(contenu[i].equals(str)){
				trouver=true;
			}
			i++;
		}
	if(trouver == true){
			System.out.println("fichier existe :");
			String pathFichier="..\\..\\git\\recrutement\\candidats\\"+str;


			BufferedReader fluxEntree = null;
			try {
				/* Cr�ation du flux vers le fichier texte */
				fluxEntree = new BufferedReader(new FileReader(pathFichier));

				/* Lecture d'une ligne */
				String ligneLue = fluxEntree.readLine();

				while(ligneLue!=null){
					System.out.println(ligneLue);
					ligneLue = fluxEntree.readLine();
				}
			}
			catch(IOException exc){
				exc.printStackTrace();
			}
			finally{
				try{
					if(fluxEntree != null){
						/* Fermeture du flux vers le fichier */
						fluxEntree.close();
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
	}
	else {
		System.out.println(" Candidat non trouv�!");
	}
	return trouver;
  }
	


/**
 * methode chercherQuestion recup�re la question rand placer en argument
 * avec les reponses associ�es � la question.
 * @param rand String
 * @return liste de question
 */
	
	public String[] chercherQuestion( String rand){
		
		
		File dossier = new File("..\\..\\git\\recrutement\\questionReponse\\");
		String[] contenu = dossier.list();
		String [] questionReponse= new String[5];
		
		int i=0;
		
		String reponse=rand+"R"; 
		boolean questionTrouver=false;
		
		   int j=0;
		   int compteur=4;
		   System.out.println(" Voici les chemins vers les fichiers question et les reponses");
		   while(i<contenu.length & compteur!=0){
			
			     if(contenu[i].equals(rand+".txt")){ 
		      	 questionTrouver=true;
			     String pathFichier="..\\..\\git\\recrutement\\questionReponse\\"+rand+".txt";
			     questionReponse[j]=pathFichier;
			     compteur--;
			     }
			     
			     else if(contenu[i].contains(rand)){
			    	 
			    	 for (int k=1; k<questionReponse.length; k++){
			    		 	String pathFichier="..\\..\\git\\recrutement\\questionReponse\\"+reponse+k+".txt";
						     questionReponse[k]=pathFichier;
						     }
			     }
			     i++;
			    
		   }  
			     for (j=0; j<questionReponse.length; j++){
			     System.out.println( "Path fichier" + j + questionReponse[j] + "\n\t");
			     }
			    
			    BufferedReader fluxEntree=null;
			    
			    for (j=0; j<questionReponse.length; j++){
		 	try {
				//Cr�ation du flux vers le fichier texte //
				fluxEntree = new BufferedReader(new FileReader(questionReponse[j]));
				String ligneLue = fluxEntree.readLine();
				while(ligneLue!=null){
					System.out.println(ligneLue);
					questionReponse[j]=ligneLue;
					ligneLue = fluxEntree.readLine();
					System.out.println(" contenu de la case "+j+" du tableau questionReponse est : " + questionReponse[j]);
				}
			}
			catch(IOException exc){
				exc.printStackTrace();
			}
			
			finally{
				try{
					if(fluxEntree!=null){
						// Fermeture du flux vers le fichier //
						fluxEntree.close();
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
				
			}
	}
			
			     
	return questionReponse;		
}	
	public int nombreCandidat(){
		
		File dossier = new File("..\\..\\git\\recrutement\\candidats\\");
		String[] contenu = dossier.list();
		System.out.println(contenu.length);
		return contenu.length;
	}
	
	
	
	public void enregistrerScore(String idCandidat, int score){
		
		
		
		File f = new File ("../../git/recrutement/scores/scoreCandidat_"+nombreCandidat()+"/candidat_"+nombreCandidat()+"Score.txt");
		try {
			
			FileWriter fw = new FileWriter(f);
			fw.write("Score = "+ score);
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
		
	
	/*public void RechercherScore(String idCandidat){
		File dossier = new File("..\\..\\git\\recrutement\\scores\\");
		String[] contenuScore = dossier.list();
		String [] questionReponse= new String[5];
	}*/
}

		
		
		
