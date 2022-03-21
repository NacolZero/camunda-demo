package org.nacol.camundarestclient.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Nacol
 * @Email Nacol@sina.com
 * @Date 2020/06/02
 * @Description 异常结案日志线程池
 */
@EnableAsync
@Configuration
public class ProcessExecutorConfig {

    public static int CORE_NUMBER = Runtime.getRuntime().availableProcessors() + 1;
    public static final String TASK_EXECUTOR = "TaskExecutor";

    @Bean(TASK_EXECUTOR)
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Task-Executor-");
        executor.setCorePoolSize(CORE_NUMBER);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setKeepAliveSeconds(30000);
        executor.setQueueCapacity(2000);
        executor.setMaxPoolSize(CORE_NUMBER * 2 - 1);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
