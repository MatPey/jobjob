/**
 * Il s'agit du timer du panel de la question 11 (question stress), le timer est initialis� � 2min.
 */
package main;

import main.metier.TimerGeneral;

/**
 * @author Benjamin
 *version 1.0
 */

//H�ritage de la class timer general
public class TimerStress extends TimerGeneral {
		
	public TimerStress(int secondPassed){
		super(secondPassed);
		
		//Timer stress 2min
		TimerGeneral TimerS = new TimerGeneral(120);
		System.out.println(TimerS);
		
		
		// commentaire � effacer benji
	}
}
