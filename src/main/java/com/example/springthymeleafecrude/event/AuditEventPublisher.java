/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * Date: 5/12/24
 * Time: 3:59 PM
 */

package com.example.springthymeleafecrude.event;

import com.example.springthymeleafecrude.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(String message){
        Map<String, String>  datMap =  new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        datMap.put("host",request.getHeader("host"));
        datMap.put("user-agent",request.getHeader("User-Agent"));
        datMap.put("message", message);
        applicationEventPublisher.publishEvent(new AuditEvent<LogData>(LogData
                .builder()
                .data(datMap)
                .build()));

    }
}
