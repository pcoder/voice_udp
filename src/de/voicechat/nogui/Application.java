package de.voicechat.nogui;

import de.voicechat.UDP_Client;
import de.voicechat.UDP_Server;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Created by test on 8/27/15.
 */
public class Application {


    public Application(int mode){
        String IP = "59.93.235.89";
        switch (mode){
            case 1:
                UDP_Server server = new UDP_Server();
                try {
                    server.start(4444);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
                break;
            default:
            case 2:
                UDP_Client client = new UDP_Client();
                try {
                    client.sendMicro(IP, 4444);
                } catch (Exception eClient) {
                    eClient.printStackTrace();
                    System.exit(-1);
                }
                break;
        }
    }

    public static void main(String[] args){
        if(args[1].equalsIgnoreCase("server")){
            new Application(1);
        }else{

        }
    }


}
