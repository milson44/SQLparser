/**
 * Created by milson on 29.11.16.
 */
import java.math.BigInteger;

/**
 * Created with IntelliJ IDEA.
 * User: nail
 * Date: 25.12.12
 * Time: 5:40
 * To change this template use File | Settings | File Templates.
 */
public class PrimeNumbers {
    int[] primes={2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61};
    BigInteger two=BigInteger.valueOf(2);
    BigInteger current=null;
    BigInteger B;

    /**
     *
     * @param B граница до которой ищем простые числа
     */
    public PrimeNumbers(BigInteger B) {
        this.B = B;
    }

    public PrimeNumbers(BigInteger B1,BigInteger B2) {
        if(B1.mod(BigInteger.valueOf(2)).compareTo(BigInteger.ZERO)==0)
            current=B1.subtract(BigInteger.ONE);
        else
            current=B1;
        B = B2;
    }


    /**
     * Вернуть следующее простое число
     * @return
     */
    public BigInteger getNext(){
        if(current==null){
            current=BigInteger.ONE;//на следующей итерации зайдя в for будет равна 3
            return two;
        }
        boolean isPrime=true;
        for (current=current.add(two); current.compareTo(B)<0;current=current.add(two)){
            isPrime=true;
            for (int j=0;j<primes.length;j++)
            {
                BigInteger prime=BigInteger.valueOf(primes[j]);
                if (current.mod(prime).compareTo(BigInteger.ZERO)==0&&current.compareTo(prime)!=0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime)
                return current;
        }
        return null;
    }
//     не используется
//    /**
//     * Вернуть список простых чисел он [0,B]
//     * @param B
//     * @return
//     */
//    public static List<BigInteger> getList(BigInteger B){
//        List<BigInteger> t=new ArrayList<BigInteger>();
//        PrimeNumbers primeNumbers=new PrimeNumbers(B);
//        BigInteger p=primeNumbers.getNext();
//        while(p!=null){
//            t.add(p);
//            p=primeNumbers.getNext();
//        }
//        return t;
//    }



}