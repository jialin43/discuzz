package cn.skycer.discuzz.advice;

import cn.skycer.discuzz.dto.ResultDTO;
import cn.skycer.discuzz.exception.CustomizeErrorCode;
import cn.skycer.discuzz.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Johnny on 2019/8/5.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Throwable e, Model model) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //return json
            ResultDTO resultDTO=null;
            if(e instanceof CustomizeException) {
                 resultDTO = ResultDTO.errorOf((CustomizeException)e);
            }else {
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return null;
        }else {
            //return page
            if(e instanceof CustomizeException) {
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message",e.getMessage());
            }
            System.out.println(e.getMessage());

            return new ModelAndView("error");
        }

    }
}
