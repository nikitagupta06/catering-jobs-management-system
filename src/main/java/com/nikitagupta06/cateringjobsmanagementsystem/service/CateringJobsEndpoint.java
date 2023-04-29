package com.nikitagupta06.cateringjobsmanagementsystem.service;

import com.nikitagupta06.cateringjobsmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringjobsmanagementsystem.model.Status;
import com.nikitagupta06.cateringjobsmanagementsystem.repository.CateringJobsRepository;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@Endpoint(id = "catering-jobs")
public class CateringJobsEndpoint {
    private CateringJobsRepository cateringJobsRepository;

    public CateringJobsEndpoint(CateringJobsRepository cateringJobsRepository) {
        this.cateringJobsRepository = cateringJobsRepository;
    }

    @ReadOperation
    Map<Status, Long> getCateringJobsMetrices() {
        return cateringJobsRepository.findAll()
                .stream()
                .collect(groupingBy(CateringJob::getStatus, Collectors.counting()));
    }
}
