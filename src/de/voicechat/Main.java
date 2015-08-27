package de.voicechat;

import com.alee.laf.WebLookAndFeel;


public class Main {

    public static void main(String[] args)
    {

        WebLookAndFeel.install();
        if(System.getProperty("os.name").toLowerCase().contains("windows")){
            WebLookAndFeel.setDecorateAllWindows(true);
        }

        MainWindow mw = new MainWindow();


    }



    /*Main m = new Main();


    m.capture();
    m.play_captured();
    m.compress();
    m.decompress();
    m.play_decompressed();*/


    /*static File outPutFile_caputre = new File("P:\\test_capture.wav");
    static File outPutFile_compressed = new File("P:\\test_compressed.wav");
    static File outPutFile_decomprssed = new File("P:\\test_decompressed.wav");

    public void capture() throws IOException, LineUnavailableException
    {
        System.out.println("start Client");
        AudioFormat af = ProjectAudioFormat.getAudioFormat();
        DataLine.Info info = new DataLine.Info (TargetDataLine.class,af);
        TargetDataLine tl = null;
        tl = (TargetDataLine) AudioSystem.getLine(info);

        ByteArrayOutputStream baOut = new ByteArrayOutputStream();

        tl.open(af);

        tl.start();
        int numBytesRead;
        // definieren und gleich einmal initialisieren.
        Boolean temptrue = true;

        FileOutputStream fos = new FileOutputStream(outPutFile_caputre);

        int avaiblecounter = 0;

        while(avaiblecounter <= 1190400){
            byte[] ba = new byte[ProjectAudioFormat.bytesize];
            numBytesRead = tl.read(ba,0,ba.length);
            baOut.write(ba, 0, numBytesRead);
            ba = baOut.toByteArray();

            //ba = Compression.compress(ba);

            fos.write(ba);
            fos.flush();

            baOut.reset();

            avaiblecounter = avaiblecounter + 640*2;
            System.out.println("CAPTURE: " + avaiblecounter);




            //send/save

            //temptrue = false;
        }
    }

    public void compress() throws IOException
    {
        int size = 1280;
        FileInputStream fis = new FileInputStream(outPutFile_caputre);
        FileOutputStream fos = new FileOutputStream(outPutFile_compressed);
        Compression comp = new Compression();
        while(fis.available()>=size){
            System.out.println("FIS - Avaible: " + fis.available());
            byte[] b = new byte[size];
            fis.read(b, 0, size);

            byte[] compressed = comp.compress(b);
            System.out.println(compressed.length);
            fos.write(compressed);
            fos.flush();
        }
        fos.close();
        System.out.println("Fine");

    }

    public void decompress() throws IOException, DataFormatException
    {
        Compression comp = new Compression();
        int size = 110;
        FileInputStream fis = new FileInputStream(outPutFile_compressed);
        FileOutputStream fos = new FileOutputStream(outPutFile_decomprssed);
        while(fis.available()>=size){
            System.out.println("FIS - Avaible: " + fis.available());
            byte[] b = new byte[size];
            fis.read(b,0,size);
            fos.write(comp.decompress(b));
            fos.flush();
        }
        fos.close();
        System.out.println("Fine");
    }


    public void play_captured()
            throws LineUnavailableException, IOException, InterruptedException
    {
        final SourceDataLine sourceLine = startSourceDataLine();

        System.out.println("start Server on Port " );
        FileInputStream fis = new FileInputStream(outPutFile_caputre);
        while(fis.available()>=640)
        {
            System.out.println("FIS-Avaible [captured]" + fis.available());
            byte[] data = new byte[640];
            fis.read(data,0,640);
            sourceLine.write(data,0,640);

        }
        sourceLine.drain();

        System.out.println("Finish");

    }
    public void play_decompressed() throws LineUnavailableException, IOException
    {
        final SourceDataLine sourceLine = startSourceDataLine();

        System.out.println("start Server on Port " );
        FileInputStream fis = new FileInputStream(outPutFile_decomprssed);
        while(fis.available()>=640)
        {
            System.out.println("DECOMPRESSEDPLAY FIS.aviavble " + fis.available());
            byte[] data = new byte[640];
            fis.read(data,0,640);
            sourceLine.write(data,0,640);
        }
        sourceLine.drain();

        System.out.println("Finish");

    }

    static AudioFormat format = ProjectAudioFormat.getAudioFormat();

    private SourceDataLine startSourceDataLine() throws LineUnavailableException {
        DataLine.Info info= new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine sourceDataLine = null;
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
        sourceDataLine.open();
        sourceDataLine.start();
        return sourceDataLine;
    }*/


}
