# official-website 
1. 招新部分代码已经弄过来，现在需要手动导入plutext-3.2.jar这个jar包。jar包文件在根目录的jar文件夹中，只需下载下来，运行以下命令。
```$xslt
mvn install:install-file -Dfile=C:\\plutext3.2.jar(位置以自己的为准) -DgroupId=com
.plutext 
-DartifactId=plutext -Dversion=3.2 -Dpackaging=jar
```
