#!/bin/bash

clear
# Directory containing test files
test_dir="./test_proj_6"

# Check if the directory exists
if [ ! -d "$test_dir" ]; then
    echo "Error: Directory $test_dir does not exist."
    exit 1
fi

# Check if ./simplec exists and is executable
if [ ! -x "./simplec" ]; then
    echo "Error: ./simplec is not found or is not executable."
    exit 1
fi

# Iterate through all files in the test directory
for file in "$test_dir"/*; do
    # Check if it's a regular file
    if [ -f "$file" ]; then
        echo "Running ./simplec $file"
        ./simplec "$file"
        echo "Done with $file"
        echo "------------------------"
    fi
done

echo "All tests completed."