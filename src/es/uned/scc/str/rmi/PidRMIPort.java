package es.uned.scc.str.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.uned.scc.str.pid.PidParameters;

public interface PidRMIPort extends Remote{
	
	double PidCalculation(double y, double yref) throws RemoteException;
	void setPidParameters(PidParameters parameters) throws RemoteException;
	boolean stopController() throws RemoteException;
	boolean startController() throws RemoteException;

}
