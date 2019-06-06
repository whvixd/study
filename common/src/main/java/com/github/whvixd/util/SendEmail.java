package com.github.whvixd.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangzhx on 2018/6/11.
 */
@Slf4j
@UtilityClass
public class SendEmail {

    public static void toWho(String toEmail, String nickName, String title, String content) {
        toWho(toEmail, nickName, title, content, () -> FromUser.USER);
    }

    //通过listener可以设置发送邮件服务器
    public static void toWho(String toEmail, String nickName, String title, String content, IChangeListener listener) {
        FromUser fromUser = listener.change();
        if (StringUtils.isBlank(fromUser.getHost())) {
            fromUser.setHost(FromUser.USER.getHost());
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        StringBuilder msgInfo = new StringBuilder();
        msgInfo.append(content).append("\r\n\t");
        msgInfo.append("时间：").append(simpleDateFormat.format(new Date()));

        try {
            SimpleEmailSingleton.INSTANCE
                    .setHostName(fromUser.getHost())
                    .setAuthentication(fromUser.getUserName(), fromUser.getPassword())
                    .setCharset("UTF-8")
                    .addTo(toEmail)
                    .setFrom(fromUser.getUserName(), nickName)
                    .setSubject(title + "-" + simpleDateFormat.format(new Date()))
                    .setMsg(msgInfo.toString())
                    .send();
            log.info("发送人:{},收件人:{},发送邮箱成功", fromUser.getUserName(), toEmail);
        } catch (EmailException e) {
            log.error("发给{}邮件失败", toEmail);
            throw new RuntimeException("发送邮箱失败！");
        }

    }

    enum SimpleEmailSingleton {
        INSTANCE;

        private static final SimpleEmail simpleEmail = new SimpleEmail();

        SimpleEmailSingleton setHostName(String hostName) {
            simpleEmail.setHostName(hostName);
            return this;
        }

        SimpleEmailSingleton setAuthentication(String userName, String password) {
            simpleEmail.setAuthentication(userName, password);
            return this;
        }

        SimpleEmailSingleton setCharset(String newCharset) {
            simpleEmail.setCharset(newCharset);
            return this;
        }

        SimpleEmailSingleton addTo(String email) throws EmailException {
            simpleEmail.addTo(email);
            return this;
        }

        SimpleEmailSingleton setFrom(String email, String name) throws EmailException {
            simpleEmail.setFrom(email, name);
            return this;
        }

        SimpleEmailSingleton setSubject(String aSubject) {
            simpleEmail.setSubject(aSubject);
            return this;
        }

        SimpleEmailSingleton setMsg(String msg) throws EmailException {
            simpleEmail.setMsg(msg);
            return this;
        }

        SimpleEmailSingleton send() throws EmailException {
            simpleEmail.send();
            return this;
        }
    }

    public enum FromUser {
        //主机服务,邮箱账号,授权码
        USER("smtp.163.com", "whvixd@163.com", "1130Wang1130");

        @Setter
        @Getter
        private String host;

        @Setter
        @Getter
        private String userName;

        @Setter
        @Getter
        private String password;


        FromUser(String host, String userName, String password) {
            this.host = host;
            this.userName = userName;
            this.password = password;
        }
    }

    @FunctionalInterface
    public interface IChangeListener {
        FromUser change();
    }

}
