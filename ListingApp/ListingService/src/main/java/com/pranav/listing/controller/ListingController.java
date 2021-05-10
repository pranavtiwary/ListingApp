package com.pranav.listing.controller;

import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.DeleteListingResponse;
import com.pranav.listing.response.GetListingResponse;
import com.pranav.listing.service.IListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/getlisting-by-userid-listingid")
    public GetListingResponse getListingByUserIdAndListingId(
            @RequestParam(required = true) String uname,
            @RequestParam(required = true) Long listingid){
        System.out.println("Received a request to Get a listing for userId : " +
                "" + uname + " and lisitng id : " + listingid);
        GetListingResponse listings = service.getListingByUnameAndListingId(uname, listingid);
        return listings;
    }

    @GetMapping("/{listingid}")
    public GetListingResponse getByListingId(@PathVariable(required = true) Long listingid){
        GetListingResponse response = service.getListById(listingid);
        return response;
    }

    @GetMapping("/user/{uname}")
    public GetListingResponse getAllListingByUserId(@PathVariable(required = true) String uname){
        System.out.println("Received a request to get all listings for userid : " + uname);
        GetListingResponse listingByUserId = service.getAllListingByUserId(uname);
        return listingByUserId;
    }

    @GetMapping("/delete")
    public DeleteListingResponse getAllListingByUserId(
            @RequestParam(required = true) String uname,
            @RequestParam(required = true) Long listingid){
        System.out.println("Received a request to get all listings for userid : " + uname);
        DeleteListingResponse res = service.deleteListing(listingid, uname);
        return res;
    }
}
