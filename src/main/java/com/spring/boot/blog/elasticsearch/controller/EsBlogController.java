package com.spring.boot.blog.elasticsearch.controller;

import com.spring.boot.blog.elasticsearch.entity.EsBlog;
import com.spring.boot.blog.elasticsearch.service.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/blog")
public class EsBlogController {
    @Autowired
    EsBlogRepository esBlogRepository;
    @GetMapping
    public List<EsBlog> getBlog(@RequestParam(value = "title")String title,
                                @RequestParam(value = "summary")String summary,
                                @RequestParam(value = "content")String content,
                                @RequestParam(value = "page")int page,
                                @RequestParam(value = "size")int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<EsBlog> esBlogs = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
        System.out.println(esBlogs.getContent());
        return esBlogs.getContent();
    }
}
