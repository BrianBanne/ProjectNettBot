package Prosjekt1;

import java.io.*;
import java.net.Socket;

public class botReadThread extends Thread {
    //buffreader for reading from client
    private BufferedReader reader;
    private Socket socket;
    private client client;
    private bot bot;
    private InputStream input;
    private PrintWriter writer;

    //constructor
    public botReadThread(Socket socket, client client, bot bot) {
        this.socket = socket;
        this.client = client;
        this.bot = bot;
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        String botName = bot.getBotNamee();
        bot.setBotName(botName);
        System.out.println(botName);
        server.addusername(botName.toString());
        writer.println(botName);


        String botnameformat = "["+botName+"]: ";

        while (true) {
            try {
                String reply = "";
                String response = reader.readLine();
                System.out.println("\n" + response);
                if (response.contains("heipådeg")) {
                    reply = bot.botgreeting();
                    System.out.println(botnameformat+reply);
                    writer.println(reply);
                  // System.out.println(response);
                }
                // bug, it prints the users name before the definition like a god damn moron
                if (botName.equalsIgnoreCase("Urbanbot")) {
                    if (response.contains("Activate Urbanbot")) {
                        //not needed, does not enter urbanbot mode like un userthread
                        if (response.contains("-exit")) {
                            return;
                        }
                        System.out.println(botnameformat+"Enter any word of your choosing, the funkier the better: ");
                        writer.println("Enter any word of your choosing, the funkier the better: ");
                        String urbanword = reader.readLine();
                        System.out.println(urbanword);
                        String[] splitarr = urbanword.split("]: ", -1);
                        String word = splitarr[1].toString();
                        if (word.contains(" ") || bot.urbandef(word) == null) {
                            writer.println("Word can't contain spaces, try again");
                            urbanword = reader.readLine();
                        }
                        String temp = urbanword + " ehh? " + bot.urbandef(word)
                                + ". huh, that doesnt seem right";

                        writer.println(temp);
                        System.out.println(botnameformat+temp);
                        if (botName.equalsIgnoreCase("alice")) {
                            System.out.println(botnameformat+"LOL, that a good one Mr.Urban");
                            writer.println("LOL, that's a good one Mr.Urban");
                        }
                    }
                }



                if (bot.getBotNamee() != null) {
                    System.out.print("[" + bot.getBotNamee() + "]: ");
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                break;
            }
        }
    }
}
