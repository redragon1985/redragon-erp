<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<div class="row wrapper border-bottom white-bg page-heading">
	<div class="col-lg-10">
		<h2>Basic Form</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">Home</a></li>
			<li class="breadcrumb-item"><a>Forms</a></li>
			<li class="breadcrumb-item active"><strong>Basic Form</strong></li>
		</ol>
	</div>
	<div class="col-lg-2"></div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">

	<div class="row">
		<div class="col-lg-12">
			<div class="ibox ">
				<div class="ibox-title">
					<h5>
						All form elements <small>With custom checbox and radion
							elements.</small>
					</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i
							class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="#" class="dropdown-item">Config option 1</a></li>
							<li><a href="#" class="dropdown-item">Config option 2</a></li>
						</ul>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>

				<div class="ibox-content">
					<form id="form" method="get">
						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">Normal</label>
							<div class="col-sm-10">
								<input name="username" type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group  row">
							<label class="col-sm-2 col-form-label">Date</label>
							<div id="data_1" class="col-sm-10">
								<div class="input-group date">
									<span class="input-group-addon" style=""><i
										class="fa fa-calendar"></i></span><input type="text"
										class="form-control" value="03/04/2014">
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Help text</label>
							<div class="col-sm-10">
								<input type="text" class="form-control"> <span
									class="form-text m-b-none">A block of help text that
									breaks onto a new line and may extend beyond one line.</span>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Password</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" name="password">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Checkboxes and
								radios <br /> <small class="text-navy">Normal Bootstrap
									elements</small>
							</label>

							<div class="col-sm-10">
								<div>
									<label> <input type="checkbox" value="">选项1
									</label>
								</div>
								<div>
									<label> <input type="checkbox" value="">选项2
									</label>
								</div>

								<div>
									<label> <input type="radio" checked="" value="option1"
										id="optionsRadios1" name="optionsRadios">选项1
									</label>
								</div>
								<div>
									<label> <input type="radio" value="option2"
										id="optionsRadios2" name="optionsRadios">选项2
									</label>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Inline checkboxes</label>

							<div class="col-sm-10">
								<label><input type="checkbox" value="option1"
									id="inlineCheckbox1">选项1</label> <label class="checkbox-inline"><input
									type="checkbox" value="option2" id="inlineCheckbox2">选项2</label>
								<label><input type="checkbox" value="option3"
									id="inlineCheckbox3">选项3</label>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Checkboxes &amp;
								radios <br /> <small class="text-navy">Custom elements</small>
							</label>

							<div class="col-sm-10">
								<div class="i-checks">
									<label> <input type="checkbox" value=""> <i></i>选项1
									</label>
								</div>
								<div class="i-checks">
									<label> <input type="checkbox" value="" checked="">
										<i></i>选项2
									</label>
								</div>
								<div class="i-checks">
									<label> <input type="checkbox" value="" disabled=""
										checked=""> <i></i>选项3
									</label>
								</div>
								<div class="i-checks">
									<label> <input type="checkbox" value="" disabled="">
										<i></i>选项4
									</label>
								</div>

								<div class="i-checks">
									<label> <input type="radio" value="option1" name="a">
										<i></i>选项1
									</label>
								</div>
								<div class="i-checks">
									<label> <input type="radio" checked="" value="option2"
										name="a"> <i></i>选项2
									</label>
								</div>
								<div class="i-checks">
									<label> <input type="radio" disabled="" checked=""
										value="option2"> <i></i>选项3
									</label>
								</div>
								<div class="i-checks">
									<label> <input type="radio" disabled="" name="a">
										<i></i>选项4
									</label>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Inline checkboxes</label>

							<div class="col-sm-10">
								<label class="checkbox-inline i-checks"> <input
									type="checkbox" value="option1">选项1
								</label> <label class="i-checks"> <input type="checkbox"
									value="option2">选项2
								</label> <label class="i-checks"> <input type="checkbox"
									value="option3">选项3
								</label>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Select</label>

							<div class="col-sm-10">
								<select class="form-control m-b" name="account">
									<option>option 1</option>
									<option>option 2</option>
									<option>option 3</option>
									<option>option 4</option>
								</select>

								<div class="col-lg-4 m-l-n">
									<select class="form-control" multiple="">
										<option>option 1</option>
										<option>option 2</option>
										<option>option 3</option>
										<option>option 4</option>
									</select>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row has-success">
							<label class="col-sm-2 col-form-label">Input with success</label>
							<div class="col-sm-10">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row has-warning">
							<label class="col-sm-2 col-form-label">Input with warning</label>
							<div class="col-sm-10">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group  row has-error">
							<label class="col-sm-2 col-form-label">Input with error</label>
							<div class="col-sm-10">
								<input type="text" class="form-control">
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Column sizing</label>
							<div class="col-sm-10">
								<div class="row">
									<div class="col-md-2">
										<input type="text" placeholder=".col-md-2"
											class="form-control">
									</div>
									<div class="col-md-3">
										<input type="text" placeholder=".col-md-3"
											class="form-control">
									</div>
									<div class="col-md-4">
										<input type="text" placeholder=".col-md-4"
											class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Input groups</label>
							<div class="col-sm-10">
								<div class="input-group m-b">
									<div class="input-group-prepend">
										<span class="input-group-addon">@</span>
									</div>
									<input type="text" placeholder="Username" class="form-control">
								</div>

								<div class="input-group m-b">
									<input type="text" class="form-control">
									<div class="input-group-append">
										<span class="input-group-addon">.00</span>
									</div>
								</div>

								<div class="input-group m-b">
									<div class="input-group-prepend">
										<span class="input-group-addon">$</span>
									</div>
									<input type="text" class="form-control">
									<div class="input-group-append">
										<span class="input-group-addon">.00</span>
									</div>
								</div>

								<div class="input-group m-b">
									<div class="input-group-prepend">
										<span class="input-group-addon"> <input type="checkbox">
										</span>
									</div>
									<input type="text" class="form-control">
								</div>

								<div class="input-group m-b">
									<div class="input-group-prepend">
										<span class="input-group-addon"> <input type="radio">
										</span>
									</div>
									<input type="text" class="form-control">
								</div>

							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">Button addons</label>
							<div class="col-sm-10">
								<div class="input-group m-b">
									<span class="input-group-prepend">
										<button type="button" class="btn btn-primary">Go!</button>
									</span> <input type="text" class="form-control">
								</div>

								<div class="input-group">
									<input type="text" class="form-control"> <span
										class="input-group-append">
										<button type="button" class="btn btn-primary">Go!</button>
									</span>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<label class="col-sm-2 col-form-label">With dropdowns</label>
							<div class="col-sm-10">
								<div class="input-group m-b">
									<div class="input-group-prepend">
										<button data-toggle="dropdown"
											class="btn btn-white dropdown-toggle" type="button">Action
										</button>
										<ul class="dropdown-menu">
											<li><a href="#">Action</a></li>
											<li><a href="#">Another action</a></li>
											<li><a href="#">Something else here</a></li>
											<li class="dropdown-divider"></li>
											<li><a href="#">Separated link</a></li>
										</ul>
									</div>
									<input type="text" class="form-control">
								</div>

								<div class="input-group">
									<input type="text" class="form-control">

									<div class="input-group-append">
										<button data-toggle="dropdown"
											class="btn btn-white dropdown-toggle" type="button">Action
										</button>
										<ul class="dropdown-menu float-right">
											<li><a href="#">Action</a></li>
											<li><a href="#">Another action</a></li>
											<li><a href="#">Something else here</a></li>
											<li class="dropdown-divider"></li>
											<li><a href="#">Separated link</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="hr-line-dashed"></div>

						<div class="form-group row">
							<div class="col-sm-4 col-sm-offset-2">
								<button class="btn btn-white btn-sm" type="submit">Cancel</button>
								<button class="btn btn-primary btn-sm" type="submit">Save
									changes</button>
								<button class="ladda-button ladda-button-demo btn btn-primary"
									data-style="expand-right">Submit</button>
							</div>
						</div>
					</form>
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

		$('#data_1 .input-group.date').datepicker({
			todayBtn : "linked",
			keyboardNavigation : false,
			forceParse : false,
			calendarWeeks : true,
			autoclose : true
		});

		var l = $('.ladda-button-demo').ladda();

		l.click(function() {
			// Start loading
			l.ladda('start');

			// Timeout example
			// Do something in backend and then stop ladda
			setTimeout(function() {
				l.ladda('stop');
			}, 2000)


		});

		$("#form").validate({
			rules : {
				username : {
					required : true,
					minlength : 3,
					userName: true
				}
			}
		});
	});
</script>