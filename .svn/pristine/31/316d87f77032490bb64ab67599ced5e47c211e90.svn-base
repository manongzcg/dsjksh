<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<%@ include file="../../include/head.jsp"%>
<link rel="stylesheet"
	href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
<link href="${ctxStatic}/custom/css/amazeui.select.css" type="text/css"
	rel="stylesheet" charset="UTF-8" />
<style>
.tpl-content-wrapper {
	margin-left: 0
}

.theme-black .widget .ztree li a {
	color: #ffffff
}
</style>
</head>
<body>
	<script src="${ctxStatic}/assets/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title am-fl">数据源列表</div>
							</div>
							<div class="widget-body am-fr">
								<div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs"> 
 											<shiro:hasPermission name="source:source:create"> 
 												<button type="button" 
 													class="am-btn am-btn-default am-btn-success" 
													onclick="openModel(false,'${ctx}/source/create')"> 
													<span class="am-icon-plus"></span> 新增
 												</button> 
											</shiro:hasPermission> 
									</div> 
<!-- 									<div class="am-btn-group am-btn-group-xs">  -->
<!-- 									<a class="am-btn am-btn-default am-btn-success"  target="_blank" href="http://localhost:8080/easyapi/druid/">数据源监控</a> -->
<!-- 									</div> -->
									</div>
								</div>
								
								
									


								<div class="am-u-sm-12">
									<table id="contentTable"
										class="am-table am-table-compact am-table-striped tpl-table-black">
										<thead>
											<tr>
												<th>序号</th>
												<th>数据源ID</th>
												<th>类型</th>
												<th>主机</th>
												<th>数据源用户</th>
												<th>数据源说明</th>
												<th>数据库名称</th>
												<th>项目名称</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${page.list}" var="datasource" varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${datasource.id}</td>
													<td>${datasource.type}</td>
													<td>${datasource.host}</td>
													<td>${datasource.sourceUser}</td>
													<td>${datasource.comment}</td>
													<td>${datasource.databaseName}</td>
													<td>${datasource.project}</td>
													<td>
													<shiro:hasPermission name="source:source:update">
															<a href="javascript:;"
																onclick="openModel(false,'${ctx}/source/update?id=${datasource.id}')"
																title="编辑"><span class="am-icon-pencil"></span></a>
														</shiro:hasPermission> <shiro:hasPermission name="source:source:delete">
																<a href="${ctx}/source/delete?id=${datasource.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}" 
																	onclick="return confirm('确认要删除该条数据吗？', this.href)"
																	title="删除"><span
																	class="am-text-danger am-icon-trash-o"></span></a>
 															
														</shiro:hasPermission> 
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

								<div class="am-u-lg-12 am-cf">
									<%@ include file="../../utils/pagination.jsp"%>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../../include/bottom.jsp"%>
	<script
		src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}/custom/js/amazeui.select.js"></script>
	<script type="text/javascript">
    $(document).ready(function () {
        var msg = '${msg}';
        if (msg != '') {
            showMsg(msg);
        }
    });
</script>
	<!-- <script>
    $(function () {
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback : {
                onClick : function(event, treeId, treeNode) {
                    location.href = "${ctx}/source/list";
                }
            }
        };
        var zNodes =[
            <c:forEach items="${organizationList}" var="o" varStatus="status">
            { id:${o.id}, pId:${o.parentId}, name:"${o.name}", open:${o.rootNode}}<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        $(document).ready(function(){
            var ztree = $.fn.zTree.init($("#tree"), setting, zNodes);
            ztree.expandAll(true);
        });
    });
</script> -->
</body>
</html>