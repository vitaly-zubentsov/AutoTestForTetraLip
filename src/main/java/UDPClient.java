

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

class UDPClient extends TimerTask {
    public String host, messageforUDP;
    public int portDst;
    public int portSrc;

    public int intervalForSendingMessageUdp;

    public void startSendingMessageUdp() {

        java.util.Timer timer = new Timer(true);
        // будем запускать каждых x секунд (x = x * 1000 миллисекунд)
        timer.scheduleAtFixedRate(this, 0, intervalForSendingMessageUdp * 1000);

    }

    @Override
    public void run() {
        try {
            InetAddress address = InetAddress.getByName(host); //получаем адрес для передачи информации
            DatagramSocket socket = new DatagramSocket();      //создаём сокет
                    byte[] buf = messageforUDP.getBytes();                     //передаем i, и определяем массив с данными
            System.out.println(buf.length);                //посмотрим между делом длину полученного массива
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, portDst); //создадим объект класса DatagramPacket и передадим ему массив, длину массива, адрес куда слать и порт
            socket.send(packet);                           //отправляем созданный UDP пакет

        } catch (IOException e) {
            e.printStackTrace();                          //отлавливаем необходимые исключения
        }
    }

    public UDPClient withsetHost(String host) {
        this.host = host;
        return this;

    }

    public UDPClient withMessageForUdp(String messageForUdp) {
        this.messageforUDP = messageForUdp;
        return this;
    }

    public UDPClient withPortDst(int portDst) {
        this.portDst = portDst;
        return this;
    }

    public UDPClient withPortSrc(int portSrc) {
        this.portSrc = portSrc;
        return this;
    }

    public UDPClient withIntervalForSendingMessageUdp(int intervalForSendingMessageUdp) {
        this.intervalForSendingMessageUdp = intervalForSendingMessageUdp;
        return this;
    }
}
