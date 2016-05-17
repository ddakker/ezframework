package org.ezdevgroup.ezframework.web.servlet.view.json;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

public class MappingJsonpView extends AbstractView {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public MappingJsonpView() {
		setContentType("text/javascript");
		setExposePathVariables(false);
	}

	@Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> outputMap = new HashMap<String, Object>();
		Iterator<String> it = model.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.indexOf("org.springframework.validation.BindingResult") == -1) {
				outputMap.put(key, model.get(key));
			}

		}
		String callback = request.getParameter("callback");
		String json = objectMapper.writeValueAsString(outputMap);

		Writer out = response.getWriter();
		out.append(callback).append("(").append(json).append(")");
		out.close();
    }
}
