package com.pranav.command.adapter;

public interface IListingAdapter {

    public void callCreateListingService(final String uname,
                                         final String title,
                                         final String description,
                                         final Double price,
                                         final String category);
}
