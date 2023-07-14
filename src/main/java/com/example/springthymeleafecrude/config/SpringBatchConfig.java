/**
 * Created by tendaimupezeni for spring-thymeleafe-crude
 * User: tendaimupezeni
 * Date: 24/6/2023
 * Time: 00:54
 */

package com.example.springthymeleafecrude.config;

import com.example.springthymeleafecrude.model.People;
import com.example.springthymeleafecrude.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;

    private StepBuilderFactory stepBuilderFactory;

    private PeopleRepository peopleRepository;



    @Bean
    public FlatFileItemReader<People> reader(){
        FlatFileItemReader<People> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/people.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper((lineMapper()));
        return itemReader;

    }

    private LineMapper<People> lineMapper() {
        DefaultLineMapper<People> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer  delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames("Id","User_Id","First_Name","Last_Name","Sex","Email","Phone","Date_of_birth","Job_Title");

        BeanWrapperFieldSetMapper<People> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(People.class);

        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public  PeopleProcessor peopleProcessor(){
        return  new PeopleProcessor();
    }

    @Bean
    public RepositoryItemWriter<People> writer(){
        RepositoryItemWriter<People> writer = new RepositoryItemWriter<>();
        writer.setRepository(peopleRepository);
        writer.setMethodName("save");
        return writer;
    }

    public Step Step1(){
        return  stepBuilderFactory.get("csv-step").<People,People>chunk(10)
                .reader(reader())
                .processor(peopleProcessor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }


    @Bean
    public Job job(){
        return jobBuilderFactory.get("importPeopleINfor")
                .flow(Step1()).end().build();
    }

    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return  asyncTaskExecutor;

    }



}
