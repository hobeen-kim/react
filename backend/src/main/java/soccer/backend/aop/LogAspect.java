package soccer.backend.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LogAspect {

    private static final Logger errorFileLogger = LoggerFactory.getLogger("ERROR_FILE_LOGGER");

    @Around("execution(* soccer.backend..*Controller.*(..))")
    public Object Controllerlog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication != null ? authentication.getName() : "Unknown";

        log.info("{} called the method: {}", name, methodName);
        Object result = joinPoint.proceed();
        log.info("{} method has finished: {}", name, methodName);
        return result;
    }

    @Around("execution(* soccer.backend..*ExceptionHandler.*(..)) && !execution(* soccer.backend..*ExceptionHandler.handleException(..))")
    public Object IntendedExceptionHandlerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication != null ? authentication.getName() : "Unknown";
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Exception e = null;

        for (Object arg : args) {
            if(arg instanceof Exception) {
                e = (Exception) arg;
                break;
            }
        }

        if (e != null) {
            log.info("{} 실행", methodName);
            log.info("{} Error detail: ", name, e);
        } else {
            log.warn("No exception found in the method arguments for {}", methodName);
        }

        log.info("{} called the method: {}", name, methodName);
        Object result = joinPoint.proceed();
        log.info("{} method has finished: {}", name, methodName);
        return result;
    }

    @Around("execution(* soccer.backend..*ExceptionHandler.handleException(..))")
    public Object ExceptionHandlerLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication != null ? authentication.getName() : "Unknown";
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Exception e = null;

        for (Object arg : args) {
            if(arg instanceof Exception) {
                e = (Exception) arg;
                break;
            }
        }

        if (e != null) {
            errorFileLogger.error("{} 실행", methodName);
            errorFileLogger.error("{} Error detail: ", name, e);
        } else {
            log.error("No exception found in the method arguments for {}", methodName);
        }

        log.info("{} called the method: {}", name, methodName);
        Object result = joinPoint.proceed();
        log.info("{} method has finished: {}", name, methodName);
        return result;
    }

}
