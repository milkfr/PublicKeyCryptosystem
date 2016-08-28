package com.francium.Rabin;

import java.math.BigInteger;
import java.util.ArrayList;

import com.francium.cipher.Util;

public class RabinMethod {

	public static int LENGTH = 513;

	/**
	 * 随机生成Rabin的公钥n，私钥(p, q)并返回
	 *
	 * @return
	 */
	public static RabinKeyModel KeyGen() {

		// 保证同阶的参照物
		BigInteger refer1 = (new BigInteger("2")).pow(LENGTH-1);
		BigInteger refer2 = (new BigInteger("2")).pow(LENGTH);
		// 随机生成两个大小不同的4k + 3的大素数
		BigInteger p = Util.PrimeGen(LENGTH, "4");
		while(p.compareTo(refer1) < 0 || p.compareTo(refer2) > 0) {
			p = Util.PrimeGen(LENGTH, "4");
		}
		BigInteger q = Util.PrimeGen(LENGTH, "4");
		while(q.compareTo(refer1) < 0 || q.compareTo(refer2) > 0) {
			q = Util.PrimeGen(LENGTH, "4");
		}
		while (p.equals(q)) {
			q = Util.PrimeGen(LENGTH, "4");
			while(q.compareTo(refer1) < 0 || q.compareTo(refer2) > 0) {
				q = Util.PrimeGen(LENGTH, "4");
			}
		}
		BigInteger n = p.multiply(q);

		// 通过RabinKeyModel类型返回公钥和私钥
		RabinKeyModel rkm = new RabinKeyModel();
		rkm.setP(p);
		rkm.setQ(q);
		rkm.setN(n);
		return rkm;

	}

	/**
	 * 传入公钥n，明文plaintext，用于确认的尾缀suffix，返回加密后的密文ciphertext
	 *
	 * @param n
	 * @param plaintext
	 * @param suffix
	 * @return ciphertext
	 */
	public static BigInteger RabinEnc(BigInteger n, String plaintext,
									  String suffix) {

		// 将明文和尾缀连接在一起，成为新的全部的要加密的明文
		BigInteger allPlaintext = new BigInteger(plaintext + suffix);

		// 密文ciphertext = allPlaintext * allPlaintext % n
		BigInteger ciphertext = allPlaintext.multiply(allPlaintext)
				.remainder(n);

		// 返回密文
		return ciphertext;
	}

	/**
	 * 传入密钥密文和后缀，获取明文
	 *
	 * @param p
	 * @param q
	 * @param n
	 * @param ciphertext
	 * @param suffix
	 * @return
	 */
	public static RabinDecReturnModel RabinDec(BigInteger p, BigInteger q, BigInteger n,
											   String ct, String suffix) {

		BigInteger ciphertext = new BigInteger(ct);
		// 用一个ArrayList存储ExtendedEuclid算法返回的逆元，为了方便表示赋值给(s, t)
		ArrayList<BigInteger> inverseElement = Util.ExtendedEuclid(p, q);
		BigInteger s = inverseElement.get(1);
		BigInteger t = inverseElement.get(2);

		// u = ciphertext ^ ((p - 1) / 4) % n, v = ciphertext ^ ((q - 1) / 4) %
		// n
		BigInteger u = Util.ModExp(ciphertext,
				(p.add(BigInteger.ONE)).divide(new BigInteger("4")), p);
		BigInteger v = Util.ModExp(ciphertext,
				(q.add(BigInteger.ONE)).divide(new BigInteger("4")), q);

		// x = (t * q * u + s * p * v) % n, y = (t * q *u + s * p * v) % n
		BigInteger x = ((t.multiply(q).multiply(u)).add(s.multiply(p).multiply(
				v))).remainder(n);
		BigInteger y = ((t.multiply(q).multiply(u)).subtract(s.multiply(p)
				.multiply(v))).remainder(n);

		// 同余式的4个根是x, -x % n, y, -y % n, 根据suffix确定是哪一个根
		// m1 = abs(x)
		String m1 = x.abs().toString();
		// m2 = n - abs(x)
		String m2 = (n.subtract(x.abs())).toString();
		// m3 = abs(y)
		String m3 = y.abs().toString();
		// m4 = n - abs(y)
		String m4 = (n.subtract(y.abs())).toString();

		RabinDecReturnModel rdrm = new RabinDecReturnModel();
		rdrm.setS(s);
		rdrm.setT(t);
		rdrm.setU(u);
		rdrm.setV(v);
		rdrm.setM1(m1);
		rdrm.setM2(m2);
		rdrm.setM3(m3);
		rdrm.setM4(m4);

		if (m1.substring(m1.length() - suffix.length()).equals(suffix)) {
			rdrm.setDecResult(m1.substring(0, m1.length() - suffix.length()));
			return rdrm;
		}
		if (m2.substring(m2.length() - suffix.length()).equals(suffix)) {
			rdrm.setDecResult(m2.substring(0, m2.length() - suffix.length()));
			return rdrm;
		}
		if (m3.substring(m3.length() - suffix.length()).equals(suffix)) {
			rdrm.setDecResult(m3.substring(0, m3.length() - suffix.length()));
			return rdrm;
		}
		rdrm.setDecResult(m4.substring(0, m4.length() - suffix.length()));
		return rdrm;
	}
}
