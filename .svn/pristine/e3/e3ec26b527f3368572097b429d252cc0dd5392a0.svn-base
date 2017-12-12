<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<html>
<head>
<title>用户编辑</title>
<%@ include file="../../include/head.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/3rd-lib/codemirror/lib/codemirror.css">
<link rel="stylesheet" href="${ctxStatic}/3rd-lib/codemirror/theme/neat.css">
<style>
.tpl-content-wrapper {
	margin-left: 0
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
								<div class="widget-title am-fl">列表信息</div>
							</div>
							<div class="widget-body am-fr">
								<form class="am-form tpl-form-border-form" data-am-validator
									modelAttribute="port"
									action="${ctx}/port/<c:choose><c:when test="${empty port.id}">create</c:when><c:otherwise>update</c:otherwise></c:choose>"
									method="post">
									<input type="hidden" name="id" value="${port.id}" />
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">接口名：</label>
										<div class="am-u-sm-9">
											<input type="text" name="portName" placeholder="接口名称"
												value="${port.portName}" required/>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">所属类型：</label>
										<div class="am-u-sm-9">
											<select name="state" id ="state" data="${port.state}"onchange="show()">
													<option value="1" >not bean</option>
													<option value="2">bean</option>
													<option value="-1">分页</option>
											</select>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">所属数据源：</label>
										<div class="am-u-sm-9">
											<select name="sourceId" data="${port.sourceId}">
												<c:forEach items="${datasour}" var="source">
													<option value="${source.id}">${source.project}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">SQL参数名：</label>
										<div class="am-u-sm-9">
											<input type="text" name="params" placeholder="SQL参数用,分隔" value="${port.params}" required></input>
										</div>
									</div>

									<%--<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">SQL语句：</label>
										<div class="am-u-sm-9">
											<textarea rows="3" cols="1" name="commitSql" placeholder="SQL语句 参数用?代替">${port.commitSql}</textarea>
										</div>
									</div>--%>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">SQL语句：</label>
										<div class="am-u-sm-9">
											<textarea id="cm_ta" rows="3" cols="1" name="commitSql" placeholder="SQL语句 参数用?代替 ,where后面必须 1=1" required>${port.commitSql}</textarea>
										</div>
									</div>
									
									
									
									
									
									<div id ="bean" hidden="hidden" >
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">入口类类名：</label>
										<div class="am-u-sm-9">
											<input type="text" name="firstjavaname" placeholder="入口类类名"
												value="${port.firstjavaname}" required/>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">入口类：</label>
										<div class="am-u-sm-9">
											<textarea id="cm_ta" rows="3" cols="1" name="firstjava" placeholder="封装类" required>${port.firstjava}</textarea>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">出口类类名：</label>
										<div class="am-u-sm-9">
											<input type="text" name="lastjavaname" placeholder="出口类类名"
												value="${port.lastjavaname}" required/>
										</div>
									</div>
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">出口类：</label>
										<div class="am-u-sm-9">
											<textarea id="cm_ta" rows="3" cols="1" name="lastjava" placeholder="必须有public String export(String result,Map<String,String> map){...}" required>${port.lastjava}</textarea>
										</div>
									</div>
									
									</div>
									
									
									
									
									<div class="am-form-group">
										<label class="am-u-sm-3 am-form-label">接口说明：</label>
										<div class="am-u-sm-9">
											<textarea rows="3" cols="1" name="portComment" placeholder="接口说明">${port.portComment}</textarea>
										</div>
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
<script src="${ctxStatic}/3rd-lib/codemirror/lib/codemirror.js"></script>
<script src="${ctxStatic}/3rd-lib/codemirror/mode/sql/sql.js"></script>
<script>
function show(){
		if(document.getElementById("state").value == 2){
			$("#bean").show();
		}else{
			$("#bean").hide();
		}
	}



	$(document).ready(function() {
		
        var editor = CodeMirror.fromTextArea(document.getElementById("cm_ta"), {
            lineNumbers: true,     // 显示行数
            indentUnit: 4,         // 缩进单位为4
            styleActiveLine: true, // 当前行背景高亮
            matchBrackets: true,   // 括号匹配
            mode: 'text/x-sql',     // SQL模式
            lineWrapping: true,    // 自动换行
            theme: 'neat',      // 使用monokai模版
        });
        $("form").on('submit', function(){
            editor.save();
        })

		//消息提醒
		var msg = '${msg}';
		if(msg!=''){
			showMsg(msg);
			if(msg=="保存成功"){
				//closeModel(true);//关闭窗口
				location.href="${ctx}/port/list";			}
		}
		initSelectValue(true);//初始化下拉框的值
		if(document.getElementById("state").value == 2){
			$("#bean").show();
		};
	}
	);
</script>

</body>
</html>