package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    @Query("SELECT c FRom Comment c WHERE c.post.id =:postId")
    List<Comment> getCommentsByPostId(@Param("postId") Long postId);

    @Query("SELECT c FRom Comment c WHERE c.post.id =:postId AND c.id =:commentId")
    List<Comment> getCommentsByPostIdAndCommentId(@Param("postId")Long postId, @Param("commentId") Long commentId);

    @Query("DELETE FROM Comment c WHERE c.post.id =:postId AND c.id =:commentId")
    @Modifying
    void deleteByIdAndPostId(Long postId, Long commentId);

    // END
}
