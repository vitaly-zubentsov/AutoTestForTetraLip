package model;

public class AliveMessage implements UDPMessage{


    private UDPMessageHelper udpMessageHelper = new UDPMessageHelper();

    public byte[] getUdpMessage() {
        String udpAliveMessage = udpPacketsCounter.getPacket_counter() + "00000000000000000000000000000000";
        udpPacketsCounter.setPacket_counter(udpMessageHelper.addTheNumberToBinString(udpPacketsCounter.getPacket_counter(), 1, 1294967295));
        return udpMessageHelper.convertBinStringToByteArray(udpAliveMessage);
    }
}
