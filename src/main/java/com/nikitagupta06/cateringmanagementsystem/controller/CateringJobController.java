package com.nikitagupta06.cateringmanagementsystem.controller;

import com.nikitagupta06.cateringmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringmanagementsystem.model.Status;
import com.nikitagupta06.cateringmanagementsystem.repository.CateringJobRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("cateringJobs")
public class CateringJobController {
    private final CateringJobRepository cateringJobRepository;

    public CateringJobController(CateringJobRepository cateringJobRepository) {
        this.cateringJobRepository = cateringJobRepository;
    }
    @GetMapping
    @ResponseBody
    public List<CateringJob> getCateringJobs() {
        return cateringJobRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CateringJob getCateringJobById(@PathVariable Long id) {
        if (cateringJobRepository.existsById(id)) {
            return cateringJobRepository.findById(id).get();
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByStatus")
    public List<CateringJob> getCateringJobByStatus(@RequestParam Status status) {
        return cateringJobRepository.findByStatus(status);
    }
}
