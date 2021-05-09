package com.pranav.command.adapter;

import com.pranav.command.response.CreateUserResponse;
import com.pranav.command.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

@Component
public class UserAdapterImpl implements  IUserAdapter{

    @Value("${create.user.service.url}")
    private String create_userServiceUrl;

    @Value("${get.user.service.url}")
    private String get_userServiceUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public CreateUserResponse registerUser(final String username){
        System.out.println("Calling user service to Register user : "+ username);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("uname", username);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<CreateUserResponse> httpResponse = restTemplate.postForEntity(
                create_userServiceUrl, request , CreateUserResponse.class);
        CreateUserResponse response = null;
        if(HttpStatus.OK == httpResponse.getStatusCode()){
            System.out.println("Got 200 Response from user service");
            response = httpResponse.getBody();
        }else{
            System.out.println("Got non-200 Response from user service");
        }
        return response;
    }

    @Override
    public GetUserResponse getUser(final String username){
        System.out.println("Calling user service to get user : "+ username);
        ResponseEntity<GetUserResponse> httpResponse =
                restTemplate.getForEntity(get_userServiceUrl+username, GetUserResponse.class);
        GetUserResponse response = null;
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
        System.out.println("Connecting to create user service at : " + create_userServiceUrl);
        System.out.println("Connecting to get user service at : " + get_userServiceUrl);
    }

}
