/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * Date: 5/12/24
 * Time: 4:01 PM
 */

package com.example.springthymeleafecrude.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class LogData {
    private Map<String, String> data;
}
