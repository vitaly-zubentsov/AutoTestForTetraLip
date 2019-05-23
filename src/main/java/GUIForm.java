import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        buttonToSendUdpMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipDst = textFieldForIpDst.getText();
                int portDst = Integer.parseInt(textFieldForPortDst.getText());
                int portSrc = Integer.parseInt(textFieldForPortSrc.getText());
                String messageForUdp = textFieldForMessageUdp.getText();
                int intervalForSendingMessageUdp = Integer.parseInt(textFieldForIntervalSendingUDP.getText());
                UDPClient udpClient = new UDPClient();
                udpClient.withsetHost(ipDst).withPortDst(portDst).withMessageForUdp(messageForUdp).withIntervalForSendingMessageUdp(intervalForSendingMessageUdp).startSendingMessageUdp();
            }
        });
    }

}
