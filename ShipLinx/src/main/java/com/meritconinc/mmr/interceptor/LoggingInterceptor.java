/**
 * 
 */
package com.meritconinc.mmr.interceptor;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class LoggingInterceptor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
  private static final Logger logger = Logger.getLogger(LoggingInterceptor.class);

  /**
   * default constructor
   * 
   */
  public LoggingInterceptor() {
  }

  /**
   * 
   * @param method
   * @param args
   * @param target
   * @throws Throwable
   */
  public void before(Method method, Object[] args, Object target) throws Throwable {
    logger.info(target.getClass() + " > " + method.getName());
  }

  /**
   * 
   * @param returnValue
   * @param method
   * @param args
   * @param target
   * @throws Throwable
   */
  public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
      throws Throwable {
    logger.info(target.getClass() + " < " + method.getName());
  }

  /**
   * 
   * @param m
   * @param args
   * @param target
   * @param ex
   */
  public void afterThrowing(Method m, Object[] args, Object target, Throwable ex) {
    logger.info(target.getClass() + ": Exception in method - " + m.getName(), ex);
  }

}