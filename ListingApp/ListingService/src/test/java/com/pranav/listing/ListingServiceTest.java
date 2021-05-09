package com.pranav.listing;

import com.pranav.listing.dto.ListingDTO;
import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.GetListingResponse;
import com.pranav.listing.service.IListingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ListingServiceTest {

    @Autowired
    private IListingService service;

    @Test
    public void testCreateListing_Success(){
        CreateListingResponse response = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());
    }

    @Test
    public void testCreateListing_SuccessTwiceWithSameData(){
        CreateListingResponse response = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        CreateListingResponse response2 = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response2);
        Assert.assertTrue(response2.isSuccess());
        Assert.assertNotNull(response2.getLisitngId());

        Assert.assertTrue(response.getLisitngId().longValue() != response2.getLisitngId().longValue());

    }


    @Test
    public void testGetListing_SuccessTwiceWithSameData(){
        CreateListingResponse response = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        GetListingResponse listingResponse = service.getListingByUnameAndListingId("pranav",
                response.getLisitngId());

        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNotNull(listingResponse.getListings());
        Assert.assertTrue(listingResponse.getListings().size()==1);
        ListingDTO listingDTO = listingResponse.getListings().get(0);
        Assert.assertNotNull(listingDTO);
        Assert.assertTrue(listingDTO.getId().longValue()==response.getLisitngId().longValue());
        Assert.assertEquals("Electronic & Media", listingDTO.getCategory());


        GetListingResponse listingResponseWithUpparCase = service.getListingByUnameAndListingId("Pranav",
                response.getLisitngId());
        Assert.assertNotNull(listingResponseWithUpparCase);
        Assert.assertTrue(listingResponseWithUpparCase.isSuccess());
        Assert.assertNotNull(listingResponseWithUpparCase.getListings());
        Assert.assertTrue(listingResponseWithUpparCase.getListings().size()==1);
        ListingDTO listingDTO2 = listingResponseWithUpparCase.getListings().get(0);
        Assert.assertNotNull(listingDTO2);
        Assert.assertTrue(listingDTO2.getId().longValue()==response.getLisitngId().longValue());
        Assert.assertEquals("Electronic & Media", listingDTO2.getCategory());
    }
}
