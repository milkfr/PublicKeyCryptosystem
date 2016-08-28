package com.francium.RSA;

import java.math.BigInteger;

public class RSAKeyModel {

	private BigInteger p;
	private BigInteger q;
	private BigInteger n;
	private BigInteger coPrime;
	private BigInteger e;
	private BigInteger d;

	public BigInteger getP() {
		return p;
	}

	public void setP(BigInteger p) {
		this.p = p;
	}

	public BigInteger getQ() {
		return q;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

	public BigInteger getE() {
		return e;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

	public BigInteger getD() {
		return d;
	}

	public void setD(BigInteger d) {
		this.d = d;
	}

	public BigInteger getCoPrime() {
		return coPrime;
	}

	public void setCoPrime(BigInteger coPrime) {
		this.coPrime = coPrime;
	}

}
