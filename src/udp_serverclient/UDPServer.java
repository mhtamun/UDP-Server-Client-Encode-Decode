package udp_serverclient;

import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        
        DatagramSocket serverSocket = new DatagramSocket(9097);

        byte[] receiveData = new byte[10240];
        byte[] sendData  = new byte[10240];

        while(true) {
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            
            System.out.println("FROM CLIENT (Before Decode):" + sentence);
            
            String afterDecodeSentence = "";
            
            if(sentence.length() > 0){
                for(int i = 0; i<= sentence.length(); i++){
                    char getChar = 0;
                    
                    try{
                        getChar = sentence.charAt(i);
                    }
                    catch(IndexOutOfBoundsException e){
                        
                    }
                    int getASCII = getChar;

                    getASCII++;

                    getChar = (char)getASCII;

                    afterDecodeSentence = afterDecodeSentence + getChar;
                }
            }
            
            System.out.println("FROM CLIENT (After Decode):" + afterDecodeSentence);

            InetAddress IPAddress = receivePacket.getAddress();

            int port = receivePacket.getPort();

            //String capitalizedSentence = sentence.toUpperCase();

            sendData = afterDecodeSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            serverSocket.send(sendPacket);
        }
    }
}
