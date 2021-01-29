package com.example.webfluxdemo.service.impl;

import com.example.webfluxdemo.ratelimit.PricingPlan;
import com.example.webfluxdemo.service.PricingPlanService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PricingPlanServiceImpl implements PricingPlanService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();


    @Override
    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    @Override
    public Bucket newBucket(String apiKey) {
        PricingPlan pricingPlan = PricingPlan.resolvePlanFromApiKey(apiKey);
        return bucket(pricingPlan.getLimit());
    }

    @Override
    public Bucket bucket(Bandwidth limit) {
        return Bucket4j.builder()
                .addLimit(limit)
                .build();
    }
}
