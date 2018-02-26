package com.atguigu.mall.log.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.mall.log.service.LogService;

public class MyMessageListener implements MessageListener{
	
	@Autowired
	LogService logService;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			logService.saveLoginout(text);
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
	}

	

}
