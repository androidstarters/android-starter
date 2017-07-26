# AndroidMvpStarter [![Build Status](https://travis-ci.org/androidstarters/android-starter.svg?branch=master)](https://travis-ci.org/androidstarters/android-starter)

[![Backers on Open Collective](https://opencollective.com/android-starter/backers/badge.svg)](#backers) [![Sponsors on Open Collective](https://opencollective.com/android-starter/sponsors/badge.svg)](#sponsors) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20MVP%20Starter-blue.svg?style=flat)](https://android-arsenal.com/details/3/5232)
[![Join the chat at https://gitter.im/android-starter/Lobby](https://badges.gitter.im/android-starter/Lobby.svg)](https://gitter.im/android-starter/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

An MVP Boilerplate to save me having to create the same project over from scratch every time! :)
<p align="center">
  <img src="http://g.recordit.co/L5selg7aIv.gif" width="250">
  <img src="http://g.recordit.co/xt4o5wTySc.gif">
</p>

## This project uses:
- [RxJava2](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Retrofit](http://square.github.io/retrofit/) / [OkHttp](http://square.github.io/okhttp/)
- [Gson](https://github.com/google/gson)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Google Play Services](https://developers.google.com/android/guides/overview)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide 3](https://github.com/bumptech/glide)
- [Stetho](http://facebook.github.io/stetho/)
- [Espresso](https://google.github.io/android-testing-support-library/) for UI tests
- [Robolectric](http://robolectric.org/) for framework specific unit tests
- [Mockito](http://mockito.org/)
- [Checkstyle](http://checkstyle.sourceforge.net/), [PMD](https://pmd.github.io/) and [Findbugs](http://findbugs.sourceforge.net/) for code analysis


## Create new project using yeoman [generator-android-mvp-starter](https://github.com/androidstarters/generator-android-mvp-starter)
```bash
npm install -g yo
npm install -g generator-android-mvp-starter
mkdir NewApp && cd $_
yo android-mvp-starter
```

## Building

To build, install and run a debug version, run this from the root of the project:
```sh
./gradlew app:assembleDebug
```
    
## Testing

To run **unit** tests on your machine:

```sh
./gradlew test
```

To run **instrumentation** tests on connected devices:

```sh
./gradlew connectedAndroidTest
```

## Code Analysis tools

The following code analysis tools are set up on this project:

* [PMD](https://pmd.github.io/)

```sh
./gradlew pmd
```

* [Findbugs](http://findbugs.sourceforge.net/)

```sh
./gradlew findbugs
```

* [Checkstyle](http://checkstyle.sourceforge.net/)

```sh
./gradlew checkstyle
```

## The check task

To ensure that your code is valid and stable use check:

```sh
./gradlew check
```

## Jacoco Reports

#### Generate Jacoco coverage reports for the Debug build. Only unit tests.

```sh
app:testDebugUnitTestCoverage
```

#### Generate Jacoco coverage reports for the Release build. Only unit tests.

```sh
app:testReleaseUnitTestCoverage
```

#### Generate Jacoco coverage reports for the Debug build. Both unit and espresso tests.

```sh
app:unitAndEspressoDebugTestCoverage
```

#### Generate Jacoco coverage reports for the Release build. Both unit and espresso tests.

```sh
app:unitAndEspressoReleaseTestCoverage
```

### Created & Maintained By
[Ravindra Kumar](https://github.com/ravidsrk) ([@ravidsrk](https://www.twitter.com/ravidsrk))

> If you found this repo helpful or you learned something from the source code and want to thank me, consider [buying me a cup of](https://www.paypal.me/ravidsrk) :coffee:

## Contributors

This project exists thanks to all the people who contribute. [[Contribute]](CONTRIBUTING.md).
<a href="graphs/contributors"><img src="https://opencollective.com/android-starter/contributors.svg?width=890" /></a>


## Backers

Thank you to all our backers! 🙏 [[Become a backer](https://opencollective.com/android-starter#backer)]

<a href="https://opencollective.com/android-starter#backers" target="_blank"><img src="https://opencollective.com/android-starter/backers.svg?width=890"></a>


## Sponsors

Support this project by becoming a sponsor. Your logo will show up here with a link to your website. [[Become a sponsor](https://opencollective.com/android-starter#sponsor)]

<a href="https://opencollective.com/android-starter/sponsor/0/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/0/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/1/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/1/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/2/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/2/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/3/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/3/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/4/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/4/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/5/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/5/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/6/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/6/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/7/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/7/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/8/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/8/avatar.svg"></a>
<a href="https://opencollective.com/android-starter/sponsor/9/website" target="_blank"><img src="https://opencollective.com/android-starter/sponsor/9/avatar.svg"></a>



## License
```
MIT License

Copyright (c) 2017 Ravindra Kumar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
