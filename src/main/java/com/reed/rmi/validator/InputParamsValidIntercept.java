package com.reed.rmi.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;

import com.reed.rmi.validator.groups.ValidatorGroup4Add;
import com.reed.rmi.validator.groups.ValidatorGroup4Update;

/**
 * 用于校验输入参数的拦截器 暂只支持定义的Bean中annotation校验方式，直接在参数中声明验证的方式不支持（如 execute(@NotBlank
 * String name ....)） 可对annotation-valid进行分组，验证时验证指定分组
 * 
 * @author reed
 * 
 * 
 */
public class InputParamsValidIntercept {

	private static ValidatorFactory validatorfactory;

	static {
		validatorfactory = Validation.buildDefaultValidatorFactory();
	}

	/**
	 * 验证方法，验证不通过会抛出异常
	 * 
	 * @param joinPoint
	 * @throws Exception
	 */
	public void valid(JoinPoint joinPoint) throws Exception {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		if (null != args) {
			// groups 根据methodName设定具体的validGroup
			Class<?> validGroup = null;
			if (methodName.startsWith("save")) {
				validGroup = ValidatorGroup4Add.class;
			} else if (methodName.startsWith("update")) {
				validGroup = ValidatorGroup4Update.class;
			} else {
				validGroup = Default.class;
			}
			// valid 根据validGroup验证参数并获取验证结果
			StringBuffer errMsg = new StringBuffer();
			for (Object arg : args) {
				if (null != arg) {
					this.validWraperParam(arg, validGroup, errMsg);
				}
			}
			// exception errMsg非空抛出异常
			if (StringUtils.isNotBlank(errMsg.toString())) {
				throw new Exception(errMsg.toString());
			}
		}

	}

	/**
	 * 校验参数并将校验结果写入errMsg中
	 * 
	 * @param arg
	 * @param validGroup
	 * @param errMsg
	 */
	private void validWraperParam(Object arg, Class<?> validGroup,
			StringBuffer errMsg) {
		Validator validator = validatorfactory.getValidator();
		Set<ConstraintViolation<Object>> validResults = validator.validate(arg,
				validGroup);
		if (null != validResults) {
			for (ConstraintViolation<Object> violt : validResults) {
				if (null != violt) {
					if (null != violt.getMessage()) {
						errMsg.append(violt.getPropertyPath().toString())
								.append(violt.getMessage()).append(",");
					}
				}
			}
		}
	}

}
