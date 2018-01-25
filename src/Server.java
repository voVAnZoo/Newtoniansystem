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
                try {
                    ServerSocket server= new ServerSocket(533);
                    Socket client = server.accept();

                    Psub clientsub = new Psub(1,0,0,0,0,0,0,0);
                    clientsub.isActiv = false;
                    Data.gp.allsub.add(clientsub);

                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    DataInputStream in = new DataInputStream(client.getInputStream());

                    while(!client.isClosed()){
                        read(in);

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

                }catch (Exception e){
                    e.printStackTrace();
                    Data.servWork = false;
                }
            }

            public  void read(DataInputStream in){
                try {
                    String entry = in.readUTF();
                    Data.gp.allsub.get(0).setX(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    Data.gp.allsub.get(0).setY(Double.parseDouble(
                            entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    Data.gp.allsub.get(0).setPhi(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));
                    /*System.out.println(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    System.out.println(Double.parseDouble(
                    entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    System.out.println(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));*/
                }catch (Exception e){
                    e.printStackTrace();
                    read(in);
                }
            }
        });

        serverThread.start();
    }
}
