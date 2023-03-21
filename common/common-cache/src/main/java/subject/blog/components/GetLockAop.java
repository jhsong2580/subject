package subject.blog.components;


import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import subject.blog.annotation.GetLock;
import subject.blog.utils.CustomSpringELParser;
import subject.blog.utils.lock.CustomLock;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class GetLockAop {

    private static final String REDISSON_KEY_PREFIX = "Lock_";


    @Around("@annotation(subject.blog.annotation.GetLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final GetLock redisManageLock = method.getAnnotation(GetLock.class);

        String key = REDISSON_KEY_PREFIX + CustomSpringELParser.getDynamicValue(
            signature.getParameterNames(), joinPoint.getArgs(),
            new String[]{redisManageLock.key()});

        try (CustomLock customLock = new CustomLock(key, redisManageLock.waitTime(),
            redisManageLock.timeUnit())) {
            return joinPoint.proceed();

        } catch (Exception e) {
            throw e;
        }
    }


}
