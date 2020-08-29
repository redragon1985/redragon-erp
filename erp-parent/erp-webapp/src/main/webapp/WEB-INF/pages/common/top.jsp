<%--

    Copyright 2020-2021 redragon.dongbin
 
    This file is part of redragon-erp/赤龙ERP.

    redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License, or
    (at your option) any later version.

    redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
	
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="row border-bottom">
	<nav class="navbar navbar-static-top white-bg" role="navigation"
		style="margin-bottom: 0">

		<div class="navbar-header">
			<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
				href="#"><i class="fa fa-bars"></i> </a>
			<%-- 
            <form role="search" class="navbar-form-custom" action="search_results.html">
                <div class="form-group">
                    <input type="text" placeholder="功能搜索" class="form-control" name="top-search" id="top-search">
                </div>
            </form>
            --%>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li><span class="m-r-sm text-muted welcome-message">欢迎使用ERP系统</span>
			</li>
			
			<%--bcc 暂时取消 站内信功能 
			<li class="dropdown">
				<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> 
					<i class="fa fa-envelope"></i><span class="label label-warning">16</span>
				</a>
				<ul class="dropdown-menu dropdown-messages">
					<li>
						<div class="dropdown-messages-box">
							<a class="dropdown-item float-left" href="profile.html"> <img
								alt="image" class="rounded-circle" src="img/a7.jpg">
							</a>
							<div>
								<small class="float-right">46h ago</small> <strong>Mike
									Loreipsum</strong> started following <strong>Monica Smith</strong>. <br>
								<small class="text-muted">3 days ago at 7:58 pm -
									10.06.2014</small>
							</div>
						</div>
					</li>
					<li class="dropdown-divider"></li>
					<li>
						<div class="dropdown-messages-box">
							<a class="dropdown-item float-left" href="profile.html"> <img
								alt="image" class="rounded-circle" src="img/a4.jpg">
							</a>
							<div>
								<small class="float-right text-navy">5h ago</small> <strong>Chris
									Johnatan Overtunk</strong> started following <strong>Monica
									Smith</strong>. <br> <small class="text-muted">Yesterday
									1:21 pm - 11.06.2014</small>
							</div>
						</div>
					</li>
					<li class="dropdown-divider"></li>
					<li>
						<div class="dropdown-messages-box">
							<a class="dropdown-item float-left" href="profile.html"> <img
								alt="image" class="rounded-circle" src="img/profile.jpg">
							</a>
							<div>
								<small class="float-right">23h ago</small> <strong>Monica
									Smith</strong> love <strong>Kim Smith</strong>. <br> <small
									class="text-muted">2 days ago at 2:30 am - 11.06.2014</small>
							</div>
						</div>
					</li>
					<li class="dropdown-divider"></li>
					<li>
						<div class="text-center link-block">
							<a href="mailbox.html" class="dropdown-item"> <i
								class="fa fa-envelope"></i> <strong>Read All Messages</strong>
							</a>
						</div>
					</li>
				</ul>
			</li>
			--%>
			
			<%--bcc 暂时取消 通知功能 
			<li class="dropdown">
				<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> 
					<i class="fa fa-bell"></i> <span class="label label-primary">8</span>
				</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="mailbox.html" class="dropdown-item">
							<div>
								<i class="fa fa-envelope fa-fw"></i> You have 16 messages <span
									class="float-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="dropdown-divider"></li>
					<li><a href="profile.html" class="dropdown-item">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="float-right text-muted small">12 minutes ago</span>
							</div>
					</a></li>
					<li class="dropdown-divider"></li>
					<li><a href="grid_options.html" class="dropdown-item">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="float-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="dropdown-divider"></li>
					<li>
						<div class="text-center link-block">
							<a href="notifications.html" class="dropdown-item"> <strong>See
									All Alerts</strong> <i class="fa fa-angle-right"></i>
							</a>
						</div>
					</li>
				</ul>
			</li>
			--%>

			<li><a href="logout" class="text-muted"><i class="fa fa-sign-out"></i>退出</a></li>
			
			<%-- bbc 暂时取消 右侧弹出层 
			<li><a class="right-sidebar-toggle"> <i class="fa fa-tasks"></i></a></li>
			--%>
		</ul>

	</nav>
</div>
