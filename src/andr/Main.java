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
                    //log();
                    sPack = new MyPackage(100, user_id, null);
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
    public MyPackage log(){
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        while(true) {
            login = scanner.nextLine();
            password = scanner.nextLine();
            if (login.matches("[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}") && password.matches("/[A-Za-z0-9]/")) break;
            System.out.println("некорректно введены данные для входа");
        }
        String data = login.concat(" " + password);
        return new MyPackage(200, 0, data.getBytes());
    }
    public MyPackage reg(){
        Scanner scanner = new Scanner(System.in);
        String login;
        while(true){
            login = scanner.nextLine();
            if(login.matches("[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}")) break;
            System.out.println("Некорректно введена почта");
        }
        return new MyPackage(200, 0, login.getBytes());
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


