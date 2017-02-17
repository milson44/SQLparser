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

/**
 * Created with IntelliJ IDEA.
 * User: nail
 * Date: 25.12.12
 * Time: 7:16
 * To change this template use File | Settings | File Templates.
 */
public class NodForm extends JFrame {
    private JPanel contentPane;
    private JTextField tf_n;
    private JTextField tf_b;
    private JTextField tf_a;
    private JTextField tf_result;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NodForm frame = new NodForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void NodCalculate(){
        BigInteger n=new BigInteger(tf_n.getText().trim());
        BigInteger b=new BigInteger(tf_b.getText().trim());

        BigInteger res=Nod.euclid(n,b)[2];
        tf_result.setText(res.toString());
    }

    /**
     * Create the frame.
     */
    public NodForm() {
        setTitle("Hello, NodForm!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblN = new JLabel("a");
        lblN.setBounds(10, 11, 46, 14);
        contentPane.add(lblN);

        JLabel lblB = new JLabel("b");
        lblB.setBounds(10, 36, 46, 14);
        contentPane.add(lblB);



        tf_n = new JTextField();
        tf_n.setBounds(66, 8, 186, 20);
        contentPane.add(tf_n);
        tf_n.setColumns(10);

        tf_b = new JTextField();
        tf_b.setBounds(66, 33, 186, 20);
        contentPane.add(tf_b);
        tf_b.setColumns(10);



        JButton btnNewButton = new JButton("nod");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                tf_result.setText("");
                NodCalculate();
            }
        });
        btnNewButton.setBounds(162, 57, 89, 23);
        contentPane.add(btnNewButton);

        tf_result = new JTextField();
        tf_result.setBounds(10, 89, 241, 20);
        contentPane.add(tf_result);
        tf_result.setColumns(10);
    }

}