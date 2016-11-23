package com.linlong.f10.core.globalPathSetting;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

/**
 * 
 * @ClassName: CllFreeMarkerView
 * @Description: 设定全局的路径变量，便于前台引用js，css，image等
 * @author suliang
 * @date 2015年6月3日 下午2:04:37
 *
 */
public class CustomFreeMarkerView extends FreeMarkerView {
	private static final String CONTEXT_PATH = "webRootPath";
	private static final String BASE_PATH = "basePath";
	private static final String STATIC_PATH = "staticPath";

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		String webRootPath = request.getContextPath();
		String port = "";
		if (80 == request.getServerPort() || 443 == request.getServerPort()) {
			port = "";
		} else {
			port = ":" + Integer.toString(request.getServerPort());
		}
		String basePath = request.getScheme() + "://" + request.getServerName() + port + webRootPath;
		Properties p = new Properties();
		String file = "conf/config.properties";
		try {
			InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
			// sce.getServletContext().getResourceAsStream("/WEB-INF/classes/conf/config.properties");
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.put(CONTEXT_PATH, webRootPath);
		model.put(BASE_PATH, basePath);
		model.put(STATIC_PATH, p.getProperty("static.path"));
		super.exposeHelpers(model, request);
	}

}
