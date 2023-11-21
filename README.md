# exam-restmenu
This repository is for finding all menus in a mongodb and returning them as a list of json to be displayed on either app or web

## Requirements
This requires a mongodb-community-edition docker container and the connection string in a .env file and the data which is the .json file in repo

## How to start mongodb docker and import data

```
docker pull mongodb/mongodb-community-server
docker run --name mongodb -d -p 8000:27017 mongodb/mongodb-community-server:latest
```
You can now connect to this docker in your mongodb compas with via this connection string: `mongodb://localhost:8000/`

You might need to make a database called `mtogo` and a collection called `restaurantMenus` before you can import the data.
```
use mtogo
db.createCollection("restaurantMenus")
```
You can now import the .json file to get some data.
