/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * Date: 5/12/24
 * Time: 3:53 PM
 */

package com.example.springthymeleafecrude.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class AuditEvent<T>  extends ApplicationEvent {

    private  T data;
    public AuditEvent(T source) {
        super(source);
    }

}
