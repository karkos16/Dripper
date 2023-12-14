import RPi.GPIO as GPIO
from time import sleep

GPIO.setmode(GPIO.BCM)

GPIO.setup(4, GPIO.OUT)

GPIO.output(4, GPIO.HIGH)

sleep(10)

GPIO.output(4, GPIO.LOW)

GPIO.cleanup()