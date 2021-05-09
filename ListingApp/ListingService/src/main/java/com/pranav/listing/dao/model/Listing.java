package com.pranav.listing.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {

    @Id
    private Long id;
    @Column(updatable = false, nullable = false)
    private String uname;
    @Column(updatable = true, nullable = true)
    private String description;
    @Column(updatable = true, nullable = false)
    private Double price;
    @Column(updatable = true, nullable = false)
    private String category;
    @Column(updatable = true, nullable = false)
    private String title;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
}
