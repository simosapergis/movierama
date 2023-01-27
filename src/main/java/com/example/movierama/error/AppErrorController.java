package com.example.movierama.error;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Basic Controller which is called for unhandled errors
 */
@Controller
public class AppErrorController implements ErrorController {

    /**
     * Error Attributes in the Application
     */
    private final ErrorAttributes errorAttributes;

    private final static String ERROR_PATH = "/error";

    /**
     * Controller for the Error Controller
     * @param errorAttributes
     */
    public AppErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    /**
     * Supports the HTML Error View
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(WebRequest request) {
        return new ModelAndView("/error_page", errorAttributes.getErrorAttributes(request, ErrorAttributeOptions.defaults()));
    }
//
//    /**
//     * Supports other formats like JSON, XML
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH)
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }
//
//
//    private boolean getTraceParameter(HttpServletRequest request) {
//        String parameter = request.getParameter("trace");
//        if (parameter == null) {
//            return false;
//        }
//        return !"false".equals(parameter.toLowerCase());
//    }
//
//    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
//                                                   boolean includeStackTrace) {
//        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
//        return this.errorAttributes.getErrorAttributes(requestAttributes,
//                includeStackTrace);
//    }
//
//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer) request
//                .getAttribute("javax.servlet.error.status_code");
//        if (statusCode != null) {
//            try {
//                return HttpStatus.valueOf(statusCode);
//            }
//            catch (Exception ex) {
//            }
//        }
//        return HttpStatus.INTERNAL_SERVER_ERROR;
//    }
}