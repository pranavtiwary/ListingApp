package com.pranav.listing.controller;

import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.DeleteListingResponse;
import com.pranav.listing.response.GetListingResponse;
import com.pranav.listing.response.GetTopCategoryResponse;
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
        System.out.println("create: Received a request to create a listing");
        CreateListingResponse response = service.createListing(uname, title, description, price, category);
        return response;
    }

    @GetMapping("/getlisting-by-userid-listingid")
    public GetListingResponse getListingByUserIdAndListingId(
            @RequestParam(required = true) String uname,
            @RequestParam(required = true) Long listingid){
        System.out.println("getListingByUserIdAndListingId: Received a request to Get a listing for userId : " +
                "" + uname + " and lisitng id : " + listingid);
        GetListingResponse listings = service.getListingByUnameAndListingId(uname, listingid);
        return listings;
    }

    @GetMapping("/{listingid}")
    public GetListingResponse getByListingId(@PathVariable(required = true) Long listingid){
        System.out.println("getByListingId : Received a request to get a listing : " +listingid);
        GetListingResponse response = service.getListById(listingid);
        return response;
    }

    @GetMapping("/user/{uname}")
    public GetListingResponse getAllListingByUserId(@PathVariable(required = true) String uname){
        System.out.println("getAllListingByUserId : Received a request to get all listings for userid : " + uname);
        GetListingResponse listingByUserId = service.getAllListingByUserId(uname);
        return listingByUserId;
    }

    @GetMapping("/delete")
    public DeleteListingResponse deleteListingByUserIdAndListingId(
            @RequestParam(required = true) String uname,
            @RequestParam(required = true) Long listingid){
        System.out.println("deleteListingByUserIdAndListingId : Received a request to " +
                "delete listings for : userid=" + uname + " & listingid="+listingid);
        DeleteListingResponse res = service.deleteListing(listingid, uname);
        return res;
    }

    @GetMapping("/category")
    public GetListingResponse getAllListingByCategoryByUser(
            @RequestParam(required = true) String uname,
            @RequestParam(required = true) String category,
            @RequestParam(required = true) String sortby,
            @RequestParam(required = true) String order){
        System.out.println("getAllListingByCategoryByUser : Received a request to get all listings for " +
                "userid : " + uname  + "and category : "+ category);
        GetListingResponse res = service.getAllListingByCategoryByUser(uname, category, sortby, order);
        return res;
    }


    @GetMapping("/topcategory")
    public GetTopCategoryResponse getAllTopCategoryListingByUser(
            @RequestParam(required = true) String uname){
        System.out.println("getAllTopCategoryListingByUser : Received a request to get all listings " +
                "of top category, uname : " + uname);
        GetTopCategoryResponse res = service.getTopCategoryInListings();
        return res;
    }
}
