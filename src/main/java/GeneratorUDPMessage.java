public class GeneratorUDPMessage {

    public String
            packet_counter = "00000000000000000000000011011101",
            ssi,
            longitude_from_tetra_server = "00000000001101011000111110010111",
            latitude_from_tetra_server = "00000000010011110101011001001111",
            length_in_bits = "00000000 00000000 00000000 01011101",
            pdu_headers,
            pdu_type,
            time_elapsed,
            longitude,
            latitude,
            position_error = "010",
            horizontal_velocity = "0000001",
            direction_of_travel = "0111",
            type_of_additional_data = "0",
            reason_for_sending,
    pdu_tail = "000001111";


    public String getUdpMesage() {
        ssi = convertDecStringNumberToHexStringNumber(ssi, 32);
        latitude = calculateLipHexLatitudeFromDecLatitude(latitude);
        longitude = calculateLipHexLongitudeFromDecLongitude(longitude);
        String udpMessage = packet_counter +
                ssi +
                longitude_from_tetra_server +
                latitude_from_tetra_server +
                length_in_bits +
                pdu_headers +
                pdu_type +
                time_elapsed +
                longitude +
                latitude +
                position_error +
                horizontal_velocity +
                direction_of_travel +
                type_of_additional_data +
                reason_for_sending +
                pdu_tail + "000";

        return udpMessage;
    }

    private String convertDecStringNumberToHexStringNumber(String decNumber, int bitInHexNumbre) {

        StringBuilder hex = new StringBuilder(Integer.toHexString(Integer.parseInt(decNumber)));
        while (hex.length() < bitInHexNumbre) {
            hex.insert(0, "0");
        }
        return hex.toString();
    }

    private String calculateLipHexLatitudeFromDecLatitude (String latitude){
        double decLatitude =  Double.parseDouble(latitude);
        double x = 180 / 16777216;
        long decLipLatitude = Math.round(decLatitude / x);

        return convertDecStringNumberToHexStringNumber(Long.toString(decLipLatitude), 24);
    }

    private String calculateLipHexLongitudeFromDecLongitude (String longitude){
        double decLatitude =  Double.parseDouble(longitude);
        double x = 360 / 16777216;
        long decLipLatitude = Math.round(decLatitude / x);
        return convertDecStringNumberToHexStringNumber(Long.toString(decLipLatitude), 25);
    }



}
