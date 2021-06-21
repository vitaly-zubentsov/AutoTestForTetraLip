import java.util.Map;

public class LongLipType1WithRecieveLevel implements UDPMessage {

    private Map<Integer, Boolean> changeMap;
    private UDPMessageHelper udpMessageHelper = new UDPMessageHelper();

    private String SSI;
    private static String BASE_STATION_NUMBER = "00000000000000000000000000001010";
    private static String LONGITUDE_FROM_SERVER_OV = "00000000000000000000000000000000";
    private static String LATITUDE_FROM_SERVER_OV = "00000000000000000000000000000000";
    private static String LENGTH_IN_BITS = "00000000000000000000000010101000"; // равняется 161 бит + 7 бит padding = 168 бит (21 байт)
    private static String PDU_HEADERS = "00001010";
    private static String PDU_TYPE = "01";
    private static String PDU_TYPE_EXTENSIONS = "0011";
    private static String TIME_DATA = "00";
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
    private static String TYPE_5_ELEMENT_IDENTIFIER = "00001";
    private static String TYPE_5_ELEMENT_LENGTH = "000000";
    private static String TYPE_5_ELEMENT_LENGTH_EXTENSION = "0001001";
    private static String TYPE_OF_MESSAGE_FROM_DEVICE = "01";
    private String type_of_device;
    private String measurement_frequency;
    private String channel_code;
    private String frequency_offset_code;
    private String signal_reception_level;

    private static String PDU_TAIL = "1111111"; // В дополнительном сообщении заполнение осуществляется единицами

    private String binSSI;

    private String binLongitude;

    private String binLatitude;
    private String binHorizontalPositionUncertainty;
    private String binLocationAltitude;
    private String binHorizontalVelocity;
    private String binDirectionOfTravelExtended;
    private String binReasonForSending;
    private String binTypeOfDevice;

    private String binMeasurementFrequency;

    private String binChannelCode;
    private String binFrequencyOffsetCode;
    private String binSignalReceptionLevel;

    public void initValuesFromUI() {
        binSSI = udpMessageHelper.convertDecStringNumberToBinStringNumber(SSI, 32);
        binLongitude = udpMessageHelper.calculateLipLongitudeFromDecLongitude(longitude);
        binLatitude = udpMessageHelper.calculateLipLatitudeFromDecLatitude(latitude);
        binHorizontalPositionUncertainty = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_position_uncertainty, 6);
        binLocationAltitude = udpMessageHelper.convertDecStringNumberToBinStringNumber(location_altitude, 12);
        binHorizontalVelocity = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_velocity, 7);
        binDirectionOfTravelExtended = udpMessageHelper.convertDecStringNumberToBinStringNumber(direction_of_travel_extended, 8);
        binReasonForSending = udpMessageHelper.convertDecStringNumberToBinStringNumber(reason_for_sending, 8);

        binTypeOfDevice = udpMessageHelper.convertDecStringNumberToBinStringNumber(type_of_device, 3);
        binMeasurementFrequency = udpMessageHelper.convertDecStringNumberToBinStringNumber(measurement_frequency, 1);
        binChannelCode = udpMessageHelper.convertDecStringNumberToBinStringNumber(channel_code, 12);
        binFrequencyOffsetCode = udpMessageHelper.convertDecStringNumberToBinStringNumber(frequency_offset_code, 2);
        binSignalReceptionLevel = udpMessageHelper.convertDecStringNumberToBinStringNumber(signal_reception_level, 8);

    }

    public byte[] getUdpMessage() {
        String udpMessage = udpPacketsCounter.getPacket_counter() +
                binSSI +
                BASE_STATION_NUMBER +
                LONGITUDE_FROM_SERVER_OV +
                LATITUDE_FROM_SERVER_OV +
                LENGTH_IN_BITS +
                PDU_HEADERS +
                PDU_TYPE +
                PDU_TYPE_EXTENSIONS +
                TIME_DATA +
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
                TYPE_5_ELEMENT_IDENTIFIER +
                TYPE_5_ELEMENT_LENGTH +
                TYPE_5_ELEMENT_LENGTH_EXTENSION +
                TYPE_OF_MESSAGE_FROM_DEVICE +
                binTypeOfDevice +
                binMeasurementFrequency +
                binChannelCode +
                binFrequencyOffsetCode +
                binSignalReceptionLevel +
                PDU_TAIL;
        changeValuesOfElementsLipMessage();
        return udpMessageHelper.convertBinStringToByteArray(udpMessage);
    }


    public void changeValuesOfElementsLipMessage() {
        if (changeMap.get(0)) {
            binSSI = udpMessageHelper.addTheNumberToBinStringForSSI(binSSI, 1, 8000);
        }

        if (changeMap.get(1)) {
            binLongitude = udpMessageHelper.addTheNumberToBinString(binLongitude, 10, 33554431);
        }
        if (changeMap.get(2)) {
            binLatitude = udpMessageHelper.addTheNumberToBinString(binLatitude, 10, 16777215);
        }
        if (changeMap.get(3)) {
            binHorizontalPositionUncertainty = udpMessageHelper.addTheNumberToBinString(binHorizontalPositionUncertainty, 1, 7);
        }
        if (changeMap.get(4)) {
            binLocationAltitude = udpMessageHelper.addTheNumberToBinString(binLocationAltitude, 1, 2047);
        }
        if (changeMap.get(5)) {
            binHorizontalVelocity = udpMessageHelper.addTheNumberToBinString(binHorizontalVelocity, 1, 127);
        }
        if (changeMap.get(6)) {
            binDirectionOfTravelExtended = udpMessageHelper.addTheNumberToBinString(binDirectionOfTravelExtended, 1, 255);
        }
        if (changeMap.get(7)) {
            binReasonForSending = udpMessageHelper.addTheNumberToBinString(binReasonForSending, 1, 255);
        }
        if (changeMap.get(8)) {
            binTypeOfDevice = udpMessageHelper.addTheNumberToBinString(binTypeOfDevice, 1, 7);
        }
        if (changeMap.get(9)) {
            binMeasurementFrequency = udpMessageHelper.addTheNumberToBinString(binMeasurementFrequency, 1, 1);
        }
        if (changeMap.get(10)) {
            binChannelCode = udpMessageHelper.addTheNumberToBinString(binChannelCode, 1, 4095);
        }
        if (changeMap.get(11)) {
            binFrequencyOffsetCode = udpMessageHelper.addTheNumberToBinString(binFrequencyOffsetCode, 1, 3);
        }
        if (changeMap.get(12)) {
            binSignalReceptionLevel = udpMessageHelper.addTheNumberToBinString(binSignalReceptionLevel, 1, 255);
        }
        udpPacketsCounter.setPacket_counter(udpMessageHelper.addTheNumberToBinString(udpPacketsCounter.getPacket_counter(), 1, 1294967295));
    }

    public LongLipType1WithRecieveLevel withChangeMap(Map<Integer, Boolean> changeMap) {
        this.changeMap = changeMap;
        return this;
    }

    public LongLipType1WithRecieveLevel withSSI(String SSI) {
        this.SSI = SSI;
        return this;
    }

    public LongLipType1WithRecieveLevel withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public LongLipType1WithRecieveLevel withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public LongLipType1WithRecieveLevel withHorizontal_position_uncertainty(String horizontal_position_uncertainty) {
        this.horizontal_position_uncertainty = horizontal_position_uncertainty;
        return this;
    }

    public LongLipType1WithRecieveLevel withLocation_altitude(String location_altitude) {
        this.location_altitude = location_altitude;
        return this;
    }

    public LongLipType1WithRecieveLevel withHorizontal_velocity(String horizontal_velocity) {
        this.horizontal_velocity = horizontal_velocity;
        return this;
    }

    public LongLipType1WithRecieveLevel withDirection_of_travel_extended(String direction_of_travel_extended) {
        this.direction_of_travel_extended = direction_of_travel_extended;
        return this;
    }

    public LongLipType1WithRecieveLevel withReason_for_sending(String reason_for_sending) {
        this.reason_for_sending = reason_for_sending;
        return this;
    }

    public LongLipType1WithRecieveLevel withType_of_device(String type_of_device) {
        this.type_of_device = type_of_device;
        return this;
    }

    public LongLipType1WithRecieveLevel withMeasurement_frequency(String measurement_frequency) {
        this.measurement_frequency = measurement_frequency;
        return this;
    }

    public LongLipType1WithRecieveLevel withChannel_code(String channel_code) {
        this.channel_code = channel_code;
        return this;
    }

    public LongLipType1WithRecieveLevel withFrequency_offset_code(String frequency_offset_code) {
        this.frequency_offset_code = frequency_offset_code;
        return this;
    }

    public LongLipType1WithRecieveLevel withSignal_reception_level(String signal_reception_level) {
        this.signal_reception_level = signal_reception_level;
        return this;
    }
}
