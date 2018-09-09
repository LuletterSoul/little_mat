package njust.inteceptors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author XiangDe Liu qq313700046@icloud.com .
 * @version 1.5 created in
 */

public class AccessProcessInterceptor implements HandlerInterceptor
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessProcessInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o)
        throws Exception
    {
        LOGGER.info("Http Request method is ~~~~~~~~~~~~~~~~{}~~~~~~~~~~~~~~~~~.",httpServletRequest.getMethod());
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        httpServletResponse.setHeader("Access-Control-Allow-Headers",
                "Content-Type,Content-Length, Authorization, Accept,X-Requested-With,X-timestamp,Username,X-ApiKey");

        httpServletResponse.setHeader("Access-Control-Allow-Methods",
            "PUT,POST,GET,DELETE,OPTIONS");

        String method = httpServletRequest.getMethod();

        if (method.equals("OPTIONS"))
        {
            httpServletResponse.setStatus(200);
            return false;
        }

        System.out.println(method);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView)
        throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e)
        throws Exception
    {

    }
}
