import RPi.GPIO as GPIO
from time import sleep
import Adafruit_DHT
import Adafruit_GPIO.SPI as SPI
import Adafruit_MCP3008

def value_to_percentage(val):#map values from 0-1023 to 0-100
    # val = 0 -> highest moisture
    return abs(val-1023) * 100 / 1023

sensor = Adafruit_DHT.DHT11

#połączenie czujnika za pomocą SPI
SPI_PORT   = 0
SPI_DEVICE = 1
mcp = Adafruit_MCP3008.MCP3008(spi=SPI.SpiDev(SPI_PORT, SPI_DEVICE))

GPIO.setmode(GPIO.BCM)


pins = [3,4] #GPIO pompek
DHT_GPIO = 14

GPIO.setup(3, GPIO.OUT)
GPIO.setup(4, GPIO.OUT)
values = [0]*2 # tablica na wartości wilgotności gleby

while True:
    #sczytywanie wilgotności i temperatury powietrza
    humidity, temperature = Adafruit_DHT.read_retry(sensor, DHT_GPIO)
    if humidity is not None and temperature is not None:
        print('Temp={0:0.1f}*C  Humidity={1:0.1f}%'.format(temperature, humidity))
    else:
        print('Failed to get reading. Try again!')

    #sczytywanie wilgotności gleby
    for i in range(2):
        values[i] = value_to_percentage(mcp.read_adc(i))
    print('| {0:>4} | {1:>4} |'.format(*values))

    #proste odpalanie pompki gdy wilgotność gleby > 2
    for i in range(2):
        if values[i] > 2:
            GPIO.output(pins[i], GPIO.HIGH)
        else:
            GPIO.output(pins[i], GPIO.LOW)

    sleep(1)