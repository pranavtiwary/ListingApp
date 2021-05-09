package com.pranav.command.adapter;

import com.pranav.command.response.CreateListingResponse;
import com.pranav.command.response.CreateUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class ListingAdapterImpl implements  IListingAdapter{

    @Value("${create.listing.service.url}")
    private String create_url;

    @Value("${getlisting.byall.service.url}")
    private String list_all_url;

    @Value("${getlisting.byuser.bylistingid.service.url}")
    private String list_by_userid_lisitngid_url;

    @Value("${getlisting.byUsername.service.url}")
    private String list_by_username_url;

    @Value("${getlisting.bylistingId.service.url}")
    private String list_by_listingid_url;

    private RestTemplate restTemplate = new RestTemplate();

    public CreateListingResponse createListing(final String uname,
                                               final String title,
                                               final String description,
                                               final Double price,
                                               final String category){
        System.out.println("Calling Listing service to Create listing");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("uname", uname);
        map.add("title", title);
        map.add("description", description);
        map.add("price", price.toString());
        map.add("category", category);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<CreateListingResponse> httpResponse = restTemplate.postForEntity(
                create_url, request , CreateListingResponse.class);
        CreateListingResponse response = null;
        if(HttpStatus.OK == httpResponse.getStatusCode()){
            System.out.println("Got 200 Response from user service");
            response = httpResponse.getBody();
        }else{
            System.out.println("Got non-200 Response from user service");
        }
        return response;
    }

    @PostConstruct
    public void init(){
        System.out.println("Connecting to create listing service at : " + create_url);
        System.out.println("Connecting to get all listing service at : " + list_all_url);
        System.out.println("Connecting to get list by listingid listing service at : " + list_by_listingid_url);
        System.out.println("Connecting to get all listing by userid & lisitngId service at : " + list_by_userid_lisitngid_url);
    }

}
