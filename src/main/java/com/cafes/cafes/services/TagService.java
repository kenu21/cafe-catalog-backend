package com.cafes.cafes.services;

import com.cafes.cafes.dto.TagDtoResponse;
import com.cafes.cafes.mappers.TagMapper;
import com.cafes.cafes.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDtoResponse> getTags() {
        return tagRepository.findAll().stream().map(tagMapper::toDto).toList();
    }
}
