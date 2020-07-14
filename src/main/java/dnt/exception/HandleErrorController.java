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

    @ResponseBody
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        return String.format("<html><head>" +
                        "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">" +
                        "    <style>" +
                        "        body { background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAaCAYAAACpSkzOAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMC8yOS8xMiKqq3kAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAABHklEQVRIib2Vyw6EIAxFW5idr///Qx9sfG3pLEyJ3tAwi5EmBqRo7vHawiEEERHS6x7MTMxMVv6+z3tPMUYSkfTM/R0fEaG2bbMv+Gc4nZzn+dN4HAcREa3r+hi3bcuu68jLskhVIlW073tWaYlQ9+F9IpqmSfq+fwskhdO/AwmUTJXrOuaRQNeRkOd5lq7rXmS5InmERKoER/QMvUAPlZDHcZRhGN4CSeGY+aHMqgcks5RrHv/eeh455x5KrMq2yHQdibDO6ncG/KZWL7M8xDyS1/MIO0NJqdULLS81X6/X6aR0nqBSJcPeZnlZrzN477NKURn2Nus8sjzmEII0TfMiyxUuxphVWjpJkbx0btUnshRihVv70Bv8ItXq6Asoi/ZiCbU6YgAAAABJRU5ErkJggg==);}" +
                        "        .error-template {padding: 40px 15px;text-align: center;}" +
                        "        .error-actions {margin-top:15px;margin-bottom:15px;}" +
                        "        .error-actions .btn { margin-right:10px; }" +
                        "    </style>" +
                        "</head>  " +
                        "<div class=\"container\">" +
                        "    <div class=\"row\">" +
                        "        <div class=\"col-md-12\">" +
                        "            <div class=\"error-template\">" +
                        "                <h1 class=\"display-1\">Oops!</h1><br><br><br>" +
                        "                <h2><b>" +
                                            statusCode + " - " + message +
                        "               </b></h2>" +
                        "                <div class=\"error-details\">" +
                        "                    Sorry, an error has occured!" +
                        "                </div><br>" +
                        "                <div class=\"error-actions\">" +
                        "                    <a href=\"/\" class=\"btn btn-primary btn-lg\"><span class=\"glyphicon glyphicon-home\"></span>" +
                        "                        Take Home </a>" +
                        "                </div>" +
                        "            </div>" +
                        "        </div>" +
                        "    </div>" +
                        "</div></html>",
                statusCode, message);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}