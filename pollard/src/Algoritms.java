/**
 * Created by milson on 29.11.16.
 */
import java.awt.EventQueue;
import java.io.Console;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Algoritms {
    /**
     * Первая стадия Получения делителя числа n методом (p-1) полларда
     *
     * @param n факторизуемое число
     * @param B - граница B
     * @param a любое число: 1<=a<=p, где p - простой делитель факторизуемого числа n
     * @return делитель числа n
     */
    public BigInteger pm1pollard1Step(BigInteger n, BigInteger B, BigInteger a) {
        Debug.println("step 1",System.currentTimeMillis()+"");
        // определяем элементы множества P(простые числа меньше границы B)
        //Вычисляем M(B) (произведение простых чисел+их степени)
        BigInteger MB = P.getPrimeMultWithMod(B, n);
        BigInteger aMBmodn = FastOperations.power(a, MB, n); //вычисляем aM(В) mod n
        BigInteger divider = Nod.nod(n, aMBmodn.subtract(BigInteger.ONE)); //Находим НОД(n,aM-1) если повезет он даст искомый делитель
        return divider;
    }

    /**
     * Получение делителя числа n методом (p-1) полларда - 2 стадия
     * @param B1
     * @param b          число a^M(B1) mod n, вычисленное на первой стадии работы алгоритма
     * @param n          факторизуемое число
     * @return делитель числа n
     */
    public BigInteger pm1pollard2step(BigInteger B1, BigInteger b, BigInteger n) {
        Debug.println("step 2",System.currentTimeMillis()+"");
        BigInteger B2 = B1.multiply(B1);//B2=B1*B1;
        PrimeNumbers primeNumbers = new PrimeNumbers(B1, B2); //менеджер псевдопростых простых чисел на интервале   B1,B2
        BigInteger qi = primeNumbers.getNext();
        if(qi==null)
            Debug.println("Выбрана слишком маленькая граница B2","");
        BigInteger ci = FastOperations.power(b, qi, n);
        BigInteger d = Nod.nod(n, ci.subtract(BigInteger.ONE));
        if (d.compareTo(BigInteger.ONE) != 0) return d;
        else {
            BigInteger ciplus1;
            BigInteger deltai;
            for (BigInteger qiplus1 = primeNumbers.getNext(); qiplus1 != null; qiplus1 = primeNumbers.getNext()) { //начинаем перебирать все простые числа
                deltai = qiplus1.subtract(qi);
                BigInteger b_delta_i_mod_n = FastOperations.power(b, deltai, n);
                ciplus1 = ci.multiply(b_delta_i_mod_n);
                d = Nod.nod(n, ci.subtract(BigInteger.ONE));
                if (d.compareTo(BigInteger.ONE) != 0) return d;
                //обновляем значения аккумулирующих переменных для след итерации
                ci = ciplus1;
                qi = qiplus1;
            }
            return BigInteger.ZERO;
        }
    }

    /**
     * Получение делителя числа n методом (p-1) полларда - 1+2 стадия
     * @param n факторизуемое число
     * @param B - граница B
     * @param a любое число: 1<=a<=p, где p - простой делитель факторизуемого числа n
     * @return
     */
    public BigInteger pm1pollard(BigInteger n, BigInteger B,BigInteger a){
        Debug.println("step 1",System.currentTimeMillis()+"");
        // определяем элементы множества P(простые числа меньше границы B)
        //Вычисляем M(B) (произведение простых чисел+их степени)
        BigInteger MB = P.getPrimeMultWithMod(B, n);
        BigInteger aMBmodn = FastOperations.power(a, MB, n); //вычисляем aM(В) mod n   (на второй стадии это b)
        BigInteger divider = Nod.nod(n, aMBmodn.subtract(BigInteger.ONE)); //Находим НОД(n,aM-1) если повезет он даст искомый делитель

        if ( (divider.compareTo(BigInteger.ONE)==0)||(divider.compareTo(n)==0))//Если не повезло на первом этапе, выполняем вторую стадию
        {

            divider = pm1pollard2step(B, aMBmodn, n);	//выполняем вторую стадию
            return divider; //возвращаем результат работы второй стадии
        }
        return divider;
    }

    private void printList(String message, List list) {
        String t = "";
        int size = Math.min(list.size(), 100);
        for (int i = 0; i < size; i++) {
            t += list.get(i) + " ";

        }
        System.out.println(String.format("%s size=%s(%s)", message, list.size(), t));
    }
}