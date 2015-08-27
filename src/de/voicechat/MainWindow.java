package de.voicechat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by SirSky on 15.03.2015.
 */
public class MainWindow
{
    private JPanel mainPannel;
    private JTextField textFieldRemoteIpDomain;
    private JTextField textFieldRemotePort;
    private JTextField textFieldLocalPort;
    private JButton startButton;
    private JLabel labelConnectionInfo;
    private JTextPane textPaneConnectionInfo;

    public MainWindow()
    {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(mainPannel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                startButton.setEnabled(false);
                startButton.setText("VoiceChat Active");
                textFieldRemoteIpDomain.setEnabled(false);
                textFieldRemotePort.setEnabled(false);
                textFieldLocalPort.setEnabled(false);
                startServer();
                startClient();
            }
        });
    }

    private void startServer(){
        UDP_Server server = new UDP_Server();
        try {
            server.start(Integer.parseInt(textFieldLocalPort.getText()),this);
        } catch (Exception eServer) {
            eServer.printStackTrace();
            System.exit(-1);
        }

    }

    private void startClient(){
        UDP_Client client = new UDP_Client();
        try {
            client.sendMicro(textFieldRemoteIpDomain.getText(),Integer.parseInt(textFieldRemotePort.getText()));
        } catch (Exception eClient) {
            eClient.printStackTrace();
            System.exit(-1);
        }
    }

    public void setConnectionInfo(String info){
        if(!textPaneConnectionInfo.getText().equals(info)){
            textPaneConnectionInfo.setText(info);
        }
    }

    public String clientInfos()
    {
        return "This Computer" + "-->" + textFieldRemoteIpDomain.getText();
    }


/* */
}
