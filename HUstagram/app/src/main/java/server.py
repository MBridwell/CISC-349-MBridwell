import json
from flask import Flask
from pymongo import MongoClient  
from flask import request
from flask.json import jsonify
import sys
import base64
from datetime import datetime

app = Flask(__name__)

client = MongoClient('mongodb+srv://masontry2hard:Mdb11262002@cisc349-customers.ii2c8.mongodb.net/?retryWrites=true&w=majority&appName=CISC349-customers')
db = client["CISC349-Customers"]
 

# A welcome message to test our server
@app.route('/')
def index():
    return "<h1>Welcome to our server 123 !!</h1>"


# Add user
@app.route('/add', methods=['POST'])
def add():
    collection = db["CISC349-Customers"]
    request_data = request.get_json()
    name = request_data['name']
    address = request_data['address']
    data = { "name": name, "address": address }
    _id = collection.insert_one(data) 
    return json.dumps({'id' : str(_id.inserted_id)})

# Select All users

@app.route('/all', methods=['POST'])
def all():
    collection = db["CISC349-Customers"] 
    customers = list(collection.find())
    # we need to convert _id to str.
    return json.dumps(customers, default=str)

@app.route("/save", methods=["POST"])
def image_save():
    collection = db["images"]
    content = request.get_json()

    image_data = content['image']
    date_data = content['date']
    comment_data = content['comment']

    image = {
        "image": image_data,
        "date": date_data,
        "comment": comment_data
    }
    _id = collection.insert_one(image)

    return jsonify({'id': str(_id.inserted_id)})


@app.route('/get_data', methods=['GET'])
def image_list():
    collection = db["images"] 
    images = list(collection.find())
    for i in images:
        i['_id'] = str(i['_id'])
    return jsonify(images)


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000)