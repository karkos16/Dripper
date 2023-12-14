import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)

for i in range(2,5,1):
    GPIO.setup(2, GPIO.OUT)
    GPIO.output(2, GPIO.LOW)
GPIO.cleanup()