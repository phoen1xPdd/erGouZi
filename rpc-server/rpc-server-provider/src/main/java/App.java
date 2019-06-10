import com.java.learnning.IUserService;

public class App {
    public static void main(String[] args) {
        IUserService user = new UserServiceImpl();
        System.out.println("erfhger");
        ProxyServer proxyServer = new ProxyServer();
        proxyServer.publisher(666,user);
    }
}
