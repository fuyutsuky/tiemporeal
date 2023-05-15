package es.uned.scc.str.rt;

//import javax.realtime.PeriodicParameters;
//import javax.realtime.PriorityParameters;
//import javax.realtime.PriorityScheduler;
//import javax.realtime.RealtimeThread;
//import javax.realtime.RelativeTime;
import javax.realtime.*;
import es.uned.scc.str.pid.*;

public class RTPidControllerThread extends RealtimeThread {

	private boolean running = true;
	private PidController PiD;

	// Constructor
	public RTPidControllerThread(PidController controller,
			PeriodicParameters pp, ImmortalMemory mem) {
		//
		//
		super(null, pp, null, mem, null, null);

		this.setSchedulingParameters(new PriorityParameters(PriorityScheduler
				.getMinPriority(this) + 2));
		this.setName("RTPidControllerThread");
		this.PiD = controller;

	}

	public void run() {
		Clock rtclock = Clock.getRealtimeClock();
		AbsoluteTime time = rtclock.getTime();
		AbsoluteTime lasttime = new AbsoluteTime();
		RelativeTime rtime = new RelativeTime();
		// -------------------
		// PidController PiD = new PidController(0.4,0.20,1,0.6);
		PiD.setPID(0.4, 0.20, 1, 0.6);
		PiD.setSetpoint(40);
		PiD.setOutputLimits(0, 110);

		// for(int i=0; i<8; ++i){
		// System.out.println("Salida PID" + PiD.getOutput(PiD.getOutput(),
		// 50-i));
		// waitForNextPeriod();
		// }
		try {
			while (this.running) {
				// Update last time
				lasttime.set(time);
				// get actual time
				rtclock.getTime(time);

				time.subtract(lasttime, rtime);
				double output = PiD.getOutput();
				waitForNextPeriod();
				System.out.println(this.getName() + "  Salida PID  " + output);
				// if(output==100){this.running=false;}
			}
System.out.println( this.getName() + " Thread parado ");
		}

		catch (Exception e) {
			System.err.println(this.getName() + " Error: " + e.getMessage());
		
		}

	}

	public final boolean stopRTPid() {
		return stopRunning();
		

	}

	private synchronized boolean stopRunning() {
		this.running = false;
		return true;

	}

	/*
	 * public static void main(String [] args){ PeriodicParameters pp = new
	 * PeriodicParameters(new RelativeTime(500, 0)); RTPidControllerThread RTC =
	 * new RTPidControllerThread(PiD, pp, null);
	 * 
	 * RTC.start();
	 * 
	 * try{ RTC.join(); } catch (InterruptedException ie) { //ignore } }
	 */

}
