package com.nikitagupta06.cateringjobsmanagementsystem.service;

import com.nikitagupta06.cateringjobsmanagementsystem.repository.CateringJobsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    CateringJobsRepository repository;

    @Scheduled(cron = "*/10 * * * * *")
    public void reportOrderStatus() {
        int jobs = repository.findAll().size();
        logger.info("we have {} jobs", jobs);
    }

}
