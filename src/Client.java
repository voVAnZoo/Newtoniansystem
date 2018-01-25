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
                Psub serverSub = new Psub(1,0,0,0,0,0,0,0);
                try {
                    Socket socket = new Socket(s, 533);

                    DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                    DataInputStream ois = new DataInputStream(socket.getInputStream());


                    serverSub.isActiv = false;
                    Data.gp.allsub.add(serverSub);

                    while(!socket.isOutputShutdown()&&Data.clientWork){
                        oos.writeUTF(Data.gp.allplayer.get(0).getX() + "%"
                                + Data.gp.allplayer.get(0).getY() + "%"
                                + Data.gp.allplayer.get(0).getPhi());
                        oos.flush();

                        read(ois,serverSub,socket);
                    }

                    ois.close();
                    oos.close();
                    socket.close();
                    Data.gp.allsub.remove(serverSub);
                    Data.clientWork = false;
                }catch (Exception e){
                    Data.gp.allsub.remove(serverSub);
                    Data.clientWork = false;
                    e.printStackTrace();
                }
            }

            public  void read(DataInputStream in, Psub serverSub, Socket socket){
                try {
                    String entry = in.readUTF();
                    serverSub.setX(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    serverSub.setY(Double.parseDouble(
                            entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    serverSub.setPhi(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));

                }catch (Exception e){
                    if (!socket.isOutputShutdown()&&Data.clientWork) {
                        read(in, serverSub,socket);
                    }else {
                        Data.gp.allsub.remove(serverSub);
                    }
                }
            }
        });

        clientThread.start();
    }


}
