package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main (String [] arg) {
        String lastCity = "???";
        String answerClient;
        int count = 0;
        try (ServerSocket serverSocket = new ServerSocket(Config.port)) {
            System.out.println("Игра запущена");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {
                    out.println(lastCity);
                    answerClient = in.readLine();
                    if(count > 0){
                        if(Character.toUpperCase(lastCity.charAt(lastCity.length() - 1)) == Character.toUpperCase(answerClient.charAt(0))){
                            lastCity = answerClient;
                            out.println("OK");
                        }else{
                            out.println("Not OK");
                        }
                    }else{
                        lastCity = answerClient;
                        out.println("OK");
                    }
                    count +=1;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
