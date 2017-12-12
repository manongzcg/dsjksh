<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<title>用户编辑</title>
<%@ include file="../../include/head.jsp"%>
<link rel="stylesheet"
	href="${ctxStatic}/3rd-lib/jquery-ztree/css/zTreeStyle.css">
<style>
ul.ztree {
	margin-top: 10px;
	border: 1px solid #ddd;
	background: #fff;
	width: 198px;
	height: 200px;
	overflow-y: auto;
	overflow-x: auto;
}

.tpl-content-wrapper {
	margin-left: 0
}

.am-selected {
	width: 100%;
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
								<div class="widget-title am-fl">数据源信息</div>
							</div>
							<div class="widget-body am-fr">
								<form class="am-form tpl-form-border-form" data-am-validator
									modelAttribute="datasource"
									action="${ctx}/source/<c:choose><c:when test="${empty datasource.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>"
									method="post">
									<input type="hidden" name="id" value="${datasource.id}" />
									<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label"><span class="error">*</span>数据源类型：</label>
									<div class="am-u-sm-9">
										<select name="type" data="${datasource.type}">
											<option value="MYSQL">MySQL</option>
											<option value="ORCALE">Orcale</option>
										</select>
									</div>
									<label class="am-u-sm-4"></label>
								</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据源主机：</label>
										<div class="am-u-sm-9">
											<input type="text" name="host" placeholder="数据源主机"
												value="${datasource.host}" onkeyup="clearNoNum1(this)"required/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据源服务端口：</label>
										<div class="am-u-sm-9">
											<input type="text" name="port" placeholder="数据源服务端口"
												value="${datasource.port}" onkeyup="clearNoNum(this)"required/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据源管理用户：</label>
										<div class="am-u-sm-9" >
											<input type="text" name="sourceUser" placeholder="数据源管理用户"
												value="${datasource.sourceUser}" required/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据源管理密码：</label>
										<div class="am-u-sm-9">
											<input type="text" name="password" placeholder="数据源管理密码"
												value="${datasource.password}" required/>
										</div>
									</div>
									

									<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">数据源对应角色：</label>
									<div class="am-u-sm-9">
										<select name="roleId" data="${datasource.roleId}" required>
											<c:forEach items="${role}" var="role">
												<option value="${role.id}">${role.name}</option>
											</c:forEach>
										</select>
									</div>
								</div> 
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据源说明：</label>
										<div class="am-u-sm-9">
											<input type="text" name="comment" placeholder="数据源说明"
												value="${datasource.comment}" required/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据对应数据库名称：</label>
										<div class="am-u-sm-9">
											<input type="text" name="databaseName" placeholder="数据对应数据库名称"
												value="${datasource.databaseName}" required/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">数据对应项目名称：</label>
										<div class="am-u-sm-9">
											<input type="text" name="project" placeholder="数据对应项目名称"
												value="${datasource.project}" required/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">初始化时连接的个数：</label>
										<div class="am-u-sm-9">
											<input type="text" name="initialSize" placeholder="初始化时连接的个数"
												value="${empty datasource.initialSize ? 10 : datasource.initialSize}" onkeyup="clearNoNum(this)"/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">最小连接池数量：</label>
										<div class="am-u-sm-9">
											<input type="text" name="minIdle" placeholder="最小连接池数量"
												value="${empty datasource.minIdle ? 10 : datasource.minIdle}" onkeyup="clearNoNum(this)"/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">最大连接池数量：</label>
										<div class="am-u-sm-9">
											<input type="text" name="maxActive" placeholder="最大连接池数量"
												value="${empty datasource.maxActive ? 30 : datasource.maxActive}" onkeyup="clearNoNum(this)"/>
										</div>
									</div>
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">连接最大等待时间：</label>
										<div class="am-u-sm-9">
											<input type="text" name="maxWait" placeholder="连接最大等待时间"
												value="${empty datasource.maxWait ? 60000 : datasource.maxWait}" onkeyup="clearNoNum(this)"/>
											
										</div>
									</div>
									
									<div class="am-form-group">
										<div class="am-u-sm-9 am-u-sm-push-3">
											<button type="submit" class="am-btn am-btn-primary">保存</button>
											<button type="button" class="am-btn am-btn-danger"
												onclick="closeModel(false)">关闭</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="menuContent" class="menuContent"
		style="display: none; position: absolute; z-index: 10000;">
		<ul id="tree" class="ztree" style="margin-top: 0;"></ul>
	</div>
	<%@ include file="../../include/bottom.jsp"%>
	<script
		src="${ctxStatic}/3rd-lib/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${ctxStatic}/custom/js/ztree.select.js"></script>
<script>
	$(document).ready(function() {
		//消息提醒
		var msg = '${msg}';
		if(msg!=''){
			showMsg(msg);
			if(msg=="保存成功"){
				//closeModel(true);//关闭窗口
				location.href="${ctx}/source/list";			}
		}
		initSelectValue(true);//初始化下拉框的值
	});
	
	function clearNoNum(obj){
        //把非数字的都替换掉，除了数字
        obj.value = obj.value.replace(/[^\d]/g,"");
    }
	function clearNoNum1(obj){
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d.a-zA-Z]/g,"");
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g,"");
        //保证.只出现一次，而不能出现两次以上
        //obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    }
</script>

</body>
</html>