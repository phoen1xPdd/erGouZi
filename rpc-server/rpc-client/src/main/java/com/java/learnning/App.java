package com.java.learnning;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ProxyCllent proxyCllent = new ProxyCllent();
        IUserService service =proxyCllent.clientProxy(IUserService.class,"127.0.0.1",666);
        String result = service.sayHello("hahahahlalala");
        System.out.println( result );
    }
}
