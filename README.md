# NumberToWord

A lightweight Kotlin library for converting numbers to words. Currently supports **Bangla** and **English**.

## Features

- Convert integer numbers to Bangla words
- Convert integer numbers to English words

## Installation

### 1. Clone the repository
```bash
git clone https://github.com/CoderBDK/NumberToWord.git
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

}
```
## 🛣️ Roadmap

- [x] Bangla support
- [x] English support
- [ ] Android Jetpack integration

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
