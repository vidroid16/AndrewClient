package andr;

import andr.Udp.MyPackage;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {

    public static Hashtable<Integer, Passport> hashtable = new Hashtable<>();
    public static ByteBuffer buffer = ByteBuffer.allocate(800000);
    public static ArrayList<Integer> userIds = new ArrayList<>();
    public static final String mailRegexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static void main(String[] args) throws IOException {
        FileController fileController = new FileController("file.xml");
        Scanner scanner = new Scanner(System.in);
        /*FileController controller = new FileController("file.xml");*/


        if(args.length == 0){
            System.out.println("Введите IP (IP не был введен)");
            System.exit(229);
        }

        if(args.length == 1){
            System.out.println("Введите порт (порт не был введен)");
            System.exit(228);
        }

        DatagramSocket socket = new DatagramSocket();

        InetAddress IP = InetAddress.getByName(args[0]);

        int port = Integer.parseInt(args[1]);

        int  user_id = 0;



        while(true){
            try{
                MyPackage sPack = null;
                if(user_id == 0){
                    System.out.println("Введите login - для входа \nВведите register - для регистрации ");
                    while(sPack == null){
                        String enter = scanner.nextLine();
                        if(enter.equals("login")){
                            sPack = Main.log();
                        }else if (enter.equals("register")){
                            sPack = Main.reg();
                        }else{
                            System.out.println("Введите login - для входа\n Введите register - для регистрации");
                        }
                    }
                    //sPack = new MyPackage(100, user_id, null);
                }else{
                    String line = scanner.nextLine();
                    //System.out.println("ВЫ ВВЕЛИ КОММАНДУ");
                    if(line.equals("load")){
                        hashtable = fileController.readCollection();
                        ArrayList<Passport> userPassports = (ArrayList<Passport>) Main.hashtable.entrySet().stream().map(p->p.getValue()).collect(Collectors.toList());
                        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)){
                            oos.writeObject(userPassports);
                            sPack = new MyPackage(10, user_id, baos.toByteArray());
                        }catch (IOException e){
                            System.err.println("Serialization error");
                        }
                    }else {
                        sPack = new MyPackage(0, user_id, line.getBytes());
                    }
                }

                byte[] sbytes = sPack.getSerialized();
                //System.out.println("Sending "+ sbytes.length +" bytes "+ IP + port);
                /*try {
                    if (!socket.getInetAddress().isReachable(2)) continue;
                }catch (NullPointerException e){
                    continue;
                }*/
                socket.setSoTimeout(3000);
                socket.send(new DatagramPacket(sbytes, sbytes.length, InetAddress.getLocalHost(), port));
                byte[] rbytes = new byte[800000];

                DatagramPacket recievedDP = new DatagramPacket(rbytes, rbytes.length);

                socket.receive(recievedDP);

                MyPackage recievedPack = new MyPackage(recievedDP.getData());
                if (user_id == 0) {
                    user_id = recievedPack.getUserId();
                }
                if (recievedPack.getId() == 11){
                    try(ByteArrayInputStream bais = new ByteArrayInputStream(recievedPack.getData());
                        ObjectInputStream ois = new ObjectInputStream(bais)){
                        ArrayList<Passport> userPassports = (ArrayList<Passport>) ois.readObject();
                        Hashtable<Integer, Passport> table = new Hashtable<>();
                        userPassports.forEach(p->table.put(p.hashCode(),p));
                        fileController.writeCollection(table);
                    }catch (ClassNotFoundException e){
                        System.out.println("Произошла ошибка");
                    }
                }else{
                    if (recievedPack.getData() != null) System.out.println(new String(recievedPack.getData()));
                   // if (recievedPack.getData() != null) System.out.println(recievedPack.getId());
                }
            }catch(SocketTimeoutException e){
                //System.out.println("Соединение с сервером потеряно!");
                continue;
            }
        }




    }
    private static MyPackage log(){
        Scanner scanner = new Scanner(System.in);
        String login = "";
        String password = "";
        System.out.print("Введеите логин: ");
        login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        password = scanner.nextLine();

        if (login.matches("^[a-z\\d][a-z\\d]*[_-]?[a-z\\d]*[a-z\\d]$") && password.matches("^[a-z\\d][a-z\\d]*[_-]?[a-z\\d]*[a-z\\d]$")
                            && login.length()>3 && login.length()<13 && password.length()>6 && password.length()<13){
            String data = login.concat(" " + password);
            return new MyPackage(200, 0, data.getBytes());
        }else{
            System.out.println("Некорректно введены данные для входа");
            return null;
        }
    }
    private static MyPackage reg(){
        Scanner scanner = new Scanner(System.in);
        String login = "";
        String password = "";
        String mail = "";
        System.out.print("Введеите почту: ");
        mail = scanner.nextLine();
        System.out.print("Введеите логин: ");
        login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        password = scanner.nextLine();

        if(mail.matches(Main.mailRegexp) && login.matches("^[a-z\\d][a-z\\d]*[_-]?[a-z\\d]*[a-z\\d]$")
                        && password.matches("^[a-z\\d][a-z\\d]*[_-]?[a-z\\d]*[a-z\\d]$")
                        && login.length()>3 && login.length()<13 && password.length()>6 && password.length()<13){
            String rez = mail.concat(" "+ login + " " + password);
            return new MyPackage(201, 0, rez.getBytes());
        }else{
            System.out.println("Некорректно введены данные для регистрации.\n " +
                    "Проверьте, что ваши логин и пароль состоят только из латинсиких букв и цифр, и имеют длину > 3 и <13 символов");
            return null;
        }
    }
    public static class ProgramPauser{
        public static void pause(String message){
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public static void pauseErr(String message){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.err.println(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


