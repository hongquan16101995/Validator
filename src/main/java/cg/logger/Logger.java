package cg.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Logger {

    @AfterThrowing(pointcut = "execution(public * cg.service.ProductService.*(..))", throwing = "ex")
    public void log(JoinPoint joinPoint, Exception ex) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] co loi xay ra: %s.%s%s: %s%n", className, method, args, ex.getMessage());
    }

    @Before("execution(public * cg.service.ProductService.*(..))")
    public void log1(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] log truoc khi run method: %s.%s%s", className, method, args);
        System.out.println();
    }
}
