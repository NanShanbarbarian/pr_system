package edu.zjut.tempest.service.impl;

import java.util.List;

import edu.zjut.tempest.dao.NoticeDAO;
import edu.zjut.tempest.entity.Notice;
import edu.zjut.tempest.service.NoticeService;

public class NoticeServiceImpl implements NoticeService{

	private NoticeDAO noticeDAO;

	public NoticeDAO getNoticeDAO() {
		return noticeDAO;
	}

	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Override
	public void saveNotice(Notice notice) {
		noticeDAO.saveNotice(notice);
	}

	@Override
	public void updateNotice(Notice notice) {
		noticeDAO.updateNotice(notice);
	}

	@Override
	public int getNoticeCount() {
		return noticeDAO.getNoticeCount();
	}

	@Override
	public List<Notice> getNoticeList(int begin, int rowsPage) {
		return noticeDAO.getNoticeList(begin, rowsPage);
	}

	@Override
	public Notice getNoticeById(int id) {
		return noticeDAO.getNoticeById(id);
	}
}
