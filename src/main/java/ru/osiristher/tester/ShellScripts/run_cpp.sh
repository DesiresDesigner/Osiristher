#!/bin/bash
# ToDo: need to limit time and memory for process!
# errors:
#  1 - too few arguments
#  2 - error in prog

usage(){
	echo "Usage: run_cpp.sh full_path_to_exec full_path_to_input"
	exit 1
}

if [[ $# -eq 0 ]] ; then
    echo 'no file_name in arguments'
    usage
    exit 1
fi

if [[ $# -eq 1 ]] ; then
    echo 'no input file name in arguments'
    usage
    exit 1
fi

pathToFile=$1
pathToInput=$2

$pathToFile < $pathToInput

if [[ $? -ne 0 ]] ; then
    echo "error $?"
    exit 2
fi

exit 0