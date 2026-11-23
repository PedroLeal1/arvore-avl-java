@echo off
chcp 65001 >nul
title Demo AVL
cd /d "%~dp0"
javac -encoding UTF-8 *.java
if errorlevel 1 goto erro
cls
echo Ajuste a janela (F11 = tela cheia, Ctrl + / Ctrl - = tamanho da fonte),
echo comece a gravar no OBS e aperte Enter para iniciar a demonstracao.
pause >nul
cls
java -Dstdout.encoding=UTF-8 -Dfile.encoding=UTF-8 Main
echo.
echo Fim. Pode parar a gravacao.
pause >nul
exit /b 0
:erro
echo ERRO ao compilar. Veja a mensagem acima.
pause
