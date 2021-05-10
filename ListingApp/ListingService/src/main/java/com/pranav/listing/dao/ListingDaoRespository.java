package com.pranav.listing.dao;


import com.pranav.listing.dao.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListingDaoRespository extends JpaRepository<Listing, Long> {
    Listing findOneByIdAndUnameIgnoreCase(Long id, String uname);

    List<Listing> findAllByUnameIgnoreCase(String uname);

    List<Listing> findByCategoryIgnoreCaseOrderByCreatedOnAsc(String category);
    List<Listing> findByCategoryIgnoreCaseOrderByCreatedOnDesc(String category);
    List<Listing> findByCategoryIgnoreCaseOrderByPriceAsc(String category);
    List<Listing> findByCategoryIgnoreCaseOrderByPriceDesc(String category);

    @Query(value = "SELECT e FROM Listing e WHERE " +
            "lower(e.category) = lower(:category) " +
            "ORDER BY e.createdOn DESC",
            nativeQuery = false)
    List<Listing> findByCategorySortTimeDesc_NQ(@Param("category") String category);

    @Query(value = "SELECT e FROM Listing e WHERE " +
            "lower(e.category) = lower(:category) " +
            "ORDER BY e.createdOn ASC",
            nativeQuery = false)
    List<Listing> findByCategorySortTimeAsc_NQ(@Param("category") String category);
}
