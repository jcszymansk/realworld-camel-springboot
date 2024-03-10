#!/usr/bin/env bash

# modified from https://imalsha-sg.medium.com/how-to-migrate-your-h2-databases-to-the-latest-h2-version-14b514ec7577

src_dir=./data
dest_dir=./data-new
username=
password=
oldver=2.1.210
newver=2.2.224
wget https://repo1.maven.org/maven2/com/h2database/h2/${newver}/h2-${newver}.jar
wget https://repo1.maven.org/maven2/com/h2database/h2/${oldver}/h2-${oldver}.jar

for filepath in $src_dir/*.mv.db; do
    dbname=$(basename "$filepath" .mv.db)

    # Export data from old db file to backup.zip
    echo "Exporting database..."
    java -cp h2-${oldver}.jar org.h2.tools.Script -url jdbc:h2:$src_dir/$dbname -user "$username" -password "$password" -script backup.zip -options compression zip
    rm -f $dest_dir/$dbname.mv.db
    # Import data from the backup.zip to the new db file
    echo "Importing data..."
    java -cp h2-${newver}.jar org.h2.tools.RunScript -url jdbc:h2:$dest_dir/$dbname -user "$username" -password "$password" -script ./backup.zip -options compression zip
    rm -f backup.zip
    echo "$dbname migrated succesfully"
done
rm -f h2-${oldver}.jar
rm -f h2-${newver}.jar