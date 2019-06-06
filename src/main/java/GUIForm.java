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
    private UDPClient udpClientForLipMessages = new UDPClient();
    private boolean firstStart = true;
    ShortLipMessage shortLipMessage = new ShortLipMessage();

    GUIForm() {

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

                if (firstStart) {
                    firstStart = false;
                    shortLipMessage.withSSI(textFieldForSSI.getText())
                            .withPdu_type(textFieldForPduType.getText())
                            .withTime_elapsed(textFieldForTimeElapsed.getText())
                            .withLongitude(textFieldForLongitude.getText())
                            .withLatitude(textFieldForLatitude.getText())
                            .withReason_for_sending(textFieldForReasonForSending.getText()).initValuesFromUI();

                    textFieldForIpDst.setEditable(false);
                    textFieldForPortDst.setEditable(false);
                    textFieldForIntervalSendingUDP.setEditable(false);
                    textFieldForIpDst.setEditable(false);
                    textFieldForLatitude.setEditable(false);
                    textFieldForLongitude.setEditable(false);

                    udpClientForLipMessages.initUDPConnection(
                            textFieldForIpDst.getText(),
                            textFieldForPortDst.getText(),
                            textFieldForPortSrc.getText(),
                            textFieldForIntervalSendingUDP.getText());
                    udpClientForLipMessages.withShortLipMessage(shortLipMessage).startSendingMessageUdp();
                } else {
                    shortLipMessage.withSSI(textFieldForSSI.getText())
                            .withPdu_type(textFieldForPduType.getText())
                            .withTime_elapsed(textFieldForTimeElapsed.getText())
                            .withReason_for_sending(textFieldForReasonForSending.getText());

                    udpClientForLipMessages.withShortLipMessage(shortLipMessage).continueSendingUdpLipMessage();
                }

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
            JDialog dialog = createDialog(warning);
            dialog.setVisible(true);

            throw new Exception(checkNumber + "cannot be out of range [" + beginRange + ".." + endRange + "]");
        }
    }

    private void inputValidation(String checkString, double beginRange, double endRange) throws Exception {
        double checkNumber = Double.parseDouble(checkString);
        if ((checkNumber < beginRange) || (checkNumber > endRange)) {
            String warning = "Value : '" + checkNumber + "' cannot be out of range [" + beginRange + ".." + endRange + "]";
            JDialog dialog = createDialog(warning);
            dialog.setVisible(true);

            throw new Exception(checkNumber + "cannot be out of range [" + beginRange + ".." + endRange + "]");
        }
    }

    private JDialog createDialog(String messageInDialog) {
        JDialog dialog = new JDialog(this, "Ошибка", true);
        JLabel dialogLabel = new JLabel(messageInDialog);
        dialog.add(dialogLabel);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 90);
        return dialog;
    }

}
