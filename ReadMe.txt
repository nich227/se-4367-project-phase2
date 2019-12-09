 
Phase II consist of augmenting the coverage collection tool implemented in Phase-1 to trace more
information about program internal states, e.g., tracing accessible field/variable values
for the beginning of each method execution. The project ran against commons-dbutils(Contains license error) and commons-dbutilstest(No license  error)

p.s No dependancy needs to established in order to run the tool simply pull the project from the repository and follow the instructions stated above,

1. Go to agent folder run the comman "mvn clean install"

2. If common-dubuilts choosen as the test program then go into the common-dbuilts folder and run the command "mvn -Drat.skip=true package"
   else
If commons-dbutilstest choosen as the test program then go into the respective folder and run the comman "mvn test".

3. The previous steps done correctly will produce the trace files "stmt-cov", "Name-Trace", "Invariant-Trace". p.s in order to deduce more information regarding the respective trace files simply refer to the Final Report.


Note:

When employing other projects beyond the projects given in this folder there will be instances where Mvn build failure might occur whenever other project's test fails, and this is not cause by the coverage tool. And fixing the test case in the other projects is not the objective of this tool. 