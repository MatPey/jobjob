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

public class ConnectionDB 
{
	static Statement 	 st;
	/*
	 * M�thode qui se connecte � la base 	   
	 */
	public static boolean connectionBase(boolean acces,String recruteur,String MDP) throws ClassNotFoundException, SQLException
	{
	/*
	 * Ici je stocke et initialise mes �l�ments de connection 
	 */
	String		 url 		= "jdbc:mysql://sta6101855:3306/jobjob_1_2";
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
	public static void enregistrerNouveauCandidatEnBase(int id,String nom,String prenom, String telephone, String mail) throws ClassNotFoundException
	{
		
		String sql = "INSERT INTO candidat  (identifiant,nom,prenom,telephone,mail) VALUES ('" + id+"','"+nom+"','"+prenom+"','"+telephone+"','"+mail+ "');";
		
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * M�thode pour r�cup�rer un candidat dans la base de donn�e � partir de son identifiant.
	 * affiche dans la console les champs associ�s � l'�l�ment id dans la table
	 *    
	 */
	public static void recupererCandidatEnBase(int id) throws ClassNotFoundException
	{
		ResultSet rs=null;
		int id2=0;
		String nom="";
		String prenom="";
		String telephone="";
		String mail="";
		
		String sql2 = "SELECT * FROM candidat WHERE identifiant='"+id+"'; ";
		try {
			rs = (ResultSet) st.executeQuery(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				try {
					id2 = rs.getInt("identifiant");
					nom = rs.getString("nom");
					prenom= rs.getString("prenom");
					telephone= rs.getString("telephone");
					mail= rs.getString("mail");
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(id2);
				System.out.println(nom);
				System.out.println(prenom);
				System.out.println(telephone);
				System.out.println(mail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/*
	 * M�thode pour r�cup�rer les r�ponses li�es � la question pass�e en param�tres
	 * 
	 */

	public void chercherQuestionEnBase(questionReponse[] questrep) throws SQLException
	{
		ResultSet	res				= null;
		String		Rep1 			= "";
		String		Rep2			= "";
		String		Rep3			= "";
		String		Rep4			= "";
		String		question		= "";
		byte		numq			=0;
	
		int numeroQuestion[] = new int[15];
		

		// On r�cup�re 5 Questions de cat�gorie CultureG�n�ral (1), ainsi que leur num�ro associ�.
		String request = "SELECT textesQuestion, numero FROM questions WHERE idCategorie = 1 ORDER by Rand() LIMIT 6";
		res = (ResultSet) st.executeQuery(request);
			//On remplit le libell� des 5 premi�res questions dans les objets questionReponse de notre tableau questrep, ainsi que notre tableau contenant l'ensemble des num�ros de question
			int i = 0;
			while (res.next()) 

			{
				questrep[i].libelleQuestion = res.getString("textesQuestion");	
				numeroQuestion[i] = res.getInt("numero");
				i++;
				if (i == 5) {
					i = 14;

			}
			
		//M�me chose pour les 5 questions suivantes :
		String request2 = "SELECT textesQuestion, numero FROM questions WHERE idCategorie = 1 ORDER by Rand() LIMIT 5";
		res = (ResultSet) st.executeQuery(request);
		//On remplit le libell� des 5 premi�res questions dans les objets questionReponse de notre tableau questrep, ainsi que notre tableau contenant l'ensemble des num�ros de question
		int j = 0;
		while (res.next()) 
		{
			questrep[i].libelleQuestion = res.getString("textesQuestion");	
			numeroQuestion[i] = res.getInt("numero");
			i++;
		}
			
	}
			
	}

	public int nombreCandidat(){
		
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




