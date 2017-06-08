package edu.zjut.tempest.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import edu.zjut.tempest.entity.Notice;
import edu.zjut.tempest.entity.Teacher;
import edu.zjut.tempest.service.NoticeService;

public class NoticeAction {
	
	private NoticeService noticeService;

	private Integer id;
	private String title;
	private String content;
	
	private static int currPage = 1;
	private static int rowsPage = 10;
	
	public String notice_getList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int totalRows = noticeService.getNoticeCount();   //获得项目发布者的人数
		int totalPage=totalRows/rowsPage;//总页数
		totalPage = (totalRows%rowsPage)>0?totalPage+1:totalPage;
		
		if(totalRows != 0) {
			if (request.getParameter("currPage")!=null) {
				try { 
					currPage=Integer.parseInt(request.getParameter("currPage"));//当前显示第几页
					if(currPage < 1) {
						currPage = 1;
					} else if(currPage > totalPage) {
						currPage = totalPage;
					}
				} catch(Exception e) { 
					currPage=1;//当前显示第几页
				}
			} else {
				currPage=1;//当前显示第几页
			}		
			int begin = (currPage - 1) * rowsPage;
			List<Notice> list = noticeService.getNoticeList(begin, rowsPage);
			
			request.setAttribute("currPage", currPage);
			request.setAttribute("rowsPage", rowsPage);
			request.setAttribute("totalRows", totalRows);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("list", list);
			return "getList_success";
		}
		
		return "getList_success";
	}

	public void notice_publish() {
		try {
			Notice notice = new Notice();
			
			HttpSession session =  ServletActionContext.getRequest().getSession();
			Teacher user = (Teacher)session.getAttribute("user");
			notice.setTitle(title);
			notice.setContent(content);
			notice.setState("U");
			notice.setCreatetime(new Date());
			notice.setCreate_user(user.getName());
			
			noticeService.saveNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String notice_getById() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Notice notice = noticeService.getNoticeById(id);
		
		request.setAttribute("notice", notice);
		return "getById_success";
	}
	
	public String notice_getById2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Notice notice = noticeService.getNoticeById(id);
		
		request.setAttribute("notice", notice);
		return "getById2_success";
	}
	
	public void notice_delete() {
		try {
			Notice notice = noticeService.getNoticeById(id);
			
			notice.setState("D");
			noticeService.updateNotice(notice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String notice_getRecentList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Notice> list = noticeService.getNoticeList(0, 20);
		
		request.setAttribute("list", list);
		return "getRecentList_success";
	}
	
	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
