<#--
/*
 * $Id: form-close-validate.ftl 720258 2008-11-24 19:05:16Z musachy $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<#--
START SNIPPET: supported-validators
Only the following validators are supported:
* required validator
* requiredstring validator
* stringlength validator
* regex validator
* email validator
* url validator
* int validator
* double validator
END SNIPPET: supported-validators
-->
<#if ((parameters.validate?default(false) == true) && (parameters.performValidation?default(false) == true))>
<script type="text/javascript">
  jQuery(document).ready(function() {
    <#list parameters.tagNames as tagName>
        <#list tag.getValidators("${tagName}") as validator>
            <#assign vclass="">
            <#if validator.validatorType = "required">
                 <#assign vclass="required"+vclass><#lt/>
            <#elseif validator.validatorType = "requiredstring">
<#lt/>
            <#elseif validator.validatorType = "stringlength">
           <#lt/>
            <#elseif validator.validatorType = "regex">
        <#lt/>
            <#elseif validator.validatorType = "email">
           <#lt/>
            <#elseif validator.validatorType = "url">
           <#lt/>
            <#elseif validator.validatorType = "int">
          <#lt/>
            <#elseif validator.validatorType = "double">
           <#lt/>
            </#if>
        <#lt/>
            <#if validator_has_next> <#assign vclass=vclass+"," >
            <#else>
                <#if (tag.getValidators("${tagName}")?exists && tag.getValidators("${tagName}")?size > 0) >
                    <#assign vclass="validate["+vclass+"]" >
                   <#rt/>  jQuery("input[name='${validator.fieldName}']").addClass("${vclass}");
                </#if>
            </#if>
        </#list>
    </#list>
                $("#${parameters.id}").validationEngine();
    });
</script>
</#if>
