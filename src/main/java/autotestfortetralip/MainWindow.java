package autotestfortetralip;

import javax.swing.*;

public class MainWindow extends JFrame {
    private JTextField ipDst;
    private JTextField intervalForPacket;
    private JTextField portSrc;
    private JTextField portDst;
    private JTextField textForPacket;
    private JButton startSendPacket;
    private JPanel rootPanel;

    public MainWindow() {

        add(rootPanel);

        setTitle("Parser for messages");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
