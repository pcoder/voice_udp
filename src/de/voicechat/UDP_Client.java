package de.voicechat;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by skyblock4000 on 26.07.2014.
 */
public class UDP_Client {
    public void send(String domain_ip, int port, byte[] data) throws IOException {
        InetAddress ia;
        ia = InetAddress.getByName(domain_ip);
        DatagramPacket packet = new DatagramPacket( data, data.length, ia, port );
        DatagramSocket toSocket = new DatagramSocket();
        toSocket.send( packet );
    }

    public void sendMicro(final String domain_ip, final int port) throws LineUnavailableException {
        Thread t = new Thread( new Runnable()
        {
            @Override
            public void run()
            {
                Compression comp = new Compression();
                System.out.println("start Client");
                AudioFormat af = ProjectAudioFormat.getAudioFormat();
                DataLine.Info info = new DataLine.Info (TargetDataLine.class,af);
                TargetDataLine tl = null;
                try
                {
                    tl = (TargetDataLine) AudioSystem.getLine(info);
                } catch (LineUnavailableException e)
                {
                    e.printStackTrace();
                }
                ByteArrayOutputStream baOut = new ByteArrayOutputStream();
                try
                {
                    tl.open(af);
                } catch (LineUnavailableException e)
                {
                    e.printStackTrace();
                }
                tl.start();
                int numBytesRead;
                // definieren und gleich einmal initialisieren.
                Boolean temptrue = true;
                while(temptrue){
                    byte[] ba = new byte[ProjectAudioFormat.bytesize];
                    numBytesRead = tl.read(ba,0,ba.length);
                    baOut.write(ba, 0, numBytesRead);
                    ba = baOut.toByteArray();
                    try {
                        ba = comp.compress(ba);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    baOut.reset();
                    try {
                        send(domain_ip,port,ba);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //temptrue = false;
                }
            }
        });
        t.start();



    }
}
