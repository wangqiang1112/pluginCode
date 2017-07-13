//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.htjs.platform.web.tools;

import com.opensymphony.xwork2.ActionContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.htjs.platform.domain.exception.NoSuchBeanException;
import net.htjs.platform.domain.tools.BeansHelp;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class JsonHelp extends BeansHelp {
    private static Logger a;
    private static ApplicationContext b;
    public static int YEAR;
    public static int MONTH;
    public static int DAY;
    private static Class c;
    private static Class d;
    private static Class e;

    public JsonHelp() {
    }

    public static ApplicationContext getInstanceCtx() {
        if(b == null) {
            showInfo();
            a.info("从web.xml容器中加载" + mapProperties.get("SPRING_CNF_CLIENT") + "...");
            b = new ClassPathXmlApplicationContext((String)mapProperties.get("SPRING_CNF_CLIENT"));
            a.info("加载完毕.");
        }

        return b;
    }

    public static void showInfo() {
        if(b == null) {
            Properties var0 = new Properties();

            try {
                var0.load((d == null?(d = a("net.htjs.platform.domain.tools.BeansHelp")):d).getResourceAsStream("/htjs-ptcore3-client.properties"));
                a.info("加载平台主配置文件:htjs-ptcore3-client.properties");
                mapProperties.clear();
                Enumeration var1 = var0.propertyNames();

                while(var1.hasMoreElements()) {
                    String var2 = (String)var1.nextElement();
                    String var3 = var0.getProperty(var2);
                    mapProperties.put(var2, var3);
                }
            } catch (Exception var4) {
                a.error(var4.getMessage());
            }
        }

        String var5;
        if((var5 = (String)mapProperties.get("PROPER_FILES")) != null && var5.trim().length() > 0) {
            String[] var6 = var5.split(",");

            for(int var8 = 0; var8 < var6.length; ++var8) {
                a.info("加载平台构建项目的配置文件:" + var6[var8]);
                mapProperties = (HashMap)fillProperNew(var6[var8], mapProperties);
            }
        }

        StringBuffer var7;
        (var7 = new StringBuffer()).append("\n\t\t\t基础系统架构 Ver2.0 Date:2012.3\n").append("\t--------------------------------------------------\n").append("\t\t部署环境:\n").append("\t\t\t中文环境(unix/liunx)设置 export lang=zh_CN\n").append("\t\t\tJDK1.4.x servlet2.4 jsp2.0\n").append("\t\t系统架构:\n").append("\t\t\tJavaEncode:").append(BeansHelp.getPropertiesByKey("JAVA_CHARSET")).append("\n").append("\t\t\tweb层:struts2 Spring2.5.6 jQuery1.4 pageEncode:UTF-8\n").append("\n\n\t\t\t\t\t\t*工程技术中心 杨红建*\n").append("\t\t\t\t河南航天电子有限公司\n").append("\t--------------------------------------------------\n");
        a.info(var7.toString());
    }

    public static Object getBean(String var0) throws NoSuchBeanException {
        if(getInstanceCtx().containsBeanDefinition(var0)) {
            Object var1 = getInstanceCtx().getBean(var0);
            return var1;
        } else {
            a.info("beanID=" + var0 + "没有定义");
            throw new NoSuchBeanException(var0 + "没有定义!");
        }
    }

    public static Map getSession() {
        return ActionContext.getContext().getSession();
    }

    public static Object getSessionAttribute(String var0) {
        return getSession().get(var0);
    }

    public static void setSessionAttribute(String var0, Object var1) {
        getSession().put(var0, var1);
    }

    public static HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public static Cookie[] getCookies() {
        return getRequest().getCookies();
    }

    public static String getCookieVal(String var0) {
        Cookie[] var1;
        if((var1 = getCookies()) == null) {
            return "";
        } else {
            String var3 = "";

            for(int var4 = 0; var4 < var1.length; ++var4) {
                Cookie var2;
                if((var2 = var1[var4]).getName().equals(var0)) {
                    var3 = (var3 = var2.getValue()) != null?var3:"";
                }
            }

            return var3;
        }
    }

    public static void setCookie(String var0, String var1) {
        Cookie var2;
        (var2 = new Cookie(var0, var1)).setMaxAge(-1);
        var2.setPath("/");
        getResponse().addCookie(var2);
    }

    public static boolean delCookie(String var0) {
        Cookie[] var1;
        if((var1 = getCookies()) == null) {
            return true;
        } else {
            for(int var3 = 0; var3 < var1.length; ++var3) {
                Cookie var2;
                if((var2 = var1[var3]).getName().equals(var0)) {
                    var2.setValue((String)null);
                    var2.setPath("/");
                    var2.setMaxAge(0);
                    getResponse().addCookie(var2);
                    return true;
                }
            }

            return false;
        }
    }

    public static List createListByMap(Map var0, String var1) {
        ArrayList var2 = new ArrayList();
        String[] var9 = ((String)var0.get(var1)).split(",", -1);

        for(int var3 = 0; var3 < var9.length; ++var3) {
            HashMap var4 = new HashMap();
            Iterator var5 = var0.entrySet().iterator();

            while(var5.hasNext()) {
                Entry var6;
                String var7 = (String)(var6 = (Entry)var5.next()).getKey();
                String[] var8;
                String var10;
                if((var8 = (var10 = (String)var6.getValue()).split(",", -1)).length < var9.length) {
                    var4.put(var7, var10);
                } else {
                    var4.put(var7, var8[var3]);
                }
            }

            var2.add(var4);
        }

        return var2;
    }

    public static List createListByMap(Map var0) {
        ArrayList var1 = new ArrayList();
        String[] var2 = ((String)var0.get("XH")).split(",", -1);

        for(int var3 = 0; var3 < var2.length; ++var3) {
            HashMap var4 = new HashMap();
            Iterator var5 = var0.entrySet().iterator();

            while(var5.hasNext()) {
                Entry var6;
                String var7 = (String)(var6 = (Entry)var5.next()).getKey();
                String[] var8;
                String var9;
                if((var8 = (var9 = (String)var6.getValue()).split(",", -1)).length < var2.length) {
                    var4.put(var7, var9);
                } else if(var7.equals("XH")) {
                    var4.put(var7, var8[var3].length() == 0?"":var8[var3]);
                } else {
                    var4.put(var7, var8[var3]);
                }
            }

            var1.add(var4);
        }

        return var1;
    }

    public static Map getReqParamMap() {
        HashMap var0 = new HashMap();
        Entry var1 = null;
        Iterator var2 = getRequest().getParameterMap().entrySet().iterator();

        while(var2.hasNext()) {
            Object var3 = (var1 = (Entry)var2.next()).getKey();
            String[] var4 = (String[])var1.getValue();
            String var6 = "";

            for(int var5 = 0; var5 < var4.length; ++var5) {
                var6 = var6 + reconvert(var4[var5]) + ',';
            }

            var6 = var6.substring(0, var6.length() - 1);
            var0.put(var3, var6);
        }

        Object var7;
        if((var7 = getSessionAttribute("USERMAP")) != null && var7 instanceof Map) {
            Map var8 = (Map)var7;
            var0.put("LOG_USERID", var8.get("USERID"));
            var0.put("LOG_CZRY_DM", var8.get("CZRY_DM"));
            var0.put("LOG_ZZJG_DM", var8.get("ZZJG_DM"));
            var0.put("ACCOUNTID", var8.get("ACCOUNTID"));
            var0.put("SJQXFA", var8.get("SJQXFA"));
        }

        return var0;
    }

    public static String getReqParamVal(String var0) {
        Object var1 = new ArrayList();

        try {
            var1 = Arrays.asList((String[])getRequest().getParameterMap().get(var0));
        } catch (NullPointerException var3) {
            ;
        }

        var0 = "";

        for(int var2 = 0; var2 < ((List)var1).size(); ++var2) {
            var0 = var0 + (String)((List)var1).get(var2) + ',';
        }

        return var0.length() > 0?var0.substring(0, var0.length() - 1):var0;
    }

    public static void ajaxResponseTxt(int var0, String var1) {
        HashMap var2;
        (var2 = new HashMap()).put("data", "");
        ajaxResponseTxt(var0, var1, (Map)var2);
    }

    public static void ajaxResponseTxt(int var0, String var1, Map var2) {
        ArrayList var3;
        (var3 = new ArrayList()).add(var2);
        ajaxResponseTxt(var0, var1, (List)var3);
    }

    public static void ajaxResponseTxt(int var0, String var1, List var2) {
        HttpServletResponse var3;
        (var3 = getResponse()).setHeader("Charset", "UTF-8");
        var3.setHeader("Cache-Control", "no-cache");
        var3.setHeader("Expires", "0");
        var3.setHeader("Pragma", "No-cache");
        var3.setContentType("text/json;charset=UTF-8");
        HashMap var4;
        (var4 = new HashMap()).put("code", String.valueOf(var0));
        var4.put("msg", var1);
        JSONObject var9;
        (var9 = new JSONObject()).putAll(var4);
        Iterator var11 = var2.iterator();

        while(var11.hasNext()) {
            var9.putAll((Map)var11.next());
        }

        String var10 = var9.toString();
        var10 = (var1 = getReqParamVal("callback")).length() == 0?var10:var1 + "(" + var10 + ")";
        a.info(var10);
        getRequest().getHeader("Accept-Encoding");
        PrintWriter var12 = null;

        try {
            (var12 = var3.getWriter()).write(var10);
            var12.flush();
            return;
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            if(var12 != null) {
                var12.close();
            }

        }

    }

    public static void ajaxResponse(String var0) {
        HttpServletResponse var1;
        (var1 = getResponse()).setHeader("Charset", "UTF-8");
        var1.setHeader("Cache-Control", "no-cache");
        var1.setHeader("Expires", "0");
        var1.setHeader("Pragma", "No-cache");
        var1.setContentType("text/json;charset=UTF-8");
        String var2;
        var0 = (var2 = getReqParamVal("callback")).length() == 0?var0:var2 + "(" + var0 + ")";
        a.info(var0);

        try {
            PrintWriter var4;
            (var4 = var1.getWriter()).write(var0);
            var4.flush();
            var4.close();
        } catch (IOException var3) {
            a.error(var3.getMessage());
        }
    }

    public static String createJsonString(int var0, String var1) {
        return createJsonString(var0, var1, (Map)(new HashMap()));
    }

    public static String createJsonString(int var0, String var1, Map var2) {
        ArrayList var3;
        (var3 = new ArrayList()).add(var2);
        return createJsonString(var0, var1, (List)var3);
    }

    public static String createJsonString(int var0, String var1, List var2) {
        System.currentTimeMillis();
        HashMap var3;
        (var3 = new HashMap()).put("code", String.valueOf(var0));
        var3.put("msg", var1);
        var1 = "JSONObject";
        JSONObject var11;
        (var11 = new JSONObject()).putAll(var3);
        Iterator var7 = var2.iterator();

        while(var7.hasNext()) {
            var11.putAll((Map)var7.next());
        }

        long var13 = System.currentTimeMillis();
        String var12 = var11.toString();
        long var9 = System.currentTimeMillis();
        System.out.println("->JsonHelp(" + var1 + ")生成Json字符串:" + (var9 - var13) + "ms");
        return var12;
    }

    public static String createJsonStringBak(int var0, String var1, List var2) {
        JSONObject var3 = new JSONObject();
        HashMap var4;
        (var4 = new HashMap()).put("code", String.valueOf(var0));
        var4.put("msg", var1);
        var3.putAll(var4);
        Iterator var5 = var2.iterator();

        while(var5.hasNext()) {
            var3.putAll((Map)var5.next());
        }

        return var3.toString();
    }

    public static String getCurSkssqDate(int var0) {
        int var1 = Calendar.getInstance().get(1);
        int var2;
        if((var2 = Calendar.getInstance().get(2)) == 0) {
            --var1;
            var2 = 12;
        }

        String var3 = var2 < 10?"0" + String.valueOf(var2):String.valueOf(var2);
        var2 = daysInMonth(var1, var2);
        String var4;
        switch(var0) {
        case 1:
            var4 = String.valueOf(var1);
            break;
        case 2:
            var4 = var3;
            break;
        case 3:
            var4 = var2 < 10?"0" + String.valueOf(var2):String.valueOf(var2);
            break;
        case 4:
            var4 = var1 + var3;
            break;
        default:
            var4 = "";
        }

        return var4;
    }

    public static void main(String[] var0) {
        convert("中国");
        HashMap var2;
        (var2 = new HashMap()).put("code", (Object)null);
        var2.put("msg", "msg");
        var2.put("sd", (Object)null);
        JSONObject var1;
        (var1 = new JSONObject()).putAll(var2);
        System.out.println(var1.toString());
        String var3 = "{code:0,msg:\'good\',data:[{name:\'\',good:\'\'}]}";
        Object var4 = JSONObject.toBean(JSONObject.fromObject("{code:0,msg:\'good\',data:[{name:\'\',good:\'\'}]}"), e == null?(e = a("java.util.Map")):e);
        System.out.println(var4);
        System.out.println((List)((Map)var4).get("data"));
    }

    private static Class a(String var0) {
        try {
            return Class.forName(var0);
        } catch (ClassNotFoundException var1) {
            throw (new NoClassDefFoundError()).initCause(var1);
        }
    }

    static {
        a = Logger.getLogger(c == null?(c = a("net.htjs.platform.web.tools.JsonHelp")):c);
        b = null;
        YEAR = 1;
        MONTH = 2;
        DAY = 3;
    }
}
