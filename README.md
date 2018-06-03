# Java parser for Cassandra base
cassandra base's link for this parser' : [cassandra - github][cassandra - github]

## Parseur's composition
The parser in java version witch follows the logic of the following UML diagram:
![UML](http://www.image-heberg.fr/files/15280285741702336688.jpg)

This project was created to import easily our data in our cassandra base.

## Usage
to use this parser , you need to compile the *.java files. To do that, please write the following command lines :
```bash
git clone https://github.com/alexConts/Cassandra_parsing_Java_version.git
cd Cassandra_parsing_Java_version/scr
javac *.java
```

Then 
```bash
java Parseur "[pathToYour'donnee.txt']"
```

If you just write `java Paseur`, you will import data of this project.

[cassandra - github]: https://github.com/alexConts/cassandra