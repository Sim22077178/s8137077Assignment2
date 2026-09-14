# NIT3213 Android Application - Assignment 2

## Student Information

**Student ID:** s8137077
**Project Name:** s8137077Assignment2
**Course:** NIT3213 - Mobile Application Development

---

## Project Description

This project is an Android application developed for NIT3213 Assignment 2.

The application demonstrates Android application development using Kotlin, REST API integration, dependency injection, ViewModels, RecyclerView, navigation, and unit testing.

The application communicates with the provided NIT3213 API to authenticate users and retrieve application data.

---

## Main Features

The application contains the following main screens and features:

### 1. Login Screen

* Allows the user to enter their login credentials.
* Communicates with the API for authentication.
* Handles successful and unsuccessful login attempts.
* Provides appropriate feedback to the user.

### 2. Dashboard Screen

* Displays data retrieved from the API.
* Uses RecyclerView to display multiple items.
* Allows the user to select an item to view more information.

### 3. Details Screen

* Displays detailed information about the selected item.
* Receives the selected item from the Dashboard screen.
* Provides navigation between application screens.

---

## Technologies Used

* **Kotlin**
* **Android Studio**
* **Android SDK**
* **Jetpack ViewModel**
* **Kotlin Coroutines**
* **Retrofit**
* **Gson**
* **RecyclerView**
* **Koin Dependency Injection**
* **JUnit**
* **REST API**

---

## Architecture

The application follows a separation of responsibilities between the user interface, ViewModels, repositories, API services, and data models.

The general flow of the application is:

```text
UI
 ↓
ViewModel
 ↓
Repository
 ↓
Retrofit API Service
 ↓
NIT3213 REST API
```

### ViewModel

ViewModels are used to manage UI-related data and application state while separating business and data-handling logic from the Activity/Fragment.

### Repository

The Repository provides a layer between the ViewModel and the API service. It is responsible for requesting and providing data to the ViewModel.

### API Service

Retrofit is used to communicate with the REST API and perform HTTP requests.

---

## Dependency Injection

Koin is used as the dependency injection framework for the application.

Dependencies such as the Retrofit API service, repository, and ViewModel are provided through Koin modules.

This reduces tight coupling between components and makes the application easier to maintain and test.

---

## API Integration

The application uses the NIT3213 REST API provided for the assignment.

Retrofit is used to send requests to the API and Gson is used for JSON conversion.

The application uses the API for:

* User authentication
* Retrieving application data
* Retrieving details for selected data

---

## Unit Testing

Unit tests are included for important application components.

Testing focuses on critical logic such as ViewModel behaviour and data handling.

JUnit is used as the testing framework.

---

## How to Run the Project

1. Open the project in Android Studio.
2. Allow Gradle to synchronize and download the required dependencies.
3. Make sure an Android emulator or physical Android device is available.
4. Select the `app` configuration.
5. Run the application.
6. Use the required API login credentials provided for the assignment to authenticate.

---

## Project Structure

The project is organised into separate components for maintainability.

```text
app/
└── src/
    └── kotlin+java/
            └── com.example.s8137077assignment2/
            │       ├── data/
            │       ├── di/
            │       ├── network/
            │       ├── ui/
            │       └── MainApplication/
            │ 
            └── res/
```

> The exact package and folder structure may vary depending on the final implementation.

---

## Git Usage

Git and GitHub are used for version control throughout the development of the project.

Meaningful commit messages are used to document major stages of development, including implementation of API integration, application screens, dependency injection, testing, and final improvements.

---

## Conclusion

This project demonstrates the development of an Android application using Kotlin and modern Android development practices. It integrates a REST API, uses ViewModels and dependency injection, provides multiple screens with navigation, and includes unit testing for important application components.
