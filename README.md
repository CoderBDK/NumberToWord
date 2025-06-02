# NumberToWord

A lightweight Kotlin library for converting numbers to words. Currently supports **Bangla** and **English**.

## Features

- Convert numbers to words:
  - Bangla
  - English
## 📸 Preview

<div align="center">
  <img src="https://github.com/user-attachments/assets/f9c60920-0e92-4bf3-9d53-5fd8361dfc77" alt="Screenshot 3" width="300" />
  <img src="https://github.com/user-attachments/assets/cfaba983-19e3-4e5a-baac-c7ec428e68fc" alt="Screenshot 3" width="300" />
  <img src="https://github.com/user-attachments/assets/1e2825fb-d27f-47e3-9b7c-e7a13d85ab93" alt="Screenshot 3" width="300" />
</div>

## Installation

### 1. Clone the repository
```bash
git clone https://github.com/NumberToWord.git
```
### 2. In your **root project's `settings.gradle.kts`**, include the module:

```kotlin
include(":numwordconverter")
```
### 3. Then in your app-level build.gradle.kts:
```kotlin
dependencies {
    implementation(project(":numwordconverter"))
}
```
## Usage
```kotlin
fun main() {
    val bnConverter = NumberWordConverter(Type.BANGLA)
    // Output: নয় কোটি নয় লক্ষ নয় হাজার নয় শত নয়
    println(bnConverter.numberToWords(90909909))


    val enConverter = NumberWordConverter(Type.ENGLISH)
    // Output: Nine Crore Nine Lakh Nine Thousand Nine Hundred Nine
    println(enConverter.numberToWords(90909909))

    val enInternationalConverter = NumberWordConverter(Type.ENGLISH_INTERNATIONAL)
   // Output: One Billion Nine Hundred Ninety-One Million Nine Hundred Ninety-Nine Thousand One Hundred One
    println(enInternationalConverter.numberToWords(1991999101))
}
```
## 🛣️ Roadmap

- [x] Bangla support
- [x] English support
- [x] Android Jetpack integration

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
