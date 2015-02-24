#!/bin/bash
# errors:
#  1 - too few arguments
#  2 - error in prog

usage(){
	echo "Usage: run_cpp.sh resources_path testing_data_path file_name input_name"
	exit 1
}

if [[ $# -eq 0 ]] ; then
    echo 'no path to resources in arguments'
    usage
    exit 1
fi

if [[ $# -eq 1 ]] ; then
    echo 'no path to testing data in arguments'
    usage
    exit 1
fi

if [[ $# -eq 2 ]] ; then
    echo 'no file_name in arguments'
    usage
    exit 1
fi

if [[ $# -eq 3 ]] ; then
    echo 'no input file name in arguments'
    usage
    exit 1
fi

resources=$1
testing_data=$2
file_name=$3
input_name=$4

$resources/ExecFiles/$file_name < $testing_data/$input_name

if [[ $? -ne 0 ]] ; then
    echo "error $?"
    exit 2
fi

exit 0