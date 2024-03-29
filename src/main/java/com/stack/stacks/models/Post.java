package com.stack.stacks.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(
            name = "posts_tags",
            joinColumns = {@JoinColumn(name="post_id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id")}
    )
    private List<Tag> tags;

    public Post () {
    }

    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Post(String title, String content, User user, List<Tag> tags) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user +
                ", tags=" + tags +
                '}';
    }
}
