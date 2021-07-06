package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
    private long id;
    private final Map<Long, Post> posts = new HashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.of(posts.get(id));
    }

    public synchronized Post save(Post post) {
        return posts.put(++id, post);
    }

    public synchronized Post update(Post post) {
        return posts.put(post.getId(), post);
    }

    public synchronized void removeById(long id) {
        posts.remove(id);
    }
}
