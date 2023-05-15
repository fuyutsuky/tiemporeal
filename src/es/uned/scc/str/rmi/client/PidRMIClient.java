package es.uned.scc.str.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import es.uned.scc.str.pid.PidParameters;
import es.uned.scc.str.rmi.PidRMIPort;

public class PidRMIClient {

	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			String name = "PidRMIPort";
			int default_port = 9000;
			int port = -1;
			if (args.length == 1) {
				try {
					port = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					port = default_port;
					System.out
							.println("Port is not a number.....Using default port:  "
									+ port);
				}
			} else {
				port = default_port;
				System.out
						.println("Port parameter not defined ......Usisng default port:  "
								+ port);
			}

			Registry registry = LocateRegistry.getRegistry(port);
			PidRMIPort pidPort = (PidRMIPort) registry.lookup(name);

			// Start real time thread

			boolean startOk = pidPort.startController();
			if (startOk) {
				double y = 0;
				double yref = 0.5;
				@SuppressWarnings("unused")
				double pid_out = 0;
				int i = 0;
				// Run for 20 seconds
				int delayInMs = 1000;
				while (i < 20) {
					y = Math.cos(i / 100.0);
					pid_out = pidPort.PidCalculation(y, yref);

					// System.out.println("Pid calculated output:  "+ pid_out +
					// "for y: " + y + "& yref: " + yref);

					//pidPort.setPidParameters(new PidParameters(Kp, Ti, Td, F, OUTmax, OUTmin));
					i++;
					// sleep for 1s
					Thread.sleep(delayInMs);
				}
				// Stop the controller

				boolean stopOk = pidPort.stopController();
				if (!stopOk) {
					System.out
							.println("Error stopping remote Real Time Thread.....");
				}
			} else {

				System.out
						.println("Error starting remote Real Time Thread.....");
			}
		} catch (Exception e) {
			System.out.println("PidRMIClient exception: ");
			e.printStackTrace();
		}
	}
}
