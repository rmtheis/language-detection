# language-detection
* * * 

[Language detection](http://en.wikipedia.org/wiki/Language_detection) for Android: Given a string 
of text, identify what language the text is written in.

This project is a fork of an excellent Java language detection library 
([language-detection](http://code.google.com/p/language-detection/)) written by Nakatani Shuyo. 
The original git version control history and commit messages are retained in this project.

## NO LONGER MAINTAINED

There are no plans to update this project, but feel free to fork the repo and have at it. 

**_2017-05-23 Update:_ Android O has introduced on-device language detection!
See the [`TextClassificationManager.detectLanguages()`](https://developer.android.com/reference/android/view/textclassifier/TextClassificationManager.html#detectLanguages(java.lang.CharSequence))
method in the text classification service.**

## Changes

I've made two significant changes to the original code:

1. Speed enhancements. As an alternative to using JSON-based text files for storing language 
profiles, a Python script is used to convert language profiles into Java code that can be bundled 
with an app. With the resulting performance improvement, language detection is fast enough to run 
acceptably on Android devices.

2. Additional language profiles:

- Aragonese
- Asturian
- Basque
- Belarusian
- Breton
- Catalan
- Galician
- Haitian
- Icelandic
- Irish
- Malay
- Maltese
- Occitan
- Serbian (Cyrillic alphabet)
- Welsh
- Yiddish

## Download

    git clone git@github.com:rmtheis/language-detection.git

## Sample usage

See [the original project on Google Code](http://code.google.com/p/language-detection/).

Set up the language profile list in `DetectorFactory.java`.

## Training: Generating language profiles

To generate a language profile, [download](http://dumps.wikimedia.org/backup-index.html) a 
Wikipedia abstract file to use as a training data set.

For example, click `anwiki` and download `anwiki-20121227-abstract.xml` to 
`language-detection/abstracts/` and do:

    cd language-detection
    mkdir abstracts/profiles
    java -jar lib/langdetect.jar --genprofile -d language-detection/abstracts an
    python scripts/genprofile.py -i abstracts/profiles/an > AN.java

## Maven

Maven repository:

    <repository>
        <id>nitin.public.maven.repository.release</id>
        <name>Nitin's Public Release Repository</name>
        <url>https://raw.github.com/nitinverma/public.maven.repository/master/releases/</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>

Maven dependency:

        <dependency>
            <groupId>com.cybozu.labs</groupId>
            <artifactId>langdetect</artifactId>
            <version>${com.cybozu.labs.version}</version>
        </dependency>

## Android settings

The hard-coded n-grams hashmap language profiles in this project may lead to memory problems 
during the build of an Android project containing the language profiles.
 
To work around this, change the default max heap size setting for dx to 

    defaultMx="-Xmx2048M" 

in `android-sdk/platform-tools/dx` (or `dx.bat` on Windows).

Building with ant may give better results than Eclipse.

## License

[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
