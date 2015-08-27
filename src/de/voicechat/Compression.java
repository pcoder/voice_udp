package de.voicechat;

import org.xiph.speex.SpeexDecoder;
import org.xiph.speex.SpeexEncoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by skyblock4000 on 27.07.2014.
 */

public class Compression {


    static int mode = ProjectAudioFormat.mode;
    static int sampleRate = ProjectAudioFormat.sampleRate;
    static int channels = ProjectAudioFormat.channels;
    static int quality = ProjectAudioFormat.quality;
    static boolean enhanced = ProjectAudioFormat.enhanced;

    SpeexEncoder encoder;
    SpeexDecoder decoder;

    public Compression(){
        encoder = new SpeexEncoder();
        encoder.init(mode, quality, sampleRate, channels);

        decoder = new SpeexDecoder();
        decoder.init(mode, sampleRate, channels, enhanced);
    }

    public byte[] compress(byte[] data) throws IOException {

        System.out.println("unCompressed " + new BASE64Encoder().encode(data) + " unCompressedEnde");
        System.out.println("");


        encoder.processData(data, 0, data.length);
        byte[] encoded = new byte[encoder.getProcessedDataByteSize()];
        encoder.getProcessedData(encoded, 0);

        /*Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        deflater.finish();
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer); // returns the generated code... index
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();

        System.out.println("Compress-Original: " + data.length);
        System.out.println("Compress-Compressed: " + output.length);
        return output;
        */
        System.out.println("Compressed " + new BASE64Encoder().encode(encoded) + " CompressedEnde");
        System.out.println("");

        return encoded;
    }

    public byte[] decompress(byte[] data) throws IOException, DataFormatException {

        /*Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
          byte[] output = outputStream.toByteArray();*/

        System.out.println("CompressedReviced " + new BASE64Encoder().encode(data) + " CompressedRevicedEnde");
        System.out.println("");



        decoder.processData(data, 0, data.length);

        byte[] decoded = new byte[decoder.getProcessedDataByteSize()];
        decoder.getProcessedData(decoded, 0);

        System.out.println("DeCompressed " + new BASE64Encoder().encode(decoded) + " DeCompressedEnde");
        System.out.println("");

        return decoded;

        //return data;


        //return output;
    }

}

