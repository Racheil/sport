package com.rachel.sustsport.controller;

import com.rachel.sustsport.common.HttpClientUtil;
import com.rachel.sustsport.common.JSONResult;
import com.rachel.sustsport.common.JsonUtils;
import com.rachel.sustsport.common.RedisOperator;
import com.rachel.sustsport.model.WXSessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private RedisOperator redis;

    @PostMapping("/wxLogin")
    public JSONResult wxLogin(String code) {

        System.out.println("wxlogin - code: " + code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> param = new HashMap<>();
        param.put("appid", "wx1ece71c0fe4dfec1");
        param.put("secret", "b617546adf3d80da44334deb80b88859");
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String wxResult = HttpClientUtil.doGet(url, param);
        System.out.println(wxResult);

        WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);

        // 存入session到redis
        redis.set("user-redis-session:" + model.getOpenid(),
                model.getSession_key(),
                1000 * 60 * 30);

        return JSONResult.ok();
    }

}
