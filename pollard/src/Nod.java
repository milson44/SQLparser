/**
 * Created by milson on 29.11.16.
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nail
 * Date: 25.12.12
 * Time: 7:21
 * To change this template use File | Settings | File Templates.
 */
public class Nod {

    public static BigInteger[] euclid(BigInteger a, BigInteger b) {

        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger mAB=a.multiply(b),dAB=a.multiply(b),x=zero,y=one;
        BigInteger A=a;
        BigInteger B=b;
        BigInteger[] ret=new BigInteger[3];
        if ((A.compareTo(one)==0)||(B.mod(A).compareTo(zero)==0))
        {
            ret[0]=one;
            ret[1]=zero;
            ret[2]=A;
            return ret;
        }
        if ((B.compareTo(one)==0)||(A.mod(B).compareTo(zero)==0))
        {
            ret[0]=zero;
            ret[1]=one;
            ret[2]=B;
            return ret;
        }


        List<BigInteger> helper = new ArrayList();

        while (mAB.compareTo(zero)!=0)
        {
            mAB=a.mod(b);
            dAB=a.divide(b);
            a=b;
            b=mAB;
            helper.add(dAB);
        }

        int len=helper.size();

        for (int i=len-1;i>0;i--)
        {
            ret[0]=y;
            ret[1]=x.subtract(y.multiply(helper.get(i-1)));
            x=ret[0];
            y=ret[1];
        }
        ret[2]=ret[1].multiply(B).add(ret[0].multiply(A));

        return ret;
    }

    public static BigInteger nod(BigInteger a, BigInteger b){
        return euclid(a,b)[2];
    }
}