package com.commerce.controler;

import com.commerce.dto.ProductEs;
import com.commerce.service.AutoCompleteService;
import com.commerce.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search-service")
public class SearchController {

    @Autowired
    private AutoCompleteService autoCompleteService;

    @Autowired
    private SearchService searchService;

    @GetMapping(path = "/search")
    public List<ProductEs> search(@RequestParam String query) throws IOException {
        return this.searchService.search(query);
    }

    @GetMapping(path = "/autocomplte")
    public List<ProductEs> autoCompleteSearch(@RequestParam String query) throws IOException {
        return autoCompleteService.performAutocompleteSearch(query);
    }

}
