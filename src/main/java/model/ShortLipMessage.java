package model;

import java.util.Map;

public class ShortLipMessage extends UDPMessage {


    private String ssi;
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
        return convertBinStringToByteArray(udpMessage);
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

    protected void changeValuesOfElementsLipMessage() {
        if (changeMap.get(0)) {
            binSSI = addTheNumberToBinStringForSSI(binSSI, 1, 8000);
        }
        if (changeMap.get(1)) {
            binTimeElapsed = addTheNumberToBinString(binTimeElapsed, 1, 3);
        }
        if (changeMap.get(2)) {
            binLongitude = addTheNumberToBinString(binLongitude, 10, 33554431);
        }
        if (changeMap.get(3)) {
            binLatitude = addTheNumberToBinString(binLatitude, 10, 16777215);
        }
        if (changeMap.get(4)) {
            binPositionError = addTheNumberToBinString(binPositionError, 1, 7);
        }
        if (changeMap.get(5)) {
            binHorizontalVelocity = addTheNumberToBinString(binHorizontalVelocity, 1, 127);
        }
        if (changeMap.get(6)) {
            binDirectionOfTravel = addTheNumberToBinString(binDirectionOfTravel, 1, 15);
        }
        if (changeMap.get(7)) {
            binReasonForSending = addTheNumberToBinString(binReasonForSending, 1, 255);
        }
        packet_counter = addTheNumberToBinString(packet_counter, 1, 1294967295);
        System.out.println(binLatitude);
        System.out.println(binTimeElapsed);
    }
}
