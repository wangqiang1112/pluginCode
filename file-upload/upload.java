using System;  
using System.Collections.Generic;  
using System.Linq;  
using System.IO;  
using System.Net;  
using System.Web;  
using System.Web.Services;  
namespace fupload  
{  
    /// <summary>  
    /// Handler1 ��ժҪ˵��  
    /// </summary>  
    public class Handler1 : IHttpHandler  
    {  
   
        public void ProcessRequest(HttpContext context)  
        {  
            context.Response.ContentType = "text/plain";  
   
            HttpPostedFile file = context.Request.Files["Filedata"];//�Կͻ����ļ��ķ���  
   
            string uploadPath = HttpContext.Current.Server.MapPath(@context.Request["folder"])+"\\";//���������ļ�����·��  
   
            if (file != null)  
            {  
                if (!Directory.Exists(uploadPath))  
                {  
                    Directory.CreateDirectory(uploadPath);//����������ļ���  
                }  
   
                file.SaveAs(uploadPath + file.FileName);//�����ļ�  
                context.Response.Write("�ϴ��ɹ�");  
            }  
   
            else  
            {  
                context.Response.Write("0");  
            }  
   
        }  
   
        public bool IsReusable  
        {  
            get  
            {  
                return false;  
            }  
        }  
    }  
}


/**
ǰ̨�����ϴ���С��������Ӧʱ��
<span style="font-size:18px;"><system.web>  
      <compilation debug="true" targetFramework="4.0" />  
    <httpRuntime maxRequestLength="20480000" executionTimeout ="7200"/>  
    </system.web></span>  


��̨�����ϴ���С
<span style="font-size:18px;"><system.webServer>  
    <security>  
    <requestFiltering>  
      <requestLimits maxAllowedContentLength="999999999"></requestLimits>  
    </requestFiltering>       
    </security>  
  </system.webServer></span>  

*/