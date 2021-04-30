
#!/usr/bin/python3
import os

x = 1400
while(x < 1600):
	#time.sleep(1)
	os.system('adb shell input text ' + str(x).zfill(4))
	os.system("adb shell input tap 800 1700")
	print("Trying %04d", x)
	x += 1
