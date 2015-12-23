package udp_serverclient;

import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getLocalHost();

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentenceFromUser = inFromUser.readLine();
        sendData = sentenceFromUser.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9097);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket =
           new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String sentenceFromServer = new String(receivePacket.getData());
        System.out.println("FROM SERVER (Before Encode):" + sentenceFromServer);
        
        String afterEncodeSentence = "";
            
        if(sentenceFromServer.length() > 0){
            for(int i = 0; i<= sentenceFromServer.length(); i++){
                char getChar = 0;

                try{
                    getChar = sentenceFromServer.charAt(i);
                }
                catch(IndexOutOfBoundsException e){

                }
                int getASCII = getChar;

                getASCII--;

                getChar = (char)getASCII;

                afterEncodeSentence = afterEncodeSentence + getChar;
            }
        }

        System.out.println("FROM SERVER (After Encode):" + afterEncodeSentence);
        clientSocket.close();
    }
}

