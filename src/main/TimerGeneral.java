package main;
import java.util.Timer;
import java.util.TimerTask;
 
/**
 * 
 * @author Alban
 * CLASSE TIMER : COMPTEURS UTILISES SUR LES FORMULAIRES
 */
public class TimerGeneral
{
	// ATTRIBUTS DE LA CLASSE
	int secondPassed = 1800;
	int minuteAff = 0;
	int secondeAff = 0;
	String secondeAffS = "";
	
    Timer timer = new Timer();
    TimerTask tache = new TimerTask() 
    {     
        @Override
        public void run() 
        {
        	if(secondPassed <= 0)
        	{
        		tache.cancel();
        	}
        	else
        	{
        		secondPassed--;
        		affichage(secondPassed);
        	}
        }
    };
    
    // DELAI TIMER
    public void start()
    {
    	timer.scheduleAtFixedRate(tache, 1000, 1000);
    }
    
    // CONVERTIR LE TIMER : SECONDES => MINUTES/SECONDES
    public String affichage(int sec)
    {
		String res = "";
    	secondeAff = (sec%60);
		minuteAff = (sec - secondeAff)/60;
		if(secondeAff < 10)
		{
			secondeAffS = "0" + secondeAff;
			res = minuteAff + ":" + secondeAffS;
//			System.out.println(minuteAff + ":" + secondeAffS);
		}
		else
		{
			res = minuteAff + ":" + secondeAff;
//        	System.out.println(minuteAff + ":" + secondeAff);
		}
		return res;
    }
     
//    public static void main(String[] args) 
//    {
//    	Timer tp = new Timer();
//    	tp.start();
//    }
}