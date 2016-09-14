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
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import main.metier.questionReponse;


// TODO class � revoir compl�tement. Il n'y a aucune fermeture de base de donn�es qu'il devrait y avoir apr�s CHAQUE requ�te
// TODO piste � �tudier mettre un destructeur (avec un log pour voir quand �a passe) ou mettre � null l'appel � l'instanciation
public class ConnectionDB 
{
	static Statement 	 st;           // TODO : ok enchant� st, sinon tu fais quoi dans la vie? 
	
	//TODO : javadoc � revoir CECI N EST PAS UNE JAVADOC + Il manque les champs @param
	/*
	 * M�thode qui se connecte � la base 	   
	 */
	public static boolean connectionBase(boolean acces,String recruteur,String MDP) throws ClassNotFoundException, SQLException
	{

	/*
	 * Ici je stocke et initialise mes �l�ments de connection 
	 */
	String		 url 		= "jdbc:mysql://localhost/jobjob_1_2";
	String 	 	 login 		= recruteur;
	String 		 passwd 	= MDP;
	Connection	 cn 		= null;
	st	= null;
	
	/*
	 * Connection au drivers de base de donn�e
	 * ici pour le SQL
	 */
	try
	{
		// chargement du driver
		Class.forName("com.mysql.jdbc.Driver");
		
		// recuperation de la connexion	
		cn = (Connection) DriverManager.getConnection(url, login, passwd);
	
		// creation d'un statement pour pouvoir lancer des requ�tes
		st = (Statement) cn.createStatement();
		
		// affiche dans la console si la connecion est ok.
		System.out.println("connection dataBase OK");
	}
	
	catch ( SQLException e)
	{
		e.printStackTrace();
		acces=false;
	}finally{}
	
	return(acces);

	
	}
	
	
	/*
	 * M�thode pour ajouter un nouveau candidat dans l abase de donn�e.
	 * 
	 *    
	 */
	
	/**
	 * M�thode permettant l'enregistrement du candidat dans la base de donnees
	 * @author florent
	 * @param id
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param mail
	 * @throws ClassNotFoundException
	 */
	public static void enregistrerNouveauCandidatEnBase(String id,String nom,String prenom, String telephone, String mail) throws ClassNotFoundException
	{
		
		String sql2 = "INSERT INTO personne (nom, prenom) VALUES ('"+nom+"','"+prenom+"');";
		
		try 
		{
			st.executeUpdate(sql2);
			
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO candidat  (idPersonne,idCandidat,telephone,mail, idPersonne_1) VALUES (LAST_INSERT_ID(),'" + id+"','"+telephone+"','"+mail+ "',3);";
		
		try 
		{
			st.executeUpdate(sql);
			
			st.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	//TODO : javadoc � revoir CECI N EST PAS UNE JAVADOC + Il manque le champ @param
	/*
	 * M�thode pour r�cup�rer un candidat dans la base de donn�e � partir de son identifiant.
	 * affiche dans la console les champs associ�s � l'�l�ment id dans la table
	 *    
	 */
	public static void recupererCandidatEnBase(String id) throws ClassNotFoundException
	{
		ResultSet rs=null;
		String id2="";
		String nom="";
		String prenom="";
		String telephone="";
		String mail="";
		
		String sql2 = "SELECT * FROM candidat WHERE idCandidat='"+id+"'; ";
		try {
			rs = (ResultSet) st.executeQuery(sql2);
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
	}
	
	//TODO : javadoc � revoir CECI N EST PAS UNE JAVADOC + Il manque le champ @param
	//TODO : indentation catastropique = paragraphe illisible : A r�indenter correctement
	/*
	 * M�thode qui remplira un tableau de 15 objets de type questionReponse.
	 * 		- Une premi�re REQUETE SQL ira chercher de mani�re al�atoire le texte d'une question, ainsi que son numero.
	 * 		  puis on remplira les objets questionReponse, ainsi qu'un tableau stockant les numero de nos questions, avec.
	 * 		- Une seconde requ�te ira chercher l'ensemble des r�ponses/propositions correspondants � un numero de question donn�e.
	 * 
	 * 		Des erreurs peuvent appara�tre en l'�tat. Si la base de donn�es n'a pas suffisamment de questions d'une certaine cat�gorie pour r�pondre � l'ensemble de la "demande".		
	 * @author Mathieu
	 * 
	 */

	public questionReponse[] chercherQuestionEnBase(questionReponse[] questrep) throws SQLException

	{
		int numeroQuestion[] = new int[15]; // Tableau qui stockera les numero de question d�j� tir�s
		questrep = new questionReponse[15];
		int ordreCategorieQuestions[] = {1,1,1,1,1,3,3,3,3,3,2,4,4,4,1}; // Le type de nos 15 questions dans notre QCM, par ordre d'apparition
		String numeroQuestionsInterdites = "0"; // variable qui permettra d'exclure des numeros de question de notre requ�te de recherche al�atoire de questions
		


		
		// Mes requ�tes ici :			
		ResultSet res = null;
		
		for (int i = 0; i < ordreCategorieQuestions.length; i++) {
			String request1 = "SELECT textesQuestion, numero FROM questions WHERE idCategorie = " + ordreCategorieQuestions[i] + " AND numero != " + numeroQuestionsInterdites + " ORDER by Rand() LIMIT 1";
			res = (ResultSet) st.executeQuery(request1);
			while (res.next()) {
				questrep[i] = new questionReponse();
				questrep[i].libelleQuestion = res.getString(1);

				numeroQuestion[i] = res.getInt("numero");
				numeroQuestionsInterdites += " AND numero  != " + res.getInt("numero");
				//System.out.println(numeroQuestionsInterdites);
			}
		}
		
		for (int j = 0; j < numeroQuestion.length; j++) {
			String request2 = "SELECT reponse FROM propositions INNER JOIN comporte ON propositions.idPropositions = comporte.idPropositions WHERE numero = " + numeroQuestion[j];
			res = (ResultSet) st.executeQuery(request2);

			res.first();
			questrep[j].libelleReponse1 = res.getString("reponse");
			res.next();
			questrep[j].libelleReponse2 = res.getString("reponse");
			res.next();
			questrep[j].libelleReponse3 = res.getString("reponse");
			res.next();
			questrep[j].libelleReponse4 = res.getString("reponse");
		}
		
		return questrep;
		
//		for (int i = 0; i < ordreCategorieQuestions.length; i++) {
//			System.out.println(questrep[i].libelleQuestion);
//		}
	}

	


	//TODO : javadoc inexistante 
	public int nombreCandidat()
	{

		ResultSet res = null;
		int nbCand=0;
		String requete = "SELECT candidat.idPersonne FROM candidat";
		try {
			res = (ResultSet) st.executeQuery(requete);
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
		return nbCand+1;
	}

}




