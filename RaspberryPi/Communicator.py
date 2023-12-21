import firebase_admin
from firebase_admin import credentials, db

class Communicator:
    def __init__(self, service_account_path, database_url):
        cred = credentials.Certificate(service_account_path)
        firebase_admin.initialize_app(cred, {"databaseURL": database_url})

    def get_plant_data(self, plant):
            try:
                plants_ref = db.reference("plants")
                plant_ref = plants_ref.child(plant)
                plant_data = plant_ref.get()

                return plant_data
            
            except Exception as e:
                print(f"Error getting data for {plant}: {e}")
                return None

  
    def set_plant_currentMoisture(self, plant, currentMoisture):
        try:
            plant = self.get_plant_data(plant) 
            plant.update({"currentMoisture": currentMoisture})
            return True
        except Exception as e:
            print(f"Error setting currentMoisture for {plant}: {e}")
            return False
    
    def get_plant_lastWatered(self, plant):
        try:
            return self.get_plant_data(plant).get("lastWatered")
        except Exception as e:
            print(f"Error getting lastWatered for {plant}: {e}")
            return None
    
    def set_plant_lastWatered(self, plant, lastWatered):
        try:
            plant = self.get_plant_data(plant) 
            plant.update({"lastWatered": lastWatered})
            return True
        except Exception as e:
            print(f"Error setting lastWatered for {plant}: {e}")
            return False
        
    def get_plant_targetMoisture(self, plant):
        try:
            return self.get_plant_data(plant).get("targetMoisture")
        except Exception as e:
            print(f"Error getting targetMoisture for {plant}: {e}")
            return None

    def set_room_temperature(self, temperature):
            try:
                room_ref = db.reference("roomInfo")
                room_ref.update({"temperature": temperature})
                return True
            except Exception as e:
                print(f"Error setting room temperature: {e}")
                return False
        
    def set_room_humidity(self, humidity):
        try:
            room_ref = db.reference("roomInfo")
            room_ref.update({"humidity": humidity})
            return True
        except Exception as e:
            print(f"Error setting room humidity: {e}")
            return False

    def get_Switch_info(self):
        try:
            switch_ref = db.reference("Switch")
            switch_value = switch_ref.get()

            return switch_value
        
        except Exception as e:
            print(f"Error getting Switch information: {e}")
            return None

