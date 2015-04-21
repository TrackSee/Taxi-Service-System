package com.netcracker.bootcamp.tracksee.mailsender.util.com.netcracker.bootcamp.tracksee.mailsender;

import com.netcracker.bootcamp.tracksee.entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created with IntelliJ IDEA.
 * User: Igor
 * Date: 21.04.15
 * Time: 1:33
 * To change this template use File | Settings | File Templates.
 */

@Local
@Stateless(name = "MailSenderEJB")
public class MailSenderBean {

    public MailSenderBean() {
    }


    /**
     * This method sends mail notification to user to inform they about blocking
     * @param user - user to send email to
     */
    public void informUserAboutBlocking(User user){

    }
}
