package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.Comment;
import fi.dalitso.ticketsystem.domain.Ticket;
import fi.dalitso.ticketsystem.exception.CommentNotFoundException;
import fi.dalitso.ticketsystem.exception.TicketNotFoundException;
import fi.dalitso.ticketsystem.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private TicketService ticketService;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(Long id) {
        return commentRepository.findOne(id);
    }

    @Transactional
    public Ticket addNewComment(Long ticketId, Comment comment)
                                            throws TicketNotFoundException {
        Ticket ticketToComment = ticketService.getTicket(ticketId);
        if (ticketToComment == null)
            throw new TicketNotFoundException();
        ticketToComment.addComment(comment);
        return ticketToComment;
    }

    @Transactional
    public Comment update(Long id, Comment uComment)
                                            throws CommentNotFoundException {
        Comment oldComment = commentRepository.findOne(id);
        if (oldComment == null)
            throw new CommentNotFoundException();
        oldComment.update(uComment);
        return oldComment;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
