package com.francium.ElGamal;

import com.francium.cipher.Util;

import java.math.BigInteger;

public class ElGamalMethod {

	public static int LENGTH = 513;

	/**
	 * 随机生成ElGamal的密钥并返回
	 *
	 * @return
	 */
	public static ElGamalKeyModel KeyGen() {

		// 保证阶数的 参照物
		BigInteger refer = new BigInteger("2").pow(LENGTH-1);
		// 随机生成一个大素数，该大素数的质因子为2和另一个大素数两个
		BigInteger q = Util.PrimeGen(LENGTH, "1");
		while (q.compareTo(refer) < 0) {
			q = Util.PrimeGen(LENGTH, "1");
		}
		BigInteger p = q.multiply(new BigInteger("2")).add(BigInteger.ONE);
		while(!Util.isPrime(p)) {
			q = Util.PrimeGen(LENGTH, "1");
			while (q.compareTo(refer) < 0) {
				q = Util.PrimeGen(LENGTH, "1");
			}
			p = q.multiply(new BigInteger("2")).add(BigInteger.ONE);
		}

		// 找到这个大素数的第一个生成元
		BigInteger g = Util.Primitive(p);

		// 随机生成一个密钥a, 1 <= a <= p - 2
		BigInteger a = Util.NumGen(LENGTH);
		while (a.compareTo(BigInteger.ONE) < 0
				|| a.compareTo(p.subtract(BigInteger.ONE)) >= 0) {
			a = Util.NumGen(LENGTH);
		}

		// 获取公钥(g ^ a % p)
		BigInteger gamod = Util.ModExp(g, a, p);

		// 返回密钥
		ElGamalKeyModel egkm = new ElGamalKeyModel();
		egkm.setP(p);
		egkm.setG(g);
		egkm.setGamod(gamod);
		egkm.setA(a);
		return egkm;

	}

	/**
	 * ElGamal加密，返回(u, v)
	 *
	 * @param plaintext
	 * @param p
	 * @param g
	 * @param gamod
	 * @return
	 */
	public static ElGamalEncResultModel ElGamalEnc(String plaintext, BigInteger p,
												   BigInteger g, BigInteger gamod) {

		// 随机生成一个数k, 1 <= k <= p - 2
		BigInteger k = Util.NumGen(LENGTH);
		while (k.compareTo(BigInteger.ONE) < 0
				|| k.compareTo(p.subtract(BigInteger.ONE)) >= 0) {
			k = Util.NumGen(LENGTH);
		}

		// u = g ^ k % p, v = plaintext * (g ^ a) ^ k % p
		BigInteger u = Util.ModExp(g, k, p);
		BigInteger v = (new BigInteger(plaintext)).multiply(
				Util.ModExp(gamod, k, p)).remainder(p);

		// 返回(k, u, v)
		ElGamalEncResultModel egerm = new ElGamalEncResultModel();
		egerm.setK(k);
		egerm.setU(u);
		egerm.setV(v);
		return egerm;

	}

	/**
	 * ElGamal解密，返回明文字符串
	 *
	 * @param u
	 * @param v
	 * @param a
	 * @param p
	 * @return
	 */
	public static String ElGamalDec(BigInteger u, BigInteger v, BigInteger a,
									BigInteger p) {

		// ciphertext = u ^ (p - 1 - a) * v % p
		return (Util.ModExp(u, p.subtract(BigInteger.ONE).subtract(a), p)
				.multiply(v).remainder(p)).toString();
	}
}
