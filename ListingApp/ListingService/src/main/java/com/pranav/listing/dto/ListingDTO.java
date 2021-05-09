package com.pranav.listing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListingDTO {
    private Long id;
    private String uname;
    private String description;
    private Double price;
    private String category;
    private String title;
    private Date createdOn;
}
