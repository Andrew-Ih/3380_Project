build: FinalProject.class SQLServer.class

SQLServer.class: SQLServer.java
	javac SQLServer.java

FinalProject.class: FinalProject.java
	javac FinalProject.java

run: build
	java -cp .:mssql-jdbc-11.2.0.jre18.jar FinalProject

clean:
	rm -f SQLServer.class FinalProject.class
