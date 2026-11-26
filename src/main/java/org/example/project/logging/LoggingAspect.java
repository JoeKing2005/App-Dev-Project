package org.example.project.logging;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.project.service.BusinessRuleException;
import org.example.project.service.DuplicateJokeException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* org.example.project.service.JokeServiceImpl.addJoke(..))")
    public void addJokeMethod() {}

    @AfterThrowing(pointcut = "addJokeMethod()", throwing = "ex")
    public void logRejectedJoke(Exception ex) {
        if (ex instanceof BusinessRuleException || ex instanceof DuplicateJokeException) {
            log.warn("Attempted invalid joke creation: {}", ex.getMessage());
        }
    }
}
