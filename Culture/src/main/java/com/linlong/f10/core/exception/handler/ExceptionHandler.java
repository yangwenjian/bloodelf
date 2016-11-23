package com.linlong.f10.core.exception.handler;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.linlong.f10.core.exception.ApplicationException;
import com.linlong.f10.core.exception.cache.ExceptionCodeCache;

/**
 * 
 * @ClassName: ExceptionHandler
 * @Description: 公共异常处理类
 * @author nisicong
 * @date 2015年6月3日 下午2:02:53
 *
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private final static Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		String errorMessage = null;
		if (exception instanceof ApplicationException) {
			errorMessage = MessageFormatter.arrayFormat(
					ExceptionCodeCache.getExceptionCache(((ApplicationException) exception).getErrorCode())
							.getMessage(), ((ApplicationException) exception).getErrorInfoParameters()).getMessage();
			log.error(errorMessage, exception);
		} else {
			errorMessage = "未知的异常信息";
			log.error(errorMessage, exception);
		}

		// 输出流写出
		OutputStream os = null;
		try {
			response.setContentType("text/plain;charset=utf-8");
			os = response.getOutputStream();
		} catch (IOException e) {
			log.error("获取输出流失败", e);
		}
		try {
			os.write("未知的异常信息，请联系管理员!\n".getBytes("utf-8"));
		} catch (IOException e) {
			log.error("写入输出流失败", e);
			try {
				os.close();
			} catch (IOException e1) {
				log.error("关闭输出流失败", e1);
			}
		}
		return null;
		// 将异常写出到指定页面，这里指定的异常页面为：webapp/WEB-INF/pages/system/error.html
		/*
		 * Map<String,String> map = new HashMap<String,String>();
		 * map.put("errorMsg", errorMessage); return new
		 * ModelAndView("system/error",map);
		 */
	}
}
