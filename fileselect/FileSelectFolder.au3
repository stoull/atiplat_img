#Region ;**** 参数创建于 ACNWrapper_GUI ****
#AutoIt3Wrapper_Compile_Both=y
#AutoIt3Wrapper_Change2CUI=y
#EndRegion ;**** 参数创建于 ACNWrapper_GUI ****


$file = FileOpen("FileSelectFolder_lastDir.txt", 0)
$chars = FileRead($file)
 ;MsgBox(4096, "测试", $chars, 10)
FileClose($file)

$var = FileSelectFolder("选择一个文件夹.",  "",4,$chars)


;canel condt
If $var=""  Then
    Exit
EndIf

;save last dir 
$file = FileOpen("FileSelectFolder_lastDir.txt", 2)
FileWrite($file, $var )
FileClose($file)


ConsoleWrite ($var)


; FileOpenDialog ( "标题", "起始目录", "文件类型" 