package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.domain.Comment;
import fi.dalitso.ticketsystem.domain.ModificationInfo;
import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.domain.User;
import fi.dalitso.ticketsystem.service.CommentService;
import fi.dalitso.ticketsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public Comment update(@PathVariable Long commentId, @RequestBody Comment comment) {
        User editingUser = userService.getAuthenticatedUser();
        comment.setCreation(new ModificationInfo(editingUser));
        return commentService.update(commentId, comment);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.POST)
    public Ticket addNewComment(@PathVariable Long ticketId, @RequestBody Comment comment) {
        User commentingUser = userService.getAuthenticatedUser();
        comment.setCreation(new ModificationInfo(commentingUser));
        return commentService.addNewComment(ticketId, comment);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
