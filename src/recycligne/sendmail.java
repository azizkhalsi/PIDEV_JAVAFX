/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recycligne;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 

/**
 *
 * @author Fabio
 */
public class sendmail {

    

    public static void sendmail(String recepient) throws Exception {

        String login = "nour.mehdi@esprit.tn";
        String pass = "07492696";
        String host = "smtp.gmail.com";
        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
       // prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", login);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");

        prop.put("mail.smtp.ssl.trust", "smtp.aol.com");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, pass);
            }
        });session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        Transport transport = session.getTransport("smtp");
//        transport.connect();
        //  transport.sendMessage(msg, msg.getAllRecipients());
        Message message = prepareMessage(session, login, recepient);
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String login, String recepient) {

        
        
        try {

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(login));

            InternetAddress toAdresse = new InternetAddress(recepient);

            msg.setRecipient(Message.RecipientType.TO, toAdresse);
            msg.setSubject("Urgent ");
          //  msg.setText("merci ");
          String htmlCode = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
"<head>\n" +
"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
"<title>Demystifying Email Design</title>\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
"</head>\n" +
"<body style=\"margin: 0; padding: 0;\">\n" +
"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> \n" +
"        <tr>\n" +
"            <td style=\"padding: 10px 0 30px 0;\">\n" +
"                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border: 1px solid #cccccc; border-collapse: collapse;\">\n" +
"                    <tr>\n" +
"                        <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">\n" +
"                            <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/h1.gif\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\" />\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td bgcolor=\"#ffffff\" style=\"padding: 40px 30px 40px 30px;\">\n" +
"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                <tr>\n" +
"                                    <td style=\"color: #153643; font-family: Arial, sans-serif; font-size: 24px;\">\n" +
"                                        <b>Lorem ipsum dolor sit amet!</b>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"padding: 20px 0 30px 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
"                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td>\n" +
"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                            <tr>\n" +
"                                                <td width=\"260\" valign=\"top\">\n" +
"                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                        <tr>\n" +
"                                                            <td>\n" +
"                                                                <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/left.gif\" alt=\"\" width=\"100%\" height=\"140\" style=\"display: block;\" />\n" +
"                                                            </td>\n" +
"                                                        </tr>\n" +
"                                                        <tr>\n" +
"                                                            <td style=\"padding: 25px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
"                                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
"                                                            </td>\n" +
"                                                        </tr>\n" +
"                                                    </table>\n" +
"                                                </td>\n" +
"                                                <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
"                                                    &nbsp;\n" +
"                                                </td>\n" +
"                                                <td width=\"260\" valign=\"top\">\n" +
"                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                        <tr>\n" +
"                                                            <td>\n" +
"                                                                <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/right.gif\" alt=\"\" width=\"100%\" height=\"140\" style=\"display: block;\" />\n" +
"                                                            </td>\n" +
"                                                        </tr>\n" +
"                                                        <tr>\n" +
"                                                            <td style=\"padding: 25px 0 0 0; color: #153643; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">\n" +
"                                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. In tempus adipiscing felis, sit amet blandit ipsum volutpat sed. Morbi porttitor, eget accumsan dictum, nisi libero ultricies ipsum, in posuere mauris neque at erat.\n" +
"                                                            </td>\n" +
"                                                        </tr>\n" +
"                                                    </table>\n" +
"                                                </td>\n" +
"                                            </tr>\n" +
"                                        </table>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\n" +
"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                <tr>\n" +
"                                    <td style=\"color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;\" width=\"75%\">\n" +
"                                        &reg; Someone, somewhere 2013<br/>\n" +
"                                        <a href=\"#\" style=\"color: #ffffff;\"><font color=\"#ffffff\">Unsubscribe</font></a> to this newsletter instantly\n" +
"                                    </td>\n" +
"                                    <td align=\"right\" width=\"25%\">\n" +
"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                                            <tr>\n" +
"                                                <td style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;\">\n" +
"                                                    <a href=\"http://www.twitter.com/\" style=\"color: #ffffff;\">\n" +
"                                                        <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/tw.gif\" alt=\"Twitter\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n" +
"                                                    </a>\n" +
"                                                </td>\n" +
"                                                <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\n" +
"                                                <td style=\"font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;\">\n" +
"                                                    <a href=\"http://www.twitter.com/\" style=\"color: #ffffff;\">\n" +
"                                                        <img src=\"https://s3-us-west-2.amazonaws.com/s.cdpn.io/210284/fb.gif\" alt=\"Facebook\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\n" +
"                                                    </a>\n" +
"                                                </td>\n" +
"                                            </tr>\n" +
"                                        </table>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </td>\n" +
"        </tr>\n" +
"    </table>\n" +
"</body>\n" +
"</html>\n" +
"\n" +
"";
             msg.setContent(htmlCode, "text/html");

            return msg;
        } catch (Exception ex) {
        }
        return null;
    }

}
    

