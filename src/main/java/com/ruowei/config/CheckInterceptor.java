package com.ruowei.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CheckInterceptor implements HandlerInterceptor {

    /**
     * 请求方法处理前执行
     * @param request
     * @param response
     * @param handler
     * @return 是否放行该请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String appId=request.getParameter("app_id");
        String appSecret=request.getParameter("app_secret");
        System.out.println(request.getRemoteAddr());
        // 校验是否合法
//        if(StringUtils.isNotBlank(appId)&& StringUtils.isNotBlank(appSecret)&& checkAppSourceIsExsist(appId,appSecret)){
//            return true;	//合法通过
//        } else{
//            //非法给出提示
//            response.setCharacterEncoding("UTF-8");
//            response.getOutputStream().write(ResultUtils.error(-1, "您无操作此接口的权限!").getBytes());
//            return false;
//        }

        return false;
    }

    /**
     *请求方法执行后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
