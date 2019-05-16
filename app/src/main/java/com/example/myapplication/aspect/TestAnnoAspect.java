package com.example.myapplication.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by hulifei on 2019/4/17.
 */
@Aspect
public class TestAnnoAspect {

//    @Pointcut("execution(* com.example.myapplication.aspect.AspectTestActivity.test(..))")
    @Pointcut("execution(@com.example.myapplication.aspect.TestAnnoTrace * *(..))")
//    @Pointcut("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    public void pointcut(){

    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName(); // 方法名：test
        Method method = signature.getMethod(); // 方法：
        Class returnType = signature.getReturnType(); // 返回值类型：void
        Class declaringType = signature.getDeclaringType(); // 方法所在类名：
        String[] parameterNames = signature.getParameterNames(); // 参数名：view
        Class[] parameterTypes = signature.getParameterTypes(); // 参数类型：View

       TestAnnoTrace annotation = method.getAnnotation(TestAnnoTrace.class);
        String value = annotation.value();

        Log.d("abc","before");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d("abc","around");
        joinPoint.proceed();
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        Log.d("abc","after");
    }

    @AfterReturning("pointcut()")
    public void afterreturning(JoinPoint joinPoint,Object returnValue){
        Log.d("abc","afterreturning");
    }

    @AfterThrowing(value = "pointcut()",throwing = "ex")
    public void afterthrowing(Throwable throwable){
        Log.d("abc","afterthrowing");
    }


}
