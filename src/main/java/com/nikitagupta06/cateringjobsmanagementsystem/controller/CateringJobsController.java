package com.nikitagupta06.cateringmanagementsystem.controller;

import com.nikitagupta06.cateringmanagementsystem.model.Status;
import com.nikitagupta06.cateringmanagementsystem.repository.CateringJobsRepository;
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
    public List<com.nikitagupta06.cateringmanagementsystem.model.CateringJob> getCateringJobs() {
        return cateringJobsRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public com.nikitagupta06.cateringmanagementsystem.model.CateringJob getCateringJobById(@PathVariable Long id) {
        if (cateringJobsRepository.existsById(id)) {
            return cateringJobsRepository.findById(id).get();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByStatus")
    public List<com.nikitagupta06.cateringmanagementsystem.model.CateringJob> getCateringJobByStatus(@RequestParam Status status) {
        return cateringJobsRepository.findByStatus(status);
    }
}
