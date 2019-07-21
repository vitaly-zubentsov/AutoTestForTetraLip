package model;

import java.util.Map;

public class ShortLipMessage implements UDPMessage {

    private Map<Integer, Boolean> changeMap;
    private UDPMessageHelper udpMessageHelper = new UDPMessageHelper();

    private String ssi;
    private static String LENGTH_IN_BITS = "00000000000000000000000001011101";  //в short lip равно 93 битам
    private static String PDU_HEADERS = "00001010";
    private static String PDU_TYPE = "00";
    private String time_elapsed;
    private String longitude;
    private String latitude;
    private String position_error;
    private String horizontal_velocity;
    private String direction_of_travel;
    private static String TYPE_OF_Additional_DATA = "0";
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

    public void initValuesFromUI() {
        binSSI = udpMessageHelper.convertDecStringNumberToBinStringNumber(ssi, 32);
        binTimeElapsed = udpMessageHelper.convertDecStringNumberToBinStringNumber(time_elapsed, 2);
        binLongitude = udpMessageHelper.calculateLipLongitudeFromDecLongitude(longitude);
        binLatitude = udpMessageHelper.calculateLipLatitudeFromDecLatitude(latitude);
        binPositionError = udpMessageHelper.convertDecStringNumberToBinStringNumber(position_error, 3);
        binHorizontalVelocity = udpMessageHelper.convertDecStringNumberToBinStringNumber(horizontal_velocity, 7);
        binDirectionOfTravel = udpMessageHelper.convertDecStringNumberToBinStringNumber(direction_of_travel, 4);
        binReasonForSending = udpMessageHelper.convertDecStringNumberToBinStringNumber(reason_for_sending, 8);
    }

    public byte[] getUdpMessage() {
        String udpMessage = udpPacketsCounter.getPacket_counter() +
                binSSI +
                "0000000" + binLongitude +
                "00000000" + binLatitude +
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
        System.out.println(udpPacketsCounter.getPacket_counter());
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
            binPositionError = udpMessageHelper.addTheNumberToBinString(binPositionError, 1, 7);
        }
        if (changeMap.get(5)) {
            binHorizontalVelocity = udpMessageHelper.addTheNumberToBinString(binHorizontalVelocity, 1, 127);
        }
        if (changeMap.get(6)) {
            binDirectionOfTravel = udpMessageHelper.addTheNumberToBinString(binDirectionOfTravel, 1, 15);
        }
        if (changeMap.get(7)) {
            binReasonForSending = udpMessageHelper.addTheNumberToBinString(binReasonForSending, 1, 255);
        }
        udpPacketsCounter.setPacket_counter(udpMessageHelper.addTheNumberToBinString(udpPacketsCounter.getPacket_counter(), 1, 1294967295));

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
