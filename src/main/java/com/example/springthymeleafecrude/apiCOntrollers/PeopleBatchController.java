/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 24/6/2023
 * Time: 01:30
 */

package com.example.springthymeleafecrude.apiCOntrollers;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class PeopleBatchController {


    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/import-people")
    public void importCsvJob(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job,jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }

    }
}
