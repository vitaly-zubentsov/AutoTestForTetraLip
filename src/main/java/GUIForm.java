import model.ShortLipMessage;

import javax.swing.*;


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

    public GUIForm() {

        super("Auto test for tetra LIP");
        add(rootPanel);
        setTitle("Parser for messages");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonToStartSendUdpMessages.addActionListener(e -> {
            ShortLipMessage shortLipMessage = new ShortLipMessage();
            shortLipMessage.withSSI(textFieldForSSI.getText())
                    .withPdu_type(textFieldForPduType.getText())
                    .withTime_elapsed(textFieldForTimeElapsed.getText())
                    .withLongitude(textFieldForLongitude.getText())
                    .withLatitude(textFieldForLatitude.getText())
                    .withReason_for_sending(textFieldForReasonForSending.getText());

            udpClientForLipMessages = new UDPClient();
            udpClientForLipMessages.withHost(textFieldForIpDst.getText()).
                    withPortDst(Integer.parseInt(textFieldForPortDst.getText())).
                    withIntervalForSendingMessageUdp(Integer.parseInt(textFieldForIntervalSendingUDP.getText())).
                    withPortSrc(Integer.parseInt(textFieldForPortSrc.getText())).
                    withShortLipMessage(shortLipMessage).
                    startSendingMessageUdp();
        });
        buttonToStopSendUdpMessages.addActionListener(e -> udpClientForLipMessages.stopSendingMessageUdp());
    }

}
