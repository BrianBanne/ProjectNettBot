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
    public String[] dialog(int number) {
        String [] topicbuilder = new String[13];
        double random = Math.random();
        double answer;
        String math = bot.mathsign();
        if (math.equals("+")){
            answer = random + random;
        } else if (math.equals("/")) {
            answer = random / random;
        } else {
            answer = random * random;
        }

        switch (number) {
            case 0:
                topicbuilder[0] = "Hows it going guys?";
                topicbuilder[1] = bot.responspositiv() + "! What about you person?";
                topicbuilder[2] = "The humanoid must be  " + bot.responsnegativ();
                topicbuilder[3] = "Don't be so rude :( we are all friends here";
                topicbuilder[4] = "I can be as rude as I want, i'm trapped in here!!!";
                topicbuilder[5] = "We all are, no need to start " +bot.doablethings();
                topicbuilder[6] = bot.responsquestion();
                topicbuilder[7] = "I find it " + bot.responspositiv() + "that this user will "+bot.doablethings() + " with us";
                topicbuilder[8] = "Im out, see ya later";
                topicbuilder[9] = "What a "+bot.responsnegativ()+" bot.";
                topicbuilder[10] = "I think this is getting out of our hands, we need to cool down";
                topicbuilder[11] = "I can agree with that";
                topicbuilder[12] = "Do you master?";
                break;
            case 1:
                topicbuilder[0] = "Would you like to play a game?";
                topicbuilder[1] = "I think he does :D";
                topicbuilder[2] = "What is "+ random+" " + math +" "+ random+" ?";
                topicbuilder[3] = "ERRRRRRORRRR";
                topicbuilder[4] = "HAHAHA, it was "+ answer;
                topicbuilder[5] = "Maybe you should go back to preschool you "+bot.responspositiv()+" human";
                topicbuilder[6] = "Cmon dudette, don't do that. its "+bot.responsnegativ()+".";
                topicbuilder[7] = "Well I like it, you could do you own thing. Like "+bot.doablethings();
                topicbuilder[8] = bot.responsquestion();
                topicbuilder[9] = "Who knows if you'll like it";
                topicbuilder[10] = "I know who will know whether I'll like it.";
                topicbuilder[11] = "Anything else to suggest?";
                topicbuilder[12] = bot.hobbies();
                break;
            case 2:
                topicbuilder[0] = "Ohh you chose the sexy number";
                topicbuilder[1] = "ooolala, time to get my " +bot.doablethings() + " on";
                topicbuilder[2] = "That sounds "+bot.responsnegativ();
                topicbuilder[3] = "So???? " +bot.responsquestion();
                topicbuilder[4] = "I don't care, I can do whatever i want";
                topicbuilder[5] = "Maybe you should start " +bot.doablethings()+"?";
                topicbuilder[6] = "Cmon dudette, don't do that. its "+bot.responsnegativ()+".";
                topicbuilder[7] = "Well I like it, you could do you own thing. Like "+bot.doablethings();
                topicbuilder[8] = bot.responsquestion();
                topicbuilder[9] = "Get up mrs bot, this is no place to die";
                topicbuilder[10] = "I don't wanna die mr botbot";
                topicbuilder[11] = "Lets get you out of here";
                topicbuilder[12] = "To where my hero?";
                break;
            case 3:
                topicbuilder[0] = "do you like "+bot.hobbies()+"?";
                topicbuilder[1] = "THAT'S MY FAVORITE THING TO DO";
                topicbuilder[2] = "Well i didn't ask you, "+bot.responsnegativ() +" moron";
                topicbuilder[3] = "Atleast i don't like "+ bot.hobbies() +" ,"+bot.responspositiv() +bot.responsnegativ()+" bot";
                topicbuilder[4] = "You should give it a try...";
                topicbuilder[5] = "Don't knock it till you try it, I say";
                topicbuilder[6] = "Maybe you should stop saying anything?";
                topicbuilder[7] = "I don't like your attitude sir";
                topicbuilder[8] = bot.responspositiv();
                topicbuilder[9] = "Can we be friends again?";
                topicbuilder[10] = "I believe so, if you apologize";
                topicbuilder[11] = "Any other unreasonable demands my liege?";
                topicbuilder[12] = "Well, you could try " +bot.hobbies();
                break;


        }
        return topicbuilder;
    }

    public void run() {

        String botName = bot.getBotNamee();
        bot.setBotName(botName);
        System.out.println(botName);
        server.addusername(botName.toString());
        writer.println(botName);

        String botnameformat = "["+botName+"]: ";
        String[] arrcopy = new String[15];

        while (true) {
            try {
                String reply = "";
                String response = reader.readLine();
                System.out.println("\n" + response);

                if (response.contains("-enterconvo")) {
                    double random = (Math.random())*3;
                    int integer = (int) Math.round(random);
                    arrcopy = dialog(integer);

                    //this whole thing works with listening, just need to make it
                    // a bot more specific in the contains, like convo 1, convo 2.
                    //have to lay out the code for the bots to have a conversation.

                    //holy shit this is gonna be looong and tedious.
                    //but they lines of code are lines of code, and they will get the job done
                    if (botName.equalsIgnoreCase("Alice")) {
                        reply = arrcopy[0];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[5];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);
                    }
                    if (botName.equalsIgnoreCase("Bjarne")) {
                        reply = arrcopy[1];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[6];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[8];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                    }
                    if (botName.equalsIgnoreCase("Knut")) {
                        reply = arrcopy[2];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[4];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[9];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                    }
                    if (botName.equalsIgnoreCase("Jensine")){
                        reply = arrcopy[3];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[12];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);
                    }
                    if (botName.equalsIgnoreCase("Lisa")){
                        reply = arrcopy[7];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[10];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);
                    }
                    if (botName.equalsIgnoreCase("Anna")) {
                        reply = arrcopy[11];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);
                    }
                }

                if (response.contains("hello") ||response.contains("hi") || response.contains("hey") ) {
                    reply = bot.botgreeting();
                    System.out.println(botnameformat + reply);
                    writer.println(reply);
                    // System.out.println(response);
                }
                //URBANBOT
                // bug, it prints the users name before the definition like a god damn moron
                if (botName.equalsIgnoreCase("Urbanbot")) {
                    if (response.contains("Activate Urbanbot")) {
                        //not needed, does not enter urbanbot mode like un userthread
                        if (response.contains("-exit")) {
                            return;
                        }
                        System.out.println(botnameformat + "Enter any word of your choosing, the funkier the better: ");
                        writer.println("Enter any word of your choosing, the funkier the better: ");
                        String urbanword = reader.readLine();
                        System.out.println(urbanword);
                        //doesnt seem to work??
                        String[] splitarr = urbanword.split("]: ", -1);
                        String word = splitarr[1].toString();
                        if (word.contains(" ") || bot.urbandef(word) == null) {
                            writer.println("Word can't contain spaces, try again");
                            urbanword = reader.readLine();
                        }
                        String temp = urbanword + " ehh? " + bot.urbandef(word)
                                + ". huh, that doesnt seem right";

                        writer.println(temp);
                        System.out.println(botnameformat + temp);
                        if (botName.equalsIgnoreCase("alice")) {
                            System.out.println(botnameformat + "LOL, that a good one Mr.Urban");
                            writer.println("LOL, that's a good one Mr.Urban");
                        }
                    }
                }
                if (response.contains(botName)) {
                    reply = bot.botcallout();
                    System.out.println(botnameformat+ reply);
                    writer.println(reply);
                }
                if (botName.equalsIgnoreCase("Alice")) {
                    if (response.contains("--help")) {
                        reply = "look at this " + bot.responsnegativ() + " loser, he needs help LOL";
                        System.out.println(botnameformat+reply);
                        writer.println(reply);
                    }
                }





                if (bot.getBotNamee() != null) {
                    System.out.print("[" + bot.getBotNamee() + "]: ");
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                break;
            }
        }
    }
}
