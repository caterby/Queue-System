/*Jie Liu Netid:jxl164830 */
import java.util.LinkedList;
public class Queueing {
	public static double Seed=1111.0;
	public static double uni_rv(){
		double k = 16807.0;
		double m = 2.147483647e9;
		double rv;
		Seed = (k*Seed)%m;
		rv = Seed/m;
		return rv;
	}
	public static double exp_rv(double lambda){
		double exp;
		exp = ((-1)/lambda)*java.lang.Math.log(uni_rv());
		return exp;
	}
	
    public static void adding(LinkedList<Event> eventlinklist, Event newevent){
    	if(eventlinklist.size()>0){
    		int i = 0;
    		Event current;
    		while(i < eventlinklist.size()){
    			current =eventlinklist.get(i);
    			if(current.time > newevent.time)
    			{
    				eventlinklist.add(i, newevent);
    				break;
    			}
    			i++;
    		}
    		if(i >= eventlinklist.size())
    			eventlinklist.add(i, newevent);
    		else
    			;
    	}
      else if(eventlinklist.size()== 0){
    			eventlinklist.addFirst(newevent);
      }
      else
      	;
    }
	public static void simulation(double L, double m, int K, double lambda, double mu){
		LinkedList<Event> Eventlinklist=new LinkedList<Event>();
		int ARR = 0;
		int DEP = 1;
		double currentclock = 0.0;
		double baseclock = 0.0;
		// # of current jobs in system
		int currentN = 0; 
		// total jobs
		int totalN = 0;
		// block jobs
		int block = 0;   
		// expected value of the number of jobs in system
		double EN = 0.0;                               
		double U = 0.0; 
		Event CurrentEvent;
		int iterator=0;
		// generate first arrival Event with L*lambda arr rate
		adding(Eventlinklist, new Event(currentclock+exp_rv(L*lambda),ARR));     
		//iteration 100000 times
		while(iterator < 100000)                                
		{
			// store clock value
			baseclock = currentclock;    
			// get next Event from list
			CurrentEvent = Eventlinklist.removeFirst(); 
			// update clock value
			currentclock = CurrentEvent.time;                 
			if(currentN > 0 && currentN <= K)
				 // compute utilization of system
				U = U + (java.lang.Math.min(currentN, m)/m)*(currentclock-baseclock);              
			EN = EN + currentN * (currentclock-baseclock);
			switch(CurrentEvent.type){
			case 0:
				if(currentN >=0 && currentN<K){
					currentN++;
					totalN++;
					if(currentN==1){                
						//System.out.println("N"+N);// if N=1, then we can use an idle server and need generate a departure for it.
						adding(Eventlinklist, new Event(currentclock+exp_rv(mu),DEP));}
					    //adding(Eventlinklist, new Event(clock+java.lang.Math.min((N+1), m)*exp_rv(mu),DEP));
				}
				else if(currentN==K){
					block++;   
					}                      //for other cases block all arrivals
				adding(Eventlinklist, new Event(currentclock+exp_rv(java.lang.Math.max(L-currentN, 0)*lambda),ARR));  
				//adding(Eventlinklist, new Event(clock+exp_rv((L-N)*lambda),ARR)); 
				break;
			case 1: 
				currentN--;
				iterator++;
				if(currentN>0&&currentN<=K){
					    //System.out.println("N"+N);
					    adding(Eventlinklist, new Event(currentclock+exp_rv(java.lang.Math.min(currentN, m)*mu),DEP)); 
					    //adding(Eventlinklist, new Event(clock+exp_rv(mu),DEP)); 
				}
				break;
		}
	}
		// get E[T] 
		double ET = EN/totalN;  
	    // get E[N]
		EN = EN/currentclock;    
		// get blocking probability
		double Pblock = (double)block/(totalN+block);  
		// get utilization
		 U = U/(currentclock);                                    
	System.out.println("E[T]:"+ET);
	System.out.println("E[N]:"+EN);
	System.out.println("Utilization:"+U);
	System.out.println("Blocking probability:"+Pblock);
	System.out.println(" ");
}
	public static void main(String[] args){
		int m = 2;
		int L = 10;	
		int K = 4;
		double mu = 3;
		for(int i = 1;i <= 10;i++){
			double lambda = 0.1*m*mu*i;
			double rho = 0.1*i;
			System.out.println("Situation for rho ="+ rho+" "+"lambda ="+lambda+" "+" mu= "+mu+" "+" K="+K);
			System.out.println("lambda = "+lambda+" "+" mu= "+mu+" "+" K= "+K);
			simulation(L, m, K,lambda,mu);
		}
	}
}

