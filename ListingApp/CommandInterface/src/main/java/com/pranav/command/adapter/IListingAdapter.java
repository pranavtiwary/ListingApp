package com.pranav.command.adapter;

import com.pranav.command.response.CreateListingResponse;
import com.pranav.command.response.DeleteListingResponse;
import com.pranav.command.response.GetListingResponse;

public interface IListingAdapter {

    CreateListingResponse createListing(final String uname,
                                               final String title,
                                               final String description,
                                               final Double price,
                                               final String category);

    GetListingResponse getListingByUserIdAndListingId(final String uname,
                                                      final String listingid);

    DeleteListingResponse deleteListingByUserIdAndListingId(final String uname,
                                                            final String listingid);

    GetListingResponse getListingByCategory(final String userName,
                                            final String category,
                                            final String sortby,
                                            final String order);
}
