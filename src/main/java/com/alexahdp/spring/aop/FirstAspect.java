package com.alexahdp.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FirstAspect {
    /*
    @within - check annotation on the class level
     */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {
    }

    /*
    within - checks class type name
     */
    @Pointcut("@within(com.alexahdp.spring.service.*)")
    public void isServiceLayer() {
    }

    /*
    this -check AOP proxy class type
    target - check target object class type
     */
    @Pointcut("this(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {
    }

    /*
     @annotation - check annotation on the method level
     Available operations: &&, ||, !
     */
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {
    }

    /*
     */
    @Pointcut("isControllerLayer() && @args(com.alexahdp.spring.validation.UserInfo,...)")
    public void hasUserInfoParamAnnotation() {
    }

    /*
    bean - check bean name
     */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    /*
    bean - check bean name
     */
    @Pointcut("execution(public * com.alexahdp.spring.service.*")
    public void anyFindByIdServiceMethod() {
    }

    @Before("anyFindByIdServiceMethod()")
    public void addLogging() {
        log.info("Service method findById() called");
    }

    @AfterReturning(value = "anyFindByIdServiceMethod() && target(service", returning = "result")
    public void addLoggingAfterReturning(Object result) {
        log.info("Service method findById() returned: {}", result);
    }

    @AfterThrowing(value = "anyFindByIdServiceMethod() && target(service)", throwing = "exception")
    public void addLoggingAfterThrowing(Exception exception) {
        log.error("Service method findById() threw an exception: {}", exception.getMessage());
    }

    @After("anyFindByIdServiceMethod() && target(service)")
    public void addLoggingAfterFinally(Object service) {
        log.info("After (finally) - invoked method in class {}", service);
    }

    @Around("anyFindByIdServiceMethod() && target(service) && args(id)")
    public Object addLoggingAround(
            ProceedingJoinPoint proceedingJoinPoint,
            Object service,
            Object id
    ) throws Throwable {
        log.info("Around Before - invoked method in class {}", proceedingJoinPoint.getTarget().getClass().getName());
        try {
            Object result = proceedingJoinPoint.proceed();
            log.info("Around After - invoked method in class {}", proceedingJoinPoint.getTarget().getClass().getName());
            return result;
        } catch (Throwable ex) {
            log.info("Around After Throwing - invoked method in class {}", proceedingJoinPoint.getTarget().getClass().getName());
            throw ex;
        } finally {
            log.info("Around After (finally) - invoked method in class {}", proceedingJoinPoint.getTarget().getClass().getName());
        }
    }
}
