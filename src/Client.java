import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Vova on 25.01.2018.
 */
public class Client {
    public static void start(String s){
        Data.clientWork = true;
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket(s, 533);

                    DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                    DataInputStream ois = new DataInputStream(socket.getInputStream());

                    Psub sevrSub = new Psub(1,0,0,0,0,0,0,0);
                    sevrSub.isActiv = false;
                    Data.gp.allsub.add(sevrSub);

                    while(!socket.isOutputShutdown()){
                        oos.writeUTF(Data.gp.allplayer.get(0).getX() + "%" + Data.gp.allplayer.get(0).getY() + "%" + Data.gp.allplayer.get(0).getPhi());
                        oos.flush();

                        read(ois);

                    }

                    ois.close();
                    oos.close();
                    socket.close();
                    Data.clientWork = false;
                }catch (Exception e){
                    Data.clientWork = false;
                    e.printStackTrace();
                }
            }

            public  void read(DataInputStream in){
                try {
                    String entry = in.readUTF();
                    Data.gp.allsub.get(0).setX(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    Data.gp.allsub.get(0).setY(Double.parseDouble(entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    Data.gp.allsub.get(0).setPhi(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));

                }catch (Exception e){
                    read(in);
                    e.printStackTrace();
                }
            }
        });

        clientThread.start();
    }


}
