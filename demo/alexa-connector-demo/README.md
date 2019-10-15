Amazon Alexa  Connector Demo
====================================
Anypoint Studio demo for Amazon Alexa Conector.


Prerequisites
---------------

* Anypoint Studio 7 with Mule ESB 4.2.1 Runtime.
* Mule Amazon Alexa connector v1.0.0.


How to Run Sample
-----------------

1. Import the project folder demo in Studio.
2. Run the application.
3. Create amazon alexa developer account and whitlist hostname in security profile creted in amazon account.
4. Click autorize endpoint and enter amzon credentails, check if you see the message token received successfully on browser.
5. Access the endpoints for respective operation like create Skill,Update Skill,Delete Skill etc.


About the Sample
----------------
After successfull receive of token in step (4) of above, use the below link to create skill
Using a browser, navigate to http://localhost:8081/create-skill
