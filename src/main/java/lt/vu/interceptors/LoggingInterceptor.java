package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@MyInterceptor
@Interceptor
public class LoggingInterceptor implements Serializable {

    @AroundInvoke
    public Object doSomeStuff(InvocationContext ctx) throws Exception{
        System.out.println("Kvieciama funkcija: " + ctx.getMethod().toString());
        Object result = ctx.proceed();
        if (result != null)
            System.out.println("Gautas objektas: " + result.toString());
        return result;
    }
}