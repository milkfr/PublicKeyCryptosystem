package com.francium.ElGamal;

import java.math.BigInteger;

public class ElGamalKeyModel {

	private BigInteger p;
	private BigInteger g;
	private BigInteger a;
	private BigInteger gamod;

	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public BigInteger getG() {
		return g;
	}

	public void setG(BigInteger g) {
		this.g = g;
	}

	public BigInteger getA() {
		return a;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public BigInteger getGamod() {
		return gamod;
	}

	public void setGamod(BigInteger gamod) {
		this.gamod = gamod;
	}

}
