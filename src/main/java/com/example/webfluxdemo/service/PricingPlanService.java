package com.example.webfluxdemo.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

public interface PricingPlanService {
    Bucket resolveBucket(String apiKey);

    Bucket newBucket(String apiKey);

    Bucket bucket(Bandwidth limit);
}
