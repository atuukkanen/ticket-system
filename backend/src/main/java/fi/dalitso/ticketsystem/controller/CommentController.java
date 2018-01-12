package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.domain.Comment;
import fi.dalitso.ticketsystem.domain.ModificationInfo;
import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    public Comment update(@PathVariable Long commentId, Comment comment) {
        // TODO: Get the real editing user.
        comment.setCreator(new ModificationInfo("Editing user"));
        return commentService.update(commentId, comment);
    }

    @RequestMapping(value = "/{ticketId}", method = RequestMethod.POST)
    public Ticket addNewComment(@PathVariable Long ticketId, Comment comment) {
        // TODO: Get the real commenting user.
        comment.setCreator(new ModificationInfo("Commenting user"));
        return commentService.addNewComment(ticketId, comment);
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
