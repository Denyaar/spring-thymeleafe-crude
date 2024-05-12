/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * Date: 5/12/24
 * Time: 4:09 PM
 */

package com.example.springthymeleafecrude.event;

import com.example.springthymeleafecrude.model.LogData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AuditEventHandler {

    @SneakyThrows
    @EventListener
    @Async
    public void handleEvent(AuditEvent<LogData> auditEvent) {
        System.out.println(new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(auditEvent.getData()));
    }
}
