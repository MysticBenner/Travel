package com.cb.travel.controller;

import com.cb.travel.entity.Result;
import com.cb.travel.entity.User;
import com.cb.travel.service.UserService;
import com.cb.travel.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(String code, String key, @RequestBody User user, HttpServletRequest request){
        log.info("接收的验证码：" + code);
        log.info("接收的user对象：" + user);
        Result result = new Result();
        String keyCode = (String) request.getServletContext().getAttribute(key); // 获取验证码

        try {
            if (code.equalsIgnoreCase(keyCode)) {
                User userDB = userService.login(user);
                request.getServletContext().setAttribute(userDB.getId(), userDB);
                result.setMsg("登录成功!!!").setUserId(userDB.getId());
            } else {
                throw new RuntimeException("验证码错误!");
            }
        } catch (Exception e){
            result.setState(false).setMsg(e.getMessage());
        }

        return result;
    }

    @PostMapping("/register")
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request) { // axios发送的是JSON数据, 需要加@RequestBody来接收
        log.info("接收的验证码：" + code);
        log.info("接收的user对象：" + user);
        Result result = new Result();
        String keyCode = (String) request.getServletContext().getAttribute(key); // 获取验证码
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                // 验证码正确, 注册用户
                userService.register(user);
                result.setMsg("注册成功!!!");
            } else {
                throw new RuntimeException("验证码错误!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);
        }
        return result;
    }


    @GetMapping("/getImage")
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        String securityCode = createImageCode.getCode();
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        BufferedImage image = createImageCode.getBuffImg();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        String string = Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }
}
