package com.rodrigoats.urlshortner.api.services;

import com.rodrigoats.encryption.Base62Encoder;
import org.springframework.stereotype.Service;

@Service
public class ShortenService {
  //TODO  Use @Value to get this from ENV Var
  private static final String BASE_URL = "http://localhost:8080/";


  public String shortenUrl(String url) {
    return "{ 'key': '" + Base62Encoder.encode(url) + "', 'value': '" +url+"'}";
  }
}
