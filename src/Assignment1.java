
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.swing.*;

public class Assignment1 extends JPanel {

    public static void main (String[] args) {
        JFrame frame = new JFrame ("Assignment1 -- Darragh Meehan");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Assignment1());
        frame.pack();
        frame.setVisible (true);
    }

    private JButton loadBtn;
    private JButton reverseBtn;
    private JButton revWordBtn;
    private JButton countBtn;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JButton clearBtn;
    private String stringReverse;
    String file = new String();

    public Assignment1(){

        loadBtn = new JButton ("Load");
        loadBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    java.io.File filePath = fileChooser.getSelectedFile();

                    file = filePath.getAbsolutePath();

                    try {
                        BufferedReader br = new BufferedReader(new FileReader(filePath));
                        String read = br.readLine();
                        while (read != null) {
                            textArea1.append(read + "\n");
                            read = br.readLine();
                        }
                        br.close();
                    } catch (IOException ex) {

                    }
                }
            }
        });

        reverseBtn = new JButton ("Reverse");
        reverseBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String read = br.readLine();
                    while (read != null) {

                        String[] s1 = read.split(" ");

                        for(int x=s1.length-1; x>=0; x--)
                        {
                            textArea2.append(s1[x] + " ");
                            {

                            }
                        }
                        textArea2.append("\n");
                        read = br.readLine();
                    }
                    br.close();
                } catch (IOException ex) {

                }
            }
        });

        revWordBtn = new JButton ("Rev. Word");
        revWordBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    StringBuffer text = new StringBuffer();

                    String line = br.readLine();
                    while (line != null) {

                        String[] i = line.split(" ");
                        int x;
                        for (x = i.length - 1; x >= 0; x--) {
                            textArea3.append(i[x] + " ");
                        }
                        textArea3.setText(text.toString());
                        textArea3.append("\n");
                    }
                    stringReverse = textArea2.getText();

                } catch (IOException ex) {

                }
            }
        });

        countBtn = new JButton ("Count");
        countBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String read = br.readLine();
                    Map map = new HashMap();
                    if (read != null) {
                        String[] separatedWords = read.split(" ");

                        for (String str : separatedWords) {

                            if (map.containsKey(str)) {
                                int count = Integer.parseInt(String.valueOf(map.get(str)));
                                map.put(str, String.valueOf(count + 1));

                            }
                            else {
                                map.put(str, "1");
                            }
                        }
                    }
                    textArea4.append(map + "\n");
                    br.close();
                }
                catch (IOException ex) {

                }
            }
        });

        clearBtn = new JButton ("Clear");
        clearBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textArea1.setText("");
                textArea2.setText("");
                textArea3.setText("");
                textArea4.setText("");
            }
        });

        textArea1 = new JTextArea (5, 5);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea2 = new JTextArea (5, 5);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea3 = new JTextArea (5, 5);
        textArea3.setLineWrap(true);
        textArea3.setWrapStyleWord(true);
        textArea4 = new JTextArea (5, 5);
        textArea4.setLineWrap(true);
        textArea4.setWrapStyleWord(true);

        //adjust size and set layout
        setPreferredSize (new Dimension (731, 900));
        setLayout (null);

        //add components
        add (loadBtn);
        add (reverseBtn);
        add (revWordBtn);
        add (countBtn);
        add (textArea1);
        add (textArea2);
        add (textArea3);
        add (textArea4);
        add (clearBtn);

        //set component bounds (only needed by Absolute Positioning)
        loadBtn.setBounds (0, 0, 100, 20);
        reverseBtn.setBounds (185, 0, 100, 20);
        revWordBtn.setBounds (360, 0, 110, 20);
        countBtn.setBounds (545, 0, 110, 20);
        textArea1.setBounds (0, 20, 175, 800);
        textArea2.setBounds (185, 20, 160, 800);
        textArea3.setBounds (360, 20, 170, 800);
        textArea4.setBounds (545, 20, 155, 800);
        clearBtn.setBounds (280, 850, 100, 20);
    }
}