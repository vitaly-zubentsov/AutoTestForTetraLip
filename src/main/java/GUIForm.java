import model.ShortLipMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;


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
  //  private JTextField textFieldForPduType;
    private JTextField textFieldForTimeElapsed;
    private JTextField textFieldForReasonForSending;
    private JButton buttonToPauseSendingLIPMessages;
    private JCheckBox checkBoxChangingSSI;
    private JCheckBox checkBoxChangingLongitude;
    private JCheckBox checkBoxChangingLatitude;
    private JCheckBox checkBoxChangingReasonForSending;
    private JRadioButton shortLIPRadioButton;
    private JRadioButton longLIP1RadioButton;
    private JRadioButton longLIPTelRadioButton;
    private JRadioButton longLIP2RadioButton;
    private JRadioButton longLIP3RadioButton;
    private JTextField textFieldForHorizontalVelocity;
    private JTextField textFieldForDirectionOfTravel;
    private JCheckBox checkBoxTimeElapsed;
    private JCheckBox checkBoxChangingPositionError;
    private JCheckBox checkBoxChangingHorizontalVelocity;
    private JCheckBox checkBoxChangingDirectionOfTravel;
    private JButton continueSendingLIPMessagesButton;
    private JTextField textFieldForPositionError;
    private UDPClient udpClientForLipMessages = new UDPClient();
    private boolean firstStart = true;
    private Map<Integer, Boolean> changeMap;
    ShortLipMessage shortLipMessage = new ShortLipMessage();

    GUIForm() {

        super("Auto test for tetra LIP");
        add(rootPanel);
        setTitle("Parser for messages");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonToStartSendUdpMessages.addActionListener(e -> {
            try {
                inputValidation();
                initChangeMap();
                shortLipMessage.withSSI(textFieldForSSI.getText())
                        .withTimeElapsed(textFieldForTimeElapsed.getText())
                        .withLongitude(textFieldForLongitude.getText())
                        .withLatitude(textFieldForLatitude.getText())
                        .withPositionError(textFieldForPositionError.getText())
                        .withHorizontalVelocity(textFieldForHorizontalVelocity.getText())
                        .withDirectionOfTravel(textFieldForDirectionOfTravel.getText())
                        .withReasonForSending(textFieldForReasonForSending.getText())
                        .withChangeMap(changeMap)
                        .initValuesFromUI();


                udpClientForLipMessages.initUDPConnection(
                        textFieldForIpDst.getText(),
                        textFieldForPortDst.getText(),
                        textFieldForPortSrc.getText(),
                        textFieldForIntervalSendingUDP.getText());
                udpClientForLipMessages.withShortLipMessage(shortLipMessage).startSendingMessageUdp();

                textFieldForIpDst.setEditable(false);
                textFieldForPortDst.setEditable(false);
                textFieldForIntervalSendingUDP.setEditable(false);
                textFieldForIpDst.setEditable(false);

                buttonToStartSendUdpMessages.setEnabled(false);
                continueSendingLIPMessagesButton.setEnabled(true);
                buttonToPauseSendingLIPMessages.setEnabled(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonToPauseSendingLIPMessages.addActionListener(e -> udpClientForLipMessages.stopSendingMessageUdp());

        continueSendingLIPMessagesButton.addActionListener(e -> {
            initChangeMap();
            try {
                inputValidation();

                shortLipMessage.withSSI(textFieldForSSI.getText())
                        .withTimeElapsed(textFieldForTimeElapsed.getText())
                        .withLongitude(textFieldForLongitude.getText())
                        .withLatitude(textFieldForLatitude.getText())
                        .withPositionError(textFieldForPositionError.getText())
                        .withHorizontalVelocity(textFieldForHorizontalVelocity.getText())
                        .withDirectionOfTravel(textFieldForDirectionOfTravel.getText())
                        .withReasonForSending(textFieldForReasonForSending.getText())
                        .withChangeMap(changeMap)
                        .initValuesFromUI();


                udpClientForLipMessages.withShortLipMessage(shortLipMessage).continueSendingUdpLipMessage();


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        shortLIPRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedOnlyForChoosingRadioButton(shortLIPRadioButton);

            }
        });
        longLIP1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedOnlyForChoosingRadioButton(longLIP1RadioButton);
            }
        });
        longLIP2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedOnlyForChoosingRadioButton(longLIP2RadioButton);
            }
        });
        longLIP3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedOnlyForChoosingRadioButton(longLIP3RadioButton);
            }
        });
        longLIPTelRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSelectedOnlyForChoosingRadioButton(longLIPTelRadioButton);
            }
        });

    }

    private void setSelectedOnlyForChoosingRadioButton(JRadioButton choosingRadioButton) {
        shortLIPRadioButton.setSelected(false);
        longLIP1RadioButton.setSelected(false);
        longLIP2RadioButton.setSelected(false);
        longLIP3RadioButton.setSelected(false);
        longLIPTelRadioButton.setSelected(false);
        choosingRadioButton.setSelected(true);
    }

    private void inputValidation() throws Exception {
        checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
        checkIntValueFromString(textFieldForTimeElapsed.getText(), 0, 3, "TimeElapsed");
        checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
        checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
        checkIntValueFromString(textFieldForPositionError.getText(), 0, 7, "PositionError");
        checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "HorizontalVelocity");
        checkIntValueFromString(textFieldForDirectionOfTravel.getText(), 0, 15, "DirectionOfTravel");
        checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "ReasonForSending");
    }

    private void initChangeMap() {
        this.changeMap = new HashMap<Integer, Boolean>();
        for (int i = 0; i < 8; i++) {
            changeMap.put(i, false);
        }
        if (checkBoxChangingSSI.isSelected()) {
            changeMap.replace(0, true);
        }
        if (checkBoxTimeElapsed.isSelected()) {
            changeMap.replace(1, true);
        }
        if (checkBoxChangingLongitude.isSelected()) {
            changeMap.replace(2, true);
        }
        if (checkBoxChangingLatitude.isSelected()) {
            changeMap.replace(3, true);
        }
        if (checkBoxChangingPositionError.isSelected()) {
            changeMap.replace(4, true);
        }
        if (checkBoxChangingHorizontalVelocity.isSelected()) {
            changeMap.replace(5, true);
        }
        if (checkBoxChangingDirectionOfTravel.isSelected()) {
            changeMap.replace(6, true);
        }
        if (checkBoxChangingReasonForSending.isSelected()) {
            changeMap.replace(7, true);
        }
    }

    private void checkIntValueFromString(String checkString, int beginRange, int endRange, String nameOfField) throws Exception {
        try {
            int checkNumber = Integer.parseInt(checkString);

            if ((checkNumber < beginRange) || (checkNumber > endRange)) {
                String warning = nameOfField + " = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]";
                JDialog dialog = createDialog(warning);
                dialog.setVisible(true);
                throw new Exception(warning);
            }
        } catch (Exception ex) {
            JDialog dialog = createDialog("Wrong type Of value for " + nameOfField);
            dialog.setVisible(true);
            throw new Exception(nameOfField + " should be int");
        }

    }

    private void checkDoubleValueFromString(String checkString, double beginRange, double endRange, String nameOfField) throws Exception {
        try {
            double checkNumber = Double.parseDouble(checkString);
            if ((checkNumber < beginRange) || (checkNumber > endRange)) {
                String warning = nameOfField + " = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]";
                JDialog dialog = createDialog(warning);
                dialog.setVisible(true);
                throw new Exception(checkNumber + "cannot be out of range [" + beginRange + ".." + endRange + "]");
            }
        } catch (Exception ex) {
            JDialog dialog = createDialog("Wrong type value for " + nameOfField);
            dialog.setVisible(true);
            throw new Exception(nameOfField + " should be double");
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
