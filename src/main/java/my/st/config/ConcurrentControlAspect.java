package my.st.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Aspect
@Component
public class ConcurrentControlAspect {  
    private final Map<String,Semaphore> semaphoreMap = new ConcurrentHashMap<>();

  
    @Around("@annotation(concurrentControl)")
    public Object controlConcurrent(ProceedingJoinPoint proceed, ConcurrentControl concurrentControl) throws Throwable {

        String type = concurrentControl.type();
        synchronized (semaphoreMap){
            semaphoreMap.putIfAbsent(type, new Semaphore(concurrentControl.value()));
        }

        Semaphore semaphore = semaphoreMap.get(type);

        if (semaphore.tryAcquire()) { // 尝试获取信号量许可
            try {  
                return proceed.proceed(); // 执行方法  
            } finally {  
                semaphore.release(); // 释放信号量许可
            }  
        } else {  
            return "正在处理中，请稍后再试"; // 如果没有信号量许可，返回提示信息
        }  
    }  
}