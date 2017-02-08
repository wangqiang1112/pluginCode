<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/7
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件处理</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/uploadify.css">
    <script type="text/javascript" src="../resources/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="../resources/uploadify/jquery.uploadify.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#imageify").uploadify({
                'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
                method : 'post',            //以get方式上传
                
                'buttonText':'上传文件',        //上传按钮的文字
                'fileTypeDesc':'Image Files',    //可上传文件格式的描述
                'fileTypeExts':'*.gif; *.jpg; *.png; *.txt',    //可上传的文件格式 
                'auto':true,    //选择一个文件是否自动上传
                'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
                swf : '../resources/uploadify/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
                uploader : '/web01/file/fileUpload.do',                //服务器响应地址
                removeCompleted: false,  //进度条是否消失
                'formData' : {'name':'lmy'},        //这里是上传时候指定的参数,如果需要动态指定参数需要在onUploadStart方法里面指定
                'onCancel' : function(file) {
		            alert('The file ' + file.name + ' was cancelled.');
		        },
                'onUploadStart' : function(file) {    //上传前触发的事件
                	//$('#imageify').uploadify('cancel');
                   //在这里添加  $('#imageify').uploadify('cancel'); 可以取消上传
                    //$("#imageify").uploadify("settings","formData",{'name':'lmy1'}); //动态指定参数
                } ,
                'onUploadSuccess' : function(file, data, response) {    //上传成功后触发的事件
                    //alert("上传成功");
                    alert( 'id: ' + file.id+ ' - 索引: ' + file.index+ ' - 文件名: ' + file.name  
                    + ' - 文件大小: ' + file.size+ ' - 类型: ' + file.type+ ' - 创建日期: ' + file.creationdate  
                    + ' - 修改日期: ' + file.modificationdate+ ' - 文件状态: ' + file.filestatus+ ' - 服务器端消息: ' + data  
                    + ' - 是否上传成功: ' + response);
                },
                onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) { //这里是取消的时候发生    
	                // $("#dialog-message").html(errorString); 
	                alert(errorString);   
                }   
            });
        });
        
    </script>
</head>
<body>
	<input id="imageify" name="imageify" type="file"/>
    <div id="queue"></div> <!-- 上传文件存放的地方，ID为queue -->
<a href="javascript:$('#imageify').uploadify('cancel')">Cancel First File</a> 
<a href="javascript:$('#imageify').uploadify('cancel', '*')">Clear the Queue</a> 
<a href="javascript:$('#imageify').uploadify('upload', '*')">Upload the Files</a>

</body>
</html>
