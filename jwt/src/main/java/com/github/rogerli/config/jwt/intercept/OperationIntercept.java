package com.github.rogerli.config.jwt.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.rogerli.config.jwt.auth.JwtAuthenticationToken;
import com.github.rogerli.config.jwt.model.UserContext;
import com.github.rogerli.framework.annotation.OperationAction;
import com.github.rogerli.system.operation.entity.Operation;
import com.github.rogerli.system.operation.service.OperationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class OperationIntercept {

    @Autowired
    private OperationService operationService;

    @Pointcut("@annotation(com.github.rogerli.framework.annotation.OperationAction)")
    public void logOperate() {

    }

    @Around(value = "logOperate() && @annotation(action)")
    public Object process(ProceedingJoinPoint point, OperationAction action) throws Throwable {
        System.out.println("@Around：执行目标方法之前");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
//        for (Object arg :
//                args) {
//            System.out.println("参数对象：" + arg.getClass());
//        }
        Object arg = point.getArgs()[0]; // 默认为第一个参数对象记录
        Object target = point.getTarget();
        Object oldObj = null;
        Object newObj = null;
        Operation operation = null;
        String id = null;
        ObjectMapper mapper = new ObjectMapper();
        if (arg != null && arg instanceof Serializable) {
            BeanWrapper bw = new BeanWrapperImpl(arg);
            Object objId = bw.getPropertyValue("id");
            if (objId != null) {
                id = (String) bw.getPropertyValue("id");
                if (StringUtils.hasText(id)) {
                    Method method = target.getClass().getMethod("findByKey", Object.class);
                    oldObj = method.invoke(target, id);
                }
            }
        }
        //用改变后的参数执行目标方法
        newObj = point.proceed(args);
        if (newObj instanceof Serializable
                && newObj != null
                && !newObj.equals(oldObj)) {
            operation = new Operation();
            operation.setWorkId(id);
            operation.setWorkType(action.type());
            operation.setWorkClass(arg.getClass().getName());
            operation.setOldValue(mapper.writeValueAsString(oldObj));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JwtAuthenticationToken token = null;
            if (authentication instanceof JwtAuthenticationToken) {
                token = (JwtAuthenticationToken) authentication;
            }
            if (token != null) {
                UserContext context = (UserContext) token.getPrincipal();
                operation.setModifyUser(context.getUsername());
            }
            operation.setNewValue(mapper.writeValueAsString(newObj));
            operation.setModifyDate(new Date());
            operationService.insert(operation);
        }
        System.out.println("@Around：执行目标方法之后");
        System.out.println("@Around：被织入的目标对象为：" + target);
        return newObj;
    }

//    @Before("logOperate()")
//    public void permissionCheck(JoinPoint point) {
//        System.out.println("@Before：模拟权限检查");
//        System.out.println("@Before：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
//        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
//    }

//    @AfterReturning(pointcut = "logOperate()",
//            returning = "result")
//    public void action(JoinPoint point, Object result) {
//        System.out.println("@AfterReturning：模拟日志记录功能...");
//        System.out.println("@AfterReturning：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@AfterReturning：参数为：" +
//                Arrays.toString(point.getArgs()));
//        System.out.println("@AfterReturning：返回值为：" + result);
//        System.out.println("@AfterReturning：被织入的目标对象为：" + point.getTarget());
//    }

//    @After("logOperate()")
//    public void releaseResource(JoinPoint point) {
//        System.out.println("@After：模拟释放资源...");
//        System.out.println("@After：目标方法为：" +
//                point.getSignature().getDeclaringTypeName() +
//                "." + point.getSignature().getName());
//        System.out.println("@After：参数为：" + Arrays.toString(point.getArgs()));
//        System.out.println("@After：被织入的目标对象为：" + point.getTarget());
//    }

//    /**
//     * 在目标方法非正常执行完成, 抛出异常的时候会走此方法
//     *
//     * @param jp
//     * @param ex
//     */
//    @AfterThrowing(pointcut = "logOperate()", throwing = "ex")
//    public void doAfterThrowing(JoinPoint jp, Exception ex) {
//        System.out.println("===========执行异常通知============");
//    }

}