/*package org.ezdevgroup.ezframework.web.servlet.view.xml;

import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

public class MappingXmlView extends AbstractView {

	public MappingXmlView() {
		setContentType("application/xml");
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
        ObjectMapper om = new ObjectMapper();
        String jsonStr = om.writeValueAsString(outputMap);

        XMLSerializer serializer = new XMLSerializer();
        JSON json = JSONSerializer.toJSON( jsonStr );
        serializer.setTypeHintsEnabled(false);
        serializer.setRootName("result");
        serializer.setTypeHintsEnabled(false);
        String xml = serializer.write( json );

        Writer out = response.getWriter();
        out.append(xml);
        out.close();
    }
}
*/