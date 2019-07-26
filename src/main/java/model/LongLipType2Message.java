package model;

import java.util.Map;

public class LongLipType2Message implements UDPMessage {

    private Map<Integer, Boolean> changeMap;
    private UDPMessageHelper udpMessageHelper = new UDPMessageHelper();

    private String SSI;
    private static String LONGITUDE_FROM_SERVER_OV = "00000000000000000000000000000000";
    private static String LATITUDE_FROM_SERVER_OV = "00000000000000000000000000000000";
    private static String LENGTH_IN_BITS = "00000000000000000000000001110110"; //в лонг лип типе 2 = 118 бит
    private static String PDU_HEADERS = "00001010";
    private static String PDU_TYPE = "01";
    private static String PDU_TYPE_EXTENSIONS = "0011";
    private static String TIME_DATA = "01";
    private String time_elapsed;
    private static String LOCATION_SHAPE = "0101";
    private String longitude;
    private String latitude;
    private String horizontal_position_uncertainty;
    private String location_altitude;
    private static String VELOCITY_TYPE = "101";
    private String horizontal_velocity;
    private String direction_of_travel_extended;
    private String ACKNOWLEDGEMENT_REQUEST = "0";
    private static String TYPE_OF_Additional_DATA = "0";
    private String reason_for_sending;

    private static String PDU_TAIL = "00000";

    private String binSSI;

    private String binLongitude;
    private String binLatitude;
    private String binHorizontalPositionUncertainty;
    private String binLocationAltitude;
    private String binHorizontalVelocity;
    private String binDirectionOfTravelExtended;
    private String binReasonForSending;
    private String binTimeElapsed;


    public void initValuesFromUI() {
        binSSI = udpMessageHelper.convertDecStringNumberToBinStringNumber(SSI, 32);
        binTimeElapsed = udpMessageHelper.convertDecStringNumberToBinStringNumber(time_elapsed,2);
        binLongitude = udpMessageHelper.calculateLipLongitudeFromDecLongitude(longitude);
        binLatitude = udpMessageHelper.calculateLipLatitudeFromDecLatitude(latitude);
        binHorizontalPositionUncertainty = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_position_uncertainty, 6);
        binLocationAltitude = udpMessageHelper.convertDecStringNumberToBinStringNumber(location_altitude, 12);
        binHorizontalVelocity = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_velocity, 7);
        binDirectionOfTravelExtended = udpMessageHelper.convertDecStringNumberToBinStringNumber(direction_of_travel_extended, 8);
        binReasonForSending = udpMessageHelper.convertDecStringNumberToBinStringNumber(reason_for_sending, 8);

    }

    public byte[] getUdpMessage() {
        String udpMessage = udpPacketsCounter.getPacket_counter() +
                binSSI +
                LONGITUDE_FROM_SERVER_OV +
                LATITUDE_FROM_SERVER_OV +
                LENGTH_IN_BITS +
                PDU_HEADERS +
                PDU_TYPE +
                PDU_TYPE_EXTENSIONS +
                TIME_DATA +
                binTimeElapsed +
                LOCATION_SHAPE +
                binLongitude +
                binLatitude +
                binHorizontalPositionUncertainty +
                binLocationAltitude +
                VELOCITY_TYPE +
                binHorizontalVelocity +
                binDirectionOfTravelExtended +
                ACKNOWLEDGEMENT_REQUEST +
                TYPE_OF_Additional_DATA +
                binReasonForSending +
                PDU_TAIL;
        changeValuesOfElementsLipMessage();
        return udpMessageHelper.convertBinStringToByteArray(udpMessage);
    }


    public void changeValuesOfElementsLipMessage() {
        if (changeMap.get(0)) {
            binSSI = udpMessageHelper.addTheNumberToBinStringForSSI(binSSI, 1, 8000);
        }

        if (changeMap.get(1)) {
            binTimeElapsed = udpMessageHelper.addTheNumberToBinString(binTimeElapsed, 1, 3);
        }
        if (changeMap.get(2)) {
            binLongitude = udpMessageHelper.addTheNumberToBinString(binLongitude, 10, 33554431);
        }
        if (changeMap.get(3)) {
            binLatitude = udpMessageHelper.addTheNumberToBinString(binLatitude, 10, 16777215);
        }
        if (changeMap.get(4)) {
            binHorizontalPositionUncertainty = udpMessageHelper.addTheNumberToBinString(binHorizontalPositionUncertainty, 1, 7);
        }
        if (changeMap.get(5)) {
            binLocationAltitude = udpMessageHelper.addTheNumberToBinString(binLocationAltitude, 1, 2047);
        }
        if (changeMap.get(6)) {
            binHorizontalVelocity = udpMessageHelper.addTheNumberToBinString(binHorizontalVelocity, 1, 127);
        }
        if (changeMap.get(7)) {
            binDirectionOfTravelExtended = udpMessageHelper.addTheNumberToBinString(binDirectionOfTravelExtended, 1, 255);
        }
        if (changeMap.get(8)) {
            binReasonForSending = udpMessageHelper.addTheNumberToBinString(binReasonForSending, 1, 255);
        }
        udpPacketsCounter.setPacket_counter(udpMessageHelper.addTheNumberToBinString(udpPacketsCounter.getPacket_counter(), 1, 1294967295));
    }


    public LongLipType2Message withChangeMap(Map<Integer, Boolean> changeMap) {
        this.changeMap = changeMap;
        return this;
    }

    public LongLipType2Message withSSI(String SSI) {
        this.SSI = SSI;
        return this;
    }

    public LongLipType2Message withTimeElapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
        return this;
    }

    public LongLipType2Message withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public LongLipType2Message withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public LongLipType2Message withHorizontal_position_uncertainty(String horizontal_position_uncertainty) {
        this.horizontal_position_uncertainty = horizontal_position_uncertainty;
        return this;
    }

    public LongLipType2Message withLocation_altitude(String location_altitude) {
        this.location_altitude = location_altitude;
        return this;
    }

    public LongLipType2Message withHorizontal_velocity(String horizontal_velocity) {
        this.horizontal_velocity = horizontal_velocity;
        return this;
    }

    public LongLipType2Message withDirection_of_travel_extended(String direction_of_travel_extended) {
        this.direction_of_travel_extended = direction_of_travel_extended;
        return this;
    }

    public LongLipType2Message withReason_for_sending(String reason_for_sending) {
        this.reason_for_sending = reason_for_sending;
        return this;
    }
}
