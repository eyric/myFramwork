package com.levin.framework.springmvc.demo.controller;

import com.levin.framework.springmvc.demo.service.INamedService;
import com.levin.framework.springmvc.demo.service.IService;
import com.levin.framework.springmvc.framework.annotation.Autowired;
import com.levin.framework.springmvc.framework.annotation.Controller;
import com.levin.framework.springmvc.framework.annotation.RequestMapping;
import com.levin.framework.springmvc.framework.annotation.RequestParam;
import com.levin.framework.springmvc.framework.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: levin
 * @ date: 2018/2/25 上午10:48
 * @ description:
 */
@Controller("firstController")
@RequestMapping("/web")
public class FirstController {

    @Autowired
    private IService service;

    @Autowired
    private INamedService namedService;

    @RequestMapping("/query/.*.json")
//    @ResponseBody
    public ModelAndView query(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("name") String name,
                              @RequestParam("addr") @Deprecated String addr) {
//        out(response, "get params name =" + name);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("name", name);
        model.put("addr", addr);
        return new ModelAndView("first.emml", model);
    }

    @RequestMapping("/add.json")
//    @ResponseBody
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        out(response, "this is json str");
        return null;
    }

    private void out(HttpServletResponse response, String str) {
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
