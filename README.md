# Document_Classify
# A program that reads documents of every kind, guesses what the contents are and creates index of documents.

Run this project:

in local
1. clone it on your computer
2. use IDE to open the "pom.xml" as project
3. import dependencies (right click on "pom.xml")
4. run src\main\java\org\utpe\freeopenuniversity\intelligentdocumentclassifier\IntelligentdocumentclassifierApplication.java
5. visite "http://localhost:8180/"

on server
1. java -jar intelligentdocumentclassifier-0.0.1-SNAPSHOT.jar &
2. visite http://66.76.242.195:8180/

### 1. target

An application can read files, guesses what the contents are and creates and index of documents.
(1) Open a folder, the classifier will classify the documents automatically
(2) The Classifier could continue to update 
(3) Store the Classifier and dataset into database
  

### 2. technology

* Stanford CoreNLP 
* Protege
* Java  
* Java Swing
* Java Spring Boot

### 3. principle
⚫ The core part based on the ColumnDataClassifier API of Stanford NLP. It could train data set and predict new files

⚫ Learning and utilizing ontological link distance to figure out the relationship and degree of words, using K means clustering to create its own categories of commonality

* Create folders -> classify documents to different folder

Machine learning part

result:  input pdf,  output label
need: 1) train dataset
       has label and pdf
      2) training algorithm   **

### 4. Components
(1)Web
  1) Admin/User send a file to server, return its' category; if the answer is wrong, admin have the authority to send the right answer to the server to learn
     upload file button
     appear its' category below
  2) Upload a fold, return zip of classified documents
  3) Admin can upload a folder for training 
  4) User can sign in and sign up
  sign in/up page
  
(2) Desktop
  Using Java Swing to built UI, it has buttons of "Reset Classifier"  "Open Train Folder to Update", "Open Train Folder for once test", "Open Test Folder",  "Predict File" "Classify Folder"
    Using "Reset Classifier" could delete the past classifier data
    Using " Open Train Folder to Update" to update the present Classifier. You should choose a folder stored with files as dataSet, every sub folder's name must be set to the files' category name.
    Using "Open Train Folder for once test", allow you to train a temporary Classifier
    Using " Open Test Folder" to test the accuracy of the classifier.
    Using " Predict File" to predict one file's category
    Using " Classify Folder" to predict all the files in the folder, and create category folders, place each file to its' according folder automatically
### 5. The idea of 1th generation product
(1) 
(1) set some labels in the database:
   1) computer science
   2) physics 
   3) math
   4) nutrition
   5) fiction
   6) poem
   7) 

