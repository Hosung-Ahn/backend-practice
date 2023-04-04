package helle.restapimysql.service.impl;

import helle.restapimysql.domain.Post;
import helle.restapimysql.payload.PostDto;
import helle.restapimysql.repository.PostRepository;
import helle.restapimysql.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post newPost = postRepository.save(post);

        PostDto postResponse = new PostDto();
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setDescription(post.getDescription());
        return postResponse;
    }
}
