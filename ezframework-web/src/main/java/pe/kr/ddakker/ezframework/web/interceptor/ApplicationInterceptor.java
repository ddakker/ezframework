package pe.kr.ddakker.ezframework.web.interceptor;

import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pe.kr.ddakker.ezframework.web.GlobalsProperties;
import pe.kr.ddakker.ezframework.web.servlet.view.excel.ExcelView;
import pe.kr.ddakker.ezframework.web.vo.Paging;


/**
 * 일반적인 공통 처리 인터셉터
 * @author ddakker
 *
 */
public class ApplicationInterceptor extends HandlerInterceptorAdapter {
	private Logger log = LoggerFactory.getLogger(ApplicationInterceptor.class);

	@Resource
	private GlobalsProperties globalsProperties;

	/*@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return super.preHandle(request, response, handler);
	}*/

	@SuppressWarnings("rawtypes")
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			if (org.apache.commons.lang.StringUtils.indexOf(request.getHeader("accept"), "text/html")  == -1) {
				modelAndView.addObject(globalsProperties.getProperty("content.key.result.code"), globalsProperties.getProperty("content.value.success"));
				modelAndView.addObject(globalsProperties.getProperty("content.key.result.message"), "");
			}

			/**
			 *  엑셀 다운로드일 경우 jsp처리 대신 ExcelView로 이동
			 */
			if ( request != null && "true".equals(request.getParameter("excelFlag")) ) {
				if ( modelAndView.getModel().get(ExcelView.EXCEL_DATA) == null ) {
					modelAndView.getModel().put(ExcelView.EXCEL_DATA, request.getParameter("excelData"));
					modelAndView.getModel().put(ExcelView.EXCEL_WIDTH, request.getParameter("excelWidth"));
					modelAndView.getModel().put(ExcelView.EXCEL_ALIGN, request.getParameter("excelAlign"));

					String modelkey = "";
					Iterator iter = modelAndView.getModel().keySet().iterator();
					while (iter.hasNext()) {
						modelkey = iter.next().toString();
						Class cls = Class.forName(modelAndView.getModel().get(modelkey).getClass().getName());

						if ("pe.kr.ddakker.ezframework.tag.servlet.jsp.pagination.Paging".equals(cls.getName())) {
							break;
						}
					}
					modelAndView.getModel().put(ExcelView.EXCEL_VALUE, ((Paging) modelAndView.getModel().get(modelkey)).getList() );
				}

				modelAndView.setViewName("excelView");
			} // end if

		}
	}
}