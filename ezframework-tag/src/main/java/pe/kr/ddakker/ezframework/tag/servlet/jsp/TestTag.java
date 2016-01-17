package pe.kr.ddakker.ezframework.tag.servlet.jsp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;


public class TestTag extends TagSupport {


	public static String js(String url) {
		return url;
	}

	private String value;

		/*public static String resource(String value){
			System.out.println("v1: " + value);
			return value;
		}*/

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		/*@Override
		public void doTag() throws JspException, IOException {
			System.out.println("doTag---------------------------");
			JspWriter writer = getJspContext().getOut();
			writer.write(value);
		}*/
		@Override
		public int doStartTag() throws JspException {
			System.out.println("doStartTag---------------------------");
			try {
				pageContext.getOut().print(value);
			} catch (IOException e) {
				throw new JspTagException("Error:  IOException while writing to the user");
			}
			return 1;
		}
}