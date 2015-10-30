
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment1_Swing extends JPanel {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Assignment1 using SwingWorker -- Darragh Meehan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Assignment1_Swing());
        frame.pack();
        frame.setVisible(true);
    }

    private JButton loadBtn;
    private JButton reverseBtn;
    private JButton revWordBtn;
    private JButton countBtn;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private String stringReverse;
    private String file;

    public Assignment1_Swing() {
        //construct components
        loadBtn = new JButton("Load");
        reverseBtn = new JButton("Reverse");
        revWordBtn = new JButton("Reverse Word");
        countBtn = new JButton("Count");
        textArea1 = new JTextArea(5, 5);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea2 = new JTextArea(5, 5);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea3 = new JTextArea(5, 5);
        textArea3.setLineWrap(true);
        textArea3.setWrapStyleWord(true);
        textArea4 = new JTextArea(5, 5);
        textArea4.setLineWrap(true);
        textArea4.setWrapStyleWord(true);

        //adjust size and set layout
        setPreferredSize(new Dimension(731, 900));
        setLayout(null);

        //add components
        add(loadBtn);
        add(reverseBtn);
        add(revWordBtn);
        add(countBtn);
        add(textArea1);
        add(textArea2);
        add(textArea3);
        add(textArea4);

        //set component bounds (only needed by Absolute Positioning)
        loadBtn.setBounds(0, 0, 100, 20);
        reverseBtn.setBounds(185, 0, 100, 20);
        revWordBtn.setBounds(360, 0, 110, 20);
        countBtn.setBounds(545, 0, 110, 20);
        textArea1.setBounds(0, 20, 175, 800);
        textArea2.setBounds(185, 20, 160, 800);
        textArea3.setBounds(360, 20, 170, 800);
        textArea4.setBounds(545, 20, 155, 800);

        class loadText extends SwingWorker<Integer, String> {
            @Override
            protected Integer doInBackground() throws Exception {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                    try {
                        FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile());
                        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                        StringBuffer text = new StringBuffer();

                        file = fileChooser.getSelectedFile().getAbsolutePath();
                        System.out.print(file);

                        String read;
                        while ((read = br.readLine()) != null) {
                            text.append(read).append("\n");
                        }
                        textArea1.setText(text.toString());
                    } catch (IOException ex) {

                    }
                }
                return null;
            }

            protected void process(List<String> word) {
                for (int x = 0; x < word.size(); x++) {
                    textArea1.append(word.get(x));
                }
            }
        }

        class reverseText extends SwingWorker<Integer, String> {
            @Override
            protected Integer doInBackground() throws Exception {
                try {
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    StringBuffer text = new StringBuffer();

                    String read = br.readLine();
                    while ((read = br.readLine()) != null) {

                        String[] i = read.split(" ");
                        int x;
                        for (x = i.length - 1; x >= 0; x--) {
                            text.append(i[x] + " ");
                        }
                        textArea2.setText(text.toString());
                        textArea2.append("\n");
                    }
                    stringReverse = textArea2.getText();

                } catch (IOException ex) {

                }
                return null;
            }

            protected void process(List<String> word) {
                for (int x = 0; x < word.size(); x++) {
                    textArea2.append(word.get(x));
                }
            }
        }

        class reverseWords extends SwingWorker<Integer, String> {
            @Override
            protected Integer doInBackground() throws Exception {
                textArea3.setText("");
                String StringRead;
                StringRead = stringReverse;
                String[] line = StringRead.split("\n");
                for (String lines : line) {
                    String[] word = lines.split(" ");
                    if (word.length % 2 == 0) {
                        for (int i = 0; i < word.length; i = i + 2) {
                            publish(word[i + 1] + " ");
                            publish(word[i] + " ");
                        }
                        String aa = " \n";
                        publish(aa);
                    } else {
                        for (int i = 0; i < word.length - 1; i = i + 2) {
                            publish(word[i + 1] + " ");
                            publish(word[i] + " ");
                        }
                        String bb = word[word.length - 1] + " \n";
                        publish(bb);
                    }
                    String cc = " \n";
                    publish(cc);
                }
                return null;
            }

            protected void process(List<String> word) {
                for (int x = 0; x < word.size(); x++) {
                    textArea3.append(word.get(x));
                }
            }
        }

        class countText extends SwingWorker<Integer, String> {
            @Override
            protected Integer doInBackground() throws Exception {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String read = br.readLine();
                    Map mapA = new HashMap();
                    if (read != null) {
                        String[] separatedWords = read.split(" ");
                        for (String str : separatedWords) {
                            if (mapA.containsKey(str)) {
                                int count = Integer.parseInt(String.valueOf(mapA.get(str)));
                                mapA.put(str, String.valueOf(count + 1));
                            } else {
                                mapA.put(str, "1");
                            }
                        }
                    }
                    textArea4.append(mapA + "\n");
                    br.close();
                } catch (IOException ex) {
                }
                return null;
            }

            protected void process(List<String> word) {
                for (int x = 0; x < word.size(); x++) {
                    textArea2.append(word.get(x));
                }
            }
        }

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loadText().execute();
            }
        });
        reverseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new reverseText().execute();
            }
        });
        revWordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new reverseWords().execute();
            }
        });
        countBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new countText().execute();
            }
        });
    }
}