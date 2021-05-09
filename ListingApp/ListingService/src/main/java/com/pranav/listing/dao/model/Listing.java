package com.pranav.listing.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "listing")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Listing {

    @Id
    @GeneratedValue
    @Column(nullable = false)
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
