@ECHO OFF

	SET "binPath=.\bin"
	SET "invokeParam=%1"
	
	if NOT defined invokeParam (
		ECHO Execute o batch passando por parametro o numero do exercicio. Ex: ./run.bat 1
		PAUSE
		EXIT
	)

	IF exist %binPath% (rmdir /S /Q %binPath%)
	MKDIR %binPath%

		javac -d %binPath% "./Exercicio %invokeParam%/"*.java
		if NOT ["%ERRORLEVEL%"]==["0"] ( PAUSE EXIT )

	java -cp %binPath% Main

rem EXIT