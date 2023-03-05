config="
directory:
  source: from
  target: to

options:
  date:
    any:
      from: 2021-01-01
      to: 2022-12-31
  copy:
    created: true
    last_modified: true
"
  

mkdir from
mkdir to
cd from || exit
mkdir A
mkdir B
mkdir C
cd A || exit
mkdir AA
mkdir AB
touch A1.txt
touch A2.txt
touch A3.txt
touch -c -m -t 2401011225 A1.txt
touch -c -m -t 1901011225 A2.txt
touch -c -m -t 7001011225 A3.txt
cd AA || exit
touch AA1.txt
touch AA2.txt
touch AA3.txt
touch -c -m -t 2201011225 AA1.txt
touch -c -m -t 2205011225 AA2.txt
touch -c -m -t 2207011225 AA3.txt
cd ..
cd AB || exit
touch AB1.txt
touch AB2.txt
touch AB3.txt
touch -c -m -t 2212011225 AB1.txt
touch -c -m -t 2205011225 AB2.txt
touch -c -m -t 2207011225 AB3.txt
cd ..
cd ../B || exit
touch B1.txt
touch B2.txt
touch B3.txt
touch -c -m -t 2201011225 B1.txt 
touch -c -m -t 2205011225 B2.txt 
touch -c -m -t 2207011225 B3.txt 
cd ../C || exit
touch C1.txt
touch C3.txt
touch -c -m -t 2101011225 C1.txt
touch -c -m -t 2101021225 C3.txt
cd ../..
touch configuration.yaml
echo "${config}" > configuration.yaml
java -jar build/libs/directorySort.jar
echo "only files starting with B or C should be in the to directory"