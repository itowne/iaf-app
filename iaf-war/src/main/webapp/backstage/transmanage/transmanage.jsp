<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:include page="${ctx}/template/head.jsp" />
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
	<div id="workframe-wrapper" class="clearfix">
		<div id="location">
			<p>当前位置：系统维护</p>
		</div>
		<div id="content">
			<h3 class="title">
				<span>系统维护操作</span>
			</h3>
			<div class="results report">
				<form action="#" target="runFrame">
					<table width="85%" border="0" cellspacing="0" cellpadding="0"
						class="data-table">
						<tr>
							<td class="label">商户资料文件实时导入</td>
							<td class="data"><input type="text" name="merchFileName"
								value="${merchFileName}" />格式: yyyyMMdd (<font style="color: #8E8E8E ">按格式填写导入文件名中的日期,点击载入</font>)</td>
							<td class="data"><input class="dark-btn" type="button" value="导入文件"
								class="submit" onclick="ftpMerchBaseSubmit();" /></td>
						</tr>
						<tr>
							<td class="label">巡检信息文件实时导入</td>
							<td class="data"><input type="text" name="inspectFileName"
								value="${inspectFileName}" />格式: yyyyMMdd (<font style="color: #8E8E8E ">按格式填写导入文件名中的日期,点击载入</font>)</td>
							<td class="data"><input class="dark-btn" type="button" value="导入文件"
								class="submit" onclick="ftpInspectLogSubmit();" /></td>
						</tr>
						<tr>
							<td class="label">交易记录文件实时导入(开始)</td>
							<td class="data"><input type="text" name="transBeginDate"
								value="${transBeginDate}" />格式: yyyyMMdd (<font style="color: #8E8E8E ">支持多文件批量导入,按格式填写第一份导入文件名中的日期</font>)</td>
							<td rowspan="2" class="data"><input class="dark-btn" type="button"
								value="导入文件" class="submit" onclick="ftpTransLogSubmit();" /></td>
						</tr>
						<tr>
							<td class="label">交易记录文件实时导入(结束)</td>
							<td class="data"><input type="text" name="transEndDate"
								value="${transEndDate}" />格式: yyyyMMdd (<font style="color: #8E8E8E ">支持多文件批量导入,按格式填写第一份导入文件名中的日期</font>)</td>
						</tr>
						<tr>
							<td class="label">装机撤机记录文件实时导入</td>
							<td class="data"><input type="text" name="installFileName"
								value="${installFileName}" />格式: yyyyMMdd (<font style="color: #8E8E8E ">按格式填写导入文件名中的日期,点击载入</font>)</td>
							<td class="data"><input class="dark-btn" type="button" value="导入文件"
								class="submit" onclick="ftpInstallLogSubmit();" /></td>
						</tr>
						<!--  
						<tr>
							<td class="label">交易流水月份汇总开始月份</td>
							<td class="data"><input type="text" name="transMouthBeginDate"
								value="${transMouthBeginDate}" />&nbsp;格式: yyyyMM</td>
							<td rowspan="2"><input class="dark-btn" type="button"
								value="載入" class="submit" onclick="genTransLogMouth();" /></td>
						</tr>
						<tr>
							<td class="label">交易流水月份汇总结束月份</td>
							<td class="data"><input type="text" name="transMouthEndDate"
								value="${transMouthEndDate}" />&nbsp;格式: yyyyMM</td>
						</tr>
						-->
						<tr>
							<td class="label">实时更新订单逾期状态</td>
							<td class="data">&nbsp;手工扫描系统上截至到当前,那些订单已经超过有效期，自动更改状态.<br>(<font style="color: #8E8E8E ">系统每天自动执行一次，异常时才需执行此操作</font>)</td>
							<td class="data"><input class="dark-btn" type="button" value="导入文件"
								class="submit" onclick="loanTrace();" /></td>
						</tr>
						<tr>
							<td class="label">实时更新机构后台统计报表</td>
							<td class="data">&nbsp;手工实时更新机构后台首页的统计数据报表.<br>(<font style="color: #8E8E8E ">系统每天自动执行一次，异常时才需执行此操作</font>)</td>
							<td class="data"><input class="dark-btn" type="button" value="导入文件"
								class="submit" onclick="loanStatistics();" /></td>
						</tr>
						<!-- 
						<tr>
							<td class="label">执行当前订单统计定时器</td>
							<td class="data">&nbsp;手工执行当前订单数统计.(定时器会自动运行,异常时手工执行)</td>
							<td><input class="dark-btn" type="button" value="运行"
								class="submit" onclick="currentStatistics();" /></td>
						</tr>
						 -->
					</table>
				</form>
			</div>
			<div align="center" style="margin-left:70px;width: 80%;"><h2>导入操作结果</h2></div>
			<div align="right" style="width: 80%; height: 300px;">
				<iframe id="runFrame" name="runFrame" align="top" height="200px"
					width="80%" frameborder="1"></iframe>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function setAction(url) {
			$('form').attr("action", url);
			$('form').submit();
		}

		function genTransLogMouth() {
			setAction("${basepath}/backstage/transmanage/transmanage!genTransLogMouth");
		}

		function ftpMerchBaseSubmit() {
			setAction("${basepath}/backstage/transmanage/transmanage!ftpMerchBase");
		}

		function ftpInspectLogSubmit() {
			setAction("${basepath}/backstage/transmanage/transmanage!ftpInspectLog");
		}

		function ftpTransLogSubmit() {
			setAction("${basepath}/backstage/transmanage/transmanage!ftpTransLog");
		}

		function ftpInstallLogSubmit() {
			setAction("${basepath}/backstage/transmanage/transmanage!ftpInstallLog");
		}
		function loanTrace() {
			runFrame.location.href = "${basepath}/backstage/transmanage/transmanage!loanTrace";
		}

		function loanStatistics() {
			runFrame.location.href = "${basepath}/backstage/transmanage/transmanage!loanStatistics";
		}

		function currentStatistics() {
			runFrame.location.href = "${basepath}/backstage/transmanage/transmanage!statisticsCurrent";
		}
	</script>
</body>
</html>
