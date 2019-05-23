import javax.swing.*;

public class GUIForm extends JFrame {
    private JTextField textFieldForIpDst;
    private JButton buttonToSendUdpMessage;
    private JPanel rootPanel;
    private JTextField textFieldForPortDst;
    private JTextField textFieldForPortSrc;
    private JTextField textFieldForMessageUdp;
    private JTextField textFieldForIntervalSendingUDP;

    public GUIForm() {

        super("Auto test for tetra LIP");
        add(rootPanel);
        setTitle("Parser for messages");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
