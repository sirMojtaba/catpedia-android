# 🐱 Catpedia

Catpedia is an Android application built using **Jetpack Compose**, **Clean architecture**, and modern Android development libraries. It allows users to browse various cat breeds, search among them, and mark their favorites.

## ✨ Features

- 🐈 View a list of cat breeds
- 🔍 Search among breeds
- ❤️ Mark breeds as favorite
- 🔄 Pull-to-refresh and infinite scrolling
- 🍃 Offline-first with Room database
- 🌐 Retrofit + Interceptor for API requests
- 📦 Hilt for dependency injection
- 📱 Jetpack Compose UI with Material Design

## 🛠 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Hilt** for Dependency Injection
- **Retrofit** for networking
- **Room** for local storage
- **Coroutines** and **Flow** for async operations
- **Material 3** UI components

## 📸 Screenshots

<p float="left">
  <img src="https://github.com/user-attachments/assets/43a46391-c2ea-45ef-8db3-59683f815702" width="250"/>
  <img src="https://github.com/user-attachments/assets/d5690d78-48fb-464b-8be7-c9c06b400308" width="250"/>
  <img src="https://github.com/user-attachments/assets/2d713539-a71c-428c-b1bc-6fc7d354cfb1" width="250"/>
</p>




## 🚀 Getting Started

### Prerequisites

- Android Studio Giraffe or newer
- JDK 17
- Minimum SDK: 21
- Target SDK: 34

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Catopedia.git
   cd Catopedia

2.Create a local.properties file (if not already present) and add your API key if required.

3.Sync the project with Gradle.

4.Run the app on your emulator or connected device.

## 📂 Project Structure

```text
Catpedia
├── app                     # App-level logic and navigation
├── data                    # Data layer: handles local & remote sources
│   ├── remote              # Retrofit services, DTOs
│   └── local               # Room database, DAOs
├── domain                  # Business logic layer
│   ├── usecase             # Use cases
│   └── model               # Domain models
├── presentation            # UI layer using Jetpack Compose
│   ├── screens             # Individual screens
│   └── components          # Reusable UI components
```

## 🤝 Contributing

Contributions, issues and feature requests are welcome! Feel free to fork the repo and submit a pull request.
