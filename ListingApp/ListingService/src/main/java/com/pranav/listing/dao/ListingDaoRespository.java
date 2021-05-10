package com.pranav.listing.dao;


import com.pranav.listing.dao.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListingDaoRespository extends JpaRepository<Listing, Long> {
    Listing findOneByIdAndUnameIgnoreCase(Long id, String uname);

    List<Listing> findAllByUnameIgnoreCase(String uname);

    List<Listing> findByUnameIgnoreCaseAndCategoryIgnoreCaseOrderByCreatedOnAsc(String uname, String category);
    List<Listing> findByUnameIgnoreCaseAndCategoryIgnoreCaseOrderByCreatedOnDesc(String uname, String category);
    List<Listing> findByUnameIgnoreCaseAndCategoryIgnoreCaseOrderByPriceAsc(String uname, String category);
    List<Listing> findByUnameIgnoreCaseAndCategoryIgnoreCaseOrderByPriceDesc(String uname, String category);

    @Query(value = "SELECT e FROM Listing e WHERE lower(e.uname) = lower(:uname) " +
            "and lower(e.category) = lower(:category) " +
            "ORDER BY e.createdOn DESC",
            nativeQuery = false)
    List<Listing> findByCategorySortTimeDesc_NQ(@Param("uname") String uname,
                                                @Param("category") String category);

    @Query(value = "SELECT e FROM Listing e WHERE lower(e.uname) = lower(:uname) " +
            "and lower(e.category) = lower(:category) " +
            "ORDER BY e.createdOn ASC",
            nativeQuery = false)
    List<Listing> findByCategorySortTimeAsc_NQ(@Param("uname") String uname,
                                               @Param("category") String category);
    @Query(value = "SELECT e.category FROM Listing e WHERE lower(e.uname) = lower(:uname) " +
            "and lower(e.category) = lower(:category) " +
            "ORDER BY e.createdOn ASC",
            nativeQuery = false)
    String getTopCategory();
}
