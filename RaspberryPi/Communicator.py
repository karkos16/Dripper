import firebase_admin
from firebase_admin import credentials, db

class Communicator:
    def __init__(self, service_account_path, database_url):
        cred = credentials.Certificate(service_account_path)
        firebase_admin.initialize_app(cred, {"databaseURL": database_url})

    def get_plant_data(self, plant_name):
        try:
            plants_ref = db.reference("plants")
            plants = plants_ref.get()

            if plants:
                for plant_key, plant_data in plants.items():
                    if plant_data.get("name") == plant_name:
                        return plant_data
            return None
        
        except Exception as e:
            print(f"Error getting data for {plant_name}: {e}")
            return None


    def get_plant_currentMoisture(self, plant_name):
        try:
            return self.get_plant_data(plant_name).get("currentMoisture")
        except Exception as e:
            print(f"Error getting currentMoisture for {plant_name}: {e}")
            return None
    
    def set_plant_currentMoisture(self, plant_name, currentMoisture):
        try:
            plants_ref = db.reference("plants")
            plants = plants_ref.get()
            
            if plants:
                for plant_key, plant_data in plants.items():
                    if plant_data.get("name") == plant_name:
                        plants_ref.child(plant_key).update({"currentMoisture": currentMoisture})
                        return True
        except Exception as e:
            print(f"Error setting currentMoisture for {plant_name}: {e}")
            return False
    
    def get_plant_lastWatered(self, plant_name):
        try:
            return self.get_plant_data(plant_name).get("lastWatered")
        except Exception as e:
            print(f"Error getting lastWatered for {plant_name}: {e}")
            return None
    
    def set_plant_lastWatered(self, plant_name, lastWatered):
        try:
            plants_ref = db.reference("plants")
            plants = plants_ref.get()
            
            if plants:
                for plant_key, plant_data in plants.items():
                    if plant_data.get("name") == plant_name:
                        plants_ref.child(plant_key).update({"lastWatered": lastWatered})
                        return True
        except Exception as e:
            print(f"Error setting lastWatered for {plant_name}: {e}")
            return False
        
    def get_plant_targetMoisture(self, plant_name):
        try:
            return self.get_plant_data(plant_name).get("targetMoisture")
        except Exception as e:
            print(f"Error getting targetMoisture for {plant_name}: {e}")
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



