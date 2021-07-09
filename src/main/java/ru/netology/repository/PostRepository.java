package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final AtomicLong id = new AtomicLong();
    private final Map<Long, Post> posts = new ConcurrentHashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.of(posts.get(id));
    }

    public Post save(Post post) {
        post.setId(id.incrementAndGet());
        return posts.put(id.get(), post);
    }

    public Post update(Post post) {
        return posts.put(post.getId(), post);
    }

    public synchronized void removeById(long id) {
        posts.remove(id);
    }
}
