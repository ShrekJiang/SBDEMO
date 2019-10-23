package com.xqj.sbdemo.controller;

import com.xqj.sbdemo.entity.Author;
import com.xqj.sbdemo.mapper.AuthorMapper;
import com.xqj.sbdemo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.xqj.sbdemo.utils.OSUtils.*;

@Slf4j
@Controller
@RequestMapping(value = {"/hello"})
public class HelloController {

    @Autowired
    private AuthorMapper mapper;

    @Value("${name}")
    private String name;

    @Value("${age}")
    private int age;

    @Value("${projectName}")
    private String projectName;

    @Autowired
    //private StringRedisTemplate<String,String> stringRedisTemplate;这样写报错
    //explain 这里stringRedisTemplate取名随便取都可以，那说明这里的@Autowired是根据前面的参数类型去定对象的。。
    private RedisTemplate<String, String> stringRedisTemplate;

    //explain 这里划红色波浪线，但是也能正常用
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = {"/testRedis"}, method = RequestMethod.GET)
    public String testRedis() {
        Author author = new Author();
        author.setId("111");
        author.setName("111_name");
        author.setAge(100);
        Author a = mapper.getAuthorByID(author);
        //valueOperations.set("author",author);
        Author aa = (Author) valueOperations.get("author");
        boolean exist = stringRedisTemplate.hasKey("author");
        if (aa != null) {
            log.info(aa.getName());
            log.error("no exist");
        } else {
            System.out.printf("no exist");
        }
        return a.getId() + a.getName() + a.getAge() + name + age + stringRedisTemplate.opsForValue().get("sex");
    }

    @RequestMapping(value = {"/testEmail"})
    public void testEmail() {

        System.out.println(diskInfo());
    }

    @RequestMapping(value = {"/testJVMInfo"})
    public void testJVMInfo() {
        jvmInfo();
    }

    @RequestMapping(value = {"/testBootstrap"})
    public String testBootstrap(Map<String, Object> paramMap) {//explain 默认Map的内容会放大请求域中，页面可以直接取值*/
        paramMap.put("projectName",projectName);
        paramMap.put("projectName","正常");
        return "index";
    }

    @RequestMapping(value = {"/testHtmlRefresh"})
    public String testHtmlRefresh(Model model) {
        Map<String, String> cpuResult = cpuUsage();
        Map<String, String> memResult = memoryUsage();
        Map<String, String> networkResult = networkUsage();

        model.addAttribute("cpuResult", cpuResult.get("result"));
        model.addAttribute("memResult", memResult.get("result"));
        model.addAttribute("networkResult", networkResult.get("result"));
        // model.addAttribute("networkResult", new Date());
        List<String> jvmInfoList = jvmInfo();
        model.addAttribute("jvmInfoList", jvmInfoList);
        return "index::div_systemInfo";
    }
}