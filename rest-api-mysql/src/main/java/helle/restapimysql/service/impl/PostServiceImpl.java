package helle.restapimysql.service.impl;

import helle.restapimysql.domain.Post;
import helle.restapimysql.exception.ResourceNotFoundException;
import helle.restapimysql.payload.PostDto;
import helle.restapimysql.repository.PostRepository;
import helle.restapimysql.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    private static Post mapToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        return post;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream().map(m -> mapToDto(m)).toList();
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("post", "post_id", id.toString()));
        return mapToDto(post);

    }
}
