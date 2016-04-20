package org.ezdevgroup.common.service.acl;

import javax.annotation.Resource;

import org.ezdevgroup.ezframework.web.GlobalProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * 메일 발송을 담당한다.
 * @author ddakker
 *
 */
@Service
public class MailService {
	private static Logger log = LoggerFactory.getLogger(MailService.class);

	@Resource
	GlobalProperties globalProperties;

	@Resource
	private JavaMailSender javaMailSender;


	/**
	 * 메일을 발송한다.
	 * @param to		받는 사람
	 * @param subject	제목
	 * @param content	내용
	 * @auther ddakker 2014. 7. 23.
	 */
	public boolean send(String to, String subject, String content) {
		return send(to, globalProperties.getProperty("mail.admin"), subject, content);
	}

	/**
	 * 메일을 발송한다.
	 * @param to		받는 사람
	 * @param from		보내는 사람
	 * @param subject	제목
	 * @param content	내용
	 * @return
	 * @auther ddakker 2014. 7. 23.
	 */
	public boolean send(String to, String from, String subject, String content) {
		return send(to, from, subject, content, true);
	}

	/**
	 * 메일을 발송한다.
	 * @param to		받는 사람
	 * @param from		보내는 사람
	 * @param subject	제목
	 * @param content	내용
	 * @param isHtml	HTML 파싱 여부
	 * @auther ddakker 2014. 7. 23.
	 */
	public boolean send(String to, String from, String subject, String content, boolean isHtml) {
		/*MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try{
			helper = new MimeMessageHelper(msg, false, "utf-8");
			helper.setTo(to);
			if( from.indexOf("<") > -1 && from.indexOf(">") > -1 ){
				helper.setFrom(from.substring(from.indexOf("<") + 1, from.indexOf(">")), from.substring(0, from.indexOf("<")));
			}else{
				helper.setFrom(from);
			}
			helper.setSubject(subject);
			helper.setText(content, isHtml);

			javaMailSender.send(msg);

			return true;
		}catch(Exception e){
			log.error("Exception e: " + e);
			log.error("Mail Send Info to: " + to);
			log.error("Mail Send Info from: " + from);
			log.error("Mail Send Info subject: " + subject);
			log.error("Mail Send Info content: " + content);
			log.error("Mail Send Info bHtml: " + isHtml);
			return false;
		}*/
		return false;
	}
}
