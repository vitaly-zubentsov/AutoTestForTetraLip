package autotestfortetralip;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPClient {
    private static String HOST;
    private static int
            PORT,
            MESSAGE_SENDING_TIME;

    public static void main(String[] args) throws InterruptedException {
        HOST = "192.168.1.124";
        PORT = 1095;
        MESSAGE_SENDING_TIME = 1000;
        try {
            InetAddress address = InetAddress.getByName(HOST); //получаем адрес для передачи информации
            DatagramSocket socket = new DatagramSocket();      //создаём сокет
            while (true) {
                int i = (int) (1000 * Math.random());            //создаем случайное число для длины пакета
                byte[] buf = new byte[i];                     //передаем i, и определяем массив с данными
                System.out.println(buf.length);                //посмотрим между делом длину полученного массива
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT); //создадим объект класса DatagramPacket и передадим ему массив, длину массива, адрес куда слать и порт
                socket.send(packet);                           //отправляем созданный UDP пакет
            }
        } catch (IOException e) {
            e.printStackTrace();                          //отлавливаем необходимые исключения
        }
    }
}
