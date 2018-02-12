import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Created by Vova on 25.01.2018.
 */
public class Server {

    public static void start(){


        Data.servWork = true;

        Thread serverThread = new Thread(new Runnable() {
            private Selector selector;
            private Map<SocketChannel,List> dataMapper;
            private InetSocketAddress listenAddress;
            String address = "localhost";
            int port = 533;
            @Override
            public void run() {
                try {
                    listenAddress = new InetSocketAddress(address, port);
                    dataMapper = new HashMap<SocketChannel, List>();
                    selector = Selector.open();
                    ServerSocketChannel serverChannel = ServerSocketChannel.open();
                    serverChannel.configureBlocking(false);
                    serverChannel.socket().bind(listenAddress);
                    serverChannel.register(selector, SelectionKey.OP_ACCEPT);


                    while (Data.servWork) {
                        selector.select();

                        Iterator keys = selector.selectedKeys().iterator();
                        while (keys.hasNext()) {
                            SelectionKey key = (SelectionKey) keys.next();
                            keys.remove();

                            if (!key.isValid()) {
                                continue;
                            }

                            if (key.isAcceptable()) {
                                accept(key);
                            } else if (key.isReadable()) {
                                read(key);
                            }
                        }
                    }
                    {
//                    ServerSocket server= new ServerSocket(533);
//                    Socket client = server.accept();
//
//                    clientSub.isActiv = false;
//                    Data.gp.allsub.add(clientSub);
//
//                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
//                    DataInputStream in = new DataInputStream(client.getInputStream());
//
//                    while(!client.isClosed()&&Data.servWork){
//                        read(in,clientSub, client);
//
//                        /*out.writeUTF(Data.gp.allplayer.get(0).getX() + "%"
//                                + Data.gp.allplayer.get(0).getY() + "%"
//                                + Data.gp.allplayer.get(0).phi());*/
//                        out.flush();
//                    }
//
//                    in.close();
//                    out.close();
//
//                    client.close();
//                    server.close();
//
//                    Data.servWork = false;
//                    Data.gp.allsub.remove(clientSub);
                }
                }catch (Exception e){
                    Data.servWork = false;
                }
            }

//            public  void read(DataInputStream in, Psub clientSub, Socket client){
//                if (!client.isClosed()&&Data.servWork) {
//                    try {
//                        String entry = in.readUTF();
//                        clientSub.setX(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
//                        clientSub.setY(Double.parseDouble(
//                                entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
//                        //clientSub.setPhi(Double.parseDouble(entry.substring(entry.lastIndexOf('%') + 1)));
//                    } catch (Exception e) {
//                        if (!client.isClosed() && Data.servWork) {
//                            read(in, clientSub, client);
//                        } else {
//                            Data.gp.allsub.remove(clientSub);
//                        }
//                    }
//                }
//            }

            private void accept(SelectionKey key) throws IOException {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                SocketChannel channel = serverChannel.accept();
                channel.configureBlocking(false);

                dataMapper.put(channel, new ArrayList());
                channel.register(this.selector, SelectionKey.OP_READ);
            }

            private void read(SelectionKey key) throws IOException {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int numRead = -1;
                numRead = channel.read(buffer);

                if (numRead == -1) {
                    this.dataMapper.remove(channel);
                    channel.close();
                    key.cancel();
                    return;
                }

                /*byte[] data = new byte[numRead];
                System.arraycopy(buffer.array(), 0, data, 0, numRead);
                System.out.println("Got: " + new String(data));*/
            }
        });

        serverThread.start();
    }
}
