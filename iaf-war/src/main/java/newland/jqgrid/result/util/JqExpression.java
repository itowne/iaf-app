package newland.jqgrid.result.util;

import newland.base.formater.Formatter;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 *
 * @author 黄瑞斌
 */
public class JqExpression {

    private Object data;
    private ActionInvocation actionInvocation;
    private ActionSupport action;

    public JqExpression(Object data, ActionInvocation actionInvocation) {
        this.data = data;
        this.actionInvocation = actionInvocation;
        action = (ActionSupport) actionInvocation.getAction();
    }

    public JqExpression(ActionInvocation actionInvocation) {
        this.actionInvocation = actionInvocation;
        action = (ActionSupport) actionInvocation.getAction();

    }

    public ActionInvocation getActionInvocation() {
        return actionInvocation;
    }

    public void setActionInvocation(ActionInvocation actionInvocation) {
        this.actionInvocation = actionInvocation;
        action = (ActionSupport) actionInvocation.getAction();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    // eg o:xxx
    private Object oExp(String str) throws OgnlException {
        Object[] obj = evalArgs(str);
        OgnlContext context = new OgnlContext();
        context.setRoot(data);
        return Ognl.getValue(Ognl.parseExpression(obj[0].toString()), context, context.getRoot());
    }

    public Object eval(String str) throws OgnlException {
        if (str.startsWith("{")) {
            str = decodeExpr(str);
            String[] args = decodeExprArgs(str);
            if (args[0].equals("o")) {
                return oExp(args[1]);
            }
            if (args[0].equals("i")) {
                return iExp(args[1]);
            }
            if (args[0].equals("f")) {
                return fExp(args[1]);
            }
            return decodeExpr(str);
        } else {
            return str;
        }
    }
    //i:{f:{fname,{o:kk}}}
    //i:{money,{o:money}}
    //i:dd

    private String iExp(String str) throws OgnlException {
        try {
            Object[] obj = evalArgs(str);
            if (obj.length > 0) {
                List args = new ArrayList();
                for (int i = 1; i < obj.length; i++) {
                    args.add(obj[i]);
                }
                return action.getText(obj[0].toString(), args);
            }
        } catch (Exception ex) {
            return "";
        }
        return "";
    }
    //f:{fname,{o:cc}}

    private String fExp(String str) throws OgnlException {
        try {
            Object[] obj = evalArgs(str);
            Formatter formatter = (Formatter) actionInvocation.getStack().findValue(obj[0].toString());
            return formatter.format(obj[1]);
        } catch (Exception ex) {
            return "";
        }

    }

    private String decodeExpr(String str) {
        return str.substring(1, str.length() - 1);
    }

    private String[] decodeExprArgs(String str) {
        String[] args = new String[2];
        args[0] = str.substring(0, str.indexOf(":"));
        args[1] = str.substring(str.indexOf(":") + 1);
        return args;
    }

    private String[] decodeFunArgs(String str) {
        List<String> list = new ArrayList<String>();
        if (str.startsWith("(")) {
            char[] charArgs = str.toCharArray();
            int pos = 0;
            int begin = 0;
            int end = 0;
            for (int i = 0; i < charArgs.length; i++) {
                if (charArgs[i] == '(') {
                    if (pos == 0) {
                        begin = i;
                    }
                    pos = pos + 1;
                }
                if (charArgs[i] == ')') {
                    pos = pos - 1;
                    if (pos == 0) {
                        list.add(str.substring(begin + 1, i));
                    }
                }

            }
        } else {
            list.add(str);
        }
        return list.toArray(new String[0]);
    }

    private Object[] evalArgs(String str) throws OgnlException {
        String[] fargs = decodeFunArgs(str);
        Object[] obj = new Object[fargs.length];
        for (int i = 0; i < fargs.length; i++) {
            obj[i] = eval(fargs[i]);
        }
        return obj;
    }
}
