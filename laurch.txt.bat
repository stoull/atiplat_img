rem @echo off
   %~dp0fileselect\FileSelectFolder.exe > "%temp%\FileSelectFolder_out.txt"

rem ʱ��������Ҫ�ṩһ����������,��  �û��Լ����������ֵ,Ȼ�����������������ֵ������Ӧ����,�����Ҿ���˵˵��SET����  ���﷨,ֻ��Ҫ��һ��"/P"�����Ϳ�����!  
set /p sltFlder=  <  "%temp%\FileSelectFolder_out.txt"
rem del "%temp%\111.txt"
 echo --dbg:sltFlder:%sltFlder%

  %~dp0fileselect\FileOpenDialog.exe > "%temp%\FileOpenDialog_out.txt"
set /p tmpl=  <  "%temp%\FileOpenDialog_out.txt"
echo --dbg:tmpl:%tmpl%

 set java_home=../jre18071
 set path=%path%;%~dp0opencv_build_x64_vc12_bin;%~dp0;./
 
 set javaexec="%java_home%\bin\java.exe"
@ rem for dbg 
@rem set sltFlder=C:\0workspace\atiplat_img\fmspaint
rem set tmpl=C:\0workspace\atiplat_img\fmspaint\t.jpg  
rem a  start explorer z:
%javaexec%  -classpath ".;.\classes;%~dp0bin;%prj_home%\bin;%prj_home%WEB-INF\classes;%~dp0lib_lazy\atiplat_core.jar;%~dp0lib_lazy\opencv-2413.jar" -Djava.ext.dirs="%~dp0lib;%prj_home%\lib;%prj_home%WEB-INF\lib;%prj_home%lib_jetty9"   -Djava.library.path="%~dp0;%~dp0dll;%prj_home%\dll;%~dp0opencv_build_x64_vc12_bin"  com.attilax.img.util.ImgSearch "%sltFlder%"  "%tmpl%"
pause
 