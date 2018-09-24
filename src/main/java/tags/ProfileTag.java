package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public class ProfileTag extends BodyTagSupport {

	private String userDTO;
	private String login;

	public void setUserDTO(String userDTO) {
		this.userDTO = userDTO;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	@Override
	public int doStartTag() throws JspTagException {
		try{
			pageContext.getOut().print(login + "  "+ this.userDTO + "<br>");
			pageContext.getOut().print("<br>");
		}catch (IOException ex){
			throw new JspTagException(ex.getMessage());
		}
		return EVAL_BODY_INCLUDE;
	}

}
