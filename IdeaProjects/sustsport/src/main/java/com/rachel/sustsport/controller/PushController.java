package com.rachel.sustsport.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import me.chanjar.weixin.common.error.WxErrorException;

@RestController
public class PushController {

    @RequestMapping("/push")
    public String push(@RequestParam String openid, @RequestParam String formid) {
        //1,配置小程序信息
        WxMaInMemoryConfig wxConfig = new WxMaInMemoryConfig();
        wxConfig.setAppid("wx1ece71c0fe4dfec1");//小程序appid
        wxConfig.setSecret("b617546adf3d80da44334deb80b88859");//小程序AppSecret

        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(wxConfig);

        //2,设置模版信息（keyword1：类型，keyword2：内容）
        List<WxMaTemplateData> templateDataList = new ArrayList<>(2);
        WxMaTemplateData data1 = new WxMaTemplateData("keyword1", "闫静");
        WxMaTemplateData data2 = new WxMaTemplateData("keyword2", "2019年6月30日");
        WxMaTemplateData data3 = new WxMaTemplateData("keyword3", "风雨操场");
        templateDataList.add(data1);
        templateDataList.add(data2);
        templateDataList.add(data3);
        //3，设置推送消息
        WxMaTemplateMessage templateMessage = WxMaTemplateMessage.builder()
                .toUser(openid)//要推送的用户openid
                .formId(formid)//收集到的formid
                .templateId("anHahk1rcClnqpXm6nppkjZcYDBQAKO_Honqft22b3o")//推送的模版id（在小程序后台设置）
                .data(templateDataList)//模版信息
                .page("pages/index/index")//要跳转到小程序那个页面
                .build();
        //4，发起推送
        try {            wxMaService.getMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            System.out.println("推送失败：" + e.getMessage());
            return e.getMessage();
        }
        return "推送成功";
    }
}

