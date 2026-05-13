# Kutira-Kone — Zero-Waste Fabric Exchange

Kutira-Kone is a hyper-local Android marketplace that helps tailors, boutique owners, and artisans exchange leftover fabric scraps instead of discarding them.

The application promotes sustainable fabric reuse, reduces textile waste, and supports local artisans and small businesses.

Built using modern Android development practices with Kotlin and Jetpack Compose.

---

# Features

- Firebase Authentication
- Upload fabric scraps with images
- Browse nearby listings
- Search and filter materials
- Google Maps integration
- Buy/Swap request system
- Design inspiration section
- Modern Material 3 UI
- MVVM Architecture

---

# Tech Stack

- Kotlin
- Jetpack Compose
- Firebase Authentication
- Firebase Firestore
- Firebase Storage
- Google Maps SDK
- MVVM Architecture
- Navigation Component
- Coroutines
- Material 3

---

# Screens

- Login/Register
- Home Feed
- Upload Scrap
- Scrap Details
- Requests
- Map View
- Profile
- Design Ideas

---

# Download APK

You can directly download and install the latest APK from the Releases section of this repository.

## Installation Steps

1. Download the APK
2. Enable "Install from Unknown Sources" on your Android device
3. Install and open the app

---

# Setup Instructions (For Developers)

## 1. Clone Repository

```bash
git clone https://github.com/Gaganbv23/Kutira-Kone.git
```

---

## 2. Open in Android Studio

Open the project in Android Studio and allow Gradle Sync to complete.

Recommended:
- Android Studio Latest Version
- Android SDK 34+
- Latest Stable Kotlin Version

---

## 3. Firebase Setup

1. Create a Firebase project
2. Add Android app:
   com.example.kutirakone

3. Download:
   google-services.json

4. Place it inside:
   app/

Enable:
- Authentication
- Firestore Database
- Firebase Storage

---

## 4. Google Maps Setup

1. Enable Maps SDK for Android
2. Generate API key
3. Add the API key in:
   gradle.properties

Example:

```properties
MAPS_API_KEY=YOUR_API_KEY
```

---

# Running the App

1. Connect Android device or start emulator
2. Click Run ▶ in Android Studio

---

# Project Structure

```text
app/
├── data/
├── navigation/
├── ui/
├── viewmodel/
├── utils/
```

---

# Future Improvements

- Real-time chat
- Push notifications
- AI-based fabric recommendations
- Dark mode
- Multi-language support

---

# License

This project is developed for educational and sustainability purposes.
