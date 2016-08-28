package com.francium.cipher;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Util {

	/**
	 * 生成一个大数
	 * @param length
	 * @return
	 */
	public static BigInteger NumGen(int length) {

		return (new BigInteger(length, new Random()));
	}

	/**
	 * 模重复平方法计算模幂 a^m (mod n)
	 *
	 * @param a
	 *            底数
	 * @param m
	 *            指数
	 * @param n
	 *            模
	 * @return 返回计算结果
	 */
	public static BigInteger ModExp(BigInteger a, BigInteger m, BigInteger n) {

		// 将传入的m转换成2进制字符串
		String bi = m.toString(2);

		// x用于计算最后的结果
		BigInteger x = new BigInteger("1");

		for (int i = bi.length() - 1; i >= 0; i--) {
			if (bi.charAt(i) == '1') {
				// x = x * a % n
				x = x.multiply(a).remainder(n);
			}
			// a = a * a % n
			a = a.multiply(a).remainder(n);
		}
		return x;

	}

	/**
	 * Euclid算法求最大公约数
	 *
	 * @param a    较大的数
	 * @param b    较小的数
	 * @return
	 */
	public static BigInteger Euclid(BigInteger a, BigInteger b) {

		// 如果b已经被除尽(b == 0)，则此时a为公约数，否则比较(b， a % b)
		if (b.compareTo(BigInteger.ZERO) == 0) {
			return a;
		} else {
			return Euclid(b, a.remainder(b));
		}

	}

	/**
	 * 扩展的Euclid算法求逆元，返回列表
	 *
	 * @param a
	 * @param b
	 * @return result[0] = 最大公约数 (result[1], result[2]) = 逆元(s, t)
	 */
	public static ArrayList<BigInteger> ExtendedEuclid(BigInteger a,
													   BigInteger b) {

		// 初始化一个列表存储返回结果
		ArrayList<BigInteger> result = new ArrayList<BigInteger>();

		// 采用递归求逆元和最大公约数

		// (b == 0)时，达到递归条件，为result赋值
		if (b.equals(BigInteger.ZERO)) {
			result.add(a);
			result.add(BigInteger.ONE);
			result.add(BigInteger.ZERO);
			return result;
		}

		// 未达到条件是，继续辗转相除(b, a % b)
		result = ExtendedEuclid(b, a.remainder(b));

		// 得到返回值后求逆元temp = x; x = y; y = temp - (a / b) * y
		BigInteger temp = result.get(1);
		result.set(1, result.get(2));
		result.set(2, temp.subtract((a.divide(b)).multiply(result.get(2))));

		return result;
	}

	/**
	 * 大素数的生成(生成一个比特长度为length的素数) 根据Rabin与RSA、ElGamal的不同生成4k + 3的大素数或者普通大素数
	 *
	 * @param length
	 *            大素数的2进制长度
	 * @param increment
	 *            大素数的递增，若是Rabin求大素数则为"4",否则为"1"
	 * @return 返回一个大素数
	 */
	public static BigInteger PrimeGen(int length, String increment) {

		// Rabin Miller 法求大素数

		// 随机生成一个大数
		BigInteger n = Util.NumGen(length);
		// 若是Rabin中求大素数，则生成4k + 3的大数，否则生成普通大数
		n = n.multiply(new BigInteger(increment)).add(new BigInteger("3"));

		// 安全参数t
		int t = 10;

		// 进行到海枯石烂
		while (true) {

			// 定义标记，如果一直为真则n为素数
			boolean flag = true;

			// 若n能被2整除则不用考虑，直接进行下一个循环
			if (n.remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) {
				flag = false;
			} else if (n.remainder(new BigInteger("2")).compareTo(
					BigInteger.ONE) == 0) {

				// 检验t次
				for (int k = 1; k <= t; k++) {

					// 选取随机数b，(2 <= b <= n - 2)
					BigInteger b = Util.NumGen(length);
					while (b.compareTo(new BigInteger("2")) < 0
							|| b.compareTo(n.subtract(new BigInteger("2"))) > 0) {
						b = Util.NumGen(length);
					}

					// 计算r = b ^ (n - 1) % n, 若r != 1, 则n为合数，进行下一轮循环
					BigInteger r = ModExp(b, n.subtract(BigInteger.ONE), n);
					if (r.compareTo(BigInteger.ONE) != 0) {
						flag = false;
						break;
					}
				}
			}

			// 如果标记始终为真，则n为素数，返回
			if (flag) {
				return n;
			}

			// 根据Rabin和RSA、ElGamal的不同n加上一定的值(4 or 1)
			n = n.add(new BigInteger(increment));
		}

	}

	/**
	 * 判定一个数是否为大素数
	 * @param n
	 * @return
	 */
	public static boolean isPrime(BigInteger n) {
		// 安全参数t
		int t = 10;
		// 定义标记，如果一直为真则n为素数
		boolean flag = true;

		// 若n能被2整除则不用考虑，直接返回
		if (n.remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) {
			flag = false;
			return flag;
		} else if (n.remainder(new BigInteger("2")).compareTo(
				BigInteger.ONE) == 0) {

			// 检验t次
			for (int k = 1; k <= t; k++) {

				// 选取随机数b，(2 <= b <= n - 2)
				BigInteger b = Util.NumGen(n.toString(2).length());
				while (b.compareTo(new BigInteger("2")) < 0
						|| b.compareTo(n.subtract(new BigInteger("2"))) > 0) {
					b = Util.NumGen(n.toString(2).length());
				}

				// 计算r = b ^ (n - 1) % n, 若r != 1, 则n为合数，返回
				BigInteger r = ModExp(b, n.subtract(BigInteger.ONE), n);
				if (r.compareTo(BigInteger.ONE) != 0) {
					flag = false;
					return flag;
				}
			}
		}

		// 如果标记始终为真，则n为素数，返回
		return flag;
	}

	/**
	 * 返回p的一个原根
	 *
	 * @param p
	 * @return
	 */
	public static BigInteger Primitive(BigInteger p) {

		// p为素数，所以q = 与p互素的数的个数 = p - 1
		BigInteger q = p.subtract(BigInteger.ONE);

		// 用一个HashSet存储p-1也就是q的所有质因子
		HashSet<BigInteger> primeFactors = new HashSet<BigInteger>();

		// 当i小于(p - 1) % 2 或者 q被除尽或者q变为质数时退出循环，否则继续寻找质因子
		BigInteger i = new BigInteger("2");
		// i小于(p - 1) % 2
		while (i.compareTo((p.subtract(BigInteger.ONE)).divide(new BigInteger(
				"2"))) <= 0) {
			// q被除尽 || q是一个素数
			if (q.equals(BigInteger.ZERO) || isPrime(q)) {
				break;
			}
			// 找到质因子放入HashSet的集合primeFactors中
			while (q.remainder(i).equals(BigInteger.ZERO)) {
				primeFactors.add(i);
				q = q.divide(i);
			}
			// i++
			i = i.add(BigInteger.ONE);
		}
		if (q.compareTo(BigInteger.ZERO) != 0){
			primeFactors.add(q);
		}
		// 用一个ArrayList的列表dividedPrimeFactors存储(p - 1) % (p的质因子)的所有结果
		ArrayList<BigInteger> dividedPrimeFactors = new ArrayList<BigInteger>();
		q = p.subtract(BigInteger.ONE);
		for (BigInteger j : primeFactors) {
			// q % 质因子
			dividedPrimeFactors.add(q.divide(j));
		}

		i = new BigInteger("2");

		// i < p 继续进行，找到第一个生成元退出
		while (i.compareTo(p) < 0) {

			// 若标记一直为true则是生成元
			boolean flag = true;
			for (BigInteger j : dividedPrimeFactors) {

				// 若(i ^ j) % p == 1,则i不是生成元
				if (ModExp(i, j, p).equals(BigInteger.ONE)) {
					flag = false;
					break;
				}
			}

			// 如果标记始终为true,则返回生成元
			if (flag) {
				return i;
			}

			// i++
			i = i.add(BigInteger.ONE);
		}

		// 若没有找到,返回0,此一步其实多余，但防止编译器报错
		return BigInteger.ZERO;

	}

	/**
	 * 求出一个素数的所有原根
	 * @param p
	 * @return
	 */
	public static ArrayList<BigInteger> NPrimitive(BigInteger p) {

		// 先求出一个原根
		BigInteger firstPrimitive = Primitive(p);

		// 求模(p - 1)的简化剩余系
		BigInteger q = p.subtract(BigInteger.ONE);
		ArrayList<BigInteger> simpSurplus = new ArrayList<BigInteger>();
		for (BigInteger i = BigInteger.ONE; i.compareTo(q) < 0; i = i.add(BigInteger.ONE)) {
			if (Euclid(i, q).equals(BigInteger.ONE)) {
				simpSurplus.add(i);
			}
		}

		// 求所有原根
		ArrayList<BigInteger> allPrimitive = new ArrayList<BigInteger>();
		for(BigInteger i: simpSurplus) {
			allPrimitive.add(ModExp(firstPrimitive, i, p));
		}

		return allPrimitive;
	}

	/**
	 * 返回p的指数表
	 * @param p
	 * @return
	 */
	public static ArrayList<BigInteger> Ord(BigInteger p) {
		ArrayList<BigInteger> ord = new ArrayList<BigInteger>();
		for (BigInteger i = BigInteger.ONE; i.compareTo(p) < 0; i = i.add(BigInteger.ONE)) {
			for(BigInteger j = BigInteger.ONE; j.compareTo(p) < 0; j = j.add(BigInteger.ONE)) {
				if (ModExp(i, j, p).equals(BigInteger.ONE)) {
					ord.add(j);
					break;
				}
			}
		}
		return ord;
	}

	/**
	 * CRT解同余式组，传入两个列表代表b，m，传入范围，返回结果
	 * @param b
	 * @param m
	 * @param min
	 * @param max
	 * @return
	 */
	public static BigInteger CRT(ArrayList<BigInteger> b, ArrayList<BigInteger> m, BigInteger min, BigInteger max) {

		// 先计算m的乘积
		BigInteger sum = BigInteger.ONE;
		for (BigInteger i:m) {
			sum = sum.multiply(i);
		}

		// 求逆元inverseElement*(sum/mi)%mi == 1（用ExtendEuclid求逆元不知为何不对，不用ExtendEuclid的话求大数比较难）
		ArrayList<BigInteger> inverseElement = new ArrayList<BigInteger>();
		for (BigInteger i:m) {
			BigInteger j = BigInteger.ONE;
			while(j.compareTo(i)<= 0){
				if(j.multiply(sum).divide(i).remainder(i).equals(BigInteger.ONE)){
					inverseElement.add(j);
					break;
				}
				j = j.add(BigInteger.ONE);
			}
		}

		// 按范围求出结果
		BigInteger result = BigInteger.ZERO;
		for (int i = 0; i<m.size(); i++) {
			result = result.add(inverseElement.get(i).multiply(sum).divide(m.get(i)).multiply(b.get(i)));
		}
		while (result.compareTo(min)<0){
			result = result.add(sum);
		}
		while (result.compareTo(max) > 0){
			result =result.subtract(sum);
		}
		return result;
	}

	/**
	 * 在p是积素数的情况下，直接用Euler判别法则，不使用二次互反律，这里偷下懒
	 * @param a
	 * @param p
	 * @return
	 */
	public static int Legendre(BigInteger a, BigInteger p) {
		BigInteger temp = ModExp(a, (p.subtract(BigInteger.ONE).divide(new BigInteger("2"))), p);
		if(temp.equals(BigInteger.ONE)) {
			return 1;
		} else if (temp.equals(p.subtract(BigInteger.ONE))){
			return -1;
		} else {
			return 0;
		}
	}

}
