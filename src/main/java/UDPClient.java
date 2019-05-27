

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

class UDPClient extends TimerTask {
    private String host, messageForUDP;
    private int portDst;
    private int portSrc;
    private boolean sendingMessages;
    private java.util.Timer timer;
    private int intervalForSendingMessageUdp;
    private DatagramSocket socket;
    private int numberSendingPacketsBeforeAliveMessage,
    counterSendingPackets;

    void startSendingMessageUdp() {

        timer = new Timer(true);
        try {
            socket = new DatagramSocket(portSrc);      //создаём сокет
        } catch (IOException e){
            e.printStackTrace();
        }
        numberSendingPacketsBeforeAliveMessage = 30000 / intervalForSendingMessageUdp;
        counterSendingPackets = 0;
        // будем запускать каждых x секунд (x = x * 1000 миллисекунд)
        timer.scheduleAtFixedRate(this, 0, intervalForSendingMessageUdp);


    }

    @Override
    public void run() {
        try {
            ++counterSendingPackets;
            InetAddress address = InetAddress.getByName(host); //получаем адрес для передачи информации

            byte[] buf = messageForUDP.getBytes();                     //передаем i, и определяем массив с данными
            System.out.println(buf.length);                //посмотрим между делом длину полученного массива
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, portDst); //создадим объект класса DatagramPacket и передадим ему массив, длину массива, адрес куда слать и порт
            socket.send(packet);                           //отправляем созданный UDP пакет
            System.out.println(counterSendingPackets);
            if ( counterSendingPackets >= numberSendingPacketsBeforeAliveMessage) {
                String aliveMessage = "alive message";
                byte[] bufAlive = aliveMessage.getBytes();
                System.out.println(bufAlive.length);
                DatagramPacket packetAlive = new DatagramPacket(bufAlive, bufAlive.length, address, portDst);
                socket.send(packetAlive);
                counterSendingPackets = 0;
            }

           } catch (IOException e) {
            e.printStackTrace();                          //отлавливаем необходимые исключения
        }
    }

    public void stopSendingMessageUdp() {
        if (host != null) {
            timer.cancel();
        }
    }

    UDPClient withsetHost(String host) {
        this.host = host;
        return this;

    }

    UDPClient withMessageForUdp(String messageForUdp) {
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

    UDPClient withSocket(DatagramSocket socket) {
        this.socket = socket;
        return this;
    }

}
