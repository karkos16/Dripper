from Communicator import Communicator

if __name__ == "__main__":
    service_account_path = "RaspberryPi\dripper-48098-firebase-adminsdk-qeb16-6f34a7de32.json"
    database_url = "https://dripper-48098-default-rtdb.europe-west1.firebasedatabase.app/"
    
    communicator = Communicator(service_account_path, database_url)

    
    
    plant_name = "Truskawka"
    currentMoisture = communicator.get_plant_currentMoisture(plant_name)

    if currentMoisture is not None:
        print(f"The currentMoisture of {plant_name} is: {currentMoisture}")
    else:
        print(f"Failed to retrieve currentMoisture for {plant_name}")