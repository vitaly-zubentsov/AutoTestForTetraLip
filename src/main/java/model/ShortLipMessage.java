package model;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class ShortLipMessage {

    private String packet_counter = "00000000000000000000000001000001";
    private String ssi;
    private static String LENGTH_IN_BITS = "00000000000000000000000001011101";  //сделать счетчик количества бит, хотя по факту он всегда будет определён.
    private static String PDU_HEADERS = "00001010";
    private static String PDU_TYPE = "00";
    private String time_elapsed;
    private String longitude;
    private String latitude;
    private String position_error; // в клиенте не отображается, есть смысл сделать входным
    private String horizontal_velocity; //  надо исследовать, если отображается в клиенте
    private String direction_of_travel; //  надо исследовать, если отображается в клиенте
    private static String TYPE_OF_Additional_DATA = "0"; // в short lip на данный момент других решений не планируется.
    private String reason_for_sending;
    private String pdu_tail = "000001111"; //этот хвостик появляется только для short lip, какая то ошибка в сервере ОВ

    private String binSSI;
    private String binTimeElapsed;
    private String binLongitude;
    private String binLatitude;
    private String binPositionError;
    private String binHorizontalVelocity;
    private String binDirectionOfTravel;
    private String binReasonForSending;

    private Map<Integer, Boolean> changeMap;

    public void initValuesFromUI() {
        binSSI = convertDecStringNumberToBinStringNumber(ssi, 32);
        binTimeElapsed = convertDecStringNumberToBinStringNumber(time_elapsed, 2);
        binLongitude = calculateLipLongitudeFromDecLongitude(longitude);
        binLatitude = calculateLipLatitudeFromDecLatitude(latitude);
        binPositionError = convertDecStringNumberToBinStringNumber(position_error, 3);
        binHorizontalVelocity = convertDecStringNumberToBinStringNumber(horizontal_velocity, 7);
        binDirectionOfTravel = convertDecStringNumberToBinStringNumber(direction_of_travel, 4);
        binReasonForSending = convertDecStringNumberToBinStringNumber(reason_for_sending, 8);

    }

    public byte[] getUdpMessage() {
        String udpMessage = packet_counter +
                binSSI +
                "0000000" + binLongitude +
                "000000" + binLatitude +
                LENGTH_IN_BITS +
                PDU_HEADERS +
                PDU_TYPE +
                binTimeElapsed +
                binLongitude +
                binLatitude +
                binPositionError +
                binHorizontalVelocity +
                binDirectionOfTravel +
                TYPE_OF_Additional_DATA +
                binReasonForSending +
                pdu_tail +
                "000"; // без этого хвостика почему то сервер не может распарсить сообщение

        changeValuesOfElementsLipMessage();
        return convertBinStringToByteArray(udpMessage);
    }

    private void changeValuesOfElementsLipMessage() {
        if (changeMap.get(0)) {
            binSSI = addTheNumberToBinString(binSSI,1);
        }
        if (changeMap.get(1)) {
            binTimeElapsed = addTheNumberToBinString(binSSI,1);
        }
        if (changeMap.get(2)) {
            binLongitude = addTheNumberToBinString(binLongitude,10);
        }
        if (changeMap.get(3)) {
            binLatitude = addTheNumberToBinString(binLatitude,10);
        }
        if (changeMap.get(4)) {
            binPositionError = addTheNumberToBinString(binPositionError,1);
        }
        if (changeMap.get(5)) {
            binHorizontalVelocity = addTheNumberToBinString(binHorizontalVelocity,1);
        }
        if (changeMap.get(6)) {
            binDirectionOfTravel = addTheNumberToBinString(binDirectionOfTravel,1);
        }
        if (changeMap.get(7)) {
            binReasonForSending = addTheNumberToBinString(binReasonForSending,1);
        }


        packet_counter = addTheNumberToBinString(packet_counter, 1);
        binLongitude = addTheNumberToBinString(binLongitude, 10);
        binLatitude = addTheNumberToBinString(binLatitude, 10);
        System.out.println(binLatitude);
    }


    public byte[] getUdpAliveMessage() {
        String udpAliveMessage = packet_counter + "00000000000000000000000000000000";
        addTheNumberToBinString(packet_counter, 1);
        return convertBinStringToByteArray(udpAliveMessage);
    }

    private String addTheNumberToBinString(String binString, long number) {

        long numberFromBinString = convertBinStringToDecNumber(binString);
        return convertDecStringNumberToBinStringNumber(numberFromBinString + number, binString.length());
    }

    private String convertDecStringNumberToBinStringNumber(long number, int lengthOfBinString) {

        StringBuilder binString = new StringBuilder(Long.toBinaryString(number));
        while (binString.length() < lengthOfBinString) {
            binString.insert(0, "0");
        }
        return binString.toString();
    }

    private int convertBinStringToDecNumber(String binString) {
        return Integer.parseInt(binString, 2);
    }

    private byte[] convertBinStringToByteArray(String binString) {

        float numberOfDecNumbersFromBinString = binString.length() / 8;
        int lengthOfByteArrayFromBinString = (int) Math.ceil(numberOfDecNumbersFromBinString);
        byte[] byteArray = new byte[lengthOfByteArrayFromBinString];
        for (int i = 0; i < lengthOfByteArrayFromBinString; ++i) {
            int numberFromBinString = Integer.parseInt(binString.substring(0, 8), 2);
            if (numberFromBinString < 128) {
                byteArray[i] = (byte) numberFromBinString;
            } else {
                byteArray[i] = (byte) (numberFromBinString - 256);
            }
            binString = binString.substring(8);
        }
        return byteArray;
    }

    private String convertDecStringNumberToBinStringNumber(String decNumber, int bitInBinNumber) {

        String x = Integer.toBinaryString(Integer.parseInt(decNumber));
        StringBuilder binString = new StringBuilder(x);
        while (binString.length() < bitInBinNumber) {
            binString.insert(0, "0");
        }
        return binString.toString();
    }


    private String convertBinStringToHexString(String binString) {


        StringBuilder hexString = new StringBuilder();

        while (binString.length() > 3) {
            hexString.append(Integer.toHexString(parseInt(binString.substring(0, 4), 2)));
            binString = binString.substring(4);
        }
        // hexString += Integer.toHexString(Integer.parseInt(binString.substring(0,3), 2));
        return hexString.toString();
    }

    private String calculateLipLatitudeFromDecLatitude(String latitude) {
        double decLatitude = Double.parseDouble(latitude);
        double x = 180.000000000 / 16777216.000000000;
        long decLipLatitude = Math.round(decLatitude / x);
        String f = Long.toString(decLipLatitude);
        return convertDecStringNumberToBinStringNumber(Long.toString(decLipLatitude), 24);
    }

    private String calculateLipLongitudeFromDecLongitude(String longitude) {
        double decLatitude = Double.parseDouble(longitude);
        double x = 180.000000000 / 16777216.000000000;
        long decLipLatitude = Math.round(decLatitude / x);
        String f = Long.toString(decLipLatitude);
        return convertDecStringNumberToBinStringNumber(Long.toString(decLipLatitude), 25);
    }


    public ShortLipMessage withSSI(String ssi) {
        this.ssi = ssi;
        return this;
    }


    public ShortLipMessage withTimeElapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
        return this;
    }

    public ShortLipMessage withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public ShortLipMessage withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public ShortLipMessage withReasonForSending(String reason_for_sending) {
        this.reason_for_sending = reason_for_sending;
        return this;
    }

    public ShortLipMessage withPositionError(String position_error) {
        this.position_error = position_error;
        return this;
    }

    public ShortLipMessage withHorizontalVelocity(String horizontal_velocity) {
        this.horizontal_velocity = horizontal_velocity;
        return this;
    }

    public ShortLipMessage withDirectionOfTravel(String direction_of_travel) {
        this.direction_of_travel = direction_of_travel;
        return this;
    }

    public ShortLipMessage withChangeMap(Map<Integer, Boolean> changeMap) {
        this.changeMap = changeMap;
        return this;
    }

}
