package com.francium.cipher;

import com.francium.Rabin.RabinDecReturnModel;
import com.francium.Rabin.RabinKeyModel;
import com.francium.Rabin.RabinMethod;

public class Rabin {

	private String plaintext;
	private String ciphertext;
	private String suffix;
	private RabinKeyModel rkm;
	private RabinDecReturnModel rdrm;

	public Rabin(String message) {
		setPlaintext(message);
		setSuffix(Util.NumGen(10).toString());
		setRkm(RabinMethod.KeyGen());
		setCiphertext(RabinMethod.RabinEnc(getRkm().getN(), getPlaintext(),
				getSuffix()).toString());
		setRdrm(RabinMethod.RabinDec(getRkm().getP(), getRkm().getQ(), getRkm()
				.getN(), ciphertext, suffix));
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

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public RabinKeyModel getRkm() {
		return rkm;
	}

	public void setRkm(RabinKeyModel rkm) {
		this.rkm = rkm;
	}

	public RabinDecReturnModel getRdrm() {
		return rdrm;
	}

	public void setRdrm(RabinDecReturnModel rdrm) {
		this.rdrm = rdrm;
	}

}
