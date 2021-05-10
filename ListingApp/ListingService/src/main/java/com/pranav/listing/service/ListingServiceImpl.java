package com.pranav.listing.service;

import com.pranav.listing.dao.ListingDaoRespository;
import com.pranav.listing.dao.model.Listing;
import com.pranav.listing.dto.ListingDTO;
import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.DeleteListingResponse;
import com.pranav.listing.response.GetListingResponse;
import com.pranav.listing.response.GetTopCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.pranav.listing.util.ListingUtils.transformModelsIntoDTOs;
import static com.pranav.listing.util.ListingUtils.transformSingleModelIntoDTOs;

@Service
public class ListingServiceImpl implements  IListingService{

    public static final String SUCCESS="Success";
    public static final String FAILURE="Error At Server";
    public static final String NOT_FOUND_LISTING="Error - not found";
    public static final String DELETE_NOT_FOUND_LISTING= "Error - listing does not exist";
    public static final String DELETE_OWNER_NOT_FOUND= "Error - listing owner mismatch";


    @Autowired
    private ListingDaoRespository repository;

    @Override
    public CreateListingResponse createListing(final String uname,
                                               final String title,
                                               final String description,
                                               final Double price,
                                               final String category) {
        System.out.println("Inside create lisitng service");
        CreateListingResponse  response = null;
        try {
            Listing listing = Listing.builder().title(title)
                    .uname(uname).category(category).description(description).price(price).build();
            Listing listingDb = repository.save(listing);
            CacheService.updateOnCreate(category);
            response = CreateListingResponse.builder()
                    .isSuccess(true)
                    .message(SUCCESS)
                    .lisitngId(listingDb.getId())
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

    @Override
    public GetListingResponse getListingByUnameAndListingId(final String uname, final Long listingId) {
        System.out.println("Inside getListingByUnameAndListingId service");
        GetListingResponse  response = null;
        try {
            String message = null;
            Listing listing =  repository.findOneByIdAndUnameIgnoreCase(listingId, uname);
            List<ListingDTO> dtos = transformSingleModelIntoDTOs(listing);
            if(CollectionUtils.isEmpty(dtos)){
                message = NOT_FOUND_LISTING;
            }else {
                message = SUCCESS;
            }
            response = GetListingResponse.builder()
                    .isSuccess(true)
                    .message(message)
                    .listings(dtos)
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

    @Override
    public GetListingResponse getListById(final Long listingid) {
        System.out.println("Inside getListById service");
        GetListingResponse  response = null;
        try {
            String message = null;
            Listing listing = null;
            Optional<Listing> listingFromDb = repository.findById(listingid);
            if(listingFromDb.isPresent()){
                listing = listingFromDb.get();
                message = SUCCESS;
            }else {
                message = NOT_FOUND_LISTING;
            }
            response = GetListingResponse.builder()
                    .isSuccess(true)
                    .message(message)
                    .listings(transformModelsIntoDTOs(Arrays.asList(listing)))
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

    @Override
    public GetListingResponse getAllListingByCategoryByUser(final String uname,
                                                            final String category,
                                                            final String sortby,
                                                            final String order) {
        System.out.println("Inside getListingByUnameAndListingId service");
        GetListingResponse  response = null;
        try {
            String message = null;
            List<Listing> listings = null;
            if(sortby.equalsIgnoreCase("SORT_PRICE")
                    && order.equalsIgnoreCase("DSC")){
                listings =  repository.findByUnameIgnoreCaseAndCategoryIgnoreCaseOrderByPriceDesc(uname, category);
            } else  if(sortby.equalsIgnoreCase("SORT_PRICE")
                    && order.equalsIgnoreCase("ASC")){
                listings =  repository.findByUnameIgnoreCaseAndCategoryIgnoreCaseOrderByPriceAsc(uname, category);
            } else  if(sortby.equalsIgnoreCase("SORT_TIME")
                    && order.equalsIgnoreCase("DSC")){
                listings =  repository.findByCategorySortTimeDesc_NQ(uname, category);
            } else if(sortby.equalsIgnoreCase("SORT_TIME")
                    && order.equalsIgnoreCase("ASC")){
                listings =  repository.findByCategorySortTimeAsc_NQ(uname, category);
            }
            List<ListingDTO> dtos = transformModelsIntoDTOs(listings);
            if(CollectionUtils.isEmpty(dtos)){
                message = NOT_FOUND_LISTING;
            }else {
                message = SUCCESS;
            }
            response = GetListingResponse.builder()
                    .isSuccess(true)
                    .message(message)
                    .listings(dtos)
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
    public GetTopCategoryResponse getTopCategoryInListings() {
        System.out.println("Inside getTopCategoryInListings service");
        GetTopCategoryResponse  response = null;
        try {
            response = GetTopCategoryResponse.builder()
                    .isSuccess(true)
                    .message(SUCCESS)
                    .category(CacheService.getTopCategory())
                    .build();
        }catch (Exception ex){
            System.out.println("Error in getTopCategoryInListings");
            ex.printStackTrace();
            response = GetTopCategoryResponse.builder()
                    .isSuccess(false)
                    .message(FAILURE)
                    .build();
        }
        return response;
    }

    @Override
    public DeleteListingResponse deleteListing(final Long listingid, final String uname) {
        System.out.println("Inside deleteListing service");
        DeleteListingResponse  response = null;
        try {
            String message = null;
            Listing listing = null;
            boolean isSuccess = false;
            Optional<Listing> listingFromDb = repository.findById(listingid);
            if(listingFromDb.isPresent()){
                listing = listingFromDb.get();
                if(listing.getUname().equalsIgnoreCase(uname)){
                    repository.deleteById(listing.getId());
                    message = SUCCESS;
                    isSuccess = true;
                    CacheService.updateOnDelete(listing.getCategory());
                }else{
                    message = DELETE_OWNER_NOT_FOUND;
                }
            }else {
                message = DELETE_NOT_FOUND_LISTING;
            }
            response = DeleteListingResponse.builder()
                    .isSuccess(isSuccess)
                    .message(message)
                    .build();
        }catch (Exception ex){
            System.out.println("Error in deleteListing");
            ex.printStackTrace();
            response = DeleteListingResponse.builder()
                    .isSuccess(false)
                    .message(FAILURE)
                    .build();
        }
        return response;
    }
}
