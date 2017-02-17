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
import java.util.Locale;


/**
 * Created with IntelliJ IDEA.
 * User: nail
 * Date: 25.12.12
 * Time: 4:55
 * To change this template use File | Settings | File Templates.
 */
public class MyClass extends JFrame {
    private JPanel contentPane;
    private JTextField tf_n;
    private JTextField tf_B;
    private JTextField tf_a;
    private JTextField tf_result;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyClass frame = new MyClass();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void startPollard(boolean run2Step) {
        BigInteger n = new BigInteger(tf_n.getText().trim());
        String chooseB = String.valueOf((int) Math.sqrt(Math.sqrt(n.doubleValue())));
        BigInteger B = new BigInteger(chooseB);
        tf_B.setText(chooseB);
        BigInteger a = new BigInteger(tf_a.getText());

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS", Locale.getDefault());
        long begin = System.currentTimeMillis();
        System.out.println(String.format("\nstarted %s for %s (B=%s,a=%s)",begin, n, B, a));
        Algoritms p = new Algoritms();

//        List<BigInteger> res = new ArrayList<BigInteger>();//p.factorizNumber(n,B,a);



//        String pnums="";
//        for (BigInteger r : res) {
//            pnums+=r+"; ";
//        }
        BigInteger res;
        if (run2Step) res = p.pm1pollard(n, B, a);
        else res = p.pm1pollard1Step(n, B, a);
        long end = System.currentTimeMillis();
        System.out.println(String.format("_finish %s res= %s", end, res));
        long busy=end - begin;
        System.out.println(String.format("time %s %s ",sdf.format(new java.util.Date(busy)),busy));
        tf_result.setText(res.toString());
    }

    /**
     * Create the frame.
     */
    public MyClass() {
        setTitle("Hello, Pollard!");
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

        JButton btnNewButton = new JButton("p-1(1Step)");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tf_result.setText("");
                startPollard(false);
            }
        });
        btnNewButton.setBounds(260, 57, 140, 23);
        contentPane.add(btnNewButton);

        JButton btn2NewButton = new JButton("p-1(1+2Step)");
        btn2NewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tf_result.setText("");
                startPollard(true);
            }
        });
        btn2NewButton.setBounds(260, 87, 140, 23);
        contentPane.add(btn2NewButton);

        tf_result = new JTextField();
        tf_result.setBounds(10, 89, 241, 20);
        contentPane.add(tf_result);
        tf_result.setColumns(10);
    }


}
