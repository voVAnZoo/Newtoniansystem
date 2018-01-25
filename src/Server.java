import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vova on 25.01.2018.
 */
public class Server {

    public static void start(){

        Data.servWork = true;

        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Psub clientSub = new Psub(1,0,0,0,0,0,0,0);
                try {
                    ServerSocket server= new ServerSocket(533);
                    Socket client = server.accept();

                    clientSub.isActiv = false;
                    Data.gp.allsub.add(clientSub);

                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    DataInputStream in = new DataInputStream(client.getInputStream());

                    while(!client.isClosed()&&Data.servWork){
                        read(in,clientSub, client);

                        out.writeUTF(Data.gp.allplayer.get(0).getX() + "%"
                                + Data.gp.allplayer.get(0).getY() + "%"
                                + Data.gp.allplayer.get(0).getPhi());
                        out.flush();
                    }

                    in.close();
                    out.close();

                    client.close();
                    server.close();

                    Data.servWork = false;
                    Data.gp.allsub.remove(clientSub);
                }catch (Exception e){
                    Data.gp.allsub.remove(clientSub);
                    e.printStackTrace();
                    Data.servWork = false;
                }
            }

            public  void read(DataInputStream in, Psub clientSub, Socket client){
                try {
                    String entry = in.readUTF();
                    clientSub.setX(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    clientSub.setY(Double.parseDouble(
                            entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    clientSub.setPhi(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));
                    /*System.out.println(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    System.out.println(Double.parseDouble(
                    entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    System.out.println(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));*/
                }catch (Exception e){
                    if (!client.isClosed()&&Data.servWork) {
                        e.printStackTrace();
                        read(in, clientSub, client);
                    }
                }
            }
        });

        serverThread.start();
    }
}
