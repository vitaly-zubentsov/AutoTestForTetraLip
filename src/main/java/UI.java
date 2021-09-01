
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
    DefaultListModel listModel = new DefaultListModel();


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
        JButton buttonAddTelemetryOfDeviceData = new JButton("Add LIP with telemetry");
        buttonAddTelemetryOfDeviceData.setEnabled(false);
        JButton buttonAddLipWithReceiveLevel = new JButton("Add LIP with receive level");
        buttonAddLipWithReceiveLevel.setEnabled(false);

        JTextArea jTextAreaForUsersInputDATA = new JTextArea();
        Dimension s = new Dimension(200, 250);
        jTextAreaForUsersInputDATA.setPreferredSize(s);
        jTextAreaForUsersInputDATA.setText("This field show \n input data for UDP messages \n \n \n \n \n \n \n");

        JList listToShowMessages = new JList(listModel);
        listToShowMessages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollToShowMessages = new JScrollPane(listToShowMessages);
        listToShowMessages.setLayoutOrientation(JList.VERTICAL);


        JButton buttonStartSendingUDPMessage = new JButton("Start sending UDP message");
        buttonStartSendingUDPMessage.setEnabled(false);
        JButton buttonStopSendingUPDMessage = new JButton("Stop sending UDP message");
        buttonStopSendingUPDMessage.setEnabled(false);
        JButton buttonContinueSendingUPDMessage = new JButton("Continue sending UDP message");
        buttonContinueSendingUPDMessage.setEnabled(false);


        // Создание панели содержимого с размещением кнопок

        // Создание панели содержимого с размещением кнопок
        final JPanel rightSide = new JPanel();
        rightSide.setLayout(new GridLayout(10, 1, 1, 1));
        rightSide.add(buttonSetUDPOptions);
        rightSide.add(buttonAddShortLip);
        rightSide.add(buttonAddLongLipType1);
        rightSide.add(buttonAddLongLipType2);
        rightSide.add(buttonAddLongLipType3);
        rightSide.add(buttonAddTelemetryOfDeviceData);
        rightSide.add(buttonAddLipWithReceiveLevel);
        rightSide.add(buttonStartSendingUDPMessage);
        rightSide.add(buttonStopSendingUPDMessage);
        rightSide.add(buttonContinueSendingUPDMessage);


        final JPanel leftSide = new JPanel();
        leftSide.setLayout(new GridLayout(1, 1, 1, 1));
        leftSide.add(scrollToShowMessages);


        // Добавление панелей на главный слой окна
        getContentPane().add(leftSide, BorderLayout.CENTER);
        getContentPane().add(rightSide, BorderLayout.EAST);

        // Определение размера и открытие окна
        setSize(800, 600);
        setLocationRelativeTo(null);


        buttonSetUDPOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForUDPOptions = createDialog("Input options for UDP messages", true, 400, 200, 5, 2);

                JLabel labelForIpDestination = new JLabel("Input destination IP");
                JTextField textFieldForIpDestination = new JTextField("192.168.252.146", 1);
                dialogForUDPOptions.add(labelForIpDestination);
                dialogForUDPOptions.add(textFieldForIpDestination);

                JLabel labelForPortDestination = new JLabel("Input destination port");
                JTextField textFieldForPortDestination = new JTextField("11372", 1);
                dialogForUDPOptions.add(labelForPortDestination);
                dialogForUDPOptions.add(textFieldForPortDestination);

                JLabel labelForPortSource = new JLabel("Input source port");
                JTextField textFieldForPortSource = new JTextField("7733", 1);
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

                        buttonSetUDPOptions.setEnabled(false);
                        buttonAddShortLip.setEnabled(true);
                        buttonAddLongLipType1.setEnabled(true);
                        buttonAddLongLipType2.setEnabled(true);
                        buttonAddLongLipType3.setEnabled(true);
                        buttonAddTelemetryOfDeviceData.setEnabled(true);
                        buttonAddLipWithReceiveLevel.setEnabled(true);
                        buttonStartSendingUDPMessage.setEnabled(true);

                    }
                });

                dialogForUDPOptions.setVisible(true);
                String resultTextToAdding =
                        new StringBuilder().append(textFieldForIpDestination.getText()).append(":").append(textFieldForPortDestination.getText()).append(", ").append(textFieldForPortSource.getText()).append(", ").append(textFieldForIntervalOfTimeSendingUDP.getText()).toString();
                buttonSetUDPOptions.setText(resultTextToAdding);
            }
        });


        buttonAddShortLip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForShortLIP = createDialog("Input data for short LIP", true, 600, 350, 12, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForShortLIP.add(labelForSSI);
                dialogForShortLIP.add(textFieldForSSI);
                dialogForShortLIP.add(checkBoxForSSI);

                JLabel labelForBaseStationNumber = new JLabel("BSNumber");
                JTextField textFieldForBaseStationNumber = new JTextField("10", 1);
                JCheckBox checkBoxForBaseStationNumber = new JCheckBox("Changing");
                checkBoxForBaseStationNumber.setEnabled(false);
                dialogForShortLIP.add(labelForBaseStationNumber);
                dialogForShortLIP.add(textFieldForBaseStationNumber);
                dialogForShortLIP.add(checkBoxForBaseStationNumber);

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
                            checkIntValueFromString(textFieldForBaseStationNumber.getText(), 1, 130,
                                    "BaseStationNumber");
                            checkIntValueFromString(textFieldForTimeElapsed.getText(), 0, 3, "TimeElapsed");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForPositionError.getText(), 0, 7, "PositionError");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127,
                                    "HorizontalVelocity");
                            checkIntValueFromString(textFieldForDirectionOfTravel.getText(), 0, 15,
                                    "DirectionOfTravel");
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
                                .withBaseStationNumber(textFieldForBaseStationNumber.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(shortLipMessage);
                        dialogForShortLIP.dispose();

                        listModel.addElement("Short Lip " + textFieldForSSI.getText() + " " + convertChangeMapToString(changeMap));

                    }


                });

                dialogForShortLIP.setVisible(true);
            }
        });
        buttonAddLongLipType1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPType1 = createDialog("Input data for long LIP type 1", true, 600, 500, 17, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPType1.add(labelForSSI);
                dialogForLongLIPType1.add(textFieldForSSI);
                dialogForLongLIPType1.add(checkBoxForSSI);

                JLabel labelForBaseStationNumber = new JLabel("BSNumber");
                JTextField textFieldForBaseStationNumber = new JTextField("10", 1);
                JCheckBox checkBoxForBaseStationNumber = new JCheckBox("Changing");
                checkBoxForBaseStationNumber.setEnabled(false);
                dialogForLongLIPType1.add(labelForBaseStationNumber);
                dialogForLongLIPType1.add(textFieldForBaseStationNumber);
                dialogForLongLIPType1.add(checkBoxForBaseStationNumber);

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
                            checkIntValueFromString(textFieldForBaseStationNumber.getText(), 1, 130,
                                    "BaseStationNumber");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63,
                                    "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location " +
                                    "altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal " +
                                    "velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255,
                                    "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for " +
                                    "sending");
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
                                .withBaseStationNumber(textFieldForBaseStationNumber.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType1Message);

                        dialogForLongLIPType1.dispose();
                        jTextAreaForUsersInputDATA.setText("Long Lip type 1 "
                                + textFieldForSSI.getText() + " " + convertChangeMapToString(changeMap));
                    }
                });

                dialogForLongLIPType1.setVisible(true);
            }
        });
        buttonAddLongLipType2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPType2 = createDialog("Input data for long LIP type 2", true, 600, 500, 19, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPType2.add(labelForSSI);
                dialogForLongLIPType2.add(textFieldForSSI);
                dialogForLongLIPType2.add(checkBoxForSSI);

                JLabel labelForBaseStationNumber = new JLabel("BSNumber");
                JTextField textFieldForBaseStationNumber = new JTextField("10", 1);
                JCheckBox checkBoxForBaseStationNumber = new JCheckBox("Changing");
                checkBoxForBaseStationNumber.setEnabled(false);
                dialogForLongLIPType2.add(labelForBaseStationNumber);
                dialogForLongLIPType2.add(textFieldForBaseStationNumber);
                dialogForLongLIPType2.add(checkBoxForBaseStationNumber);

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
                            checkIntValueFromString(textFieldForBaseStationNumber.getText(), 1, 130,
                                    "BaseStationNumber");
                            checkIntValueFromString(textFieldForTimeElapsed.getText(), 0, 3, "Time elapsed");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63,
                                    "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location " +
                                    "altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal " +
                                    "velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255,
                                    "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for " +
                                    "sending");
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
                                .withBaseStationNumber(textFieldForBaseStationNumber.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType2Message);

                        dialogForLongLIPType2.dispose();

                        listModel.addElement("Long Lip type 2 " + textFieldForSSI.getText() + " " + convertChangeMapToString(changeMap));
                    }
                });

                dialogForLongLIPType2.setVisible(true);
            }
        });
        buttonAddLongLipType3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPType3 = createDialog("Input data for long LIP type 3", true, 600, 500, 18, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPType3.add(labelForSSI);
                dialogForLongLIPType3.add(textFieldForSSI);
                dialogForLongLIPType3.add(checkBoxForSSI);

                JLabel labelForBaseStationNumber = new JLabel("BSNumber");
                JTextField textFieldForBaseStationNumber = new JTextField("10", 1);
                JCheckBox checkBoxForBaseStationNumber = new JCheckBox("Changing");
                checkBoxForBaseStationNumber.setEnabled(false);
                dialogForLongLIPType3.add(labelForBaseStationNumber);
                dialogForLongLIPType3.add(textFieldForBaseStationNumber);
                dialogForLongLIPType3.add(checkBoxForBaseStationNumber);

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
                checkBoxForForTimeData.setEnabled(false);
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
                            checkIntValueFromString(textFieldForBaseStationNumber.getText(), 1, 130,
                                    "BaseStationNumber");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63,
                                    "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location " +
                                    "altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal " +
                                    "velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255,
                                    "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for " +
                                    "sending");
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
                                .withBaseStationNumber(textFieldForBaseStationNumber.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType3Message);

                        dialogForLongLIPType3.dispose();
                        listModel.addElement("Long Lip type 3 " + textFieldForSSI.getText() + " " + convertChangeMapToString(changeMap));
                    }
                });

                dialogForLongLIPType3.setVisible(true);
            }
        });

        buttonAddTelemetryOfDeviceData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPWithTelemetryOfDeviceData = createDialog("Input Lip with telemetry" +
                        " telemetry data", true, 700, 700, 38, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7031", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForSSI);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForSSI);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForSSI);

                JLabel labelForBaseStationNumber = new JLabel("BSNumber");
                JTextField textFieldForBaseStationNumber = new JTextField("10", 1);
                JCheckBox checkBoxForBaseStationNumber = new JCheckBox("Changing");
                checkBoxForBaseStationNumber.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForBaseStationNumber);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForBaseStationNumber);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForBaseStationNumber);

                JLabel labelForPDUType = new JLabel("PDU Type");
                JTextField textFieldFoForPDUType = new JTextField("1", 1);
                textFieldFoForPDUType.setEditable(false);
                JCheckBox checkBoxForForPDUType = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForPDUType);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldFoForPDUType);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForForPDUType);

                JLabel labelForPDUTypeExtension = new JLabel("PDU Type extension");
                JTextField textFieldForPDUTypeExtension = new JTextField("3", 1);
                textFieldForPDUTypeExtension.setEditable(false);
                JCheckBox checkBoxForPDUTypeExtension = new JCheckBox("Changing");
                checkBoxForPDUTypeExtension.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForPDUTypeExtension);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForPDUTypeExtension);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForPDUTypeExtension);

                JLabel labelForTimeData = new JLabel("Time data");
                JTextField textFieldFoTimeData = new JTextField("0", 1);
                textFieldFoTimeData.setEditable(false);
                JCheckBox checkBoxForForTimeData = new JCheckBox("Changing");
                checkBoxForForTimeData.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTimeData);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldFoTimeData);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForForTimeData);

                JLabel labelForLocationShape = new JLabel("Location shape");
                JTextField textFieldForLocationShape = new JTextField("5", 1);
                textFieldForLocationShape.setEditable(false);
                JCheckBox checkBoxForLocationShape = new JCheckBox("Changing");
                checkBoxForLocationShape.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForLocationShape);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForLocationShape);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForLocationShape);

                JLabel labelForLongitude = new JLabel("Longitude");
                JTextField textFieldForLongitude = new JTextField("37.66000628471", 1);
                JCheckBox checkBoxForLongitude = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForLongitude);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForLongitude);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForLongitude);

                JLabel labelForLatitude = new JLabel("Latitude");
                JTextField textFieldForLatitude = new JTextField("55.7839286", 1);
                JCheckBox checkBoxForLatitude = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForLatitude);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForLatitude);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForLatitude);

                JLabel labelForHorizontalPositionUncertainty = new JLabel("Horizontal position uncertainty");
                JTextField textFieldForHorizontalPositionUncertainty = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalPositionUncertainty = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForHorizontalPositionUncertainty);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForHorizontalPositionUncertainty);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForHorizontalPositionUncertainty);

                JLabel labelForLocationAltitude = new JLabel("Location altitude");
                JTextField textFieldForLocationAltitude = new JTextField("0", 1);
                JCheckBox checkBoxForLocationAltitude = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForLocationAltitude);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForLocationAltitude);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForLocationAltitude);

                JLabel labelForVelocityType = new JLabel("Velocity type");
                JTextField textFieldForVelocityType = new JTextField("5", 1);
                textFieldForVelocityType.setEditable(false);
                JCheckBox checkBoxForVelocityType = new JCheckBox("Changing");
                checkBoxForVelocityType.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForVelocityType);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForVelocityType);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForVelocityType);

                JLabel labelForHorizontalVelocity = new JLabel("Horizontal velocity");
                JTextField textFieldForHorizontalVelocity = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalVelocity = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForHorizontalVelocity);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForHorizontalVelocity);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForHorizontalVelocity);

                JLabel labelForDirectionOfTravelExtended = new JLabel("Direction of travel extended");
                JTextField textFieldForDirectionOfTravelExtended = new JTextField("0", 1);
                JCheckBox checkBoxForDirectionOfTravelExtended = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForDirectionOfTravelExtended);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForDirectionOfTravelExtended);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForDirectionOfTravelExtended);

                JLabel labelForAcknowledgementRequest = new JLabel("Acknowledgement request");
                JTextField textFieldForAcknowledgementRequest = new JTextField("0", 1);
                textFieldForAcknowledgementRequest.setEditable(false);
                JCheckBox checkBoxForForAcknowledgementRequest = new JCheckBox("Changing");
                checkBoxForForAcknowledgementRequest.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForAcknowledgementRequest);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForAcknowledgementRequest);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForForAcknowledgementRequest);

                JLabel labelForTypeOfAdditionalData = new JLabel("Type of additional data");
                JTextField textFieldForTypeOfAdditionalData = new JTextField("0", 1);
                textFieldForTypeOfAdditionalData.setEditable(false);
                JCheckBox checkBoxForTypeOfAdditionalData = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTypeOfAdditionalData);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTypeOfAdditionalData);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTypeOfAdditionalData);

                JLabel labelForReasonForSending = new JLabel("Reason for sending");
                JTextField textFieldForReasonForSending = new JTextField("129", 1);
                JCheckBox checkBoxForReasonForSending = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForReasonForSending);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForReasonForSending);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForReasonForSending);

                JLabel labelForType5ElementIdentifier = new JLabel("Type 5 element identifier");
                JTextField textFieldForType5ElementIdentifier = new JTextField("1", 1);
                textFieldForType5ElementIdentifier.setEditable(false);
                JCheckBox checkBoxForType5ElementIdentifier = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForType5ElementIdentifier);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForType5ElementIdentifier);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForType5ElementIdentifier);

                JLabel labelForType5ElementLength = new JLabel("Type 5 element length");
                JTextField textFieldForType5Type5ElementLength = new JTextField("0", 1);
                textFieldForType5Type5ElementLength.setEditable(false);
                JCheckBox checkBoxForType5ElementLength = new JCheckBox("Changing");
                checkBoxForType5ElementLength.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForType5ElementLength);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForType5Type5ElementLength);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForType5ElementLength);

                JLabel labelForType5ElementLengthExtension = new JLabel("Type 5 element length extension");
                JTextField textFieldForType5ElementLengthExtension = new JTextField("9", 1);
                textFieldForType5ElementLengthExtension.setEditable(false);
                JCheckBox checkBoxForType5ElementLengthExtension = new JCheckBox("Changing");
                checkBoxForType5ElementLengthExtension.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForType5ElementLengthExtension);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForType5ElementLengthExtension);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForType5ElementLengthExtension);

                JLabel labelForTypeOfMessageFromDevice = new JLabel("Type of message from device");
                JTextField textFieldForTypeOfMessageFromDevice = new JTextField("1", 1);
                textFieldForTypeOfMessageFromDevice.setEditable(false);
                JCheckBox checkBoxForTypeOfMessageFromDevice = new JCheckBox("Changing");
                checkBoxForTypeOfMessageFromDevice.setEnabled(false);
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTypeOfMessageFromDevice);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTypeOfMessageFromDevice);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTypeOfMessageFromDevice);

                JLabel labelForTypeOfDevice = new JLabel("Type of device");
                JTextField textFieldForTypeOfDevice = new JTextField("0", 1);
                JCheckBox checkBoxForTypeOfDevice = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTypeOfDevice);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTypeOfDevice);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTypeOfDevice);

                JLabel labelForPowerSupplyVoltageAtTheInputBPP = new JLabel("Power supply voltage at the input BPP");
                JTextField textFieldForPowerSupplyVoltageAtTheInputBPP = new JTextField("100", 1);
                JCheckBox checkBoxForPowerSupplyVoltageAtTheInputBPP = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForPowerSupplyVoltageAtTheInputBPP);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForPowerSupplyVoltageAtTheInputBPP);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForPowerSupplyVoltageAtTheInputBPP);

                JLabel labelForPowerSupplyVoltageAtTheControlConsoleInput = new JLabel("Power supply voltage at the " +
                        "control console input");
                JTextField textFieldForPowerSupplyVoltageAtTheControlConsoleInput = new JTextField("105", 1);
                JCheckBox checkBoxForPowerSupplyVoltageAtTheControlConsoleInput = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForPowerSupplyVoltageAtTheControlConsoleInput);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForPowerSupplyVoltageAtTheControlConsoleInput);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForPowerSupplyVoltageAtTheControlConsoleInput);

                JLabel labelForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission = new JLabel("Power " +
                        "supply of the transmitter output amplifier during transmission");
                JTextField textFieldForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission = new JTextField(
                        "48", 1);
                JCheckBox checkBoxForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission = new JCheckBox(
                        "Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission);

                JLabel labelForTargetTransmitterPower = new JLabel("Target transmitter power");
                JTextField textFieldForTargetTransmitterPower = new JTextField("1", 1);
                JCheckBox checkBoxForTargetTransmitterPower = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTargetTransmitterPower);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTargetTransmitterPower);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTargetTransmitterPower);

                JLabel labelForTransmitterAmplifierOutputTemperature = new JLabel("Transmitter amplifier output " +
                        "temperature");
                JTextField textFieldForTransmitterAmplifierOutputTemperature = new JTextField("148", 1);
                JCheckBox checkBoxForTransmitterAmplifierOutputTemperature = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTransmitterAmplifierOutputTemperature);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTransmitterAmplifierOutputTemperature);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTransmitterAmplifierOutputTemperature);

                JLabel labelForSWROfTheConnectedAntennaAtTheOperatingFrequency = new JLabel("SWR of the connected " +
                        "antenna at the operating frequency");
                JTextField textFieldForSWROfTheConnectedAntennaAtTheOperatingFrequency = new JTextField("25", 1);
                JCheckBox checkBoxForSWROfTheConnectedAntennaAtTheOperatingFrequency = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForSWROfTheConnectedAntennaAtTheOperatingFrequency);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForSWROfTheConnectedAntennaAtTheOperatingFrequency);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForSWROfTheConnectedAntennaAtTheOperatingFrequency);

                JLabel labelForConditionHFTract = new JLabel("Condition HF tract");
                JTextField textFieldForConditionHFTract = new JTextField("1", 1);
                JCheckBox checkBoxForConditionHFTract = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForConditionHFTract);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForConditionHFTract);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForConditionHFTract);

                JLabel labelForAudioOutputStatus = new JLabel("Audio Output Status");
                JTextField textFieldForAudioOutputStatus = new JTextField("1", 1);
                JCheckBox checkBoxForAudioOutputStatus = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForAudioOutputStatus);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForAudioOutputStatus);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForAudioOutputStatus);

                JLabel labelForStatusOfTheNavigationModule = new JLabel("Status of the navigation module");
                JTextField textFieldForStatusOfTheNavigationModule = new JTextField("1", 1);
                JCheckBox checkBoxForStatusOfTheNavigationModule = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForStatusOfTheNavigationModule);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForStatusOfTheNavigationModule);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForStatusOfTheNavigationModule);

                JLabel labelForDryContactStatus = new JLabel("Dry contact status");
                JTextField textFieldForDryContactStatus = new JTextField("3", 1);
                JCheckBox checkBoxForDryContactStatus = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForDryContactStatus);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForDryContactStatus);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForDryContactStatus);

                JLabel labelForTotalWorkingTimeOfDevice = new JLabel("Total working time of device");
                JTextField textFieldForTotalWorkingTimeOfDevice = new JTextField("159", 1);
                JCheckBox checkBoxForTotalWorkingTimeOfDevice = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTotalWorkingTimeOfDevice);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTotalWorkingTimeOfDevice);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTotalWorkingTimeOfDevice);

                JLabel labelForTotalNumberOfDeviceReloads = new JLabel("Total number of device reloads");
                JTextField textFieldForTotalNumberOfDeviceReloads = new JTextField("852", 1);
                JCheckBox checkBoxForTotalNumberOfDeviceReloads = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForTotalNumberOfDeviceReloads);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForTotalNumberOfDeviceReloads);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForTotalNumberOfDeviceReloads);

                JLabel labelForNumberOfRegularDeviceReloads = new JLabel("Number of regular device reloads");
                JTextField textFieldForNumberOfRegularDeviceReloads = new JTextField("741", 1);
                JCheckBox checkBoxForNumberOfRegularDeviceReloads = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForNumberOfRegularDeviceReloads);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForNumberOfRegularDeviceReloads);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForNumberOfRegularDeviceReloads);

                JLabel labelForOperatingTimeAfterTheLastPowerUp = new JLabel("Operating time after the last power up");
                JTextField textFieldForOperatingTimeAfterTheLastPowerUp = new JTextField("48", 1);
                JCheckBox checkBoxForOperatingTimeAfterTheLastPowerUp = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForOperatingTimeAfterTheLastPowerUp);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForOperatingTimeAfterTheLastPowerUp);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForOperatingTimeAfterTheLastPowerUp);

                JLabel labelForSoftwareVersionNumber = new JLabel("Software version number");
                JTextField textFieldForSoftwareVersionNumber = new JTextField("11", 1);
                JCheckBox checkBoxForSoftwareVersionNumber = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForSoftwareVersionNumber);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForSoftwareVersionNumber);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForSoftwareVersionNumber);

                JLabel labelForPCBVersionNumber = new JLabel("PCB version number");
                JTextField textFieldForPCBVersionNumber = new JTextField("10", 1);
                JCheckBox checkBoxForPCBVersionNumber = new JCheckBox("Changing");
                dialogForLongLIPWithTelemetryOfDeviceData.add(labelForPCBVersionNumber);
                dialogForLongLIPWithTelemetryOfDeviceData.add(textFieldForPCBVersionNumber);
                dialogForLongLIPWithTelemetryOfDeviceData.add(checkBoxForPCBVersionNumber);


                JButton buttonToAddLongLipType1 = new JButton("Add Lip with telemetry");
                dialogForLongLIPWithTelemetryOfDeviceData.add(buttonToAddLongLipType1);

                buttonToAddLongLipType1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
                            checkIntValueFromString(textFieldForBaseStationNumber.getText(), 1, 130,
                                    "BaseStationNumber");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63,
                                    "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location " +
                                    "altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal " +
                                    "velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255,
                                    "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for " +
                                    "sending");

                            checkIntValueFromString(textFieldForTypeOfDevice.getText(), 0, 7, "Type of device");
                            checkIntValueFromString(textFieldForPowerSupplyVoltageAtTheInputBPP.getText(), 0, 127,
                                    "Power supply voltage at the input BPP");
                            checkIntValueFromString(textFieldForPowerSupplyVoltageAtTheControlConsoleInput.getText(),
                                    0, 127, "Power supply voltage at the control console input");
                            checkIntValueFromString(textFieldForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission.getText(), 0, 127, "Power supply of the transmitter output amplifier during transmission");
                            checkIntValueFromString(textFieldForTargetTransmitterPower.getText(), 0, 7, "Target " +
                                    "transmitter power");
                            checkIntValueFromString(textFieldForTransmitterAmplifierOutputTemperature.getText(), 0,
                                    255, "Transmitter amplifier output temperature");
                            checkIntValueFromString(textFieldForSWROfTheConnectedAntennaAtTheOperatingFrequency.getText(), 0, 31, "SWR of the connected antenna at the operating frequency");
                            checkIntValueFromString(textFieldForConditionHFTract.getText(), 0, 1, "Condition HF tract");
                            checkIntValueFromString(textFieldForAudioOutputStatus.getText(), 0, 1, "Audio Output " +
                                    "Status");
                            checkIntValueFromString(textFieldForStatusOfTheNavigationModule.getText(), 0, 1, "Status " +
                                    "of the navigation module");
                            checkIntValueFromString(textFieldForDryContactStatus.getText(), 0, 255, "Dry contact " +
                                    "status");
                            checkIntValueFromString(textFieldForTotalWorkingTimeOfDevice.getText(), 0, 65535, "Total " +
                                    "working time of device");
                            checkIntValueFromString(textFieldForTotalNumberOfDeviceReloads.getText(), 0, 4095, "Total" +
                                    " number of device reloads");
                            checkIntValueFromString(textFieldForNumberOfRegularDeviceReloads.getText(), 0, 4095,
                                    "Number of regular device reloads");
                            checkIntValueFromString(textFieldForOperatingTimeAfterTheLastPowerUp.getText(), 0, 65535,
                                    "Operating time after the last power up");
                            checkIntValueFromString(textFieldForSoftwareVersionNumber.getText(), 0, 127, "Software " +
                                    "version number");
                            checkIntValueFromString(textFieldForPCBVersionNumber.getText(), 0, 127, "PCB version " +
                                    "number");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        Map<Integer, Boolean> changeMap = new HashMap<Integer, Boolean>();
                        for (int i = 0; i < 25; i++) {
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

                        if (checkBoxForTypeOfDevice.isSelected()) {
                            changeMap.replace(8, true);
                        }

                        if (checkBoxForPowerSupplyVoltageAtTheInputBPP.isSelected()) {
                            changeMap.replace(9, true);
                        }

                        if (checkBoxForPowerSupplyVoltageAtTheControlConsoleInput.isSelected()) {
                            changeMap.replace(10, true);
                        }

                        if (checkBoxForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission.isSelected()) {
                            changeMap.replace(11, true);
                        }

                        if (checkBoxForTargetTransmitterPower.isSelected()) {
                            changeMap.replace(12, true);
                        }

                        if (checkBoxForTransmitterAmplifierOutputTemperature.isSelected()) {
                            changeMap.replace(13, true);
                        }

                        if (checkBoxForSWROfTheConnectedAntennaAtTheOperatingFrequency.isSelected()) {
                            changeMap.replace(14, true);
                        }

                        if (checkBoxForConditionHFTract.isSelected()) {
                            changeMap.replace(15, true);
                        }

                        if (checkBoxForAudioOutputStatus.isSelected()) {
                            changeMap.replace(16, true);
                        }

                        if (checkBoxForStatusOfTheNavigationModule.isSelected()) {
                            changeMap.replace(17, true);
                        }

                        if (checkBoxForDryContactStatus.isSelected()) {
                            changeMap.replace(18, true);
                        }

                        if (checkBoxForTotalWorkingTimeOfDevice.isSelected()) {
                            changeMap.replace(19, true);
                        }

                        if (checkBoxForTotalNumberOfDeviceReloads.isSelected()) {
                            changeMap.replace(20, true);
                        }

                        if (checkBoxForNumberOfRegularDeviceReloads.isSelected()) {
                            changeMap.replace(21, true);
                        }

                        if (checkBoxForOperatingTimeAfterTheLastPowerUp.isSelected()) {
                            changeMap.replace(22, true);
                        }

                        if (checkBoxForSoftwareVersionNumber.isSelected()) {
                            changeMap.replace(23, true);
                        }

                        if (checkBoxForPCBVersionNumber.isSelected()) {
                            changeMap.replace(24, true);
                        }

                        LongLipType1WithTelemetryDataMessage longLipType1WithTelemetryDataMessage =
                                new LongLipType1WithTelemetryDataMessage();
                        longLipType1WithTelemetryDataMessage.withSSI(textFieldForSSI.getText())
                                .withLongitude(textFieldForLongitude.getText())
                                .withLatitude(textFieldForLatitude.getText())
                                .withHorizontal_position_uncertainty(textFieldForHorizontalPositionUncertainty.getText())
                                .withLocation_altitude(textFieldForLocationAltitude.getText())
                                .withHorizontal_velocity(textFieldForHorizontalVelocity.getText())
                                .withDirection_of_travel_extended(textFieldForDirectionOfTravelExtended.getText())
                                .withReason_for_sending(textFieldForReasonForSending.getText())
                                .withType_of_device(textFieldForTypeOfDevice.getText())
                                .withPower_supply_voltage_at_the_input_bpp(textFieldForPowerSupplyVoltageAtTheInputBPP.getText())
                                .withPower_supply_voltage_at_the_control_console_input(textFieldForPowerSupplyVoltageAtTheControlConsoleInput.getText())
                                .withPower_supply_of_the_transmitter_output_amplifier_during_transmission(textFieldForPowerSupplyOfTheTransmitterOutputAmplifierDuringTransmission.getText())
                                .withTarget_transmitter_power(textFieldForTargetTransmitterPower.getText())
                                .withTransmitter_amplifier_output_temperature(textFieldForTransmitterAmplifierOutputTemperature.getText())
                                .withSwr_of_the_connected_antenna_at_the_operating_frequency(textFieldForSWROfTheConnectedAntennaAtTheOperatingFrequency.getText())
                                .withCondition_hf_tract(textFieldForConditionHFTract.getText())
                                .withAudio_output_status(textFieldForAudioOutputStatus.getText())
                                .withStatus_of_the_navigation_module(textFieldForStatusOfTheNavigationModule.getText())
                                .withDry_contact_status(textFieldForDryContactStatus.getText())
                                .withTotal_working_time_of_device(textFieldForTotalWorkingTimeOfDevice.getText())
                                .withTotal_number_of_device_reloads(textFieldForTotalNumberOfDeviceReloads.getText())
                                .withNumber_of_regular_device_reloads(textFieldForNumberOfRegularDeviceReloads.getText())
                                .withOperating_time_after_the_last_power_up(textFieldForOperatingTimeAfterTheLastPowerUp.getText())
                                .withSoftware_version_number(textFieldForSoftwareVersionNumber.getText())
                                .withPcb_version_number(textFieldForPCBVersionNumber.getText())
                                .withBaseStationNumber(textFieldForBaseStationNumber.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType1WithTelemetryDataMessage);

                        dialogForLongLIPWithTelemetryOfDeviceData.dispose();
                        listModel.addElement("Lip with telemetry" + textFieldForSSI.getText() + " " + convertChangeMapToString(changeMap));
                    }
                });

                dialogForLongLIPWithTelemetryOfDeviceData.setVisible(true);

            }
        });

        buttonAddLipWithReceiveLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialogForLongLIPWithReceiveLevel = createDialog("Input data for LIP with receive " +
                        "level", true, 700, 700, 26, 3);

                JLabel labelForSSI = new JLabel("SSI");
                JTextField textFieldForSSI = new JTextField("7070", 1);
                JCheckBox checkBoxForSSI = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForSSI);
                dialogForLongLIPWithReceiveLevel.add(textFieldForSSI);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForSSI);

                JLabel labelForBaseStationNumber = new JLabel("BSNumber");
                JTextField textFieldForBaseStationNumber = new JTextField("10", 1);
                JCheckBox checkBoxForBaseStationNumber = new JCheckBox("Changing");
                checkBoxForBaseStationNumber.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForBaseStationNumber);
                dialogForLongLIPWithReceiveLevel.add(textFieldForBaseStationNumber);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForBaseStationNumber);

                JLabel labelForPDUType = new JLabel("PDU Type");
                JTextField textFieldFoForPDUType = new JTextField("1", 1);
                textFieldFoForPDUType.setEditable(false);
                JCheckBox checkBoxForForPDUType = new JCheckBox("Changing");
                checkBoxForForPDUType.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForPDUType);
                dialogForLongLIPWithReceiveLevel.add(textFieldFoForPDUType);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForForPDUType);

                JLabel labelForPDUTypeExtension = new JLabel("PDU Type extension");
                JTextField textFieldForPDUTypeExtension = new JTextField("3", 1);
                textFieldForPDUTypeExtension.setEditable(false);
                JCheckBox checkBoxForPDUTypeExtension = new JCheckBox("Changing");
                checkBoxForPDUTypeExtension.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForPDUTypeExtension);
                dialogForLongLIPWithReceiveLevel.add(textFieldForPDUTypeExtension);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForPDUTypeExtension);

                JLabel labelForTimeData = new JLabel("Time data");
                JTextField textFieldFoTimeData = new JTextField("0", 1);
                textFieldFoTimeData.setEditable(false);
                JCheckBox checkBoxForForTimeData = new JCheckBox("Changing");
                checkBoxForForTimeData.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForTimeData);
                dialogForLongLIPWithReceiveLevel.add(textFieldFoTimeData);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForForTimeData);

                JLabel labelForLocationShape = new JLabel("Location shape");
                JTextField textFieldForLocationShape = new JTextField("5", 1);
                textFieldForLocationShape.setEditable(false);
                JCheckBox checkBoxForLocationShape = new JCheckBox("Changing");
                checkBoxForLocationShape.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForLocationShape);
                dialogForLongLIPWithReceiveLevel.add(textFieldForLocationShape);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForLocationShape);

                JLabel labelForLongitude = new JLabel("Longitude");
                JTextField textFieldForLongitude = new JTextField("37.66000628471", 1);
                JCheckBox checkBoxForLongitude = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForLongitude);
                dialogForLongLIPWithReceiveLevel.add(textFieldForLongitude);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForLongitude);

                JLabel labelForLatitude = new JLabel("Latitude");
                JTextField textFieldForLatitude = new JTextField("55.7839286", 1);
                JCheckBox checkBoxForLatitude = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForLatitude);
                dialogForLongLIPWithReceiveLevel.add(textFieldForLatitude);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForLatitude);

                JLabel labelForHorizontalPositionUncertainty = new JLabel("Horizontal position uncertainty");
                JTextField textFieldForHorizontalPositionUncertainty = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalPositionUncertainty = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForHorizontalPositionUncertainty);
                dialogForLongLIPWithReceiveLevel.add(textFieldForHorizontalPositionUncertainty);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForHorizontalPositionUncertainty);

                JLabel labelForLocationAltitude = new JLabel("Location altitude");
                JTextField textFieldForLocationAltitude = new JTextField("0", 1);
                JCheckBox checkBoxForLocationAltitude = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForLocationAltitude);
                dialogForLongLIPWithReceiveLevel.add(textFieldForLocationAltitude);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForLocationAltitude);

                JLabel labelForVelocityType = new JLabel("Velocity type");
                JTextField textFieldForVelocityType = new JTextField("5", 1);
                textFieldForVelocityType.setEditable(false);
                JCheckBox checkBoxForVelocityType = new JCheckBox("Changing");
                checkBoxForVelocityType.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForVelocityType);
                dialogForLongLIPWithReceiveLevel.add(textFieldForVelocityType);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForVelocityType);

                JLabel labelForHorizontalVelocity = new JLabel("Horizontal velocity");
                JTextField textFieldForHorizontalVelocity = new JTextField("0", 1);
                JCheckBox checkBoxForHorizontalVelocity = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForHorizontalVelocity);
                dialogForLongLIPWithReceiveLevel.add(textFieldForHorizontalVelocity);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForHorizontalVelocity);

                JLabel labelForDirectionOfTravelExtended = new JLabel("Direction of travel extended");
                JTextField textFieldForDirectionOfTravelExtended = new JTextField("0", 1);
                JCheckBox checkBoxForDirectionOfTravelExtended = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForDirectionOfTravelExtended);
                dialogForLongLIPWithReceiveLevel.add(textFieldForDirectionOfTravelExtended);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForDirectionOfTravelExtended);

                JLabel labelForAcknowledgementRequest = new JLabel("Acknowledgement request");
                JTextField textFieldForAcknowledgementRequest = new JTextField("0", 1);
                textFieldForAcknowledgementRequest.setEditable(false);
                JCheckBox checkBoxForForAcknowledgementRequest = new JCheckBox("Changing");
                checkBoxForForAcknowledgementRequest.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForAcknowledgementRequest);
                dialogForLongLIPWithReceiveLevel.add(textFieldForAcknowledgementRequest);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForForAcknowledgementRequest);

                JLabel labelForTypeOfAdditionalData = new JLabel("Type of additional data");
                JTextField textFieldForTypeOfAdditionalData = new JTextField("0", 1);
                textFieldForTypeOfAdditionalData.setEditable(false);
                JCheckBox checkBoxForTypeOfAdditionalData = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForTypeOfAdditionalData);
                dialogForLongLIPWithReceiveLevel.add(textFieldForTypeOfAdditionalData);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForTypeOfAdditionalData);

                JLabel labelForReasonForSending = new JLabel("Reason for sending");
                JTextField textFieldForReasonForSending = new JTextField("129", 1);
                JCheckBox checkBoxForReasonForSending = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForReasonForSending);
                dialogForLongLIPWithReceiveLevel.add(textFieldForReasonForSending);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForReasonForSending);

                JLabel labelForType5ElementIdentifier = new JLabel("Type 5 element identifier");
                JTextField textFieldForType5ElementIdentifier = new JTextField("1", 1);
                textFieldForType5ElementIdentifier.setEditable(false);
                JCheckBox checkBoxForType5ElementIdentifier = new JCheckBox("Changing");
                checkBoxForTypeOfAdditionalData.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForType5ElementIdentifier);
                dialogForLongLIPWithReceiveLevel.add(textFieldForType5ElementIdentifier);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForType5ElementIdentifier);

                JLabel labelForType5ElementLength = new JLabel("Type 5 element length");
                JTextField textFieldForType5Type5ElementLength = new JTextField("0", 1);
                textFieldForType5Type5ElementLength.setEditable(false);
                JCheckBox checkBoxForType5ElementLength = new JCheckBox("Changing");
                checkBoxForType5ElementLength.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForType5ElementLength);
                dialogForLongLIPWithReceiveLevel.add(textFieldForType5Type5ElementLength);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForType5ElementLength);

                JLabel labelForType5ElementLengthExtension = new JLabel("Type 5 element length extension");
                JTextField textFieldForType5ElementLengthExtension = new JTextField("9", 1);
                textFieldForType5ElementLengthExtension.setEditable(false);
                JCheckBox checkBoxForType5ElementLengthExtension = new JCheckBox("Changing");
                checkBoxForType5ElementLengthExtension.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForType5ElementLengthExtension);
                dialogForLongLIPWithReceiveLevel.add(textFieldForType5ElementLengthExtension);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForType5ElementLengthExtension);

                JLabel labelForTypeOfMessageFromDevice = new JLabel("Type of message from device");
                JTextField textFieldForTypeOfMessageFromDevice = new JTextField("0", 1);
                textFieldForTypeOfMessageFromDevice.setEditable(false);
                JCheckBox checkBoxForTypeOfMessageFromDevice = new JCheckBox("Changing");
                checkBoxForTypeOfMessageFromDevice.setEnabled(false);
                dialogForLongLIPWithReceiveLevel.add(labelForTypeOfMessageFromDevice);
                dialogForLongLIPWithReceiveLevel.add(textFieldForTypeOfMessageFromDevice);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForTypeOfMessageFromDevice);

                JLabel labelForTypeOfDevice = new JLabel("Type of device");
                JTextField textFieldForTypeOfDevice = new JTextField("0", 1);
                JCheckBox checkBoxForTypeOfDevice = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForTypeOfDevice);
                dialogForLongLIPWithReceiveLevel.add(textFieldForTypeOfDevice);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForTypeOfDevice);

                JLabel labelForMeasurementFrequency = new JLabel("Measurement frequency");
                JTextField textFieldForMeasurementFrequency = new JTextField("1", 1);
                JCheckBox checkBoxForMeasurementFrequency = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForMeasurementFrequency);
                dialogForLongLIPWithReceiveLevel.add(textFieldForMeasurementFrequency);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForMeasurementFrequency);

                JLabel labelForChannelCode = new JLabel("Channel code");
                JTextField textFieldForChannelCode = new JTextField("1000", 1);
                JCheckBox checkBoxForChannelCode = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForChannelCode);
                dialogForLongLIPWithReceiveLevel.add(textFieldForChannelCode);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForChannelCode);

                JLabel labelForFrequencyOffsetCode = new JLabel("Frequency Offset Code");
                JTextField textFieldForFrequencyOffsetCode = new JTextField("3", 1);
                JCheckBox checkBoxForFrequencyOffsetCode = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForFrequencyOffsetCode);
                dialogForLongLIPWithReceiveLevel.add(textFieldForFrequencyOffsetCode);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForFrequencyOffsetCode);

                JLabel labelForSignalReceptionLevel = new JLabel("Signal reception level");
                JTextField textFieldForSignalReceptionLevel = new JTextField("50", 1);
                JCheckBox checkBoxForSignalReceptionLevel = new JCheckBox("Changing");
                dialogForLongLIPWithReceiveLevel.add(labelForSignalReceptionLevel);
                dialogForLongLIPWithReceiveLevel.add(textFieldForSignalReceptionLevel);
                dialogForLongLIPWithReceiveLevel.add(checkBoxForSignalReceptionLevel);

                JButton buttonToAddLongLipType1WithReceiveLevel = new JButton("LIP with receive level");
                dialogForLongLIPWithReceiveLevel.add(buttonToAddLongLipType1WithReceiveLevel);

                buttonToAddLongLipType1WithReceiveLevel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            checkIntValueFromString(textFieldForSSI.getText(), 0, 16777215, "SSI");
                            checkIntValueFromString(textFieldForBaseStationNumber.getText(), 1, 130,
                                    "BaseStationNumber");
                            checkDoubleValueFromString(textFieldForLongitude.getText(), -180.0, 179.0, "Longitude");
                            checkDoubleValueFromString(textFieldForLatitude.getText(), -90.0, 89.0, "Latitude");
                            checkIntValueFromString(textFieldForHorizontalPositionUncertainty.getText(), 0, 63,
                                    "Horizontal position uncertainty");
                            checkIntValueFromString(textFieldForLocationAltitude.getText(), 0, 2047, "Location " +
                                    "altitude");
                            checkIntValueFromString(textFieldForHorizontalVelocity.getText(), 0, 127, "Horizontal " +
                                    "velocity");
                            checkIntValueFromString(textFieldForDirectionOfTravelExtended.getText(), 0, 255,
                                    "Direction Of travel extended");
                            checkIntValueFromString(textFieldForReasonForSending.getText(), 0, 255, "Reason for " +
                                    "sending");

                            checkIntValueFromString(textFieldForTypeOfDevice.getText(), 0, 7, "Type of device");
                            checkIntValueFromString(textFieldForMeasurementFrequency.getText(), 0, 1, "Measurement " +
                                    "frequency");
                            checkIntValueFromString(textFieldForChannelCode.getText(), 0, 4095, "Channel code");
                            checkIntValueFromString(textFieldForFrequencyOffsetCode.getText(), 0, 3, "Frequency " +
                                    "Offset Code");
                            checkIntValueFromString(textFieldForSignalReceptionLevel.getText(), 0, 255, "Signal " +
                                    "reception level");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        Map<Integer, Boolean> changeMap = new HashMap<Integer, Boolean>();
                        for (int i = 0; i < 12; i++) {
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

                        if (checkBoxForTypeOfDevice.isSelected()) {
                            changeMap.replace(8, true);
                        }

                        if (checkBoxForMeasurementFrequency.isSelected()) {
                            changeMap.replace(9, true);
                        }

                        if (checkBoxForChannelCode.isSelected()) {
                            changeMap.replace(10, true);
                        }

                        if (checkBoxForFrequencyOffsetCode.isSelected()) {
                            changeMap.replace(11, true);
                        }

                        if (checkBoxForSignalReceptionLevel.isSelected()) {
                            changeMap.replace(12, true);
                        }


                        LongLipType1WithRecieveLevel longLipType1WithRecieveLevel = new LongLipType1WithRecieveLevel();
                        longLipType1WithRecieveLevel.withSSI(textFieldForSSI.getText())
                                .withLongitude(textFieldForLongitude.getText())
                                .withLatitude(textFieldForLatitude.getText())
                                .withHorizontal_position_uncertainty(textFieldForHorizontalPositionUncertainty.getText())
                                .withLocation_altitude(textFieldForLocationAltitude.getText())
                                .withHorizontal_velocity(textFieldForHorizontalVelocity.getText())
                                .withDirection_of_travel_extended(textFieldForDirectionOfTravelExtended.getText())
                                .withReason_for_sending(textFieldForReasonForSending.getText())
                                .withType_of_device(textFieldForTypeOfDevice.getText())
                                .withMeasurement_frequency(textFieldForMeasurementFrequency.getText())
                                .withChannel_code(textFieldForChannelCode.getText())
                                .withFrequency_offset_code(textFieldForFrequencyOffsetCode.getText())
                                .withSignal_reception_level(textFieldForSignalReceptionLevel.getText())
                                .withBaseStationNumber(textFieldForBaseStationNumber.getText())
                                .withChangeMap(changeMap)
                                .initValuesFromUI();

                        listOfUDPMessages.add(longLipType1WithRecieveLevel);

                        dialogForLongLIPWithReceiveLevel.dispose();
                        listModel.addElement("Lip with receive " +
                                "level " + textFieldForSSI.getText() + " " + convertChangeMapToString(changeMap));
                    }
                });

                dialogForLongLIPWithReceiveLevel.setVisible(true);

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
                buttonAddTelemetryOfDeviceData.setEnabled(false);
                buttonAddLipWithReceiveLevel.setEnabled(false);
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
                buttonAddTelemetryOfDeviceData.setEnabled(true);
                buttonAddLipWithReceiveLevel.setEnabled(true);
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
                buttonAddTelemetryOfDeviceData.setEnabled(false);
                buttonAddLipWithReceiveLevel.setEnabled(false);
            }
        });

    }

    private String convertChangeMapToString(Map<Integer, Boolean> changeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < changeMap.size(); i++) {
            stringBuilder.append(changeMap.get(i) ? 1 : 0);
        }
        return stringBuilder.toString();
    }

    private void checkIntValueFromString(String checkString, int beginRange, int endRange, String nameOfField) throws Exception {
        try {
            int checkNumber = Integer.parseInt(checkString);

            if ((checkNumber < beginRange) || (checkNumber > endRange)) {
                String warning =
                        nameOfField + " = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]";
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

    private void checkDoubleValueFromString(String checkString, double beginRange, double endRange,
                                            String nameOfField) throws Exception {
        try {
            double checkNumber = Double.parseDouble(checkString);
            if ((checkNumber < beginRange) || (checkNumber > endRange)) {
                String warning =
                        nameOfField + " = " + checkNumber + " cannot be out of range [" + beginRange + ".." + endRange + "]";
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
        //Помещаем поцентру относительно главного фрейма
        dialog.setLocationRelativeTo(getContentPane());
        return dialog;
    }

    private JDialog createWarningDialog(String messageInDialog) {
        JDialog dialog = new JDialog(this, "Ошибка", true);
        JLabel dialogLabel = new JLabel(messageInDialog);
        dialog.add(dialogLabel);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 90);
        //Помещаем поцентру относительно главного фрейма
        dialog.setLocationRelativeTo(getContentPane());
        return dialog;
    }

    public static void main(String[] args) {
        new UI();
    }
}
