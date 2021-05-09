package com.pranav.listing.service;

import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.DeleteListingResponse;
import com.pranav.listing.response.GetListingResponse;

public interface IListingService {
    CreateListingResponse createListing(String uname, final String title,
                                        String description, Double price, String category);

    GetListingResponse getListingByUnameAndListingId(final String uname, final Long listingId);

    GetListingResponse getAllListingByUserId(final String uname);

    DeleteListingResponse deleteListing(final Long listingid, final String uname);

    GetListingResponse getListById(final Long listingid);
}
