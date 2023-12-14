import RPi.GPIO as GPIO
import Adafruit_DHT
from time import sleep

sensor = Adafruit_DHT.DHT11

GPIO.setmode(GPIO.BCM)

#GPIO.setup(2, GPIO.IN)

for i in range(10):
    humidity, temperature = Adafruit_DHT.read_retry(sensor, 14)
    if humidity is not None and temperature is not None:
        print('Temp={0:0.1f}*C  Humidity={1:0.1f}%'.format(temperature, humidity))
    else:
        print('Failed to get reading. Try again!')
    sleep(1)