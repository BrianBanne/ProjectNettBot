package Prosjekt1;


import java.io.*;
import java.net.Socket;

public class botWriteThread extends Thread {

    private Socket socket;
    private server server;
    private PrintWriter writer;
    private BufferedReader reader;
    private bot bot;
    private client client;
    public String botClientMessage = null;
    private DataOutputStream out;

    //constructor
    public botWriteThread(Socket socket, client client, bot bot) {
        this.socket = socket;
        this.client = client;
        this.bot = bot;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void run() {
            String botName = bot.getBotNamee();
            System.out.println(botName);
            server.addusername(botName.toString());
            //writer.println(botName);
    }
}


