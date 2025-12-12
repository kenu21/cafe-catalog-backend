package com.cafes.cafes.controllers;

import com.cafes.cafes.dto.TagDtoResponse;
import com.cafes.cafes.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public List<TagDtoResponse> getTags() {
        return tagService.getTags();
    }
}
