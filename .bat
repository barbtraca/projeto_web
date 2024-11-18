@REM ----------------------------------------------------------------------------
@REM Maven Wrapper Modificado
@REM ----------------------------------------------------------------------------

@ECHO OFF
SETLOCAL ENABLEDELAYEDEXPANSION

REM Definir variáveis básicas
SET MVNW_PROPERTIES_FILE=.mvn/wrapper/maven-wrapper.properties
SET DISTRIBUTION_URL=
SET MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists
SET TEMP_DIR=%TEMP%\maven-wrapper

REM Verificar se o arquivo de configuração existe
IF NOT EXIST "%MVNW_PROPERTIES_FILE%" (
    ECHO [ERRO] Arquivo de configuração não encontrado: %MVNW_PROPERTIES_FILE%
    EXIT /B 1
)

REM Ler o URL de distribuição do arquivo de propriedades
FOR /F "tokens=2 delims==" %%A IN ('findstr distributionUrl %MVNW_PROPERTIES_FILE%') DO (
    SET DISTRIBUTION_URL=%%A
)

REM Validar se o URL foi obtido
IF "%DISTRIBUTION_URL%"=="" (
    ECHO [ERRO] URL de distribuição não definido no arquivo de configuração.
    EXIT /B 1
)

ECHO [INFO] URL de distribuição encontrado: %DISTRIBUTION_URL%

REM Verificar se o Maven já está instalado
SET MAVEN_DIR=%MAVEN_HOME%\%DISTRIBUTION_URL:~-20,10%
IF EXIST "%MAVEN_DIR%" (
    ECHO [INFO] Maven já instalado em: %MAVEN_DIR%
) ELSE (
    REM Criar diretório temporário
    MKDIR "%TEMP_DIR%"
    ECHO [INFO] Baixando Maven...

    REM Fazer download do Maven usando PowerShell
    POWERSHELL -Command "(New-Object System.Net.WebClient).DownloadFile('%DISTRIBUTION_URL%', '%TEMP_DIR%\maven.zip')"

    REM Descompactar o Maven
    ECHO [INFO] Extraindo Maven...
    POWERSHELL -Command "Expand-Archive -Path '%TEMP_DIR%\maven.zip' -DestinationPath '%MAVEN_HOME%'"

    REM Mover para o diretório final
    REN "%MAVEN_HOME%\apache-maven-*" "maven"
    SET MAVEN_DIR=%MAVEN_HOME%\maven

    REM Limpar arquivos temporários
    RMDIR /S /Q "%TEMP_DIR%"
)

REM Executar Maven
SET MAVEN_EXEC=%MAVEN_DIR%\bin\mvn.cmd
IF EXIST "%MAVEN_EXEC%" (
    ECHO [INFO] Executando Maven...
    "%MAVEN_EXEC%" %*
) ELSE (
    ECHO [ERRO] Maven não encontrado após a instalação.
    EXIT /B 1
)

EXIT /B 0
