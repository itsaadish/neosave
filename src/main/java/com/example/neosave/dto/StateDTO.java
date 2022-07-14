package com.example.neosave.dto;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class StateDTO {
    @JsonProperty("PostOffice")
    private List<PostOfficeDTO> postOffice;
}




