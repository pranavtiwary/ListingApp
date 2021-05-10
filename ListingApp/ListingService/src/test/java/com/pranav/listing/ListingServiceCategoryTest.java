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
public class ListingServiceCategoryTest {

    @Autowired
    private IListingService service;

    @Test
    public void testGetListing(){
        CreateListingResponse response = service.createListing("pranavT", "mylisting 1",
                "this is my 1st lisitng", 20d, "Mobile");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertNotNull(response.getLisitngId());

        CreateListingResponse response2 = service.createListing("pranavt", "mylisting 2",
                "this is my 2nd lisitng", 10d, "Mobile");
        Assert.assertNotNull(response2);
        Assert.assertTrue(response2.isSuccess());
        Assert.assertNotNull(response2.getLisitngId());

        GetListingResponse listingResponse1 = service.getAllListingByCategoryByUser(
                "pranavT", "mobile", "SORT_TIME", "DSC");

        System.out.println(listingResponse1);
        Assert.assertNotNull(listingResponse1);
        Assert.assertTrue(listingResponse1.isSuccess());
        Assert.assertNotNull(listingResponse1.getListings());


        GetListingResponse listingResponse2 = service.getAllListingByCategoryByUser(
                "pranavt", "MobilE", "SORT_TIME", "ASC");

        System.out.println(listingResponse2);
        Assert.assertNotNull(listingResponse2);
        Assert.assertTrue(listingResponse2.isSuccess());
        Assert.assertNotNull(listingResponse2.getListings());
        Assert.assertTrue(listingResponse2.getListings().size()==2);

        GetListingResponse listingResponse3 = service.getAllListingByCategoryByUser(
                "pRanavt", "MobiLe", "SORT_PRICE", "DSC");

        System.out.println(listingResponse3);
        Assert.assertNotNull(listingResponse3);
        Assert.assertTrue(listingResponse3.isSuccess());
        Assert.assertNotNull(listingResponse3.getListings());
        Assert.assertTrue(listingResponse3.getListings().size()==2);

        GetListingResponse listingResponse4 = service.getAllListingByCategoryByUser(
                "pranAvT", "Mobile", "SORT_PRICE", "ASC");

        System.out.println(listingResponse4);
        Assert.assertNotNull(listingResponse4);
        Assert.assertTrue(listingResponse4.isSuccess());
        Assert.assertNotNull(listingResponse4.getListings());
        Assert.assertTrue(listingResponse4.getListings().size()==2);

        //usr id changed
        GetListingResponse listingResponse5 = service.getAllListingByCategoryByUser(
                "pranav", "MobilE", "SORT_TIME", "ASC");

        System.out.println(listingResponse5);
        Assert.assertNotNull(listingResponse5);
        Assert.assertTrue(listingResponse5.isSuccess());
        Assert.assertNull(listingResponse5.getListings());
    }
}
