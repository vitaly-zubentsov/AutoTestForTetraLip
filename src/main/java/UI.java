
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UI extends JFrame {
    private static final long serialVersionUID = 1L;

    private Map<Integer, Boolean> changeMap;
    List<UDPMessage> listOfUDPMessages = new ArrayList<UDPMessage>();
    UDPClient udpClient = new UDPClient();
    AliveMessage aliveMessage = new AliveMessage();
    JPanel contents = new JPanel(new VerticalLayout());


    public UI() {

        super("DialogWindows");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        listOfUDPMessages.add(aliveMessage);

        JButton buttonSetUDPOptions = new JButton("Set UDP options");
        JButton buttonAddShortLip = new JButton("Add short LIP");
        buttonAddShortLip.setEnabled(false);
        JButton buttonAddLongLipType1 = new JButton("Add long LIP type 1");
        buttonAddLongLipType1.setEnabled(false);
        JButton buttonAddLongLipType2 = new JButton("Add long LIP type 2");
        buttonAddLongLipType2.setEnabled(false);
        JButton buttonAddLongLipType3 = new JButton("Add long LIP type 3");
        buttonAddLongLipType3.setEnabled(false);
        JButton buttonAddTelemetryData = new JButton("Add LIP with telemetry data");
        buttonAddTelemetryData.setEnabled(false);

        JTextArea jTextAreaForUsersInputDATA = new JTextArea();
        Dimension s = new Dimension(200, 250);
        jTextAreaForUsersInputDATA.setPreferredSize(s);
        jTextAreaForUsersInputDATA.setText("This field show \n input data for UDP messages \n \n \n \n \n \n \n");

        JButton buttonStartSendingUDPMessage = new JButton("Start sending UDP message");
        buttonStartSendingUDPMessage.setEnabled(false);
        JButton buttonStopSendingUPDMessage = new JButton("Stop sending UDP message");
        buttonStopSendingUPDMessage.setEnabled(false);
        JButton buttonContinueSendingUPDMessage = new JButton("Continue sending UDP message");
        buttonContinueSendingUPDMessage.setEnabled(false);


        // Создание панели содержимого с размещением кнопок

        contents.add(buttonSetUDPOptions);
        contents.add(buttonAddShortLip);
        contents.add(buttonAddLongLipType1);
        contents.add(buttonAddLongLipType2);
        contents.add(buttonAddLongLipType3);
        contents.add(buttonAddTelemetryData);

        contents.add(jTextAreaForUsersInputDATA);

        contents.add(buttonStartSendingUDPMessage);
        contents.add(buttonStopSendingUPDMessage);
        contents.add(buttonContinueSendingUPDMessage);
        setContentPane(contents);
        // Определение размера и открытие окна
        setSize(250, 600);
        setLocationRelativeTo(null);


        buttonSetUDPOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForUDPOptions = createDialog("Input options for UDP messages", true, 400, 200, 5, 2);

                JLabel labelForIpDestination = new JLabel("Input destination IP");
                JTextField textFieldForIpDestination = new JTextField("192.168.1.82", 1);
                dialogForUDPOptions.add(labelForIpDestination);
                dialogForUDPOptions.add(textFieldForIpDestination);

                JLabel labelForPortDestination = new JLabel("Input destination port");
                JTextField textFieldForPortDestination = new JTextField("11372", 1);
                dialogForUDPOptions.add(labelForPortDestination);
                dialogForUDPOptions.add(textFieldForPortDestination);

                JLabel labelForPortSource = new JLabel("Input source port");
                JTextField textFieldForPortSource = new JTextField("9999", 1);
                dialogForUDPOptions.add(labelForPortSource);
                dialogForUDPOptions.add(textFieldForPortSource);

                JLabel labelForIntervalOfTimeSendingUDP = new JLabel("Input interval sending UDP, msec");
                JTextField textFieldForIntervalOfTimeSendingUDP = new JTextField("1000", 1);
                dialogForUDPOptions.add(labelForIntervalOfTimeSendingUDP);
                dialogForUDPOptions.add(textFieldForIntervalOfTimeSendingUDP);


                JButton buttonToSetUDPOptions = new JButton("Set UDP options");
                dialogForUDPOptions.add(buttonToSetUDPOptions);

                buttonToSetUDPOptions.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        udpClient.initUDPConnection(
                                textFieldForIpDestination.getText(),
                                textFieldForPortDestination.getText(),
                                textFieldForPortSource.getText(),
                                textFieldForIntervalOfTimeSendingUDP.getText());
                        dialogForUDPOptions.dispose();

                        jTextAreaForUsersInputDATA.setText(
                                "Ip dst   = " + textFieldForIpDestination.getText() + "\n" +
                                        "Port dst = " + textFieldForPortDestination.getText() + "\n" +
                                        "Port src = " + textFieldForPortSource.getText() + "\n" +
                                        "Interval = " + textFieldForIntervalOfTimeSendingUDP.getText() + "\n" +
                                        "UDP messages : \n" +
                                        "Alive message, int = 25000 ms"
                        );
                        buttonSetUDPOptions.setEnabled(false);
                        buttonAddShortLip.setEnabled(true);
                        buttonAddLongLipType1.setEnabled(true);
                        buttonAddLongLipType2.setEnabled(true);
                        buttonAddLongLipType3.setEnabled(true);
                        buttonAddTelemetryData.setEnabled(true);
                        buttonStartSendingUDPMessage.setEnabled(true);

                    }
                });

                dialogForUDPOptions.setVisible(true);
            }
        });


        buttonAddShortLip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForShortLIP = createDialog("Input data for short LIP", true, 600, 350, 11, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForSSI);
                dialogForShortLIP.add(textFieldForSSI);
                dialogForShortLIP.add(checkBoxForSSI);

                JLabel labelForPDUType = new JLabel("PDU Type");
                JTextField textFieldFoForPDUType = new JTextField("0", 1);
                textFieldFoForPDUType.setEditable(false);
                JCheckBox checkBoxForForPDUType = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForShortLIP.add(labelForPDUType);
                dialogForShortLIP.add(textFieldFoForPDUType);
                dialogForShortLIP.add(checkBoxForForPDUType);

                JLabel labelForTimeElapsed = new JLabel("Time elapsed");
                JTextField textFieldForTimeElapsed = new JTextField("0", 1);
                JCheckBox checkBoxForTimeElapsed = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForTimeElapsed);
                dialogForShortLIP.add(textFieldForTimeElapsed);
                dialogForShortLIP.add(checkBoxForTimeElapsed);

                JLabel labelForLongitude = new JLabel("Longitude");
                JTextField textFieldForLongitude = new JTextField("37.66000628471", 1);
                JCheckBox checkBoxForLongitude = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForLongitude);
                dialogForShortLIP.add(textFieldForLongitude);
                dialogForShortLIP.add(checkBoxForLongitude);

                JLabel labelForLatitude = new JLabel("Latitude");
                JTextField textFieldForLatitude = new JTextField("55.7839286", 1);
                JCheckBox checkBoxForLatitude = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForLatitude);
                dialogForShortLIP.add(textFieldForLatitude);
                dialogForShortLIP.add(checkBoxForLatitude);

                JLabel labelForPositionError = new JLabel("Position error");
                JTextField textFieldForPositionError = new JTextField("0", 1);
                JCheckBox checkBoxForPositionError = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForPositionError);
                dialogForShortLIP.add(textFieldForPositionError);
                dialogForShortLIP.add(checkBoxForPositionError);

                JLabel labelForHorizontalVelocity = new JLabel("Horizontal velocity");
                JTextField textFieldForHorizontalVelocity = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalVelocity = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForHorizontalVelocity);
                dialogForShortLIP.add(textFieldForHorizontalVelocity);
                dialogForShortLIP.add(checkBoxForHorizontalVelocity);

                JLabel labelForDirectionOfTravel = new JLabel("DirectionOfTravel");
                JTextField textFieldForDirectionOfTravel = new JTextField("0", 1);
                JCheckBox checkBoxForDirectionOfTravel = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForDirectionOfTravel);
                dialogForShortLIP.add(textFieldForDirectionOfTravel);
                dialogForShortLIP.add(checkBoxForDirectionOfTravel);

                JLabel labelForTypeOfAdditionalData = new JLabel("Type of additional data");
                JTextField textFieldForTypeOfAdditionalData = new JTextField("0", 1);
                textFieldForTypeOfAdditionalData.setEditable(false);
                JCheckBox checkBoxForTypeOfAdditionalData = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForShortLIP.add(labelForTypeOfAdditionalData);
                dialogForShortLIP.add(textFieldForTypeOfAdditionalData);
                dialogForShortLIP.add(checkBoxForTypeOfAdditionalData);

                JLabel labelForReasonForSending = new JLabel("Reason for sending");
                JTextField textFieldForReasonForSending = new JTextField("129", 1);
                JCheckBox checkBoxForReasonForSending = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForReasonForSending);
                dialogForShortLIP.add(textFieldForReasonForSending);
                dialogForShortLIP.add(checkBoxForReasonForSending);

                JButton buttonToAddShortLip = new JButton("Add ShortLIP");
                dialogForShortLIP.add(buttonToAddShortLip);

                buttonToAddShortLip.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        try {
                            checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
                            checkIntValueFromString(textFieldForTimeElapsed.getText(), 0, 3, "TimeElapsed");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForPositionError.getText(), 0, 7, "PositionError");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "HorizontalVelocity");
                            checkIntValueFromString(textFieldForDirectionOfTravel.getText(), 0, 15, "DirectionOfTravel");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "ReasonForSending");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        Map<Integer, Boolean> changeMap = new HashMap<Integer, Boolean>();
                        for (int i = 0; i < 8; i++) {
                            changeMap.put(i, false);
                        }
                        if (checkBoxForSSI.isSelected()) {
                            changeMap.replace(0, true);
                        }
                        if (checkBoxForTimeElapsed.isSelected()) {
                            changeMap.replace(1, true);
                        }
                        if (checkBoxForLongitude.isSelected()) {
                            changeMap.replace(2, true);
                        }
                        if (checkBoxForLatitude.isSelected()) {
                            changeMap.replace(3, true);
                        }
                        if (checkBoxForPositionError.isSelected()) {
                            changeMap.replace(4, true);
                        }
                        if (checkBoxForHorizontalVelocity.isSelected()) {
                            changeMap.replace(5, true);
                        }
                        if (checkBoxForDirectionOfTravel.isSelected()) {
                            changeMap.replace(6, true);
                        }
                        if (checkBoxForReasonForSending.isSelected()) {
                            changeMap.replace(7, true);
                        }

                        ShortLipMessage shortLipMessage = new ShortLipMessage();
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

                        listOfUDPMessages.add(shortLipMessage);
                        dialogForShortLIP.dispose();


                        jTextAreaForUsersInputDATA.setText(jTextAreaForUsersInputDATA.getText() + "\n Short Lip ");


                    }


                });

                dialogForShortLIP.setVisible(true);
            }
        });
        buttonAddLongLipType1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPType1 = createDialog("Input data for long LIP type 1", true, 600, 500, 16, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForSSI);
                dialogForLongLIPType1.add(textFieldForSSI);
                dialogForLongLIPType1.add(checkBoxForSSI);

                JLabel labelForPDUType = new JLabel("PDU Type");
                JTextField textFieldFoForPDUType = new JTextField("1", 1);
                textFieldFoForPDUType.setEditable(false);
                JCheckBox checkBoxForForPDUType = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPType1.add(labelForPDUType);
                dialogForLongLIPType1.add(textFieldFoForPDUType);
                dialogForLongLIPType1.add(checkBoxForForPDUType);

                JLabel labelForPDUTypeExtension = new JLabel("PDU Type extension");
                JTextField textFieldForPDUTypeExtension = new JTextField("3", 1);
                textFieldForPDUTypeExtension.setEditable(false);
                JCheckBox checkBoxForPDUTypeExtension = new JCheckBox("Changing");
                checkBoxForPDUTypeExtension.setEnabled(false);
                dialogForLongLIPType1.add(labelForPDUTypeExtension);
                dialogForLongLIPType1.add(textFieldForPDUTypeExtension);
                dialogForLongLIPType1.add(checkBoxForPDUTypeExtension);

                JLabel labelForTimeData = new JLabel("Time data");
                JTextField textFieldFoTimeData = new JTextField("0", 1);
                textFieldFoTimeData.setEditable(false);
                JCheckBox checkBoxForForTimeData = new JCheckBox("Changing");
                checkBoxForForTimeData.setEnabled(false);
                dialogForLongLIPType1.add(labelForTimeData);
                dialogForLongLIPType1.add(textFieldFoTimeData);
                dialogForLongLIPType1.add(checkBoxForForTimeData);

                JLabel labelForLocationShape = new JLabel("Location shape");
                JTextField textFieldForLocationShape = new JTextField("5", 1);
                textFieldForLocationShape.setEditable(false);
                JCheckBox checkBoxForLocationShape = new JCheckBox("Changing");
                checkBoxForLocationShape.setEnabled(false);
                dialogForLongLIPType1.add(labelForLocationShape);
                dialogForLongLIPType1.add(textFieldForLocationShape);
                dialogForLongLIPType1.add(checkBoxForLocationShape);

                JLabel labelForLongitude = new JLabel("Longitude");
                JTextField textFieldForLongitude = new JTextField("37.66000628471", 1);
                JCheckBox checkBoxForLongitude = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForLongitude);
                dialogForLongLIPType1.add(textFieldForLongitude);
                dialogForLongLIPType1.add(checkBoxForLongitude);

                JLabel labelForLatitude = new JLabel("Latitude");
                JTextField textFieldForLatitude = new JTextField("55.7839286", 1);
                JCheckBox checkBoxForLatitude = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForLatitude);
                dialogForLongLIPType1.add(textFieldForLatitude);
                dialogForLongLIPType1.add(checkBoxForLatitude);

                JLabel labelForHorizontalPositionUncertainty = new JLabel("Horizontal position uncertainty");
                JTextField textFieldForHorizontalPositionUncertainty = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalPositionUncertainty = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForHorizontalPositionUncertainty);
                dialogForLongLIPType1.add(textFieldForHorizontalPositionUncertainty);
                dialogForLongLIPType1.add(checkBoxForHorizontalPositionUncertainty);

                JLabel labelForLocationAltitude = new JLabel("Location altitude");
                JTextField textFieldForLocationAltitude = new JTextField("0", 1);
                JCheckBox checkBoxForLocationAltitude = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForLocationAltitude);
                dialogForLongLIPType1.add(textFieldForLocationAltitude);
                dialogForLongLIPType1.add(checkBoxForLocationAltitude);

                JLabel labelForVelocityType = new JLabel("Velocity type");
                JTextField textFieldForVelocityType = new JTextField("5", 1);
                textFieldForVelocityType.setEditable(false);
                JCheckBox checkBoxForVelocityType = new JCheckBox("Changing");
                checkBoxForVelocityType.setEnabled(false);
                dialogForLongLIPType1.add(labelForVelocityType);
                dialogForLongLIPType1.add(textFieldForVelocityType);
                dialogForLongLIPType1.add(checkBoxForVelocityType);

                JLabel labelForHorizontalVelocity = new JLabel("Horizontal velocity");
                JTextField textFieldForHorizontalVelocity = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalVelocity = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForHorizontalVelocity);
                dialogForLongLIPType1.add(textFieldForHorizontalVelocity);
                dialogForLongLIPType1.add(checkBoxForHorizontalVelocity);

                JLabel labelForDirectionOfTravelExtended = new JLabel("Direction of travel extended");
                JTextField textFieldForDirectionOfTravelExtended = new JTextField("0", 1);
                JCheckBox checkBoxForDirectionOfTravelExtended = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForDirectionOfTravelExtended);
                dialogForLongLIPType1.add(textFieldForDirectionOfTravelExtended);
                dialogForLongLIPType1.add(checkBoxForDirectionOfTravelExtended);

                JLabel labelForAcknowledgementRequest = new JLabel("Acknowledgement request");
                JTextField textFieldForAcknowledgementRequest = new JTextField("0", 1);
                textFieldForAcknowledgementRequest.setEditable(false);
                JCheckBox checkBoxForForAcknowledgementRequest = new JCheckBox("Changing");
                checkBoxForForAcknowledgementRequest.setEnabled(false);
                dialogForLongLIPType1.add(labelForAcknowledgementRequest);
                dialogForLongLIPType1.add(textFieldForAcknowledgementRequest);
                dialogForLongLIPType1.add(checkBoxForForAcknowledgementRequest);

                JLabel labelForTypeOfAdditionalData = new JLabel("Type of additional data");
                JTextField textFieldForTypeOfAdditionalData = new JTextField("0", 1);
                textFieldForTypeOfAdditionalData.setEditable(false);
                JCheckBox checkBoxForTypeOfAdditionalData = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPType1.add(labelForTypeOfAdditionalData);
                dialogForLongLIPType1.add(textFieldForTypeOfAdditionalData);
                dialogForLongLIPType1.add(checkBoxForTypeOfAdditionalData);

                JLabel labelForReasonForSending = new JLabel("Reason for sending");
                JTextField textFieldForReasonForSending = new JTextField("129", 1);
                JCheckBox checkBoxForReasonForSending = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForReasonForSending);
                dialogForLongLIPType1.add(textFieldForReasonForSending);
                dialogForLongLIPType1.add(checkBoxForReasonForSending);

                JButton buttonToAddLongLipType1 = new JButton("Add long LIP type 1");
                dialogForLongLIPType1.add(buttonToAddLongLipType1);

                buttonToAddLongLipType1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63, "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255, "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for sending");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        Map<Integer, Boolean> changeMap = new HashMap<Integer, Boolean>();
                        for (int i = 0; i < 8; i++) {
                            changeMap.put(i, false);
                        }
                        if (checkBoxForSSI.isSelected()) {
                            changeMap.replace(0, true);
                        }

                        if (checkBoxForLongitude.isSelected()) {
                            changeMap.replace(1, true);
                        }
                        if (checkBoxForLatitude.isSelected()) {
                            changeMap.replace(2, true);
                        }
                        if (checkBoxForHorizontalPositionUncertainty.isSelected()) {
                            changeMap.replace(3, true);
                        }
                        if (checkBoxForLocationAltitude.isSelected()) {
                            changeMap.replace(4, true);
                        }
                        if (checkBoxForHorizontalVelocity.isSelected()) {
                            changeMap.replace(5, true);
                        }
                        if (checkBoxForDirectionOfTravelExtended.isSelected()) {
                            changeMap.replace(6, true);
                        }
                        if (checkBoxForReasonForSending.isSelected()) {
                            changeMap.replace(7, true);
                        }

                        LongLipType1Message longLipType1Message = new LongLipType1Message();
                        longLipType1Message.withSSI(textFieldForSSI.getText())
                                .withLongitude(textFieldForLongitude.getText())
                                .withLatitude(textFieldForLatitude.getText())
                                .withHorizontal_position_uncertainty(textFieldForHorizontalPositionUncertainty.getText())
                                .withLocation_altitude(textFieldForLocationAltitude.getText())
                                .withHorizontal_velocity(textFieldForHorizontalVelocity.getText())
                                .withDirection_of_travel_extended(textFieldForDirectionOfTravelExtended.getText())
                                .withReason_for_sending(textFieldForReasonForSending.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType1Message);

                        dialogForLongLIPType1.dispose();
                        jTextAreaForUsersInputDATA.setText(jTextAreaForUsersInputDATA.getText() + "\n Long Lip type 1 ");
                    }
                });

                dialogForLongLIPType1.setVisible(true);
            }
        });
        buttonAddLongLipType2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPType2 = createDialog("Input data for long LIP type 2", true, 600, 500, 17, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForSSI);
                dialogForLongLIPType2.add(textFieldForSSI);
                dialogForLongLIPType2.add(checkBoxForSSI);

                JLabel labelForPDUType = new JLabel("PDU Type");
                JTextField textFieldFoForPDUType = new JTextField("1", 1);
                textFieldFoForPDUType.setEditable(false);
                JCheckBox checkBoxForForPDUType = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPType2.add(labelForPDUType);
                dialogForLongLIPType2.add(textFieldFoForPDUType);
                dialogForLongLIPType2.add(checkBoxForForPDUType);

                JLabel labelForPDUTypeExtension = new JLabel("PDU Type extension");
                JTextField textFieldForPDUTypeExtension = new JTextField("3", 1);
                textFieldForPDUTypeExtension.setEditable(false);
                JCheckBox checkBoxForPDUTypeExtension = new JCheckBox("Changing");
                checkBoxForPDUTypeExtension.setEnabled(false);
                dialogForLongLIPType2.add(labelForPDUTypeExtension);
                dialogForLongLIPType2.add(textFieldForPDUTypeExtension);
                dialogForLongLIPType2.add(checkBoxForPDUTypeExtension);

                JLabel labelForTimeData = new JLabel("Time data");
                JTextField textFieldFoTimeData = new JTextField("1", 1);
                textFieldFoTimeData.setEditable(false);
                JCheckBox checkBoxForForTimeData = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPType2.add(labelForTimeData);
                dialogForLongLIPType2.add(textFieldFoTimeData);
                dialogForLongLIPType2.add(checkBoxForForTimeData);

                JLabel labelForTimeElapsed = new JLabel("Time elapsed");
                JTextField textFieldForTimeElapsed = new JTextField("0", 1);
                JCheckBox checkBoxForTimeElapsed = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForTimeElapsed);
                dialogForLongLIPType2.add(textFieldForTimeElapsed);
                dialogForLongLIPType2.add(checkBoxForTimeElapsed);

                JLabel labelForLocationShape = new JLabel("Location shape");
                JTextField textFieldForLocationShape = new JTextField("5", 1);
                textFieldForLocationShape.setEditable(false);
                JCheckBox checkBoxForLocationShape = new JCheckBox("Changing");
                checkBoxForLocationShape.setEnabled(false);
                dialogForLongLIPType2.add(labelForLocationShape);
                dialogForLongLIPType2.add(textFieldForLocationShape);
                dialogForLongLIPType2.add(checkBoxForLocationShape);

                JLabel labelForLongitude = new JLabel("Longitude");
                JTextField textFieldForLongitude = new JTextField("37.66000628471", 1);
                JCheckBox checkBoxForLongitude = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForLongitude);
                dialogForLongLIPType2.add(textFieldForLongitude);
                dialogForLongLIPType2.add(checkBoxForLongitude);

                JLabel labelForLatitude = new JLabel("Latitude");
                JTextField textFieldForLatitude = new JTextField("55.7839286", 1);
                JCheckBox checkBoxForLatitude = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForLatitude);
                dialogForLongLIPType2.add(textFieldForLatitude);
                dialogForLongLIPType2.add(checkBoxForLatitude);

                JLabel labelForHorizontalPositionUncertainty = new JLabel("Horizontal position uncertainty");
                JTextField textFieldForHorizontalPositionUncertainty = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalPositionUncertainty = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForHorizontalPositionUncertainty);
                dialogForLongLIPType2.add(textFieldForHorizontalPositionUncertainty);
                dialogForLongLIPType2.add(checkBoxForHorizontalPositionUncertainty);

                JLabel labelForLocationAltitude = new JLabel("Location altitude");
                JTextField textFieldForLocationAltitude = new JTextField("0", 1);
                JCheckBox checkBoxForLocationAltitude = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForLocationAltitude);
                dialogForLongLIPType2.add(textFieldForLocationAltitude);
                dialogForLongLIPType2.add(checkBoxForLocationAltitude);

                JLabel labelForVelocityType = new JLabel("Velocity type");
                JTextField textFieldForVelocityType = new JTextField("5", 1);
                textFieldForVelocityType.setEditable(false);
                JCheckBox checkBoxForVelocityType = new JCheckBox("Changing");
                checkBoxForVelocityType.setEnabled(false);
                dialogForLongLIPType2.add(labelForVelocityType);
                dialogForLongLIPType2.add(textFieldForVelocityType);
                dialogForLongLIPType2.add(checkBoxForVelocityType);

                JLabel labelForHorizontalVelocity = new JLabel("Horizontal velocity");
                JTextField textFieldForHorizontalVelocity = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalVelocity = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForHorizontalVelocity);
                dialogForLongLIPType2.add(textFieldForHorizontalVelocity);
                dialogForLongLIPType2.add(checkBoxForHorizontalVelocity);

                JLabel labelForDirectionOfTravelExtended = new JLabel("Direction of travel extended");
                JTextField textFieldForDirectionOfTravelExtended = new JTextField("0", 1);
                JCheckBox checkBoxForDirectionOfTravelExtended = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForDirectionOfTravelExtended);
                dialogForLongLIPType2.add(textFieldForDirectionOfTravelExtended);
                dialogForLongLIPType2.add(checkBoxForDirectionOfTravelExtended);

                JLabel labelForAcknowledgementRequest = new JLabel("Acknowledgement request");
                JTextField textFieldForAcknowledgementRequest = new JTextField("0", 1);
                textFieldForAcknowledgementRequest.setEditable(false);
                JCheckBox checkBoxForForAcknowledgementRequest = new JCheckBox("Changing");
                checkBoxForForAcknowledgementRequest.setEnabled(false);
                dialogForLongLIPType2.add(labelForAcknowledgementRequest);
                dialogForLongLIPType2.add(textFieldForAcknowledgementRequest);
                dialogForLongLIPType2.add(checkBoxForForAcknowledgementRequest);

                JLabel labelForTypeOfAdditionalData = new JLabel("Type of additional data");
                JTextField textFieldForTypeOfAdditionalData = new JTextField("0", 1);
                textFieldForTypeOfAdditionalData.setEditable(false);
                JCheckBox checkBoxForTypeOfAdditionalData = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPType2.add(labelForTypeOfAdditionalData);
                dialogForLongLIPType2.add(textFieldForTypeOfAdditionalData);
                dialogForLongLIPType2.add(checkBoxForTypeOfAdditionalData);

                JLabel labelForReasonForSending = new JLabel("Reason for sending");
                JTextField textFieldForReasonForSending = new JTextField("129", 1);
                JCheckBox checkBoxForReasonForSending = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForReasonForSending);
                dialogForLongLIPType2.add(textFieldForReasonForSending);
                dialogForLongLIPType2.add(checkBoxForReasonForSending);

                JButton buttonToAddLongLipType2 = new JButton("Add long LIP type 2");
                dialogForLongLIPType2.add(buttonToAddLongLipType2);

                buttonToAddLongLipType2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
                            checkIntValueFromString(textFieldForTimeElapsed.getText(),0,3,"Time elapsed");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63, "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255, "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for sending");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        Map<Integer, Boolean> changeMap = new HashMap<Integer, Boolean>();
                        for (int i = 0; i < 9; i++) {
                            changeMap.put(i, false);
                        }
                        if (checkBoxForSSI.isSelected()) {
                            changeMap.replace(0, true);
                        }
                        if (checkBoxForTimeElapsed.isSelected()) {
                            changeMap.replace(1, true);
                        }
                        if (checkBoxForLongitude.isSelected()) {
                            changeMap.replace(2, true);
                        }
                        if (checkBoxForLatitude.isSelected()) {
                            changeMap.replace(3, true);
                        }
                        if (checkBoxForHorizontalPositionUncertainty.isSelected()) {
                            changeMap.replace(4, true);
                        }
                        if (checkBoxForLocationAltitude.isSelected()) {
                            changeMap.replace(5, true);
                        }
                        if (checkBoxForHorizontalVelocity.isSelected()) {
                            changeMap.replace(6, true);
                        }
                        if (checkBoxForDirectionOfTravelExtended.isSelected()) {
                            changeMap.replace(7, true);
                        }
                        if (checkBoxForReasonForSending.isSelected()) {
                            changeMap.replace(8, true);
                        }

                        LongLipType2Message longLipType2Message = new LongLipType2Message();
                        longLipType2Message.withSSI(textFieldForSSI.getText())
                                .withTimeElapsed(textFieldForTimeElapsed.getText())
                                .withLongitude(textFieldForLongitude.getText())
                                .withLatitude(textFieldForLatitude.getText())
                                .withHorizontal_position_uncertainty(textFieldForHorizontalPositionUncertainty.getText())
                                .withLocation_altitude(textFieldForLocationAltitude.getText())
                                .withHorizontal_velocity(textFieldForHorizontalVelocity.getText())
                                .withDirection_of_travel_extended(textFieldForDirectionOfTravelExtended.getText())
                                .withReason_for_sending(textFieldForReasonForSending.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType2Message);

                        dialogForLongLIPType2.dispose();

                        jTextAreaForUsersInputDATA.setText(jTextAreaForUsersInputDATA.getText() + "\n Long Lip type 2");
                    }
                });

                dialogForLongLIPType2.setVisible(true);
            }
        });
        buttonAddLongLipType3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPType3 = createDialog("Input data for long LIP type 3", true, 600, 500, 17, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForSSI);
                dialogForLongLIPType3.add(textFieldForSSI);
                dialogForLongLIPType3.add(checkBoxForSSI);

                JLabel labelForPDUType = new JLabel("PDU Type");
                JTextField textFieldFoForPDUType = new JTextField("1", 1);
                textFieldFoForPDUType.setEditable(false);
                JCheckBox checkBoxForForPDUType = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPType3.add(labelForPDUType);
                dialogForLongLIPType3.add(textFieldFoForPDUType);
                dialogForLongLIPType3.add(checkBoxForForPDUType);

                JLabel labelForPDUTypeExtension = new JLabel("PDU Type extension");
                JTextField textFieldForPDUTypeExtension = new JTextField("3", 1);
                textFieldForPDUTypeExtension.setEditable(false);
                JCheckBox checkBoxForPDUTypeExtension = new JCheckBox("Changing");
                checkBoxForPDUTypeExtension.setEnabled(false);
                dialogForLongLIPType3.add(labelForPDUTypeExtension);
                dialogForLongLIPType3.add(textFieldForPDUTypeExtension);
                dialogForLongLIPType3.add(checkBoxForPDUTypeExtension);

                JLabel labelForTimeData = new JLabel("Time data");
                JTextField textFieldFoTimeData = new JTextField("2", 1);
                textFieldFoTimeData.setEditable(false);
                JCheckBox checkBoxForForTimeData = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPType3.add(labelForTimeData);
                dialogForLongLIPType3.add(textFieldFoTimeData);
                dialogForLongLIPType3.add(checkBoxForForTimeData);


                JLabel labelForTimeOfPosition = new JLabel("Time of position");
                JTextField textFieldForTimeOfPosition = new JTextField("1 day, 23 hours, 59 min, 0 sec", 1);
                textFieldForTimeOfPosition.setEditable(false);
                JCheckBox checkBoxForTimeOfPosition = new JCheckBox("Changing");
                checkBoxForTimeOfPosition.setEnabled(false);
                dialogForLongLIPType3.add(labelForTimeOfPosition);
                dialogForLongLIPType3.add(textFieldForTimeOfPosition);
                dialogForLongLIPType3.add(checkBoxForTimeOfPosition);

                JLabel labelForLocationShape = new JLabel("Location shape");
                JTextField textFieldForLocationShape = new JTextField("5", 1);
                textFieldForLocationShape.setEditable(false);
                JCheckBox checkBoxForLocationShape = new JCheckBox("Changing");
                checkBoxForLocationShape.setEnabled(false);
                dialogForLongLIPType3.add(labelForLocationShape);
                dialogForLongLIPType3.add(textFieldForLocationShape);
                dialogForLongLIPType3.add(checkBoxForLocationShape);

                JLabel labelForLongitude = new JLabel("Longitude");
                JTextField textFieldForLongitude = new JTextField("37.66000628471", 1);
                JCheckBox checkBoxForLongitude = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForLongitude);
                dialogForLongLIPType3.add(textFieldForLongitude);
                dialogForLongLIPType3.add(checkBoxForLongitude);

                JLabel labelForLatitude = new JLabel("Latitude");
                JTextField textFieldForLatitude = new JTextField("55.7839286", 1);
                JCheckBox checkBoxForLatitude = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForLatitude);
                dialogForLongLIPType3.add(textFieldForLatitude);
                dialogForLongLIPType3.add(checkBoxForLatitude);

                JLabel labelForHorizontalPositionUncertainty = new JLabel("Horizontal position uncertainty");
                JTextField textFieldForHorizontalPositionUncertainty = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalPositionUncertainty = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForHorizontalPositionUncertainty);
                dialogForLongLIPType3.add(textFieldForHorizontalPositionUncertainty);
                dialogForLongLIPType3.add(checkBoxForHorizontalPositionUncertainty);

                JLabel labelForLocationAltitude = new JLabel("Location altitude");
                JTextField textFieldForLocationAltitude = new JTextField("0", 1);
                JCheckBox checkBoxForLocationAltitude = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForLocationAltitude);
                dialogForLongLIPType3.add(textFieldForLocationAltitude);
                dialogForLongLIPType3.add(checkBoxForLocationAltitude);

                JLabel labelForVelocityType = new JLabel("Velocity type");
                JTextField textFieldForVelocityType = new JTextField("5", 1);
                textFieldForVelocityType.setEditable(false);
                JCheckBox checkBoxForVelocityType = new JCheckBox("Changing");
                checkBoxForVelocityType.setEnabled(false);
                dialogForLongLIPType3.add(labelForVelocityType);
                dialogForLongLIPType3.add(textFieldForVelocityType);
                dialogForLongLIPType3.add(checkBoxForVelocityType);

                JLabel labelForHorizontalVelocity = new JLabel("Horizontal velocity");
                JTextField textFieldForHorizontalVelocity = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalVelocity = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForHorizontalVelocity);
                dialogForLongLIPType3.add(textFieldForHorizontalVelocity);
                dialogForLongLIPType3.add(checkBoxForHorizontalVelocity);

                JLabel labelForDirectionOfTravelExtended = new JLabel("Direction of travel extended");
                JTextField textFieldForDirectionOfTravelExtended = new JTextField("0", 1);
                JCheckBox checkBoxForDirectionOfTravelExtended = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForDirectionOfTravelExtended);
                dialogForLongLIPType3.add(textFieldForDirectionOfTravelExtended);
                dialogForLongLIPType3.add(checkBoxForDirectionOfTravelExtended);

                JLabel labelForAcknowledgementRequest = new JLabel("Acknowledgement request");
                JTextField textFieldForAcknowledgementRequest = new JTextField("0", 1);
                textFieldForAcknowledgementRequest.setEditable(false);
                JCheckBox checkBoxForForAcknowledgementRequest = new JCheckBox("Changing");
                checkBoxForForAcknowledgementRequest.setEnabled(false);
                dialogForLongLIPType3.add(labelForAcknowledgementRequest);
                dialogForLongLIPType3.add(textFieldForAcknowledgementRequest);
                dialogForLongLIPType3.add(checkBoxForForAcknowledgementRequest);

                JLabel labelForTypeOfAdditionalData = new JLabel("Type of additional data");
                JTextField textFieldForTypeOfAdditionalData = new JTextField("0", 1);
                textFieldForTypeOfAdditionalData.setEditable(false);
                JCheckBox checkBoxForTypeOfAdditionalData = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPType3.add(labelForTypeOfAdditionalData);
                dialogForLongLIPType3.add(textFieldForTypeOfAdditionalData);
                dialogForLongLIPType3.add(checkBoxForTypeOfAdditionalData);

                JLabel labelForReasonForSending = new JLabel("Reason for sending");
                JTextField textFieldForReasonForSending = new JTextField("129", 1);
                JCheckBox checkBoxForReasonForSending = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForReasonForSending);
                dialogForLongLIPType3.add(textFieldForReasonForSending);
                dialogForLongLIPType3.add(checkBoxForReasonForSending);

                JButton buttonToAddLongLipType3 = new JButton("Add long LIP type 3");
                dialogForLongLIPType3.add(buttonToAddLongLipType3);

                buttonToAddLongLipType3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63, "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255, "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for sending");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        Map<Integer, Boolean> changeMap = new HashMap<Integer, Boolean>();
                        for (int i = 0; i < 8; i++) {
                            changeMap.put(i, false);
                        }
                        if (checkBoxForSSI.isSelected()) {
                            changeMap.replace(0, true);
                        }
                        if (checkBoxForLongitude.isSelected()) {
                            changeMap.replace(1, true);
                        }
                        if (checkBoxForLatitude.isSelected()) {
                            changeMap.replace(2, true);
                        }
                        if (checkBoxForHorizontalPositionUncertainty.isSelected()) {
                            changeMap.replace(3, true);
                        }
                        if (checkBoxForLocationAltitude.isSelected()) {
                            changeMap.replace(4, true);
                        }
                        if (checkBoxForHorizontalVelocity.isSelected()) {
                            changeMap.replace(5, true);
                        }
                        if (checkBoxForDirectionOfTravelExtended.isSelected()) {
                            changeMap.replace(6, true);
                        }
                        if (checkBoxForReasonForSending.isSelected()) {
                            changeMap.replace(7, true);
                        }

                        LongLipType3Message longLipType3Message = new LongLipType3Message();
                        longLipType3Message.withSSI(textFieldForSSI.getText())
                                .withLongitude(textFieldForLongitude.getText())
                                .withLatitude(textFieldForLatitude.getText())
                                .withHorizontal_position_uncertainty(textFieldForHorizontalPositionUncertainty.getText())
                                .withLocation_altitude(textFieldForLocationAltitude.getText())
                                .withHorizontal_velocity(textFieldForHorizontalVelocity.getText())
                                .withDirection_of_travel_extended(textFieldForDirectionOfTravelExtended.getText())
                                .withReason_for_sending(textFieldForReasonForSending.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType3Message);

                        dialogForLongLIPType3.dispose();
                        jTextAreaForUsersInputDATA.setText(jTextAreaForUsersInputDATA.getText() + "\n Long Lip type 3");
                    }
                });

                dialogForLongLIPType3.setVisible(true);
            }
        });


        buttonStartSendingUDPMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                udpClient.withListOFUDPMessage(listOfUDPMessages).startSendingMessageUdp();
                buttonStopSendingUPDMessage.setEnabled(true);
                buttonStartSendingUDPMessage.setEnabled(false);
                buttonAddShortLip.setEnabled(false);
                buttonAddLongLipType1.setEnabled(false);
                buttonAddLongLipType2.setEnabled(false);
                buttonAddLongLipType3.setEnabled(false);
                buttonAddTelemetryData.setEnabled(false);
            }
        });

        buttonStopSendingUPDMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                udpClient.stopSendingMessageUdp();
                buttonContinueSendingUPDMessage.setEnabled(true);
                buttonStopSendingUPDMessage.setEnabled(false);
                buttonAddShortLip.setEnabled(true);
                buttonAddLongLipType1.setEnabled(true);
                buttonAddLongLipType2.setEnabled(true);
                buttonAddLongLipType3.setEnabled(true);
                buttonAddTelemetryData.setEnabled(true);
            }
        });
        buttonContinueSendingUPDMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                udpClient.continueSendingUdpLipMessage();
                buttonStopSendingUPDMessage.setEnabled(true);
                buttonContinueSendingUPDMessage.setEnabled(false);
                buttonAddShortLip.setEnabled(false);
                buttonAddLongLipType1.setEnabled(false);
                buttonAddLongLipType2.setEnabled(false);
                buttonAddLongLipType3.setEnabled(false);
                buttonAddTelemetryData.setEnabled(false);
            }
        });

    }

    private void checkIntValueFromString(String checkString, int beginRange, int endRange, String nameOfField) throws Exception {
        try {
            int checkNumber = Integer.parseInt(checkString);

            if ((checkNumber < beginRange) || (checkNumber > endRange)) {
                String warning = nameOfField + " = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]";
                JDialog dialog = createWarningDialog(warning);
                dialog.setVisible(true);
                throw new Exception(warning);
            }
        } catch (Exception ex) {
            JDialog dialog = createWarningDialog("Wrong type Of value for " + nameOfField);
            dialog.setVisible(true);
            throw new Exception(nameOfField + " should be int");
        }

    }

    private void checkDoubleValueFromString(String checkString, double beginRange, double endRange, String nameOfField) throws Exception {
        try {
            double checkNumber = Double.parseDouble(checkString);
            if ((checkNumber < beginRange) || (checkNumber > endRange)) {
                String warning = nameOfField + " = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]";
                JDialog dialog = createWarningDialog(warning);
                dialog.setVisible(true);
                throw new Exception(checkNumber + "cannot be out of range [" + beginRange + ".." + endRange + "]");
            }
        } catch (Exception ex) {
            JDialog dialog = createWarningDialog("Wrong type value for " + nameOfField);
            dialog.setVisible(true);
            throw new Exception(nameOfField + " should be double");
        }
    }


    private JDialog createDialog(String title, boolean modal, int width, int height, int columns, int rows) {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(width, height);
        dialog.setLayout(new GridLayout(columns, rows));
        //Помещаем поцентру относительно главного фрейма contents
        dialog.setLocationRelativeTo(contents);
        return dialog;
    }

    private JDialog createWarningDialog(String messageInDialog) {
        JDialog dialog = new JDialog(this, "Ошибка", true);
        JLabel dialogLabel = new JLabel(messageInDialog);
        dialog.add(dialogLabel);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 90);
        //Помещаем поцентру относительно главного фрейма contents
        dialog.setLocationRelativeTo(contents);
        return dialog;
    }

    public static void main(String[] args) {
        new UI();
    }
}
