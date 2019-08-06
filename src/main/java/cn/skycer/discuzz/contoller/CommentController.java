package cn.skycer.discuzz.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Johnny on 2019/8/6.
 */
@Controller
public class CommentController {
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(){
        return null;
    }
}
