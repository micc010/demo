package com.github.rogerli.system.log.intercept;

import com.github.rogerli.system.log.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogIntercept {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.github.rogerli.framework.annotation.LogAction)")
    public void logOperate() {

    }

    @Around("logOperate()")
    public Object process(ProceedingJoinPoint point) throws Throwable {

//        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        Object object = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("@Around：执行目标方法之前");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        for (Object arg :
                args) {
            System.out.println("参数对象：" + arg.getClass());
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return returnValue;
    }

    @Before("logOperate()")
    public void permissionCheck(JoinPoint point) {
        System.out.println("@Before：模拟权限检查");
        System.out.println("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
    }

    @AfterReturning(pointcut = "logOperate()",
            returning = "result")
    public void log(JoinPoint point, Object result) {
        System.out.println("@AfterReturning：模拟日志记录功能...");
        System.out.println("@AfterReturning：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@AfterReturning：参数为：" +
                Arrays.toString(point.getArgs()));
        System.out.println("@AfterReturning：返回值为：" + result);
        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());

    }

    @After("logOperate()")
    public void releaseResource(JoinPoint point) {
        System.out.println("@After：模拟释放资源...");
        System.out.println("@After：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
    }

    /**
     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
     *
     * @param jp
     * @param ex
     */
    @AfterThrowing(pointcut = "logOperate()", throwing = "ex")
    public void doAfterThrowing(JoinPoint jp, Exception ex) {
        System.out.println("===========执行异常通知============");
    }

}