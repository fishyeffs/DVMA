# Vulnerability Walkthrough 1
The screen that welcomes you on to the app will hopefully be familiar to you. A PIN screen is found in a large number of popular apps to secure messages and data from prying eyes.  
Unfortunately, some apps do not take the time to make their PIN screens secure. Have a play with the screen and see if you can notice anything unusual compared to what you would expect.  

If you found that you had unlimited attempts at entering a PIN code, you have found a vulnerable aspect to this screen. Given that you can input as many combinations as you wish, how many would you have to input in order to gain access in the worst case scenario?  

## Task
Create a program that inputs the range of 0 to 9999 in a four digit format using the Android Debugging Bridge.  
Here are some resources to help you:
* [Using the Android Debugging Bridge]
* [How to unlock an Android phone with the ADB]
* [Automating ADB with Python]

I strongly recommend using a scripting language like Python to brute force the PIN code.



[this]: https://twelvesec.com/2015/04/14/app-security-101-top-10-vulnerabilities/
[Using the Android Debugging Bridge]: https://www.developer.com/mobile/android/using-android-debug-bridge/
[How to unlock an Android phone with the ADB]: https://stackoverflow.com/questions/29072501/how-to-unlock-android-phone-through-adb
[Automating ADB with Python]: https://amrbook.com/coding/python/automate-adb-with-python/
