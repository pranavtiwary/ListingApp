package com.pranav.listing.response;

import com.pranav.listing.dto.ListingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetListingResponse extends Response{
    private List<ListingDTO> listings;
}
