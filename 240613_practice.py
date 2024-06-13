#mongodb practice

from pymongo import MongoClient


client = MongoClient("localhost", 27017)

db1 = client.dbintel
user1 = {"name" : "김안드" , "age" : 21}
user2 = {"name" : "최이드" , "age" : 23}
user3 = {"name" : "박로이" , "age" : 25}

db2 = client.test
test1 = {"animal" : "cat", "bool" : True}
test2 = {"animal" : "dog", "bool" : False}

#Create
# db1.users.insert_one(user1)
# db1.users.insert_one(user2)
# db1.users.insert_one(user3)

# db2.testtest.insert_one(test1)
# db2.testtest.insert_one(test2)

#Read
user = db1.users.find_one({"name":"최이드"})
# print(user)

user_list = list(db1.users.find({}))
# print(user_list)

user4 = {"name": "이로이", "age" : 25}
# db1.users.insert_one(user4)

age_list = list(db1.users.find({"age":25},{"_id":False}))

print(age_list)

#Update
db1.users.update_many({"age":25}, {"$set":{"age":24}})
print(user_list)
db1.users.update_one({"name": "박로이"}, {"$set":{"age":22}})

#Delete
db1.users.delete_one({"name":"박로이"})

#보통 delete는 이렇게 처리
db1.users.update_many({"name":"이로이"},{'$set':{'deleted':True}})
