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
            try {

                inputValidation(textFieldForSSI.getText(), 0, 16777215);
                inputValidation(textFieldForPduType.getText(), 0, 3);
                inputValidation(textFieldForTimeElapsed.getText(), 0, 3);
                inputValidation(textFieldForLongitude.getText(), -180.0, 179.0);
                inputValidation(textFieldForLatitude.getText(), -90.0, 89.0);

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

            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });
        buttonToStopSendUdpMessages.addActionListener(e -> udpClientForLipMessages.stopSendingMessageUdp());
    }

    private void inputValidation(String checkString, int beginRange, int endRange) throws Exception {
        int checkNumber = Integer.parseInt(checkString);
        if ((checkNumber < beginRange) || (checkNumber > endRange)) {
            String warning = "Value : '" + checkNumber + "' cannot be out of range [" + beginRange + ".." + endRange + "]";
            JDialog dialog = createDialog("Модальное", true, warning);
            dialog.setVisible(true);

            throw new Exception(checkNumber + "cannot be out of range [" + beginRange + ".." + endRange + "]");
        }
    }

    private void inputValidation(String checkString, double beginRange, double endRange) throws Exception {
        double checkNumber = Double.parseDouble(checkString);
        if ((checkNumber < beginRange) || (checkNumber > endRange)) {
            throw new Exception("Number = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]");
        }
    }

    private JDialog createDialog(String title, boolean modal, String messageInDialog) {
        JDialog dialog = new JDialog(this, title, modal);
        JLabel dialogLabel = new JLabel(messageInDialog);
        dialog.add(dialogLabel);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 90);
        return dialog;
    }

}
