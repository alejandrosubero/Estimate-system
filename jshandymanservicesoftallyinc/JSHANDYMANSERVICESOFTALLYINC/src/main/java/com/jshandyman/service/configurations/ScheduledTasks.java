package com.jshandyman.service.configurations;

import com.jshandyman.service.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

//@Configuration
//@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private ClientService clientService;

    @Bean
    public TaskScheduler scheduler() {
        return new ThreadPoolTaskScheduler();
    }

    // */2 * * * * *  // cada 2 seg
    // 0 0 */6 * * * // cada 6 hora
    // 0 0 * * * * // cada hora
    @Scheduled(cron = "0 0 * * * *")
    public void deactivateInscriptions() {
//        System.out.println("********THE TEST WAS EJECUTE ************");
//        clientService.startFillClienteMap();
    }


    @Scheduled(initialDelay = 1000 * 2, fixedDelay = Long.MAX_VALUE)
    public void deactivateInscriptions2() {
//        System.out.println("startFillClienteMap()");
//       clientService.startFillClienteMap();
    }


}
