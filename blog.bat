//start explorer z:
//读取文件
set txt=e:\dom.txt
//保存结果的目录
set savedir= e:\mailRzt2

-----------正式代码
if exist "%~dp0AutoHotKey"  ( set ahk_home=%~dp0AutoHotKey)
 
 if exist "%~dp0ATIBROW\AutoHotKey"   set ahk_home=%~dp0ATIBROW\AutoHotKey

 set prj_home=%~dp0
if exist %~dp0AtiBrow  set prj_home=%~dp0AtiBrow
 

 
 set java_home=../jre
//if exist d:\jdk1.7.0_0132bit ( set java_home=d:\jdk1.7.0_0132bit)
 

echo %ahk_home%  %prj_home%  %java_home%


//start %ahk_home%\AutoHotKey.exe  %prj_home%\actPlayWin.ahk
rem %~dp0AutoHotKey\AutoHotKey.exe  %~dp0actPlayWin.ahk
 
 
set javaexec="%java_home%\bin\java.exe"
  
rem a  start explorer z:
%javaexec%  -classpath ".;.\classes;%~dp0bin;%prj_home%\bin;%prj_home%WEB-INF\classes" -Djava.ext.dirs="%~dp0lib;%prj_home%\lib;%prj_home%WEB-INF\lib;%prj_home%lib_jetty9"   -Djava.library.path="%~dp0dll;%prj_home%\dll"  aaaBlogger.BloggerGui
pause