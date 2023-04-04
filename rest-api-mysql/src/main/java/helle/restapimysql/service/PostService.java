package helle.restapimysql.service;

import helle.restapimysql.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
