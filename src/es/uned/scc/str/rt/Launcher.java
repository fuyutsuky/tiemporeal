package es.uned.scc.str.rt;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.realtime.PeriodicParameters;
import javax.realtime.RelativeTime;

import es.uned.scc.str.pid.PidController;
import es.uned.scc.str.rmi.PidRMIPort;
import es.uned.scc.str.rmi.PidRMIPortImpl;

public class Launcher {
	public static void main(String Args[]) {

		PidController PiD = new PidController(0.4, 0.20, 1, 0.6);
		PeriodicParameters pp = new PeriodicParameters(new RelativeTime(500, 0));
		
		int port=9000;
		boolean rmiPortOk=false;
		
		/*	RTPidControllerThread RTC = new RTPidControllerThread(PiD, pp, null);
		 

		RTC.start();

		try {
			RTC.join();
		} catch (InterruptedException ie) {
			System.err.println(" Error: " + ie.getMessage());
		}*/
		
		
		if(System.getSecurityManager()==null){

			System.setSecurityManager(new SecurityManager());
			}
		
			try{
				String name="PidRMIPort";
				
				final PidRMIPort pid_rmi = new PidRMIPortImpl(PiD, pp);
				final PidRMIPort stub = (PidRMIPort) UnicastRemoteObject.exportObject(pid_rmi,port);
				final Registry registry = LocateRegistry.createRegistry(port);
				registry.rebind(name,stub);
				System.out.println("PidRMIPort bound in port:  " + port);
				rmiPortOk = true;
			}catch (Exception e){
				System.err.println("PidRMIPort exception: ");
				e.printStackTrace();
				rmiPortOk = false;
				}

	}

}
