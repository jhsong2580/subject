package subject.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {


    /**
     * Blog 조회시 쿼리 정보 저장을 위한 쓰레드이다.
         * Lock 자원 중 "blogs" Lock을 함께 사용하니 주의하자
    */
    @Bean
    public ThreadPoolTaskExecutor requestQuerySaveExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setMaxPoolSize(400);
        executor.setCorePoolSize(30);
        executor.setQueueCapacity(0);
        executor.setKeepAliveSeconds(60);

        executor.setThreadNamePrefix("QUERY-SAVER-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}
