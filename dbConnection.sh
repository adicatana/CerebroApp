echo 'Use flag -local or -global to setup connection to database.'

if [ "$1" = "-local" ]; then
  echo 'Setup local configuration'
  cp src/main/resources/application.properties.local src/main/resources/application.properties
  echo 'Setting up ssh tunnel'
  ssh -L 63333:db.doc.ic.ac.uk:5432 ad5915@shell1.doc.ic.ac.uk
fi

if [ "$1" = "-global" ]; then
  echo 'Preparing application for deploy'
  cp src/main/resources/application.properties.global src/main/resources/application.properties
fi

