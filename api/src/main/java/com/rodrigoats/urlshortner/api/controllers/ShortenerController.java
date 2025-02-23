package com.rodrigoats.urlshortner.api.controllers;

import com.rodrigoats.urlshortner.api.services.ShortenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/ats")
public class ShortenerController {

  private final ShortenService shortenService;

  public ShortenerController(ShortenService shortenService) {
    this.shortenService = shortenService;
  }

  @GetMapping
  public String shortUrl() {
    var url = "http://test.com";
    return shortenService.shortenUrl(url);
  }
}
