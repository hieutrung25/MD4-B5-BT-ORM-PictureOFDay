package service.comment;

import model.Comment;
import model.Picture;
import service.GenericService;

import java.sql.Timestamp;
import java.util.List;

public interface CommentService extends GenericService<Comment> {
    List<Comment> findAllByPictureAndPostTimeBetween(Picture picture, Timestamp startTime, Timestamp endTime);
}
