package main

import (
	"imooc/chapter1"
	"time"
)

func main() {
	lp := &chapter1.LogProcess{
		Path:        "./access.log",
		InfluxDBDsn: "username&password",
		Rc:          make(chan string),
		Wc:          make(chan string),
	}

	go lp.ReadFromFile()
	go lp.Process()
	go lp.WriteToInfluxDB()

	time.Sleep(1 * time.Second)
}
