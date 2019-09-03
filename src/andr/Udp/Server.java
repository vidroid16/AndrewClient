package andr.Udp;

import java.io.IOException;
import java.nio.channels.Selector;

public class Server {
    public void on() {
        try {
            Selector selector = Selector.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
