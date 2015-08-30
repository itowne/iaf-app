<#include "default_style.ftl" >
<body>
	<h3 style="line-height:32px;text-align:center;font-size:15px;">商户基本信息</h3>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="report-table">
		<#if merch?exists>
		<tr>
			<td class="label">商户名称</td>
			<td class="data">
			 <#if merch.merchName?exists>
			${merch.merchName}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">商户行业类别</td>
			<td class="data">
			 <#if industry?exists>
			${industry}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if></td>
		</tr>
		<tr>
			<td class="label">注册地址</td>
			<td class="data">
			 <#if merch.regAddr?exists>
			${merch.regAddr}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">机具装机地址</td>
			<td class="data">
			 <#if merch.posAddr?exists>
			${merch.posAddr}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
		</tr>
		<tr>
			<td class="label">注册时间</td>
			<td class="data">
			 <#if merch.regTime?exists>
			${merch.regTime?string('yyyy年MM月dd日')}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">注册资本</td>
			<td class="data">￥
			 <#if merch.regCap?exists>
			${merch.regCap}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if></td>
		</tr>
		<tr>
			<td class="label">营业执照号</td>
			<td class="data">
			 <#if merch.businlic?exists>
			${merch.businlic}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">税务登记号</td>
			<td class="data">
			 <#if merch.taxReg?exists>
			${merch.taxReg}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
		</tr>
		<tr>
			<td class="label">开业时间</td>
			<td class="data">
			 <#if merch.openTime?exists>
			${merch.openTime?string('yyyy年MM月dd日')}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">公司规模</td>
			<td class="data">
			 <#if merch.companySize?exists>
			${merch.companySize}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if></td>
		</tr>
		<tr>
			<td class="label">联系人</td>
			<td class="data">
			 <#if merch.contract?exists>
			${merch.contract}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">联系电话</td>
			<td class="data">
			 <#if merch.contractTel?exists>
			${merch.contractTel}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
		</tr>
		<tr>
			<td class="label">QQ号</td>
			<td class="data">
			 <#if merch.qqUid?exists>
			${merch.qqUid}
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if>
			</#if>
			</td>
			<td class="label">&nbsp;</td>
			<td class="data">&nbsp;
			</td>
		</tr>
		<#else>
		<tr><td class="label">商户名称</td>	<td class="data">--</td><td class="label">商户行业类别</td><td class="data">--<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if></td></tr>
		<tr><td class="label">注册地址</td><td class="data">--</td><td class="label">机具装机地址</td><td class="data">	--<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if></td></tr>
		<tr><td class="label">注册时间</td><td class="data">--</td><td class="label">注册资本</td><td class="data">￥--<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if></td></tr>
		<tr><td class="label">营业执照号</td><td class="data">--</td><td class="label">税务登记号</td><td class="data">--<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if></td></tr>
		<tr><td class="label">开业时间</td><td class="data">--</td><td class="label">公司规模</td><td class="data">--<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if></td></tr>
		<tr><td class="label">联系人</td><td class="data">--</td><td class="label">联系电话</td><td class="data">--<#if merch.merchType=="GENERAL">
			<font style="color:red">(未审核)</font>
			</#if></td></tr>
		</#if>
		<#if legalPers?exists>
		<tr>
			<td class="label">法定代表人情况</td>
			<td class="data embed" colspan="3">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="label">姓名</td>
						<td class="data">
						 <#if legalPers.legalPersName?exists>
						${legalPers.legalPersName}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
						<td class="label" >性别</td>
						<td class="data" >
						 <#if legalPers.sex?exists>
						${legalPers.sex.desc}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
						<td class="label" >年龄</td>
						<td class="data" >
						 <#if legalPers.age?exists>
						${legalPers.age}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
					</tr>
					<tr>
						<td class="label">身份证号码</td>
						<td class="data">
						 <#if legalPers.cerdNo?exists>
						${legalPers.cerdNo}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
						<td class="label" >婚姻状况</td>
						<td class="data" >
						 <#if legalPers.maritalStatus?exists>
						${legalPers.maritalStatus.desc}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
						<td class="label" >学历</td>
						<td class="data" >
						 <#if legalPers.eduBackground?exists>
						${legalPers.eduBackground.desc}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
					</tr>
					<tr>
						<td class="label" >办公电话</td>
						<td class="data" >
						 <#if legalPers.officePhone?exists>
						${legalPers.officePhone}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
						<td class="label" >手机</td>
						<td class="data" >
						 <#if legalPers.mobiPhone?exists>
						${legalPers.mobiPhone}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
						<td class="label" >家庭住址</td>
						<td class="data" >
						 <#if legalPers.faAddr?exists>
						${legalPers.faAddr}
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						<#else>
						--
						<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
						</#if>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<#else>
		<tr>
			<td class="label">法定代表人情况</td><td class="data embed" colspan="3">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td class="label">姓名</td>
					<td class="data">--
					<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
					</td>
					<td class="label" style="color:blue;">性别</td>
					<td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td>
					<td class="label" style="color:blue;">年龄</td>
					<td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td></tr>
					<tr><td class="label">身份证号码</td>
					<td class="data">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td>
					<td class="label" style="color:blue;">婚姻状况</td>
					<td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td>
					<td class="label" style="color:blue;">学历</td>
					<td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td></tr>
					<tr><td class="label" style="color:blue;">办公电话</td>
					<td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td>
					<td class="label" style="color:blue;">手机</td>
					<td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td>
					<td class="label" style="color:blue;">家庭住址</td><td class="data" style="color:blue;">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td></tr>
				</table>
			</td>
		</tr>
		</#if>
		<#if busiInfo?exists>
		<tr>
			<td class="label">结算银行</td>
			<td class="data" colspan="3">
			<#if busiInfo.bank?exists>
			${busiInfo.bank}
			<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
			</#if>
			</td>
		</tr>
		<tr>
			<td class="label">贷款资金划入账号</td>
			<td class="data" colspan="3">
			 <#if busiInfo.accountNo?exists>
			${busiInfo.accountNo}
			<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
			<#else>
			--
			<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
			</#if>
			</td>
		</tr>
		<tr>
			<td class="label">商户性质</td>
			<td class="data" colspan="3">
			 <#if busiInfo.merchNatrue?exists>
			 <#if busiInfo.merchNatrue=="国营">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			国营
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			国营
			</#if>
			<#if busiInfo.merchNatrue=="集体">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			集体
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			集体
			</#if>
			<#if busiInfo.merchNatrue=="私营">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			私营
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			私营
			</#if>
			<#if busiInfo.merchNatrue=="个体工商户">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			个体工商户
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			个体工商户
			</#if>
			<#if busiInfo.merchNatrue=="合资">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			合资
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			合资
			</#if>
			<#if busiInfo.merchNatrue=="股份制">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			股份制
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			股份制
			</#if>
			<#if busiInfo.merchNatrue!="国营"&&busiInfo.merchNatrue!="集体"&&busiInfo.merchNatrue!="私营"&&busiInfo.merchNatrue!="个体工商户"&&busiInfo.merchNatrue!="合资"&&busiInfo.merchNatrue!="股份制">
			 <input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			其他
			<#else>
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			其他
			</#if>
			<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
			<#else>
				<input type="checkBox" name="checkbox" disabled="disabled"/>
			国营
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			集体
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			私营
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			个体工商户
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			合资
			<input type="checkBox" name="checkbox" disabled="disabled"/>
			股份制
			<input type="checkBox" name="checkbox" checked="checked" disabled="disabled"/>
			其他
			<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if>
			</#if>
			</td>
		</tr>
		<#else>
		<tr><td class="label">结算银行</td><td class="data" colspan="3">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td></tr>
		<tr><td class="label">贷款资金划入账号</td><td class="data" colspan="3">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td></tr>
		<tr><td class="label">商户性质</td><td class="data" colspan="3">--<#if merch.merchType=="GENERAL">
						<font style="color:red">(未审核)</font>
						</#if></td></tr>
		</#if>
	</table>
</body>
</html>