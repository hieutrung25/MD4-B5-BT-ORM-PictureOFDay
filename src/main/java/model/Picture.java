package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String imageLink;

    @Column
    private Timestamp postTime;

    @OneToMany(mappedBy = "picture", fetch = FetchType.EAGER)
    private Collection<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp datePost) {
        this.postTime = datePost;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", datePost=" + postTime +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture)) return false;
        Picture picture = (Picture) o;
        return Objects.equals(getId(), picture.getId()) &&
                Objects.equals(getName(), picture.getName()) &&
                Objects.equals(getImageLink(), picture.getImageLink()) &&
                Objects.equals(getPostTime(), picture.getPostTime()) &&
                Objects.equals(getComments(), picture.getComments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getImageLink(), getPostTime(), getComments());
    }
}
