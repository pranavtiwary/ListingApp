package com.pranav.listing.dao;


import com.pranav.listing.dao.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingDaoRespository extends JpaRepository<Listing, String> {
    Listing findOneByIdAndUname(Long id, String uname);

    List<Listing> findAllByUname(String uname);
}
