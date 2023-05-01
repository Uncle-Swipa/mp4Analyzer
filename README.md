This is a Spring Boot application that analyses an Mp4 file contained in a URL and returns the list of boxes present in the file along with their sizes and types.
To build, package and run the application follow these steps:
- Make sure you have Java 17 and above(because of the records class) installed in your system
- Clone the repository of download the source code.
-Navigate to the project directory and run the following command to build the project:

<img width="811" alt="build" src="https://user-images.githubusercontent.com/88970637/235492797-fbcdaa98-7bdc-44e5-8492-006c9ec65ba4.png">.

- After building the project, navigate to the target directory and run the following.

<img width="503" alt="run" src="https://user-images.githubusercontent.com/88970637/235495042-19ba98e4-f787-4da3-9418-9cd2d48fbfe6.png">
        
- The application should be running on http://localhost:8081
- To make sample request with curl, use the following command:

<img width="826" alt="curl" src="https://user-images.githubusercontent.com/88970637/235495857-31359fc7-a792-4812-bb0a-fe176990fbfe.png">

- This will return a JSON response with the box size and box type.

Note: The url http://demo.castlabs.com/tmp/text0.mp4 can be replaced with the url of the Mp4 file you want to analyse



