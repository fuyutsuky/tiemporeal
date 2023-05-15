package es.uned.scc.str.pid;

public class PidParameters {

	private double Kp;
	private double Ti;
	private double Td;
	// private double Beta;
	// private double Tr;
	private double F;
	//private double SP;
	private double OUTmax;
	private double OUTmin;

	public PidParameters(double Kp, double Ti, double Td, double F, double OUTmax, double OUTmin) {

		this.setKp(Kp);
		this.setTi(Ti);
		this.setTd(Td);
		// this.setBeta(Beta);
		// this.setTr(Tr);
		this.setF(F);
		//this.setSP(SP);
		this.setOUTmax(OUTmax);
		this.setOUTmin(OUTmin);

	}

	// ************
	// Getters y Setters
	// ************

	public double getKp() {
		return Kp;
	}

	public void setKp(double kp) {
		Kp = kp;
	}

	// -----------

	public double getTi() {
		return Ti;
	}

	public void setTi(double ti) {
		Ti = ti;
	}

	// ----------

	public double getTd() {
		return Td;
	}

	public void setTd(double td) {
		Td = td;
	}

	/*
	 * public double getBeta() { return Beta; }
	 * 
	 * public void setBeta(double Beta) { Beta=Beta; }
	 * 
	 * 
	 * public double getTr() { return Tr; }
	 * 
	 * public void setTr(double Tr) { Tr=Tr; }
	 */

	public double getF() {
		return F;
	}

	public void setF(double f) {
		F = f;
	}

	// ----------

/*	public double getSP() {
		return SP;
	}

	public void setSP(double sp) {
		SP = sp;
	}*/

	// ----------

	public double getOUTmax() {
		return OUTmax;
	}

	public void setOUTmax(double outmax) {
		OUTmax = outmax;
	}

	// ----------

	public double getOUTmin() {
		return OUTmin;
	}

	public void setOUTmin(double outmin) {
		OUTmin = outmin;
	}
	// ----------

}
