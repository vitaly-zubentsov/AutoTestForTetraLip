import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIForm extends JFrame {
    private JTextField textFieldForIpDst;
    private JButton buttonToStartSendUdpMessages;
    private JPanel rootPanel;
    private JTextField textFieldForPortDst;
    private JTextField textFieldForPortSrc;
    private JTextField textFieldForIntervalSendingUDP;
    private JTextField textFieldForSSI;
    private JTextField textFieldForLongitude;
    private JTextField textFieldForLatitude;
    private JTextField textFieldForPduType;
    private JTextField textFieldForTimeElapsed;
    private JTextField textFieldForReasonForSending;
    private JButton buttonToStopSendUdpMessages;
    private UDPClient udpClientForLipMessages;
    private UDPClient udpClientForAlivePackets = new UDPClient();
    private boolean firstStarForSending = true;

    public GUIForm() {

        super("Auto test for tetra LIP");
        add(rootPanel);
        setTitle("Parser for messages");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        buttonToStartSendUdpMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GeneratorUDPMessage generatorUDPMessage = new GeneratorUDPMessage();
                String messageForUdp = generatorUDPMessage.withSSI(textFieldForSSI.getText())
                        .withPdu_type(textFieldForPduType.getText())
                        .withTime_elapsed(textFieldForTimeElapsed.getText())
                        .withLongitude(textFieldForLongitude.getText())
                        .withLatitude(textFieldForLatitude.getText())
                        .withReason_for_sending(textFieldForReasonForSending.getText()).getUdpMessage();


                udpClientForLipMessages = new UDPClient();
                udpClientForLipMessages.withsetHost(textFieldForIpDst.getText()).
                        withPortDst(Integer.parseInt(textFieldForPortDst.getText())).
                        withMessageForUdp(messageForUdp).
                        withIntervalForSendingMessageUdp(Integer.parseInt(textFieldForIntervalSendingUDP.getText())).
                        withPortSrc(Integer.parseInt(textFieldForPortSrc.getText())).
                        startSendingMessageUdp();
            }
        });
        buttonToStopSendUdpMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                udpClientForLipMessages.stopSendingMessageUdp();
            }
        });
    }

}
