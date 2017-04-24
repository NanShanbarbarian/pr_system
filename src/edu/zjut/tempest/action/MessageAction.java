package edu.zjut.tempest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.Message;
import edu.zjut.tempest.service.MessageService;

public class MessageAction {

	private MessageService messageService;
	private Integer id;
	private Integer projectId;
	private Integer senderId;
	private Integer receiverId;
	private Integer status;
	private Integer frequency;
	private String content;
	
	
	public void message_new() {
		if(senderId != null && receiverId != null && projectId != null) {
			Message message = new Message();
			
			message.setProjectId(projectId);
			message.setReceiverId(receiverId);
			message.setSenderId(senderId);
			message.setStatus(1);   //设置为未读
			message.setContent(content);
			message.setCreatetime(new Date());
			
			messageService.saveMessage(message);
		}
	}
	
	public String message_getListOnStudent() {
		
		if(projectId != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Message> list = null;
			if(senderId != null) {
				list = messageService.getMessageBySenderId(projectId, senderId);
			} else if(receiverId != null) {
				list = messageService.getMessageByReceiverId(projectId, receiverId);
			}
			request.setAttribute("list", list);
		}
		
		if(senderId != null) {
			return "getListOnStudentAndSender_success";
		} else {
			return "getListOnStudentAndReceiver_success";
		}
	}
	
	public String message_getListOnTeacher() {
		if(projectId != null && senderId != null && receiverId != null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			List<Message> list = null;
			list = messageService.getMessageBySenderIdAndReceiverId(projectId, senderId, receiverId);
			request.setAttribute("list", list);
		}
		
		if(frequency != null && frequency == 0) {   //收件箱
			return "getListOnTeacherAndReceiver_success";
		} else { //已发送
			return "getListOnTeacherAndSender_success";
		}
	}
	
	public void message_getById() throws IOException {
		HttpServletResponse  response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(id != null) {
			Message message = messageService.getMessageById(id);
			if(message != null) {
				message.setStatus(0);   //设置为已读
				messageService.updateMessage(message);
				
				out.print(message.getContent());
				out.flush();
				out.close();
			}
		}
	}
	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
}
