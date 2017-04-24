package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.Message;

public interface MessageDAO {

	public void saveMessage(Message message);
	
	public void updateMessage(Message message);
	
	public List<Message> getMessageBySenderIdAndReceiverId(int projectId, int senderId, int receiverId);
	
	public List<Message> getMessageBySenderId(int projectId, int senderId);
	
	public List<Message> getMessageByReceiverId(int projectId, int receiverId);
	
	public Message getMessageById(int id);
}
