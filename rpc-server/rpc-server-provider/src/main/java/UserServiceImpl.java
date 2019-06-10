import com.java.learnning.IUserService;

public class UserServiceImpl implements IUserService {
    public String sayHello(String text) {
        System.out.println("hello,hello,hello,hello");
        return "hello"+text;
    }

    public boolean changeName(String newName) {
        System.out.println("change userName success");
        return true;
    }
}
