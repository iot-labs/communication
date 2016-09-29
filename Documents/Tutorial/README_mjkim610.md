# How to Run MQTT (Windows)
Tutorial on how to run MQTT server of IoT Labs for the Windows operating system.

----

- [Get Software](#get-software)
    - [Download Java Development Kit](#download-jdk)
    - [Download Git for Windows](#download-git)
    - [Download Intellij IDEA Community Edition](#download-intellij)
- [Prepare GitHub Repo](#prepare-github-repo)
    - [(Optional) Fork Repo](#fork)
    - [Clone GitHub Repo to Local Machine](#clone)
- [Open Project With Gradle](#open-project)
    - [Open Project in Intellij IDEA](#open-in-intellij)
    - [Test Gradle](#test-gradle)
- [Run MQTT](#run-mqtt)
    - [Start MQTT Broker](#start-mqtt-broker)
    - [Start Register Receiver](#start-register-receiver)

----

## <a name="get-software">Get Software

### <a name="download-jdk">Download Java Development Kit
- Search for `Java SE Development Kit 8 Downloads`
- Open the official JDK downloads page
    - Link: `http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html`
- Download the latest stable JDK version for your machine
    - Check processor architecture
        - 32-bit Windows: `Windows x86`
        - 64-bit Windows: `Windows x64`
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/jdk.png">

### <a name="download-git">Download Git for Windows
- Search for `Git for Windows`
    - Link: `https://git-scm.com/download/win`
- Download the latest version of Git for Windows
- <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/git.png">

### <a name="download-intellij">Download Intellij IDEA Community Edition
- Search for `Intellij IDEA`
    - Link: `https://www.jetbrains.com/idea/#chooseYourEdition`
- Download the latest version of Intellij IDEA Community Edition
- <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij.png">

----

## <a name="prepare-github-repo">Prepare GitHub Repo
IoT Labs GitHub Repo: `https://github.com/jongkwang/IoTLabs`

### <a name="fork">(Optional) Fork the Original Repo
- If you wish to make changes to the repo without conflict of the original repo, you should fork the repo into your account
- Go to `https://github.com/jongkwang/IoTLabs`
- Click the `Fork` button at the top right of the page
- <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/fork.png">

### <a name="clone">Clone GitHub Repo to Local Machine
- Copy link of the repo from GitHub (This may be the original IoT Labs repo, or your forked repo)
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/github_get_link.png">
- Start Git Bash
- Change directory(`cd`) to where you want to save the IoT Labs Project
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/git_bash_cd.png">
- Clone the repo using the command `git clone <your repo address>`
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/git_bash_clone.png">

----

## <a name="open-project">Open Project With Gradle

### <a name="open-in-intellij">Open Project in Intellij IDEA
- Launch Intellij IDEA
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij-splash.png">
- Click `Import Project`
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij-import.png">
- Navigate to `IoTLabs` folder and click `OK`
- Select `Gradle` external model to import from
- Select `Use default gradle wrapper` and click `Finish`

### <a name="test-gradle">Test Gradle
- Launch Intellij IDEA Terminal by clicking the bottom left button
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij-terminal.png">
- Navigate to `.\Platforms\Java`
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij-navigate.png">
- Run Gradle test using the `gradlew.bat test` command
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij-test.png">
- **If encoding error occurs**, reset the Intellij IDEA encoding to UTF-8
    - Go to `File` -> `Settings`
    - Type `encoding` at the top left side of the `Settings` window
    - Change `IDE Encoding` and `Project Encoding` to any other encoding, then back to UTF-8
        - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/intellij-encoding.png">

----

## <a name="run-mqtt">Run MQTT

### <a name="start-mqtt-broker">Start MQTT Broker
- Start MQTT broker using the `gradlew.bat -q start_mqtt_broker` command
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/start-mqtt-broker.png">

### <a name="start-register-receiver">Start Register Receiver
- Open a new command prompt and navigate to `\Platforms\Java`
- Start register receiver by using the `gradlew.bat -q register_receivers -Pf=src/main/resources/config/mqtt_receiver.json` command
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/register-receiver.png">

- Check that the connect event has been received
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/connect-event-received.png">
