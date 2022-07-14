package com.example.neosave.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostOfficeDTO {
    @JsonProperty("State")
    private String state;
}
