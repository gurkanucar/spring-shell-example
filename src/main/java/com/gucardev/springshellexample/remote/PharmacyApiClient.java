package com.gucardev.springshellexample.remote;

import com.gucardev.springshellexample.config.FeignClientConfig;
import com.gucardev.springshellexample.model.PharmacyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    url = "https://api.collectapi.com/health/dutyPharmacy",
    name = "pharmacyApi",
    configuration = FeignClientConfig.class)
public interface PharmacyApiClient {

  @GetMapping
  ResponseEntity<PharmacyResponse> getPharmacies(
      @RequestParam(required = true, name = "il") String city,
      @RequestParam(required = false, name = "ilce") String district);
}
