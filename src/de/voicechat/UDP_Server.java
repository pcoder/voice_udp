package de.voicechat;

import sun.rmi.runtime.Log;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.*;
import java.util.zip.DataFormatException;

public class UDP_Server{
    DatagramSocket socket;
    Thread t;
    AudioFormat format = ProjectAudioFormat.getAudioFormat();

    public void start(int port) throws IOException, LineUnavailableException
    {
        start(port, null);
    }

    public void start(int port, final MainWindow mw) throws IOException, LineUnavailableException {
        final SourceDataLine sourceLine = startSourceDataLine();

        socket = new DatagramSocket( port );

        System.out.println("start Server on Port " + port);

        t = new Thread( new Runnable() {
                @Override
                public void run() {
                    Compression comp = new Compression();
                    while ( true )
                    {
                        // Auf Anfrage warten
                        DatagramPacket packet = new DatagramPacket( new byte[ProjectAudioFormat.bytesize], ProjectAudioFormat.bytesize );
                        try {
                            socket.receive( packet );
                            byte[] data = new byte[packet.getLength()];

                            if(mw != null){
                                mw.setConnectionInfo(
                                        mw.clientInfos() + "\n" +
                                        "This Computer" + "<--" + packet.getAddress().toString()
                                );

                            }

                            System.out.println("Packet UDP: " + packet.getLength());
                            System.out.println("");
                            System.arraycopy(packet.getData(), 0, data, 0, packet.getLength());
                            try {
                                //byte[] decompressed = new byte[ProjectAudioFormat.bytesize];
                                //decompressed = Compression.decompress(data);
                                data = comp.decompress(data);
                                sourceLine.write(data, 0, data.length);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (DataFormatException e) {
                                e.printStackTrace();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            }
        } );

        t.start();

    }


    private SourceDataLine startSourceDataLine() throws LineUnavailableException {
        DataLine.Info info= new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine sourceDataLine = null;
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
        sourceDataLine.open();
        sourceDataLine.start();
        return sourceDataLine;
    }



    private void closeSourceDataLine(SourceDataLine sourceLine){
        sourceLine.drain();
        sourceLine.stop();
        sourceLine.close();
    }
}