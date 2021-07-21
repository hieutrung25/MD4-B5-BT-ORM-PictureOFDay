package service.comment;

import model.Comment;
import model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.CommentRepository;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        Iterable<Comment> iterable = commentRepository.findAll();
        List<Comment> comments = new LinkedList<>();
        for (Comment comment : iterable) {
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public List<Comment> findAll(Sort sort) {
        Iterable<Comment> iterable = commentRepository.findAll(sort);
        List<Comment> comments = new LinkedList<>();
        for (Comment comment : iterable) {
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public List<Comment> findAllByPictureAndPostTimeBetween(Picture picture, Timestamp startTime, Timestamp endTime) {
        List<Comment> comments = new LinkedList<>();
        Sort sort = Sort.by("postTime").descending();
        Iterable<Comment> iterable = commentRepository.findAllByPictureAndPostTimeBetween(picture, startTime, endTime,sort);
        for (Comment comment : iterable) {
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public Comment findOne(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
