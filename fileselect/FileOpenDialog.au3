#Region ;**** 参数创建于 ACNWrapper_GUI ****
#AutoIt3Wrapper_Compile_Both=y
#AutoIt3Wrapper_Change2CUI=y
#EndRegion ;**** 参数创建于 ACNWrapper_GUI ****
 

$var=FileOpenDialog ( "选择一个文件select a file ", "","所有文件(*.*)" ) 
ConsoleWrite ($var)
;ConsoleWrite("---")