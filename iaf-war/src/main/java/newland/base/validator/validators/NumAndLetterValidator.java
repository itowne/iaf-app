/*
 * NumOrLetterValidator.java
 * 
 * 功能：
 * 类名:NumOrLetterValidator
 * 
 *   ver     变更日期    修改人    修改说明
 * ──────────────────────────────────
 *   V1.0   2011-3-4    黄瑞斌       初版
 * 
 * Copyright (c) 2006, 2010 NewlandComputer All Rights Reserved.
 * LICENSE INFORMATION
 */
package newland.base.validator.validators;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.RegexFieldValidator;

/**
 *
 * @author 黄瑞斌
 */
public class NumAndLetterValidator extends RegexFieldValidator {

    public void validate(Object object) throws ValidationException {
        super.setExpression("/^(([a-z]+[0-9]+)|([0-9]+[a-z]+))[a-z0-9]*$/i");
        super.validate(object);
    }
}
