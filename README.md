jvm-instr-classtransformer
=======================

This project is meant to illustrate how effectively you can alter the binary code of a class using java instrumentation. 

Application has a simple servlet, which accepts string http param and uses it in the response. 
The goal is to launch this app 'normally', perform a single request (no magic here) 
and then hook up the java agent, transform the servlet's code and show the changes in servlet's behaviour on the next response. 

Maven build lifecycle is helpful here: 
- during the `test` phase I show the 'normal' behaviour of the app, 
- while in `package` I attach the java agent, 
- finally, `integration-test` illustrates properly altered app code, which captures and logs every incoming request. 
 
Test classes trigger the test Jetty instances programatically and make real requests to them in order to replicate as 'real' experience as possible.  
Important events are logged to `application.log` file. 

## Running the 2 test phases
`mvn verify -q`

## Looking at the results
`cat application.log`