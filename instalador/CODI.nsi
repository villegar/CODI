; CODI.nsi
;
; Nombre del instalador
Name "CODI"

; Archivo de salida
OutFile "InstaladorCODI.exe"

; Directorio de instalación
InstallDir $PROGRAMFILES\CODI

; Clave en el registro de Windows
InstallDirRegKey HKLM "Software\CODI" "Install_Dir"

; Requerir permisos de Administrador
RequestExecutionLevel admin

; ----------------------------
; Paginas
Page components
Page directory
Page instfiles

UninstPage uninstConfirm
UninstPage instfiles
Icon CODI.ico
UninstallIcon CODI.ico
Caption "Sistema de Contrataciones Directas CODI"
UninstallCaption "Desintalación Sistema CODI"
InstallButtonText Instalar
InstallColors (FFFFFF 43ADE7)
;InstProgressFlags colored
;VIAddVersionKey "ProductName" "Sistema de Contrataciones Directas CODI"
;VIAddVersionKey "Comments" "Oficina de Suministros y Compras"
;VIAddVersionKey "CompanyName" "Universidad de Costa Rica"
;VIAddVersionKey "LegalCopyright" "©2013 Universidad de Costa Rica"
;VIAddVersionKey "FileDescription" "Test Application"
;VIAddVersionKey "FileVersion" "1.0.0"


; ---------------------------

; Material para la instalación
Section "CODI (requerido)"
	SectionIn RO
	
	; Toma el directorio seleccionado para la instalación
	SetOutPath $INSTDIR
	
	; Archivos a instalar
	File "CODI.accdb"
	File "CODI.exe"
	File "CODI.ico"
	File "CODI.jar"
	File "Configurar BD.pdf"
	File "Origen de Datos.lnk"
	; Escribimos los registros de instacion dento del directorio registo
  	WriteRegStr HKLM SOFTWARE\CODI "Install_Dir" "$INSTDIR"
  
  	; Escribimos las claves de desinstalacion de Windows
  	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\CODI" "DisplayName" "CODI"
  	WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\CODI" "UninstallString" '"$INSTDIR\desinstalar.exe"'
  	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\CODI" "NoModify" 1
  	WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\CODI" "NoRepair" 1
  	WriteUninstaller "desinstalar.exe"
	MessageBox MB_YESNO "¿Desea configurar la Base de Datos?" IDYES true IDNO false
	true:
	  ExecShell "open" "$INSTDIR\Origen de Datos.lnk"
	  ExecShell "open" "$INSTDIR\Configurar BD.pdf"
	false:
SectionEnd

; Seccion opcional (Pudiera ser desabilitada pero en este caso necesitamos crear todo.)
Section "Crear Accesos directos"
  	CreateDirectory "$SMPROGRAMS\CODI"
  	CreateShortCut "$SMPROGRAMS\CODI\Desinstalar.lnk" "$INSTDIR\desinstalar.exe" "" "$INSTDIR\desinstalar.exe" 0
  	CreateShortCut "$SMPROGRAMS\CODI\CODI.lnk" "$INSTDIR\CODI.exe" "" "$INSTDIR\CODI.exe" 0
  	CreateShortCut "$DESKTOP\CODI.lnk" "$INSTDIR\CODI.exe" "" "$INSTDIR\CODI.exe" 0
SectionEnd

;--------------------------------

; Desinstalador
Section "Uninstall"
  	; Remover las claves del Registro
  	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\CODI"
  	DeleteRegKey HKLM SOFTWARE\CODI

  	; Eliminados los archivos y los desintalamos
  	Delete "$INSTDIR\*.*"
	Delete "$DESKTOP\CODI.*"
	Delete "$SMPROGRAMS\CODI\*.*"

  	; Eliminamos las carpetas creadas
  	RMDir "$SMPROGRAMS\CODI"
  	RMDir "$INSTDIR"
SectionEnd