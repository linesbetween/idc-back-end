# IntelligentDocumentClassify
# A web app that reads documents of every kind, guesses what the contents are and creates index of documents. Then it could predict the other documents.

Run this project:

in local
1. clone it on your computer
2. use IDE(intellij) to open the "pom.xml" as project
3. import dependencies (right click on "pom.xml")
4. run src\main\java\org\utpe\freeopenuniversity\intelligentdocumentclassifier\IntelligentdocumentclassifierApplication.java
5. visite "http://localhost:8180/"

on server
1. java -jar intelligentdocumentclassifier-0.0.1-SNAPSHOT.jar &
2. visite http://66.76.242.195:8180/

### 1. target

An application can read files, guesses what the contents are and creates and index of documents.
(1) Send a text content to the server, the classifier will classify the documents automatically
(2) The Classifier could continue to update 
  

### 2. technology

* Stanford CoreNLP    https://nlp.stanford.edu/wiki/Software/Classifier                             
* Java  
* Java Spring Boot


### 3. principle
⚫ The core part based on the ColumnDataClassifier API of Stanford NLP. It could train data set and predict new files

https://nlp.stanford.edu/nlp/javadoc/javanlp/edu/stanford/nlp/classify/ColumnDataClassifier.html

⚫ Data folder

intelligentdocumentclassifier\data                        The folder to store data
intelligentdocumentclassifier\data\train                  The folder which store training data, the app will scan this folder and generate target file for classifier. This folder may have different sub folders named with catogory
intelligentdocumentclassifier\data\permanent              The folder to generate target file for classifier



