<%--

	Copyright 2020-2021 redragon.dongbin

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
	
--%>
<%@page import="com.framework.util.ShiroUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String contentPage = request.getParameter("contentPage");
contentPage=contentPage.substring(contentPage.lastIndexOf("/")+1, contentPage.length()-4);
%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<img alt="image" class="rounded-circle" src="img/profile_small.jpg" />
					<a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)"> <span
						class="block m-t-xs font-bold">${requestScope.staffInfo.staffName}</span> 
						<span class="text-muted text-xs block">${requestScope.staffInfo.departmentName}&nbsp;&nbsp;${requestScope.staffInfo.positionName}
						<%-- bbc 暂时取消账户快捷功能 
						<b class="caret"></b>
						--%>
						</span>
					</a>
					<%-- bbc 暂时取消账户快捷功能 
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a class="dropdown-item" href="profile.html">职员信息</a></li>
						<li><a class="dropdown-item" href="contacts.html">账户信息</a></li>
						<li class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="login.html">退出</a></li>
					</ul>
					--%>
				</div>
				<div class="logo-element">张三</div>
			</li>
			
			<%if(contentPage.equalsIgnoreCase("main")){ %>
				<li class="active"><a href="index"><i class="fa fa-home"></i> <span class="nav-label">首页</span></a></li>
			<%}else{ %>
				<li><a href="index"><i class="fa fa-home"></i> <span class="nav-label">首页</span></a></li>
			<%} %>
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("system_menu_auth")){
			%>
			<%if(contentPage.contains("sys")||contentPage.contains("roleAuthRelate")||contentPage.contains("userRoleRelate")){ %>
				<li class="active"><a href="javascript:void(0)"><i class="fa fa-expeditedssl"></i> <span class="nav-label">用户与权限</span> <span class="fa arrow"></span></a>
			<%}else{ %>
				<li><a href="javascript:void(0)"><i class="fa fa-expeditedssl"></i> <span class="nav-label">用户与权限</span> <span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level">
					<%if(contentPage.equalsIgnoreCase("sysUserList")||contentPage.equalsIgnoreCase("sysUserEdit")){ %>
						<li class="active"><a href="web/sysUser/getSysUserList">用户管理</a></li>
					<%}else{ %>
						<li><a href="web/sysUser/getSysUserList">用户管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("userRoleRelate")){ %>
						<li class="active"><a href="web/sysUser/beforeRelateSysUserRole">用户分配角色</a></li>
					<%}else{ %>
						<li><a href="web/sysUser/beforeRelateSysUserRole">用户分配角色</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("sysRoleList")||contentPage.equalsIgnoreCase("sysRoleEdit")){ %>
						<li class="active"><a href="web/sysRole/getSysRoleList">角色管理</a></li>
					<%}else{ %>
						<li><a href="web/sysRole/getSysRoleList">角色管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("sysAuthList")||contentPage.equalsIgnoreCase("sysAuthEdit")){ %>
						<li class="active"><a href="web/sysAuth/getSysAuthList">权限管理</a></li>
					<%}else{ %>
						<li><a href="web/sysAuth/getSysAuthList">权限管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("roleAuthRelate")){ %>
						<li class="active"><a href="web/sysRole/beforeRelateSysRoleAuth">角色关联权限</a></li>
					<%}else{ %>
						<li><a href="web/sysRole/beforeRelateSysRoleAuth">角色关联权限</a></li>
					<%} %>
					
				</ul>
			</li>
			<%} %>
			
			
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("hr_menu_auth")){
			%>
			<%if(contentPage.contains("hr")||contentPage.contains("staffDepartmentRelate")){ %>
				<li class="active"><a href="javascript:void(0)"><i class="fa fa-user-circle"></i> <span class="nav-label">职员与组织</span> <span class="fa arrow"></span></a>
			<%}else{ %>
				<li><a href="javascript:void(0)"><i class="fa fa-user-circle"></i> <span class="nav-label">职员与组织</span> <span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level">
					<%if(contentPage.equalsIgnoreCase("hrStaffList")||contentPage.equalsIgnoreCase("hrStaffEdit")){ %>
						<li class="active"><a href="web/hrStaff/getHrStaffList">职员管理</a></li>
					<%}else{ %>
						<li><a href="web/hrStaff/getHrStaffList">职员管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("hrPositionList")||contentPage.equalsIgnoreCase("hrPositionEdit")){ %>
						<li class="active"><a href="web/hrPosition/getHrPositionList">岗位管理</a></li>
					<%}else{ %>
						<li><a href="web/hrPosition/getHrPositionList">岗位管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("hrDepartmentList")){ %>
						<li class="active"><a href="web/hrDepartment/getHrDepartmentList">部门管理</a></li>
					<%}else{ %>
						<li><a href="web/hrDepartment/getHrDepartmentList">部门管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("staffDepartmentRelate")){ %>
						<li class="active"><a href="web/hrStaffDepartmentR/beforeRelateStaffDepartment">职员关联部门</a></li>
					<%}else{ %>
						<li><a href="web/hrStaffDepartmentR/beforeRelateStaffDepartment">职员关联部门</a></li>
					<%} %>
					
				</ul>
			</li>
			<%} %>
			
			
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("md_menu_auth")){
			%>
			<%if(contentPage.contains("md")||contentPage.contains("subject")){ %>
				<li class="active"><a href="javascript:void(0)"><i class="fa fa-database"></i> <span class="nav-label">主数据</span> <span class="fa arrow"></span></a>
			<%}else{ %>
				<li><a href="javascript:void(0)"><i class="fa fa-database"></i> <span class="nav-label">主数据</span> <span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level">
					<%if(contentPage.equalsIgnoreCase("mdCustomerList")||contentPage.equalsIgnoreCase("mdCustomerEdit")){ %>
						<li class="active"><a href="web/mdCustomer/getMdCustomerList">客户管理</a></li>
					<%}else{ %>
						<li><a href="web/mdCustomer/getMdCustomerList">客户管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("mdVendorList")||contentPage.equalsIgnoreCase("mdVendorEdit")){ %>
						<li class="active"><a href="web/mdVendor/getMdVendorList">供应商管理</a></li>
					<%}else{ %>
						<li><a href="web/mdVendor/getMdVendorList">供应商管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("mdMaterialCategoryList")){ %>
						<li class="active"><a href="web/mdMaterialCategory/getMdMaterialCategoryList">物料与事项类型</a></li>
					<%}else{ %>
						<li><a href="web/mdMaterialCategory/getMdMaterialCategoryList">物料与事项类型</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("mdMaterialList")||contentPage.equalsIgnoreCase("mdMaterialEdit")){ %>
						<li class="active"><a href="web/mdMaterial/getMdMaterialList">物料与事项</a></li>
					<%}else{ %>
						<li><a href="web/mdMaterial/getMdMaterialList">物料与事项</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("mdProjectList")||contentPage.equalsIgnoreCase("mdProjectEdit")){ %>
						<li class="active"><a href="web/mdProject/getMdProjectList">项目管理</a></li>
					<%}else{ %>
						<li><a href="web/mdProject/getMdProjectList">项目管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("subjectList")){ %>
						<li class="active"><a href="web/mdFinanceSubject/getMdFinanceSubjectList">科目管理</a></li>
					<%}else{ %>
						<li><a href="web/mdFinanceSubject/getMdFinanceSubjectList">科目管理</a></li>
					<%} %>
					
				</ul>
			</li>
			<%} %>

			
			
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("order_menu_auth")){
			%>
			<%if(contentPage.contains("po")||contentPage.contains("so")){ %>
				<li class="active"><a href="#"><i class="fa fa-list-alt"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li><a href="#"><i class="fa fa-list-alt"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level collapse">
					<%if(contentPage.equalsIgnoreCase("poList")||contentPage.equalsIgnoreCase("poEdit")){ %>
						<li class="active"><a href="web/poHead/getPoHeadList">采购订单</a></li>
					<%}else{ %>
						<li><a href="web/poHead/getPoHeadList">采购订单</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("soList")||contentPage.equalsIgnoreCase("soEdit")){ %>
						<li class="active"><a href="web/soHead/getSoHeadList">销售订单</a></li>
					<%}else{ %>
						<li><a href="web/soHead/getSoHeadList">销售订单</a></li>
					<%} %>
					
					<%-- 
					<li><a href="graph_chartist.html">采购订单变更</a></li>
					<li><a href="c3.html">销售订单变更</a></li>
					--%>
				</ul>
			</li>
			<%} %>
				
			<%-- 
			<li><a href="#"><i class="fa fa-edit"></i> <span class="nav-label">库房管理</span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a href="form_basic.html">入库单</a></li>
					<li><a href="form_advanced.html">出库单</a></li>
					<li><a href="form_wizard.html">库存管理</a></li>
					<li><a href="form_file_upload.html">库存盘点</a></li>
				</ul></li>
				--%>	
				
			
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("pay_receipt_menu_auth")){
			%>	
			<%if(contentPage.contains("pay")||contentPage.contains("receipt")){ %>	
				<li class="active"><a href="#"><i class="fa fa-handshake-o"></i> <span class="nav-label">收付款</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li><a href="#"><i class="fa fa-handshake-o"></i> <span class="nav-label">收付款</span><span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level collapse">
					<%if(contentPage.equalsIgnoreCase("payList")||contentPage.equalsIgnoreCase("payEdit")){ %>
						<li class="active"><a href="web/payHead/getPayHeadList">付款单</a></li>
					<%}else{ %>
						<li><a href="web/payHead/getPayHeadList">付款单</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("receiptList")||contentPage.equalsIgnoreCase("receiptEdit")){ %>
						<li class="active"><a href="web/receiptHead/getReceiptHeadList">收款单</a></li>
					<%}else{ %>
						<li><a href="web/receiptHead/getReceiptHeadList">收款单</a></li>
					<%} %>
					
					<%-- 
					<li><a href="profile.html">付款确认</a></li>
					<li><a href="contacts_2.html">收款确认</a></li>
					--%>
				</ul>
			</li>
			<%} %>
			
			
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("fin_menu_auth")){
			%>
			<%if(contentPage.contains("voucher")){ %>		
				<li class="active"><a href="#"><i class="fa fa-money"></i> <span class="nav-label">财务管理</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li><a href="#"><i class="fa fa-money"></i> <span class="nav-label">财务管理</span><span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level collapse">
					<%if(contentPage.equalsIgnoreCase("voucherList")||contentPage.equalsIgnoreCase("voucherEdit")){ %>
						<li class="active"><a href="web/finVoucherHead/getFinVoucherHeadList">凭证管理</a></li>
					<%}else{ %>
						<li><a href="web/finVoucherHead/getFinVoucherHeadList">凭证管理</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("voucherModelList")||contentPage.equalsIgnoreCase("voucherModelEdit")){ %>
						<li class="active"><a href="web/finVoucherModelHead/getFinVoucherModelHeadList">凭证模板管理</a></li>
					<%}else{ %>
						<li><a href="web/finVoucherModelHead/getFinVoucherModelHeadList">凭证模板管理</a></li>
					<%} %>
				</ul>
			</li>
			<%} %>
			
			
			
			<%
			//菜单权限判断
			if(ShiroUtil.isPermittedAnyPerm("system_menu_auth")){
			%>
			<%if(contentPage.contains("dataset")){ %>
				<li class="active"><a href="javascript:void(0)"><i class="fa fa-cog"></i> <span class="nav-label">系统配置</span><span class="label label-info float-right">管理员</span></a>
			<%}else{ %>
				<li><a href="javascript:void(0)"><i class="fa fa-cog"></i> <span class="nav-label">系统配置</span><span class="label label-info float-right">管理员</span></a>
			<%} %>
				
				<ul class="nav nav-second-level collapse">
					<%if(contentPage.equalsIgnoreCase("datasetTypeEdit")||contentPage.equalsIgnoreCase("datasetTypeList")||
						 contentPage.equalsIgnoreCase("datasetList")){ %>
					<li class="active"><a href="web/sysDatasetType/getSysDatasetTypeList">数据字典设置</a></li>
					<%}else{ %>
					<li><a href="web/sysDatasetType/getSysDatasetTypeList">数据字典设置</a></li>
					<%} %>
					
					<%-- 
					<li><a href="nestable_list.html">审批流程设置</a></li>
					--%>
				</ul>
			</li>
			<%} %>
			
			
		</ul>

	</div>
</nav>
