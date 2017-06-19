heroku logs -n 1500 > logs/log.in.txt
echo 'Downloading log from heroku - last 1500 lines'
g++ -std=c++11 filter.cpp
./a.out logs/log.in.txt logs/log.out.txt
