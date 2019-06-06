import model.ShortLipMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

class UDPClient extends TimerTask {

    private int portDst;
    private int intervalForSendingMessageUdp;

    private java.util.Timer timer;
    private DatagramSocket socket;
    private int numberSendingPacketsBeforeAliveMessage;
    private int counterSendingPackets;
    private ShortLipMessage shortLipMessage;
    private boolean sendingUDPLIPMessage = false;
    private InetAddress address;

    private byte[] messageForUDP;

    void initUDPConnection(String host, String portDst, String portSrc, String intervalForSendingMessageUdp) {


        try {
            this.socket = new DatagramSocket(Integer.parseInt(portSrc));
            this.address = InetAddress.getByName(host);
            this.portDst = Integer.parseInt(portDst);
        } catch (IOException e) {
            e.printStackTrace();
        }
        timer = new Timer(true);
        this.intervalForSendingMessageUdp = Integer.parseInt(intervalForSendingMessageUdp);
        numberSendingPacketsBeforeAliveMessage = 25000 / Integer.parseInt(intervalForSendingMessageUdp);
        counterSendingPackets = 0;
        // запускаем переодическое выполнение (x = x * 1000 миллисекунд)


    }

    void startSendingMessageUdp() {
        sendingUDPLIPMessage = true;
        timer.scheduleAtFixedRate(this, 0, intervalForSendingMessageUdp);
    }

    void continueSendingUdpLipMessage() {
        sendingUDPLIPMessage = true;
    }

    @Override
    public void run() {
        try {

            if (sendingUDPLIPMessage) {
                messageForUDP = shortLipMessage.getUdpMessage();
                DatagramPacket packet = new DatagramPacket(messageForUDP, messageForUDP.length, address, portDst);
                socket.send(packet);


            }
            ++counterSendingPackets;
            if (counterSendingPackets >= numberSendingPacketsBeforeAliveMessage) {
                messageForUDP = shortLipMessage.getUdpAliveMessage();
                DatagramPacket packetAlive = new DatagramPacket(messageForUDP, messageForUDP.length, address, portDst);
                socket.send(packetAlive);
                counterSendingPackets = 0;
                       }
           } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopSendingMessageUdp() {
        if (sendingUDPLIPMessage) {
            sendingUDPLIPMessage = false;
        }
    }


    UDPClient withShortLipMessage(ShortLipMessage shortLipMessage) {
        this.shortLipMessage = shortLipMessage;
        return this;
    }


}
