# How to run MQTT (Windows)

----

- [Get Software](#get-software)
    - [Download Java Development Kit](#download-jdk)
    - [Download Git for Windows](#download-git)
    - [Download Intellij IDEA Community Edition](#intellij)
- [Prepare GitHub Repo](#prepare-github-repo)
    - [(Optional) Fork Repo](#fork)
    - [Clone GitHub Repo to Local Machine](#)
- [Run MQTT](#)
    - [Test Gradle](#)
    - [Start MQTT Broker](#)
    - [Start Register Receiver](#)

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
`https://github.com/jongkwang/IoTLabs`

### <a name="fork">(Optional) Fork the Original Repo
- If you wish to make changes to the repo without conflict of the original repo, you should fork the repo into your account.
- Go to `https://github.com/jongkwang/IoTLabs`
- Click the `Fork` button at the top right of the page.
- <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/fork.png">

### <a name="clone">Clone GitHub Repo to Local Machine
- Copy clone link from GitHub
    - - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/github_get_link.png">
- Start Git Bash
- Change directory(`cd`) to where you want to save the IoT Labs Project
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/git_bash_cd.png">
- Clone the repo using the command `git clone <your repo address>`
    - <img src="https://raw.githubusercontent.com/mjkim610/IoTLabs/master/assets/img/mqtt_tutorial_mjkim610/git_bash_clone.png">
