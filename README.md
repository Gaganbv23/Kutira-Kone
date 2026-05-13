# Kutira-Kone (Zero-Waste Fabric Exchange)

This is the complete Android Studio project structure for the Kutira-Kone marketplace app. 
The application is built using modern Android development practices, including Kotlin, Jetpack Compose, MVVM Architecture, and Navigation Component.

## Step-by-step instructions to run the project

1. **Open the Project:**
   Open Android Studio, select "Open", and navigate to the `Kutira_kone` folder. Wait for Gradle to sync completely. If you encounter any Gradle errors, make sure you have the required Android SDK (API 34) and Kotlin Plugin installed.

2. **Firebase Setup:**
   - Go to the [Firebase Console](https://console.firebase.google.com/).
   - Create a new project named "Kutira-Kone".
   - Add an Android App to the project. The package name must exactly match: `com.example.kutirakone`.
   - Download the `google-services.json` file.
   - Place the `google-services.json` file inside the `app/` folder of this project.
   - In the Firebase Console, go to **Authentication** and enable the "Email/Password" sign-in method.
   - Go to **Firestore Database** and create a database (start in Test Mode for development).
   - Go to **Storage** and set up Firebase Storage (start in Test Mode for development).
   - *Note: Once `google-services.json` is added, you can uncomment the `FirebaseApp.initializeApp(this)` line in `KutiraKoneApp.kt` if it's commented.*

3. **Google Maps Configuration:**
   - Go to the [Google Cloud Console](https://console.cloud.google.com/).
   - Create a new project or select an existing one.
   - Enable the **Maps SDK for Android**.
   - Go to **APIs & Services > Credentials** and generate an API Key.
   - Open `gradle.properties` in the root of the project and replace `MAPS_API_KEY=YOUR_API_KEY_HERE` with your actual API key.

4. **Run the App:**
   - Select an emulator or connect a physical Android device.
   - Click the "Run" button in Android Studio.

## Architecture
- **Data Layer:** `data/models` contains the `User`, `Scrap`, and `Request` data models.
- **UI Layer:** `ui/screens` contains the Jetpack Compose screens, wrapped in a `BottomNavigationBar` via the Navigation Graph (`KutiraNavGraph.kt`).

*Note: The UI currently has placeholder basic Compose components. To implement the full Firebase Repositories and full ViewModels, you need to sync the project first with the valid `google-services.json`.*
