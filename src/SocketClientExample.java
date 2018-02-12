import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketClientExample {

    public void startClient()
            throws IOException, InterruptedException {

        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8090);
        SocketChannel client = SocketChannel.open(hostAddress);

        System.out.println("Client... started");

        String threadName = Thread.currentThread().getName();

        // Send messages to server
        String [] messages = new String []
                {threadName + ": bbbbbbbbbbb",threadName + ": aaaaaaaaaaa",threadName + ": =-3"};

        for (int i = 0; i < messages.length; i++) {
            byte [] message = new String(messages [i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println(messages [i]);
            buffer.clear();
            //Thread.sleep(5000);
            ByteBuffer bufferr = ByteBuffer.allocate(1024);
            int numRead = -1;
            numRead = client.read(bufferr);

            byte[] data = new byte[numRead];
            System.arraycopy(bufferr.array(), 0, data, 0, numRead);
            System.err.println("good: " + new String(data));
        }
        client.close();
    }

}