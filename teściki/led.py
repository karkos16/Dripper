import RPi.GPIO as GPIO
from time import sleep

GPIO.setmode(GPIO.BCM)

GPIO.setup(3, GPIO.OUT)
GPIO.setup(4, GPIO.OUT)

while True:
    GPIO.output(3, GPIO.HIGH)
    GPIO.output(4, GPIO.HIGH)
    sleep(1)
    GPIO.output(3, GPIO.LOW)
    GPIO.output(4, GPIO.LOW)
    sleep(1)
