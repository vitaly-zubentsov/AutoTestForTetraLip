public class GeneratorUDPMessage {

    public String
            packet_counter = "00000000000000000000000011011101";
    public String ssi;
    public String longitude_from_tetra_server = "00000000001101011000111110010111";
    public String latitude_from_tetra_server = "00000000010011110101011001001111";
    public String length_in_bits = "00000000000000000000000001011101";
    public String pdu_headers = "00001010";
    public String pdu_type;
    public String time_elapsed;
    public String longitude;
    public String latitude;
    public String position_error = "010";
    public String horizontal_velocity = "0000001";
    public String direction_of_travel = "0111";

    public String type_of_additional_data = "0";

    public String reason_for_sending;
    public String pdu_tail = "000001111";

    public String getUdpMessage() {
        ssi = convertDecStringNumberToHexStringNumber(ssi, 32);
        //latitude = calculateLipHexLatitudeFromDecLatitude(latitude);
        //longitude = calculateLipHexLongitudeFromDecLongitude(longitude);
        pdu_type = convertDecStringNumberToHexStringNumber(pdu_type, 2);
        time_elapsed = convertDecStringNumberToHexStringNumber(time_elapsed, 2);
        reason_for_sending = convertDecStringNumberToHexStringNumber(reason_for_sending, 8);
        String udpMessage = packet_counter +
                ssi +
                longitude_from_tetra_server +
                latitude_from_tetra_server +
                length_in_bits +
                pdu_headers +
                pdu_type +
                time_elapsed +
                longitude_from_tetra_server + // поменять на нормальные значения
                longitude_from_tetra_server +
                position_error +
                horizontal_velocity +
                direction_of_travel +
                type_of_additional_data +
                reason_for_sending +
                pdu_tail + "000";
        System.out.println(udpMessage);
        System.out.println(convertBinStringToHexString(udpMessage));
        return convertBinStringToHexString(udpMessage);
    }

    private String convertDecStringNumberToHexStringNumber(String decNumber, int bitInHexNumber) {

        StringBuilder hex = new StringBuilder(Integer.toBinaryString(Integer.parseInt(decNumber)));
        while (hex.length() < bitInHexNumber) {
            hex.insert(0, "0");
        }
        return hex.toString();
    }

    private String convertBinStringToHexString(String binString) {
        String hexString = "";
        while (binString.length() > 8) {
            hexString += Integer.toString(Integer.parseInt(binString.substring(0,7), 2));
            binString = binString.substring(8);
        }
        hexString += Integer.toHexString(Integer.parseInt(binString.substring(0,7), 2));
        return hexString;
    }

    private String calculateLipHexLatitudeFromDecLatitude(String latitude) {
        double decLatitude = Double.parseDouble(latitude);
        double x = 180 / 16777216;
        long decLipLatitude = Math.round(decLatitude / x);

        return convertDecStringNumberToHexStringNumber(Long.toString(decLipLatitude), 24);
    }

    private String calculateLipHexLongitudeFromDecLongitude(String longitude) {
        double decLatitude = Double.parseDouble(longitude);
        double x = 360 / 16777216;
        long decLipLatitude = Math.round(decLatitude / x);
        return convertDecStringNumberToHexStringNumber(Long.toString(decLipLatitude), 25);
    }

    public GeneratorUDPMessage withSSI(String ssi) {
        this.ssi = ssi;
        return this;
    }

    public GeneratorUDPMessage withPdu_headers(String pdu_headers) {
        this.pdu_headers = pdu_headers;
        return this;
    }

    public GeneratorUDPMessage withPdu_type(String pdu_type) {
        this.pdu_type = pdu_type;
        return this;
    }

    public GeneratorUDPMessage withTime_elapsed(String time_elapsed) {
        this.time_elapsed = time_elapsed;
        return this;
    }

    public GeneratorUDPMessage withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public GeneratorUDPMessage withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public GeneratorUDPMessage withReason_for_sending(String reason_for_sending) {
        this.reason_for_sending = reason_for_sending;
        return this;
    }

}
