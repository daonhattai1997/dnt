package dnt.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Controller
public class HandleErrorController implements ErrorController {

    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        String data = "";
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        if (status != null) {
//            Integer statusCode = Integer.valueOf(status.toString());
//            if(statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "error-404";
//            }
//            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "error-500";
//            }
//        }
//        return "error";
        try {
            Resource resource = resourceLoader.getResource("classpath:templates/404.html");
            InputStream inputStream = resource.getInputStream();

            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
            data.replaceAll("CodeAndMessage", statusCode + " - " + message);

        } catch (IOException e) {

        }
        finally {
            return data;
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}