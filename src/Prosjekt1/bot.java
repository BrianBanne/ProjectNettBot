package Prosjekt1;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//will make bot its own client
public class bot {

    private String hostname;
    private int port;
    private String name;


    public bot(String hostname, int port, String name) {
        this.hostname = hostname;
        this.port = port;
        this.name = name;

    }

    static int random(int arrlengde) {
        int random;
        return (random = (int) (Math.random()* arrlengde));

    }
    static String mathsign() {
        String [] mathsign = {"+","/","*","-"};
        int sign = random(mathsign.length);
        return mathsign[sign];
    }
    //picks up defenition of a word from urban dictionary
    static String urbandef(String string) {
        //got this code from rapidapi urbandictionary
        // it was autogenerated so no credit to me here
        // but from what i understand this practically the only way to do it since
        // its just a request and a reply from the hosting server.
        while (!string.contains(" ")) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=" + string))
                    .header("x-rapidapi-key", "883af63807msh7012751fa72f9fep15f673jsn9e9b6f6157c3")
                    .header("x-rapidapi-host", "mashape-community-urban-dictionary.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = null;
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            //to cut the response to what i wanted i.e. the definition
            // my code here
            string = response.body().toString();
            String hello = string.substring(24, 400);
            //700 cause its seems long enough, but if there are more characters, i can just blame it on the bots memory xD
            String[] hello1 = hello.split("\",");
            String hello2 = hello1[0];
            String seperator = "\\\\r\\\\n";
            String[] hello4 = hello2.split(seperator);

            return hello4[0].toString();
        }
        return null;
    }
    // a bunch of methods for getting name and sentence structures.
    public static String botgreeting() {
        String [] greetingarr = {"Hi!", "Hello there :)", "Heya", "Hi, how are you?"};
        int greeting = random(greetingarr.length);
        return  greetingarr[greeting];
    }
    public static String botcallout() {
        String[] whymearr = {"Why do you use my name?", "stop harrasing me", "I value that you think that high of my opinion"
        ,"I think you should tell some other guy", "Fuck you", "Ask the guy above me"};
        int callout = random(whymearr.length);
        return whymearr[callout];
    }


    public static void botconvo() {
        String reply ;
    }

    public static String responsnegativ() {
        String [] detailnegativearr = {"weird", "idiotic", "ridiculous", "terribly stupid", "horrible", "drastic"};
        int detailneg = random(detailnegativearr.length);
        return  detailnegativearr[detailneg];
    }
    public static String responspositiv() {
        String [] responsadjektivpos = {"super", "wonderful", "fantastic", "idiot-proof", "AMAZING", "great..."};
        int detailpos = random(responsadjektivpos.length);
        return responsadjektivpos[detailpos];
    }
    public static String doablethings() {
        String [] doablethings = {"talking", "licking", "stirring the pot", "make me a baguette", "lusting", "dancing salsa"};
        int randomdoable = random(doablethings.length);
        return doablethings[randomdoable];
    }

    public static String responsquestion() {
        String [] responsquestion = {"what will you do at this moment?", "why would you do that?", "why would you invite that person?", "when should we do that?",
                "where do you suggest to do it?", "why are you looking at me like that?"};
        int question = random(responsquestion.length);
        return responsquestion[question];
    }
    public static String hobbies() {
        String [] hobby = {"soccer","movies","starring in adult films","staring at feet","walking the parrot",
        "riding a bike", "smelling people at the gym", "writing poems about your love for cooks"};
        int hobbi = random(hobby.length);
        return hobby[hobbi];
    }
    public static String yesmen() {
        String [] yes = {"I totally agree", "What an honest answer", "I just shit my pants", "can you scream it", "Told you so...", "fucking great",
        "can you promise that?", "thats right!!!", ""};
        int yesguys = random(yes.length);
        return yes[yesguys];
    }
    public static String topics() {
        String [] subject = {"movies", "books", "the art of not giving a fuck", "Ninja turtles","Water", "the cheese cheddar", "Daft Punks breakup"};
        int sub = random(subject.length);
        return  subject[sub];
    }
    public static String topsentence(){
        String [] top = {"I want to talk about ", "Do you like ", "Hey, want to hear more about ", "Please switch subject to "};
        int topic = random(top.length);
        return top[topic];
    }
    public static String movies() {
        String [] mov = {"Transformers", "Star Wars","A Serbian Film", "Transporter", "Pixar movies"};
        int movieint = random(mov.length);
        return mov[movieint];
    }
    public static String topicanswer() {
        String [] answer = {"Ohhh I love that", "It's so sad that you like that", "would give up my tits to meet the creators", "Absolutely we can talk about that","We should have a deep discussion about your preferences",
        "Lets do it", "Fuck yeah i love that suggestion", "What is your opinion?", "Lets shit our pants!!!", "I think i love you",
        "I feel strongly about that", "Can someone give me a papertowel?", "I don't want to talk about that, change the subject"};
        int answ = random(answer.length);
        return answer[answ];
    }
    public static String favorite() {
        String [] fav = {"My favorite thing is bears", " I don't like that at all", "I am an avid enjoyer aswell", "you look like someone who likes that",
        "I don't want to hear about it", "I love movies", "Why???", "cool story bro", "What makes you like that?", responsquestion(), responspositiv(), "Glad to hear it"};
        int favo = random(fav.length);
        return fav[favo];
    }
    public static String nomen() {
        String [] no = {"Wow, you couldn't be more wrong", "right....", "lets ask some other person", "you actually mean that?", "really?",
        "I dont agree with the person above", "I want to talk about something else", "You looking for a fight?",
        "shut up guy person", "you should go skydiving without a parachute", "I'm quite never ready for that", "Wowwww, yUorE sO brAvE",
        "I insist on you doing that"};
        int noguys = random(no.length);
        return no[noguys];
    }




    public void exe() {
        try {
            Socket socket = new Socket(hostname, port);
            //sends message to server verifying this is a bot.
            System.out.println("\nConnected to server :D");
            setBotName(name);
            //start new special writer and reader for bots
            new botReadThread(socket, this).start();

            //catches not finding host and any I/O errors.
        }catch (UnknownHostException i) {
            System.out.println("Server not found: " + i.getMessage());
        }catch (IOException e) {
            System.out.println("Error." +e.getMessage());
        }

    }

    //setter and getter for username hashset
    void setBotName(String username) {
        this.name = username;
    }
     String getBotNamee() {
        return this.name;
    }

    public static void main(String[] args) {
        //hostname and port must be used in the terminal when executing the program.
        //checks if more than 2 entries are present, if so then dont connect
        // java client hostname portnumber botname
        if (args.length < 3) return;
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String name = args[2];

        //maybe not needing this. let see
        if (name.toString().equalsIgnoreCase("Alice") || name.toString().equalsIgnoreCase("Knut")) {
            System.out.println("hi");
        }
        //if conditions are met, start client and connect to server with exe
        bot bot = new bot(hostname,port, name);
        bot.exe();

    }
}
