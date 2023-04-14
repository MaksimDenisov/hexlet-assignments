package exercise.controller;

import exercise.model.Comment;
import exercise.model.Post;
import exercise.repository.CommentRepository;
import exercise.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    /*
    POST /posts/{postId}/comments - создание нового комментария для поста. Должны выводится только комментарий, принадлежащие посту.
    PATCH /posts/{postId}/comments/{commentId} - редактирование конкретного комментария поста. Если такой комментарий не существует, должен вернуться ответ с кодом 404.
    DELETE /posts/{postId}/comments/{commentId} - удаление конкретного комментария у поста. Если такой комментарий не существует, должен вернуться ответ с кодом 404.
     */
    @GetMapping("{postId}/comments")
    public List<Comment> getComments(@PathVariable String postId) {
        return commentRepository.getCommentsByPostId(Long.valueOf(postId));
    }

    @GetMapping("{postId}/comments/{commentId}")
    public Comment getCommentsFromPost(@PathVariable String postId, @PathVariable String commentId) {
        List<Comment> comments = commentRepository.getCommentsByPostIdAndCommentId(Long.valueOf(postId), Long.valueOf(commentId));
        if(comments.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the page is not found"
            );
        }
        return comments.get(0);
    }

    @PostMapping("{postId}/comments")
    public List<Comment> createCommentsFromPost(@PathVariable String postId, @RequestBody Comment comment) {
        Post post = postRepository.findById(Long.valueOf(postId)).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "the page is not found"));
        comment.setPost(post);
        commentRepository.save(comment);
        return commentRepository.getCommentsByPostId(Long.valueOf(postId));
    }

    @PatchMapping("{postId}/comments/{commentId}")
    public Comment updateCommentsFromPost(@PathVariable String postId, @PathVariable String commentId, @RequestBody Comment comment) {
        Post post = postRepository.findById(Long.valueOf(postId)).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "the page is not found"));
        List<Comment> comments = commentRepository.getCommentsByPostIdAndCommentId(Long.valueOf(postId), Long.valueOf(commentId));
        if(comments.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the page is not found"
            );
        }
        comment.setId(Long.valueOf(commentId));
        comment.setPost(post);
        commentRepository.save(comment);
        return commentRepository.getCommentsByPostIdAndCommentId(Long.valueOf(postId), Long.valueOf(commentId)).get(0);
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCommentsFromPost(@PathVariable String postId, @PathVariable String commentId) {
        List<Comment> comments = commentRepository.getCommentsByPostIdAndCommentId(Long.valueOf(postId), Long.valueOf(commentId));
        if(comments.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the page is not found"
            );
        }
        commentRepository.delete(comments.get(0));
        //commentRepository.deleteByIdAndPostId(Long.valueOf(postId), Long.valueOf(commentId));
    }
}
