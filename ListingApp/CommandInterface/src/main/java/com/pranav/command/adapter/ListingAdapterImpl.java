package com.pranav.command.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ListingAdapterImpl implements  IListingAdapter{

    @Value("${create.listing.service.url}")
    private String create_url;

    @Value("${getlisting.byall.service.url}")
    private String list_all_url;

    @Value("${getlisting.byuser.bylistingid.service.url}")
    private String list_by_userid_lisitngid_url;

    @Value("${getlisting.byUsername.service.url}")
    private String list_by_username_url;

    @Value("${getlisting.bylistingId.service.url}")
    private String list_by_listingid_url;

    public void callCreateListingService(final String uname,
                                         final String title,
                                         final String description,
                                         final Double price,
                                         final String category){
        System.out.println("Calling Lisitng service to Create lisiting");
    }

    @PostConstruct
    public void init(){
        System.out.println("Connecting to create listing service at : " + create_url);
        System.out.println("Connecting to get all listing service at : " + list_all_url);
        System.out.println("Connecting to get list by listingid listing service at : " + list_by_listingid_url);
        System.out.println("Connecting to get all listing by userid & lisitngId service at : " + list_by_userid_lisitngid_url);
    }

}
