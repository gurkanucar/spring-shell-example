package com.gucardev.springshellexample.remote;

import com.gucardev.springshellexample.model.SportApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://172.24.240.1:8090/mock-handler/api", name = "leagueApi")
public interface LeagueApiClient {

  @GetMapping("/scores")
  ResponseEntity<SportApiResponse> getAllScores();
}
