package com.francium.RSA;

import com.francium.cipher.Util;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSAMethod {

	public static int LENGTH = 513;

	/**
	 * 随机生成RSA的密钥
	 *
	 * @return
	 */
	public static RSAKeyModel KeyGen() {

		// 保证同阶的 参照物
		BigInteger refer1 = (new BigInteger("2")).pow(LENGTH-1);
		BigInteger refer2 = (new BigInteger("2")).pow(LENGTH);
		// 获取两个互不相同的大素数
		BigInteger p = Util.PrimeGen(LENGTH, "1");
		while(p.compareTo(refer1) < 0 || p.compareTo(refer2) > 0) {
			p = Util.PrimeGen(LENGTH, "1");
		}
		BigInteger q = Util.PrimeGen(LENGTH, "1");
		while(q.compareTo(refer1) < 0 || q.compareTo(refer2) > 0) {
			q = Util.PrimeGen(LENGTH, "1");
		}
		while (p.equals(q)) {
			q = Util.PrimeGen(LENGTH, "1");
			while(q.compareTo(refer1) < 0 || q.compareTo(refer2) > 0) {
				q = Util.PrimeGen(LENGTH, "1");
			}
		}
		BigInteger n = p.multiply(q);

		// 获取和n互素的数的个数coPrime = (p - 1) * (q - 1)
		BigInteger coPrime = (p.subtract(BigInteger.ONE)).multiply(q
				.subtract(BigInteger.ONE));

		// 随机生成一个私钥e，1 < e < coPrime, (e, coPrime) = 1
		BigInteger e = Util.NumGen(LENGTH);
		// e > 1 && e < coPrime && (coPrime, e) = 1
		while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(coPrime) >= 0
				|| Util.Euclid(coPrime, e).compareTo(BigInteger.ONE) != 0) {
			e = Util.NumGen(LENGTH);
		}

		// 用一个ArrayList存储逆元
		ArrayList<BigInteger> inverseElement = Util.ExtendedEuclid(coPrime, e);

		// 逆元t要大于0，否则容易出错
		while (inverseElement.get(2).compareTo(BigInteger.ZERO) <= 0) {
			e = Util.NumGen(LENGTH);
			while (e.compareTo(BigInteger.ONE) <= 0
					|| e.compareTo(coPrime) >= 0
					|| Util.Euclid(coPrime, e).compareTo(BigInteger.ONE) != 0) {
				e = Util.NumGen(LENGTH);
			}

			inverseElement = Util.ExtendedEuclid(coPrime, e);
		}

		// 将密钥返回
		RSAKeyModel rsakm = new RSAKeyModel();
		rsakm.setP(p);
		rsakm.setQ(q);
		rsakm.setN(n);
		rsakm.setCoPrime(coPrime);
		rsakm.setE(e);
		rsakm.setD(inverseElement.get(2));
		return rsakm;

	}

	/**
	 * 传入明文，公钥(e, n)，返回密文
	 *
	 * @param plaintext
	 * @param n
	 * @param e
	 * @return
	 */
	public static String RSAEnc(String plaintext, BigInteger n, BigInteger e) {

		// RSA加密 ciphertext = plaintext ^ e % n
		return Util.ModExp(new BigInteger(plaintext), e, n).toString();
	}

	/**
	 * 传入明文，私钥(d, n)，返回明文
	 *
	 * @param ciphertext
	 * @param n
	 * @param d
	 * @return
	 */
	public static String RSADec(String ciphertext, BigInteger n,
								BigInteger d) {

		// RSA解密 plaintext = ciphertext ^ d % n
		return (Util.ModExp(new BigInteger(ciphertext), d, n)).toString();
	}

}
