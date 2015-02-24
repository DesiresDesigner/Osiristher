#!/bin/bash
# errors:
#  1 - too few arguments
#  2 - error in prog

if [[ $# -eq 0 ]] ; then
    echo 'no file_name in arguments'
    exit 1
fi

if [[ $# -eq 1 ]] ; then
    echo 'no input file name in arguments'
    exit 1
fi

file_name=$1
input_name=$2

../../../resources/ExecFiles/$file_name < /home/desiresdesigner/Projects/Osiristher/FitNesseRoot/testingData/$input_name

if [[ $? -ne 0 ]] ; then
    echo "error $?"
    exit 2
fi

exit 0
