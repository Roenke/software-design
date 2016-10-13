# Command Line Iterpreter

Installation
```
* Clone repository
* Open gradle project in IDEA
* Run
```
## Available Commands:
* assignment: `X=value`
* cat: `cat` - inveractive mode or `cat filename1 filename2` - files concatenation
* echo: `echo arg1 arg2` print all arguments separated by space
* pwd: `pwd` prints working directory
* wc: `wc` count number of lines, words, characters in input stream
* exit: `exit` terminate the interpreter
* grep: search by a regular expression
```
usage: grep [-h] [-i] [-w] [-A n] pattern [file]

Globally search a regular expression and print

positional arguments:
  pattern                regex pattern
  file                   path to file

optional arguments:
  -h, --help             show this help message and exit
  -i                     ignore case (default: false)
  -w                     search only entire words (default: false)
  -A n                   also  print  next   n   lines   after  each  match
                         (default: 0)
```
* any command supported by bash

## Quoting Support
* Supports strong quotes `'any exspre$$ion'`
* Supports weak quotes `"$MY$VARIABLE$USAGE"` with environment `MY=he, VARIABLE=ll, USAGE=lo` transforms into `hello`
* Supports weak quotes by default: it mean, that you can use environment variables without weak quoting `cat $FILE`
* Supports dynamic environment updating: <code>file=myfile.txt &#124; cat $FILE</code> 

## Examples
`pwd`

C:\Projects\software-design\my-shell

<code>pwd &#124; wc </code>

1 	1 	36

`cat build.gradle`

group 'com.spbau.bibaev.software.desing.shell'


`COMMAND=exit | $COMMAND`

## Continuous integration
[![Build Status](https://travis-ci.org/Roenke/software-design.svg?branch=ha1-shell)](https://travis-ci.org/Roenke/software-design)
