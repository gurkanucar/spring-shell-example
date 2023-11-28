package com.gucardev.springshellexample.remote;

import com.gucardev.springshellexample.model.ApiResponse;
import com.gucardev.springshellexample.model.League;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    url = "https://api.collectapi.com/sport/league?data.league=super-lig",
    name = "leagueApi")
public interface LeagueApiClient {

  @GetMapping
  ResponseEntity<ApiResponse<League>> getAllScores();
}
