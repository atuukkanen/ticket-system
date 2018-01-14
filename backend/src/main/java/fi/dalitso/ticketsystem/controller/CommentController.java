package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.domain.*;
import fi.dalitso.ticketsystem.exception.CommentNotFoundException;
import fi.dalitso.ticketsystem.exception.TicketNotFoundException;
import fi.dalitso.ticketsystem.service.CommentService;
import fi.dalitso.ticketsystem.service.NotifierService;
import fi.dalitso.ticketsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;
    private UserService userService;
    private NotifierService notifier;

    @ApiOperation(value = "Get all comments.")
    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @ApiOperation(value = "Edit an existing comment.")
    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public Comment update(@PathVariable Long commentId,
                          @RequestBody Comment comment) throws CommentNotFoundException {
        User editingUser = userService.getAuthenticatedUser();
        comment.setCreation(new ModificationInfo(editingUser));

        Comment editedComment = commentService.update(commentId, comment);
        notifier.notify(
                Action.COMMENT_UPDATED, null, editedComment, editingUser);
        return editedComment;
    }

    @ApiOperation(value = "Add a new comment to an existing ticket.")
    @RequestMapping(value = "/{ticketId}", method = RequestMethod.POST)
    public Ticket addNewComment(@PathVariable Long ticketId,
                                @RequestBody Comment comment) throws TicketNotFoundException {
        User commentingUser = userService.getAuthenticatedUser();
        comment.setCreation(new ModificationInfo(commentingUser));

        Ticket ticket = commentService.addNewComment(ticketId, comment);
        notifier.notify(
                Action.COMMENT_CREATED, ticket, comment, commentingUser);
        return ticket;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setNotifier(NotifierService notifier) {
        this.notifier = notifier;
    }
}
