package com.example.demo.api;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class util {
    public static boolean send(String email,String code){
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.qq.com");// smtp服务器地址
            Session session = Session.getInstance(props);
            Message msg = new MimeMessage(session);
            msg.setSubject("这是你的验证码");
            msg.setText("你好!这是你的注册验证码:"+code);//发的内容
            msg.setFrom(new InternetAddress("2878813957@qq.com"));//发件人邮箱(我的163邮箱)
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(email)); //收件人邮箱(我的QQ邮箱)
            msg.saveChanges();
            Transport transport = session.getTransport();
            transport.connect("2878813957@qq.com", "uhmxbputfaafdcfd");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("邮件发送成功...");
            transport.close();
            return true;
        }
        catch (Exception e){
            System.out.println("邮件发送失败...");
            return  false;
        }
    }
}