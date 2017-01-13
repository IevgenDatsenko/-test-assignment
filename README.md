Time series are stored in files with the following format:
1) Files are multiple plain text files in ASCII encoding
2) Each line contains exactly one record
3) Each record contains date and integer value; records are encoded like so: YYYYMMDD: X
4) Dates within single file are nonduplicate and sorted in ascending order
5) Files can be bigger than RAM available on target host

Create script which will merge arbitrary number of files, into one file. Result file should follow same format conventions as described above. Records with same date value should be merged into one by summing up Xvalues.
