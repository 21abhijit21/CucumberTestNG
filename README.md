# CucumberTestNG

#Instructions To Run:
From Local:
1. Clone To Local
2. Run using TestRunner,TestNg XML or Gradle
3. To build using Gradle , use 'Clean Test' and to generate Allure Report,use 'Clean Build allureReport'.

Using CI/CD tool like Jenkins:
1. Create usual freestyle Job.
2. Download Plugins for CucumberReports and AllureReports.
3. To build, run using 'clean test' and use below switches
-PsuiteFile = testng.xml
-Dbrowser = chrome
-DRunEnv = remote
-DGRID_HUB_IP = IP
-DPort=4444 
4. Setup Selenium Hub, and provide HUB url in Switch as IP
