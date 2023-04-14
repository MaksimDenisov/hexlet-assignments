package exercise.model;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    // BEGIN
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// – уникальный идентификатор поста. Первичный ключ, должен быть автогенерируемым

    private String content;// – содержимое комментария, количество символов в комментарии не ограничено
    @ManyToOne
    private Post post;// – пост, которому принадлежит комментарий. Укажите, что комментарий связан с постом связью "многие к одному". В одном посте может быть множество комментариев, но отдельный комментарий всегда принадлежит только одному посту
    // END
}
