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
						class="block m-t-xs font-bold">${staffInfo.staffName}</span> 
						<span class="text-muted text-xs block">${staffInfo.departmentName}&nbsp;&nbsp;${staffInfo.positionName}
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
			//获取菜单权限
			ShiroUtil.isPermittedAnyPerm("admin");
			String perms = ShiroUtil.getPerms().toString();
			%>
			<script>
			var perms = "<%=perms%>";
			
			$(document).ready(function(){
				$("li.menu").each(function(){
					var menu = $(this);
					var subMenuNum = 0;
					$(menu).find("li").each(function(i){
						var subMenu = $(this);
						var url = $(subMenu).find("a").attr("href");
						var urlArray = url.split("/");
						//获取url中web后的第一段
						var index=-1;
						for(var a=0;a<urlArray.length;a++){
							if(urlArray[a]=="web"){
								index = a+1;
								break;
							}				
						}
						
						//存在url请求第一段
						if(index!=-1&&index<urlArray.length){
							var menuKey = urlArray[index]+"_menu_auth";
							if(perms.indexOf(menuKey)==-1){
								$(subMenu).remove();
							}else{
								subMenuNum++;
							}
						}
					});
					
					if(subMenuNum==0){
						$(menu).remove();
					}
				});
				
				initControlAuth();
			});
			
			function initControlAuth(){
				var url = window.location.href;
				var urlArray = url.split("/");
				//获取url中web后的第一段
				var index=-1;
				for(var a=0;a<urlArray.length;a++){
					if(urlArray[a]=="web"){
						index = a+1;
						break;
					}				
				}
				
				if(index!=-1&&index<urlArray.length){
					if(urlArray[index]!="main"){
						var controlKey = urlArray[index]+"_control_auth";
						if(perms.indexOf(controlKey)==-1){
							$("#page-content .btn:not(.btn-notcontrol)").remove();
						}
					}
				}
			}
			</script>
			
			<%if(contentPage.contains("sys")||contentPage.contains("roleAuthRelate")||contentPage.contains("userRoleRelate")){ %>
				<li class="active menu"><a href="javascript:void(0)"><i class="fa fa-expeditedssl"></i> <span class="nav-label">用户与权限</span> <span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="javascript:void(0)"><i class="fa fa-expeditedssl"></i> <span class="nav-label">用户与权限</span> <span class="fa arrow"></span></a>
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
			
			
			
			<%if(contentPage.contains("hr")||contentPage.contains("staffDepartmentRelate")){ %>
				<li class="active menu"><a href="javascript:void(0)"><i class="fa fa-user-circle"></i> <span class="nav-label">职员与组织</span> <span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="javascript:void(0)"><i class="fa fa-user-circle"></i> <span class="nav-label">职员与组织</span> <span class="fa arrow"></span></a>
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
			
			
			
			<%if(contentPage.contains("md")||contentPage.contains("subject")){ %>
				<li class="active menu"><a href="javascript:void(0)"><i class="fa fa-database"></i> <span class="nav-label">主数据</span> <span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="javascript:void(0)"><i class="fa fa-database"></i> <span class="nav-label">主数据</span> <span class="fa arrow"></span></a>
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
			
			
			
			<%if(contentPage.contains("po")||contentPage.contains("so")){ %>
				<li class="active menu"><a href="#"><i class="fa fa-list-alt"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="#"><i class="fa fa-list-alt"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
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
				
			<%if(contentPage.contains("inv")){ %>		
				<li class="active menu"><a href="#"><i class="fa fa-truck"></i> <span class="nav-label">库房管理</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="#"><i class="fa fa-truck"></i> <span class="nav-label">库房管理</span><span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level collapse">
					<%if(contentPage.equalsIgnoreCase("invInputList")||contentPage.equalsIgnoreCase("invInputEdit")){ %>
						<li class="active"><a href="web/invInputHead/getInvInputHeadList">入库单</a></li>
					<%}else{ %>
						<li><a href="web/invInputHead/getInvInputHeadList">入库单</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("invOutputList")||contentPage.equalsIgnoreCase("invOutputEdit")){ %>
						<li class="active"><a href="web/invOutputHead/getInvOutputHeadList">出库单</a></li>
					<%}else{ %>
						<li><a href="web/invOutputHead/getInvOutputHeadList">出库单</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("invWarehouseList")||contentPage.equalsIgnoreCase("invWarehouseEdit")
						||contentPage.equalsIgnoreCase("invStockList")||contentPage.equalsIgnoreCase("invStockEdit")||contentPage.equalsIgnoreCase("invStockDetailList")
						||contentPage.equalsIgnoreCase("invStockCheckList")||contentPage.equalsIgnoreCase("invStockCheckEdit")){ %>
						<li class="active"><a href="web/invWarehouse/getInvWarehouseList">仓库及库存管理</a></li>
					<%}else{ %>
						<li><a href="web/invWarehouse/getInvWarehouseList">仓库及库存管理</a></li>
					<%} %>
				</ul>
			</li>
				
			
			
			<%if(contentPage.contains("pay")||contentPage.contains("receipt")){ %>	
				<li class="active menu"><a href="#"><i class="fa fa-handshake-o"></i> <span class="nav-label">发票管理</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="#"><i class="fa fa-handshake-o"></i> <span class="nav-label">发票管理</span><span class="fa arrow"></span></a>
			<%} %>
				<ul class="nav nav-second-level collapse">
					<%if(contentPage.equalsIgnoreCase("payList")||contentPage.equalsIgnoreCase("payEdit")){ %>
						<li class="active"><a href="web/payHead/getPayHeadList">采购发票</a></li>
					<%}else{ %>
						<li><a href="web/payHead/getPayHeadList">采购发票</a></li>
					<%} %>
					
					<%if(contentPage.equalsIgnoreCase("receiptList")||contentPage.equalsIgnoreCase("receiptEdit")){ %>
						<li class="active"><a href="web/receiptHead/getReceiptHeadList">销售发票</a></li>
					<%}else{ %>
						<li><a href="web/receiptHead/getReceiptHeadList">销售发票</a></li>
					<%} %>
					
					<%-- 
					<li><a href="profile.html">付款确认</a></li>
					<li><a href="contacts_2.html">收款确认</a></li>
					--%>
				</ul>
			</li>
			
			
			
			<%if(contentPage.contains("voucher")){ %>		
				<li class="active menu"><a href="#"><i class="fa fa-money"></i> <span class="nav-label">财务管理</span><span class="fa arrow"></span></a>
			<%}else{ %>
				<li class="menu"><a href="#"><i class="fa fa-money"></i> <span class="nav-label">财务管理</span><span class="fa arrow"></span></a>
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
					
					<%if(contentPage.equalsIgnoreCase("voucherTypeNumberEdit")){ %>
						<li class="active"><a href="web/finVoucherModelHead/getVoucherTypeNumber">凭证号维护&nbsp;<i class="fa fa-sliders"></i></a></li>
					<%}else{ %>
						<li><a href="web/finVoucherModelHead/getVoucherTypeNumber">凭证号维护&nbsp;<i class="fa fa-sliders"></i></a></li>
					<%} %>
				</ul>
			</li>
			
			
			
			<%if(contentPage.contains("dataset")){ %>
				<li class="active menu"><a href="javascript:void(0)"><i class="fa fa-cog"></i> <span class="nav-label">系统配置</span><span class="label label-info float-right">管理员</span></a>
			<%}else{ %>
				<li class="menu"><a href="javascript:void(0)"><i class="fa fa-cog"></i> <span class="nav-label">系统配置</span><span class="label label-info float-right">管理员</span></a>
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

			
			
		</ul>

	</div>
</nav>
