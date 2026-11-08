# Nexora TV OS

Nexora TV OS is the first software layer for the Nexora television experience: an Android TV-compatible launcher prototype designed for a remote control and a 1080p television.

> **Important:** This is an Android launcher app, not a replacement television firmware or kernel. It does not modify TV partitions or firmware. Device compatibility and default-home behavior must be tested on the exact TCL model before making it the default launcher.

## Current build

- Futuristic midnight-navy interface with electric blue and violet accents
- TV remote / D-pad focus navigation
- Home dashboard with featured content and app-style tiles
- Settings and device-information placeholder screens
- Android TV launcher entry points (Leanback and HOME)
- GitHub Actions workflow to compile a debug APK

The first version uses sample content only. Streaming services, AI voice features, and system settings integrations are not connected yet.

## Build

Open this repository in Android Studio (Giraffe or newer), allow Gradle sync, then choose **Build > Build Bundle(s) / APK(s) > Build APK(s)**.

Requirements:
- JDK 17
- Android SDK Platform 35
- Android Gradle Plugin 8.7.3 / Gradle 8.9

The GitHub Actions workflow also builds the debug APK on pushes and pull requests. Download it from the workflow run's **Artifacts** section.

## Install for testing

1. Build or download `app-debug.apk`.
2. Transfer it to the TV and install it using a trusted file manager / Android TV sideloading method supported by the device.
3. Open Nexora from the TV's Apps screen.
4. Test every screen with the TV remote before changing the default home app.

To test the HOME intent, press the TV remote's Home button and choose Nexora only if Android presents a launcher selection prompt. Keep the manufacturer's launcher available so you can switch back.

## Device compatibility

The target device is reported as a TCL 43P1 FHD running software version `V8-MT56552-LF1V083`. That firmware identifier alone does not prove Android version, launcher replacement support, or compatibility. Confirm the exact Android version and whether the TV permits a third-party HOME app before relying on Nexora at startup.

## Safety

Do not flash firmware, unlock the bootloader, modify partitions, or delete the TCL system launcher as part of this project. This project is an installable app only.
