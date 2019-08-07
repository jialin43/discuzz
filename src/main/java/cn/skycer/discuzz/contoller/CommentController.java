package cn.skycer.discuzz.contoller;

import cn.skycer.discuzz.dto.CommentDTO;
import cn.skycer.discuzz.dto.ResultDTO;
import cn.skycer.discuzz.exception.CustomizeErrorCode;
import cn.skycer.discuzz.mapper.CommentMapper;
import cn.skycer.discuzz.model.Comment;
import cn.skycer.discuzz.model.User;
import cn.skycer.discuzz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Johnny on 2019/8/6.
 */
@Controller
public class CommentController {

    @Autowired
    CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }else {
            Comment comment = new Comment();
            comment.setParentId(commentDTO.getParentId());
            comment.setContent(commentDTO.getContent());
            comment.setType(commentDTO.getType());
            comment.setGmtCreate(System.currentTimeMillis());
            comment.setGmtModified(System.currentTimeMillis());
            comment.setCommentator(user.getAccountId());
            comment.setLikeCount(0);
            commentService.insert(comment);
            return ResultDTO.okOf();

        }


    }
}
