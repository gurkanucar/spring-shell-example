package com.gucardev.springshellexample.remote;

import com.gucardev.springshellexample.config.FeignClientConfig;
import com.gucardev.springshellexample.model.ApiResponse;
import com.gucardev.springshellexample.model.Pharmacy;
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
  ResponseEntity<ApiResponse<Pharmacy>> getPharmacies(
      @RequestParam(required = true, name = "il") String city,
      @RequestParam(required = false, name = "ilce") String district);
}
