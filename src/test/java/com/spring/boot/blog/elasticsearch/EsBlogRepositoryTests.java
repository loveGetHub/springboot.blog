package com.spring.boot.blog.elasticsearch;

import com.spring.boot.blog.elasticsearch.entity.EsBlog;
import com.spring.boot.blog.elasticsearch.service.EsBlogRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EsBlogRepositoryTests {
    @Autowired
    EsBlogRepository esBlogRepository;
    @BeforeEach
    public void init(){
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("登黄鹤楼","王之涣的登黄鹤楼","白日依山尽，黄河入海流。欲穷千里目，更上一层楼"));
        esBlogRepository.save(new EsBlog("相思","王维的相思","红豆生南国⑵，春来发几枝⑶？愿君多采撷⑷，此物最相思⑸。"));
        esBlogRepository.save(new EsBlog("静夜思","李白的静夜思","床前明月光⑵，疑是地上霜⑶。举头望明月⑷，低头思故乡。"));
    }
    @Test
    public void tsstFindDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(){
        List<String> list = new ArrayList<>();
        list.add("id");
        // 这里的"recordNo"是实体类的主键，记住一定要是实体类的属性，而不能是数据库的字段
        Pageable pageable = PageRequest.of(0,20);
        String title="相思";
        String summary="思";
        String content="思";
        Page<EsBlog> esBlogs = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title,summary,content,pageable);
        System.out.println(esBlogs.getContent());
    }
}
