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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>


<div class="modal" id="voucherEntryDiv" tabindex="-1" role="dialog"
	aria-hidden="true" style="width: auto;">

	<div class="modal-dialog modal-lg" role="document"
		style="max-width: 1000px;">

		<div class="modal-content animated bounceInRight">
			<div class="modal-body"
				style="padding-bottom: 5px; padding-top: 5px;">

				<div class="wrapper wrapper-content animated fadeInRight"
					style="padding-bottom: 5px; padding-top: 5px;">
					<div class="row">
						<div class="col-lg-12">
							<div class="ibox " style="margin-bottom: 0px;">

								<div class="ibox-content">
									<div class="text-center">
										<h3>
											<strong>会计分录</strong>
										</h3>
									</div>


									<div class="form-group row" style="margin-bottom: 0px;">
										<div class="table-responsive">
											<table class="table table-hover"
												style="border: 2px solid; font-weight: bold; margin-bottom: 5px;"
												border="2">
												<thead>
												</thead>
												<tbody>
													<tr>
														<th width="60%" class="text-center" rowspan="2"
															style="vertical-align: middle;">会计科目</th>
														<th width="20%" class="text-center" colspan="11">借方金额</th>
														<th width="20%" class="text-center" colspan="11">贷方金额</th>
													</tr>
													<tr style="font-size: 11px; font-weight: normal;">
														<td>亿</td>
														<td>千</td>
														<td>百</td>
														<td>十</td>
														<td>万</td>
														<td>千</td>
														<td>百</td>
														<td>十</td>
														<td>元</td>
														<td style="color: silver;">角</td>
														<td style="color: silver;">分</td>
														<td>亿</td>
														<td>千</td>
														<td>百</td>
														<td>十</td>
														<td>万</td>
														<td>千</td>
														<td>百</td>
														<td>十</td>
														<td>元</td>
														<td style="color: silver;">角</td>
														<td style="color: silver;">分</td>
													</tr>

													<c:forEach items="${requestScope.finVoucherLineList}"
														var="data">
														<tr class="dataTr">
															<td>${data.subjectDesc} <input name="subjectCode"
																type="hidden" class="form-control"
																value="${data.subjectCode}">
															</td>

															<c:forEach items="${data.drAmountArray}" var="dr"
																varStatus="status">
																<c:choose>
																	<c:when test="${status.count==1}">
																		<td style="vertical-align: middle; font-size: 17px;">${dr}
																			<div class="drDiv"
																				style="position: absolute; margin-left: 8px; display: none;">
																				<input name="drAmount" type="text"
																					class="form-control" value="${data.drAmount}"
																					style="width: 150%;">
																			</div>
																		</td>
																	</c:when>
																	<c:when test="${status.count==10||status.count==11}">
																		<td
																			style="vertical-align: middle; color: silver; font-size: 12px;">${dr}</td>
																	</c:when>
																	<c:otherwise>
																		<td style="vertical-align: middle; font-size: 17px;">${dr}</td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>


															<c:forEach items="${data.crAmountArray}" var="cr"
																varStatus="status">
																<c:choose>
																	<c:when test="${status.count==1}">
																		<td style="vertical-align: middle; font-size: 17px;">${cr}
																			<div class="crDiv"
																				style="position: absolute; margin-left: 8px; display: none;">
																				<input name="crAmount" type="text"
																					class="form-control" value="${data.crAmount}"
																					style="width: 150%;">
																			</div>
																		</td>
																	</c:when>
																	<c:when test="${status.count==10||status.count==11}">
																		<td
																			style="vertical-align: middle; color: silver; font-size: 12px;">${cr}</td>
																	</c:when>
																	<c:otherwise>
																		<td style="vertical-align: middle; font-size: 17px;">${cr}</td>
																	</c:otherwise>
																</c:choose>
															</c:forEach>


														</tr>
													</c:forEach>

													<tr id="sumTr">
														<td align="right">合计：&nbsp;&nbsp;${requestScope.finVoucherHead.amountDesc}&nbsp;&nbsp;</td>

														<c:forEach
															items="${requestScope.finVoucherHead.drAmountArray}"
															var="amount" varStatus="status">
															<c:choose>
																<c:when test="${status.count==1}">
																	<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
																</c:when>
																<c:when test="${status.count==10||status.count==11}">
																	<td
																		style="vertical-align: middle; color: silver; font-size: 12px;">${amount}</td>
																</c:when>
																<c:otherwise>
																	<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														<c:forEach
															items="${requestScope.finVoucherHead.crAmountArray}"
															var="amount" varStatus="status">
															<c:choose>
																<c:when test="${status.count==1}">
																	<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
																</c:when>
																<c:when test="${status.count==10||status.count==11}">
																	<td
																		style="vertical-align: middle; color: silver; font-size: 12px;">${amount}</td>
																</c:when>
																<c:otherwise>
																	<td style="vertical-align: middle; font-size: 17px;">${amount}</td>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</tr>
												</tbody>
												<tfoot>
												</tfoot>
											</table>
										</div>
									</div>

									<div class="form-group row" style="margin-bottom: 0px;">

										<span class="col-sm-8" style="text-align: left;">分录时间：<fmt:formatDate
												value="${requestScope.finVoucherHead.voucherDate}"
												pattern="yyyy-MM-dd" /></span> <span class="col-sm-4"
											style="text-align: right;">制单人： 自动分录</span>
									</div>


								</div>
							</div>
						</div>


					</div>
				</div>

			</div>

		</div>

	</div>

</div>

<script>
	$(document).ready(function() {
	});
</script>