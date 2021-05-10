package com.pranav.listing.service;

import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.DeleteListingResponse;
import com.pranav.listing.response.GetListingResponse;

public interface IListingService {
    CreateListingResponse createListing(final String uname,
                                        final String title,
                                        final String description,
                                        final Double price,
                                        final String category);

    GetListingResponse getListingByUnameAndListingId(final String uname, final Long listingId);

    GetListingResponse getAllListingByUserId(final String uname);

    DeleteListingResponse deleteListing(final Long listingid, final String uname);

    GetListingResponse getListById(final Long listingid);

    GetListingResponse getAllListingByCategoryByUser(String uname, String category, String sortby, String order);
}
