package andr.Udp;

import java.net.SocketAddress;

public class Client {
    private SocketAddress socketAddress;
    private MyPackage myPackage;

    public MyPackage getMyPackage() {
        return myPackage;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setMyPackage(MyPackage myPackage) {
        this.myPackage = myPackage;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }
}
