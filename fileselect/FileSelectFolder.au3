#Region ;**** ���������� ACNWrapper_GUI ****
#AutoIt3Wrapper_Compile_Both=y
#AutoIt3Wrapper_Change2CUI=y
#EndRegion ;**** ���������� ACNWrapper_GUI ****


$file = FileOpen("FileSelectFolder_lastDir.txt", 0)
$chars = FileRead($file)
 ;MsgBox(4096, "����", $chars, 10)
FileClose($file)

$var = FileSelectFolder("ѡ��һ���ļ���.",  "",4,$chars)


;canel condt
If $var=""  Then
    Exit
EndIf

;save last dir 
$file = FileOpen("FileSelectFolder_lastDir.txt", 2)
FileWrite($file, $var )
FileClose($file)


ConsoleWrite ($var)


; FileOpenDialog ( "����", "��ʼĿ¼", "�ļ�����" 