package cn.axboy.demo.task.aop;

import cn.axboy.demo.task.annotation.CloudTask;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ScheduledAspect {

    @Pointcut(value = "@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void scheduledCut() {
        log.debug("scheduledCut");
    }

    @Around("scheduledCut()")
    public void scheduledCut(ProceedingJoinPoint point) throws Throwable {
        Signature sig = point.getSignature();
        if (!(sig instanceof MethodSignature)) {
            log.error(">>> Not MethodSignature, Skip aop");
            point.proceed();
            return;
        }
        MethodSignature methodSig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSig.getName(), methodSig.getParameterTypes());
        String className = target.getClass().getName();
        String methodName = currentMethod.getName();
        long startTime = System.currentTimeMillis();
        log.info(">>> {}.{}", className, methodName);
        CloudTask cloudTask = currentMethod.getAnnotation(CloudTask.class);
        if (cloudTask != null && !tryLock(cloudTask)) {
            log.info("Skip Task {}", cloudTask.tag());
            return;
        }
        point.proceed();
        unLock(cloudTask);
        log.info("<<< {}.{} - {}", className, methodName, System.currentTimeMillis() - startTime);
    }

    private boolean tryLock(CloudTask cloudTask) {
        return cloudTask == null || System.currentTimeMillis() % 1000 > 600;
    }

    private void unLock(CloudTask cloudTask) {

    }
}
