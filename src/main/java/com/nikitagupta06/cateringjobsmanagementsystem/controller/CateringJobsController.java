package com.nikitagupta06.cateringjobsmanagementsystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.nikitagupta06.cateringjobsmanagementsystem.model.CateringJob;
import com.nikitagupta06.cateringjobsmanagementsystem.model.Status;
import com.nikitagupta06.cateringjobsmanagementsystem.repository.CateringJobsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cateringJobs")
public class CateringJobsController {
    private static final String IMAGE_API = "https://api.api-ninjas.com/v1/randomimage?category=food";
    private final CateringJobsRepository cateringJobsRepository;
    WebClient client;

    public CateringJobsController(CateringJobsRepository cateringJobsRepository) {
        this.cateringJobsRepository = cateringJobsRepository;
        client = WebClient.builder().baseUrl(IMAGE_API).build();
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

    @PostMapping
    @ResponseBody
    public CateringJob createCateringJob(@RequestBody CateringJob job) {
        return cateringJobsRepository.save(job);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public CateringJob updateCateringJob(@RequestBody CateringJob cateringJob, @PathVariable Long id) {
        if (cateringJobsRepository.existsById(id)) {
            cateringJob.setId(id);
            return cateringJobsRepository.save(cateringJob);
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public CateringJob patchCateringJob(@PathVariable Long id, @RequestBody JsonNode json) {
        Optional<CateringJob> optionalJob = cateringJobsRepository.findById(id);
        if (optionalJob.isPresent()) {
            CateringJob job = optionalJob.get();
            JsonNode menu = json.get("menu");
            if (menu != null) {
                job.setMenu(menu.asText());
                return cateringJobsRepository.save(job);
            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleClientException() {
        return "Not Found: Please try again!";
    }

    @GetMapping("/surpriseMe")
    public Mono<String> getSurpriseImage() {
        return client.get().uri("/api").retrieve().bodyToMono(String.class);
    }
}
