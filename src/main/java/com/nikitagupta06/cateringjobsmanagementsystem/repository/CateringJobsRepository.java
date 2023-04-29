package com.nikitagupta06.cateringmanagementsystem.repository;

import com.nikitagupta06.cateringmanagementsystem.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CateringJobsRepository extends CrudRepository<com.nikitagupta06.cateringmanagementsystem.model.CateringJob, Long> {
    List<com.nikitagupta06.cateringmanagementsystem.model.CateringJob> findAll();
    List<com.nikitagupta06.cateringmanagementsystem.model.CateringJob> findByStatus(Status status);
}
