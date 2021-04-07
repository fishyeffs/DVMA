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
There are two (technically three, but that's explained later) vulnerabilities in this part of the app - one is a vulnerability that can happen with any careless app, the other is a logical vulnerability. Take a look at [this] list and have a guess as to which one (the non-logical vulnerability) the app will apply to.

### Hint 1:
The app is not communicating with any outside source to validate the PIN code.

### Hint 2:
The answer is hidden in the cache.

### Hint 3:
The hash brown has a distinct lack of flavour!

### Answer:
Since the PIN is randomly generated, you have to exploit/solve this problem first. There is a file located in data/data/com.example.dvma/ that is called pin.txt. It stores a hash of the PIN. This storage method is an example of [insecure data storage]. While the PIN has been hashed, there are a tiny amount of combinations available (it's a 4 digit PIN) if you have any sort of automation technique available. Your challenge is to either create a program that inputs all 9999 possibilities into the app, or to take the hash outside of the app and brute force it in any other method. 


#### Vulnerability 1:
There is a limited number of PIN combinations. Create a program that loops through 9999 to find the correct PIN.

#### Vulnerability 2:
The hash of the PIN is stored without a salt.

#### Vulnerability 3:
There is a logic problem within the code, check PinLogin.kt in the source code and go to line 36. This conditional checks that there is an existence of the pin.txt file, meaning if the file does not exist, it writes a randomly generated PIN to _pin.txt_. This vulnerability is more of a puzzle, with one clue given earlier (i.e. the PIN being randomly generated when you click the login button), and you will likely never see something like this in the wild.


[insecure data storage]: https://www.appknox.com/blog/understanding-owasp-top-10-mobile-insecure-data-storage
[this]: https://twelvesec.com/2015/04/14/app-security-101-top-10-vulnerabilities/


