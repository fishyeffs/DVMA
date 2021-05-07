# Vulnerability Walkthrough 2
## Don't Share Too Much!
[SharedPreferences] is an interface for accessing user data within an app, and is used to create and delete preference data using a key-value method. Generally, they are stored in the file `/data/data/com.example.victim/shared_prefs/`.

## Task
Find the XML file within the app and login with the credentials after signing out the existing user.

## Fix
Don't use Shared Preferences to store valuable data. Instead, use a credentials manager like [this].

[this]: https://developers.google.com/identity/smartlock-passwords/android/store-credentials
[SharedPreferences]: https://developer.android.com/reference/kotlin/android/content/SharedPreferences
