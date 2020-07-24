***Awesome Package delivery***

A command line program that keeps a record of packages processed. Each package information consists of weight (in kg) and destination postal code. Think about these packages in the same way, when you send one using postal office. Data should be kept in memory (please don’t introduce any database engines). 

**What program does:**

-        read user input from console, user enters line consisting of weight of package and destination postal code

-        once per minute - write output to console, each line consists of postal code and total weight of all packages for that postal code

-        process user command “quit”, when user enters quit to command line as input, program should exit

-        take and process command line argument specified at program run – filename of file containing lines in same format as user can enter in command line. This is considered as initial load of package information. File should be placed in the resource folder.

-        handle invalid input of user (there is no maximum weight limit in our service, so you shoud better send all you goodies at one's and goodies of all other people you want ;-) )


**Sample input:**

3.4 08801

2 90005

12.56 08801

5.5 08079 

3.2 09300


**Input line format:**

<weight: positive number, >, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits> 


**Sample output (order by total weight):**

08801 15.960

08079 5.500

09300 3.200

90005 2.000


**Output line format:**

<postal code: fixed 5 digits><space><total weight: fixed 3 decimal places, . (dot) as decimal separator>

