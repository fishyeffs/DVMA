# Damn Vulnerable Mobile Application
This project was designed with the intention of teaching the basics of mobile application security. The aim is to take an interest in mobile application security and 
turn it into a basic skillset, which can be expanded upon.

# How Do I Get Started?
To get started with mobile application security you will need a few tools. Below is what is needed with any Android penetration testing project.  

## Things you will need:
* Android Studio (with Android Debug Bridge)
* A Hypervisor (VMware, VirtualBox, etc.)
* An Android device/emulator

## Things you will need specific to this app:
* The dvma.apk file
* The image (.iso file) of the server the app communicates with

# Vulnerability Walkthrough
## PIN Screen
There are two vulnerabilities in this part of the app - one is a vulnerability that can happen with any careless app, the other is a logical vulnerability. Take a look at [this]https://twelvesec.com/2015/04/14/app-security-101-top-10-vulnerabilities/ list and have a guess as to which one (the non-logical vulnerability) the app will apply to.
### Step 1:
