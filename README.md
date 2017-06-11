# SharpSK [![Build Status](https://travis-ci.org/Sharpjaws/SharpSK.svg?branch=master)](https://travis-ci.org/Sharpjaws/SharpSK)
SharpSK is a Addon for Skript that adds alot of plugin support and other features to Skript.
<hr>

### How to compile SharpSK from Github:

What you'll need:

>-  Java JDK 1.8
>-  Maven
>-  Git


### Windows:
>- Download git here: https://git-scm.com/download/win
>- Download Java JDK 8 here: http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
>- Download Maven here: http://apache.proserve.nl/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.zip
>- Install Java JDK 1.8 by running the installer.
>- Make sure “JAVA_HOME” is added to the Windows environment variables
>- Install git by running the installer.
>- Go through the Git installing process. (Make sure to tick "Git bash here")
>- Unzip Maven to: "C:\Program Files\Apache\Maven" or  "C:\Program Files (x86)\Apache\Maven"
>- Add "M2_HOME" and "MAVEN_HOME" to your Windows environment variables, and point them both to your Maven folder.
>- Add "M2" to your Windows envornment variables and point it to "%M2_HOME%\bin"
>- Edit your system's PATH variable and add ";%M2%" to the end of it. (Recommended to backup first)
>- Once finished, create folder "SharpSK" on your desktop or any other path.
>- Right-click the folder and choose "Git Bash Here"
>- In Git run: "git clone https://github.com/Sharpjaws/SharpSK.git"
>- Wait until the cloning process is complete.
>- Close git and open Windows Command Prompt.
>- Check if maven is installed by runnning mvn --version
>- Navigate to the SharpSK folder by using "cd c:\Users\%username%\Desktop\SharpSK" if you placed it on your desktop (Note that %username% will be your windows username)
>- Run command: "mvn"
>- Wait until the compile process is complete.
>- In the SharpSK folder open folder "target"
>- Copy the jar file to your server's plugins folder



### Linux:
##### Fedora:
>- Open terminal
>- Install git with command: "sudo dnf install git"
>- Install maven with command: "sudo dnf install maven"
>- Install JDK 1.8 with command: "sudo dnf install openjdk-8-jdk"
>- Run command: "git clone https://github.com/Sharpjaws/SharpSK.git"
>- Wait until the cloning process is complete.
>- Run command: "cd SharpSK"
>- Run command: "mvn"
>- Wait until the compile process is complete.
>- In the SharpSK folder open folder "target"
>- Copy the jar file to your server's plugins folder

##### Debian:
>- Open terminal
>- Install git with command: "sudo apt-get install git"
>- Install maven with command: "sudo apt-get install maven"
>- Install JDK 1.8 with command: "sudo apt-get install openjdk-8-jdk"
>- Run command: "git clone https://github.com/Sharpjaws/SharpSK.git"
>- Wait until the cloning process is complete.
>- Run command: "cd SharpSK"
>- Run command: "mvn"
>- Wait until the compile process is complete.
>- In the SharpSK folder open folder "target"
>- Copy the jar file to your server's plugins folder

##### Mac: (Coming soon)
