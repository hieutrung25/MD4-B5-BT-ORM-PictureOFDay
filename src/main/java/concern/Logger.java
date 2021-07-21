package concern;

import model.Comment;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class Logger {
    @AfterThrowing(pointcut = "execution(public * controllers.CommentController.submitComment(..))", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println("Comment có từ xấu");
        Object[] object = joinPoint.getArgs();
        Comment commentInvalid = (Comment) object[1];
        String author = commentInvalid.getAuthor();
        String content = commentInvalid.getContent();
        String imagePost = String.valueOf(commentInvalid.getPicture().getId());
        String postTime = commentInvalid.getPostTime().toString();
        System.out.printf("Author: %s\nContent: %s\nImage: %s\nPost Time: %s\n",
                author, content, imagePost, postTime);
    }
}
