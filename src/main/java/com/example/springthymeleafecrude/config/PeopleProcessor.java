/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 24/6/2023
 * Time: 01:18
 */

package com.example.springthymeleafecrude.config;

import com.example.springthymeleafecrude.model.People;
import org.springframework.batch.item.ItemProcessor;

public class PeopleProcessor implements ItemProcessor<People,People> {
    @Override
    public People process(People people) throws Exception {
        return people;
    }
}
