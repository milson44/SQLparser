/**
 * Created by milson on 29.11.16.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nail
 * Date: 25.12.12
 * Time: 7:44
 * To change this template use File | Settings | File Templates.
 */
public class OldPollard  extends JFrame    {
    private JPanel contentPane;
    private JTextField tf_n;
    private JTextField tf_B;
    private JTextField tf_a;
    private JTextField tf_result;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OldPollard frame = new OldPollard();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startPollard(){
        BigInteger n=new BigInteger(tf_n.getText().trim());
        String chooseB = String.valueOf((int)Math.sqrt(Math.sqrt(n.doubleValue())));
        BigInteger B=new BigInteger(chooseB);
        tf_B.setText(chooseB);
        BigInteger a=new BigInteger(tf_a.getText());


        //List<BigInteger> list = security.sieveOfEratosthenes(b);
        String out = p1pollard_withSecondPart(a,n,B).toString();
        //List<BigInteger> list = security.factorizeNum(n,b,a);
        //String out = "";
        //for (int i = 0; i<list.size();i++)
        //{
        //    out+=list.get(i).toString()+"\n";
        //}
        //jTextPane1.setText(out);
        //polChart.pack();
        //RefineryUtilities.centerFrameOnScreen(polChart);
        //polChart.setVisible(true);

        tf_result.setText(out);
    }

    /**
     * Create the frame.
     */
    public OldPollard() {
        setTitle("Hello, OldPollard!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblN = new JLabel("n");
        lblN.setBounds(10, 11, 46, 14);
        contentPane.add(lblN);

        JLabel lblB = new JLabel("B");
        lblB.setBounds(10, 36, 46, 14);
        contentPane.add(lblB);

        JLabel lblA = new JLabel("a");
        lblA.setBounds(10, 61, 46, 14);
        contentPane.add(lblA);

        tf_n = new JTextField();
        tf_n.setBounds(66, 8, 186, 20);
        contentPane.add(tf_n);
        tf_n.setColumns(10);

        tf_B = new JTextField();
        tf_B.setBounds(66, 33, 86, 20);
        contentPane.add(tf_B);
        tf_B.setColumns(10);

        tf_a = new JTextField();
        tf_a.setBounds(66, 58, 86, 20);
        tf_a.setText("2");
        contentPane.add(tf_a);
        tf_a.setColumns(10);

        JButton btnNewButton = new JButton("p-1");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tf_result.setText("");
                startPollard();
            }
        });
        btnNewButton.setBounds(162, 57, 89, 23);
        contentPane.add(btnNewButton);

        tf_result = new JTextField();
        tf_result.setBounds(10, 89, 241, 20);
        contentPane.add(tf_result);
        tf_result.setColumns(10);
    }

    public int[]  euclid(int a, int b) {

        int mAB=a*b,dAB=a*b,x=0,y=1;
        int A=a;
        int B=b;
        int[] ret=new int[3];
        if ((A==1)||(B%A==0))
        {
            ret[0]=1;
            ret[1]=0;
            ret[2]=A;
            return ret;
        }
        if ((B==1)||(A%B==0))
        {
            ret[0]=0;
            ret[1]=1;
            ret[2]=B;
            return ret;
        }


        List<Integer> helper = new ArrayList();

        while (mAB!=0)
        {

            mAB=a%b;
            dAB=a/b;
            a=b;
            b=mAB;
            helper.add(dAB);
        }

        int len=helper.size();

        for (int i=len-1;i>0;i--)
        {
            ret[0]=y;
            ret[1]=x-y*helper.get(i-1);
            x=ret[0];
            y=ret[1];
        }
        ret[2]=ret[1]*B+ret[0]*A;

        return ret;
    }

    public int power(int a,int e,int n) {
        int c=0,l=0;
        List<Integer> ebin=new ArrayList();
        if ((a<0)||(e<0)||(n<0)) return 0;
        while(e!=0)
        {
            ebin.add(e%2);
            if (e%2!=0) e-=1;
            e/=2;
        }

        l=ebin.size();
        List<Integer> cpower=new ArrayList<Integer>();
        Long s=0L;
        cpower.add(a);
        for (int i=l-2;i>=0;i--)
        {
            if(ebin.get(i)==1)
            {
                s=Long.valueOf(cpower.get(l-i-2));
                s*=s;
                s%=n;
                s*=a;
                s%=n;
                cpower.add(s.intValue());
            }
            else
            {
                s=Long.valueOf(cpower.get(l-i-2));
                s*=s;
                s%=n;
                cpower.add(s.intValue());
            }
        }
        c=cpower.get(l-1);
        return c;
    }

    boolean prime(int k,int ran)
    {
        Random rand=new Random();
        int r=0;
        int r1=0;
        int s=0,t=0;
        int r1mod2=0;
        int allA=0;
        boolean isPrime=false;
        //while (!isPrime)
        //{
        r=ran;
        r1=r-1;
        s=0;
        t=r1;
        r1mod2=0;
        allA=0;
        while (r1mod2==0)
        {
            r1mod2=t%2;
            if(r1mod2==0)
            {
                s++;
                t/=2;
            }
        }

           /* int rm=retMult(t);
            while (rm!=1)
            {
                t/=rm;
                rm=retMult(t);
            }
            if(t>100)
            {*/
        boolean flag=false;
                /*for(int i=3;i<=java.lang.Math.sqrt(t);i++)
                {
                    if (t%i==0) flag=true;
                }*/
        if (!flag)
        {
            int a=0;
            while (allA<k)
            {
                a=2+rand.nextInt(r1);
                if (r%a!=0)
                {
                    int b=power(a,t,r);
                    if(b!=1)
                    {
                        int si=0;
                        while((si<=(s-1))&&(b!=(r-1)))
                        {
                            b=(b*b)%r;
                            si++;
                        }
                        if (b==(r-1)) allA++;
                        else allA=k+100;
                    }
                    else allA++;
                }
                else allA=k+100;
            }
            if (allA!=k+100) return false;
        }
        // }

        return true;
    }

    public int prime(int k) {
        Random rand=new Random();
        int r=0;
        int r1=0;
        int s=0,t=0;
        int r1mod2=0;
        int allA=0;
        boolean isPrime=false;
        while (!isPrime)
        {
            r=1000+rand.nextInt(1000);
            r1=r-1;
            s=0;
            t=r1;
            r1mod2=0;
            allA=0;
            while (r1mod2==0)
            {
                r1mod2=t%2;
                if(r1mod2==0)
                {
                    s++;
                    t/=2;
                }
            }

           /* int rm=retMult(t);
            while (rm!=1)
            {
                t/=rm;
                rm=retMult(t);
            }
            if(t>100)
            {*/
            boolean flag=false;
                /*for(int i=3;i<=java.lang.Math.sqrt(t);i++)
                {
                    if (t%i==0) flag=true;
                }*/
            if (!flag)
            {
                int a=0;
                while (allA<k)
                {
                    a=2+rand.nextInt(r1);
                    if (r%a!=0)
                    {
                        int b=power(a,t,r);
                        if(b!=1)
                        {
                            int si=0;
                            while((si<=(s-1))&&(b!=(r-1)))
                            {
                                b=(b*b)%r;
                                si++;
                            }
                            if (b==(r-1)) allA++;
                            else allA=k+100;
                        }
                        else allA++;
                    }
                    else allA=k+100;
                }
                if (allA!=k+100) isPrime=true;
            }
            // }
        }
        return r;
    }


    public List <BigInteger> factorizeNum(BigInteger n, BigInteger b, BigInteger a)
    {
        List <BigInteger> ret = new ArrayList<BigInteger>();
        BigInteger helper = p1pollard_withSecondPart(a,n,b);
        BigInteger part = n.divide(helper);
        ret.add(helper);
        while ((part.compareTo(BigInteger.ONE)!=0)&&(helper.compareTo(BigInteger.ONE)!=0))
        {
            helper = p1pollard_withSecondPart(a,part,chooseB(part));
            if (helper.compareTo(BigInteger.ONE)==0) ret.add(part);
            else ret.add(helper);
            part = part.divide(helper);
        }
        return ret;
    }

    private BigInteger chooseB(BigInteger n)
    {
        int i = n.toString().length();
        if (i==1) return new BigInteger("3");
        else if (i<3) return new BigInteger("6");
        else if (i<6) return new BigInteger("8");
        else if (i<8) return new BigInteger("10");
        else if (i<25) return new BigInteger("30");
        else if (i<96) return new BigInteger((i+5)+"");
        else return new BigInteger("100");
    }

    public BigInteger p1pollard(BigInteger a,BigInteger n, BigInteger b)
    {
        BigInteger mb = getPrimeMult(b);
        BigInteger modn = power(a,mb,n);
        BigInteger ret = euclid(n,modn.subtract(new BigInteger("1")))[2];
        return ret;
    }

    public BigInteger p1pollard_withSecondPart(BigInteger a,BigInteger n, BigInteger b)
    {
        List <BigInteger> erList = sieveOfEratosthenes(b);
        BigInteger mb = getPrimeMult(erList, b);
        BigInteger modn = power(a,mb,n);
        BigInteger ret = euclid(n,modn.subtract(BigInteger.ONE))[2];
        if ( (ret.compareTo(BigInteger.ONE)==0)||(ret.compareTo(n)==0))
        {
            BigInteger B2 = b.multiply(b).subtract(BigInteger.ONE);
            ret = p2pollard(erList, B2, modn, n);
            if ( (ret.compareTo(BigInteger.ONE)!=0)&&(ret.compareTo(n)!=0)) return ret;
            //B2 = b.multiply(b);
            return ret;
        }
        return ret;
    }



    public BigInteger p2pollard(BigInteger B,BigInteger B2, BigInteger b, BigInteger n)
    {
        List <BigInteger> q = sieveOfEratosthenesInterval(B, B2);
        List <BigInteger> bb = generateSubtractList(q);
        List <BigInteger> bbmodn = generateModNOfSubtractList(bb,b,n);
        List <BigInteger> c = new ArrayList<BigInteger>();
        c.add(power(b,q.get(0),n));
        BigInteger nod = euclid(n,c.get(0).subtract(BigInteger.ONE))[2];
        if (nod.compareTo(BigInteger.ONE)==0)
        {
            for (int i = 1; (i<q.size())&&(nod.compareTo(BigInteger.ONE)!=0);i++)
            {
                c.add(i, c.get(i-1).multiply(bbmodn.get(i)));
                nod = euclid(n,c.get(i).subtract(BigInteger.ONE))[2];
            }
            if (nod.compareTo(BigInteger.ONE)==0)
                return BigInteger.ONE;
            else
                return nod;
        }
        else
            return nod;
    }

    public BigInteger p2pollard(List <BigInteger> B,BigInteger B2, BigInteger b, BigInteger n)
    {
        List <BigInteger> q = sieveOfEratosthenesInterval(B, B2);
        List <BigInteger> bb = generateSubtractList(q);
        List <BigInteger> bbmodn = generateModNOfSubtractList(bb,b,n);
        List <BigInteger> c = new ArrayList<BigInteger>();
        c.add(power(b,q.get(0),n));
        BigInteger nod = euclid(n,c.get(0).subtract(BigInteger.ONE))[2];
        if ((nod.compareTo(BigInteger.ONE)==0)||(nod.compareTo(n))==0)
        {
            for (int i = 1; (i<bbmodn.size())&&((nod.compareTo(BigInteger.ONE)==0)||(nod.compareTo(n)==0));i++)
            {
                c.add(i, c.get(i-1).multiply(bbmodn.get(i-1)));
                nod = euclid(n,c.get(i).subtract(BigInteger.ONE))[2];
            }
            if (nod.compareTo(BigInteger.ONE)==0)
                return BigInteger.ONE;
            else
                return nod;
        }
        else
            return nod;
    }

    public BigInteger[] euclid(BigInteger a, BigInteger b) {

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

    public BigInteger power(BigInteger a,BigInteger e,BigInteger n) {
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
    }

    public BigInteger getPrimeMult(BigInteger B)
    {
        BigInteger ret = new BigInteger("1");
        List <BigInteger> a = sieveOfEratosthenes(B);
        for (int i = 0; i<a.size();i++)
        {
            BigInteger temp = a.get(i);
            while (a.get(i).multiply(temp).compareTo(B)<0)
                temp=a.get(i).multiply(temp);
            ret=ret.multiply(temp);
        }
        return ret;
    }

    public BigInteger getPrimeMult(List <BigInteger> a, BigInteger B)
    {
        BigInteger ret = new BigInteger("1");
        for (int i = 0; i<a.size();i++)
        {
            BigInteger temp = a.get(i);
            while (a.get(i).multiply(temp).compareTo(B)<0)
                temp=a.get(i).multiply(temp);
            ret=ret.multiply(temp);
        }
        return ret;
    }

    public List generateModNOfSubtractList(List <BigInteger> B, BigInteger b, BigInteger n)
    {
        List <BigInteger> ret = new ArrayList<BigInteger>();
        for(int i = 0;i<B.size();i++)
        {
            BigInteger temp = B.get(i);
            ret.add(power(b,temp, n));
        }
        return ret;
    }

    public List generateSubtractList(List <BigInteger> B)
    {
        List <BigInteger> t = new ArrayList<BigInteger>();
        for (int i = 1; i<B.size();i++)
        {
            t.add(B.get(i).subtract(B.get(i-1)));
        }
        return t;
    }

    public List sieveOfEratosthenesInterval(BigInteger B, BigInteger B2)
    {
        List <BigInteger> d = sieveOfEratosthenes(B);
        List <BigInteger> a = sieveOfEratosthenes(B2);
        a.removeAll(d);
        return a;
    }

    public List sieveOfEratosthenesInterval(List <BigInteger> B, BigInteger B2)
    {
        List <BigInteger> a = sieveOfEratosthenes(B2);
        a.removeAll(B);
        return a;
    }

    public List sieveOfEratosthenes(BigInteger B)
    {
        List <BigInteger> a = new ArrayList<BigInteger>();
        if (B.compareTo(new BigInteger("2"))==-1) return a;
        else if (B.compareTo(new BigInteger("2"))==0)
        {
            a.add(new BigInteger("2"));
            return a;
        }
        else
        {
            BigInteger zero = new BigInteger("0");
            BigInteger one = new BigInteger("1");
            BigInteger two = new BigInteger("2");
            a.add(new BigInteger(String.valueOf(2)));
            for (BigInteger i=new BigInteger("3");i.compareTo(B)<0;i=i.add(two))
            {
                a.add(i);
            }
            int i=0;
            while(i!=a.size())
            {
                BigInteger curVal = a.get(i);
                for (int j=i+1;j<a.size();j++)
                {
                    if (a.get(j).mod(curVal).compareTo(zero)==0) a.remove(j);
                }
                i++;
            }
            return a;
        }
    }


    public int retAccepted(int k)

    {
        int t=prime(k)-1;
        int rm=retMult(t);
        boolean flag =true;
        while (flag){
            while (rm!=1)
            {
                t/=rm;
                rm=retMult(t);
            }
            t=prime(k)-1;
            if (t>100) flag=false;
        }
        t++;
        return t;
    }
    public List encryption(String input) {
        List<Integer> codes=new ArrayList<Integer>();
        int k = input.length();
        for (int i=0;i<k;i++)
        {
            codes.add((int)input.charAt(i));
        }

        int p=0,q=0;

        p=this.retAccepted(100);
        q=this.retAccepted(100);

        int n=p*q;
        int fin=(q-1)*(p-1);
        Random rand=new Random();

        int e=this.retAccepted(100);
        if (e>n)
            e=rand.nextInt(n);

        while((fin%e)==0)
        {
            e=this.retAccepted(100);
            if (e>n)
                e=rand.nextInt(n);
        }

        int[] x=this.euclid(fin, e);
        if(x[1]<0) x[1]+=fin;
        int d=x[1];

        List<Integer> rsa=new ArrayList<Integer>();

        for (int i=0;i<k;i++)
        {
            rsa.add(this.power(codes.get(i),e,n));
        }
        String output="";
        for (int i=0;i<k;i++)
        {
            if(!output.isEmpty()) output+=", ";
            output+=rsa.get(i);
        }
        List out=new ArrayList();
        out.add(output);
        out.add(e);
        out.add(n);
        out.add(d);
        return out;
    }

    public String decryption(int n,int d,String input,boolean flag) {

        String[] codes=input.split(", ");
        int k=codes.length;
        int buf=0;
        int encrypted=0;
        String output="";
        int pluscode=0;
        if (flag) pluscode=848;
        for (int i=0;i<k;i++)
        {
            buf=Integer.decode(codes[i]);
            encrypted=power(buf,d,n);
            output+= (char)(encrypted+pluscode);
        }


        return output;
    }


    public int crack(int n, int e)
    {
        if ((n==1)&&(e==1)) return 1;
        int x1 = 1;
        int x = 2;
        int i = 1;
        int y = x1;
        if (isPowerOfTwo(i)) {
            x1 = x;
        }

        int abs = Math.abs(x - y);
        int[] nod=euclid(n, abs);
        if (n>1){
            while (nod[2] == 1) {
                x = (x * x - 1) % n;
                y = x1;
                i++;
                if (isPowerOfTwo(i)) {
                    x1 = x;
                }
                abs = Math.abs(x - y);
                nod=euclid(n,abs);
            }

            int p=nod[2];
            int q=n/p;

            int pq=(q-1)*(p-1);

            nod=euclid(pq,e);

            if (nod[1]<0) nod[1]+=pq;

            return nod[1];
        }
        return 1;
    }

    private static boolean isPowerOfTwo(int k) {
        boolean ret = true;

        while ((k % 2 == 0)&&(k>0)) {
            k/= 2;
        }
        k/=2;

        if (k >= 1) ret = false;

        return ret;
    }

    int retMult(int n)
    {
        int x1 = 1;
        int x = 2;
        int i = 1;
        int y = x1;
        if (isPowerOfTwo(i)) {
            x1 = x;
        }
        if (prime(10,n)) return 1;
        int abs = Math.abs(x - y);
        int[] nod=euclid(n, abs);

        while ((nod[2] == 1)) {
            x = (x * x - 1) % n;
            y = x1;
            i++;
            if (isPowerOfTwo(i)) {
                x1 = x;
            }
            abs = Math.abs(x - y);
            nod=euclid(n,abs);
        }

        return nod[2];
    }

}