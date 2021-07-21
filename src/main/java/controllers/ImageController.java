package controllers;

import model.Comment;
import model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.comment.CommentService;
import service.picture.PictureService;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/images")
public class ImageController {
    private final CommentService commentService;

    private final PictureService pictureService;

    public ImageController(CommentService commentService, PictureService pictureService) {
        this.commentService = commentService;
        this.pictureService = pictureService;
    }

    @ModelAttribute("timeNow")
    public String timeNow() {
        Locale locale = new Locale("vi", "VN");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale);
        return LocalDate.now().format(formatter);
    }

    @GetMapping()
    public ModelAndView showHome(@PageableDefault(size = 1) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Timestamp startTime = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        Timestamp endTime = Timestamp.valueOf(LocalDateTime.now());
        Page<Picture> picturePage = pictureService.findAll(pageable);
        Picture picture = picturePage.getContent().get(0);
        List<Comment> commentList = commentService.findAllByPictureAndPostTimeBetween(picture, startTime, endTime);
        modelAndView.addObject("picture", picture);
        modelAndView.addObject("picturePage", picturePage);
        modelAndView.addObject("commentList", commentList);
        modelAndView.addObject("newComment", new Comment());
        return modelAndView;
    }
}
