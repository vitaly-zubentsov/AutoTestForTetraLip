package model;

import static java.lang.Integer.parseInt;

public class ShortLipMessage {

    // private static byte[] ALIVE_MESSAGE = {0,0,9,121,};
    private String
            packet_counter = "00000000000000000000000000000001";
    private String ssi;
    private String longitude_from_tetra_server = "00000000001101011000111110010111";
    private String latitude_from_tetra_server = "00000000010011110101011001001111";
    private String length_in_bits = "00000000000000000000000001011101";  //надо научиться определять потом.
    private static String PDU_HEADERS = "00001010";
    private String pdu_type;
    private String time_elapsed;
    private String longitude;
    private String latitude;
    private String position_error = "010"; // в клиенте не отображается, есть смысл сделать входным
    private String horizontal_velocity = "0000001"; //  надо исследовать, если отображается в клиенте
    private String direction_of_travel = "0111"; //  надо исследовать, если отображается в клиенте

    private static String type_of_additional_data = "0"; // в short lip на данный момент других решений не планируется.

    private String reason_for_sending;
    private String pdu_tail = "000001111"; //этот хвостик появляется только для short lip, какая то ошибка в сервере ОВ

    public byte[] getUdpMessage() {
        String binSSI = convertDecStringNumberToBinStringNumber(ssi, 32);
        String binPduType = convertDecStringNumberToBinStringNumber(pdu_type, 2);
        String binTimeElapsed = convertDecStringNumberToBinStringNumber(time_elapsed, 2);
        String binLongitude = calculateLipLongitudeFromDecLongitude(longitude);
        String binLatitude = calculateLipLatitudeFromDecLatitude(latitude);
        String binReasonForSending = convertDecStringNumberToBinStringNumber(reason_for_sending, 8);
        String udpMessage = packet_counter +
                binSSI +
                longitude_from_tetra_server +
                latitude_from_tetra_server +
                length_in_bits +
                PDU_HEADERS +
                binPduType +
                binTimeElapsed +
                binLongitude +
                binLatitude +
                position_error +
                horizontal_velocity +
                direction_of_travel +
                type_of_additional_data +
                binReasonForSending +
                pdu_tail + "000";
        addTheNumberToBinString(packet_counter, 1);
        return convertBinStringToByteArray(udpMessage);
    }

    public byte[] getUdpAliveMessage() {
        String udpAliveMessage = packet_counter + "00000000000000000000000000000000";
        addTheNumberToBinString(packet_counter, 1);
        return convertBinStringToByteArray(udpAliveMessage);
    }

    private void addTheNumberToBinString(String binString, long number) {

        long numberFromBinString = convertBinStringToDecNumber(binString);
        packet_counter = convertDecStringNumberToBinStringNumber(numberFromBinString + number, binString.length());
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

   /* byte [] getAliveMessage() {
        return ALIVE_MESSAGE;
    }*/

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


        String hexString = "";

        while (binString.length() > 3) {
            hexString += Integer.toHexString(parseInt(binString.substring(0, 4), 2));
            binString = binString.substring(4);
        }
        // hexString += Integer.toHexString(Integer.parseInt(binString.substring(0,3), 2));
        return hexString;
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


    public ShortLipMessage withPdu_type(String pdu_type) {
        this.pdu_type = pdu_type;
        return this;
    }

    public ShortLipMessage withTime_elapsed(String time_elapsed) {
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

    public ShortLipMessage withReason_for_sending(String reason_for_sending) {
        this.reason_for_sending = reason_for_sending;
        return this;
    }
}
