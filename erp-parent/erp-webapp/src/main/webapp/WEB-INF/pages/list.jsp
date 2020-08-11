<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<div class="row wrapper border-bottom white-bg page-heading">
	<div class="col-lg-10">
		<h2>Static Tables</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
			<li class="breadcrumb-item"><a>Tables</a></li>
			<li class="breadcrumb-item active"><strong>Static
					Tables</strong></li>
		</ol>
	</div>
	<div class="col-lg-2"></div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">

	<jsp:include page="common/alert/alert.jsp"></jsp:include>

	<div class="ibox-content m-b-sm border-bottom">
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="product_name">产品名称</label> <input
						type="text" id="product_name" name="product_name" value=""
						placeholder="产品名称" class="form-control">
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label class="control-label" for="price">价格</label> <input
						type="text" id="price" name="price" value="" placeholder="00.00"
						class="form-control">
				</div>
			</div>
			<div class="col-sm-2">
				<div class="form-group">
					<label class="control-label" for="quantity">数量</label> <input
						type="text" id="quantity" name="quantity" value=""
						placeholder="123" class="form-control">
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="status">状态</label> <select
						name="status" id="status" class="form-control">
						<option value="1" selected="">启用</option>
						<option value="0">关闭</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="date_added">添加日期</label>
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span><input
							id="date_added" type="text" class="form-control"
							value="03/04/2014">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="date_modified">修改日期</label>
					<div class="input-group date">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span><input
							id="date_modified" type="text" class="form-control"
							value="03/06/2014">
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="form-group">
					<label class="control-label" for="amount">金额</label> <input
						type="text" id="amount" name="amount" value="" placeholder="金额数量"
						class="form-control">
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12 text-right">
				<div class="form-group">
					<button type="button" class="btn btn-w-m btn-primary">查询</button>
				</div>
			</div>
		</div>
	</div>


	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">

				<div class="ibox-content">
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>

									<th></th>
									<th>id&nbsp;<i class="fa fa-caret-down"></i></th>
									<th>Project &nbsp;<i class="fa fa-sort"></i></th>
									<th>Completed &nbsp;<i class="fa fa-caret-up"></i></th>
									<th>Task</th>
									<th>Date</th>
									<th>status</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="checkbox" checked class="i-checks"
										name="input[]"></td>
									<td>1</td>
									<td>Project<small>This is example of project</small></td>
									<td><span class="pie">0.52/1.561</span></td>
									<td>20%</td>
									<td>Jul 14, 2013</td>
									<td><span class="label label-primary">启用</span></td>
									<td>
										<div class="btn-group">
											<button class="btn-white btn btn-xs">查看</button>
											<button class="btn-white btn btn-xs">编辑</button>
										</div>
									</td>
								</tr>
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td>2</td>
									<td>Alpha project</td>
									<td><span class="pie">6,9</span></td>
									<td>40%</td>
									<td>Jul 16, 2013</td>
									<td><span class="label label-primary">启用</span></td>
									<td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
								</tr>
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td>3</td>
									<td>Betha project</td>
									<td><span class="pie">3,1</span></td>
									<td>75%</td>
									<td>Jul 18, 2013</td>
									<td><span class="label label-danger">关闭</span></td>
									<td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
								</tr>
								<tr>
									<td><input type="checkbox" class="i-checks" name="input[]"></td>
									<td>4</td>
									<td>Gamma project</td>
									<td><span class="pie">4,9</span></td>
									<td>18%</td>
									<td>Jul 22, 2013</td>
									<td><span class="label label-warning">低库存</span></td>
									<td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
								</tr>
							</tbody>
							<tfoot>
								<jsp:include page="common/pages.jsp"></jsp:include>
							</tfoot>
						</table>
					</div>

				</div>
			</div>
		</div>

	</div>
</div>

<script>
	$(document).ready(function() {
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});

		$('#date_added').datepicker({
			todayBtn : "linked",
			keyboardNavigation : false,
			forceParse : false,
			calendarWeeks : true,
			autoclose : true
		});

		$('#date_modified').datepicker({
			todayBtn : "linked",
			keyboardNavigation : false,
			forceParse : false,
			calendarWeeks : true,
			autoclose : true
		});
	});
</script>