package com.nikitagupta06.cateringjobsmanagementsystem.repository;


import com.nikitagupta06.cateringjobsmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringjobsmanagementsystem.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CateringJobsRepository extends CrudRepository<CateringJob, Long> {
    List<CateringJob> findAll();
    List<CateringJob> findByStatus(Status status);
}
