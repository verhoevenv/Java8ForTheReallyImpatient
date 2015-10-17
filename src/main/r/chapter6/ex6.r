mydata = read.delim("out/sorting.csv")
attach(mydata)
plot(arrsize,duration,col=ifelse(sorttype=="SINGLE","red","black"))
