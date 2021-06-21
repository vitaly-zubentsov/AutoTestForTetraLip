public class AliveMessage implements UDPMessage
{

    private UDPMessageHelper udpMessageHelper = new UDPMessageHelper();
    //Generate alive message
    // 32 bits - packet Counter
    // 256 bits - body of alive message, if all ok, then all characters are zeros
    public byte[] getUdpMessage() {
        String udpAliveMessage = udpPacketsCounter.getPacket_counter() + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        udpPacketsCounter.setPacket_counter(udpMessageHelper.addTheNumberToBinString(udpPacketsCounter.getPacket_counter(), 1, 1294967295));
        return udpMessageHelper.convertBinStringToByteArray(udpAliveMessage);
    }
}
