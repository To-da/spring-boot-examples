Simple Application using Autoconfig for POI implementation
==========================================================


# To test autoconfig behavior - try:
Run project as:

```
mvn spring-boot:run -Pdep
```
to run without necessary dependency - project will fail on missing bean

```
mvn spring-boot:run -Pdepall
```
for all necessary dependencies - project will start properly with 'Property' value 30 - retrieved from application.properties

```
mvn spring-boot:run -Pbean
```
to run with all necessary dependencies + initialize own bean and rewrite the one registered by autoconfiguration (with 'Property' value 10)

# To test properties order behavior
Try to uncomment row 26 and then row 22 in Application.java.

# To test custom command
Run application in *depall* or *bean* profile and try to connect to it by console :

```
ssh -p 2000 toda@localhost
```
password is *pass*  (all can be found inside application.properties file) and try to input:
```
hello -h
```
,
```
man hello
```
,
```
hello Zombie
```


# Video
Related [youtube video](http://youtu.be/fEuXdeJPY7U)