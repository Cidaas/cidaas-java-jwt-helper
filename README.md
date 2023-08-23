# How to release

## Release new version
Please use the commands `mvn release:prepare` and `mvn release:perform` to release a new version and upload it to the widas's gitlab.

For this you will need a settings.xml for maven with the credentials of the gitlab server.  

Ask maintainers of this project for the information!

## Upload project to maven central
To upload a new release to maven central, please use the command: `mvn clean deploy -DdeployToCentral=true`.

Also for this step, we need to have some credentials in the settings.xml and have install gpg on the local machine. 

Please look at [this guide](https://gitlab.widas.de/cidaas-public-devkits/cidaas-public-devkit-documentation/-/wikis/How-to-deploy-java-artifacts-to-maven-central-repository) for more information!
