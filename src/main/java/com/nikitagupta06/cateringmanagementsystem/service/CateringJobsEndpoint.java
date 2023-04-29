package com.nikitagupta06.cateringmanagementsystem.service;

import com.nikitagupta06.cateringmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringmanagementsystem.model.Status;
import com.nikitagupta06.cateringmanagementsystem.repository.CateringJobRepository;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@Endpoint(id = "catering-jobs")
public class CateringJobsEndpoint {
    private CateringJobRepository cateringJobRepository;

    public CateringJobsEndpoint(CateringJobRepository cateringJobRepository) {
        this.cateringJobRepository = cateringJobRepository;
    }

    @ReadOperation
    Map<Status, Long> getCateringJobsMetrices() {
        return cateringJobRepository.findAll()
                .stream()
                .collect(groupingBy(CateringJob::getStatus, Collectors.counting()));
    }
}
