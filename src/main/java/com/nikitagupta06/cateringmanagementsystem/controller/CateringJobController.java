package com.nikitagupta06.cateringmanagementsystem.controller;

import com.nikitagupta06.cateringmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringmanagementsystem.repository.CateringJobRepository;
import org.springframework.web.bind.annotation.*;

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
}
