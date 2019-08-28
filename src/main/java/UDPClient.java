import model.UDPMessage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class UDPClient extends TimerTask {

    private int portDst;
    private int intervalForSendingMessageUdp;

    private java.util.Timer timer;
    private DatagramSocket socket;
    private int numberSendingPacketsBeforeAliveMessage;
    private int counterSendingPackets;
    private boolean sendingUDPLIPMessage = false;
    private InetAddress address;

    private byte[] messageForUDP;
    private List<UDPMessage> listOfUDPMessages;

    public void initUDPConnection(String host, String portDst, String portSrc, String intervalForSendingMessageUdp) {


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

    public void startSendingMessageUdp() {
        sendingUDPLIPMessage = true;
        timer.scheduleAtFixedRate(this, 0, intervalForSendingMessageUdp);
    }

    public void continueSendingUdpLipMessage() {
        sendingUDPLIPMessage = true;
    }

    @Override
    public void run() {
        try {

            if (sendingUDPLIPMessage) {

                for (int i = 1; i < listOfUDPMessages.size(); i++) {
                    messageForUDP = listOfUDPMessages.get(i).getUdpMessage();
                    DatagramPacket packet = new DatagramPacket(messageForUDP, messageForUDP.length, address, portDst);
                    socket.send(packet);
                }
            }
            ++counterSendingPackets;
            if (counterSendingPackets >= numberSendingPacketsBeforeAliveMessage) {
                messageForUDP = listOfUDPMessages.get(0).getUdpMessage();
                DatagramPacket packetAlive = new DatagramPacket(messageForUDP, messageForUDP.length, address, portDst);
                socket.send(packetAlive);
                counterSendingPackets = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopSendingMessageUdp() {
        if (sendingUDPLIPMessage) {
            sendingUDPLIPMessage = false;
        }
    }


    public UDPClient withListOFUDPMessage(List<UDPMessage> listOfUDPMessages) {
        this.listOfUDPMessages = listOfUDPMessages;
        return this;
    }
}
