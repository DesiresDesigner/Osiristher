# Osiristher
 Osiristher is the system of automatic testing of students laboratory works.
 Osiristher Native is the testing part of main System/

Error Codes
pattern: [moduleID][errorID][userID][taskID][message]
0 - no module errors
    0 - no errors in source
    1 - compile error
    2 - not correct result
1 - CompilerCaller
    1 - unknown language
    2 - no extension in filename
    3 - Input/Output problems
2 - Examiner
    1 - I/O problems
3 - TesterCaller
    1 - I/O problems
4 - Fixtures
5 - Config
    1 - nu such file