import com.java.learnning.IUserService;
import com.java.learnning.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/*
    处理客户端请求
 */
public class ProcessorHandler implements Runnable {
    Socket socket = null;
    Object service = null;

    public ProcessorHandler(Socket socket, IUserService user) {
        this.socket = socket;
        this.service = user;
    }

    @Override
    public void run() {
        //1、拿到输入流，读取到request对象
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            ServerRequest request = (ServerRequest) inputStream.readObject();
            //2、反射调用本地方法
            Object result = invoke(request);

            //将结果通过流返回到客户端
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if(null!=outputStream){
                    outputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private Object invoke(ServerRequest request) {
        Object result = "";
        //获取参数列表
        Object[] args = request.getArgs();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }

        try {
            //反射拿到类
            Class clazz = Class.forName(request.getClassName());
            //获取方法
            Method method = clazz.getMethod(request.getMethod(), types);
            //通过反射执行客户端调用的方法，拿到返回值
            result = method.invoke(service, args);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {

            return result;
        }

    }
}
