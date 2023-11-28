package com.gucardev.springshellexample.model;

import java.util.List;


public record ApiResponse<T>(List<T> result) {}