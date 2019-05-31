import model.ShortLipMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

class UDPClient extends TimerTask {
    private String host;
    private byte[] messageForUDP;
    private int portDst;
    private int portSrc;
    private java.util.Timer timer;
    private int intervalForSendingMessageUdp;
    private DatagramSocket socket;
    private int numberSendingPacketsBeforeAliveMessage,
            counterSendingPackets;
    private ShortLipMessage shortLipMessage;

    void startSendingMessageUdp() {

        timer = new Timer(true);
        try {
            socket = new DatagramSocket(portSrc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        numberSendingPacketsBeforeAliveMessage = 25000 / intervalForSendingMessageUdp;
        counterSendingPackets = 0;
        // будем запускать каждых x секунд (x = x * 1000 миллисекунд)
        timer.scheduleAtFixedRate(this, 0, intervalForSendingMessageUdp);


    }

    @Override
    public void run() {
        try {
            InetAddress address = InetAddress.getByName(host);
            messageForUDP = shortLipMessage.getUdpMessage();
            DatagramPacket packet = new DatagramPacket(messageForUDP, messageForUDP.length, address, portDst);
            socket.send(packet);
            ++counterSendingPackets;
            System.out.println("Hello from UDPRun");
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

    public void stopSendingMessageUdp() {
        if (host != null) {
            timer.cancel();
            socket.close();
        }
    }

    UDPClient withHost(String host) {
        this.host = host;
        return this;

    }

    UDPClient withMessageForUdp(byte[] messageForUdp) {
        this.messageForUDP = messageForUdp;
        return this;
    }

    UDPClient withPortDst(int portDst) {
        this.portDst = portDst;
        return this;
    }

    UDPClient withPortSrc(int portSrc) {
        this.portSrc = portSrc;
        return this;
    }

    UDPClient withIntervalForSendingMessageUdp(int intervalForSendingMessageUdp) {
        this.intervalForSendingMessageUdp = intervalForSendingMessageUdp;
        return this;
    }

    UDPClient withShortLipMessage(ShortLipMessage shortLipMessage) {
        this.shortLipMessage = shortLipMessage;
        return this;
    }


}
