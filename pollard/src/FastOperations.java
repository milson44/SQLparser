/**
 * Created by milson on 29.11.16.
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FastOperations {
    public static BigInteger power(BigInteger a,BigInteger e,BigInteger n) {
        try {

            BigInteger zero = new BigInteger("0");
            BigInteger one = new BigInteger("1");
            BigInteger two = new BigInteger("2");
            BigInteger c;
            int l=0;
            List<Short> ebin=new ArrayList<Short>();
            if ((a.compareTo(zero)<0)||(e.compareTo(zero)<0)||(n.compareTo(zero)<0)) return zero;
            while(e.compareTo(zero)!=0)
            {
                ebin.add(e.mod(two).shortValue());
                if (e.mod(two).compareTo(zero)!=0) e=e.subtract(one);
                e=e.divide(two);
            }
            l=ebin.size();
            List<BigInteger> cpower=new ArrayList<BigInteger>();
            BigInteger s;
            cpower.add(a);
            for (int i=l-2;i>=0;i--)
            {
                if(ebin.get(i)==1)
                {
                    s=cpower.get(l-i-2);
                    s=s.multiply(s);
                    s=s.mod(n);
                    s=s.multiply(a);
                    s=s.mod(n);
                    cpower.add(s);
                }
                else
                {
                    s=cpower.get(l-i-2);
                    s=s.multiply(s);
                    s=s.mod(n);
                    cpower.add(s);
                }
            }
            c=cpower.get(l-1);
            return c;
        } catch (Exception e2) {
            Debug.println("ex2", e2.toString());
            return null;
        }
    }

    /**
     * Нахождение  a^M mod n = a^M(B) mod n
     * при большом значении B число M(B) может оказаться чрезвычайно большим
     * разбиваем произведение M(B) на блоки L=i-количество простых чисел на интервале
     * @param B
     * @param n
     * @return
     */
    public static BigInteger aMBmodn(BigInteger a,BigInteger B,BigInteger n){
        PrimeNumbers primeNumbers=new PrimeNumbers(B);
        BigInteger prime=primeNumbers.getNext();
        BigInteger Mi=P.primePowDegree(prime,B); //p в степени
        BigInteger ai=FastOperations.power(a,Mi,n);
        BigInteger aiplus1;
        BigInteger Miplus1;
        while (prime!=null){
            Miplus1=P.primePowDegree(prime,B); //p в степени
            aiplus1=FastOperations.power(ai,Miplus1,n);
        }
        //последовательность строится, осталось взять предел этой последовательности
        return null;
    }
}