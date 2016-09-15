/**
 * 
 * Cette classe permet la connection � une base de donn�e de type SQL.
 * Cette base est sur la machine de Marc � l'ip : 10.111.61.52:3306
 * et la base s'appelle  "jobjobdb".
 *  !!! RESTE A FAIRE !!!
 * 
 * IL FAUT GERER LE PASSAGE DE LOGIN ET PASSWORD DU RECRUTEUR 
 * PAR UNE FENETRE DE LOGIN AU DEMARRAGE DE L APPLI
 * 
 * Dans cette classe il y a une methode qui permet la connection.
 * @author Lionel Chialvo
 * @author Marc Naouache
 * @date	 31/08/2016
 * @version  1.0
 */
package main.donnees;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import main.metier.Candidat;
import main.metier.questionReponse;


public class ConnectionDB 
{
	//private static String url= "jdbc:mysql://localhost/jobjob_2_0"; // POUR TEST LOCAL UNIQUEMENT 
	private static String url= "jdbc:mysql://sta6101855/jobjob_2_0"; 
	private static String login = "cdi";
	private static String passwd = "cdi";
	private static Connection connection;
	private static Statement statement;
	private String recruteur = "cdi";
	private String MDP = "cdi";
	private  boolean connected = false;
	
	/**
	 * Constructeur de la classe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ConnectionDB () throws ClassNotFoundException, SQLException 
	{
		if (this.connectionBase()) System.out.println("connect�");
	}
	
	/**
	 * M�thode qui se connecte � la base et cr�e un premier statement 
	 * @param acces, param�tre qui autorise l'acc�s
	 * @param recruteur, param�tre qui enregistre le nom du recruteur
	 * @param MDP , param�tre qui enregistre le MDP du recruteur
	 */
	public boolean connectionBase() throws ClassNotFoundException, SQLException
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // chargement du driver
			// recuperation de la connexion
			System.out.println("login : " + login);
			connection = (Connection) DriverManager.getConnection(url,
																  login,
																  passwd);
		
			// creation d'un statement pour pouvoir lancer des requ�tes
			statement = (Statement) connection.createStatement();	
			// affiche dans la console si la connecion est ok.
			System.out.println("connection dataBase OK");
			connected = true;
		}
		
		catch ( SQLException e)
		{
			e.printStackTrace();
			connected = false;
		}finally{}
		
		return connected;
	}
	
	/**
	 * Fonction de d�connexion de la base fermant le statement et la connexion en cours 
	 * @throws SQLException
	 */
	public void Deconnexion() throws SQLException
	{
		statement.close();
		connection.close();
	}
	
	/**
	 * M�thode permettant l'enregistrement du candidat dans la base de donnees
	 * Elle retourne un int, correspondant � l'idPersonne du nouveau candidat enregistr�
	 * @author florent
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param mail
	 * @throws ClassNotFoundException
	 * @throws SQLException 
	 */
	
	public int enregistrerNouveauCandidatEnBase(String id,String nom,String prenom, String telephone, String mail) throws ClassNotFoundException, SQLException
	{
		String sql2 = "INSERT INTO personne (nom, prenom) VALUES ('"+nom+"','"+prenom+"');";		
		try 
		{
			statement.executeUpdate(sql2);
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		

		String sql = "INSERT INTO candidat  (idPersonne,idCandidat,telephone,mail, idPersonne_Recruteur) VALUES (LAST_INSERT_ID(),'" + id+"','"+telephone+"','"+mail+ "',3);";
		
		try 
		{
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
		int answer = 0;
		
		
		// Partie d�vou�e au test Unitaire, renvoyant l'idPersonne du candidat qui vient d'�tre cr�� dans la base
		ResultSet generatedKeys = (ResultSet) statement.getGeneratedKeys(); 
		while(generatedKeys.next()) {
			answer = Integer.parseInt(generatedKeys.getString(1));
			System.out.println("sysoiut1" + answer);
		}
		System.out.println("sysout 2" + answer);
		Deconnexion();
		return answer;
		
	}
	
	
	//TODO : javadoc � revoir CECI N EST PAS UNE JAVADOC + Il manque le champ @param
	/**
	 * M�thode pour r�cup�rer un candidat dans la base de donn�e � partir de son identifiant.
	 * affiche dans la console les champs associ�s � l'�l�ment id dans la table
	 * @param id
	 * @throws SQLException 
	 *    
	 */
	public void recupererCandidatEnBase(String id) throws ClassNotFoundException, SQLException
	{
		ResultSet rs=null;
		String id2="";
		String nom="";
		String prenom="";
		String telephone="";
		String mail="";
		
		String sql2 = "SELECT * FROM candidat WHERE idCandidat='"+id+"'; ";
		try {
			rs = (ResultSet) statement.executeQuery(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				try {
					id2 = rs.getString("identifiant");
					nom = rs.getString("nom");
					prenom= rs.getString("prenom");
					telephone= rs.getString("telephone");
					mail= rs.getString("mail");
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(id2);
				System.out.println(nom);
				System.out.println(prenom);
				System.out.println(telephone);
				System.out.println(mail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Deconnexion();
	}
	

	/** M�thode renvoyant un tableau d'objets de type questionReponse
	 * Pour chaque objet seront compl�t�s le libelleQuestion, ainsi que les libellesReponses. Les questions sont choisies de mani�re al�atoire dans la BDD
	 * 
	 * Deux requ�tes SQL vont �tre utilis�es :
	 * 		-	Une premi�re (request1) ira chercher de mani�re al�atoire le texte d'une question, ainsi que son numero.
	 * 			Lors de la lecture de cette requ�te, seront remplit le libelleQuestion de l'objet, ainsi que 2 tableaux contenant le numero de la question. Un premier tableau servira � trouver les r�ponses � cette question ult�rieurement, un second servira � ne pas avoir de doublons dans le choix des questions.
	 * 		-	Une seconde requ�te (request2) remplira pour chaque objet les 4 libellesReponses.
	 * 
	 * Des erreurs peuvent appara�tre en l'�tat. Si la base de donn�es n'a pas suffisamment de questions d'une certaine cat�gorie pour r�pondre � l'ensemble de la "demande".		
	 * 
	 * @author Mathieu
	 * @param questionReponse[15]
	 * @return questionReponse[15]
	 * @throws ClassNotFoundException 
	 */
	public questionReponse[] chercherQuestionEnBase(questionReponse[] questrep) throws SQLException, ClassNotFoundException
	{
		// D�claration de mes diff�rentes variables :
		int numeroQuestion[] = new int[15]; // Tableau qui stockera les numero de question d�j� tir�s
		questrep = new questionReponse[15];
		int ordreCategorieQuestions[] = {1,1,1,1,1,3,3,3,3,3,2,4,4,4,1}; // Le type de nos 15 questions dans notre QCM, par ordre d'apparition
		String numeroQuestionsInterdites = "0"; // variable qui permettra d'exclure des numeros de question de notre requ�te de recherche al�atoire de questions		
		ResultSet res = null;
		
		
		// Boucles et requ�tes permettant de remplir le tableau questionReponse[] fournit en param�tre		
			// Premi�re requ�te, ins�r�e dans une boucle de 15 it�rations
			for (int i = 0; i < ordreCategorieQuestions.length; i++) {
				
				String request1 = "SELECT textesQuestion, numero, idPropositions FROM questions WHERE idCategorie = " + ordreCategorieQuestions[i] + " AND numero != " + numeroQuestionsInterdites + " ORDER by Rand() LIMIT 1";
				res = (ResultSet) statement.executeQuery(request1);
				
				while (res.next()) 
				{
					questrep[i] = new questionReponse();
					questrep[i].libelleQuestion = res.getString(1);					
					
					questrep[i].numQuestionBDD = res.getInt("numero");				
								
					numeroQuestion[i] = res.getInt("numero");					
					numeroQuestionsInterdites += " AND numero  != " + res.getInt("numero");
					
					questrep[i].bonneReponse = res.getInt("idPropositions");						
				}
				
			}
		
			// Seconde requ�te, ins�r�e dans une boucle contenant autant d'it�rations que l'on a eu de questions pr�c�demment
			for (int j = 0; j < numeroQuestion.length; j++) {
				String request2 = "SELECT reponse FROM propositions INNER JOIN comporte ON propositions.idPropositions = comporte.idPropositions WHERE numero = " + numeroQuestion[j];
				res = (ResultSet) statement.executeQuery(request2);
	
				res.first();
				questrep[j].libelleReponse1 = res.getString("reponse");
				res.next();
				questrep[j].libelleReponse2 = res.getString("reponse");
				res.next();
				questrep[j].libelleReponse3 = res.getString("reponse");
				res.next();
				questrep[j].libelleReponse4 = res.getString("reponse");
			}
					
		Deconnexion();
		// Notre retour
		return questrep;
	}

	


	//TODO : javadoc inexistante 
	public int nombreCandidat() throws SQLException, ClassNotFoundException
	{
		ResultSet res = null;
		int nbCand=0;
		String requete = "SELECT candidat.idPersonne FROM candidat";
		try {
			res = (ResultSet) statement.executeQuery(requete);
		} catch (SQLException e) {
			System.out.println("impossible d effectuer la requete");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (res.next()) {
				try {
				nbCand = res.getInt("idPersonne");
			} catch (SQLException e) {
				System.out.println("fauxe");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Deconnexion();
		return nbCand+1;
	}
	
	/**
	 * @author florent
	 * Remplissage de la table lienqcmquestions avec le numero de la question, le score associ� et l'idQcm correspondant
	 * @param score int[]
	 * @param questrep questionReponse[]
	 * @param cd Candidat
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void enregistrerScoreCandidat(int[] score, questionReponse[] questrep, Candidat cd) throws SQLException, ClassNotFoundException
	{		
		for (int i = 0; i < questrep.length; i++) {
			String sql = "INSERT INTO lienqcmquestions (score, idQcm, numero) VALUES ("+score[i]+", (SELECT idQcm FROM qcm INNER JOIN candidat ON qcm.idPersonne = candidat.idPersonne WHERE idCandidat = '"+cd.chaine[4]+"'), "+questrep[i].numQuestionBDD+");";
			try {
				int a = statement.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Deconnexion();
	}
	
	/**
	 * @author cyril
	 * appel de la proc�dure stock�e resultatCandidat
	 */
	public static void recupererStatistiques() {
		CallableStatement cs = null;
		ResultSet resultat = null;

		try {
			cs = (CallableStatement) connection.prepareCall("{call resultatsCandidat()}");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			if (cs.execute()) 
			{
				resultat =  (ResultSet) cs.getResultSet();
				while(resultat.next())
				{
					System.out.println("Nom du candidat:"+resultat.getString("nomCandidat"));
					System.out.println("Date de l'evaluation:"+resultat.getString("dateEvaluation"));
					System.out.println("Notes Technique: "+resultat.getString("Technique"));
					System.out.println("Notes Adaptabilit�: "+resultat.getString("Adaptabilite"));
					System.out.println("Notes Culture: "+resultat.getString("Culture"));
					System.out.println("Notes Stress: "+resultat.getString("Stress"));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}




