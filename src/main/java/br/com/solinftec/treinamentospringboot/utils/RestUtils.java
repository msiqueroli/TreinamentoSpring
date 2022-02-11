package br.com.solinftec.treinamentospringboot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestUtils {

    private static Logger logger = LoggerFactory.getLogger(RestUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private RestUtils() {

    }

    public static <T> List<T> getForList(String url, Class<T> type) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            String result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            return objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

    public static <T> T postForObject(String url, Class<T> type, Object body) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(body), getHeader("token"));
            String result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
            return objectMapper.readValue(result, type);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return null;
    }

//    public static <T> List<T> postForList(String url, Class<T> type, Object body) {
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(body), getHeader(RequestContext.getCurrentToken()));
//            String result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
//            return objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
//        } catch (Exception e) {
//            logger.error(e.toString());
//        }
//        return null;
//    }

    public static <T> List<T> getForList(String url, Class<T> type, String token) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> entity = new HttpEntity<>(getHeader(token));
            String result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            return objectMapper.readValue(result, objectMapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return new ArrayList<>();
    }

    private static HttpHeaders getHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("x-auth-token", token);
        headers.add("request-origin", "farmer-api");
        return headers;
    }

}
