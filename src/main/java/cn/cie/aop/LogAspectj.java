package cn.cie.aop;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@Component
@Aspect
public class LogAspectj {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Before("execution(* cn.cie.controller.*Controller.*(..)) && !execution( * cn.cie.controller.AbstractController.*(..))")   // 切面为controller中的所有方法
    public void logParams(JoinPoint joinPoint) {
        PropertyConfigurator.configure("log4j-acc.properties");
        logger.info("***************************************************************************");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        logger.info("url={}" + request.getRequestURL());

        //method
        logger.info("method={}" + request.getMethod());

        //ip
        logger.info("ip={}" + request.getRemoteAddr());

        //类方法
        logger.info("class_method={}" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        logger.info("args={}" + joinPoint.getArgs());
    }

}
