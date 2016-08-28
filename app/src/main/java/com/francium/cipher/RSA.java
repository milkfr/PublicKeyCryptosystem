package com.francium.cipher;

import com.francium.RSA.RSAKeyModel;
import com.francium.RSA.RSAMethod;

public class RSA {

	private String plaintext;
	private String ciphertext;
	private RSAKeyModel rsakm;
	private String DecResult;

	public RSA(String message) {
		setPlaintext(message);
		setRsakm(RSAMethod.KeyGen());
		setCiphertext(RSAMethod.RSAEnc(getPlaintext(), getRsakm().getN(),
				getRsakm().getE()));
		setDecResult(RSAMethod.RSADec(getCiphertext(), getRsakm().getN(),
				getRsakm().getD()));
	}

	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public String getCiphertext() {
		return ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	public RSAKeyModel getRsakm() {
		return rsakm;
	}

	public void setRsakm(RSAKeyModel rsakm) {
		this.rsakm = rsakm;
	}

	public String getDecResult() {
		return DecResult;
	}

	public void setDecResult(String decResult) {
		DecResult = decResult;
	}

}
