package com.francium.cipher;

import com.francium.ElGamal.ElGamalEncResultModel;
import com.francium.ElGamal.ElGamalKeyModel;
import com.francium.ElGamal.ElGamalMethod;

public class ElGamal {

	private String plaintext;
	private ElGamalKeyModel egkm;
	private ElGamalEncResultModel egerm;
	private String DecResult;

	public ElGamal(String message) {
		setPlaintext(message);
		setEgkm(ElGamalMethod.KeyGen());
		setEgerm(ElGamalMethod.ElGamalEnc(getPlaintext(), getEgkm().getP(),
				getEgkm().getG(), getEgkm().getGamod()));
		setDecResult(ElGamalMethod.ElGamalDec(getEgerm().getU(), getEgerm()
				.getV(), getEgkm().getA(), getEgkm().getP()));
	}

	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public ElGamalKeyModel getEgkm() {
		return egkm;
	}

	public void setEgkm(ElGamalKeyModel egkm) {
		this.egkm = egkm;
	}

	public ElGamalEncResultModel getEgerm() {
		return egerm;
	}

	public void setEgerm(ElGamalEncResultModel egerm) {
		this.egerm = egerm;
	}

	public String getDecResult() {
		return DecResult;
	}

	public void setDecResult(String decResult) {
		DecResult = decResult;
	}

}
