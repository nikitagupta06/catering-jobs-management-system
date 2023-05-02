package com.nikitagupta06.cateringjobsmanagementsystem.service;

import com.nikitagupta06.cateringjobsmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringjobsmanagementsystem.repository.CateringJobsRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public CateringJobsRepository repository;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    @StepScope
    public FlatFileItemReader<CateringJob> reader() {
        FlatFileItemReader<CateringJob> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("upload.csv"));
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "customerName", "phoneNumber", "email", "menu", "noOfGuests", "status");

        DefaultLineMapper<CateringJob> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(new CateringJobMapper());

        reader.setLineMapper(lineMapper);
        return reader;
    }

    @Bean
    public RepositoryItemWriter<CateringJob> writer() {
        RepositoryItemWriter<CateringJob> writer = new RepositoryItemWriter<>();
        writer.setRepository(repository);
        return writer;
    }

    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository).<CateringJob, CateringJob>chunk(10, transactionManager).reader(reader()).writer(writer()).build();
    }

    @Bean
    public Job uploadCateringJob() {
        return new JobBuilder("uploadCateringJob", jobRepository).start(step()).build();
    }
}