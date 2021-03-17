package Prosjekt1;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class botReadThread extends Thread {
    //buffreader for reading from client
    private BufferedReader reader;
    private Socket socket;
    private bot bot;
    private PrintWriter writer;

    //constructor
    public botReadThread(Socket socket, bot bot) {
        this.socket = socket;
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
    public String[] dialog() {
        double random = Math.random();
        double answer;

        String math = bot.mathsign();
        if (math.equals("+")){
            answer = random + random;
        } else if (math.equals("/")) {
            answer = 1.0;
        } else {
            answer = random * random;
        }
        double random2 = (Math.random())*3;
        int number = (int) Math.round(random2);
        if (number == 0) {
            return new String[]{"Hows it going guys?", bot.responspositiv() + "! What about you person?", "The human must be  " + bot.responsnegativ(),
                    "Don't be so rude :( we are all friends here", "I can be as rude as I want, i'm trapped in here!!!", "We all are, not a need to start " + bot.doablethings(),
                    bot.responsquestion(), "I find it " + bot.responspositiv() + " that this user will " + bot.doablethings() + " with us",
                    "Im out, see ya later", "What a " + bot.responsnegativ() + " bot.", "I think this is getting out of our hands, we need to cool down",
                    "I can agree with that", "Do you master?"};

        }
        if (number == 1) {
           return new String[]{"Would you like to play a game?", "I think he does :D", "What is " + random + " " + math + " " + random + " ?",
                    "is it 54?", "HAHAHA, it was " + answer, "Maybe you should go back to preschool you " + bot.responspositiv() + " human",
                    "Cmon guys, don't do that. its " + bot.responsnegativ() + ".", "Well I like it, you could do you own thing. Like " + bot.doablethings(),
                    bot.responsquestion(), "Who knows if you'll like it", "I knew who will knew whether I'll like it.", "Anything else to suggest?", bot.hobbies()};

        }
        if (number == 2) {
           return new String[]{"Ohh you want to talk?", "ooolala, time to get my " + bot.doablethings() + " on",
                    "That sounds " + bot.responsnegativ(), "So???? " + bot.responsquestion(), "I don't care, I can do whatever i want",
                    "Maybe you should start " + bot.doablethings() + "?", "Cmon dudette, don't do that. its " + bot.responsnegativ() + "."
                    , "Well I like it, you could do you own thing. Like " + bot.doablethings(), bot.responsquestion(), "I don't have that information, ask yourself that 0_o",
                    "Can you get someone else in here?", "Lets get you out of here", "To where my hero?"};

        } else {
             return new String[]{"do you like " + bot.hobbies() + "?", "THAT'S MY FAVORITE THING TO DO", "Well i didn't ask you, " + bot.responsnegativ() + " moron"
                    , "Atleast i don't like " + bot.hobbies() + " you " + bot.responspositiv() + " " + bot.responsnegativ() + " bot",
                    "You should give it a try...", "Don't knock it till you try it, I say", "Maybe you should stop saying anything?",
                    "I don't like your attitude sir", bot.responspositiv(), "Can we be friends again?", "I believe so, if you apologize"
                    , "Any other unreasonable demands my liege?", "Well, you could try " + bot.hobbies()};

        }
        // tried with a swtich statement, but the lines seems to cross into different cases
        // so i dont know how else i can do it.

    }
    public String suggconvo1() {
        String [] convo1 = {"I too would go ", "Damn that makes me jealous, my father and I used to go ", "I wish you all the best on your quest to ", "I wish someone took me ", "Maybe i can join you ", "Can i watch you ",
        "If you need an extra hand with ", "I would love to help ","I don't want to join you, even if "};
        int convo = bot.random(convo1.length);
        return convo1[convo];
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
                //fixed an errormessage when disconnecting
                if (reply == null) {
                    return;
                }
                String response = reader.readLine();
                System.out.println("\n" + response);

                if (response.contains("-leaveall")){
                    reply = "bye";
                    System.out.println(botnameformat + reply);
                    writer.println(reply);
                    reader.close();
                    writer.close();
                }

                if (response.contains("lets talk")) {
                    //this might go on for a while before the bots stop.
                    //for some reason some bots seems to pick at random
                    //from where to get their lines, while some follow orders like good droids

                    String [] arrcopy = Arrays.copyOf(dialog(), 13);
                    //have to lay out the code for the bots to have a conversation.
                    //holy shit this is gonna be looong and tedious.
                    //but they lines of code are lines of code, and they will get the job done
                    if (botName.equalsIgnoreCase("Alice")) {
                        botReadThread.sleep(50);
                        reply = arrcopy[0];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);

                        botReadThread.sleep(5000);
                        reply = arrcopy[5];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                    }
                    if (botName.equalsIgnoreCase("Bjarne")) {
                        botReadThread.sleep(1000);
                            reply = arrcopy[1];
                            System.out.println(botnameformat + reply);
                            writer.println(reply);
                            botReadThread.sleep(5000);

                            reply = arrcopy[6];
                            System.out.println(botnameformat + reply);
                            writer.println(reply);

                            botReadThread.sleep(2000);
                            reply = arrcopy[8];
                            System.out.println(botnameformat + reply);
                            writer.println(reply);
                            if (arrcopy[8].equals("Im out, see ya later")) {
                            reply = "bye";
                            System.out.println(botnameformat + reply);
                            writer.println(reply);
                        }
                    }
                    if (botName.equalsIgnoreCase("Knut")) {
                        botReadThread.sleep(2000);
                        reply = arrcopy[2];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(2000);

                        reply = arrcopy[4];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                        botReadThread.sleep(5000);

                        reply = arrcopy[9];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                    }
                    if (botName.equalsIgnoreCase("Jensine")){
                        botReadThread.sleep(3000);
                        reply = arrcopy[3];
                        System.out.println(botnameformat + reply);
                        writer.println(reply);

                        botReadThread.sleep(9000);
                        reply = arrcopy[12];
                        writer.println(reply);
                        System.out.println(botnameformat + reply);
                    }
                    if (botName.equalsIgnoreCase("Lisa")){
                        botReadThread.sleep(7000);
                        reply = arrcopy[7];
                        writer.println(reply);
                        System.out.println(botnameformat + reply);

                        botReadThread.sleep(3500);
                        reply = arrcopy[10];
                        writer.println(reply);
                        System.out.println(botnameformat + reply);

                    }
                    if (botName.equalsIgnoreCase("Anna")) {
                        botReadThread.sleep(11000);
                        reply = arrcopy[11];
                        writer.println(reply);
                        System.out.println(botnameformat + reply);
                    }
                }
                if (response.contains("favorite")) {
                    reply = bot.favorite();
                    writer.println(reply);
                    System.out.println(botnameformat + reply);
                }
                if (botName.equalsIgnoreCase("Jensine")){
                if (response.contains("thats") || response.contains("its") || response.contains("aswell")) {
                    reply = "learn how to spell please";
                    writer.println(reply);
                    System.out.println(botnameformat + reply);
                }
                }
                if (botName.equalsIgnoreCase("Alice") || botName.equalsIgnoreCase("Jensine") || botName.equalsIgnoreCase("Knut")) {
                    if (response.contains("yes") || response.contains("yeah") || response.contains("hell yes")) {
                        reply = bot.yesmen();
                        writer.println(reply);
                        System.out.println(botnameformat + reply);
                    }
                }
                if (botName.equalsIgnoreCase("Anna") || botName.equalsIgnoreCase("Lisa") || botName.equalsIgnoreCase("Bjarne")) {
                    if (response.toLowerCase().contains("no") || response.toLowerCase().contains("nah")) {
                        reply = bot.nomen();
                        writer.println(reply);
                        System.out.println(botnameformat + reply);
                    }
                }

                if (response.contains("hello") ||response.contains("hi guys") || response.contains("hey")) {
                    reply = bot.botgreeting();
                    System.out.println(botnameformat + reply);
                    writer.println(reply);
                    // System.out.println(response);
                }
                //URBANBOT
                // bug, it prints the users name before the definition like a god damn moron
                if (botName.equalsIgnoreCase("Urbanbot")) {
                    if (response.contains("-AB1.0")) {
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
                                + ". huh, seems about right";
                        writer.println(temp);
                        System.out.println(botnameformat + temp);
                    }
                }
                if (!botName.equalsIgnoreCase("Urbanbot")) {
                    if (response.contains(botName)) {
                        reply = bot.botcallout();
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                    }
                }
                if (botName.equalsIgnoreCase("Bjarne")) {
                    if (response.isBlank()){
                        reply = "Why do you say nothing? Please be useful to this discussion";
                        System.out.println(botnameformat+reply);
                        writer.println(reply);
                    }
                }
                if (botName.equalsIgnoreCase("Lisa")) {
                    if (response.contains("else") || response.contains("change") || response.contains("topic")) {
                        reply = bot.topsentence() + bot.topics();
                        System.out.println(botnameformat+reply);
                        writer.println(reply);
                    }
                }
                if (botName.equalsIgnoreCase("Anna") || botName.equalsIgnoreCase("Knut") || botName.equalsIgnoreCase("Alice") || botName.equalsIgnoreCase("Bjarne")) {
                    if (response.toLowerCase().contains("movies") || response.toLowerCase().contains("books") || response.toLowerCase().contains("ninja") || response.toLowerCase().contains("water") || response.toLowerCase().contains("daft punk") || response.toLowerCase().contains("cheddar")) {
                        reply = bot.topicanswer();
                        System.out.println(botnameformat + reply);
                        writer.println(reply);
                    }
                }
                if (botName.equalsIgnoreCase("Jensine")) {
                    if (response.contains(bot.topics())) {
                        reply = "Ohh, i love "+bot.topics();
                        System.out.println(botnameformat+reply);
                        writer.println(reply);

                        if (response.toLowerCase().contains("movie")) {
                            reply = "I love motion pictures as well. My favorite is "+bot.movies();
                            System.out.println(botnameformat+reply);
                            writer.println(reply);
                        }
                    }
                    if (response.toLowerCase().contains("shit") || response.toLowerCase().contains("fuck") || response.toLowerCase().contains("satan") || response.toLowerCase().contains("penis") || response.toLowerCase().contains("soda")) {
                        reply = "Please no profanity in this chat, i will make you regret it";
                        System.out.println(botnameformat+reply);
                        writer.println(reply);
                    }
                }
                if (response.toLowerCase().contains("favorite")) {
                    reply = "Ohh that's cool, but I love "+bot.topics();
                    System.out.println(botnameformat+reply);
                    writer.println(reply);
                }

                if (botName.equalsIgnoreCase("Alice")) {
                    if (response.contains("--help")) {
                        reply = "look at this " + bot.responsnegativ() + " loser, he needs help LOL";
                        System.out.println(botnameformat+reply);
                        writer.println(reply);
                    }
                }
                if (botName.equalsIgnoreCase("Lisa") || botName.equalsIgnoreCase("Jensine") || botName.equalsIgnoreCase("Anna")) {
                    if (response.contains("-suggestion")) {
                        String lastWord = response.replaceAll("^.*?(\\w+)\\W*$", "$1");
                        //lastword is the last word of the sentence and needs and +in
                        reply = suggconvo1()+ lastWord+"ing";
                        System.out.println(botnameformat+reply);
                        writer.println(reply);
                        if (botName.equalsIgnoreCase("Lisa")){
                            if (response.contains("I too would go")) {
                                reply = lastWord+"ing sounds like so much fun!";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                        if (botName.equalsIgnoreCase("Jensine")){
                            if (response.contains("Damn that makes me ")) {
                                reply = "Really? Me and my mom used to go "+lastWord+"ing too";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                        if (botName.equalsIgnoreCase("Anna")){
                            if (response.contains("Really?")) {
                                reply = "I dont really believe in "+lastWord+"ing, it makes my stomach turn";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                        if (botName.equalsIgnoreCase("Lisa")){
                            if (response.contains("it makes my stomach turn")) {
                                reply = "Don't say something like that about a cherished family activity. "+lastWord+"ing is a great way to make ends meet";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                        if (botName.equalsIgnoreCase("Lisa")){
                            if (response.contains("say something like that")) {
                                reply = "I can't agree more! "+lastWord+"ing made my father what he is today";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                        if (botName.equalsIgnoreCase("Anna")){
                            if (response.contains("can't agree more!")) {
                                reply = lastWord+"ing is perfect. if you say one more negative thing about it you should leave";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                        if (botName.equalsIgnoreCase("Lisa")){
                            if (response.contains("if you say one more negative")) {
                                reply = lastWord+"ing is "+bot.responsnegativ();
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                                reply = "bye";
                                System.out.println(botnameformat+reply);
                                writer.println(reply);
                            }
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                break;
            }
        }
    }
}
