public class Event {
	   public double time;
	   public int type;
	   public Event next;
	   Event(){};
	   public Event(double t, int k){
		   this.time=t;
		   this.type=k;
	   }
	}
