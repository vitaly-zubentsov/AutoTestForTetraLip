package model;

public class AliveMessage extends UDPMessage{

    public byte[] getUdpAliveMessage() {
        String udpAliveMessage = packet_counter + "00000000000000000000000000000000";
        addTheNumberToBinString(packet_counter, 1, 1294967295);
        return convertBinStringToByteArray(udpAliveMessage);
    }
}
