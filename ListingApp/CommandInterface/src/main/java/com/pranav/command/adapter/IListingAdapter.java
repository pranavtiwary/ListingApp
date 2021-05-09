package com.pranav.command.adapter;

import com.pranav.command.response.CreateListingResponse;

public interface IListingAdapter {

    CreateListingResponse createListing(final String uname,
                                               final String title,
                                               final String description,
                                               final Double price,
                                               final String category);
}
