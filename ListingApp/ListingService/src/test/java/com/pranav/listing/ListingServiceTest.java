package com.pranav.listing;

import com.pranav.listing.dto.ListingDTO;
import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.DeleteListingResponse;
import com.pranav.listing.response.GetListingResponse;
import com.pranav.listing.service.IListingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.pranav.listing.service.ListingServiceImpl.DELETE_NOT_FOUND_LISTING;
import static com.pranav.listing.service.ListingServiceImpl.DELETE_OWNER_NOT_FOUND;
import static com.pranav.listing.service.ListingServiceImpl.NOT_FOUND_LISTING;
import static com.pranav.listing.service.ListingServiceImpl.SUCCESS;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
    public void testGetLisitng(){
        CreateListingResponse response = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        GetListingResponse listingResponse =
                service.getListingByUnameAndListingId("PRAnav", response.getLisitngId());
        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNotNull(listingResponse.getListings());
        Assert.assertTrue(listingResponse.getListings().size()==1);
        Assert.assertEquals("mylisting 1", listingResponse.getListings().get(0).getTitle());

        listingResponse =
                service.getListingByUnameAndListingId("PRAnavt", response.getLisitngId());
        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNull(listingResponse.getListings());

        listingResponse =
                service.getListingByUnameAndListingId("PRAnav", 12314253534l);
        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNull(listingResponse.getListings());
        Assert.assertEquals(NOT_FOUND_LISTING,listingResponse.getMessage());
    }


    @Test
    public void testDeleteLisitng(){
        CreateListingResponse response = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        GetListingResponse listingResponse =
                service.getListingByUnameAndListingId("PRAnav", response.getLisitngId());
        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNotNull(listingResponse.getListings());
        Assert.assertTrue(listingResponse.getListings().size()==1);
        Assert.assertEquals("mylisting 1", listingResponse.getListings().get(0).getTitle());

        DeleteListingResponse deleteListing = service.deleteListing(response.getLisitngId(), "pranavt");
        Assert.assertNotNull(deleteListing);
        Assert.assertFalse(deleteListing.isSuccess());
        Assert.assertEquals(DELETE_OWNER_NOT_FOUND, deleteListing.getMessage());

        deleteListing = service.deleteListing(1232423342344l, "pranav");
        Assert.assertNotNull(deleteListing);
        Assert.assertFalse(deleteListing.isSuccess());
        Assert.assertEquals(DELETE_NOT_FOUND_LISTING, deleteListing.getMessage());

        deleteListing = service.deleteListing(response.getLisitngId(), "pranav");
        Assert.assertNotNull(deleteListing);
        Assert.assertTrue(deleteListing.isSuccess());
        Assert.assertEquals(SUCCESS, deleteListing.getMessage());
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

    @Test
    public void testGetListing(){
        CreateListingResponse response = service.createListing("pranavT", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        CreateListingResponse response2 = service.createListing("pranavt", "mylisting 2",
                "this is my 2nd lisitng", 20d, "Mobile");
        Assert.assertNotNull(response2);
        Assert.assertTrue(response2.isSuccess());
        Assert.assertNotNull(response2.getLisitngId());

        Assert.assertTrue(response.getLisitngId().longValue() != response2.getLisitngId().longValue());

        GetListingResponse listingResponseWithUpparCase = service.getAllListingByUserId("PRANAVT");

        Assert.assertNotNull(listingResponseWithUpparCase);
        Assert.assertTrue(listingResponseWithUpparCase.isSuccess());
        Assert.assertNotNull(listingResponseWithUpparCase.getListings());
        Assert.assertTrue(listingResponseWithUpparCase.getListings().size()==2);

        ListingDTO listingDTO1 = listingResponseWithUpparCase.getListings().get(0);
        Assert.assertNotNull(listingDTO1);
        Assert.assertTrue(listingDTO1.getId().longValue()==response.getLisitngId().longValue());
        Assert.assertEquals("Electronic & Media", listingDTO1.getCategory());
        Assert.assertEquals("mylisting 1", listingDTO1.getTitle());

        ListingDTO listingDTO2 = listingResponseWithUpparCase.getListings().get(1);
        Assert.assertNotNull(listingDTO2);
        Assert.assertTrue(listingDTO2.getId().longValue()==response2.getLisitngId().longValue());
        Assert.assertEquals("Mobile", listingDTO2.getCategory());
        Assert.assertEquals("mylisting 2", listingDTO2.getTitle());
    }


    @Test
    public void testGetListingByUserId(){
        CreateListingResponse response = service.createListing("pranav", "mylisting 1",
                "this is my 1st lisitng", 20d, "Electronic & Media");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        CreateListingResponse response2 = service.createListing("pranav", "mylisting 2",
                "this is my 2nd lisitng", 20d, "Mobile");
        Assert.assertNotNull(response2);
        Assert.assertTrue(response2.isSuccess());
        Assert.assertNotNull(response2.getLisitngId());

        Assert.assertTrue(response.getLisitngId().longValue() != response2.getLisitngId().longValue());

        GetListingResponse listingResponse =
                service.getListingByUnameAndListingId("PRAnav", response.getLisitngId());
        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNotNull(listingResponse.getListings());
        Assert.assertTrue(listingResponse.getListings().size()==1);
        Assert.assertEquals("mylisting 1", listingResponse.getListings().get(0).getTitle());

        listingResponse =
                service.getListingByUnameAndListingId("pranav", response2.getLisitngId());
        Assert.assertNotNull(listingResponse);
        Assert.assertTrue(listingResponse.isSuccess());
        Assert.assertNotNull(listingResponse.getListings());
        Assert.assertTrue(listingResponse.getListings().size()==1);
        Assert.assertEquals("mylisting 2", listingResponse.getListings().get(0).getTitle());
    }
}
