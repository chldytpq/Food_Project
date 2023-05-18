package kopo.poly.test;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class test1 {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "http://openapi.foodsafetykorea.go.kr/api/56ceaa92652744248404/COOKRCP01/json/1/3?RCP_NM=김치찌개";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


        System.out.println(response);
    }
}