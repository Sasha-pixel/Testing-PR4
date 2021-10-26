https://github.com/swtestacademy/TestNGAllureReport

Скачайте choco, если хотите через него устанавливать chromedriver: `@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "[System.Net.ServicePointManager]::SecurityProtocol = 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"`

Если вы скачивали chromedriver не через choco, то убедитесь что `chromedriver.exe` лежит по пути `C:\ProgramData\chocolatey\lib\chromedriver\tools\chromedriver.exe`

Скачайте chromedriver (в качестве version укажите версию своего браузера): `choco install chromedriver --version=92.0.4515.1070`

Для работы создайте в intellij idea 2 конфигурации:
1) Edit Configurations -> + -> Maven

Имя любое

В поле `Command line:` пишем следующую команду: `clean test`

В пункте `Runner` нужно убрать галочку с `Use Project Settings` и в качестве JRE выбрать java 11

2) Повторить тоже самое но в поле `Command line:` вписать `io.qameta.allure:allure-maven:serve`
