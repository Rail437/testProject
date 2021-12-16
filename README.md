# testProject
Прилижение написано на Java 11.
Для запуска необходимо выполнить:

mvn clean package - собирает проект в .jar файл.

Для создания докер образа необходимо прописать:
docker build --tag=testproject:latest .

Для запуска образа: 
docker run --name testproject -p 8081:8080 -t testproject:latest

--------------------
