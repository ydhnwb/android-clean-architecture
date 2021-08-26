# android-clean-architecture
A clean architecture example. Using Kotlin Flow, Retrofit and Dagger Hilt, etc. 

## Intro

_Architecture_ means the overall design of the project. It’s the organization of the code into classes or files or components or modules. And it’s how all these groups of code relate to each other. The architecture defines where the application performs its core functionality and how that functionality interacts with things like the database and the user interface.

_Clean architecture_ refers to organizing the project so that it’s easy to understand and easy to change as the project grows. This doesn’t happen by chance. It takes intentional planning.

## Reference
- If you want a db cache using room db, [go here](https://github.com/ydhnwb/android-cache-example)
- This android app using backend from [this repo](https://github.com/ydhnwb/golang_heroku) 
- If you want tutorial how to create this app step by step, go to this [youtube playlist](https://www.youtube.com/playlist?list=PLkVx132FdJZnNsBTJSr4Sc1oAwRFXl2G4).

## Screenshots
<img src="https://github.com/ydhnwb/android-clean-architecture/blob/main/docs/Screenshot_1622220461.png" width="400" height="800">  <img src="https://github.com/ydhnwb/android-clean-architecture/blob/main/docs/Screenshot_1622220467.png" width="400" height="800">


<img src="https://github.com/ydhnwb/android-clean-architecture/blob/main/docs/Screenshot_1622220439.png" width="400" height="800">  <img src="https://github.com/ydhnwb/android-clean-architecture/blob/main/docs/Screenshot_1622220448.png" width="400" height="800">

## Notes
I created this app just for example how to implement clean architecture on android. _**It really biased to my preference and experience. Also, _clean architecture_ is not mandatory to do. If you feel that you/your team are taking so much benefit by implementing this design pattern, then go for it. Otherwise, don't use it or just modify to you/your team preferences.**_
This app have little bug. If you go from FragmentA to FragmentB, then go back, the fragmentA re render again. I Know how to fix it but I dont have time to do it. Search for "retain fragment jetpack navigation", use the newest solution


## Main Picture
There are 3 layer in this app.  
| Presentation Layer      | Domain Layer          | Data Layer                         |
| ----------------------- | --------------------- | ---------------------------------- |
| your ui/view            | entity                | data source, dto                   |
| your viewmodel          | usecase               | repository implementation          |
| probably your extension | repository interface  | your library config(retrofit/room) |
| etc...                  | etc..                 | etc...                             |


<img src="https://github.com/ydhnwb/android-clean-architecture/blob/main/docs/clean.png">

## App Level Example

| Presentation Layer             | Something in Between  | Domain Layer                      | Data Layer                         | Outer data layer |
| ------------------------------ | --------------------- | --------------------------------- | ---------------------------------- | ---------------- |
| LoginActivity & LoginViewModel | <- LoginUseCase ->    | <- LoginRepository (interface) -> | <- LoginRepositoryImplementation   | DataSource       |

