/*
 * ValidatorType.java
 * 
 * 功能：
 * 类名:ValidatorType
 * 
 *   ver     变更日期    修改人    修改说明
 * ──────────────────────────────────
 *   V1.0   2011-3-4    黄瑞斌       初版
 * 
 * Copyright (c) 2006, 2010 NewlandComputer All Rights Reserved.
 * LICENSE INFORMATION
 */

package newland.base.validator;

/**
 *
 * @author 黄瑞斌
 */
public interface ValidatorType {

    public static final String ONLY_NUMBER ="onlyNumber";
    public static final String OPTIONAL ="optional";
    public static final String NUM_OR_LETTER ="numOrLetter";
    public static final String NUM_AND_LETTER="numAndLetter";
    public static final String REQUIRED_FIELD ="requiredField";
    public static final String EMAIL = "email";
}
