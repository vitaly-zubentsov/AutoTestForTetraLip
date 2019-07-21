package model;


public interface UDPMessage {

    UDPPacketsCounter udpPacketsCounter = UDPPacketsCounter.getUDPPacketsCounter();
    byte[] getUdpMessage();



 class UDPPacketsCounter {

    private static UDPPacketsCounter udpPacketsCounter;
    public String packet_counter;

    public static synchronized UDPPacketsCounter getUDPPacketsCounter() {
        if (udpPacketsCounter == null)
            udpPacketsCounter = new UDPPacketsCounter();
            udpPacketsCounter.packet_counter = "00000000000000000000000000000001";
        return udpPacketsCounter;
    }

    public String getPacket_counter() {
        return packet_counter;
    }

    public void setPacket_counter(String packet_counter) {
        this.packet_counter = packet_counter;
    }
}

}