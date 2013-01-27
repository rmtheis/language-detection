# language-detection
* * * 

[Language detection](http://en.wikipedia.org/wiki/Language_detection) for Android: Given a string 
of text, identify what language the text is written in.

This project is a fork of an excellent Java language detection library 
([language-detection](http://code.google.com/p/language-detection/)) written by Nakatani Shuyo. 
The original git version control history and commit messages are retained in this project.

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

## Training: Generating language profiles

To generate a language profile, [download](http://download.wikimedia.org) a Wikipedia abstract 
file to use as a training data set.

For example, click `anwiki` and download `anwiki-20121227-abstract.xml` to 
`language-detection/abstracts/` and do:

    cd language-detection
    mkdir abstracts/profiles
    java -jar lib/langdetect.jar --genprofile -d language-detection/abstracts an
    python scripts/genprofile.py -i abstracts/profiles/an > AN.java

## Batch test (for 21 languages only)

    cd language-detection
    wget http://language-detection.googlecode.com/files/europarl-test.zip
    unzip europarl-test.zip
    cd profiles
    java -jar ../lib/langdetect.jar --batchtest ../europarl.test    

### Example batch test results

    bg (985/1000=0.99): {bg=985, ru=5, mk=10}
    cs (994/1000=0.99): {sk=5, en=1, cs=994}
    da (966/1000=0.97): {da=966, no=33, en=1}
    de (998/1000=1.00): {de=998, da=1, af=1}
    el (1000/1000=1.00): {el=1000}
    en (995/1000=1.00): {ht=3, en=995, nl=1, af=1}
    es (977/1000=0.98): {gl=16, ca=1, an=2, ast=4, es=977}
    et (995/1000=1.00): {de=1, oc=1, fi=2, et=995, af=1}
    fi (998/1000=1.00): {fi=998, et=2}
    fr (997/1000=1.00): {oc=1, it=1, ca=1, fr=997}
    hu (998/1000=1.00): {sl=1, hu=998, br=1}
    it (998/1000=1.00): {eu=1, it=998, es=1}
    lt (998/1000=1.00): {lv=2, lt=998}
    lv (999/1000=1.00): {pt=1, lv=999}
    nl (976/1000=0.98): {de=1, sv=1, nl=976, af=22}
    pl (999/1000=1.00): {pl=999, nl=1}
    pt (981/1000=0.98): {gl=15, it=1, hu=1, pt=981, es=2}
    ro (999/1000=1.00): {ro=999, pt=1}
    sk (987/1000=0.99): {hr=1, sl=2, sk=987, mt=1, lt=1, cs=8}
    sl (975/1000=0.98): {hr=24, sl=975, ast=1}
    sv (991/1000=0.99): {da=2, no=7, sv=991}
    total: 20806/21000 = 0.991

## License

[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
