package edu.psu.abington.ist.ist242;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Simple Echo Client");
        try {
            System.out.println("Trying to connect...");
            InetAddress localAddress = InetAddress.getLocalHost();

            try (Socket clientSocket = new Socket(localAddress, 8080);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                 Scanner scanner = new Scanner(System.in);

                while (true) {
                    System.out.print("Enter text: ");
                    String inputLine = scanner.nextLine();
                    if ("quit".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                    out.println(inputLine);
                    String response = in.readLine();
                    System.out.println("Received response: " + response);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}