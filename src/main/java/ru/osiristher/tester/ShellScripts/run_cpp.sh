#!/bin/bash
# ToDo: need to limit time and memory for process!
# errors:
#  1 - too few arguments
#  2 - error in prog

usage(){
	echo "Usage: run_cpp.sh resources_path testing_data_path file_name input_name"
	exit 1
}

#if [[ $# -eq 0 ]] ; then
#    echo 'no path to resources in arguments'
#    usage
#    exit 1
#fi

#if [[ $# -eq 1 ]] ; then
#    echo 'no path to testing data in arguments'
#    usage
#    exit 1
#fi

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

#resources=$1
#testing_data=$2
pathToFile=$1
pathToInput=$2

$pathToFile < $pathToInput

if [[ $? -ne 0 ]] ; then
    echo "error $?"
    exit 2
fi

exit 0