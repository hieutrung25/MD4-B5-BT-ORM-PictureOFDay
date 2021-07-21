package controllers;

import model.Comment;
import model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.comment.CommentService;
import service.comment.exception.InvalidContentException;
import service.picture.PictureService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    private final PictureService pictureService;

    @Autowired
    public CommentController(CommentService commentService, PictureService pictureService) {
        this.commentService = commentService;
        this.pictureService = pictureService;
    }

    @GetMapping("/like/{image}/{comment}")
    public RedirectView likeComment(@PathVariable("image") Long pictureId,
                                    @PathVariable("comment") Long commentId) {
        Comment comment = commentService.findOne(commentId);
        comment.setLikes(comment.getLikes() + 1);
        commentService.save(comment);
        return new RedirectView("/picture/" + pictureId);
    }

    @PostMapping("/submit/{image}")
    public RedirectView submitComment(@PathVariable("image") Long pictureId,
                                      @Validated @ModelAttribute("newComment") Comment comment,
                                      BindingResult bindingResult) throws InvalidContentException {
        Picture picture = pictureService.findOne(pictureId);
        Timestamp postTime = Timestamp.valueOf(LocalDateTime.now());
        comment.setPicture(picture);
        comment.setPostTime(postTime);

        if (bindingResult.hasFieldErrors()) {
            throw new InvalidContentException();
        } else {
            commentService.save(comment);
        }
        return new RedirectView("/images?page=" + (pictureId - 1));
    }

    @ExceptionHandler(InvalidContentException.class)
    public ModelAndView handleException() {
        return new ModelAndView("error");
    }
}
