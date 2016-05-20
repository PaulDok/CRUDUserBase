1) База данных - MySQL5.7 / schema: test / table: user
Столбцы в таблице - как в задании
Логин/пароль - root/root
Все порты по умолчанию

2) Скрипт по наполнению базы тестовыми данными лежит в
src/main/resources/mockuserdata.sql
в нем 30 записей

3) Разворачивал на Tomcat7 - Local Server
Deploy at startup: CRUDUserBase:war exploded

4) Веб-апп доступен по адресу
http://localhost:8080/
к остальным адресам стучатся скрипты на странице
