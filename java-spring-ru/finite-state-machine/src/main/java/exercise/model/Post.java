package exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Lob
    private String body;

    private PostState state = PostState.CREATED;

    // BEGIN
    public boolean publish() {
        if (state == PostState.CREATED) {
            state = PostState.PUBLISHED;
            return true;
        }
        return false;
    }

    public boolean archive() {
        if (state == PostState.CREATED || state == PostState.PUBLISHED) {
            state = PostState.ARCHIVED;
            return true;
        }
        return false;
    }
    // END
}
