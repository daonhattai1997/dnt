package dnt.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HandleErrorController implements ErrorController {

    @ResponseBody
    @RequestMapping("/error")
    public Map handleError(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();
        map.put("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
        map.put("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString());

        return map;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}