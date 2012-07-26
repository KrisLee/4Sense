# 4Sense

### Creators
- Ali Nadir Arslan
- Dimitrios Damopoulos
- João Xavier
- Manuel Grizonnet

### Overview

4Sense is a mobile game concept developed for the Android platform during the ESA App Camp 2012 contest <http://www.app-camp.eu/>.
It aims to increase the visibility of GMES services to the public and incorporate GMES services into the daily lives of people.
At the same time, 4Sense is also aiming to retrieve user needs to GMES services providers for future improvement of these services.

[4Sense screenshot](/res/raw/4sense_ss.png)

#### How does it work?

The player tries to get points (named Sentinels in the game) by performing 4 actions: sense, share, search and secure. The GMES services are represented in the game by the GMES character who is interacting with players.
There is an international ranking for all the participants and the best players compete to be in the 4Sense hall-of-fame. The friendly competitive aspect is a great motivation and the 10 best players can win a one week travel to the European Astronaut Centre in Cologne.

#### From space to people

Through this friendly competitive challenge, 4Sense tries to bridge the gap between the GMES system and people.


### Functionalities

#### Sense
People are the sensor and interact with the space sensors through a quiz. Questions are directly related to the user environment and use GMES products to provide the answer. For example:

    "The air you are breathing now is more or less polluted than before?"

We use the POLYPHEMUS air quality system and the WRF-model for meteorological simulation. services (WMS-T) available on the WDC-RSAT website using the localization to dynamically generate the request and get the answer.
A good answer gives 2 Sentinels to the player. After that, the application displays a map with the air quality product where the player localization also appears.

#### Search

As Sentinel satellites will provide global monitoring (Land, Ocean, Atmosphere...) working a like a constellation, a player can search for other players (sensors) around him and increase his awareness of his environment. 
He can access to the Activations map with information recently shared by other players in his neighborhood. Then, he can register to notifications related to this activation and if he think that it is of interest, he can share this information on social network (Twitter and Facebook for now) and be rewarded by passing the information.

Players can registered to a maximum of 10 notifications by week.

#### Share

The player can share information in his environment with the GMES character and he gets rewarded.    
The player is taking an image and select the topic related to it. Then, he gets feedback from the GMES character. It works as notifications about this event which are provided by the GMES services. Of course, he can share this information also on social network. Any “sharing” in the game give one Sentinel to the player. The player wins Sentinel each time what he sharing his relayed by other players. 

Sharing example - air quality:
An inhabitant from Beijing player takes a picture of a strange pollution cloud over the city last Monday.
He wins one Sentinel by sharing the picture and wins 30 more Sentinels because 30 players also shared this information.

#### Secure 

The GMES character is providing users with challenging tasks, requests or important messages. Once a player responds to the challenge task, he will be able to increase his level in the game. He is acting as a volunteer and having some fun while progressing inside the game. 
On the GMES side, this in-situ information can be extremely useful in lots of cases, for instance, in the use of Volunteer geographic information in order to make automatic remote sensing image analysis more robust or in the integration of volunteer operators in the image analysis process of large volumes of remote sensing data.

#### Getting involved

To get involved in the application, you can share all of your actions inside the game on social network, send invitation to participate to the challenge to your friends and you get rewarded if they accept (3 Sentinels).


### Data sources

The potential of this application can lead to change by orders of magnitude the use of remote sensing information. GMES products provided by Sentinel data will be use in the quiz and the secure actions. Moreover, we can imagine that information collected inside the game could improve all of the GMES products. For example:

- The player shares images related to the release of a petrol in the environment. It could help to the oil-spill monitoring done by Sentinel-1;
- Land Use/Land Cover State and changes products using satellite data often involve methods which required a learning step (such as image classification) which need annotated examples as input. Information shared by the player can be uses in Land Cover State products derived by Sentinel-2 data to increase their accuracy;
- Similar examples can be described on how the game can improve related to ocean or atmosphere monitoring provided by Sentinel-3 and Sentinel-4 missions.


### Relevance for GMES

This game application is relevant for GMES in two different ways:

- It will increase the awareness of GMES data because people will learn about them in their daily actions inside the game through the quiz but also by getting notifications about GMES services and share them on the social network.;
- It will increase the quality of GMES products by putting the player's information inside the system. In exchange the player is increasing his level in the game.




### License

Copyright [2012] [Ali Nadir Arslan, Dimitrios Damopoulos, João Xavier, Manuel Grizonnet]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
