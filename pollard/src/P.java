/**
 * Created by milson on 29.11.16.
 */
import java.math.BigInteger;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nail
 * Date: 25.12.12
 * Time: 9:00
 * To change this template use File | Settings | File Templates.
 */
public class P {
    /**
     * Степень вхождения простого числа(p^i<B) и результат
     * @param p простое число
     * @param B граница
     * @return [0] - i - степень вхождения [1] - p^i
     */
    public static BigInteger[] degreeOfOccurrenceAndResult(BigInteger p, BigInteger B)
    {
        BigInteger[] ret=new BigInteger[2];
        BigInteger t = p;
        BigInteger tNext = p.multiply(p);
        BigInteger i=BigInteger.ONE;
        while (tNext.compareTo(B)<0){
            t=tNext;
            tNext=t.multiply(p);
            i=i.add(BigInteger.ONE);
        }
        ret[0]=i;
        ret[1]=t;
        return ret;
    }

    /**
     * p^i
     */
    public static BigInteger primePowDegree(BigInteger p, BigInteger B){
        return degreeOfOccurrenceAndResult(p,B)[1];
    }
    /**
     * i
     */
    public static BigInteger primeDegree(BigInteger p, BigInteger B){
        return degreeOfOccurrenceAndResult(p,B)[1];
    }
//    не используется изза потребления памяти и перемножения без mod n
//    /**
//     * M=M(B) - перемножение простых чисел(вместе со степенями)
//     */
//    public static BigInteger getPrimeMult(List<BigInteger> a, BigInteger B)
//    {
//        BigInteger ret = new BigInteger("1");
//        for (int i = 0; i<a.size();i++)
//        {
//            BigInteger temp = primePowDegree(a.get(i),B);
//            ret=ret.multiply(temp);
//        }
//        return ret;
//    }

    /**
     * M=M(B) - перемножение простых чисел(вместе со степенями) по модулю n
     */
    public static BigInteger getPrimeMultWithMod(BigInteger B,BigInteger n)
    {
        PrimeNumbers primeNumbers=new PrimeNumbers(B);
        BigInteger ret = new BigInteger("1");
        for (BigInteger p = primeNumbers.getNext(); p!=null;p=primeNumbers.getNext())
        {
            BigInteger temp = primePowDegree(p,B);
            ret=ret.multiply(temp).mod(n);
        }
        return ret;
    }
}