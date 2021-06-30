# TRexGame

This is a clone of the Chrome Dino game written in Java.

## Descriptions of the classes involved
|Class|	Description|
| --- | --- |
|Animation|Class for the animation of the character using a list of frames|
|Cactus|This class extends the enemy abstract class Contains the attributes about the cactus|
|Character|Class responsible for the main character(T-Rex)|
|Clouds|Class declaring the clouds for the background|
|Enemy|Abstract class for the enemy classes|
|EnemyManager| Takes care of the obstacles in the game and adds a cactus or 3 cacti to an arrayList which is implemented using Random|
|GameScreen|Class containing code for what is to be used within the game screen and it extends JPanel|
|GameWindow|Main class for the game window|
|Land|Class for the land images. Different land images are added using an arrayList|
|Resource|Class which is used to obtain the images from the data folder|

![trex-game](https://user-images.githubusercontent.com/57500357/123904669-de8e4e80-d93e-11eb-90b7-cf2fc8517d8c.gif)
