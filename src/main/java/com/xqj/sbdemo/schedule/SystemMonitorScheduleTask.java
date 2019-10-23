package com.xqj.sbdemo.schedule;

import com.xqj.sbdemo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

import static com.xqj.sbdemo.utils.OSUtils.*;

@Slf4j
@Configuration
@EnableScheduling
public class SystemMonitorScheduleTask {

    @Autowired
    private EmailService emailService;

    @Value("${schedule.enabled}")
    private String scheduleEnabled;

    //@Scheduled(cron = "0/60 * * * * ?")
    @Scheduled(cron = "0 */60 * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        if (Boolean.parseBoolean(scheduleEnabled)) {
            Map<String, String> cpuResult = cpuUsage();
            System.out.println(cpuResult.get("result"));
            Map<String, String> memResult = memoryUsage();
            System.out.println(memResult.get("result"));
            Map<String, String> networkResult = networkUsage();
            emailService.sendSimpleEmail("320820480@qq.com", "服务器cpu和内存信息", cpuResult.get("result") + "   " + memResult.get("result") + "   " + networkResult.get("result"));
        } else{
            System.out.println("获取服务器信息的定时器已被关闭");
            log.info("获取服务器信息的定时器已被关闭");
        }
    }
}
