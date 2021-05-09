package com.pranav.listing.service;

import com.pranav.listing.dao.ListingDaoRespository;
import com.pranav.listing.dao.model.Listing;
import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.GetListingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.pranav.listing.util.ListingUtils.transformModelsIntoDTOs;

@Service
public class ListingServiceImpl implements  IListingService{

    private static final String USER_ALREADY_PRESENT="Error - user already existing";
    private static final String SUCCESS="Success";
    private static final String FAILURE="Error At Server";

    @Autowired
    private ListingDaoRespository repository;

    @Override
    public CreateListingResponse createListing(final String uname,
                                               final String title,
                                               final String description,
                                               final Double price,
                                               final String category) {
        {
            System.out.println("Inside create lisitng service");
            CreateListingResponse  response = null;
            try {
                Listing listing = Listing.builder().title(title)
                        .uname(uname).category(category).description(description).price(price).build();
                repository.save(listing);
                response = CreateListingResponse.builder()
                        .isSuccess(true)
                        .message(SUCCESS)
                        .lisitngId(listing.getId())
                        .build();
            }catch (Exception ex){
                System.out.println("Error in creating Listing");
                ex.printStackTrace();
                response = CreateListingResponse.builder()
                        .isSuccess(false)
                        .message(FAILURE)
                        .build();
            }
            return response;
        }
    }

    @Override
    public GetListingResponse getListingByUnameAndListingId(final String uname, final Long listingId) {
        System.out.println("Inside getListingByUnameAndListingId service");
        GetListingResponse  response = null;
        try {
            Listing listing =  repository.findOneByIdAndUnameIgnoreCase(listingId, uname);
            response = GetListingResponse.builder()
                    .isSuccess(true)
                    .message(SUCCESS)
                    .listings(transformModelsIntoDTOs(Arrays.asList(listing)))
                    .build();
        }catch (Exception ex){
            System.out.println("Error in getting Listing");
            ex.printStackTrace();
            response = GetListingResponse.builder()
                    .isSuccess(false)
                    .message(FAILURE)
                    .build();
        }
        return response;
    }

    @Override
    public GetListingResponse getAllListingByUserId(final String uname) {
        System.out.println("Inside getAllListingByUserId service");
        GetListingResponse  response = null;
        try {
            List<Listing> listings = repository.findAllByUnameIgnoreCase(uname);
            response = GetListingResponse.builder()
                    .isSuccess(true)
                    .message(SUCCESS)
                    .listings(transformModelsIntoDTOs(listings))
                    .build();
        }catch (Exception ex){
            System.out.println("Error in getAllListingByUserId");
            ex.printStackTrace();
            response = GetListingResponse.builder()
                    .isSuccess(false)
                    .message(FAILURE)
                    .build();
        }
        return response;
    }


}
