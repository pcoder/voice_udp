package de.voicechat;

import javax.sound.sampled.AudioFormat;

/**
 * Created by skyblock4000 on 26.07.2014.
 */
public class ProjectAudioFormat {

    //Compression
        static int mode =1;//0,1,2
        static int sampleRate = 44100;
        static int channels = 1;
        static int quality = 10; //0-10
        static boolean enhanced = false;
    //


    //AudioFormat
    public static int bytesize = 640;//mode 0 = 320, 1=640, 2=1280

    public static AudioFormat getAudioFormat(){
        float sampleRate = 44100.0F;
        //8000,11025,16000,22050,44100
        int sampleSizeInBits = 16;
        //8,16
        int channels = 1;
        //1,2
        boolean signed = true;
        //true,false
        boolean bigEndian = false;
        //true,false
        return new AudioFormat(sampleRate,
                sampleSizeInBits,
                channels,
                signed,
                bigEndian);
    }

    //



}
