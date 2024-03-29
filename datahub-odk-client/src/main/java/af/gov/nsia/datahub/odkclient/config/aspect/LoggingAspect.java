package af.gov.nsia.datahub.odkclient.config.aspect;

import af.gov.nsia.datahub.odkclient.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("@annotation(af.gov.nsia.datahub.odkclient.config.aspect.Loggable)")
    public void annotatedBeforeLoggingAdvice(JoinPoint joinPoint) throws Throwable{
        log.info("[" + joinPoint.getSignature().getDeclaringTypeName() + "]" +
                "[" + MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName() + "] " +
                "Input Params : " + Utility.objectToJson(joinPoint.getArgs()));
    }

}
