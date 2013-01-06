# language-detection

A fork of an excellent Java language detection library ([language-detection](http://code.google.com/p/language-detection/)) written by Nakatani Shuyo.

This project contains the following additional profiles not contained in the original library:

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
- Serbian
- Welsh
- Yiddish

These additional profiles were generated from Wikipedia abstracts the same way that the original profiles were generated.

## Download

    git clone git@github.com:rmtheis/language-detection.git

## Sample usage

See [the original project on Google Code](http://code.google.com/p/language-detection/).

## Training: Generating language profiles

To generate a language profile, [download](http://download.wikimedia.org) a Wikipedia abstract file to use as a training data set.

For example, click anwiki and download anwiki-20121227-abstract.xml to language-detection/abstracts/ then do:

    cd language-detection
    mkdir abstracts/profiles
    java -jar lib/langdetect.jar --genprofile -d language-detection/abstracts an


## Batch test (for 21 languages only)

    cd language-detection
    wget http://language-detection.googlecode.com/files/europarl-test.zip
    unzip europarl-test.zip
    cd profiles
    java -jar ../lib/langdetect.jar --batchtest ../europarl.test    

## Example batch test results

    bg (989/1000=0.99): {bg=989, ru=2, mk=9}
    cs (995/1000=1.00): {sk=3, pt=1, en=1, cs=995}
    da (972/1000=0.97): {da=972, no=28}
    de (998/1000=1.00): {de=998, da=1, af=1}
    el (1000/1000=1.00): {el=1000}
    en (997/1000=1.00): {en=997, nl=1, cs=1, af=1}
    es (996/1000=1.00): {pt=4, es=996}
    et (997/1000=1.00): {de=1, fi=1, et=997, af=1}
    fi (999/1000=1.00): {fi=999, et=1}
    fr (999/1000=1.00): {it=1, fr=999}
    hu (998/1000=1.00): {id=1, hu=998, af=1}
    it (999/1000=1.00): {it=999, es=1}
    lt (998/1000=1.00): {lv=2, lt=998}
    lv (999/1000=1.00): {pt=1, lv=999}
    nl (975/1000=0.98): {de=1, sv=1, nl=975, af=23}
    pl (999/1000=1.00): {pl=999, nl=1}
    pt (994/1000=0.99): {it=1, pt=994, es=5}
    ro (999/1000=1.00): {ro=999, pt=1}
    sk (986/1000=0.99): {sl=2, sk=986, lt=1, et=1, nl=1, cs=9}
    sl (976/1000=0.98): {hr=24, sl=976}
    sv (991/1000=0.99): {da=2, no=7, sv=991}
    total: 20856/21000 = 0.993

## License

[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)
