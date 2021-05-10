package com.pranav.listing;

import com.pranav.listing.response.CreateListingResponse;
import com.pranav.listing.response.GetListingResponse;
import com.pranav.listing.response.GetTopCategoryResponse;
import com.pranav.listing.service.CacheService;
import com.pranav.listing.service.IListingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ListingServiceTopCategoryTest {

    @Autowired
    private IListingService service;

    @Before
    public void setup(){
        CacheService.clear();
    }

    @Test
    public void testTopListing(){
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

        GetTopCategoryResponse resCat = service.getTopCategoryInListings();
        Assert.assertNotNull(resCat);
        Assert.assertTrue(resCat.isSuccess());
        Assert.assertNotNull(resCat.getCategory());
        Assert.assertEquals("Mobile".toLowerCase(Locale.ROOT), resCat.getCategory());

        CacheService.printCache();

        CreateListingResponse response3 = service.createListing("user", "mylisting 2 Elec",
                "this is my 2nd lisitng", 10d, "Elec");
        CreateListingResponse response4 = service.createListing("user1", "mylisting 3",
                "this is my 2nd lisitng", 10d, "Elec");
        CreateListingResponse response5 = service.createListing("user2", "mylisting 4",
                "this is my 2nd lisitng", 10d, "Elec");

        CacheService.printCache();

        resCat = service.getTopCategoryInListings();
        Assert.assertNotNull(resCat);
        Assert.assertTrue(resCat.isSuccess());
        Assert.assertNotNull(resCat.getCategory());
        Assert.assertEquals("Elec".toLowerCase(Locale.ROOT), resCat.getCategory());

        service.deleteListing(response3.getLisitngId(), "user");
        service.deleteListing(response4.getLisitngId(), "user1");

        CacheService.printCache();

        resCat = service.getTopCategoryInListings();
        Assert.assertNotNull(resCat);
        Assert.assertTrue(resCat.isSuccess());
        Assert.assertNotNull(resCat.getCategory());
        Assert.assertEquals("Mobile".toLowerCase(Locale.ROOT), resCat.getCategory());
    }
}
