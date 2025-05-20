package org.example.sii_charity_collection_boxes.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


@Service
public class CurrencyService {
    private final String API_KEY = "d84231fab177ed30b2228d15f8eee2f2";

    public BigDecimal convertCurrency(String from, String to, BigDecimal amount) throws Exception{
        String url = "https://api.exchangerate.host/convert?access_key=" + API_KEY + "&from=" + from + "&to=" + to + "&amount=" + amount;

        RestTemplate restTemplate = new RestTemplate();

            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            JsonNode resultNode = root.path("result");
            if (resultNode.isMissingNode()) {
                throw new RuntimeException("'result' not found.");
            }

            return resultNode.decimalValue();
    }
}
