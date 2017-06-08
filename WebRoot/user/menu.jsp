<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入JSTL核心标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="sidebar-menu">
	<li class="treeview">
		<a href="user/user.jsp"><i class="fa fa-home"></i><span>首页</span></a>
	</li>
	<c:if test="${sessionScope.login.role == '0'}">
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-user"></i><span>账号信息</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="user/teacher_info.jsp"><i class="fa fa-circle-o"></i>个人资料</a></li>
		  <li><a href="user/teacher_alter.jsp"><i class="fa fa-circle-o"></i>修改资料</a></li>
		  <li><a href="user/password.jsp"><i class="fa fa-circle-o"></i>修改密码</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-user-plus"></i><span>用户管理</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="content/admin/admin.jsp"><i class="fa fa-circle-o"></i>普通管理员</a></li>
		  <li><a href="content/teacher/teacher.jsp"><i class="fa fa-circle-o"></i>项目发布者</a></li>
		  <li><a href="content/student/student.jsp"><i class="fa fa-circle-o"></i>项目开发者</a></li>
		  <li><a href="content/admin/applyUser/applyUser.jsp"><i class="fa fa-circle-o"></i>申请人员管理</a></li>
		</ul>
	</li>
	<li><a href="content/project/project.jsp"><i class="fa fa-th"></i> <span>项目管理</span></a></li>
	<li><a href="content/tag/tag.jsp"><i class="fa fa-tags"></i> <span>标签管理</span></a></li>

	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-folder"></i><span>科技创新</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>竞赛获奖<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/competition/apply.jsp"><i class="fa fa-circle-o"></i>竞赛获奖申请</a></li>
			  <li><a href="content/admin/innovation/competition/competition.jsp"><i class="fa fa-circle-o"></i>竞赛获奖记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>期刊论文<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/paper/apply.jsp"><i class="fa fa-circle-o"></i>期刊论文申请</a></li>
			  <li><a href="content/admin/innovation/paper/paper.jsp"><i class="fa fa-circle-o"></i>期刊论文记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>软件著作<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/sc/apply.jsp"><i class="fa fa-circle-o"></i>软件著作申请</a></li>
			  <li><a href="content/admin/innovation/sc/sc.jsp"><i class="fa fa-circle-o"></i>软件著作记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>发明专利<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/patent/apply.jsp"><i class="fa fa-circle-o"></i>发明专利申请</a></li>
			  <li><a href="content/admin/innovation/patent/patent.jsp"><i class="fa fa-circle-o"></i>发明专利记录</a></li>
			</ul>
		  </li>
		  <li><a href="content/admin/innovation/setuppro/setuppro.jsp"><i class="fa fa-circle-o"></i>项目立项结题</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-cog"></i><span>系统活动</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="content/admin/mentor/mentor.jsp"><i class="fa fa-circle-o"></i>优才导师计划</a></li>
		  <li><a href="content/notice/notice.jsp"><i class="fa fa-circle-o"></i>系统公告</a></li>
		  <li><a href="content/admin/system/activity.jsp"><i class="fa fa-circle-o"></i>活动管理</a></li>
		</ul>
	</li>
	</c:if>
	<!-- 普通管理员 -->
	<c:if test="${sessionScope.login.role == '1'}">
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-user"></i><span>账号信息</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="user/teacher_info.jsp"><i class="fa fa-circle-o"></i>个人资料</a></li>
		  <li><a href="user/teacher_alter.jsp"><i class="fa fa-circle-o"></i>修改资料</a></li>
		  <li><a href="user/password.jsp"><i class="fa fa-circle-o"></i>修改密码</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-user-plus"></i><span>用户管理</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="content/teacher/teacher.jsp"><i class="fa fa-circle-o"></i>项目发布者</a></li>
		  <li><a href="content/student/student.jsp"><i class="fa fa-circle-o"></i>项目开发者</a></li>
		  <li><a href="content/admin/applyUser/applyUser.jsp"><i class="fa fa-circle-o"></i>申请人员管理</a></li>
		</ul>
	</li>
	<li><a href="content/project/project.jsp"><i class="fa fa-th"></i> <span>项目管理</span></a></li>
	<li><a href="content/tag/tag.jsp"><i class="fa fa-tags"></i> <span>标签管理</span></a></li>

	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-folder"></i><span>科技创新</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>竞赛获奖<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/competition/apply.jsp"><i class="fa fa-circle-o"></i>竞赛获奖申请</a></li>
			  <li><a href="content/admin/innovation/competition/competition.jsp"><i class="fa fa-circle-o"></i>竞赛获奖记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>期刊论文<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/paper/apply.jsp"><i class="fa fa-circle-o"></i>期刊论文申请</a></li>
			  <li><a href="content/admin/innovation/paper/paper.jsp"><i class="fa fa-circle-o"></i>期刊论文记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>软件著作<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/sc/apply.jsp"><i class="fa fa-circle-o"></i>软件著作申请</a></li>
			  <li><a href="content/admin/innovation/sc/sc.jsp"><i class="fa fa-circle-o"></i>软件著作记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>发明专利<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/admin/innovation/patent/apply.jsp"><i class="fa fa-circle-o"></i>发明专利申请</a></li>
			  <li><a href="content/admin/innovation/patent/patent.jsp"><i class="fa fa-circle-o"></i>发明专利记录</a></li>
			</ul>
		  </li>
		  <li><a href="content/admin/innovation/setuppro/setuppro.jsp"><i class="fa fa-circle-o"></i>项目立项结题</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-cog"></i><span>系统活动</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="content/admin/mentor/mentor.jsp"><i class="fa fa-circle-o"></i>优才导师计划</a></li>
		  <li><a href="content/notice/notice.jsp"><i class="fa fa-circle-o"></i>系统公告</a></li>
		  <li><a href="content/admin/system/activity.jsp"><i class="fa fa-circle-o"></i>活动管理</a></li>
		</ul>
	</li>
	</c:if>
	<!-- 项目发布者 -->
	<c:if test="${sessionScope.login.role == '2'}">
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-user"></i><span>账号信息</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="user/teacher_info.jsp"><i class="fa fa-circle-o"></i>个人资料</a></li>
		  <li><a href="user/teacher_alter.jsp"><i class="fa fa-circle-o"></i>修改资料</a></li>
		  <li><a href="user/password.jsp"><i class="fa fa-circle-o"></i>修改密码</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-th"></i><span>项目信息</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="content/teacher/project/project_new.jsp"><i class="fa fa-circle-o"></i>发布项目</a></li>
		  <li><a href="content/teacher/project/project_my.jsp"><i class="fa fa-circle-o"></i>我的项目</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-cog"></i><span>系统活动</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		   <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>优才导师<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/teacher/mentor/mentor_new.jsp"><i class="fa fa-circle-o"></i>发布信息</a></li>
			  <li><a href="content/teacher/mentor/mentor.jsp"><i class="fa fa-circle-o"></i>发布记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>系统公告</a></li>
		</ul>
	</li>
	</c:if>
	<!-- 项目开发者 -->
	<c:if test="${sessionScope.login.role == '3'}">
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-user"></i><span>账号信息</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="user/student_info.jsp"><i class="fa fa-circle-o"></i>个人资料</a></li>
		  <li><a href="user/student_alter.jsp"><i class="fa fa-circle-o"></i>修改资料</a></li>
		  <li><a href="user/password.jsp"><i class="fa fa-circle-o"></i>修改密码</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-th"></i><span>项目信息</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="content/student/project/project_recommend.jsp"><i class="fa fa-circle-o"></i>推荐项目</a></li>
		  <li><a href="content/student/project/project_my.jsp"><i class="fa fa-circle-o"></i>我的项目</a></li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-folder"></i><span>科技创新</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>竞赛获奖<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/innovation/competition/apply.jsp"><i class="fa fa-circle-o"></i>竞赛获奖申请</a></li>
			  <li><a href="content/innovation/competition/history.jsp"><i class="fa fa-circle-o"></i>竞赛获奖记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>期刊论文<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/innovation/paper/apply.jsp"><i class="fa fa-circle-o"></i>期刊论文申请</a></li>
			  <li><a href="content/innovation/paper/history.jsp"><i class="fa fa-circle-o"></i>期刊论文记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>软件著作<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/innovation/sc/apply.jsp"><i class="fa fa-circle-o"></i>软件著作申请</a></li>
			  <li><a href="content/innovation/sc/history.jsp"><i class="fa fa-circle-o"></i>软件著作记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>发明专利<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/innovation/patent/apply.jsp"><i class="fa fa-circle-o"></i>发明专利申请</a></li>
			  <li><a href="content/innovation/patent/history.jsp"><i class="fa fa-circle-o"></i>发明专利记录</a></li>
			</ul>
		  </li>
		</ul>
	</li>
	<li class="treeview">
		<a href="javascript:void(0);">
		  <i class="fa fa-cog"></i><span>系统活动</span><i class="fa fa-angle-right pull-right"></i>
		</a>
		<ul class="treeview-menu">
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>优才导师<i class="fa fa-angle-right pull-right"></i></a>
		  	<ul class="treeview-menu">
			  <li><a href="content/student/mentor/mentor_select.jsp"><i class="fa fa-circle-o"></i>活动申请</a></li>
			  <li><a href="content/student/mentor/mentor.jsp"><i class="fa fa-circle-o"></i>查看记录</a></li>
			</ul>
		  </li>
		  <li><a href="javascript:void(0);"><i class="fa fa-circle-o"></i>系统公告</a></li>
		</ul>
	</li>
	</c:if>
</ul>
