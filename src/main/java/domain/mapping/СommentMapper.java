package domain.mapping;

import domain.entity.Comment;
import servlet.entity.CommentDto;

import static domain.mapping.UserMapper.userDao;

public class СommentMapper {

    public static Comment dtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        int userId = userDao.getByUsername(commentDto.getUsername()).get(0).getId();
        comment.setUserId(userId);
        comment.setPostId(commentDto.getPostId());
        comment.setCommentText(commentDto.getCommentText());
        return comment;
    }

    public static CommentDto commentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        String username = userDao.getById(String.valueOf(comment.getUserId())).get(0).getUsername();
        commentDto.setUsername(username);
        commentDto.setPostId(comment.getPostId());
        commentDto.setCommentText(comment.getCommentText());
        return commentDto;
    }

}
