import com.java.learnning.IUserService;
import com.java.learnning.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    接受客户端的socket请求
 */
public class ProxyServer {

    ExecutorService executors = Executors.newCachedThreadPool();//创建一个线程池
    public void publisher(int port, IUserService user) {
        Socket socket = null;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();//创建socket连接
            executors.execute(new ProcessorHandler(socket,user));//交给线程池处理读写流操作
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=socket){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
