package model;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment {
    @Transient
    private static final String BAD_WORDS = "địt|địt mẹ|đm|đcm|vl|vãi lồn";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String author;

    @Column
    @Pattern(regexp = "^.*(" + BAD_WORDS + ").*$", message = "Invalid Comment")
    private String content;

    @Column
    private int likes;

    @Column
    private int mark;

    @Column
    @PastOrPresent
    private Timestamp postTime;

    @ManyToOne()
    @JoinColumn(name = "picture_id", referencedColumnName = "id")
    private Picture picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int love) {
        this.likes = love;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", love=" + likes +
                ", mark=" + mark +
                ", postTime=" + postTime +
                ", picture=" + picture +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return getLikes() == comment.getLikes() &&
                getMark() == comment.getMark() &&
                Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getAuthor(), comment.getAuthor()) &&
                Objects.equals(getContent(), comment.getContent()) &&
                Objects.equals(getPostTime(), comment.getPostTime()) &&
                Objects.equals(getPicture(), comment.getPicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getContent(), getLikes(), getMark(), getPostTime(), getPicture());
    }
}
