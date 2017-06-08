package edu.zjut.tempest.dao;

import java.util.List;

import edu.zjut.tempest.entity.Notice;

public interface NoticeDAO {
	
	public void saveNotice(Notice notice);
	
	public void updateNotice(Notice notice);
	
	public int getNoticeCount();
	
	public List<Notice> getNoticeList(int begin, int rowsPage);
	
	public Notice getNoticeById(int id);
	
}
