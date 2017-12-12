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
								<div class="widget-title am-fl">用户列表</div>
							</div>
							<div class="widget-body am-fr">
								<div class="am-u-sm-12">
									<table id="contentTable"
										class="am-table am-table-compact am-table-striped tpl-table-black">
										<thead>
											<tr>
												<th>序号</th>
												<th>账号</th>
												<th>姓名</th>
												<th>ApiKey</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${page.list}" var="user" varStatus="status">
												<tr>
													<td>${status.index+1}</td>
													<td>${user.username}</td>
													<td>${user.name}</td>
													<td>${user.apikey}</td>
													<td><shiro:hasPermission name="sys:user:update">
															<a href="${ctx}/apikey/update?id=${user.id}"
																title="重新生成"> <span class="am-icon-refresh">
															</span>
															</a>
														</shiro:hasPermission></td>
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
	<script>
    $(function () {
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback : {
                onClick : function(event, treeId, treeNode) {
                    location.href = "${ctx}/user/list?organizationId="+treeNode.id;
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
</script>
</body>
</html>