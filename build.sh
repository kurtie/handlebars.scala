BASE="src/main/scala/com/gilt/handlebars/scala"
CLASSPATH=slf4j-api-1.6.4.jar
../scala-2.11.8/bin/scalac -g:none -optimise -classpath $CLASSPATH -d classes/ $BASE/*.scala $BASE/binding/*.scala $BASE/binding/dynamic/*.scala $BASE/context/*.scala $BASE/helper/*.scala $BASE/logging/*.scala $BASE/parser/*.scala $BASE/partial/*.scala $BASE/visitor/*.scala 
cd classes
jar cf ../handlebars_scala.jar com
