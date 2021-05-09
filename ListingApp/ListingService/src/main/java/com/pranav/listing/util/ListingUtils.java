package com.pranav.listing.util;

import com.pranav.listing.dao.model.Listing;
import com.pranav.listing.dto.ListingDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListingUtils {
    public static List<ListingDTO> transformModelsIntoDTOs(final List<Listing> listings) {
        if(CollectionUtils.isEmpty(listings)){
            return null;
        }
        List<ListingDTO> dtos = new ArrayList<>();
        for (Listing listing : listings){
            dtos.add(transformModelIntoDTO(listing));
        }
        return dtos;
    }

    public static  ListingDTO transformModelIntoDTO(final Listing listing){
        if(null == listing){
            return null;
        }
        ListingDTO dto = ListingDTO.builder()
                .id(listing.getId())
                .createdOn(listing.getCreatedOn())
                .title(listing.getTitle())
                .price(listing.getPrice())
                .uname(listing.getUname())
                .category(listing.getCategory())
                .description(listing.getDescription())
                .build();
        return dto;
    }

    public static List<ListingDTO> transformSingleModelIntoDTOs(final Listing listing) {
        if(null == listing){
            return null;
        }
        return transformModelsIntoDTOs(Arrays.asList(listing));
    }
}
