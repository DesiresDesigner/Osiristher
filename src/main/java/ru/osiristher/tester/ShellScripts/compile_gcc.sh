#!/bin/bash
# errors:
#  1 - too few arguments
#  2 - gcc error

if [[ $# -eq 0 ]] ; then
    echo 'no file_name in arguments'
    exit 1
fi

if [[ $# -eq 1 ]] ; then
    echo 'no directory in arguments'
    exit 1
fi

file_name=$1
dir_name=$2

if [ ! -d "../../../../../resources/ExecFiles/$dir_name" ]; then
	mkdir ../../../../../resources/ExecFiles/$dir_name
fi

g++ -o ../../../../../resources/ExecFiles/$dir_name/$file_name.o ../../../../../resources/SourceCode/$dir_name/$file_name.cc -std=c++0x
#g++ -o $exec_name $file_name -std=c++0x

if [[ $? -ne 0 ]] ; then
    echo "gcc error $?"
    exit 2
fi

exit 0