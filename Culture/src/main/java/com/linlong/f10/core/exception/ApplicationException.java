package com.linlong.f10.core.exception;

import org.slf4j.helpers.MessageFormatter;

import com.linlong.f10.core.exception.cache.ExceptionCodeCache;

/**
 * 
 * @ClassName: ApplicationException 
 * @Description: 大平台系统自定义异常 
 * @author nisicong
 * @date 2015年6月3日 下午2:03:58 
 *
 */
public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 8825888944174426639L;
	private String errorCode;
	private String[] errorInfoParameters;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String[] getErrorInfoParameters() {
		return errorInfoParameters;
	}
	public void setErrorInfoParameters(String[] errorInfoParameters) {
		this.errorInfoParameters = errorInfoParameters;
	}
	
	public ApplicationException() {
	}
	
	public ApplicationException(String errorCode) {
		this(errorCode, (String[]) null);
	}

	public ApplicationException(String errorCode, String[] errorInfoParameters) {
		this(errorCode, errorInfoParameters, null);
	}
	
	public ApplicationException(String errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public ApplicationException(String errorCode, String[] errorInfoParameters,
			Throwable cause) {
		super(MessageFormatter.arrayFormat(
				ExceptionCodeCache.getExceptionCache(errorCode).getMessage(),
				errorInfoParameters).getMessage(), cause);
		this.errorCode = errorCode;
		this.errorInfoParameters = errorInfoParameters;
	}
}
