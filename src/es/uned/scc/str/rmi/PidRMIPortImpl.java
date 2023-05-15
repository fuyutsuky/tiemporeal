package es.uned.scc.str.rmi;

import java.rmi.RemoteException;

import javax.realtime.PeriodicParameters;

import es.uned.scc.str.pid.PidController;
import es.uned.scc.str.pid.PidParameters;
import es.uned.scc.str.rt.RTPidControllerThread;

public class PidRMIPortImpl implements PidRMIPort {

	private PidController pid;
	private PeriodicParameters pp;
	private RTPidControllerThread controlThread;

	public PidRMIPortImpl(PidController pid, PeriodicParameters pp) {
		this.pid = pid;
		this.pp = pp;
		this.controlThread=new RTPidControllerThread(pid, pp, null);
		
	}

	@Override
	public double PidCalculation(double y, double yref) throws RemoteException {

		return pid.getOutput(y, yref);
	}

	@Override
	public void setPidParameters(PidParameters parameters)
			throws RemoteException {
		pid.setD(parameters.getTd());
		pid.setF(parameters.getF());
		pid.setI(parameters.getTi());
		pid.setP(parameters.getKp());
		pid.setOutputLimits(parameters.getOUTmin(), parameters.getOUTmax());

	}

	@Override
	public boolean stopController() throws RemoteException {
		return controlThread.stopRTPid();
	}

	@Override
	public boolean startController() throws RemoteException {
		controlThread.start();
		return true;
	}

}
