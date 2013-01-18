# -*- coding: utf-8 -*-
"""
Converts a language-detection profile file into a Java class, with n-grams
data hard-coded into a hash map.

Text of the class is printed to standard out.
"""
import argparse
import json
import unicodedata

# This file is opened if no input file command line argument (-i) is specified
default_infile = "C:\ocr\lang-detect\profiles\el"

parser = argparse.ArgumentParser(add_help=True)
parser.add_argument('-i', nargs='?', help='profile filename', 
                    default='', required=False)
opts = parser.parse_args()
infile = opts.i
if infile == '':
  infile = default_infile

def readfile(filename):
  with open(filename, "r") as f:
    str = f.read()   
  return str

json_str = readfile(infile)
s = json.loads(json_str)

print """package com.rmtheis.langdetect.profile;

import java.util.HashMap;

import com.cybozu.labs.langdetect.util.LangProfile;
import com.cybozu.labs.langdetect.util.NGram;

public class %s {
  private static final String name = "%s";
  private static final HashMap<String, Integer> freq = new HashMap<String, Integer>();
  private static final int[] n_words = new int[NGram.N_GRAM];

  public %s() {
    init();  
  }

  public final LangProfile getLangProfile() {
    return new LangProfile(name, freq, n_words);
  }
  
  private void init() {""" % (s['name'].upper(), s['name'], s['name'].upper())

cnt = 0
for n in s['n_words']:
  print '    n_words[%s] = %s;' % (cnt, n)
  cnt = cnt + 1
print ''

cnt = 0
for key, value in s['freq'].iteritems():
  cnt = cnt + 1
  if cnt >= 4000:
    print """
    init2();
  }
  private static void init2() {
    """
    cnt = 0;
  key_str = unicodedata.normalize('NFKD', key).encode('utf-8', 'ignore')
  print '    freq.put("%s", %s);' % (key_str, value)

print """  }
  
}
"""
