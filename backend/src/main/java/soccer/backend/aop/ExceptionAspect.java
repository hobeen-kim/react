package soccer.backend.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class ExceptionAspect {

    @Around("execution(* soccer.backend.controller..*(..)) || execution(* soccer.backend.auth.controller..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{

        try{
            Object result = joinPoint.proceed();
            return result;
        }catch (RuntimeException e) {

            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

}
