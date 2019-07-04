package cn.cghome.video.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ThreadPool {

    @Value("${video.options.initTask.threadCount}")
    private int threadCount;

    private ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void execute(Runnable runnable){
        executorService.execute(runnable);
    }

    public Future<?> submit(Runnable runnable){
        return executorService.submit(runnable);
    }
}
