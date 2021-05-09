package com.pranav.listing.controller;

import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.service.IListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    private IListingService service;

    @PostMapping("/create")
    public CreateListingResponse create(
            @RequestParam(required = true) String uname,
            @RequestParam(required = true) String title,
            @RequestParam(required = true) String description,
            @RequestParam(required = true) Double price,
            @RequestParam(required = true) String category){
        System.out.println("Received a request to create a listing");
        CreateListingResponse response = service.createListing(uname, title, description, price, category);
        return response;
    }
}
