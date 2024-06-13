
from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient
import random

app = Flask(__name__)
client = MongoClient("localhost", 27017)
db = client.dbintel

@app.route("/")
def home():
    return render_template('index.html')

# @app.route("/test")
# def test():
#     return "This is easy"

@app.route("/users", methods = ["GET"])
def user_list():
    user_name = request.args.get('name')
    # return jsonify({'result': 'success', 'msg': 'Get 요청:' + user_name})
    age = int(request.args.get('age'))

    users = list(db.users.find({'age': {"$gt":age}}, {'_id':False}))
    return jsonify({'users': users})



# @app.route("/create")
# def add_user_list():
#     db.users.insert_one({'name':'mr.Kim', 'age': random.randrange(20,50) })
#     return "임시화면"
