import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Vova on 25.01.2018.
 */
public class Client {
    public static void start(){
        Data.clientWork = true;
        System.out.println("asd");
        Thread clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 533);
                    System.out.println("yes");

                    DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                    DataInputStream ois = new DataInputStream(socket.getInputStream());
                    System.out.println("yes yes");

                    Psub sevrSub = new Psub(1,0,0,0,0,0,0,0);
                    sevrSub.isActiv = false;
                    Data.gp.allsub.add(sevrSub);

                    while(!socket.isOutputShutdown()){
                        oos.writeUTF(Data.gp.allplayer.get(0).getX() + "%" + Data.gp.allplayer.get(0).getY() + "%" + Data.gp.allplayer.get(0).getPhi());
                        oos.flush();
                        System.out.println("go");

                        read(ois);

                    }

                    ois.close();
                    oos.close();
                    socket.close();
                    Data.clientWork = false;
                    System.out.println("stop");
                }catch (Exception e){
                    Data.clientWork = false;
                    System.out.println("err");
                    e.printStackTrace();
                }
            }

            public  void read(DataInputStream in){
                System.out.println("поехали");
                try {
                    String entry = in.readUTF();
                    Data.gp.allsub.get(0).setX(Double.parseDouble(entry.substring(0, entry.indexOf('%'))));
                    Data.gp.allsub.get(0).setY(Double.parseDouble(entry.substring(entry.indexOf('%') + 1, entry.lastIndexOf('%'))));
                    Data.gp.allsub.get(0).setPhi(Double.parseDouble(entry.substring(entry.lastIndexOf('%')+1)));
                    System.out.println("ok");

                }catch (Exception e){
                    System.out.println("ещё нет");
                    read(in);
                    e.printStackTrace();
                }
            }
        });

        clientThread.start();
    }


}
