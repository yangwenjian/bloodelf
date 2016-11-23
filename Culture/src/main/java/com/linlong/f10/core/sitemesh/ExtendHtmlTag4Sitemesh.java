package com.linlong.f10.core.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * Created on   2015年7月8日
 * Title:       麟龙大平台_[投顾CMS]_[公共]
 * Description: [根据项目页面布局设计需求，sitemesh3自定义标签,用于sitemesh3模板 动态加载 被装饰页面内指定标签中的内容]
 * Copyright:   Copyright (c) 2015
 * Company:     麟龙科技股份有限公司
 * Department:  研发部
 * @author:     suliang
 * @version:    1.0
*/
public class ExtendHtmlTag4Sitemesh implements TagRuleBundle {

	/**
	 * Created on 2015年7月8日
	 * Discription:[自定义标签]
	 * @param defaultState
	 * @param contentProperty
	 * @param siteMeshContext
	 * @author: suliang
	 * @update: 2015年7月8日 下午1:59:31
	 */
	@Override
	public void install(State defaultState, ContentProperty contentProperty,
			SiteMeshContext siteMeshContext) {
		// 被装饰页面顶端主标题标签
		defaultState.addRule("headline", new ExportTagToContentRule(siteMeshContext,contentProperty.getChild("headline"), false));
		// 被装饰页面顶端，副标题导航
		defaultState.addRule("subheadline", new ExportTagToContentRule(siteMeshContext,contentProperty.getChild("subheadline"), false));
	}
	
	/**
	 * Created on 2015年7月8日
	 * Discription:[cleanUp]
	 * @param defaultState
	 * @param contentProperty
	 * @param siteMeshContext
	 * @author: linlong
	 * @update: 2015年7月8日 下午2:00:46
	 */
	@Override
	public void cleanUp(State defaultState, ContentProperty contentProperty,
			SiteMeshContext siteMeshContext) {
	}
}
