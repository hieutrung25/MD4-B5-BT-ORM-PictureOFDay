package repositories;

import model.Comment;
import model.Picture;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    Iterable<Comment> findAllByPictureAndPostTimeBetween(Picture picture,
                                                         Timestamp startTime,
                                                         Timestamp endTime,
                                                         Sort sort);
}
