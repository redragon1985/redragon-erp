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

<%-- 导出星标modal --%>
<jsp:include page="common/gitStar.jsp"></jsp:include>

<div class="wrapper wrapper-content animated fadeInRight">
	
	<%-- 顶部版本及价格导览开始 --%>
	<div class="row">
		<div class="col-lg-4">
			<div class="ibox" style="height:206px;">
				<div class="ibox-title" style="padding-right:15px;">
					<h4 class="float-right text-danger">永久免费</h4>
					<h5>
						<i class="fa fa-puzzle-piece"></i> 社区版<span class="label label-success">开源</span>
					</h5>
				</div>
				<div class="ibox-content">
					<p>
						（1）软件开源免费、功能持续迭代<br /> （2）实现ERP主流程的业务财务闭环<br /> （3）提供必备的构建文档和操作手册<br />
						<span class="text-success" style="font-weight: bold;">（4）供个人学习或企业内部使用</span>
					</p>
					
					<div class="text-right"><button class="btn btn-default btn-outline btn-xs" type="button" onclick="window.open('https://www.redragon-erp.com/product.html')">了解更多</button></div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="ibox" style="height:206px;">
				<div class="ibox-title" style="padding-right:15px;">
					<h4 class="float-right text-danger">
						¥20,000.00 <span class="text-muted"
							style="text-decoration: line-through;font-weight:400;">¥50,000.00</span>
					</h4>
					<h5>
						<i class="fa fa-puzzle-piece"></i> 社区版<span class="label label-info">商业授权</span>
					</h5>
				</div>
				<div class="ibox-content">
					<p>
						（1）社区版开源、功能持续迭代<br /> （2）实现ERP主流程的业务财务闭环<br /> （3）提供完备的技术文档，易于部署和二次开发<br />
						<span class="text-info" style="font-weight: bold;">（4）获取商业授权后，可二次分发，可商用</span>
					</p>
					
					<div class="text-right"><button class="btn btn-default btn-outline btn-xs" type="button" onclick="window.open('https://www.redragon-erp.com/product.html')">了解更多</button></div>
				</div>
			</div>
		</div>
		<div class="col-lg-4">
			<div class="ibox" style="height:206px;">
				<div class="ibox-title" style="padding-right:15px;">
					<h4 class="float-right text-danger">
						¥3,000.00 <span class="text-muted"
							style="text-decoration: line-through;font-weight:400;">¥20,000.00</span>
					</h4>
					<h5>
						<i class="fa fa-globe"></i> 生态版<span class="label label-primary">会员制</span>
					</h5>
				</div>
				<div class="ibox-content">
					<p>
						（1）含社区版所有功能及服务<br /> <span class="text-navy" style="font-weight: bold;">（2）生态产品簇EHR、CRM持续发布</span><br />
						<span class="text-navy" style="font-weight: bold;">（3）完整的企业级ERP开源解决方案</span><br /> <span
							class="text-navy" style="font-weight: bold;">（4）更完善更专业的配套服务</span>
					</p>
					
					<div class="text-right"><button class="btn btn-default btn-outline btn-xs" type="button" onclick="window.open('https://www.redragon-erp.com/product.html')">了解更多</button></div>
				</div>
			</div>
		</div>
	</div>
	<%-- 顶部版本及价格导览结束 --%>
	
	<%-- 中部系统初始化向导开始 --%>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>ERP初始化向导</h5>
					<div class="ibox-tools">
					</div>
				</div>
				<div class="ibox-content">
					<p>请根据下面的步骤完成配置，即可正常使用</p>
					<div id="wizard">
						<h1>&nbsp;<i class="fa fa-expeditedssl"></i>&nbsp;用户与权限配置</h1>
						<div class="step-content" style="position: static;">
							<div class="text-center m-t-md">
								<h2></h2>
							</div>
							<!-- 引导1内容 -->
							<div style="font-size: 16px;">
								<ol>
								    <li>
								        <p>
								            进入<strong>【用户与权限】</strong>模块
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【用户管理】</strong>中创建新用户
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【角色管理】</strong>中创建新角色，或使用现有角色
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【权限管理】</strong>中创建新权限，或使用现有权限
								        </p>
								    </li>
								    <li>
								        <p>
								            如果存在新增的角色或权限，在<strong>【角色关联权限】</strong>中进行关联
								        </p>
								    </li>
								    <li>
								        <p>
								            最后在<strong>【用户分配角色】</strong>中将新增的用户与角色关联
								        </p>
								    </li>
								</ol>
							</div>
							<!-- 引导1内容 -->
						</div>

						<h1>&nbsp;<i class="fa fa-user-circle"></i>&nbsp;职员与组织配置</h1>
						<div class="step-content" style="position: static;">
							<div class="text-center m-t-md">
								<h2></h2>
							</div>
							<!-- 引导2内容 -->
							<div style="font-size: 16px;">
								<ol>
								    <li>
								        <p>
								            进入<strong>【职员与组织】</strong>模块
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【职员管理】</strong>中创建新职员
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【岗位管理】</strong>中创建公司相关岗位
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【部门管理】</strong>中创建公司组织架构
								        </p>
								    </li>
								    <li>
								        <p>
								            最后在<strong>【职员关联部门】</strong>中将新增的职员与部门关联
								        </p>
								    </li>
								</ol>
							</div>
							<!-- 引导2内容 -->
						</div>

						<h1>&nbsp;<i class="fa fa-cog"></i>&nbsp;数据字典配置</h1>
						<div class="step-content" style="position: static;">
							<div class="text-center m-t-md">
								<h2></h2>
							</div>
							<!-- 引导3内容 -->
							<div style="font-size: 16px;">
								<ol>
								    <li>
								        <p>
								            进入<strong>【系统配置】</strong>模块
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【数据字典设置】</strong>中维护所有已存在值集中的值
								        </p>
								    </li>
								</ol>
							</div>
							<!-- 引导3内容 -->
						</div>
						
						<h1>&nbsp;<i class="fa fa-database"></i>&nbsp;主数据配置</h1>
						<div class="step-content" style="position: static;">
							<div class="text-center m-t-md">
								<h2></h2>
							</div>
							<!-- 引导4内容 -->
							<div style="font-size: 16px;">
								<ol>
								    <li>
								        <p>
								            进入<strong>【主数据】</strong>模块
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【客户管理】</strong>中创建新用户，并提交审核
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【供应商管理】</strong>中创建新供应商，并提交审核
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【物料与服务类型】</strong>中创建公司需要的物料或服务类型
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【物料与服务】</strong>中创建新物料或服务，并提交审核
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【项目管理】</strong>中创建新项目，并提交审核
								        </p>
								    </li>
								    <li>
								        <p>
								            在<strong>【科目管理】</strong>中创建公司的科目树
								        </p>
								    </li>
								</ol>
							</div>
							<!-- 引导4内容 -->
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%-- 中部系统初始化向导结束 --%>
	
	<%-- 底部时间线开始 --%>
	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>ERP研发时间轴</h5>
					<div class="ibox-tools">
					</div>
				</div>
				<div class="ibox-content" id="ibox-content">

					<div id="vertical-timeline"
						class="vertical-container dark-timeline center-orientation">
						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon blue-bg">
								<i class="fa fa-upload"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>第一版上线：完成基础功能，并实现最小业务闭环</h2>
								<p>
								(1)&nbsp;用户与权限<br/>
								(2)&nbsp;职员与组织<br/>
								(3)&nbsp;主数据<br/>
								(4)&nbsp;系统配置<br/>
								(5)&nbsp;采购与销售订单<br/>
								(6)&nbsp;付款与收款单<br/>
								(7)&nbsp;凭证管理
								</p>
								<a href="https://www.redragon-erp.com" target="_blank" class="btn btn-sm btn-success">&nbsp;访问官网&nbsp;</a> 
								<span class="vertical-date" style="font-weight: bold;"> v0.5 （已发布）<br /> <small>2020.8.12</small>
								</span>
							</div>
						</div>

						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon yellow-bg">
								<i class="fa fa-info-circle"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>小版本更新：解决第一版BUG，增加小功能</h2>
								<p>
								(1)&nbsp;增加数据权限功能及配置<br/>
								(2)&nbsp;增加操作权限功能及配置<br/>
								(3)&nbsp;优化凭证自动编号逻辑<br/>
								(4)&nbsp;优化表单提交验证逻辑<br/>
								(5)&nbsp;增加结算时收付款金额的判断逻辑<br/>
								</p>
								<span class="vertical-date" style="font-weight: bold;"> v0.51 （已发布）<br /> <small>2020.8</small>
								</span>
							</div>
						</div>

						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon lazur-bg">
								<i class="fa fa-flash"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>新功能迭代</h2>
								<p>
								(1)&nbsp;增加入库管理、出库管理<br/>
								(2)&nbsp;增加仓库管理、库存管理及库存盘点<br/>
								(3)&nbsp;优化shiro授权时的存储逻辑<br/>
								</p>
								<a href="https://gitee.com/redragon/redragon-erp/blob/master/README.md" target="_blank" class="btn btn-sm btn-info">版本说明</a> 
								<span class="vertical-date" style="font-weight: bold;"> v0.6 （已发布）<br /> <small>2020.9</small></span>
							</div>
						</div>
						
						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon yellow-bg">
								<i class="fa fa-info-circle"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>小版本更新：解决0.6版本 BUG，增加小功能</h2>
								<p>
								(1)&nbsp;优化物料选择逻辑<br/>
								(2)&nbsp;优化maven jar包依赖关系<br/>
								(3)&nbsp;软件分发时同步发布war包<br/>
								</p>
								<span class="vertical-date" style="font-weight: bold;"> v0.61 （已发布）<br /> <small>2020.9</small>
								</span>
							</div>
						</div>
						
						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon lazur-bg">
								<i class="fa fa-flash"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>新功能迭代</h2>
								<p>
								(1)&nbsp;增加应收发票管理<br/>
								(2)&nbsp;增加应付发票管理<br/>
								(3)&nbsp;实现发票与订单和出入库的三单匹配<br/>
								(4)&nbsp;增加付款和收款模块<br/>
								(5)&nbsp;实现收付款对发票的核销<br/>
								</p>
								<a href="https://gitee.com/redragon/redragon-erp/blob/master/README.md" target="_blank" class="btn btn-sm btn-info">版本说明</a> 
								<span class="vertical-date" style="font-weight: bold;"> v0.7 （已发布）<br /> <small>2020.9</small></span>
							</div>
						</div>
						
						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon lazur-bg">
								<i class="fa fa-flash"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>新功能迭代</h2>
								<p>
								(1)&nbsp;增加会计分录功能<br/>
								(2)&nbsp;增加凭证的导出功能，用于与第三方财务系统集成<br/>
								(3)&nbsp;自动生成付款、收款、转账凭证<br/>
								</p>
								<a href="https://gitee.com/redragon/redragon-erp/blob/master/README.md" target="_blank" class="btn btn-sm btn-info">版本说明</a> 
								<span class="vertical-date" style="font-weight: bold;"> v0.8 （已发布）<br /> <small>2020.9</small></span>
							</div>
						</div>
						
						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon yellow-bg">
								<i class="fa fa-info-circle"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>小版本更新：解决0.8之前版本 BUG，增加小功能</h2>
								<p>
								(1)&nbsp;修改之前版本的所有BUG<br/>
								(2)&nbsp;优化各个模块的功能<br/>
								(3)&nbsp;增加变更版本记录与查看功能<br/>
								</p>
								<span class="vertical-date" style="font-weight: bold;"> v0.9 （已发布）<br /> <small>2020.10</small>
								</span>
							</div>
						</div>
						
						<div class="vertical-timeline-block">
							<div class="vertical-timeline-icon lazur-bg">
								<i class="fa fa-flash"></i>
							</div>

							<div class="vertical-timeline-content">
								<h2>新功能迭代</h2>
								<p>
								(1)&nbsp;增加采购、销售协议模块<br/>
								(2)&nbsp;增加采购、销售订单类型，并与协议联动<br/>
								(3)&nbsp;增加杂项出入库功能<br/>
								(4)&nbsp;增加入库退货与出库退货的流程<br/>
								(5)&nbsp;增加预付款与预收款处理流程<br/>
								(6)&nbsp;增加借项通知单、贷项通知单功能<br/>
								(7)&nbsp;合并处理预付款、预收款、借项通知单、贷项通知单的收付款功能<br/>
								(8)&nbsp;增加收付款冲销功能<br/>
								</p>
								<a href="https://gitee.com/redragon/redragon-erp/blob/master/README.md" target="_blank" class="btn btn-sm btn-info">生态板</a> 
								<span class="vertical-date" style="font-weight: bold;"> v0.9.5 （已发布）<br /> <small>2020.10</small></span>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- 底部时间线结束 --%>
	
</div>

<!-- Steps -->
<link href="css/plugins/steps/jquery.steps.css" rel="stylesheet">
<script src="js/plugins/steps/jquery.steps.min.js"></script>

<script>
	$(document).ready(function() {
		$("#wizard").steps();
	});
</script>