package com.nikitagupta06.cateringjobsmanagementsystem.controller;

import com.nikitagupta06.cateringjobsmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringjobsmanagementsystem.model.Status;
import com.nikitagupta06.cateringjobsmanagementsystem.repository.CateringJobsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("cateringJobs")
public class CateringJobsController {
    private final CateringJobsRepository cateringJobsRepository;

    public CateringJobsController(CateringJobsRepository cateringJobsRepository) {
        this.cateringJobsRepository = cateringJobsRepository;
    }
    @GetMapping
    @ResponseBody
    public List<CateringJob> getCateringJobs() {
        return cateringJobsRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CateringJob getCateringJobById(@PathVariable Long id) {
        if (cateringJobsRepository.existsById(id)) {
            return cateringJobsRepository.findById(id).get();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByStatus")
    public List<CateringJob> getCateringJobByStatus(@RequestParam Status status) {
        return cateringJobsRepository.findByStatus(status);
    }
}
