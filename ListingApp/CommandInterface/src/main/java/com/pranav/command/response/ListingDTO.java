package com.pranav.command.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListingDTO implements Serializable {
    private Long id;
    private String uname;
    private String description;
    private Double price;
    private String category;
    private String title;
    private Date createdOn;
}
