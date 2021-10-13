# Damn Vulnerable Mobile Application
This project was designed with the intention of teaching the basics of mobile application security. The aim is to take an interest in mobile application security and 
turn it into a basic skillset, which can be expanded upon.

# How Do I Get Started?
To get started with mobile application security you will need a few tools. Below is what is needed with any Android penetration testing project.  

## Things You Will Need:
* Android Studio (with Android Debug Bridge)
* A Hypervisor (VMware, VirtualBox, etc.)
* An Android device/emulator
* Basic programming skills

## Things You Will Need Specific To This App:
* The dvma.apk file
* The [image] (.ova file) of the server the app communicates with
* The reverse engineering tool [ApkTool]

## Setup Guide
* Clone this repository by heading to "VCS > Get from Version Control..."  

![VCS](/Assets/image.png)  

* Paste the URL of this repo into the input box
* Follow the instructions
* Build the app on an Android device (preferably the emulator)  

Now run the app and enjoy!

## Vulnerabilities
* [Vulnerability 1 - SimplePin]
* [Vulnerability 1.1 - BadPINHash]
* More walkthroughs in the future.


[ApkTool]: https://ibotpeaches.github.io/Apktool/
[image]: https://drive.google.com/file/d/1Nve1vf4GZLqoLauzNVkdBu50YUIGTvxI/view?usp=sharing
[Vulnerability 1 - SimplePin]: /Vulns/SimplePIN/SimplePin.md
[Vulnerability 1.1 - BadPINHash]: https://github.com/fishyeffs/DVMA/blob/master/Vulns/BadPINHash/BadPINHash.md
