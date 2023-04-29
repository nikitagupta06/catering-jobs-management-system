package com.nikitagupta06.cateringmanagementsystem.repository;

import com.nikitagupta06.cateringmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringmanagementsystem.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CateringJobRepository extends CrudRepository<CateringJob, Long> {
    List<CateringJob> findAll();
    List<CateringJob> findByStatus(Status status);
}
