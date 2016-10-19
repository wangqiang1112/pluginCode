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
    /// Handler1 的摘要说明  
    /// </summary>  
    public class Handler1 : IHttpHandler  
    {  
   
        public void ProcessRequest(HttpContext context)  
        {  
            context.Response.ContentType = "text/plain";  
   
            HttpPostedFile file = context.Request.Files["Filedata"];//对客户端文件的访问  
   
            string uploadPath = HttpContext.Current.Server.MapPath(@context.Request["folder"])+"\\";//服务器端文件保存路径  
   
            if (file != null)  
            {  
                if (!Directory.Exists(uploadPath))  
                {  
                    Directory.CreateDirectory(uploadPath);//创建服务端文件夹  
                }  
   
                file.SaveAs(uploadPath + file.FileName);//保存文件  
                context.Response.Write("上传成功");  
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
前台配置上传大小，设置响应时间
<span style="font-size:18px;"><system.web>  
      <compilation debug="true" targetFramework="4.0" />  
    <httpRuntime maxRequestLength="20480000" executionTimeout ="7200"/>  
    </system.web></span>  


后台配置上传大小
<span style="font-size:18px;"><system.webServer>  
    <security>  
    <requestFiltering>  
      <requestLimits maxAllowedContentLength="999999999"></requestLimits>  
    </requestFiltering>       
    </security>  
  </system.webServer></span>  

*/