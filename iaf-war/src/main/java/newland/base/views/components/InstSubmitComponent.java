package newland.base.views.components;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import newland.iaf.base.service.IafSession;
import newland.iaf.base.service.impl.IafConsoleSession;
import newland.iaf.base.service.impl.InstSession;
import newland.iaf.base.servlet.SessionFilter;

import org.apache.struts2.components.FormButton;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

@StrutsTag(
	    name="submit",
	    tldTagClass="newland.base.views.tags.InstSubmitTag",
	    description="Render a submit button",
	    allowDynamicAttributes=true)
public class InstSubmitComponent extends FormButton{

	 private static final Logger LOG = LoggerFactory.getLogger(InstSubmitComponent.class);
	    final public static String OPEN_TEMPLATE = "submit";
	    final public static String TEMPLATE = "submit-close";
	    protected String src;
	    protected String authCode;

	    public InstSubmitComponent(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
	        super(stack, request, response);
	    }

	    public String getDefaultOpenTemplate() {
	        return OPEN_TEMPLATE;
	    }

	    protected String getDefaultTemplate() {
	        return TEMPLATE;
	    }

	    public void evaluateParams() {
	        if ((key == null) && (value == null)) {
	            value = "Submit";
	        }

	        if (((key != null)) && (value == null)) {
	            this.value = "%{getText('"+key +"')}";
	        }

	        super.evaluateParams();
	    }

	    public void evaluateExtraParams() {
	        super.evaluateExtraParams();
	        HttpSession sess = request.getSession();
			InstSession instSess = (InstSession) (IafSession) sess
					.getAttribute(SessionFilter.IAF_LOGIN_SESSION);
			if(!instSess.hasInstAuth(authCode)){
				addParameter("disabled",true);
				addParameter("cssClass","dark-btn-disable");
			}
			if(id!=null){
				addParameter("id",id);
			}

	        if (src != null)
	            addParameter("src", findString(src));
	    }

	    /**
	     * Indicate whether the concrete button supports the type "image".
	     *
	     * @return <tt>true</tt> to indicate type image is supported.
	     */
	    protected boolean supportsImageType() {
	        return true;
	    }

	    @StrutsTagAttribute(description="Supply an image src for <i>image</i> type submit button. Will have no effect for types <i>input</i> and <i>button</i>.")
	    public void setSrc(String src) {
	        this.src = src;
	    }


	    @Override
	    public boolean usesBody() {
	        return true;
	    }

	    /**
	     * Overrides to be able to render body in a template rather than always before the template
	     */
	    public boolean end(Writer writer, String body) {
	        evaluateParams();
	        try {
	            addParameter("body", body);

	            mergeTemplate(writer, buildTemplateName(template, getDefaultTemplate()));
	        } catch (Exception e) {
	            LOG.error("error when rendering", e);
	        }
	        finally {
	            popComponentStack();
	        }

	        return false;
	    }

	    @StrutsTagAttribute(description="HTML form authCode attribute")
		public void setAuthCode(String authCode) {
			this.authCode = authCode;
		}

		public String getAuthCode() {
			return authCode;
		}
}
