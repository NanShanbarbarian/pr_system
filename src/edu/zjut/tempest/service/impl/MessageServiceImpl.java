package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.MessageDAO;
import edu.zjut.tempest.entity.Message;
import edu.zjut.tempest.service.MessageService;

public class MessageServiceImpl implements MessageService{

	private MessageDAO messageDAO;

	@Override
	public void saveMessage(Message message) {
		messageDAO.saveMessage(message);
	}

	@Override
	public void updateMessage(Message message) {
		messageDAO.updateMessage(message);
	}

	@Override
	public Message getMessageById(int id) {
		return messageDAO.getMessageById(id);
	}
	
	@Override
	public List<Message> getMessageBySenderIdAndReceiverId(int projectId, int senderId, int receiverId) {
		return messageDAO.getMessageBySenderIdAndReceiverId(projectId, senderId, receiverId);
	}

	@Override
	public List<Message> getMessageBySenderId(int projectId, int senderId) {
		return messageDAO.getMessageBySenderId(projectId, senderId);
	}

	@Override
	public List<Message> getMessageByReceiverId(int projectId, int receiverId) {
		return messageDAO.getMessageByReceiverId(projectId, receiverId);
	}
	
	public MessageDAO getMessageDAO() {
		return messageDAO;
	}

	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

}
