import RPi.GPIO as GPIO
import Adafruit_DHT
import Adafruit_GPIO.SPI as SPI
import Adafruit_MCP3008
from time import sleep
from Communicator import Communicator
import threading
from datetime import datetime

sensor = Adafruit_DHT.DHT11
GPIO.setmode(GPIO.BCM)

DHT_GPIO = 14

#połączenie czujnika za pomocą SPI
SPI_PORT   = 0
SPI_DEVICE = 1
mcp = Adafruit_MCP3008.MCP3008(spi=SPI.SpiDev(SPI_PORT, SPI_DEVICE))

pump_pins = {
    "plant1": 3,
    "plant2": 4
}

for name, value in pump_pins.items():
    GPIO.setup(value, GPIO.OUT)

def run_pump(pin):
    GPIO.output(pin, GPIO.HIGH)
    sleep(5)
    GPIO.output(pin, GPIO.LOW)

def value_to_percentage(val):  # map values from 0-1023 to 0-100
    # val = 0 -> highest moisture
    return abs(val - 1023) * 100 / 1023

def read_room_sensor(pin):
    try:
        humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)
        if humidity is not None and temperature is not None:
            return temperature, humidity
        else:
            print("Failed to get reading. Try again!")
            return None, None
    except Exception as e:
        print(f"Error reading sensor: {e}")
        return None, None
    
def update_room_database(communicator, temperature, humidity):
    try:
        if temperature is not None and humidity is not None:
            print(f'Room: Temp={temperature:.1f}*C  Humidity={humidity:.1f}%')

            communicator.set_room_temperature(temperature)    
            communicator.set_room_humidity(humidity)
        else:
            print("Failed to get reading. Try again!")
    except Exception as e:
        print(f"Error updating database: {e}")       

def read_and_update_room_info(communicator, pin):     
    while True:
    
        readings = []
        for i in range(16):
            temperature, humidity = read_room_sensor(pin)
            readings.append((temperature, humidity))
            sleep(0.5)

        readings.sort()
        readings = readings[1:-1]

        avg_temperature = sum(reading[0] for reading in readings) / len(readings)
        avg_humidity = sum(reading[1] for reading in readings) / len(readings)

        update_room_database(communicator, avg_temperature, avg_humidity)

        sleep(10)
    
def read_plant_sensor(pin):
    try:
        moisture = value_to_percentage(mcp.read_adc(pin))
        if moisture is not None:
            return moisture
        else:
            print("Failed to get reading. Try again!")
            return None
    except Exception as e:
        print(f"Error reading sensor: {e}")
        return None
    
def update_plant_database(communicator, plant, moisture):
    try:
        if moisture is not None:
            print(f'{plant}: moisture={moisture:.1f}%')

            communicator.set_plant_currentMoisture(plant, moisture)
        else:
            print(f'{plant}: Sensor reading is invalid.')
    except Exception as e:
        print(f"Error updating database: {e}")

def read_and_update_plant_info(plant, communicator, pin):
    while True:
        if communicator.get_Switch_info() == "On" and communicator.get_plant_switch_info(plant) == "On":
    
            readings = []
            for i in range(16):
                moisture = read_plant_sensor(pin)
                readings.append(moisture)
                sleep(0.5)

            readings.sort()
            readings = readings[1:-1]

            avg_moisture = sum(readings) / len(readings)
            target_moisture = communicator.get_plant_targetMoisture(plant)
            if (avg_moisture - target_moisture < -5):
                run_pump(pump_pins[plant])
                time = datetime.now().strftime("%D %H:%M")
                communicator.set_plant_lastWatered(plant, time)

            update_plant_database(communicator, plant, avg_moisture)

        sleep(10)



if __name__ == "__main__":
    service_account_path = "dripper-48098-firebase-adminsdk-qeb16-6f34a7de32.json"
    database_url = "https://dripper-48098-default-rtdb.europe-west1.firebasedatabase.app/"
    
    communicator = Communicator(service_account_path, database_url)

    plants = ["plant1", "plant2"]
    # piny do plantuw tylko
    plant_pins = { #GPIO pompek
        "plant1": 3,
        "plant2": 4
    }

    threads = []
    for plant, pin in plant_pins.items():
        print(plant)
        thread = threading.Thread(target=read_and_update_plant_info, args=(plant, communicator, pin))
        threads.append(thread)
        thread.start()

    thread = threading.Thread(target=read_and_update_room_info, args=(communicator, DHT_GPIO))
    threads.append(thread)
    thread.start()

    for thread in threads:
        thread.join()

    

    # while True:
    #     if(communicator.get_Switch_info() == "On"):
            
    #         plant = "plant1"
    #         newCurrentMoisture = 15
    #         if communicator.set_plant_currentMoisture(plant, newCurrentMoisture):
    #             print(f"CurrentMoisture set to: {newCurrentMoisture}")
    #         else:
    #             print("Failed to set currentMoisture")    

    #         read_room_temp = 69.69   
    #         if communicator.set_room_temperature(read_room_temp):
    #             print(f"Room temperature set to: {read_room_temp}")
    #         else:
    #             print("Failed to set room temperature")