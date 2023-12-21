# import RPi.GPIO as GPIO
# import Adafruit_DHT
from time import sleep
from Communicator import Communicator
import threading

sensor = Adafruit_DHT.DHT11
GPIO.setmode(GPIO.BCM)

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
    while communicator.get_Switch_info() == "On":
    
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
    print("dupa")
    
def update_plant_database(communicator, plant, temperature, moisture):
    try:
        if temperature is not None and moisture is not None:
            print(f'{plant}: Temp={temperature:.1f}*C  moisture={moisture:.1f}%')

            communicator.set_plant_currentMoisture(plant, moisture)
            communicator.set_room_temperature(temperature)
        else:
            print(f'{plant}: Sensor reading is invalid.')
    except Exception as e:
        print(f"Error updating database: {e}")

def read_and_update_plant_info(plant, communicator, pin):
    while communicator.get_Switch_info() == "On":
    
        readings = []
        for i in range(16):
            temperature, moisture = read_plant_sensor(pin)
            readings.append((temperature, moisture))
            sleep(0.5)

        readings.sort()
        readings = readings[1:-1]

        avg_temperature = sum(reading[0] for reading in readings) / len(readings)
        avg_moisture = sum(reading[1] for reading in readings) / len(readings)

        update_plant_database(communicator, plant, avg_temperature, avg_moisture)

        sleep(10)



if __name__ == "__main__":
    service_account_path = "RaspberryPi\dripper-48098-firebase-adminsdk-qeb16-6f34a7de32.json"
    database_url = "https://dripper-48098-default-rtdb.europe-west1.firebasedatabase.app/"
    
    communicator = Communicator(service_account_path, database_url)

    plants = ["plant1", "plant2"]
    # piny do plantuw tylko
    pins = ["chuj", "muj"]

    threads = []
    for plant, pin in zip(plants, pins):
        thread = threading.Thread(target=read_and_update_plant_info, args=(plant, communicator, pin))
        threads.append(thread)
        thread.start()

    thread = threading.Thread(target=read_room_sensor, args=())
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