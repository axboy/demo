package main

import (
	"time"
	"imooc/chapter2"
)

func main() {
	r := &chapter2.ReadFromFile{
		Path: "./access.log",
	}
	w := &chapter2.WriteToInfluxDB{

	}

	lp := &chapter2.LogProcess{
		Rc:    make(chan string),
		Wc:    make(chan string),
		Read:  r,
		Write: w,
	}

	go lp.Read.Read(lp.Rc)
	go lp.Process()
	go lp.Write.Write(lp.Wc)

	time.Sleep(1 * time.Second)
}
