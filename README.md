
## About the app and the exercise

This is a web application, which implements a "blog". You are able to create an account, log in and then write blog posts.
You can also browse and read the blog posts. Take a look through the app, get it running and ideally understand its components.
If you have notes on the implementation, feel free to bring them to our next interview. 

This app will also serve as a basis for a code review during the next interview, so make sure you understand the code and 
click through the app. 

## Running the app

1. You can run the app directly with maven

```
./mvnw spring-boot:run
```

2. You can also run the app with Docker

```
docker build -t blog-app .
docker run -it -p "8088:8088" -p "8082:8082" blog-app
```

## Navigating the blog

Once the app is started it will be available at [localhost:8088](http://localhost:8088). 

Some initial users and blog posts are already created using Liquibase. 

You can log in the app using the following credentials:
- Username: john
- Password: test

Alternatively you can create a new user at [localhost:8088/signup](http://localhost:8088/signup).

Once logged in, you can create blog posts from here [localhost:8088/blog/create](http://localhost:8088/blog/create).

The index page [localhost:8088](http://localhost:8088) will list all blog posts. Then by clicking on one of the blog posts, you
will be redirected to a page, where you can read the blog post. One example can be found [here](http://localhost:8088/blog/1).

## Application design

This is a simple Spring Boot application, running on Java 11 with an H2 Database. The frontend is built using Thymeleaf.
It also utilizes Liquibase for schema evolution. The app has a built-in authentication using Spring Security. 

The app has the following entities and their respective attributes:

User
- id
- username
- password

BlogPost
- id
- title
- content
- author_id -> id of the user, that wrote the blog post
- edited_at -> the last time, that the blog post was edited
- views - the number of times, that the blog post has been read


## Important

If you have any questions or problems running the app please let us know as soon as possible.
