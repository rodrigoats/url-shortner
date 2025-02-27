package com.rodrigoats.urlshortner.api.controllers;

import com.rodrigoats.urlshortner.api.services.ShortenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/ats")
public class ShortenerController {

  private final ShortenService shortenService;

  public ShortenerController(ShortenService shortenService) {
    this.shortenService = shortenService;
  }

  @PostMapping
  public String shortUrl(@RequestBody String url) {
    return shortenService.shortenUrl(url);
  }

  @GetMapping("/{urlEncoded}")
  public void redirect(@PathVariable String urlEncoded, ServerHttpResponse response) {
    response.setStatusCode(HttpStatus.FOUND);
    response.getHeaders().setLocation(URI.create(urlEncoded));
  }
}
